package com.inzyme.p2p.rules.bizrule.service;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;
import org.inzy.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface InvestbidtypeServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	public <T> void update(T entity);
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InvestbidtypeEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InvestbidtypeEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InvestbidtypeEntity t);
 	public boolean update(InvestbidtypeEntity t);
}
