package org.inzy.framework.poi.handler.inter;

public abstract interface IExcelDataHandler
{
  public abstract String[] getNeedHandlerFields();
  
  public abstract void setNeedHandlerFields(String[] paramArrayOfString);
  
  public abstract Object exportHandler(Object paramObject1, String paramString, Object paramObject2);
  
  public abstract Object importHandler(Object paramObject1, String paramString, Object paramObject2);
}