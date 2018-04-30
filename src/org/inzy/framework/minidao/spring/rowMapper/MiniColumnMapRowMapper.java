 package org.inzy.framework.minidao.spring.rowMapper;
 
 import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.inzy.framework.minidao.spring.map.MiniDaoLinkedMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
 
 
 
 
 
 
 
 public class MiniColumnMapRowMapper
   implements RowMapper<Map<String, Object>>
 {
   public Map<String, Object> mapRow(ResultSet resultset, int rowNum)
     throws SQLException
   {
     ResultSetMetaData rsmd = resultset.getMetaData();
     int columnCount = rsmd.getColumnCount();
     Map<String, Object> mapOfColValues = createColumnMap(columnCount);
     for (int i = 1; i <= columnCount; i++) {
       String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
       Object obj = getColumnValue(resultset, i);
       mapOfColValues.put(key, obj);
     }
     
     return mapOfColValues;
   }
   
   protected Map<String, Object> createColumnMap(int columnCount) {
     return new MiniDaoLinkedMap(columnCount);
   }
   
   protected String getColumnKey(String columnName) {
     return columnName;
   }
   
   protected Object getColumnValue(ResultSet rs, int index) throws SQLException
   {
     return JdbcUtils.getResultSetValue(rs, index);
   }
}