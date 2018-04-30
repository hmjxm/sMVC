package com.inzyme.p2p.rules.bizrule.service.impl;
import com.inzyme.p2p.rules.bizrule.service.InvestbidtypeServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("investbidtypeService")
@Transactional
public class InvestbidtypeServiceImpl extends CommonServiceImpl implements InvestbidtypeServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((InvestbidtypeEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((InvestbidtypeEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((InvestbidtypeEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InvestbidtypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InvestbidtypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InvestbidtypeEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,InvestbidtypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bidtypename}",String.valueOf(t.getBidtypename()));
 		sql  = sql.replace("#{iconimg}",String.valueOf(t.getIconimg()));
 		sql  = sql.replace("#{titleimg}",String.valueOf(t.getTitleimg()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{allowperson}",String.valueOf(t.getAllowperson()));
 		sql  = sql.replace("#{allowcompany}",String.valueOf(t.getAllowcompany()));
 		sql  = sql.replace("#{checkbean}",String.valueOf(t.getCheckbean()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public <T> void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(InvestbidtypeEntity t) {
		System.out.println("kaljdsflkjasdlk fjaklsdjf kldaj ");
		return false;
	}
}