 package org.inzy.framework.poi.exception.excel.enums;
 
 
 
 
 
 public enum ExcelExportEnum
 {
   PARAMETER_ERROR("Excel 导出   参数错误"), 
   EXPORT_ERROR("Excel导出错误");
   
   private String msg;
   
   private ExcelExportEnum(String msg) {
     this.msg = msg;
   }
   
   public String getMsg() {
     return this.msg;
   }
   
   public void setMsg(String msg) {
     this.msg = msg;
   }
}