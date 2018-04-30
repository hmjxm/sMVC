 package org.inzy.framework.poi.excel.imports.verifys;
 
 import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.inzy.framework.poi.excel.entity.result.ExcelVerifyHanlderResult;
 
 
 
 
 
 
 
 public class BaseVerifyHandler
 {
   private static String NOT_NULL = "不允许为空";
   private static String IS_MOBILE = "不是手机号";
   private static String IS_TEL = "不是电话号码";
   private static String IS_EMAIL = "不是邮箱地址";
   private static String MIN_LENGHT = "小于规定长度";
   private static String MAX_LENGHT = "超过规定长度";
   
 
   private static Pattern mobilePattern = Pattern.compile("/^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$/");
   
 
   private static Pattern telPattern = Pattern.compile("/^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$/");
   
 
   private static Pattern emailPattern = Pattern.compile(" /^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$/");
   
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult notNull(String name, Object val)
   {
     if (val == null) {
       return new ExcelVerifyHanlderResult(false, name + NOT_NULL);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult isMobile(String name, Object val)
   {
     if (!mobilePattern.matcher(String.valueOf(val)).matches()) {
       return new ExcelVerifyHanlderResult(false, name + IS_MOBILE);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult isTel(String name, Object val)
   {
     if (!telPattern.matcher(String.valueOf(val)).matches()) {
       return new ExcelVerifyHanlderResult(false, name + IS_TEL);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult isEmail(String name, Object val)
   {
     if (!emailPattern.matcher(String.valueOf(val)).matches()) {
       return new ExcelVerifyHanlderResult(false, name + IS_EMAIL);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult minLength(String name, Object val, int minLength)
   {
     if ((notNull(name, val).isSuccess()) && 
       (String.valueOf(val).length() < minLength)) {
       return new ExcelVerifyHanlderResult(false, name + MIN_LENGHT);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult maxLength(String name, Object val, int maxLength)
   {
     if ((notNull(name, val).isSuccess()) && 
       (String.valueOf(val).length() > maxLength)) {
       return new ExcelVerifyHanlderResult(false, name + MAX_LENGHT);
     }
     return new ExcelVerifyHanlderResult(true);
   }
   
 
 
 
 
 
 
 
   public static ExcelVerifyHanlderResult regex(String name, Object val, String regex, String regexTip)
   {
     Pattern pattern = Pattern.compile(regex);
     if (!pattern.matcher(String.valueOf(val)).matches()) {
       return new ExcelVerifyHanlderResult(false, name + regexTip);
     }
     return new ExcelVerifyHanlderResult(true);
   }
}