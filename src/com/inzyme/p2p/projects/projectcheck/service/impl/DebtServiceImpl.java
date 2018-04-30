package com.inzyme.p2p.projects.projectcheck.service.impl;
import com.inzyme.p2p.projects.projectcheck.service.DebtServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.projects.projectcheck.entity.DebtEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("debtService")
@Transactional
public class DebtServiceImpl extends CommonServiceImpl implements DebtServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((DebtEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((DebtEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((DebtEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(DebtEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(DebtEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(DebtEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,DebtEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_user}",String.valueOf(t.getCreateUser()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{update_user}",String.valueOf(t.getUpdateUser()));
 		sql  = sql.replace("#{update_time}",String.valueOf(t.getUpdateTime()));
 		sql  = sql.replace("#{holderid}",String.valueOf(t.getHolderid()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{buydate}",String.valueOf(t.getBuydate()));
 		sql  = sql.replace("#{paidprincipal}",String.valueOf(t.getPaidprincipal()));
 		sql  = sql.replace("#{paidinterest}",String.valueOf(t.getPaidinterest()));
 		sql  = sql.replace("#{payableprincipal}",String.valueOf(t.getPayableprincipal()));
 		sql  = sql.replace("#{payableinterest}",String.valueOf(t.getPayableinterest()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}