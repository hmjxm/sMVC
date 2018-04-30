 package org.inzy.framework.poi.excel.imports;
 
 import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.inzy.framework.poi.excel.entity.params.ExcelImportEntity;
import org.inzy.framework.poi.exception.excel.ExcelImportException;
import org.inzy.framework.poi.exception.excel.enums.ExcelImportEnum;
import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 
 public class CellValueServer
 {
   private static final Logger logger = LoggerFactory.getLogger(CellValueServer.class);
   
   private List<String> hanlderList = null;
   
 
 
 
 
 
 
 
 
   public Object getValue(IExcelDataHandler dataHanlder, Object object, Cell cell, Map<String, ExcelImportEntity> excelParams, String titleString)
     throws Exception
   {
     ExcelImportEntity entity = (ExcelImportEntity)excelParams.get(titleString);
     Method setMethod = (entity.getMethods() != null) && 
       (entity.getMethods().size() > 0) ? (Method)entity.getMethods().get(
       entity.getMethods().size() - 1) : entity.getMethod();
     Type[] ts = setMethod.getGenericParameterTypes();
     String xclass = ts[0].toString();
     Object result = getCellValue(xclass, cell, entity);
     result = replaceValue(entity.getReplace(), result);
     result = hanlderValue(dataHanlder, object, result, titleString);
     return getValueByType(xclass, result);
   }
   
 
 
 
 
 
   private Object getValueByType(String xclass, Object result)
   {
     try
     {
       if (xclass.equals("class java.util.Date")) {
         return result;
       }
       if ((xclass.equals("class java.lang.Boolean")) || 
         (xclass.equals("boolean"))) {
         return Boolean.valueOf(String.valueOf(result));
       }
       if ((xclass.equals("class java.lang.Double")) || (xclass.equals("double"))) {
         return Double.valueOf(String.valueOf(result));
       }
       if ((xclass.equals("class java.lang.Long")) || (xclass.equals("long"))) {
         return Long.valueOf(String.valueOf(result));
       }
       if ((xclass.equals("class java.lang.Integer")) || (xclass.equals("int"))) {
         return Integer.valueOf(String.valueOf(result));
       }
       return String.valueOf(result);
     } catch (NumberFormatException e) {
       e.printStackTrace();
       throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
     }
   }
   
 
 
 
 
 
 
 
 
 
   private Object hanlderValue(IExcelDataHandler dataHanlder, Object object, Object result, String titleString)
   {
     if (dataHanlder == null) {
       return result;
     }
     if (this.hanlderList == null) {
       this.hanlderList = Arrays.asList(dataHanlder.getNeedHandlerFields());
     }
     if (this.hanlderList.contains(titleString)) {
       return dataHanlder.importHandler(object, titleString, result);
     }
     return result;
   }
   
 
 
 
 
 
 
   private Object replaceValue(String[] replace, Object result)
   {
     if ((replace != null) && (replace.length > 0)) {
       String temp = String.valueOf(result);
       
       for (int i = 0; i < replace.length; i++) {
         String[] tempArr = replace[i].split("_");
         if (temp.equals(tempArr[0])) {
           return tempArr[1];
         }
       }
     }
     return result;
   }
   
 
 
 
 
 
 
 
 
   private Object getCellValue(String xclass, Cell cell, ExcelImportEntity entity)
   {
     if (cell == null) {
       return "";
     }
     Object result = null;
     
     if (xclass.equals("class java.util.Date")) {
       if (cell.getCellType() == 0)
       {
         result = cell.getDateCellValue();
       } else {
         cell.setCellType(1);
         result = getDateData(entity, cell.getStringCellValue());
       }
     } else if (cell.getCellType() == 0) {
       result = Double.valueOf(cell.getNumericCellValue());
     } else if (4 == cell.getCellType()) {
       result = Boolean.valueOf(cell.getBooleanCellValue());
     } else {
       cell.setCellType(1);
       result = cell.getStringCellValue();
     }
     return result;
   }
   
 
 
 
 
 
 
 
 
   private Date getDateData(ExcelImportEntity entity, String value)
   {
     if ((StringUtils.isNotEmpty(entity.getFormat())) && 
       (StringUtils.isNotEmpty(value))) {
       SimpleDateFormat format = new SimpleDateFormat(entity.getFormat());
       try {
         return format.parse(value);
       } catch (ParseException e) {
         logger.error("时间格式化失败,格式化:{},值:{}", entity.getFormat(), value);
         throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
       }
     }
     return null;
   }
}