 package org.inzy.framework.poi.excel.view;
 
 import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.inzy.framework.poi.excel.ExcelExportUtil;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.springframework.web.servlet.view.AbstractView;
 
 
 
 
 
 
 
 
 
 public class InzyTemplateExcelView
   extends AbstractView
 {
   private static final String CONTENT_TYPE = "application/vnd.ms-excel";
   
   public InzyTemplateExcelView()
   {
     setContentType("application/vnd.ms-excel");
   }
   
 
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     String codedFileName = "临时文件.xls";
     if (model.containsKey("fileName")) {
       codedFileName = 
         (String)model.get("fileName") + ".xls";
     }
     if (isIE(request)) {
       codedFileName = URLEncoder.encode(codedFileName, "UTF8");
     } else {
       codedFileName = new String(codedFileName.getBytes("UTF-8"), 
         "ISO-8859-1");
     }
     response.setHeader("content-disposition", 
       "attachment;filename=" + codedFileName);
     Workbook workbook = ExcelExportUtil.exportExcel(
       (TemplateExportParams)model.get("params"), 
       (Class)model.get("entity"), 
       (List)model.get("list"), 
       (Map)model.get("map"));
     ServletOutputStream out = response.getOutputStream();
     workbook.write(out);
     out.flush();
   }
   
   public boolean isIE(HttpServletRequest request) {
     return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) || 
       (request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0);
   }
}