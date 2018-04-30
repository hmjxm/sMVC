 package org.inzy.framework.poi.word.entity.params;
 
 
 
 
 public class ListParamEntity
 {
   public static final String SINGLE = "single";
   
 
 
   public static final String LIST = "list";
   
 
 
   private String name;
   
 
 
   private String target;
   
 
   private Object value;
   
 
   private String type;
   
 
 
   public ListParamEntity() {}
   
 
 
   public ListParamEntity(String name, String target)
   {
     this.name = name;
     this.target = target;
     this.type = "list";
   }
   
   public ListParamEntity(String name, Object value) {
     this.name = name;
     this.value = value;
     this.type = "list";
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public String getTarget() {
     return this.target;
   }
   
   public void setTarget(String target) {
     this.target = target;
   }
   
   public String getType() {
     return this.type;
   }
   
   public void setType(String type) {
     this.type = type;
   }
   
   public Object getValue() {
     return this.value;
   }
   
   public void setValue(Object value) {
     this.value = value;
   }
}