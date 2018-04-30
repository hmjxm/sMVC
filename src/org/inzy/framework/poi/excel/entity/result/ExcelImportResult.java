 package org.inzy.framework.poi.excel.entity.result;
 
 import java.util.List;
 import org.apache.poi.ss.usermodel.Workbook;
 
 
 
 
 
 
 public class ExcelImportResult
 {
   private List list;
   private boolean verfiyFail;
   private Workbook workbook;
   
   public ExcelImportResult() {}
   
   public ExcelImportResult(List list, boolean verfiyFail, Workbook workbook)
   {
     this.list = list;
     this.verfiyFail = verfiyFail;
     this.workbook = workbook;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public List getList()
   {
     return this.list;
   }
   
   public void setList(List list) {
     this.list = list;
   }
   
   public boolean isVerfiyFail() {
     return this.verfiyFail;
   }
   
   public void setVerfiyFail(boolean verfiyFail) {
     this.verfiyFail = verfiyFail;
   }
   
   public Workbook getWorkbook() {
     return this.workbook;
   }
   
   public void setWorkbook(Workbook workbook) {
     this.workbook = workbook;
   }
}