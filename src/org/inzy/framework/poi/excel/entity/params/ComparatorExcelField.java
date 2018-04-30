 package org.inzy.framework.poi.excel.entity.params;
 
 import java.util.Comparator;
 
 
 
 
 
 public class ComparatorExcelField
   implements Comparator<ExcelExportEntity>
 {
   public int compare(ExcelExportEntity prev, ExcelExportEntity next)
   {
     return prev.getOrderNum() - next.getOrderNum();
   }
}