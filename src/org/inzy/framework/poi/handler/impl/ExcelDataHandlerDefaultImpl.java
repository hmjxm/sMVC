 package org.inzy.framework.poi.handler.impl;
 
 import org.inzy.framework.poi.handler.inter.IExcelDataHandler;
 
 
 
 
 
 
 
 
 public abstract class ExcelDataHandlerDefaultImpl
   implements IExcelDataHandler
 {
   private String[] needHandlerFields;
   
   public Object exportHandler(Object obj, String name, Object value)
   {
     return value;
   }
   
   public Object importHandler(Object obj, String name, Object value)
   {
     return value;
   }
   
   public String[] getNeedHandlerFields()
   {
     return this.needHandlerFields;
   }
   
   public void setNeedHandlerFields(String[] needHandlerFields)
   {
     this.needHandlerFields = needHandlerFields;
   }
}