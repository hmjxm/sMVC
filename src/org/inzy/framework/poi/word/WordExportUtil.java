 package org.inzy.framework.poi.word;
 
 import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.inzy.framework.poi.word.parse.ParseWord07;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class WordExportUtil
 {
   public static XWPFDocument exportWord07(String url, Map<String, Object> map)
     throws Exception
   {
     return new ParseWord07().parseWord(url, map);
   }
   
 
 
 
 
 
 
 
 
   public static void exportWord07(XWPFDocument document, Map<String, Object> map)
     throws Exception
   {
     new ParseWord07().parseWord(document, map);
   }
}