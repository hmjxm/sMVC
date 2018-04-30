 package org.inzy.framework.minidao.util;
 
 import java.math.BigDecimal;
 import org.apache.commons.beanutils.Converter;
 
 
 
 
 public class BigDecimalConverter
   implements Converter
 {
   public Object convert(Class type, Object value)
   {
     if (value == null)
       return null;
     if ((value instanceof String)) {
       String tmp = (String)value;
       if (tmp.trim().length() == 0) {
         return null;
       }
       return new BigDecimal(tmp);
     }
     
     return value;
   }
}