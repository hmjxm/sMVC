 package org.inzy.framework.poi.word.parse.excel;
 
 import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.inzy.framework.poi.excel.annotation.ExcelTarget;
import org.inzy.framework.poi.excel.entity.params.ExcelExportEntity;
import org.inzy.framework.poi.excel.export.base.ExportBase;
import org.inzy.framework.poi.exception.word.WordExportException;
import org.inzy.framework.poi.exception.word.enmus.WordExportEnum;
import org.inzy.framework.poi.util.POIPublicUtil;
import org.inzy.framework.poi.word.entity.params.ExcelListEntity;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelEntityParse
   extends ExportBase
 {
   public void parseNextRowAndAddRow(XWPFTable table, int index, ExcelListEntity entity)
   {
     checkExcelParams(entity);
     
     Map<String, Integer> titlemap = getTitleMap(table, index, 
       entity.getHeadRows());
     try
     {
       Field[] fileds = POIPublicUtil.getClassFields(entity.getClazz());
       ExcelTarget etarget = (ExcelTarget)entity.getClazz().getAnnotation(
         ExcelTarget.class);
       String targetId = null;
       if (etarget != null) {
         targetId = etarget.value();
       }
       
       List<ExcelExportEntity> excelParams = new ArrayList();
       getAllExcelField(null, targetId, fileds, excelParams, 
         entity.getClazz(), null);
       
       sortAndFilterExportField(excelParams, titlemap);
       short rowHeight = getRowHeight(excelParams);
       Iterator<?> its = entity.getList().iterator();
       while (its.hasNext()) {
         Object t = its.next();
         index += createCells(index, t, excelParams, table, rowHeight);
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
 
   private int createCells(int index, Object t, List<ExcelExportEntity> excelParams, XWPFTable table, short rowHeight)
     throws Exception
   {
     XWPFTableRow row = table.createRow();
     row.setHeight(rowHeight);
     int maxHeight = 1;int cellNum = 0;
     int k = 0; for (int paramSize = excelParams.size(); k < paramSize; k++) {
       ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(k);
       if (entity.getList() != null) {
         Collection<?> list = (Collection)entity.getMethod().invoke(
           t, new Object[0]);
         int listC = 0;
         for (Object obj : list) {
           createListCells(index + listC, cellNum, obj, 
             entity.getList(), table);
           listC++;
         }
         cellNum += entity.getList().size();
         if ((list != null) && (list.size() > maxHeight)) {
           maxHeight = list.size();
         }
       } else {
         Object value = getCellValue(entity, t);
         if (entity.getType() == 1) {
           setCellValue(row, value, cellNum++);
         }
       }
     }
     
     cellNum = 0;
     k = 0; for (int paramSize = excelParams.size(); k < paramSize; k++) {
       ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(k);
       if (entity.getList() != null) {
         cellNum += entity.getList().size();
       } else if (entity.isNeedMerge()) {
         table.setCellMargins(index, index + maxHeight - 1, cellNum, 
           cellNum);
         cellNum++;
       }
     }
     return maxHeight;
   }
   
 
 
 
   public void createListCells(int index, int cellNum, Object obj, List<ExcelExportEntity> excelParams, XWPFTable table)
     throws Exception
   {
     XWPFTableRow row;
     
 
 
     if (table.getRow(index) == null) {
       row = table.createRow();
       row.setHeight(getRowHeight(excelParams));
     } else {
       row = table.getRow(index);
     }
     int k = 0; for (int paramSize = excelParams.size(); k < paramSize; k++) {
       ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(k);
       Object value = getCellValue(entity, obj);
       if (entity.getType() == 1) {
         setCellValue(row, value, cellNum++);
       }
     }
   }
   
   private void setCellValue(XWPFTableRow row, Object value, int cellNum) {
     if (row.getCell(cellNum++) != null) {
       row.getCell(cellNum - 1).setText(
         value == null ? "" : value.toString());
     } else {
       row.createCell().setText(value == null ? "" : value.toString());
     }
   }
   
 
 
 
 
 
 
 
   private void sortAndFilterExportField(List<ExcelExportEntity> excelParams, Map<String, Integer> titlemap)
   {
     for (int i = excelParams.size() - 1; i >= 0; i--) {
       if ((((ExcelExportEntity)excelParams.get(i)).getList() != null) && 
         (((ExcelExportEntity)excelParams.get(i)).getList().size() > 0)) {
         sortAndFilterExportField(((ExcelExportEntity)excelParams.get(i)).getList(), titlemap);
         if (((ExcelExportEntity)excelParams.get(i)).getList().size() == 0) {
           excelParams.remove(i);
         } else {
           ((ExcelExportEntity)excelParams.get(i)).setOrderNum(i);
         }
       }
       else if (titlemap.containsKey(((ExcelExportEntity)excelParams.get(i)).getName())) {
         ((ExcelExportEntity)excelParams.get(i)).setOrderNum(i);
       } else {
         excelParams.remove(i);
       }
     }
     
     sortAllParams(excelParams);
   }
   
 
 
 
 
 
 
 
   private Map<String, Integer> getTitleMap(XWPFTable table, int index, int headRows)
   {
     if (index < headRows) {
       throw new WordExportException(WordExportEnum.EXCEL_NO_HEAD);
     }
     Map<String, Integer> map = new HashMap();
     
     for (int j = 0; j < headRows; j++) {
       List<XWPFTableCell> cells = table.getRow(index - j - 1)
         .getTableCells();
       for (int i = 0; i < cells.size(); i++) {
         String text = ((XWPFTableCell)cells.get(i)).getText();
         if (StringUtils.isEmpty(text)) {
           throw new WordExportException(
             WordExportEnum.EXCEL_HEAD_HAVA_NULL);
         }
         map.put(text, Integer.valueOf(i));
       }
     }
     return map;
   }
   
   private static void checkExcelParams(ExcelListEntity entity) {
     if ((entity.getList() == null) || (entity.getClazz() == null)) {
       throw new WordExportException(WordExportEnum.EXCEL_PARAMS_ERROR);
     }
   }
}