package com.inzyme.p2p.projects.bids.service;
import com.inzyme.p2p.projects.bids.entity.InvestprojectEntity;
import org.inzy.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface InvestprojectServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InvestprojectEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InvestprojectEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InvestprojectEntity t);
}
