package org.inzy.framework.web.cgreport.page.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.web.cgreport.entity.core.CgreportConfigItemEntity;

import javax.persistence.SequenceGenerator;


import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 动态报表配置抬头
 * @author Goodman Zhang
 * @date 2013-12-07 16:00:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jform_cgreport_head", schema = "")
@SuppressWarnings("serial")
public class CgreportConfigHeadPage extends IdEntity implements java.io.Serializable {
	/**保存-动态报表配置明细*/
	private List<CgreportConfigItemEntity> cgreportConfigItemList = new ArrayList<CgreportConfigItemEntity>();
	public List<CgreportConfigItemEntity> getCgreportConfigItemList() {
		return cgreportConfigItemList;
	}
	public void setCgreportConfigItemList(List<CgreportConfigItemEntity> cgreportConfigItemList) {
		this.cgreportConfigItemList = cgreportConfigItemList;
	}

	/**编码*/
	private java.lang.String code;
	/**名称*/
	private java.lang.String name;
	/**查询数据SQL*/
	private java.lang.String cgrSql;
	/**描述*/
	private java.lang.String content;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编码
	 */
	@Column(name ="CODE",nullable=false,length=36)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	@Column(name ="NAME",nullable=false,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  查询数据SQL
	 */
	@Column(name ="CGR_SQL",nullable=false,length=2000)
	public java.lang.String getCgrSql(){
		return this.cgrSql;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  查询数据SQL
	 */
	public void setCgrSql(java.lang.String cgrSql){
		this.cgrSql = cgrSql;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="CONTENT",nullable=false,length=1000)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
}
