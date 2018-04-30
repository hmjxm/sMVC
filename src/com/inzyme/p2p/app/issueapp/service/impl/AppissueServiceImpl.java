package com.inzyme.p2p.app.issueapp.service.impl;
import com.inzyme.p2p.app.issueapp.service.AppissueServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.app.issueapp.entity.AppissueEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("appissueService")
@Transactional
public class AppissueServiceImpl extends CommonServiceImpl implements AppissueServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AppissueEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((AppissueEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((AppissueEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AppissueEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AppissueEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AppissueEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AppissueEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{appcode}",String.valueOf(t.getAppcode()));
 		sql  = sql.replace("#{appname}",String.valueOf(t.getAppname()));
 		sql  = sql.replace("#{apptype}",String.valueOf(t.getApptype()));
 		sql  = sql.replace("#{issueversion}",String.valueOf(t.getIssueversion()));
 		sql  = sql.replace("#{iconimg}",String.valueOf(t.getIconimg()));
 		sql  = sql.replace("#{downloadurl}",String.valueOf(t.getDownloadurl()));
 		sql  = sql.replace("#{effectivedate}",String.valueOf(t.getEffectivedate()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}