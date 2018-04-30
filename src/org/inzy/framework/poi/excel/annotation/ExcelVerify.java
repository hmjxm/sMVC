package org.inzy.framework.poi.excel.annotation;

import java.lang.annotation.Annotation;

public @interface ExcelVerify
{
  boolean interHandler() default true;
  
  boolean notNull() default false;
  
  boolean isMobile() default false;
  
  boolean isTel() default false;
  
  boolean isEmail() default false;
  
  int minLength() default -1;
  
  int maxLength() default -1;
  
  String regex() default "";
  
  String regexTip() default "数据不符合规范";
}