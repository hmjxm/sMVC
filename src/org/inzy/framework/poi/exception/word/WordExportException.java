 package org.inzy.framework.poi.exception.word;
 
 import org.inzy.framework.poi.exception.word.enmus.WordExportEnum;
 
 
 
 
 
 
 
 public class WordExportException
   extends RuntimeException
 {
   private static final long serialVersionUID = 1L;
   
   public WordExportException() {}
   
   public WordExportException(String msg)
   {
     super(msg);
   }
   
   public WordExportException(WordExportEnum exception) {
     super(exception.getMsg());
   }
}