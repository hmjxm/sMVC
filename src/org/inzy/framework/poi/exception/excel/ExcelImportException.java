 package org.inzy.framework.poi.exception.excel;
 
 import org.inzy.framework.poi.exception.excel.enums.ExcelImportEnum;
 
 
 
 
 
 
 
 public class ExcelImportException
   extends RuntimeException
 {
   private static final long serialVersionUID = 1L;
   private ExcelImportEnum type;
   
   public ExcelImportException() {}
   
   public ExcelImportException(String message)
   {
     super(message);
   }
   
   public ExcelImportException(ExcelImportEnum type) {
     super(type.getMsg());
     this.type = type;
   }
   
   public ExcelImportException(String message, ExcelImportEnum type) {
     super(message);
     this.type = type;
   }
   
   public ExcelImportException(ExcelImportEnum type, Throwable cause) {
     super(type.getMsg(), cause);
   }
   
   public ExcelImportEnum getType() {
     return this.type;
   }
   
   public void setType(ExcelImportEnum type) {
     this.type = type;
   }
}