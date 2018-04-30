 package org.inzy.framework.minidao.aop;
 
 import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ognl.Ognl;
import ognl.OgnlException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.inzy.framework.minidao.annotation.Arguments;
import org.inzy.framework.minidao.annotation.ResultType;
import org.inzy.framework.minidao.annotation.Sql;
import org.inzy.framework.minidao.hibernate.dao.IGenericBaseCommonDao;
import org.inzy.framework.minidao.pojo.MiniDaoPage;
import org.inzy.framework.minidao.spring.rowMapper.GenericRowMapper;
import org.inzy.framework.minidao.spring.rowMapper.MiniColumnMapRowMapper;
import org.inzy.framework.minidao.spring.rowMapper.MiniColumnOriginalMapRowMapper;
import org.inzy.framework.minidao.util.FreemarkerParseFactory;
import org.inzy.framework.minidao.util.MiniDaoUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MiniDaoHandler
   implements MethodInterceptor
 {
   private static final Logger logger = Logger.getLogger(MiniDaoHandler.class);
   
   private JdbcTemplate jdbcTemplate;
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   private IGenericBaseCommonDao miniDaoHiberCommonDao;
   private BasicFormatterImpl formatter = new BasicFormatterImpl();
   
   private String UPPER_KEY = "upper";
   private String LOWER_KEY = "lower";
   
 
 
 
   private String keyType = "origin";
   private boolean formatSql = false;
   private boolean showSql = false;
   private String dbType;
   
   public Object invoke(MethodInvocation methodInvocation) throws Throwable {
     Method method = methodInvocation.getMethod();
     Object[] args = methodInvocation.getArguments();
     
     Object returnObj = null;
     
     String templateSql = null;
     
     Map<String, Object> sqlParamsMap = new HashMap();
     
     MiniDaoPage pageSetting = new MiniDaoPage();
     
 
     if (!MiniDaoUtil.isAbstract(method)) {
       return methodInvocation.proceed();
     }
     
     Map<String, Object> rs = new HashMap();
     if (miniDaoHiber(rs, method, args)) {
       return rs.get("returnObj");
     }
     
     templateSql = installDaoMetaData(pageSetting, method, sqlParamsMap, args);
     
 
     String executeSql = parseSqlTemplate(method, templateSql, sqlParamsMap);
     
 
     Map<String, Object> sqlMap = installPlaceholderSqlParam(executeSql, sqlParamsMap);
     
 
     returnObj = getReturnMinidaoResult(this.dbType, pageSetting, this.jdbcTemplate, method, executeSql, sqlMap);
     
     if (this.showSql) {
       logger.info("MiniDao-SQL:\n\n" + (this.formatSql ? this.formatter.format(executeSql) : executeSql) + "\n");
     }
     return returnObj;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
   private boolean miniDaoHiber(Map rs, Method method, Object[] args)
   {
     if ("saveByHiber".equals(method.getName())) {
       this.miniDaoHiberCommonDao.save(args[0]);
       return true;
     }
     if ("getByIdHiber".equals(method.getName()))
     {
       Class<?> clz = (Class)args[0];
       rs.put("returnObj", this.miniDaoHiberCommonDao.get(clz, args[1].toString()));
       return true;
     }
     if ("getByEntityHiber".equals(method.getName()))
     {
       rs.put("returnObj", this.miniDaoHiberCommonDao.get(args[0]));
       return true;
     }
     if ("updateByHiber".equals(method.getName())) {
       this.miniDaoHiberCommonDao.saveOrUpdate(args[0]);
       return true;
     }
     if ("deleteByHiber".equals(method.getName())) {
       this.miniDaoHiberCommonDao.delete(args[0]);
       return true;
     }
     if ("deleteByIdHiber".equals(method.getName())) {
       Class<?> clz = (Class)args[0];
       this.miniDaoHiberCommonDao.deleteEntityById(clz, args[1].toString());
       return true;
     }
     if ("listByHiber".equals(method.getName())) {
       rs.put("returnObj", this.miniDaoHiberCommonDao.loadAll(args[0]));
       return true;
     }
     return false;
   }
   
 
 
 
 
 
 
 
   private String parseSqlTemplate(Method method, String templateSql, Map<String, Object> sqlParamsMap)
   {
     FreemarkerParseFactory freemarkerUtil = new FreemarkerParseFactory();
     
     String executeSql = null;
     
 
 
     if (StringUtils.isNotEmpty(templateSql)) {
       executeSql = freemarkerUtil.parseTemplateContent(templateSql, sqlParamsMap);
     } else {
       String sqlTempletPath = "/" + method.getDeclaringClass().getName().replace(".", "/").replace("/dao/", "/sql/") + "_" + method.getName() + ".sql";
       if (!freemarkerUtil.isExistTemplate(sqlTempletPath)) {
         sqlTempletPath = "/" + method.getDeclaringClass().getName().replace(".", "/") + "_" + method.getName() + ".sql";
       }
       logger.debug("MiniDao-SQL-Path:" + sqlTempletPath);
       executeSql = new FreemarkerParseFactory().parseTemplate(sqlTempletPath, sqlParamsMap);
     }
     return getSqlText(executeSql);
   }
   
 
 
 
   private String getSqlText(String sql)
   {
     return 
       sql.replaceAll("\\n", " ").replaceAll("\\t", " ").replaceAll("\\s{1,}", " ").trim();
   }
   
 
 
 
 
 
   private Map<String, Object> installPlaceholderSqlParam(String executeSql, Map sqlParamsMap)
     throws OgnlException
   {
     Map<String, Object> map = new HashMap();
     String regEx = ":[ tnx0Bfr]*[0-9a-z.A-Z]+";
     Pattern pat = Pattern.compile(regEx);
     Matcher m = pat.matcher(executeSql);
     while (m.find()) {
       logger.debug(" Match [" + m.group() + "] at positions " + m.start() + "-" + (m.end() - 1));
       String ognl_key = m.group().replace(":", "").trim();
       map.put(ognl_key, Ognl.getValue(ognl_key, sqlParamsMap));
     }
     return map;
   }
   
 
 
 
 
 
 
 
 
 
 
   private Object getReturnMinidaoResult(String dbType, MiniDaoPage pageSetting, JdbcTemplate jdbcTemplate, Method method, String executeSql, Map<String, Object> paramMap)
   {
     String methodName = method.getName();
     
     if (checkActiveKey(methodName)) {
       if (paramMap != null) {
         return Integer.valueOf(this.namedParameterJdbcTemplate.update(executeSql, paramMap));
       }
       return Integer.valueOf(jdbcTemplate.update(executeSql));
     }
     if (checkBatchKey(methodName)) {
       return batchUpdate(jdbcTemplate, executeSql);
     }
     
     Class<?> returnType = method.getReturnType();
     if (returnType.isPrimitive()) {
       Number number = (Number)jdbcTemplate.queryForObject(executeSql, BigDecimal.class);
       if ("int".equals(returnType))
         return Integer.valueOf(number.intValue());
       if ("long".equals(returnType))
         return Long.valueOf(number.longValue());
       if ("double".equals(returnType))
         return Double.valueOf(number.doubleValue());
     } else {
       if (returnType.isAssignableFrom(List.class))
       {
         int page = pageSetting.getPage();
         int rows = pageSetting.getRows();
         if ((page != 0) && (rows != 0)) {
           executeSql = MiniDaoUtil.createPageSql(dbType, executeSql, page, rows);
         }
         
 
         ResultType resultType = (ResultType)method.getAnnotation(ResultType.class);
         String[] values = (String[])null;
         if (resultType != null) {
           values = resultType.value();
         }
         if ((values == null) || (values.length == 0) || ("java.util.Map".equals(values[0]))) {
           if (paramMap != null) {
             return this.namedParameterJdbcTemplate.query(executeSql, paramMap, getColumnMapRowMapper());
           }
           return jdbcTemplate.query(executeSql, getColumnMapRowMapper());
         }
         
         Class clazz = null;
         try {
           clazz = Class.forName(values[0]);
         } catch (Exception e) {
           e.printStackTrace();
         }
         if (paramMap != null) {
           return this.namedParameterJdbcTemplate.query(executeSql, paramMap, new GenericRowMapper(clazz));
         }
         return jdbcTemplate.query(executeSql, new GenericRowMapper(clazz));
       }
       
 
       if (returnType.isAssignableFrom(Map.class))
       {
         if (paramMap != null) {
           return (Map)this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, getColumnMapRowMapper());
         }
         return (Map)jdbcTemplate.queryForObject(executeSql, getColumnMapRowMapper());
       }
       if (returnType.isAssignableFrom(String.class))
         try
         {
           if (paramMap != null) {
             return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, String.class);
           }
           return jdbcTemplate.queryForObject(executeSql, String.class);
         }
         catch (EmptyResultDataAccessException e) {
           return null;
         }
       if (MiniDaoUtil.isWrapClass(returnType)) {
         try
         {
           if (paramMap != null) {
             return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, returnType);
           }
           return jdbcTemplate.queryForObject(executeSql, returnType);
         }
         catch (EmptyResultDataAccessException e) {
           return null;
         }
       }
       
       RowMapper<?> rm = ParameterizedBeanPropertyRowMapper.newInstance(returnType);
       try {
         if (paramMap != null) {
           return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, rm);
         }
         return jdbcTemplate.queryForObject(executeSql, rm);
       }
       catch (EmptyResultDataAccessException e) {
         return null;
       }
     }
     
     return null;
   }
   
 
 
 
 
 
   private int[] batchUpdate(JdbcTemplate jdbcTemplate, String executeSql)
   {
     String[] sqls = executeSql.split(";");
     if (sqls.length < 100) {
       return jdbcTemplate.batchUpdate(sqls);
     }
     int[] result = new int[sqls.length];
     List<String> sqlList = new ArrayList();
     for (int i = 0; i < sqls.length; i++) {
       sqlList.add(sqls[i]);
       if (i % 100 == 0) {
         addResulArray(result, i + 1, jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
         sqlList.clear();
       }
     }
     addResulArray(result, sqls.length, jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
     return result;
   }
   
 
 
 
 
 
   private void addResulArray(int[] result, int index, int[] arr)
   {
     int length = arr.length;
     for (int i = 0; i < length; i++) {
       result[(index - length + i)] = arr[i];
     }
   }
   
 
   public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
   {
     return this.namedParameterJdbcTemplate;
   }
   
 
 
   public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
   {
     this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
   }
   
 
 
 
 
 
 
 
 
 
 
   private String installDaoMetaData(MiniDaoPage pageSetting, Method method, Map<String, Object> sqlParamsMap, Object[] args)
     throws Exception
   {
     String templateSql = null;
     
     boolean arguments_flag = method.isAnnotationPresent(Arguments.class);
     if (arguments_flag)
     {
       Arguments arguments = (Arguments)method.getAnnotation(Arguments.class);
       logger.debug("@Arguments------------------------------------------" + Arrays.toString(arguments.value()));
       if (arguments.value().length > args.length)
       {
         throw new Exception("[注释标签]参数数目，不能大于[方法参数]参数数目");
       }
       
       int args_num = 0;
       String[] arrayOfString; int j = (arrayOfString = arguments.value()).length; for (int i = 0; i < j; i++) { String v = arrayOfString[i];
         
         if (v.equalsIgnoreCase("page")) {
           pageSetting.setPage(Integer.parseInt(args[args_num].toString()));
         }
         if (v.equalsIgnoreCase("rows")) {
           pageSetting.setRows(Integer.parseInt(args[args_num].toString()));
         }
         
         sqlParamsMap.put(v, args[args_num]);
         args_num++;
       }
     }
     else {
       if (args.length > 1)
         throw new Exception("方法参数数目>=2，方法必须使用注释标签@Arguments");
       if (args.length == 1)
       {
         sqlParamsMap.put("dto", args[0]);
       }
     }
     
 
 
     if (method.isAnnotationPresent(Sql.class)) {
       Sql sql = (Sql)method.getAnnotation(Sql.class);
       
       if (StringUtils.isNotEmpty(sql.value())) {
         templateSql = sql.value();
       }
       logger.debug("@Sql------------------------------------------" + sql.value());
     }
     return templateSql;
   }
   
 
 
 
 
 
 
   private static boolean checkActiveKey(String methodName)
   {
     String[] keys = "insert,add,create,update,modify,store,delete,remove".split(",");
     String[] arrayOfString1; int j = (arrayOfString1 = keys).length; for (int i = 0; i < j; i++) { String s = arrayOfString1[i];
       if (methodName.startsWith(s))
         return true;
     }
     return false;
   }
   
 
 
 
 
 
   private static boolean checkBatchKey(String methodName)
   {
     String[] keys = "batch".split(",");
     String[] arrayOfString1; int j = (arrayOfString1 = keys).length; for (int i = 0; i < j; i++) { String s = arrayOfString1[i];
       if (methodName.startsWith(s))
         return true;
     }
     return false;
   }
   
 
   private RowMapper<Map<String, Object>> getColumnMapRowMapper()
   {
     if (getKeyType().equalsIgnoreCase(this.LOWER_KEY))
       return new MiniColumnMapRowMapper();
     if (getKeyType().equalsIgnoreCase(this.UPPER_KEY)) {
       return new ColumnMapRowMapper();
     }
     return new MiniColumnOriginalMapRowMapper();
   }
   
   public JdbcTemplate getJdbcTemplate()
   {
     return this.jdbcTemplate;
   }
   
   public IGenericBaseCommonDao getMiniDaoHiberCommonDao() {
     return this.miniDaoHiberCommonDao;
   }
   
   public void setMiniDaoHiberCommonDao(IGenericBaseCommonDao miniDaoHiberCommonDao) {
     this.miniDaoHiberCommonDao = miniDaoHiberCommonDao;
   }
   
   public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
     this.jdbcTemplate = jdbcTemplate;
   }
   
   public boolean isFormatSql() {
     return this.formatSql;
   }
   
   public void setFormatSql(boolean formatSql) {
     this.formatSql = formatSql;
   }
   
 
   public String getKeyType()
   {
     return this.keyType;
   }
   
 
   public void setKeyType(String keyType)
   {
     this.keyType = keyType;
   }
   
   public void setShowSql(boolean showSql) {
     this.showSql = showSql;
   }
   
 
   public String getDbType()
   {
     return this.dbType;
   }
   
 
   public void setDbType(String dbType)
   {
     this.dbType = dbType;
   }
}