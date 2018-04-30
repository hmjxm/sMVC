 package org.inzy.framework.poi.word.entity;
 
 
 
 
 
 
 
 
 public class WordImageEntity
 {
   public static String URL = "url";
   public static String Data = "data";
   
 
 
   private String type = URL;
   
 
   private int width;
   
   private int height;
   
   private String url;
   
   private byte[] data;
   
 
   public WordImageEntity() {}
   
 
   public WordImageEntity(String url, int width, int height)
   {
     this.url = url;
     this.width = width;
     this.height = height;
   }
   
   public WordImageEntity(byte[] data, int width, int height) {
     this.data = data;
     this.width = width;
     this.height = height;
     this.type = Data;
   }
   
   public String getType() {
     return this.type;
   }
   
   public void setType(String type) {
     this.type = type;
   }
   
   public int getWidth() {
     return this.width;
   }
   
   public void setWidth(int width) {
     this.width = width;
   }
   
   public int getHeight() {
     return this.height;
   }
   
   public void setHeight(int height) {
     this.height = height;
   }
   
   public String getUrl() {
     return this.url;
   }
   
   public void setUrl(String url) {
     this.url = url;
   }
   
   public byte[] getData() {
     return this.data;
   }
   
   public void setData(byte[] data) {
     this.data = data;
   }
}