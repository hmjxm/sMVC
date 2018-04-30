 package org.inzy.framework.poi.excel.entity;
 
 
 
 public class TemplateExportParams
   extends ExcelBaseParams
 {
   private String templateUrl;
   
 
 
   public TemplateExportParams() {}
   
 
   public TemplateExportParams(String templateUrl)
   {
     this.templateUrl = templateUrl;
   }
   
   public TemplateExportParams(String templateUrl, int sheetNum)
   {
     this.templateUrl = templateUrl;
     this.sheetNum = sheetNum;
   }
   
   public TemplateExportParams(String templateUrl, String sheetName)
   {
     this.templateUrl = templateUrl;
     this.sheetName = sheetName;
   }
   
   public TemplateExportParams(String templateUrl, String sheetName, int sheetNum)
   {
     this.templateUrl = templateUrl;
     this.sheetName = sheetName;
     this.sheetNum = sheetNum;
   }
   
 
 
 
 
 
 
 
 
   private int sheetNum = 0;
   
 
 
   private String sheetName;
   
 
 
   private int headingRows = 1;
   
 
 
   private int headingStartRow = 2;
   
   public String getTemplateUrl() {
     return this.templateUrl;
   }
   
   public void setTemplateUrl(String templateUrl) {
     this.templateUrl = templateUrl;
   }
   
   public int getSheetNum() {
     return this.sheetNum;
   }
   
   public void setSheetNum(int sheetNum) {
     this.sheetNum = sheetNum;
   }
   
   public String getSheetName() {
     return this.sheetName;
   }
   
   public void setSheetName(String sheetName) {
     this.sheetName = sheetName;
   }
   
   public int getHeadingRows() {
     return this.headingRows;
   }
   
   public void setHeadingRows(int headingRows) {
     this.headingRows = headingRows;
   }
   
   public int getHeadingStartRow() {
     return this.headingStartRow;
   }
   
   public void setHeadingStartRow(int headingStartRow) {
     this.headingStartRow = headingStartRow;
   }
}