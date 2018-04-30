 package org.inzy.framework.poi.exception.excel.enums;
 
 
 
 
 
 public enum ExcelImportEnum
 {
   GET_VALUE_ERROR("Excel 值获取失败");
   
   private String msg;
   
   private ExcelImportEnum(String msg) {
     this.msg = msg;
   }
   
   public String getMsg() {
     return this.msg;
   }
   
   public void setMsg(String msg) {
     this.msg = msg;
   }
}