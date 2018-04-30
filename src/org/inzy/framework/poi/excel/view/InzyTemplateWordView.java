 package org.inzy.framework.poi.excel.view;
 
 import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.inzy.framework.poi.word.WordExportUtil;
import org.springframework.web.servlet.view.AbstractView;
 
 
 
 
 
 
 
 
 public class InzyTemplateWordView
   extends AbstractView
 {
   private static final String CONTENT_TYPE = "application/msword";
   
   public InzyTemplateWordView()
   {
     setContentType("application/msword");
   }
   
 
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     String codedFileName = "临时文件.docx";
     if (model.containsKey("fileName")) {
       codedFileName = 
         (String)model.get("fileName") + ".docx";
     }
     if (isIE(request)) {
       codedFileName = URLEncoder.encode(codedFileName, "UTF8");
     } else {
       codedFileName = new String(codedFileName.getBytes("UTF-8"), 
         "ISO-8859-1");
     }
     response.setHeader("content-disposition", "attachment;filename=" + 
       codedFileName);
     XWPFDocument document = 
       WordExportUtil.exportWord07((String)model.get("url"), 
       
       (Map)model.get("map"));
     ServletOutputStream out = response.getOutputStream();
     document.write(out);
     out.flush();
   }
   
   public boolean isIE(HttpServletRequest request) {
     return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) || 
       (request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0);
   }
}