package org.inzy.framework.poi.excel.export.base;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.inzy.framework.poi.excel.entity.params.ExcelExportEntity;
import org.inzy.framework.poi.excel.entity.params.MergeEntity;
import org.inzy.framework.poi.util.POIPublicUtil;

public abstract class ExcelExportBase extends ExportBase {
	private int currentIndex = 0;

	public void mergeCells(Sheet sheet, List<ExcelExportEntity> excelParams,
			int titleHeight) {
		Map<Integer, int[]> mergeMap = getMergeDataMap(excelParams);
		Map<Integer, MergeEntity> mergeDataMap = new HashMap();
		if (mergeMap.size() == 0) {
			return;
		}

		Set<Integer> sets = mergeMap.keySet();
		for (int i = titleHeight; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (Iterator localIterator = sets.iterator(); localIterator
					.hasNext();) {
				Integer index = (Integer) localIterator.next();
				if (row.getCell(index.intValue()) == null) {
					((MergeEntity) mergeDataMap.get(index)).setEndRow(i);
				} else {
					String text = row.getCell(index.intValue())
							.getStringCellValue();
					if (StringUtils.isNotEmpty(text)) {
						hanlderMergeCells(index, i, text, mergeDataMap, sheet,
								row.getCell(index.intValue()),
								(int[]) mergeMap.get(index));
					} else {
						mergeCellOrContinue(index, mergeDataMap, sheet);
					}
				}
			}
		}
		for (Integer index : sets) {
			sheet.addMergedRegion(new CellRangeAddress(
					((MergeEntity) mergeDataMap.get(index)).getStartRow(),
					((MergeEntity) mergeDataMap.get(index)).getEndRow(), index
							.intValue(), index.intValue()));
		}
	}

	private void mergeCellOrContinue(Integer index,
			Map<Integer, MergeEntity> mergeDataMap, Sheet sheet) {
		if ((mergeDataMap.containsKey(index))
				&& (((MergeEntity) mergeDataMap.get(index)).getEndRow() != ((MergeEntity) mergeDataMap
						.get(index)).getStartRow())) {
			sheet.addMergedRegion(new CellRangeAddress(
					((MergeEntity) mergeDataMap.get(index)).getStartRow(),
					((MergeEntity) mergeDataMap.get(index)).getEndRow(), index
							.intValue(), index.intValue()));
			mergeDataMap.remove(index);
		}
	}

	private void hanlderMergeCells(Integer index, int rowNum, String text,
			Map<Integer, MergeEntity> mergeDataMap, Sheet sheet, Cell cell,
			int[] delys) {
		if (mergeDataMap.containsKey(index)) {
			if (checkIsEqualByCellContents(
					(MergeEntity) mergeDataMap.get(index), text, cell, delys,
					rowNum)) {
				((MergeEntity) mergeDataMap.get(index)).setEndRow(rowNum);
			} else {
				sheet.addMergedRegion(new CellRangeAddress(
						((MergeEntity) mergeDataMap.get(index)).getStartRow(),
						((MergeEntity) mergeDataMap.get(index)).getEndRow(),
						index.intValue(), index.intValue()));
				mergeDataMap.put(index,
						createMergeEntity(text, rowNum, cell, delys));
			}
		} else {
			mergeDataMap.put(index,
					createMergeEntity(text, rowNum, cell, delys));
		}
	}

	private String getCellNotNullText(Cell cell, int index, int rowNum) {
		String temp = cell.getRow().getCell(index).getStringCellValue();
		while (StringUtils.isEmpty(temp)) {
			temp = cell.getRow().getSheet().getRow(--rowNum).getCell(index)
					.getStringCellValue();
		}
		return temp;
	}

	private MergeEntity createMergeEntity(String text, int rowNum, Cell cell,
			int[] delys) {
		MergeEntity mergeEntity = new MergeEntity(text, rowNum, rowNum);
		List<String> list = new ArrayList(delys.length);
		mergeEntity.setRelyList(list);
		for (int i = 0; i < delys.length; i++) {
			list.add(getCellNotNullText(cell, delys[i], rowNum));
		}
		return mergeEntity;
	}

