package com.inzyme.p2p.projects.bids.service.impl;
import com.inzyme.p2p.projects.bids.service.InvestprojectServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.projects.bids.entity.InvestprojectEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("investprojectService")
@Transactional
public class InvestprojectServiceImpl extends CommonServiceImpl implements InvestprojectServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((InvestprojectEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((InvestprojectEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((InvestprojectEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InvestprojectEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InvestprojectEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InvestprojectEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,InvestprojectEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_user}",String.valueOf(t.getCreateUser()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{update_user}",String.valueOf(t.getUpdateUser()));
 		sql  = sql.replace("#{update_time}",String.valueOf(t.getUpdateTime()));
 		sql  = sql.replace("#{bidtypeid}",String.valueOf(t.getBidtypeid()));
 		sql  = sql.replace("#{titles}",String.valueOf(t.getTitles()));
 		sql  = sql.replace("#{borrowerid}",String.valueOf(t.getMemberid()));
 		sql  = sql.replace("#{usages}",String.valueOf(t.getUsages()));
 		sql  = sql.replace("#{amounts}",String.valueOf(t.getAmounts()));
 		sql  = sql.replace("#{interestrate}",String.valueOf(t.getInterestrate()));
 		sql  = sql.replace("#{interesttypeid}",String.valueOf(t.getInteresttypeid()));
 		sql  = sql.replace("#{args}",String.valueOf(t.getArgs()));
 		sql  = sql.replace("#{returnterm}",String.valueOf(t.getReturnterm()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{loanapplyid}",String.valueOf(t.getLoanapplyid()));
 		sql  = sql.replace("#{bidterm}",String.valueOf(t.getBidterm()));
 		sql  = sql.replace("#{issuedate}",String.valueOf(t.getIssuedate()));
 		sql  = sql.replace("#{accountdate}",String.valueOf(t.getAccountdate()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{biddenamount}",String.valueOf(t.getBiddenamount()));
 		sql  = sql.replace("#{paidprincipal}",String.valueOf(t.getPaidprincipal()));
 		sql  = sql.replace("#{paidinterest}",String.valueOf(t.getPaidinterest()));
 		sql  = sql.replace("#{payableprincipal}",String.valueOf(t.getPayableprincipal()));
 		sql  = sql.replace("#{payableinterest}",String.valueOf(t.getPayableinterest()));
 		sql  = sql.replace("#{versions}",String.valueOf(t.getVersions()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}