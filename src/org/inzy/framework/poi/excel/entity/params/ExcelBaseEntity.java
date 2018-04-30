 package org.inzy.framework.poi.excel.entity.params;
 
 import java.lang.reflect.Method;
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelBaseEntity
 {
   private String name;
   private int type;
   private String databaseFormat;
   private String format;
   private String[] replace;
   private Method method;
   private List<Method> methods;
   
   public String getName()
   {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public int getType() {
     return this.type;
   }
   
   public void setType(int type) {
     this.type = type;
   }
   
   public String getDatabaseFormat() {
     return this.databaseFormat;
   }
   
   public void setDatabaseFormat(String databaseFormat) {
     this.databaseFormat = databaseFormat;
   }
   
   public String getFormat() {
     return this.format;
   }
   
   public void setFormat(String format) {
     this.format = format;
   }
   
   public String[] getReplace() {
     return this.replace;
   }
   
   public void setReplace(String[] replace) {
     this.replace = replace;
   }
   
   public Method getMethod() {
     return this.method;
   }
   
   public void setMethod(Method method) {
     this.method = method;
   }
   
   public List<Method> getMethods() {
     return this.methods;
   }
   
   public void setMethods(List<Method> methods) {
     this.methods = methods;
   }
}