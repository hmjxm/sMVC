package com.inzyme.p2p.projects.projectcheck.service;
import com.inzyme.p2p.projects.projectcheck.entity.LoanapplyEntity;
import org.inzy.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface LoanapplyServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(LoanapplyEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(LoanapplyEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(LoanapplyEntity t);
}
