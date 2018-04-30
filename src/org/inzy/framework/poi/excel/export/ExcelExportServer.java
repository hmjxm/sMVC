 package org.inzy.framework.poi.excel.export;
 
 import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.inzy.framework.poi.excel.annotation.ExcelTarget;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.entity.params.ExcelExportEntity;
import org.inzy.framework.poi.excel.export.base.ExcelExportBase;
import org.inzy.framework.poi.exception.excel.ExcelExportException;
import org.inzy.framework.poi.exception.excel.enums.ExcelExportEnum;
import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
import org.inzy.framework.poi.util.POIPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 public class ExcelExportServer
   extends ExcelExportBase
 {
   private static final Logger logger = LoggerFactory.getLogger(ExcelExportServer.class);
   
 
   private static final short cellFormat = HSSFDataFormat.getBuiltinFormat("TEXT");
   
 
   private final int MAX_NUM = 60000;
   
   public void createSheet(HSSFWorkbook workbook, ExportParams entity, Class<?> pojoClass, Collection<?> dataSet)
   {
     if (logger.isDebugEnabled()) {
       logger.debug("Excel export start ,class is {}", pojoClass);
     }
     if ((workbook == null) || (entity == null) || (pojoClass == null) || 
       (dataSet == null)) {
       throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
     }
     Sheet sheet = null;
     try {
       sheet = workbook.createSheet(entity.getSheetName());
     }
     catch (Exception e) {
       sheet = workbook.createSheet();
     }
     try {
       this.dataHanlder = entity.getDataHanlder();
       if (this.dataHanlder != null) {
         this.needHanlderList = Arrays.asList(this.dataHanlder
           .getNeedHandlerFields());
       }
       
       Map<String, HSSFCellStyle> styles = createStyles(workbook);
       Drawing patriarch = sheet.createDrawingPatriarch();
       List<ExcelExportEntity> excelParams = new ArrayList();
       if (entity.isAddIndex()) {
         excelParams.add(indexExcelEntity());
       }
       
       Field[] fileds = POIPublicUtil.getClassFields(pojoClass);
       ExcelTarget etarget = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
       String targetId = etarget == null ? null : etarget.value();
       getAllExcelField(entity.getExclusions(), targetId, fileds, 
         excelParams, pojoClass, null);
       sortAllParams(excelParams);
       int index = createHeaderAndTitle(entity, sheet, workbook, 
         excelParams);
       int titleHeight = index;
       setCellWith(excelParams, sheet);
       short rowHeight = getRowHeight(excelParams);
       setCurrentIndex(1);
       Iterator<?> its = dataSet.iterator();
       List<Object> tempList = new ArrayList();
       while (its.hasNext()) {
         Object t = its.next();
         
         index = index + createCells(patriarch, index, t, excelParams, sheet, workbook, styles, rowHeight);
         tempList.add(t);
         if (index >= 60000)
           break;
       }
       mergeCells(sheet, excelParams, titleHeight);
       
       its = dataSet.iterator();
       int i = 0; for (int le = tempList.size(); i < le; i++) {
         its.next();
         its.remove();
       }
       
       if (dataSet.size() > 0) {
         createSheet(workbook, entity, pojoClass, dataSet);
       }
     }
     catch (Exception e) {
       e.printStackTrace();
       throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, 
         e.getCause());
     }
   }
   
   private ExcelExportEntity indexExcelEntity() {
     ExcelExportEntity entity = new ExcelExportEntity();
     entity.setOrderNum(0);
     entity.setName("序号");
     entity.setWidth(10);
     entity.setFormat("isAddIndex");
     return entity;
   }
   
   private int createHeaderAndTitle(ExportParams entity, Sheet sheet, HSSFWorkbook workbook, List<ExcelExportEntity> excelParams)
   {
     int rows = 0;int feildWidth = getFieldWidth(excelParams);
     if (entity.getTitle() != null) {
       rows += createHeaderRow(entity, sheet, workbook, feildWidth);
     }
     rows += createTitleRow(entity, sheet, workbook, rows, excelParams);
     sheet.createFreezePane(0, rows, 0, rows);
     return rows;
   }
   
 
 
 
 
 
 
 
   private int createTitleRow(ExportParams title, Sheet sheet, HSSFWorkbook workbook, int index, List<ExcelExportEntity> excelParams)
   {
     Row row = sheet.createRow(index);
     int rows = getRowNums(excelParams);
     row.setHeight((short)450);
     Row listRow = null;
     if (rows == 2) {
       listRow = sheet.createRow(index + 1);
       listRow.setHeight((short)450);
     }
     int cellIndex = 0;
     CellStyle titleStyle = getTitleStyle(workbook, title);
     int i = 0; for (int exportFieldTitleSize = excelParams.size(); i < exportFieldTitleSize; i++) {
       ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(i);
       createStringCell(row, cellIndex, entity.getName(), titleStyle, 
         entity);
       if (entity.getList() != null) {
         List<ExcelExportEntity> sTitel = entity.getList();
         sheet.addMergedRegion(new CellRangeAddress(index, index, 
           cellIndex, cellIndex + sTitel.size() - 1));
         int j = 0; for (int size = sTitel.size(); j < size; j++) {
           createStringCell(listRow, cellIndex, ((ExcelExportEntity)sTitel.get(j))
             .getName(), titleStyle, entity);
           cellIndex++;
         }
       } else if (rows == 2) {
         sheet.addMergedRegion(new CellRangeAddress(index, index + 1, 
           cellIndex, cellIndex));
       }
       cellIndex++;
     }
     return rows;
   }
   
 
 
 
 
 
 
   private int getRowNums(List<ExcelExportEntity> excelParams)
   {
     for (int i = 0; i < excelParams.size(); i++) {
       if (((ExcelExportEntity)excelParams.get(i)).getList() != null) {
         return 2;
       }
     }
     return 1;
   }
   
 
 
 
 
 
 
 
 
   public int createHeaderRow(ExportParams entity, Sheet sheet, HSSFWorkbook workbook, int feildWidth)
   {
     Row row = sheet.createRow(0);
     row.setHeight(entity.getTitleHeight());
     createStringCell(row, 0, entity.getTitle(), 
       getHeaderStyle(workbook, entity), null);
     sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, feildWidth));
     if (entity.getSecondTitle() != null) {
       row = sheet.createRow(1);
       row.setHeight(entity.getSecondTitleHeight());
       HSSFCellStyle style = workbook.createCellStyle();
       style.setAlignment((short)3);
       createStringCell(row, 0, entity.getSecondTitle(), style, null);
       sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, feildWidth));
       return 2;
     }
     return 1;
   }
   
 
 
 
 
 
 
   public HSSFCellStyle getTitleStyle(HSSFWorkbook workbook, ExportParams entity)
   {
     HSSFCellStyle titleStyle = workbook.createCellStyle();
     titleStyle.setFillForegroundColor(entity.getHeaderColor());
     titleStyle.setAlignment((short)2);
     titleStyle.setVerticalAlignment((short)1);
     titleStyle.setFillPattern((short)1);
     titleStyle.setWrapText(true);
     return titleStyle;
   }
   
 
 
 
 
 
 
   public HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook, ExportParams entity)
   {
     HSSFCellStyle titleStyle = workbook.createCellStyle();
     Font font = workbook.createFont();
     font.setFontHeightInPoints((short)24);
     titleStyle.setFont(font);
     titleStyle.setFillForegroundColor(entity.getColor());
     titleStyle.setAlignment((short)2);
     titleStyle.setVerticalAlignment((short)1);
     return titleStyle;
   }
   
   public HSSFCellStyle getTwoStyle(HSSFWorkbook workbook, boolean isWarp) {
     HSSFCellStyle style = workbook.createCellStyle();
     style.setBorderLeft((short)1);
     style.setBorderRight((short)1);
     style.setBorderBottom((short)1);
     style.setBorderTop((short)1);
     style.setFillForegroundColor((short)41);
     style.setFillPattern((short)1);
     style.setAlignment((short)2);
     style.setVerticalAlignment((short)1);
     style.setDataFormat(cellFormat);
     if (isWarp) {
       style.setWrapText(true);
     }
     return style;
   }
   
   public HSSFCellStyle getOneStyle(HSSFWorkbook workbook, boolean isWarp) {
     HSSFCellStyle style = workbook.createCellStyle();
     style.setBorderLeft((short)1);
     style.setBorderRight((short)1);
     style.setBorderBottom((short)1);
     style.setBorderTop((short)1);
     style.setAlignment((short)2);
     style.setVerticalAlignment((short)1);
     style.setDataFormat(cellFormat);
     if (isWarp) {
       style.setWrapText(true);
     }
     return style;
   }
   
   private Map<String, HSSFCellStyle> createStyles(HSSFWorkbook workbook) {
     Map<String, HSSFCellStyle> map = new HashMap();
     map.put("one", getOneStyle(workbook, false));
     map.put("oneWrap", getOneStyle(workbook, true));
     map.put("two", getTwoStyle(workbook, false));
     map.put("twoWrap", getTwoStyle(workbook, true));
     return map;
   }
   
   public CellStyle getStyles(Map<String, HSSFCellStyle> map, boolean needOne, boolean isWrap)
   {
     if ((needOne) && (isWrap)) {
       return (CellStyle)map.get("oneWrap");
     }
     if (needOne) {
       return (CellStyle)map.get("one");
     }
     if ((!needOne) && (isWrap)) {
       return (CellStyle)map.get("twoWrap");
     }
     return (CellStyle)map.get("two");
   }
}