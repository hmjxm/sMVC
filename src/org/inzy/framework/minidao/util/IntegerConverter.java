 package org.inzy.framework.minidao.util;
 
 import org.apache.commons.beanutils.Converter;
 
 
 
 public class IntegerConverter
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
       return new Integer(tmp);
     }
     
     return value;
   }
}