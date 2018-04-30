 package org.inzy.framework.minidao.util;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.lang.reflect.Field;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.lang.reflect.Modifier;
 import java.text.MessageFormat;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Set;
 import org.apache.commons.beanutils.BeanUtils;
 import org.apache.log4j.Logger;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MiniDaoUtil
 {
   public static final String DATABSE_TYPE_MYSQL = "mysql";
   public static final String DATABSE_TYPE_POSTGRE = "postgresql";
   public static final String DATABSE_TYPE_ORACLE = "oracle";
   public static final String DATABSE_TYPE_SQLSERVER = "sqlserver";
   public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
   public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
   public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
   public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";
   private static final Logger logger = Logger.getLogger(MiniDaoUtil.class);
   
   public static void main(String[] args) throws Exception {
     logger.debug(Boolean.valueOf(isWrapClass(Long.class)));
     logger.debug(Boolean.valueOf(isWrapClass(Integer.class)));
     logger.debug(Boolean.valueOf(isWrapClass(String.class)));
   }
   
 
 
 
 
   public static boolean isWrapClass(Class clz)
   {
     try
     {
       return ((Class)clz.getField("TYPE").get(null)).isPrimitive();
     } catch (Exception e) {}
     return false;
   }
   
 
 
 
 
 
 
   public static boolean isAbstract(Method method)
   {
     int mod = method.getModifiers();
     return Modifier.isAbstract(mod);
   }
   
 
 
 
 
   public static String getMethodSqlLogicJar(String sqlurl)
   {
     StringBuffer sb = new StringBuffer();
     
     InputStream is = MiniDaoUtil.class.getResourceAsStream(sqlurl);
     BufferedReader br = new BufferedReader(new InputStreamReader(is));
     String s = "";
     try {
       while ((s = br.readLine()) != null)
         sb.append(s + " ");
     } catch (IOException e) {
       e.printStackTrace();
     }
     return sb.toString();
   }
   
 
 
 
 
 
   public static String getFirstSmall(String name)
   {
     name = name.trim();
     if (name.length() >= 2) {
       return name.substring(0, 1).toLowerCase() + name.substring(1);
     }
     return name.toLowerCase();
   }
   
 
 
 
 
 
 
 
 
 
   public static void populate(Object bean, Map properties)
     throws IllegalAccessException, InvocationTargetException
   {
     if ((bean == null) || (properties == null)) {
       return;
     }
     
     Iterator names = properties.keySet().iterator();
     while (names.hasNext())
     {
 
       String name = (String)names.next();
       if (name != null)
       {
 
         Object value = properties.get(name);
         String camelName = CamelCaseUtils.toCamelCase(name);
         
 
         BeanUtils.setProperty(bean, camelName, value);
       }
     }
   }
   
 
 
 
 
 
 
   public static String createPageSql(String dbType, String sql, int page, int rows)
   {
     int beginNum = (page - 1) * rows;
     String[] sqlParam = new String[3];
     sqlParam[0] = sql;
     sqlParam[1] = String.valueOf(beginNum);
     sqlParam[2] = String.valueOf(rows);
     String jdbcType = dbType;
     if ((jdbcType == null) || ("".equals(jdbcType))) {
       throw new RuntimeException("org.inzy.framework.minidao.aop.MiniDaoHandler:(数据库类型:dbType)没有设置,请检查配置文件");
     }
     if (jdbcType.indexOf("mysql") != -1) {
       sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {1},{2}", sqlParam);
     } else if (jdbcType.indexOf("postgresql") != -1) {
       sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {2} offset {1}", sqlParam);
     } else {
       int beginIndex = (page - 1) * rows;
       int endIndex = beginIndex + rows;
       sqlParam[2] = Integer.toString(beginIndex);
       sqlParam[1] = Integer.toString(endIndex);
       if (jdbcType.indexOf("oracle") != -1) {
         sql = MessageFormat.format("select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}", sqlParam);
       } else if (jdbcType.indexOf("sqlserver") != -1) {
         sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
         sql = MessageFormat.format("select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}", sqlParam);
       }
     }
     return sql;
   }
   
   private static int getAfterSelectInsertPoint(String sql) {
     int selectIndex = sql.toLowerCase().indexOf("select");
     int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
     return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
   }
}