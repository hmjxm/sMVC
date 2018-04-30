 package org.inzy.framework.poi.excel.entity.params;
 
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelImportEntity
   extends ExcelBaseEntity
 {
   private String collectionName;
   private String saveUrl;
   private int saveType;
   private String classType;
   private ExcelVerifyEntity verify;
   private List<ExcelImportEntity> list;
   
   public List<ExcelImportEntity> getList()
   {
     return this.list;
   }
   
   public void setList(List<ExcelImportEntity> list) {
     this.list = list;
   }
   
   public String getSaveUrl() {
     return this.saveUrl;
   }
   
   public void setSaveUrl(String saveUrl) {
     this.saveUrl = saveUrl;
   }
   
   public String getClassType() {
     return this.classType;
   }
   
   public void setClassType(String classType) {
     this.classType = classType;
   }
   
   public String getCollectionName() {
     return this.collectionName;
   }
   
   public void setCollectionName(String collectionName) {
     this.collectionName = collectionName;
   }
   
   public int getSaveType() {
     return this.saveType;
   }
   
   public void setSaveType(int saveType) {
     this.saveType = saveType;
   }
   
   public ExcelVerifyEntity getVerify() {
     return this.verify;
   }
   
   public void setVerify(ExcelVerifyEntity verify) {
     this.verify = verify;
   }
}