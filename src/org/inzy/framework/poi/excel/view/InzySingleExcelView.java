 package org.inzy.framework.poi.excel.view;
 
 import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.export.ExcelExportServer;
import org.springframework.web.servlet.view.document.AbstractExcelView;
 
 
 
 
 
 
 
 public class InzySingleExcelView
   extends AbstractExcelView
 {
   protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
     throws Exception
   {
     String codedFileName = "临时文件.xls";
     if (model.containsKey("fileName")) {
       codedFileName = 
         (String)model.get("fileName") + ".xls";
     }
     if (isIE(httpServletRequest)) {
       codedFileName = URLEncoder.encode(codedFileName, "UTF8");
     } else {
       codedFileName = new String(codedFileName.getBytes("UTF-8"), 
         "ISO-8859-1");
     }
     httpServletResponse.setHeader("content-disposition", 
       "attachment;filename=" + codedFileName);
     if (model.containsKey("mapList")) {
       List<Map<String, Object>> list = 
         (List)model.get("mapList");
       for (Map<String, Object> map : list)
       {
         new ExcelExportServer().createSheet(hssfWorkbook, 
           (ExportParams)map.get("params"), 
           (Class)map.get("entity"), 
           
           (Collection)map.get("data"));
       }
     } else {
       new ExcelExportServer().createSheet(hssfWorkbook, 
         (ExportParams)model.get("params"), 
         (Class)model.get("entity"), 
         (Collection)model.get("data"));
     }
   }
   
   public boolean isIE(HttpServletRequest request) {
     return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) || 
       (request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0);
   }
}