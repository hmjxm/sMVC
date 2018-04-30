 package org.inzy.framework.minidao.util;
 
 import java.io.PrintStream;
 
 public class CamelCaseUtils {
   private static final char SEPARATOR = '_';
   
   public static String toUnderlineName(String s) { if (s == null) {
       return null;
     }
     
     StringBuilder sb = new StringBuilder();
     boolean upperCase = false;
     for (int i = 0; i < s.length(); i++) {
       char c = s.charAt(i);
       
       boolean nextUpperCase = true;
       
       if (i < s.length() - 1) {
         nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
       }
       
       if ((i >= 0) && (Character.isUpperCase(c))) {
         if (((!upperCase) || (!nextUpperCase)) && 
           (i > 0)) { sb.append('_');
         }
         upperCase = true;
       } else {
         upperCase = false;
       }
       
       sb.append(Character.toLowerCase(c));
     }
     
     return sb.toString();
   }
   
   public static String toCamelCase(String s) {
     if (s == null) {
       return null;
     }
     
     s = s.toLowerCase();
     
     StringBuilder sb = new StringBuilder(s.length());
     boolean upperCase = false;
     for (int i = 0; i < s.length(); i++) {
       char c = s.charAt(i);
       
       if (c == '_') {
         upperCase = true;
       } else if (upperCase) {
         sb.append(Character.toUpperCase(c));
         upperCase = false;
       } else {
         sb.append(c);
       }
     }
     
     return sb.toString();
   }
   
   public static String toCapitalizeCamelCase(String s) {
     if (s == null) {
       return null;
     }
     s = toCamelCase(s);
     return s.substring(0, 1).toUpperCase() + s.substring(1);
   }
   
   public static void main(String[] args) {
     System.out.println(toUnderlineName("ISOCertifiedStaff"));
     System.out.println(toUnderlineName("CertifiedStaff"));
     System.out.println(toUnderlineName("UserID"));
     System.out.println(toCamelCase("iso_certified_staff"));
     System.out.println(toCamelCase("certified_staff"));
     System.out.println(toCamelCase("user_id"));
   }
}