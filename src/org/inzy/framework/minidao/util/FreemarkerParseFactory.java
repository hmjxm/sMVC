 package org.inzy.framework.minidao.util;
 
 import freemarker.template.Configuration;
 import freemarker.template.Template;
 import java.io.FileWriter;
 import java.io.StringWriter;
 import java.util.Map;
 
 
 
 
 
 
 
 
 public class FreemarkerParseFactory
 {
   private static final Configuration _tplConfig = new Configuration();
   
   public FreemarkerParseFactory() {
     _tplConfig.setClassForTemplateLoading(getClass(), "/");
     _tplConfig.setNumberFormat("0.#####################");
   }
   
 
 
 
 
 
   public String parseTemplate(String tplName, String encoding, Map<String, Object> paras)
   {
     try
     {
       StringWriter swriter = new StringWriter();
       Template mytpl = _tplConfig.getTemplate(tplName, encoding);
       mytpl.process(paras, swriter);
       return swriter.toString();
     }
     catch (Exception e) {
       e.printStackTrace();
       return e.toString();
     }
   }
   
 
 
 
   public boolean isExistTemplate(String tplName)
   {
     try
     {
       Template mytpl = _tplConfig.getTemplate(tplName, "UTF-8");
       if (mytpl == null) {
         return false;
       }
     } catch (Exception e) {
       return false;
     }
     return true;
   }
   
 
 
 
 
 
 
   public void parseTemplate(String tplName, String encoding, Map<String, Object> paras, FileWriter swriter)
   {
     try
     {
       Template mytpl = _tplConfig.getTemplate(tplName);
       mytpl.process(paras, swriter);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
 
 
 
 
 
 
   public String parseTemplate(String tplName, Map<String, Object> paras)
   {
     return parseTemplate(tplName, "utf-8", paras);
   }
   
 
 
 
 
 
 
 
 
   public String parseTemplateContent(String tplContent, Map<String, Object> paras, String encoding)
   {
     Configuration cfg = new Configuration();
     StringWriter writer = new StringWriter();
     cfg.setTemplateLoader(new StringTemplateLoader(tplContent));
     encoding = encoding == null ? "UTF-8" : encoding;
     cfg.setDefaultEncoding(encoding);
     
     try
     {
       Template template = cfg.getTemplate("");
       template.process(paras, writer);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return writer.toString();
   }
   
 
 
 
 
 
 
 
   public String parseTemplateContent(String tplContent, Map<String, Object> paras)
   {
     Configuration cfg = new Configuration();
     StringWriter writer = new StringWriter();
     cfg.setTemplateLoader(new StringTemplateLoader(tplContent));
     cfg.setDefaultEncoding("UTF-8");
     
     try
     {
       Template template = cfg.getTemplate("");
       template.process(paras, writer);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return writer.toString();
   }
}