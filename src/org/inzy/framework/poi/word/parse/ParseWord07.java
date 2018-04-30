 package org.inzy.framework.poi.word.parse;
 
 import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.inzy.framework.poi.cache.WordCache;
import org.inzy.framework.poi.word.entity.InzyXWPFDocument;
import org.inzy.framework.poi.word.entity.WordImageEntity;
import org.inzy.framework.poi.word.entity.params.ExcelListEntity;
import org.inzy.framework.poi.word.parse.excel.ExcelEntityParse;
import org.inzy.framework.poi.word.parse.excel.ExcelMapParse;
import org.inzy.framework.poi.word.util.ParseWordUtil;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ParseWord07
 {
   public XWPFDocument parseWord(String url, Map<String, Object> map)
     throws Exception
   {
     InzyXWPFDocument doc = WordCache.getXWPFDocumen(url);
     parseWordSetValue(doc, map);
     return doc;
   }
   
 
 
 
 
 
 
 
   public void parseWord(XWPFDocument document, Map<String, Object> map)
     throws Exception
   {
     parseWordSetValue((InzyXWPFDocument)document, map);
   }
   
   private void parseWordSetValue(InzyXWPFDocument doc, Map<String, Object> map)
     throws Exception
   {
     parseAllParagraphic(doc.getParagraphs(), map);
     
 
     Iterator<XWPFTable> itTable = doc.getTablesIterator();
     while (itTable.hasNext()) {
       XWPFTable table = (XWPFTable)itTable.next();
       if (table.getText().indexOf("{{") != -1) {
         parseThisTable(table, map);
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
   private void parseAllParagraphic(List<XWPFParagraph> paragraphs, Map<String, Object> map)
     throws Exception
   {
     for (int i = 0; i < paragraphs.size(); i++) {
       XWPFParagraph paragraph = (XWPFParagraph)paragraphs.get(i);
       if (paragraph.getText().indexOf("{{") != -1) {
         parseThisParagraph(paragraph, map);
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   private void parseThisTable(XWPFTable table, Map<String, Object> map)
     throws Exception
   {
     ExcelEntityParse excelEntityParse = new ExcelEntityParse();
     for (int i = 0; i < table.getNumberOfRows(); i++) {
       XWPFTableRow row = table.getRow(i);
       List<XWPFTableCell> cells = row.getTableCells();
       if (cells.size() == 1) {
         Object listobj = checkThisTableIsNeedIterator((XWPFTableCell)cells.get(0), map);
         if (listobj == null) {
           parseThisRow(cells, map);
         } else if ((listobj instanceof ExcelListEntity)) {
           table.removeRow(i);
           excelEntityParse.parseNextRowAndAddRow(table, i, 
             (ExcelListEntity)listobj);
         } else {
           table.removeRow(i);
           ExcelMapParse.parseNextRowAndAddRow(table, i, 
             (List)listobj);
         }
       } else {
         parseThisRow(cells, map);
       }
     }
   }
   
   private void parseThisRow(List<XWPFTableCell> cells, Map<String, Object> map) throws Exception
   {
     for (XWPFTableCell cell : cells) {
       parseAllParagraphic(cell.getParagraphs(), map);
     }
   }
   
 
 
 
 
 
 
 
   private Object checkThisTableIsNeedIterator(XWPFTableCell cell, Map<String, Object> map)
     throws Exception
   {
     String text = cell.getText().trim();
     
     if ((text.startsWith("{{")) && (text.endsWith("}}")) && 
       (text.indexOf("in ") != -1))
     {
 
       return ParseWordUtil.getRealValue(text.replace("in ", "").trim(), 
         map);
     }
     return null;
   }
   
 
 
 
 
 
 
 
 
   private void parseThisParagraph(XWPFParagraph paragraph, Map<String, Object> map)
     throws Exception
   {
     XWPFRun currentRun = null;
     String currentText = "";
     
     Boolean isfinde = Boolean.valueOf(false);
     List<Integer> runIndex = new ArrayList();
     for (int i = 0; i < paragraph.getRuns().size(); i++) {
       XWPFRun run = (XWPFRun)paragraph.getRuns().get(i);
       String text = run.getText(0);
       if ((text != null) && (text != ""))
       {
 
         if (isfinde.booleanValue()) {
           currentText = currentText + text;
           if (currentText.indexOf("{{") == -1) {
             isfinde = Boolean.valueOf(false);
             runIndex.clear();
           } else {
             runIndex.add(Integer.valueOf(i));
           }
           if (currentText.indexOf("}}") != -1) {
             changeValues(paragraph, currentRun, currentText, runIndex, 
               map);
             currentText = "";
             isfinde = Boolean.valueOf(false);
           }
         } else if (text.indexOf("{") >= 0) {
           currentText = text;
           isfinde = Boolean.valueOf(true);
           currentRun = run;
         } else {
           currentText = "";
         }
         if (currentText.indexOf("}}") != -1) {
           changeValues(paragraph, currentRun, currentText, runIndex, map);
           isfinde = Boolean.valueOf(false);
         }
       }
     }
   }
   
 
 
 
 
 
 
 
   private void changeValues(XWPFParagraph paragraph, XWPFRun currentRun, String currentText, List<Integer> runIndex, Map<String, Object> map)
     throws Exception
   {
     Object obj = ParseWordUtil.getRealValue(currentText, map);
     if ((obj instanceof WordImageEntity)) {
       currentRun.setText("", 0);
       addAnImage((WordImageEntity)obj, currentRun);
     } else {
       currentText = obj.toString();
       currentRun.setText(currentText, 0);
     }
     for (int k = 0; k < runIndex.size(); k++) {
       ((XWPFRun)paragraph.getRuns().get(((Integer)runIndex.get(k)).intValue())).setText("", 0);
     }
     runIndex.clear();
   }
   
 
 
 
 
 
 
 
 
   private void addAnImage(WordImageEntity obj, XWPFRun currentRun)
     throws Exception
   {
     Object[] isAndType = ParseWordUtil.getIsAndType(obj);
     try
     {
       String picId = currentRun
         .getParagraph()
         .getDocument()
         .addPictureData((byte[])isAndType[0], 
         ((Integer)isAndType[1]).intValue());
       ((InzyXWPFDocument)currentRun.getParagraph().getDocument())
         .createPicture(
         currentRun, 
         picId, 
         currentRun
         .getParagraph()
         .getDocument()
         .getNextPicNameNumber(
         ((Integer)isAndType[1]).intValue()), 
         obj.getWidth(), obj.getHeight());
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
}