 package org.inzy.framework.poi.excel;
 
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.inzy.framework.poi.excel.entity.ImportParams;
import org.inzy.framework.poi.excel.entity.result.ExcelImportResult;
import org.inzy.framework.poi.excel.imports.ExcelImportServer;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExcelImportUtil
 {
   public static ExcelImportResult importExcelVerify(File file, Class<?> pojoClass, ImportParams params)
   {
     FileInputStream in = null;
     try {
       in = new FileInputStream(file);
       return new ExcelImportServer().importExcelByIs(in, pojoClass, 
         params);
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       try {
         in.close();
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     return null;
   }
   
   public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params)
   {
     FileInputStream in = null;
     List result = null;
     try {
       in = new FileInputStream(file);
       result = new ExcelImportServer().importExcelByIs(in, pojoClass, 
         params).getList();
     } catch (Exception e) {
       e.printStackTrace();
       try
       {
         in.close();
       } catch (IOException ex) {
         ex.printStackTrace();
       }
     }
     finally
     {
       try
       {
         in.close();
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     return result;
   }
   
   public static ExcelImportResult importExcelByIsAndVerify(InputStream inputstream, Class<?> pojoClass, ImportParams params)
     throws Exception
   {
     return new ExcelImportServer().importExcelByIs(inputstream, pojoClass, 
       params);
   }
   
 
 
 
 
 
 
 
 
   public static <T> List<T> importExcelByIs(InputStream inputstream, Class<?> pojoClass, ImportParams params)
     throws Exception
   {
     return 
       new ExcelImportServer().importExcelByIs(inputstream, pojoClass, params).getList();
   }
}