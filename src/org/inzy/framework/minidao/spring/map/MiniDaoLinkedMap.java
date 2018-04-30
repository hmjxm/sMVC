 package org.inzy.framework.minidao.spring.map;
 
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 
 public class MiniDaoLinkedMap
   extends LinkedHashMap<String, Object>
 {
   private static final long serialVersionUID = 1L;
   private final Locale locale;
   
   public MiniDaoLinkedMap()
   {
     this(null);
   }
   
   public MiniDaoLinkedMap(Locale locale) {
     this.locale = (locale == null ? Locale.getDefault() : locale);
   }
   
   public MiniDaoLinkedMap(int initialCapacity) {
     this(initialCapacity, null);
   }
   
   public MiniDaoLinkedMap(int initialCapacity, Locale locale) {
     super(initialCapacity);
     this.locale = (locale == null ? Locale.getDefault() : locale);
   }
   
   public Object put(String key, Object value) {
     return super.put(convertKey(key), value);
   }
   
   public void putAll(Map map) {
     if (map.isEmpty())
       return;
     Map.Entry entry;
     for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); put(
           convertKey((String)entry.getKey()), entry.getValue())) {
       entry = (Map.Entry)iterator.next();
     }
   }
   
   public boolean containsKey(Object key)
   {
     return ((key instanceof String)) && (super.containsKey(convertKey((String)key)));
   }
   
   public Object get(Object key) {
     if ((key instanceof String)) {
       return super.get(convertKey((String)key));
     }
     return null;
   }
   
   public Object remove(Object key) {
     if ((key instanceof String)) {
       return super.remove(convertKey((String)key));
     }
     return null;
   }
   
   public void clear() {
     super.clear();
   }
   
   protected String convertKey(String key) {
     return key.toLowerCase(this.locale);
   }
}