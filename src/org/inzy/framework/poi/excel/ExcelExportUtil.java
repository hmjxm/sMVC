 package org.inzy.framework.poi.excel;
 
 import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.inzy.framework.poi.excel.export.ExcelExportServer;
import org.inzy.framework.poi.excel.export.template.ExcelExportOfTemplateUtil;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public final class ExcelExportUtil
 {
   public static HSSFWorkbook exportExcel(List<Map<String, Object>> list)
   {
     HSSFWorkbook workbook = new HSSFWorkbook();
     ExcelExportServer server = new ExcelExportServer();
     for (Map<String, Object> map : list) {
       server.createSheet(workbook, 
         (ExportParams)map.get("title"), 
         (Class)map.get("entity"), 
         (Collection)map.get("data"));
     }
     return workbook;
   }
   
 
 
 
 
 
 
 
 
   public static HSSFWorkbook exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet)
   {
     HSSFWorkbook workbook = new HSSFWorkbook();
     new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet);
     return workbook;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
   public static Workbook exportExcel(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map)
   {
     return new ExcelExportOfTemplateUtil().createExcleByTemplate(params, 
       pojoClass, dataSet, map);
   }
   
 
 
 
 
 
 
 
 
 
   public static Workbook exportExcel(TemplateExportParams params, Map<String, Object> map)
   {
     return new ExcelExportOfTemplateUtil().createExcleByTemplate(params, 
       null, null, map);
   }
}