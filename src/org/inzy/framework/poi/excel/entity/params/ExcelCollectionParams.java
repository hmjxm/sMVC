 package org.inzy.framework.poi.excel.entity.params;
 
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelCollectionParams
 {
   private String name;
   private Class<?> type;
   private Map<String, ExcelImportEntity> excelParams;
   
   public String getName()
   {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public Class<?> getType() {
     return this.type;
   }
   
   public void setType(Class<?> type) {
     this.type = type;
   }
   
   public Map<String, ExcelImportEntity> getExcelParams() {
     return this.excelParams;
   }
   
   public void setExcelParams(Map<String, ExcelImportEntity> excelParams) {
     this.excelParams = excelParams;
   }
}