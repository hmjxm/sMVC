package org.inzy.framework.poi.excel.export.template;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.inzy.framework.poi.cache.ExcelCache;
import org.inzy.framework.poi.excel.annotation.ExcelTarget;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.inzy.framework.poi.excel.entity.params.ExcelExportEntity;
import org.inzy.framework.poi.excel.export.base.ExcelExportBase;
import org.inzy.framework.poi.exception.excel.ExcelExportException;
import org.inzy.framework.poi.exception.excel.enums.ExcelExportEnum;
import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
import org.inzy.framework.poi.util.POIPublicUtil;

public final class ExcelExportOfTemplateUtil extends ExcelExportBase {
	public Workbook createExcleByTemplate(TemplateExportParams params,
			Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
		if ((params == null) || (map == null)
				|| (StringUtils.isEmpty(params.getTemplateUrl()))) {
			throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
		}
		Workbook wb = null;
		try {
			wb = getCloneWorkBook(params);
			if (StringUtils.isNotEmpty(params.getSheetName())) {
				wb.setSheetName(0, params.getSheetName());
			}

			parseTemplate(wb.getSheetAt(0), map);
			if (dataSet != null) {
				this.dataHanlder = params.getDataHanlder();
				if (this.dataHanlder != null) {
					this.needHanlderList = Arrays.asList(this.dataHanlder
							.getNeedHandlerFields());
				}
				addDataToSheet(params, pojoClass, dataSet, wb.getSheetAt(0), wb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return wb;
	}

	private Workbook getCloneWorkBook(TemplateExportParams params)
			throws Exception {
		return ExcelCache.getWorkbook(params.getTemplateUrl(),
				params.getSheetNum());
	}

	private void addDataToSheet(TemplateExportParams params,
			Class<?> pojoClass, Collection<?> dataSet, Sheet sheet,
			Workbook workbook) throws Exception {
		Map<String, Integer> titlemap = getTitleMap(params, sheet);
		Drawing patriarch = sheet.createDrawingPatriarch();

		Field[] fileds = POIPublicUtil.getClassFields(pojoClass);
		ExcelTarget etarget = (ExcelTarget) pojoClass
				.getAnnotation(ExcelTarget.class);
		String targetId = null;
		if (etarget != null) {
			targetId = etarget.value();
		}

		List<ExcelExportEntity> excelParams = new ArrayList();
		getAllExcelField(null, targetId, fileds, excelParams, pojoClass, null);

		sortAndFilterExportField(excelParams, titlemap);
		short rowHeight = getRowHeight(excelParams);
		Iterator<?> its = dataSet.iterator();
		int index = sheet.getLastRowNum() + 1;
		int titleHeight = index;
		while (its.hasNext()) {
			Object t = its.next();

			index = index
					+ createCells(patriarch, index, t, excelParams, sheet,
							workbook, null, rowHeight);
		}

		mergeCells(sheet, excelParams, titleHeight);
	}

	private void sortAndFilterExportField(List<ExcelExportEntity> excelParams,
			Map<String, Integer> titlemap) {
		for (int i = excelParams.size() - 1; i >= 0; i--) {
			if ((((ExcelExportEntity) excelParams.get(i)).getList() != null)
					&& (((ExcelExportEntity) excelParams.get(i)).getList()
							.size() > 0)) {
				sortAndFilterExportField(
						((ExcelExportEntity) excelParams.get(i)).getList(),
						titlemap);
				if (((ExcelExportEntity) excelParams.get(i)).getList().size() == 0) {
					excelParams.remove(i);
				} else {
					((ExcelExportEntity) excelParams.get(i)).setOrderNum(i);
				}
			} else if (titlemap.containsKey(((ExcelExportEntity) excelParams
					.get(i)).getName())) {
				((ExcelExportEntity) excelParams.get(i)).setOrderNum(i);
			} else {
				excelParams.remove(i);
			}
		}

		sortAllParams(excelParams);
	}

	private Map<String, Integer> getTitleMap(TemplateExportParams params,
			Sheet sheet) {
		Row row = null;

		Map<String, Integer> titlemap = new HashMap();
		for (int j = 0; j < params.getHeadingRows(); j++) {
			row = sheet.getRow(j + params.getHeadingStartRow());
			Iterator<Cell> cellTitle = row.cellIterator();
			int i = row.getFirstCellNum();
			while (cellTitle.hasNext()) {
				Cell cell = (Cell) cellTitle.next();
				String value = cell.getStringCellValue();
				if (!StringUtils.isEmpty(value)) {
					titlemap.put(value, Integer.valueOf(i));
				}
				i++;
			}
		}
		return titlemap;
	}

	private void parseTemplate(Sheet sheet, Map<String, Object> map)
     throws Exception
   {
     Iterator<Row> rows = sheet.rowIterator();
     while (rows.hasNext()) {
    	 Row row = rows.next();
    	 for( int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
    		 setValueForCellByMap(row.getCell(i), map);
    	 }
     }
   }

	private void setValueForCellByMap(Cell cell, Map<String, Object> map)
			throws Exception {
		String oldString;
		try {
			oldString = cell.getStringCellValue();
		} catch (Exception e) {
			oldString = null;
			return;
		}
		if ((oldString != null) && (oldString.indexOf("{{") != -1)) {

			while (oldString.indexOf("{{") != -1) {
				String params = oldString.substring(
						oldString.indexOf("{{") + 2, oldString.indexOf("}}"));
				oldString = oldString.replace("{{" + params + "}}",
						getParamsValue(params.trim(), map));
			}
			cell.setCellValue(oldString);
		}
	}

	private String getParamsValue(String params, Map<String, Object> map)
			throws Exception {
		if (params.indexOf(".") != -1) {
			String[] paramsArr = params.split("\\.");
			return getValueDoWhile(map.get(paramsArr[0]), paramsArr, 1);
		}
		return map.containsKey(params) ? map.get(params).toString() : "";
	}

	private String getValueDoWhile(Object object, String[] paramsArr, int index)
			throws Exception {
		if (object == null) {
			return "";
		}
		if ((object instanceof Map)) {
			object = ((Map) object).get(paramsArr[index]);
		} else {
			object = POIPublicUtil.getMethod(paramsArr[index],
					object.getClass()).invoke(object, new Object[0]);
		}
		return index == paramsArr.length - 1 ? object.toString()
				: object == null ? "" : getValueDoWhile(object, paramsArr,
						++index);
	}
}