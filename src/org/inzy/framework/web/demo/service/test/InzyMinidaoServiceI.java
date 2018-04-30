package org.inzy.framework.web.demo.service.test;

import java.util.List;

import org.inzy.framework.web.demo.entity.test.InzyMinidaoEntity;

/**
 * Minidao例子
 * @author fancq
 *
 */
public interface InzyMinidaoServiceI {
	public List<InzyMinidaoEntity> listAll(InzyMinidaoEntity inzyMinidao, int page, int rows);
	
	public InzyMinidaoEntity getEntity(Class clazz, String id);
	
	public void insert(InzyMinidaoEntity inzyMinidao);
	
	public void update(InzyMinidaoEntity inzyMinidao);
	
	public void delete(InzyMinidaoEntity inzyMinidao);
	
	public void deleteAllEntitie(List<InzyMinidaoEntity> entities);
	
	public Integer getCount();
	
	public Integer getSumSalary();
}
