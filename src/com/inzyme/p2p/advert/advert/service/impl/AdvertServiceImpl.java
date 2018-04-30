package com.inzyme.p2p.advert.advert.service.impl;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.advert.advert.entity.AdvertEntity;
import com.inzyme.p2p.advert.advert.service.AdvertServiceI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("advertService")
@Transactional
public class AdvertServiceImpl extends CommonServiceImpl implements AdvertServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AdvertEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((AdvertEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((AdvertEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AdvertEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AdvertEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AdvertEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AdvertEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_user}",String.valueOf(t.getCreateUser()));
 		sql  = sql.replace("#{update_user}",String.valueOf(t.getUpdateUser()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{update_time}",String.valueOf(t.getUpdateTime()));
 		sql  = sql.replace("#{spacecode}",String.valueOf(t.getSpacecode()));
 		sql  = sql.replace("#{adttype}",String.valueOf(t.getAdttype()));
 		sql  = sql.replace("#{adtname}",String.valueOf(t.getAdtname()));
 		sql  = sql.replace("#{templateid}",String.valueOf(t.getTemplateid()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{imgurl}",String.valueOf(t.getImgurl()));
 		sql  = sql.replace("#{linkurl}",String.valueOf(t.getLinkurl()));
 		sql  = sql.replace("#{effectivedate}",String.valueOf(t.getEffectivedate()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}