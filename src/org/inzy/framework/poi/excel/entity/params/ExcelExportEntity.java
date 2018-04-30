 package org.inzy.framework.poi.excel.entity.params;
 
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelExportEntity
   extends ExcelBaseEntity
 {
   private int width;
   private int height;
   private int exportImageType;
   private int orderNum;
   private boolean isWrap;
   private boolean needMerge;
   private boolean mergeVertical;
   private int[] mergeRely;
   private String cellFormula;
   private List<ExcelExportEntity> list;
   
   public int getWidth()
   {
     return this.width;
   }
   
   public void setWidth(int width) {
     this.width = width;
   }
   
   public List<ExcelExportEntity> getList() {
     return this.list;
   }
   
   public void setList(List<ExcelExportEntity> list) {
     this.list = list;
   }
   
   public int getHeight() {
     return this.height;
   }
   
   public void setHeight(int height) {
     this.height = height;
   }
   
   public int getOrderNum() {
     return this.orderNum;
   }
   
   public void setOrderNum(int orderNum) {
     this.orderNum = orderNum;
   }
   
   public boolean isWrap() {
     return this.isWrap;
   }
   
   public void setWrap(boolean isWrap) {
     this.isWrap = isWrap;
   }
   
   public boolean isNeedMerge() {
     return this.needMerge;
   }
   
   public void setNeedMerge(boolean needMerge) {
     this.needMerge = needMerge;
   }
   
   public int[] getMergeRely() {
     return this.mergeRely;
   }
   
   public void setMergeRely(int[] mergeRely) {
     this.mergeRely = mergeRely;
   }
   
   public int getExportImageType() {
     return this.exportImageType;
   }
   
   public void setExportImageType(int exportImageType) {
     this.exportImageType = exportImageType;
   }
   
   public String getCellFormula() {
     return this.cellFormula;
   }
   
   public void setCellFormula(String cellFormula) {
     this.cellFormula = cellFormula;
   }
   
   public boolean isMergeVertical() {
     return this.mergeVertical;
   }
   
   public void setMergeVertical(boolean mergeVertical) {
     this.mergeVertical = mergeVertical;
   }
}