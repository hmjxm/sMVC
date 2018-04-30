 package org.inzy.framework.poi.cache;
 
 import java.io.IOException;
import java.io.InputStream;

import org.inzy.framework.poi.cache.manager.POICacheManager;
import org.inzy.framework.poi.word.entity.InzyXWPFDocument;
 
 
 
 
 
 
 
 public class WordCache
 {
   public static InzyXWPFDocument getXWPFDocumen(String url)
   {
     InputStream is = null;
     try {
       is = POICacheManager.getFile(url);
       InzyXWPFDocument doc = new InzyXWPFDocument(is);
       return doc;
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       try {
         is.close();
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     return null;
   }
}