package com.inzyme.p2p.projects.projectcheck.service.impl;
import com.inzyme.p2p.projects.projectcheck.service.LoanapplyServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.projects.projectcheck.entity.LoanapplyEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("loanapplyService")
@Transactional
public class LoanapplyServiceImpl extends CommonServiceImpl implements LoanapplyServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((LoanapplyEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((LoanapplyEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((LoanapplyEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(LoanapplyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(LoanapplyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(LoanapplyEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,LoanapplyEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bidtypeid}",String.valueOf(t.getBidtypeid()));
 		sql  = sql.replace("#{titles}",String.valueOf(t.getTitles()));
 		sql  = sql.replace("#{memberid}",String.valueOf(t.getMemberid()));
 		sql  = sql.replace("#{usages}",String.valueOf(t.getUsages()));
 		sql  = sql.replace("#{amounts}",String.valueOf(t.getAmounts()));
 		sql  = sql.replace("#{interesttypeid}",String.valueOf(t.getInteresttypeid()));
 		sql  = sql.replace("#{args}",String.valueOf(t.getArgs()));
 		sql  = sql.replace("#{returnterm}",String.valueOf(t.getReturnterm()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}