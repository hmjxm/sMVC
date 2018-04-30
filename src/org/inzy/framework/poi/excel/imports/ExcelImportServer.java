package org.inzy.framework.poi.excel.imports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.inzy.framework.poi.excel.annotation.Excel;
import org.inzy.framework.poi.excel.annotation.ExcelTarget;
import org.inzy.framework.poi.excel.annotation.ExcelVerify;
import org.inzy.framework.poi.excel.entity.ImportParams;
import org.inzy.framework.poi.excel.entity.params.ExcelCollectionParams;
import org.inzy.framework.poi.excel.entity.params.ExcelImportEntity;
import org.inzy.framework.poi.excel.entity.params.ExcelVerifyEntity;
import org.inzy.framework.poi.excel.entity.result.ExcelImportResult;
import org.inzy.framework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.inzy.framework.poi.excel.imports.verifys.VerifyHandlerServer;
import org.inzy.framework.poi.util.POIPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelImportServer {
	private static final Logger logger = LoggerFactory
			.getLogger(ExcelImportServer.class);

	private CellValueServer cellValueServer;

	private VerifyHandlerServer verifyHandlerServer;

	private boolean verfiyFail = false;

	public ExcelImportServer() {
		this.cellValueServer = new CellValueServer();
		this.verifyHandlerServer = new VerifyHandlerServer();
	}

	public ExcelImportResult importExcelByIs(InputStream inputstream,
			Class<?> pojoClass, ImportParams params) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Excel import start ,class is {}", pojoClass);
		}
		List<T> result = new ArrayList();
		Workbook book = null;
		boolean isXSSFWorkbook = true;
		if (!inputstream.markSupported()) {
			inputstream = new PushbackInputStream(inputstream, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inputstream)) {
			book = new HSSFWorkbook(inputstream);
			isXSSFWorkbook = false;
		} else if (POIXMLDocument.hasOOXMLHeader(inputstream)) {
			book = new XSSFWorkbook(OPCPackage.open(inputstream));
		}

		for (int i = 0; i < params.getSheetNum(); i++) {
			Map<String, PictureData> pictures;
			if (isXSSFWorkbook) {
				pictures = POIPublicUtil.getSheetPictrues07(
						(XSSFSheet) book.getSheetAt(i), (XSSFWorkbook) book);
			} else {
				pictures = POIPublicUtil.getSheetPictrues03(
						(HSSFSheet) book.getSheetAt(i), (HSSFWorkbook) book);
			}
			result.addAll(importExcel(result, book.getSheetAt(i), pojoClass,
					params, pictures));
		}
		if (params.isNeedSave()) {
			saveThisExcel(params, pojoClass, isXSSFWorkbook, book);
		}
		return new ExcelImportResult(result, this.verfiyFail, book);
	}

	private void saveThisExcel(ImportParams params, Class<?> pojoClass,
			boolean isXSSFWorkbook, Workbook book) throws Exception {
		String path = POIPublicUtil.getWebRootPath(getSaveExcelUrl(params,
				pojoClass));
		File savefile = new File(path);
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyMMddHHmmss");
		FileOutputStream fos = new FileOutputStream(path + "/"
				+ format.format(new Date()) + "_"
				+ Math.round(Math.random() * 100000.0D)
				+ (isXSSFWorkbook ? ".xlsx" : ".xls"));
		book.write(fos);
		fos.close();
	}

	private String getSaveExcelUrl(ImportParams params, Class<?> pojoClass)
			throws Exception {
		String url = "";
		if (params.getSaveUrl().equals("upload/excelUpload")) {
			url = pojoClass.getName().split("\\.")[(pojoClass.getName().split(
					"\\.").length - 1)];
			return params.getSaveUrl() + "/"
					+ url.substring(0, url.lastIndexOf("Entity"));
		}
		return params.getSaveUrl();
	}

	private <T> List<T> importExcel(Collection<T> result, Sheet sheet,
			Class<?> pojoClass, ImportParams params,
			Map<String, PictureData> pictures) throws Exception {
		List collection = new ArrayList();
		Map<String, ExcelImportEntity> excelParams = new HashMap();
		List<ExcelCollectionParams> excelCollection = new ArrayList();
		Field[] fileds = POIPublicUtil.getClassFields(pojoClass);
		ExcelTarget etarget = (ExcelTarget) pojoClass
				.getAnnotation(ExcelTarget.class);
		String targetId = null;
		if (etarget != null) {
			targetId = etarget.value();
		}
		getAllExcelField(targetId, fileds, excelParams, excelCollection,
				pojoClass, null);
		Iterator<Row> rows = sheet.rowIterator();
		for (int j = 0; j < params.getTitleRows(); j++) {
			rows.next();
		}
		Row row = null;

		Map<Integer, String> titlemap = new HashMap();
		String value;
		for (int j = 0; j < params.getHeadRows(); j++) {
			row = (Row) rows.next();
			Iterator<Cell> cellTitle = row.cellIterator();
			int i = row.getFirstCellNum();
			while (cellTitle.hasNext()) {
				Cell cell = (Cell) cellTitle.next();
				value = cell.getStringCellValue();
				if (!StringUtils.isEmpty(value)) {
					titlemap.put(Integer.valueOf(i), value);
				}
				i++;
			}
		}
		Object object = null;

		while (rows.hasNext()) {
			row = (Row) rows.next();

			if (((row.getCell(params.getKeyIndex()) == null) || (StringUtils
					.isEmpty(getKeyValue(row.getCell(params.getKeyIndex())))))
					&& (object != null)) {
				for (ExcelCollectionParams param : excelCollection) {
					addListContinue(object, param, row, titlemap, targetId,
							pictures, params);
				}
			} else {
				object = POIPublicUtil.createObject(pojoClass, targetId);
				int i = row.getFirstCellNum();
				for (int le = row.getLastCellNum(); i < le; i++) {
					Cell cell = row.getCell(i);
					String titleString = (String) titlemap.get(Integer
							.valueOf(i));
					if (excelParams.containsKey(titleString)) {
						if (((ExcelImportEntity) excelParams.get(titleString))
								.getType() == 2) {
							String picId = row.getRowNum() + "_" + i;
							saveImage(object, picId, excelParams, titleString,
									pictures, params);
						} else {
							saveFieldValue(params, object, cell, excelParams,
									titleString, row);
						}
					}
				}
				for (ExcelCollectionParams param : excelCollection) {
					addListContinue(object, param, row, titlemap, targetId,
							pictures, params);
				}
				collection.add(object);
			}
		}
		return collection;
	}

	private void saveFieldValue(ImportParams params, Object object, Cell cell,
			Map<String, ExcelImportEntity> excelParams, String titleString,
			Row row) throws Exception {
		Object value = this.cellValueServer.getValue(params.getDataHanlder(),
				object, cell, excelParams, titleString);
		ExcelVerifyHanlderResult verifyResult = this.verifyHandlerServer
				.verifyData(object, value, titleString,
						((ExcelImportEntity) excelParams.get(titleString))
								.getVerify(), params.getVerifyHanlder());
		if (verifyResult.isSuccess()) {
			setValues((ExcelImportEntity) excelParams.get(titleString), object,
					value);
		} else {
			Cell errorCell = row.createCell(row.getLastCellNum());
			errorCell.setCellValue(verifyResult.getMsg());
			this.verfiyFail = true;
		}
	}

	private String getKeyValue(Cell cell) {
		Object obj = null;
		switch (cell.getCellType()) {
		case 1:
			obj = cell.getStringCellValue();
			break;
		case 4:
			obj = Boolean.valueOf(cell.getBooleanCellValue());
			break;
		case 0:
			obj = Double.valueOf(cell.getNumericCellValue());
		}

		return obj == null ? null : obj.toString();
	}

	private void saveImage(Object object, String picId,
			Map<String, ExcelImportEntity> excelParams, String titleString,
			Map<String, PictureData> pictures, ImportParams params)
			throws Exception {
		if (pictures == null) {
			return;
		}
		PictureData image = (PictureData) pictures.get(picId);
		byte[] data = image.getData();
		String fileName = "pic" + Math.round(Math.random() * 1.0E11D);
		fileName = fileName + "." + POIPublicUtil.getFileExtendName(data);
		if (((ExcelImportEntity) excelParams.get(titleString)).getSaveType() == 1) {
			String path = POIPublicUtil.getWebRootPath(getSaveUrl(
					(ExcelImportEntity) excelParams.get(titleString), object));
			File savefile = new File(path);
			if (!savefile.exists()) {
				savefile.mkdirs();
			}
			savefile = new File(path + "/" + fileName);
			FileOutputStream fos = new FileOutputStream(savefile);
			fos.write(data);
			fos.close();
			setValues(
					(ExcelImportEntity) excelParams.get(titleString),
					object,
					getSaveUrl(
							(ExcelImportEntity) excelParams.get(titleString),
							object)
							+ "/" + fileName);
		} else {
			setValues((ExcelImportEntity) excelParams.get(titleString), object,
					data);
		}
	}

	private String getSaveUrl(ExcelImportEntity excelImportEntity, Object object)
			throws Exception {
		String url = "";
		if (excelImportEntity.getSaveUrl().equals("upload")) {
			if ((excelImportEntity.getMethods() != null)
					&& (excelImportEntity.getMethods().size() > 0)) {
				object = getFieldBySomeMethod(excelImportEntity.getMethods(),
						object);
			}
			url = object.getClass().getName().split("\\.")[(object.getClass()
					.getName().split("\\.").length - 1)];
			return excelImportEntity.getSaveUrl() + "/"
					+ url.substring(0, url.lastIndexOf("Entity"));
		}
		return excelImportEntity.getSaveUrl();
	}

	private void addListContinue(Object object, ExcelCollectionParams param,
			Row row, Map<Integer, String> titlemap, String targetId,
			Map<String, PictureData> pictures, ImportParams params)
			throws Exception {
		Collection collection = (Collection) POIPublicUtil.getMethod(
				param.getName(), object.getClass()).invoke(object,
				new Object[0]);
		Object entity = POIPublicUtil.createObject(param.getType(), targetId);

		boolean isUsed = false;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			String titleString = (String) titlemap.get(Integer.valueOf(i));
			if (param.getExcelParams().containsKey(titleString)) {
				if (((ExcelImportEntity) param.getExcelParams()
						.get(titleString)).getType() == 2) {
					String picId = row.getRowNum() + "_" + i;
					saveImage(object, picId, param.getExcelParams(),
							titleString, pictures, params);
				} else {
					saveFieldValue(params, entity, cell,
							param.getExcelParams(), titleString, row);
				}
				isUsed = true;
			}
		}
		if (isUsed) {
			collection.add(entity);
		}
	}

	private void setValues(ExcelImportEntity entity, Object object, Object value)
			throws Exception {
		if (entity.getMethods() != null) {
			setFieldBySomeMethod(entity.getMethods(), object, value);
		} else {
			entity.getMethod().invoke(object, new Object[] { value });
		}
	}

	private void setFieldBySomeMethod(List<Method> setMethods, Object object,
			Object value) throws Exception {
		Object t = getFieldBySomeMethod(setMethods, object);
		((Method) setMethods.get(setMethods.size() - 1)).invoke(t,
				new Object[] { value });
	}

	private Object getFieldBySomeMethod(List<Method> list, Object t)
			throws Exception {
		for (int i = 0; i < list.size() - 1; i++) {
			Method m = (Method) list.get(i);
			t = m.invoke(t, new Object[0]);
		}
		return t;
	}

	private void getAllExcelField(String targetId, Field[] fields,
			Map<String, ExcelImportEntity> excelParams,
			List<ExcelCollectionParams> excelCollection, Class<?> pojoClass,
			List<Method> getMethods) throws Exception {
		ExcelImportEntity excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!POIPublicUtil.isNotUserExcelUserThis(null, field, targetId)) {

				if (POIPublicUtil.isCollection(field.getType())) {
					ExcelCollectionParams collection = new ExcelCollectionParams();
					collection.setName(field.getName());
					Map<String, ExcelImportEntity> temp = new HashMap();
					ParameterizedType pt = (ParameterizedType) field
							.getGenericType();
					Class<?> clz = (Class) pt.getActualTypeArguments()[0];
					collection.setType(clz);
					getExcelFieldList(targetId,
							POIPublicUtil.getClassFields(clz), clz, temp, null);
					collection.setExcelParams(temp);
					excelCollection.add(collection);
				} else if (POIPublicUtil.isJavaClass(field)) {
					addEntityToMap(targetId, field, excelEntity, pojoClass,
							getMethods, excelParams);
				} else {
					List<Method> newMethods = new ArrayList();
					if (getMethods != null) {
						newMethods.addAll(getMethods);
					}
					newMethods.add(POIPublicUtil.getMethod(field.getName(),
							pojoClass));
					getAllExcelField(targetId,
							POIPublicUtil.getClassFields(field.getType()),
							excelParams, excelCollection, field.getType(),
							newMethods);
				}
			}
		}
	}

	private void getExcelFieldList(String targetId, Field[] fields,
			Class<?> pojoClass, Map<String, ExcelImportEntity> temp,
			List<Method> getMethods) throws Exception {
		ExcelImportEntity excelEntity = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!POIPublicUtil.isNotUserExcelUserThis(null, field, targetId)) {

				if (POIPublicUtil.isJavaClass(field)) {
					addEntityToMap(targetId, field, excelEntity, pojoClass,
							getMethods, temp);
				} else {
					List<Method> newMethods = new ArrayList();
					if (getMethods != null) {
						newMethods.addAll(getMethods);
					}
					newMethods.add(POIPublicUtil.getMethod(field.getName(),
							pojoClass, field.getType()));
					getExcelFieldList(targetId,
							POIPublicUtil.getClassFields(field.getType()),
							field.getType(), temp, newMethods);
				}
			}
		}
	}

	private void addEntityToMap(String targetId, Field field,
			ExcelImportEntity excelEntity, Class<?> pojoClass,
			List<Method> getMethods, Map<String, ExcelImportEntity> temp)
			throws Exception {
		Excel excel = (Excel) field.getAnnotation(Excel.class);
		excelEntity = new ExcelImportEntity();
		excelEntity.setType(excel.type());
		excelEntity.setSaveUrl(excel.savePath());
		excelEntity.setSaveType(excel.imageType());
		excelEntity.setReplace(excel.replace());
		excelEntity.setDatabaseFormat(excel.databaseFormat());
		excelEntity.setVerify(getImportVerify(field));
		getExcelField(targetId, field, excelEntity, excel, pojoClass);
		if (getMethods != null) {
			List<Method> newMethods = new ArrayList();
			newMethods.addAll(getMethods);
			newMethods.add(excelEntity.getMethod());
			excelEntity.setMethods(newMethods);
		}
		temp.put(excelEntity.getName(), excelEntity);
	}

	private ExcelVerifyEntity getImportVerify(Field field) {
		ExcelVerify verify = (ExcelVerify) field
				.getAnnotation(ExcelVerify.class);
		if (verify != null) {
			ExcelVerifyEntity entity = new ExcelVerifyEntity();
			entity.setEmail(verify.isEmail());
			entity.setInterHandler(verify.interHandler());
			entity.setMaxLength(verify.maxLength());
			entity.setMinLength(verify.minLength());
			entity.setMobile(verify.isMobile());
			entity.setNotNull(verify.notNull());
			entity.setRegex(verify.regex());
			entity.setRegexTip(verify.regexTip());
			entity.setTel(verify.isTel());
			return entity;
		}
		return null;
	}

	private void getExcelField(String targetId, Field field,
			ExcelImportEntity excelEntity, Excel excel, Class<?> pojoClass)
			throws Exception {
		excelEntity.setName(getExcelName(excel.name(), targetId));
		String fieldname = field.getName();
		excelEntity.setMethod(POIPublicUtil.getMethod(fieldname, pojoClass,
				field.getType()));
		if (StringUtils.isEmpty(excel.importFormat())) {
			excelEntity.setFormat(excel.format());
		} else {
			excelEntity.setFormat(excel.importFormat());
		}
	}

	private String getExcelName(String exportName, String targetId) {
		if (exportName.indexOf("_") < 0) {
			return exportName;
		}
		String[] arr = exportName.split(",");
		String[] arrayOfString1;
		int j = (arrayOfString1 = arr).length;
		for (int i = 0; i < j; i++) {
			String str = arrayOfString1[i];
			if (str.indexOf(targetId) != -1) {
				return str.split("_")[0];
			}
		}
		return null;
	}
}