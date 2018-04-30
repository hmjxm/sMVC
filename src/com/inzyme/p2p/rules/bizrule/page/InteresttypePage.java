
package com.inzyme.p2p.rules.bizrule.page;
import com.inzyme.p2p.rules.bizrule.entity.InteresttypeEntity;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;

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
import javax.persistence.SequenceGenerator;


/**   
 * @Title: Entity
 * @Description: 计息种类
 * @author onlineGenerator
 * @date 2015-07-04 20:08:18
 * @version V1.0   
 *
 */
public class InteresttypePage implements java.io.Serializable {
	/**保存-计息种类参数*/
	private List<InterestargEntity> interestargList = new ArrayList<InterestargEntity>();
	public List<InterestargEntity> getInterestargList() {
		return interestargList;
	}
	public void setInterestargList(List<InterestargEntity> interestargList) {
		this.interestargList = interestargList;
	}

	/**主键*/
	private java.lang.String id;
	/**计息名称*/
	private java.lang.String inerestname;
	/**参数*/
	private java.lang.String argnums;
	/**Bean对象名称*/
	private java.lang.String caclbean;
	/**备注*/
	private java.lang.String remark;
	/**图标*/
	private java.lang.String iconimg;
	/**状态*/
	private java.lang.String status;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息名称
	 */
	public java.lang.String getInerestname(){
		return this.inerestname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息名称
	 */
	public void setInerestname(java.lang.String inerestname){
		this.inerestname = inerestname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */
	public java.lang.String getArgnums(){
		return this.argnums;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setArgnums(java.lang.String argnums){
		this.argnums = argnums;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Bean对象名称
	 */
	public java.lang.String getCaclbean(){
		return this.caclbean;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Bean对象名称
	 */
	public void setCaclbean(java.lang.String caclbean){
		this.caclbean = caclbean;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标
	 */
	public java.lang.String getIconimg(){
		return this.iconimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标
	 */
	public void setIconimg(java.lang.String iconimg){
		this.iconimg = iconimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
