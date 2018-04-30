 package org.inzy.framework.poi.excel.entity;
 
 
 
 public class ExportParams
   extends ExcelBaseParams
 {
   private String title;
   
 
 
   public ExportParams() {}
   
 
 
   public ExportParams(String title, String sheetName)
   {
     this.title = title;
     this.sheetName = sheetName;
   }
   
   public ExportParams(String title, String secondTitle, String sheetName) {
     this.title = title;
     this.secondTitle = secondTitle;
     this.sheetName = sheetName;
   }
   
 
 
 
 
 
 
 
   private short titleHeight = 20;
   
 
 
   private String secondTitle;
   
 
 
   private short secondTitleHeight = 8;
   
 
 
   private String sheetName;
   
 
 
   private String[] exclusions;
   
 
 
   private boolean addIndex;
   
 
 
   private short color = 9;
   
 
 
 
   private short headerColor = 40;
   
   public String getTitle() {
     return this.title;
   }
   
   public void setTitle(String title) {
     this.title = title;
   }
   
   public String getSheetName() {
     return this.sheetName;
   }
   
   public void setSheetName(String sheetName) {
     this.sheetName = sheetName;
   }
   
   public short getColor() {
     return this.color;
   }
   
   public void setColor(short color) {
     this.color = color;
   }
   
   public String[] getExclusions() {
     return this.exclusions;
   }
   
   public void setExclusions(String[] exclusions) {
     this.exclusions = exclusions;
   }
   
   public String getSecondTitle() {
     return this.secondTitle;
   }
   
   public void setSecondTitle(String secondTitle) {
     this.secondTitle = secondTitle;
   }
   
   public short getHeaderColor() {
     return this.headerColor;
   }
   
   public void setHeaderColor(short headerColor) {
     this.headerColor = headerColor;
   }
   
   public short getTitleHeight() {
     return (short)(this.titleHeight * 50);
   }
   
   public void setTitleHeight(short titleHeight) {
     this.titleHeight = titleHeight;
   }
   
   public short getSecondTitleHeight() {
     return (short)(this.secondTitleHeight * 50);
   }
   
   public void setSecondTitleHeight(short secondTitleHeight) {
     this.secondTitleHeight = secondTitleHeight;
   }
   
   public boolean isAddIndex() {
     return this.addIndex;
   }
   
   public void setAddIndex(boolean addIndex) {
     this.addIndex = addIndex;
   }
}