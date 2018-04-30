 package org.inzy.framework.poi.excel.imports.verifys;
 
 import org.apache.commons.lang.StringUtils;
import org.inzy.framework.poi.excel.entity.params.ExcelVerifyEntity;
import org.inzy.framework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.inzy.framework.poi.handler.inter.IExcelVerifyHandler;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class VerifyHandlerServer
 {
   public ExcelVerifyHanlderResult verifyData(Object object, Object value, String name, ExcelVerifyEntity verify, IExcelVerifyHandler iExcelVerifyHandler)
   {
     ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
     if (verify == null) {
       return result;
     }
     if (verify.isEmail()) {
       addVerifyResult(BaseVerifyHandler.isEmail(name, value), result);
     }
     if (verify.isInterHandler()) {
       addVerifyResult(
         iExcelVerifyHandler.verifyHandler(object, name, value), 
         result);
     }
     if (verify.isMobile()) {
       addVerifyResult(BaseVerifyHandler.isMobile(name, value), result);
     }
     if (verify.isNotNull()) {
       addVerifyResult(BaseVerifyHandler.notNull(name, value), result);
     }
     if (verify.isTel()) {
       addVerifyResult(BaseVerifyHandler.isTel(name, value), result);
     }
     if (verify.getMaxLength() != -1) {
       addVerifyResult(
         BaseVerifyHandler.maxLength(name, value, 
         verify.getMaxLength()), result);
     }
     if (verify.getMinLength() != -1) {
       addVerifyResult(
         BaseVerifyHandler.minLength(name, value, 
         verify.getMinLength()), result);
     }
     if (StringUtils.isNotEmpty(verify.getRegex())) {
       addVerifyResult(BaseVerifyHandler.regex(name, value, 
         verify.getRegex(), verify.getRegexTip()), result);
     }
     return result;
   }
   
 
   private void addVerifyResult(ExcelVerifyHanlderResult temp, ExcelVerifyHanlderResult result)
   {
     if (!temp.isSuccess()) {
       result.setSuccess(false);
       result.setMsg(result.getMsg() + temp.getMsg());
     }
   }
}