 package org.inzy.framework.poi.excel.entity.params;
 
 
 
 
 
 
 
 public class ExcelVerifyEntity
 {
   private boolean interHandler;
   
 
 
 
 
 
   private boolean notNull;
   
 
 
 
 
 
   private boolean isMobile;
   
 
 
 
 
 
   private boolean isTel;
   
 
 
 
 
 
   private boolean isEmail;
   
 
 
 
 
 
   private int minLength;
   
 
 
 
 
   private int maxLength;
   
 
 
 
 
   private String regex;
   
 
 
 
 
   private String regexTip;
   
 
 
 
 
 
   public boolean isInterHandler()
   {
     return this.interHandler;
   }
   
   public void setInterHandler(boolean interHandler) { this.interHandler = interHandler; }
   
   public boolean isNotNull() {
     return this.notNull;
   }
   
   public void setNotNull(boolean notNull) { this.notNull = notNull; }
   
   public boolean isMobile() {
     return this.isMobile;
   }
   
   public void setMobile(boolean isMobile) { this.isMobile = isMobile; }
   
   public boolean isTel() {
     return this.isTel;
   }
   
   public void setTel(boolean isTel) { this.isTel = isTel; }
   
   public boolean isEmail() {
     return this.isEmail;
   }
   
   public void setEmail(boolean isEmail) { this.isEmail = isEmail; }
   
   public int getMinLength() {
     return this.minLength;
   }
   
   public void setMinLength(int minLength) { this.minLength = minLength; }
   
   public int getMaxLength() {
     return this.maxLength;
   }
   
   public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
   
   public String getRegex() {
     return this.regex;
   }
   
   public void setRegex(String regex) { this.regex = regex; }
   
   public String getRegexTip() {
     return this.regexTip;
   }
   
   public void setRegexTip(String regexTip) { this.regexTip = regexTip; }
}