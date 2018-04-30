package com.inzyme.p2p.rules.bizrule.service;
import com.inzyme.p2p.rules.bizrule.entity.InteresttypeEntity;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;

import java.util.List;
import org.inzy.framework.core.common.service.CommonService;
import java.io.Serializable;

public interface InteresttypeServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(InteresttypeEntity interesttype,
	        List<InterestargEntity> interestargList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(InteresttypeEntity interesttype,
	        List<InterestargEntity> interestargList);
	public void delMain (InteresttypeEntity interesttype);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InteresttypeEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InteresttypeEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InteresttypeEntity t);
}
