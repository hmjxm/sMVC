package org.inzy.framework.poi.handler.inter;

import org.inzy.framework.poi.excel.entity.result.ExcelVerifyHanlderResult;

public abstract interface IExcelVerifyHandler
{
  public abstract String[] getNeedVerifyFields();
  
  public abstract ExcelVerifyHanlderResult verifyHandler(Object paramObject1, String paramString, Object paramObject2);
  
  public abstract void setNeedVerifyFields(String[] paramArrayOfString);
}