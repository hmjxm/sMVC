package org.inzy.framework.minidao.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public abstract interface IGenericBaseCommonDao
{
  public abstract <T> void save(T paramT);
  
  public abstract <T> void saveOrUpdate(T paramT);
  
  public abstract <T> void delete(T paramT);
  
  public abstract <T> T get(T paramT);
  
  public abstract <T> List<T> loadAll(T paramT);
  
  public abstract <T> T get(Class<T> paramClass, Serializable paramSerializable);
  
  public abstract Session getSession();
  
  public abstract <T> T findUniqueByProperty(Class<T> paramClass, String paramString, Object paramObject);
  
  public abstract <T> void deleteEntityById(Class paramClass, Serializable paramSerializable);
}