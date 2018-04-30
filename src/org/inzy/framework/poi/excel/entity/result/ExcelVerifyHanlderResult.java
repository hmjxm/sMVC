 package org.inzy.framework.poi.excel.entity.result;
 
 
 
 
 public class ExcelVerifyHanlderResult
 {
   private boolean success;
   
 
 
   private String msg;
   
 
 
 
   public ExcelVerifyHanlderResult() {}
   
 
 
 
   public ExcelVerifyHanlderResult(boolean success)
   {
     this.success = success;
   }
   
   public ExcelVerifyHanlderResult(boolean success, String msg) {
     this.success = success;
     this.msg = msg;
   }
   
   public boolean isSuccess() {
     return this.success;
   }
   
   public void setSuccess(boolean success) {
     this.success = success;
   }
   
   public String getMsg() {
     return this.msg;
   }
   
   public void setMsg(String msg) {
     this.msg = msg;
   }
}