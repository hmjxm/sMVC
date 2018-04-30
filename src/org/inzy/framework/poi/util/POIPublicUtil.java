 package org.inzy.framework.poi.util;
 
 import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.inzy.framework.poi.excel.annotation.Excel;
import org.inzy.framework.poi.excel.annotation.ExcelCollection;
import org.inzy.framework.poi.excel.annotation.ExcelEntity;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
 
 public class POIPublicUtil
 {
   public static String getWebRootPath(String filePath)
   {
     String path = POIPublicUtil.class.getClassLoader().getResource("")
       .getPath() + 
       filePath;
     path = path.replace("WEB-INF/classes/", "");
     path = path.replace("file:/", "");
     return path;
   }
   
 
 
 
 
 
   public static Field[] getClassFields(Class<?> clazz)
   {
     List<Field> list = new java.util.ArrayList();
     Field[] fields;
     do {
       fields = clazz.getDeclaredFields();
       for (int i = 0; i < fields.length; i++) {
         list.add(fields[i]);
       }
       clazz = clazz.getSuperclass();
     } while ((clazz != Object.class) && (clazz != null));
     return (Field[])list.toArray(fields);
   }
   
 
 
 
 
 
   public static boolean isCollection(Class<?> clazz)
   {
     return java.util.Collection.class.isAssignableFrom(clazz);
   }
   
 
 
 
 
 
 
 
 
   public static boolean isNotUserExcelUserThis(List<String> exclusionsList, Field field, String targetId)
   {
     boolean boo = true;
     if (field.getAnnotation(org.inzy.framework.poi.excel.annotation.ExcelIgnore.class) != null) {
       boo = true;
     } else { if ((boo) && 
         (field.getAnnotation(ExcelCollection.class) != null) && 
         (isUseInThis(((ExcelCollection)field.getAnnotation(ExcelCollection.class))
         .name(), targetId)))
         if ((exclusionsList == null) || (!exclusionsList.contains(
           ((ExcelCollection)field.getAnnotation(ExcelCollection.class)).name()))) {
           boo = false; return boo; }
       if ((boo) && 
         (field.getAnnotation(Excel.class) != null) && 
         (isUseInThis(((Excel)field.getAnnotation(Excel.class)).name(), 
         targetId)))
         if ((exclusionsList == null) || (!exclusionsList.contains(
           ((Excel)field.getAnnotation(Excel.class)).name()))) {
           boo = false; return boo; }
       if ((boo) && 
         (field.getAnnotation(ExcelEntity.class) != null) && 
         (isUseInThis(((ExcelEntity)field.getAnnotation(ExcelEntity.class)).name(), 
         targetId)))
         if ((exclusionsList == null) || (!exclusionsList.contains(
           ((ExcelEntity)field.getAnnotation(ExcelEntity.class)).name())))
           boo = false;
     }
     return boo;
   }
   
 
 
 
 
 
 
 
 
   private static boolean isUseInThis(String exportName, String targetId)
   {
     return (targetId == null) || (exportName.equals("")) || (exportName.indexOf("_") < 0) || (exportName.indexOf(targetId) != -1);
   }
   
 
 
 
 
 
   public static boolean isJavaClass(Field field)
   {
     Class<?> fieldType = field.getType();
     boolean isBaseClass = false;
     if (fieldType.isArray()) {
       isBaseClass = false;
     } else if ((fieldType.isPrimitive()) || (fieldType.getPackage() == null) || 
       (fieldType.getPackage().getName().equals("java.lang")) || 
       (fieldType.getPackage().getName().equals("java.math")) || 
       (fieldType.getPackage().getName().equals("java.util"))) {
       isBaseClass = true;
     }
     return isBaseClass;
   }
   
 
 
 
 
 
   public static Object createObject(Class<?> clazz, String targetId)
   {
     Object obj = null;
     try
     {
       obj = clazz.newInstance();
       Field[] fields = getClassFields(clazz);
       Field[] arrayOfField1; int j = (arrayOfField1 = fields).length; for (int i = 0; i < j; i++) { Field field = arrayOfField1[i];
         if (!isNotUserExcelUserThis(null, field, targetId))
         {
 
           if (isCollection(field.getType())) {
             ExcelCollection collection = 
               (ExcelCollection)field.getAnnotation(ExcelCollection.class);
             Method setMethod = getMethod(field.getName(), clazz, 
               field.getType());
             setMethod.invoke(obj, new Object[] { collection.type().newInstance() });
           } else if (!isJavaClass(field)) {
             Method setMethod = getMethod(field.getName(), clazz, 
               field.getType());
             setMethod.invoke(obj, new Object[] {
               createObject(field.getType(), targetId) });
           }
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
       throw new RuntimeException("创建对象异常");
     }
     return obj;
   }
   
 
 
 
 
 
 
 
 
   public static Method getMethod(String name, Class<?> pojoClass)
     throws Exception
   {
     StringBuffer getMethodName = new StringBuffer("get");
     getMethodName.append(name.substring(0, 1).toUpperCase());
     getMethodName.append(name.substring(1));
     Method method = null;
     try {
       method = pojoClass.getMethod(getMethodName.toString(), 
         new Class[0]);
     } catch (Exception e) {
       method = pojoClass.getMethod(
         getMethodName.toString().replace("get", 
         "is"), new Class[0]);
     }
     return method;
   }
   
 
 
 
 
 
 
 
 
   public static Method getMethod(String name, Class<?> pojoClass, Class<?> type)
     throws Exception
   {
     StringBuffer getMethodName = new StringBuffer("set");
     getMethodName.append(name.substring(0, 1).toUpperCase());
     getMethodName.append(name.substring(1));
     return pojoClass.getMethod(getMethodName.toString(), 
       new Class[] { type });
   }
   
 
 
 
   public static String getFileExtendName(byte[] photoByte)
   {
     String strFileExtendName = "JPG";
     if ((photoByte[0] == 71) && (photoByte[1] == 73) && 
       (photoByte[2] == 70) && (photoByte[3] == 56) && 
       ((photoByte[4] == 55) || (photoByte[4] == 57)) && 
       (photoByte[5] == 97)) {
       strFileExtendName = "GIF";
     } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && 
       (photoByte[8] == 73) && (photoByte[9] == 70)) {
       strFileExtendName = "JPG";
     } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
       strFileExtendName = "BMP";
     } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && 
       (photoByte[3] == 71)) {
       strFileExtendName = "PNG";
     }
     return strFileExtendName;
   }
   
 
 
 
 
 
 
 
 
 
   public static Map<String, PictureData> getSheetPictrues03(org.apache.poi.hssf.usermodel.HSSFSheet sheet, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook)
   {
     Map<String, PictureData> sheetIndexPicMap = new java.util.HashMap();
     List<HSSFPictureData> pictures = workbook.getAllPictures();
     if (pictures.size() != 0) {
       for (org.apache.poi.hssf.usermodel.HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
         HSSFClientAnchor anchor = (HSSFClientAnchor)shape.getAnchor();
         if ((shape instanceof HSSFPicture)) {
           HSSFPicture pic = (HSSFPicture)shape;
           int pictureIndex = pic.getPictureIndex() - 1;
           HSSFPictureData picData = (HSSFPictureData)pictures.get(pictureIndex);
           String picIndex = String.valueOf(anchor.getRow1()) + "_" + 
             String.valueOf(anchor.getCol1());
           sheetIndexPicMap.put(picIndex, picData);
         }
       }
       return sheetIndexPicMap;
     }
     return null;
   }
   
 
 
 
 
 
 
 
 
 
 
   public static Map<String, PictureData> getSheetPictrues07(org.apache.poi.xssf.usermodel.XSSFSheet sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook workbook)
   {
     Map<String, PictureData> sheetIndexPicMap = new java.util.HashMap();
     for (org.apache.poi.POIXMLDocumentPart dr : sheet.getRelations()) {
       if ((dr instanceof XSSFDrawing)) {
         XSSFDrawing drawing = (XSSFDrawing)dr;
         List<org.apache.poi.xssf.usermodel.XSSFShape> shapes = drawing.getShapes();
         for (org.apache.poi.xssf.usermodel.XSSFShape shape : shapes) {
           XSSFPicture pic = (XSSFPicture)shape;
           org.apache.poi.xssf.usermodel.XSSFClientAnchor anchor = pic.getPreferredSize();
           CTMarker ctMarker = anchor.getFrom();
           String picIndex = ctMarker.getRow() + "_" + 
             ctMarker.getCol();
           sheetIndexPicMap.put(picIndex, pic.getPictureData());
         }
       }
     }
     return sheetIndexPicMap;
   }
}