package org.inzy.framework.web.demo.service.impl.test;

import java.util.List;

import org.inzy.framework.web.demo.dao.test.InzyMinidaoDao;
import org.inzy.framework.web.demo.entity.test.InzyMinidaoEntity;
import org.inzy.framework.web.demo.service.test.InzyMinidaoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Minidao例子
 * @author fancq
 *
 */
@Service("inzyMinidaoService")
@Transactional
public class InzyMinidaoServiceImpl implements InzyMinidaoServiceI {
	@Autowired
	private InzyMinidaoDao inzyMinidaoDao;
	
	public List<InzyMinidaoEntity> listAll(InzyMinidaoEntity inzyMinidao, int page, int rows) {
		List<InzyMinidaoEntity> entities = inzyMinidaoDao.getAllEntities2(inzyMinidao, page, rows);
		return entities;
	}
	
	public InzyMinidaoEntity getEntity(Class clazz, String id) {
		InzyMinidaoEntity inzyMinidao = (InzyMinidaoEntity)inzyMinidaoDao.getByIdHiber(clazz, id);
		return inzyMinidao;
	}
	
	public void insert(InzyMinidaoEntity inzyMinidao) {
		inzyMinidaoDao.saveByHiber(inzyMinidao);
	}
	
	public void update(InzyMinidaoEntity inzyMinidao) {
		inzyMinidaoDao.updateByHiber(inzyMinidao);
	}
	
	public void delete(InzyMinidaoEntity inzyMinidao) {
		inzyMinidaoDao.deleteByHiber(inzyMinidao);
	}
	
	public void deleteAllEntitie(List<InzyMinidaoEntity> entities) {
		for (InzyMinidaoEntity entity : entities) {
			inzyMinidaoDao.deleteByHiber(entity);
		}
	}
	
	public Integer getCount() {
		return inzyMinidaoDao.getCount();
	}
	
	public Integer getSumSalary() {
		return inzyMinidaoDao.getSumSalary();
	}
}