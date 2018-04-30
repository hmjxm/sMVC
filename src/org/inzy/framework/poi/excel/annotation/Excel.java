package org.inzy.framework.poi.excel.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Excel
{
  String name();
  
  int width() default 10;
  
  int height() default 10;
  
  String[] replace() default {};
  
  int type() default 1;
  
  int imageType() default 1;
  
  String savePath() default "upload";
  
  String orderNum() default "0";
  
  boolean isWrap() default true;
  
  boolean needMerge() default false;
  
  boolean mergeVertical() default false;
  
  int[] mergeRely() default {};
  
  String databaseFormat() default "yyyyMMddHHmmss";
  
  String exportFormat() default "";
  
  String importFormat() default "";
  
  String format() default "";
  
  String cellFormula() default "";
}