	private boolean checkIsEqualByCellContents(MergeEntity mergeEntity,
			String text, Cell cell, int[] delys, int rowNum) {
		if ((delys == null) || (delys.length == 0)) {
			return mergeEntity.getText().equals(text);
		}

		if (mergeEntity.getText().equals(text)) {
			for (int i = 0; i > delys.length; i++) {
				if (!getCellNotNullText(cell, delys[i], rowNum).equals(
						mergeEntity.getRelyList().get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private Map<Integer, int[]> getMergeDataMap(
			List<ExcelExportEntity> excelParams) {
		Map<Integer, int[]> mergeMap = new HashMap();

		int i = 0;
		for (ExcelExportEntity entity : excelParams) {
			if (entity.isMergeVertical()) {
				mergeMap.put(Integer.valueOf(i), entity.getMergeRely());
			}
			if (entity.getList() != null) {
				for (ExcelExportEntity inner : entity.getList()) {
					if (inner.isMergeVertical()) {
						mergeMap.put(Integer.valueOf(i), inner.getMergeRely());
					}
					i++;
				}
			} else {
				i++;
			}
		}
		return mergeMap;
	}

	public int createCells(Drawing patriarch, int index, Object t,
			List<ExcelExportEntity> excelParams, Sheet sheet,
			Workbook workbook, Map<String, HSSFCellStyle> styles,
			short rowHeight) throws Exception {
		Row row = sheet.createRow(index);
		row.setHeight(rowHeight);
		int maxHeight = 1;
		int cellNum = 0;
		int indexKey = createIndexCell(row, styles, index,
				(ExcelExportEntity) excelParams.get(0));
		cellNum += indexKey;
		int k = indexKey;
		for (int paramSize = excelParams.size(); k < paramSize; k++) {
			ExcelExportEntity entity = (ExcelExportEntity) excelParams.get(k);
			if (entity.getList() != null) {
				Collection<?> list = (Collection) entity.getMethod().invoke(t,
						new Object[0]);
				int listC = 0;
				for (Object obj : list) {
					createListCells(patriarch, index + listC, cellNum, obj,
							entity.getList(), sheet, workbook, styles);
					listC++;
				}
				cellNum += entity.getList().size();
				if ((list != null) && (list.size() > maxHeight)) {
					maxHeight = list.size();
				}
			} else {
				Object value = getCellValue(entity, t);
				if (entity.getType() == 1) {
					createStringCell(
							row,
							cellNum++,
							value == null ? "" : value.toString(),
							index % 2 == 0 ? getStyles(styles, false,
									entity.isWrap()) : getStyles(styles, true,
									entity.isWrap()), entity);
				} else {
					createImageCell(patriarch, entity, row, cellNum++,
							value == null ? "" : value.toString(), t);
				}
			}
		}

		cellNum = 0;
		k = indexKey;
		for (int paramSize = excelParams.size(); k < paramSize; k++) {
			ExcelExportEntity entity = (ExcelExportEntity) excelParams.get(k);
			if (entity.getList() != null) {
				cellNum += entity.getList().size();
			} else if (entity.isNeedMerge()) {
				sheet.addMergedRegion(new CellRangeAddress(index, index
						+ maxHeight - 1, cellNum, cellNum));
				cellNum++;
			}
		}
		return maxHeight;
	}

	private int createIndexCell(Row row, Map<String, HSSFCellStyle> styles,
			int index, ExcelExportEntity excelExportEntity) {
		if ((excelExportEntity.getName().equals("序号"))
				&& (excelExportEntity.getFormat().equals("isAddIndex"))) {
			createStringCell(row, 0, String.valueOf(this.currentIndex),
					index % 2 == 0 ? getStyles(styles, false, false)
							: getStyles(styles, true, false), null);
			this.currentIndex += 1;
			return 1;
		}
		return 0;
	}

	public CellStyle getStyles(Map<String, HSSFCellStyle> styles, boolean b,
			boolean wrap) {
		return null;
	}

	public void createListCells(Drawing patriarch, int index, int cellNum,
			Object obj, List<ExcelExportEntity> excelParams, Sheet sheet,
			Workbook workbook, Map<String, HSSFCellStyle> styles)
			throws Exception {
		Row row;

		if (sheet.getRow(index) == null) {
			row = sheet.createRow(index);
			row.setHeight(getRowHeight(excelParams));
		} else {
			row = sheet.getRow(index);
		}
		int k = 0;
		for (int paramSize = excelParams.size(); k < paramSize; k++) {
			ExcelExportEntity entity = (ExcelExportEntity) excelParams.get(k);
			Object value = getCellValue(entity, obj);
			if (entity.getType() == 1) {
				createStringCell(
						row,
						cellNum++,
						value == null ? "" : value.toString(),
						row.getRowNum() % 2 == 0 ? getStyles(styles, false,
								entity.isWrap()) : getStyles(styles, true,
								entity.isWrap()), entity);
			} else {
				createImageCell(patriarch, entity, row, cellNum++,
						value == null ? "" : value.toString(), obj);
			}
		}
	}

	public void setCellWith(List<ExcelExportEntity> excelParams, Sheet sheet) {
		int index = 0;
		for (int i = 0; i < excelParams.size(); i++) {
			if (((ExcelExportEntity) excelParams.get(i)).getList() != null) {
				List<ExcelExportEntity> list = ((ExcelExportEntity) excelParams
						.get(i)).getList();
				for (int j = 0; j < list.size(); j++) {
					sheet.setColumnWidth(index,
							256 * ((ExcelExportEntity) list.get(j)).getWidth());
					index++;
				}
			} else {
				sheet.setColumnWidth(index,
						256 * ((ExcelExportEntity) excelParams.get(i))
								.getWidth());
				index++;
			}
		}
	}

	public void createStringCell(Row row, int index, String text,
			CellStyle style, ExcelExportEntity entity) {
		Cell cell = row.createCell(index);
		RichTextString Rtext = new HSSFRichTextString(text);
		cell.setCellValue(Rtext);
		if (style != null) {
			cell.setCellStyle(style);
		}
	}

	public void createImageCell(Drawing patriarch, ExcelExportEntity entity,
			Row row, int i, String string, Object obj) throws Exception {
		row.setHeight((short) (50 * entity.getHeight()));
		row.createCell(i);
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) i,
				row.getRowNum(), (short) (i + 1), row.getRowNum() + 1);
		if (StringUtils.isEmpty(string)) {
			return;
		}
		if (entity.getExportImageType() == 1) {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			try {
				String path = POIPublicUtil.getWebRootPath(string);
				path = path.replace("WEB-INF/classes/", "");
				path = path.replace("file:/", "");
				BufferedImage bufferImg = ImageIO.read(new File(path));
				ImageIO.write(
						bufferImg,
						string.substring(string.indexOf(".") + 1,
								string.length()), byteArrayOut);
				byte[] value = byteArrayOut.toByteArray();
				patriarch.createPicture(anchor, row.getSheet().getWorkbook()
						.addPicture(value, getImageType(value)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			byte[] value = (byte[]) (entity.getMethods() != null ? getFieldBySomeMethod(
					entity.getMethods(), obj) : entity.getMethod().invoke(obj,
					new Object[0]));
			if (value != null) {
				patriarch.createPicture(anchor, row.getSheet().getWorkbook()
						.addPicture(value, getImageType(value)));
			}
		}
	}

	public int getImageType(byte[] value) {
		String type = POIPublicUtil.getFileExtendName(value);
		if (type.equalsIgnoreCase("JPG"))
			return 5;
		if (type.equalsIgnoreCase("PNG")) {
			return 6;
		}
		return 5;
	}

	public int getFieldWidth(List<ExcelExportEntity> excelParams) {
		int length = -1;
		for (ExcelExportEntity entity : excelParams) {
			length += (entity.getList() != null ? entity.getList().size() : 1);
		}
		return length;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
}