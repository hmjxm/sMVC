package org.inzy.framework.minidao.hibernate;

import java.io.Serializable;
import java.util.List;

public abstract interface MiniDaoSupportHiber<T>
{
  public abstract void saveByHiber(T paramT);
  
  public abstract <T> T getByIdHiber(Class<T> paramClass, Serializable paramSerializable);
  
  public abstract <T> T getByEntityHiber(T paramT);
  
  public abstract void updateByHiber(T paramT);
  
  public abstract void deleteByHiber(T paramT);
  
  public abstract List<T> listByHiber(T paramT);
  
  public abstract <T> void deleteByIdHiber(Class paramClass, Serializable paramSerializable);
}