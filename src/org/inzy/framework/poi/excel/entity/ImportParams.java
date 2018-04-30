 package org.inzy.framework.poi.excel.entity;
 
 import org.inzy.framework.poi.handler.inter.IExcelVerifyHandler;
 
 
 
 
 
 
 
 
 
 public class ImportParams
   extends ExcelBaseParams
 {
   private int titleRows = 0;
   
 
 
   private int headRows = 1;
   
 
 
   private int startRows = 0;
   
 
 
   private int keyIndex = 0;
   
 
 
   private int sheetNum = 1;
   
 
 
   private boolean needSave = false;
   
 
 
 
   private String saveUrl = "upload/excelUpload";
   
   private IExcelVerifyHandler verifyHanlder;
   
 
   public int getTitleRows()
   {
     return this.titleRows;
   }
   
   public void setTitleRows(int titleRows) {
     this.titleRows = titleRows;
   }
   
   public int getStartRows() {
     return this.startRows;
   }
   
   public void setStartRows(int startRows) {
     this.startRows = startRows;
   }
   
   public int getSheetNum() {
     return this.sheetNum;
   }
   
   public void setSheetNum(int sheetNum) {
     this.sheetNum = sheetNum;
   }
   
   public int getKeyIndex() {
     return this.keyIndex;
   }
   
   public void setKeyIndex(int keyIndex) {
     this.keyIndex = keyIndex;
   }
   
   public boolean isNeedSave() {
     return this.needSave;
   }
   
   public void setNeedSave(boolean needSave) {
     this.needSave = needSave;
   }
   
   public String getSaveUrl() {
     return this.saveUrl;
   }
   
   public void setSaveUrl(String saveUrl) {
     this.saveUrl = saveUrl;
   }
   
   public IExcelVerifyHandler getVerifyHanlder() {
     return this.verifyHanlder;
   }
   
   public void setVerifyHanlder(IExcelVerifyHandler verifyHanlder) {
     this.verifyHanlder = verifyHanlder;
   }
   
   public int getHeadRows() {
     return this.headRows;
   }
   
   public void setHeadRows(int headRows) {
     this.headRows = headRows;
   }
}