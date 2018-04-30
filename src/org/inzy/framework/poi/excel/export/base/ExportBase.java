 package org.inzy.framework.poi.excel.export.base;
 
 import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.inzy.framework.poi.excel.annotation.Excel;
import org.inzy.framework.poi.excel.annotation.ExcelCollection;
import org.inzy.framework.poi.excel.annotation.ExcelEntity;
import org.inzy.framework.poi.excel.entity.params.ComparatorExcelField;
import org.inzy.framework.poi.excel.entity.params.ExcelExportEntity;
import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
import org.inzy.framework.poi.util.POIPublicUtil;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExportBase
 {
   protected IExcelDataHandler dataHanlder;
   protected List<String> needHanlderList;
   
   public void getAllExcelField(String[] exclusions, String targetId, Field[] fields, List<ExcelExportEntity> excelParams, Class<?> pojoClass, List<Method> getMethods)
     throws Exception
   {
     List<String> exclusionsList = exclusions != null ? 
       Arrays.asList(exclusions) : null;
     
 
     for (int i = 0; i < fields.length; i++) {
       Field field = fields[i];
       
       if (!POIPublicUtil.isNotUserExcelUserThis(exclusionsList, field, 
         targetId))
       {
 
 
         if (field.getAnnotation(Excel.class) != null) {
           excelParams.add(createExcelExportEntity(field, targetId, 
             pojoClass, getMethods));
         } else if (POIPublicUtil.isCollection(field.getType())) {
           ExcelCollection excel = 
             (ExcelCollection)field.getAnnotation(ExcelCollection.class);
           ParameterizedType pt = (ParameterizedType)field
             .getGenericType();
           Class<?> clz = (Class)pt.getActualTypeArguments()[0];
           List<ExcelExportEntity> list = new ArrayList();
           getAllExcelField(exclusions, 
             StringUtils.isNotEmpty(excel.id()) ? excel.id() : 
             targetId, POIPublicUtil.getClassFields(clz), 
             list, clz, null);
           ExcelExportEntity excelEntity = new ExcelExportEntity();
           excelEntity.setName(getExcelName(excel.name(), targetId));
           excelEntity
             .setOrderNum(getCellOrder(excel.orderNum(), targetId));
           excelEntity.setMethod(POIPublicUtil.getMethod(field.getName(), 
             pojoClass));
           excelEntity.setList(list);
           excelParams.add(excelEntity);
         } else {
           List<Method> newMethods = new ArrayList();
           if (getMethods != null) {
             newMethods.addAll(getMethods);
           }
           newMethods.add(POIPublicUtil.getMethod(field.getName(), 
             pojoClass));
           ExcelEntity excel = (ExcelEntity)field.getAnnotation(ExcelEntity.class);
           getAllExcelField(exclusions, 
             StringUtils.isNotEmpty(excel.id()) ? excel.id() : 
             targetId, POIPublicUtil.getClassFields(field
             .getType()), excelParams, field.getType(), 
             newMethods);
         }
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
   private ExcelExportEntity createExcelExportEntity(Field field, String targetId, Class<?> pojoClass, List<Method> getMethods)
     throws Exception
   {
     Excel excel = (Excel)field.getAnnotation(Excel.class);
     ExcelExportEntity excelEntity = new ExcelExportEntity();
     try {
       excelEntity.setType(excel.type());
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     getExcelField(targetId, field, excelEntity, excel, pojoClass);
     if (getMethods != null) {
       List<Method> newMethods = new ArrayList();
       newMethods.addAll(getMethods);
       newMethods.add(excelEntity.getMethod());
       excelEntity.setMethods(newMethods);
     }
     return excelEntity;
   }
   
 
 
 
 
 
 
   public String getExcelName(String exportName, String targetId)
   {
     if (exportName.indexOf(",") < 0) {
       return exportName;
     }
     String[] arr = exportName.split(",");
     String[] arrayOfString1; int j = (arrayOfString1 = arr).length; for (int i = 0; i < j; i++) { String str = arrayOfString1[i];
       if (str.indexOf(targetId) != -1) {
         return str.split("_")[0];
       }
     }
     return null;
   }
   
 
 
 
 
 
 
   public int getCellOrder(String orderNum, String targetId)
   {
     if ((isInteger(orderNum)) || (targetId == null)) {
       return Integer.valueOf(orderNum).intValue();
     }
     String[] arr = orderNum.split(",");
     String[] arrayOfString1;
     int j = (arrayOfString1 = arr).length; for (int i = 0; i < j; i++) { String str = arrayOfString1[i];
       String[] temp = str.split("_");
       if (targetId.equals(temp[1])) {
         return Integer.valueOf(temp[0]).intValue();
       }
     }
     return 0;
   }
   
 
   public boolean isInteger(String value)
   {
     try
     {
       Integer.parseInt(value);
       return true;
     } catch (NumberFormatException e) {}
     return false;
   }
   
 
 
 
 
 
 
 
 
 
 
 
   private void getExcelField(String targetId, Field field, ExcelExportEntity excelEntity, Excel excel, Class<?> pojoClass)
     throws Exception
   {
     excelEntity.setName(getExcelName(excel.name(), targetId));
     excelEntity.setWidth(excel.width());
     excelEntity.setHeight(excel.height());
     excelEntity.setNeedMerge(excel.needMerge());
     excelEntity.setMergeVertical(excel.mergeVertical());
     excelEntity.setMergeRely(excel.mergeRely());
     excelEntity.setReplace(excel.replace());
     excelEntity.setOrderNum(getCellOrder(excel.orderNum(), targetId));
     excelEntity.setWrap(excel.isWrap());
     excelEntity.setExportImageType(excel.imageType());
     excelEntity.setDatabaseFormat(excel.databaseFormat());
     excelEntity
       .setFormat(StringUtils.isNotEmpty(excel.exportFormat()) ? excel
       .exportFormat() : excel.format());
     String fieldname = field.getName();
     excelEntity.setMethod(POIPublicUtil.getMethod(fieldname, pojoClass));
   }
   
 
 
 
 
 
   public short getRowHeight(List<ExcelExportEntity> excelParams)
   {
     int maxHeight = 0;
     for (int i = 0; i < excelParams.size(); i++) {
       maxHeight = maxHeight > ((ExcelExportEntity)excelParams.get(i)).getHeight() ? maxHeight : 
         ((ExcelExportEntity)excelParams.get(i)).getHeight();
       if (((ExcelExportEntity)excelParams.get(i)).getList() != null) {
         for (int j = 0; j < ((ExcelExportEntity)excelParams.get(i)).getList().size(); j++) {
           maxHeight = 
             maxHeight > ((ExcelExportEntity)((ExcelExportEntity)excelParams.get(i)).getList().get(j)).getHeight() ? maxHeight : 
             ((ExcelExportEntity)((ExcelExportEntity)excelParams.get(i)).getList().get(j)).getHeight();
         }
       }
     }
     return (short)(maxHeight * 50);
   }
   
 
 
   public void sortAllParams(List<ExcelExportEntity> excelParams)
   {
     Collections.sort(excelParams, new ComparatorExcelField());
     for (ExcelExportEntity entity : excelParams) {
       if (entity.getList() != null) {
         Collections.sort(entity.getList(), new ComparatorExcelField());
       }
     }
   }
   
 
 
 
 
 
 
 
   public Object getCellValue(ExcelExportEntity entity, Object obj)
     throws Exception
   {
     Object value = entity.getMethods() != null ? getFieldBySomeMethod(
       entity.getMethods(), obj) : entity.getMethod().invoke(obj, 
       new Object[0]);
     if ((this.needHanlderList != null) && 
       (this.needHanlderList.contains(entity.getName()))) {
       value = this.dataHanlder.exportHandler(obj, entity.getName(), value);
     } else if (StringUtils.isNotEmpty(entity.getFormat())) {
       value = formatValue(value, entity);
     } else if ((entity.getReplace() != null) && 
       (entity.getReplace().length > 0)) {
       value = replaceValue(entity.getReplace(), String.valueOf(value));
     }
     return value == null ? "" : value.toString();
   }
   
   private Object replaceValue(String[] replace, String value) {
     String[] arrayOfString1;
     int j = (arrayOfString1 = replace).length; for (int i = 0; i < j; i++) { String str = arrayOfString1[i];
       String[] temp = str.split("_");
       if (value.equals(temp[1])) {
         value = temp[0];
         break;
       }
     }
     return value;
   }
   
   private Object formatValue(Object value, ExcelExportEntity entity) throws Exception
   {
     Date temp = null;
     if ((value instanceof String)) {
       SimpleDateFormat format = new SimpleDateFormat(
         entity.getDatabaseFormat());
       temp = format.parse(value.toString());
     } else if ((value instanceof Date)) {
       temp = (Date)value;
     }
     if (temp != null) {
       SimpleDateFormat format = new SimpleDateFormat(entity.getFormat());
       value = format.format(temp);
     }
     return value;
   }
   
 
 
 
 
 
 
 
   public Object getFieldBySomeMethod(List<Method> list, Object t)
     throws Exception
   {
     for (Method m : list) {
       if (t == null) {
         t = "";
         break;
       }
       t = m.invoke(t, new Object[0]);
     }
     return t;
   }
}