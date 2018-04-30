 package org.inzy.framework.minidao.util;
 
 import freemarker.cache.TemplateLoader;
 import java.io.IOException;
 import java.io.Reader;
 import java.io.StringReader;
 import java.util.HashMap;
 import java.util.Map;
 
 
 
 
 
 
 public class StringTemplateLoader
   implements TemplateLoader
 {
   private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
   private Map templates = new HashMap();
   
   public StringTemplateLoader(String defaultTemplate) {
     if ((defaultTemplate != null) && (!defaultTemplate.equals(""))) {
       this.templates.put("_default_template_key", defaultTemplate);
     }
   }
   
   public void AddTemplate(String name, String template) {
     if ((name == null) || (template == null) || (name.equals("")) || 
       (template.equals(""))) {
       return;
     }
     if (!this.templates.containsKey(name)) {
       this.templates.put(name, template);
     }
   }
   
   public void closeTemplateSource(Object templateSource)
     throws IOException
   {}
   
   public Object findTemplateSource(String name) throws IOException
   {
     if ((name == null) || (name.equals(""))) {
       name = "_default_template_key";
     }
     return this.templates.get(name);
   }
   
   public long getLastModified(Object templateSource) {
     return 0L;
   }
   
   public Reader getReader(Object templateSource, String encoding) throws IOException
   {
     return new StringReader((String)templateSource);
   }
}