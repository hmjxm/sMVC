package org.inzy.framework.poi.excel.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ExcelCollection
{
  String id() default "";
  
  String name();
  
  String orderNum() default "0";
  
  Class<?> type() default ArrayList.class;
}