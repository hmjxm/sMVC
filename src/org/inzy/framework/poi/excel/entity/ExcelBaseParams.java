 package org.inzy.framework.poi.excel.entity;
 
 import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
 
 
 
 
 
 
 
 public class ExcelBaseParams
 {
   private IExcelDataHandler dataHanlder;
   
   public IExcelDataHandler getDataHanlder()
   {
     return this.dataHanlder;
   }
   
   public void setDataHanlder(IExcelDataHandler dataHanlder) {
     this.dataHanlder = dataHanlder;
   }
}