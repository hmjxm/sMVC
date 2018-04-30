 package org.inzy.framework.minidao.hibernate;
 
 import javax.persistence.GeneratedValue;
 import javax.persistence.MappedSuperclass;
 
 @MappedSuperclass
 public abstract class IdEntity
 {
   private String id;
   
   @javax.persistence.Id
   @GeneratedValue(generator="hibernate-uuid")
   @org.hibernate.annotations.GenericGenerator(name="hibernate-uuid", strategy="uuid")
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
}