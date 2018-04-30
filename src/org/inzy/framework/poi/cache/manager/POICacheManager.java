 package org.inzy.framework.poi.cache.manager;
 
 import com.google.common.cache.CacheBuilder;
 import com.google.common.cache.CacheLoader;
 import com.google.common.cache.LoadingCache;
 import java.io.ByteArrayInputStream;
 import java.io.InputStream;
 import java.util.Arrays;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.TimeUnit;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class POICacheManager
 {
   private static LoadingCache<String, byte[]> loadingCache = CacheBuilder.newBuilder()
     .expireAfterWrite(1L, TimeUnit.DAYS).maximumSize(50L)
     .build(new CacheLoader()
   {
     public byte[] load(Object url) throws Exception {
       return new FileLoade().getFile(String.valueOf(url));
     }
   });
   
 
 
 
 
   public static InputStream getFile(String id)
   {
     try
     {
       byte[] result = Arrays.copyOf((byte[])loadingCache.get(id), ((byte[])loadingCache.get(id)).length);
       return new ByteArrayInputStream(result);
     } catch (ExecutionException e) {
       e.printStackTrace();
     }
     return null;
   }
}