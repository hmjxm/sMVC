 package org.inzy.framework.minidao.util;
 
 import java.text.ParseException;
 import org.apache.commons.beanutils.Converter;
 import org.apache.commons.lang.time.DateUtils;
 
 
 
 
 
 public class DateConverter
   implements Converter
 {
   String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S" };
   
   public Object convert(Class type, Object value)
   {
     if (value == null)
       return null;
     if ((value instanceof String)) {
       String tmp = (String)value;
       if (tmp.trim().length() == 0) {
         return null;
       }
       try {
         return DateUtils.parseDate(tmp, this.parsePatterns);
       } catch (ParseException e) {
         e.printStackTrace();
       }
     }
     else
     {
       return value;
     }
     return value;
   }
}