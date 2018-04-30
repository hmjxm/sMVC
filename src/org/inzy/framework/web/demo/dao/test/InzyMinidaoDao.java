package org.inzy.framework.web.demo.dao.test;

import java.util.List;
import java.util.Map;

import org.inzy.framework.minidao.annotation.Arguments;
import org.inzy.framework.minidao.annotation.MiniDao;
import org.inzy.framework.minidao.annotation.ResultType;
import org.inzy.framework.minidao.annotation.Sql;
import org.inzy.framework.minidao.hibernate.MiniDaoSupportHiber;
import org.inzy.framework.web.demo.entity.test.InzyMinidaoEntity;


/**
 * Minidao例子
 * @author fancq
 * 
 */
@MiniDao
public interface InzyMinidaoDao extends MiniDaoSupportHiber<InzyMinidaoEntity> {
	@Arguments({"inzyMinidao", "page", "rows"})
	public List<Map> getAllEntities(InzyMinidaoEntity inzyMinidao, int page, int rows);

	@Arguments({"inzyMinidao", "page", "rows"})
	@ResultType("org.inzy.framework.web.demo.entity.test.InzyMinidaoEntity")
	public List<InzyMinidaoEntity> getAllEntities2(InzyMinidaoEntity inzyMinidao, int page, int rows);

	//@Arguments("id")
	//InzyMinidaoEntity getInzyMinidao(String id);

	@Sql("SELECT count(*) FROM inzy_minidao")
	Integer getCount();

	@Sql("SELECT SUM(salary) FROM inzy_minidao")
	Integer getSumSalary();

	/*@Arguments("inzyMinidao")
	int update(InzyMinidaoEntity inzyMinidao);

	@Arguments("inzyMinidao")
	void insert(InzyMinidaoEntity inzyMinidao);

	@Arguments("inzyMinidao")
	void delete(InzyMinidaoEntity inzyMinidao);*/
}
