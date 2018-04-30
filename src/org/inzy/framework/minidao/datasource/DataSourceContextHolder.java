 package org.inzy.framework.minidao.datasource;
 
 
 
 
 
 
 
 
 
 
 
 public class DataSourceContextHolder
 {
   private static final ThreadLocal contextHolder = new ThreadLocal();
   
   public static void setDataSourceType(DataSourceType dataSourceType) {
     contextHolder.set(dataSourceType);
   }
   
   public static DataSourceType getDataSourceType() {
     return (DataSourceType)contextHolder.get();
   }
   
   public static void clearDataSourceType() {
     contextHolder.remove();
   }
}