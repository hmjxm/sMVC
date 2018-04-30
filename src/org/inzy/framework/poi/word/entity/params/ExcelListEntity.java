 package org.inzy.framework.poi.word.entity.params;
 
 import java.util.List;

import org.inzy.framework.poi.excel.entity.ExcelBaseParams;
import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
 
 
 
 
 
 public class ExcelListEntity
   extends ExcelBaseParams
 {
   private List<?> list;
   private Class<?> clazz;
   
   public ExcelListEntity() {}
   
   public ExcelListEntity(List<?> list, Class<?> clazz)
   {
     this.list = list;
     this.clazz = clazz;
   }
   
   public ExcelListEntity(List<?> list, Class<?> clazz, int headRows) {
     this.list = list;
     this.clazz = clazz;
     this.headRows = headRows;
   }
   
   public ExcelListEntity(List<?> list, Class<?> clazz, IExcelDataHandler dataHanlder)
   {
     this.list = list;
     this.clazz = clazz;
     setDataHanlder(dataHanlder);
   }
   
   public ExcelListEntity(List<?> list, Class<?> clazz, IExcelDataHandler dataHanlder, int headRows)
   {
     this.list = list;
     this.clazz = clazz;
     this.headRows = headRows;
     setDataHanlder(dataHanlder);
   }
   
 
 
 
 
 
 
 
 
 
 
 
   private int headRows = 1;
   
   public List<?> getList() {
     return this.list;
   }
   
   public void setList(List<?> list) {
     this.list = list;
   }
   
   public Class<?> getClazz() {
     return this.clazz;
   }
   
   public void setClazz(Class<?> clazz) {
     this.clazz = clazz;
   }
   
   public int getHeadRows() {
     return this.headRows;
   }
   
   public void setHeadRows(int headRows) {
     this.headRows = headRows;
   }
}