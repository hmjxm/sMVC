package com.inzyme.p2p.rules.bizrule.service.impl;
import com.inzyme.p2p.rules.bizrule.service.InteresttypeServiceI;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import com.inzyme.p2p.rules.bizrule.entity.InteresttypeEntity;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("interesttypeService")
@Transactional
public class InteresttypeServiceImpl extends CommonServiceImpl implements InteresttypeServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((InteresttypeEntity)entity);
 	}
	
	public void addMain(InteresttypeEntity interesttype,
	        List<InterestargEntity> interestargList){
			//保存主信息
			this.save(interesttype);
		
			/**保存-计息种类参数*/
			for(InterestargEntity interestarg:interestargList){
				//外键设置
				interestarg.setInteresttypeid(interesttype.getId());
				this.save(interestarg);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(interesttype);
	}

	
	public void updateMain(InteresttypeEntity interesttype,
	        List<InterestargEntity> interestargList) {
		//保存主表信息
		this.saveOrUpdate(interesttype);
		//===================================================================================
		//获取参数
		Object id0 = interesttype.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-计息种类参数
	    String hql0 = "from InterestargEntity where 1 = 1 AND iNTERESTTYPEID = ? ";
	    List<InterestargEntity> interestargOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-计息种类参数
		for(InterestargEntity oldE:interestargOldList){
			boolean isUpdate = false;
				for(InterestargEntity sendE:interestargList){
					//需要更新的明细数据-计息种类参数
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-计息种类参数
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-计息种类参数
			for(InterestargEntity interestarg:interestargList){
				if(oConvertUtils.isEmpty(interestarg.getId())){
					//外键设置
					interestarg.setInteresttypeid(interesttype.getId());
					this.save(interestarg);
				}
			}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(interesttype);
	}

	
	public void delMain(InteresttypeEntity interesttype) {
		//删除主表信息
		this.delete(interesttype);
		//===================================================================================
		//获取参数
		Object id0 = interesttype.getId();
		//===================================================================================
		//删除-计息种类参数
	    String hql0 = "from InterestargEntity where 1 = 1 AND iNTERESTTYPEID = ? ";
	    List<InterestargEntity> interestargOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(interestargOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(InteresttypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(InteresttypeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(InteresttypeEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,InteresttypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{inerestname}",String.valueOf(t.getInerestname()));
 		sql  = sql.replace("#{argnums}",String.valueOf(t.getArgnums()));
 		sql  = sql.replace("#{caclbean}",String.valueOf(t.getCaclbean()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{iconimg}",String.valueOf(t.getIconimg()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}