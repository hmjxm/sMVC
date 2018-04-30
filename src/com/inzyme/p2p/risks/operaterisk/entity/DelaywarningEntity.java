package com.inzyme.p2p.risks.operaterisk.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 逾期预警
 * @author Auto-generator
 * @date 2015-07-15 17:58:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_delaywarning", schema = "")
@SuppressWarnings("serial")
public class DelaywarningEntity extends IdEntity implements java.io.Serializable {
	@Excel(name="项目编号")
	private java.lang.String projectid;
	/**会员编号*/
	@Excel(name="会员编号")
	private java.lang.String memberid ;
	/**需要支付的本金*/
	@Excel(name="需要支付的本金")
	private java.lang.Integer needpayprincipal;
	/**需要支付的利息*/
	@Excel(name="需要支付的利息")
	private java.lang.Double needpayinterest;
	/**应该支付的日期*/
	@Excel(name="应该支付的日期")
	private java.util.Date datetobepaid;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目编号
	 */
	@Column(name ="PROJECTID",nullable=true,length=32)
	public java.lang.String getProjectid(){
		return this.projectid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目编号
	 */
	public void setProjectid(java.lang.String projectid){
		this.projectid = projectid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员编号
	 */
	@Column(name ="MEMBERID ",nullable=true,length=32)
	public java.lang.String getMemberid (){
		return this.memberid ;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员编号
	 */
	public void setMemberid (java.lang.String memberid ){
		this.memberid  = memberid ;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  需要支付的本金
	 */
	@Column(name ="NEEDPAYPRINCIPAL",nullable=true,length=11)
	public java.lang.Integer getNeedpayprincipal(){
		return this.needpayprincipal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  需要支付的本金
	 */
	public void setNeedpayprincipal(java.lang.Integer needpayprincipal){
		this.needpayprincipal = needpayprincipal;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  需要支付的利息
	 */
	@Column(name ="NEEDPAYINTEREST",nullable=true,length=32)
	public java.lang.Double getNeedpayinterest(){
		return this.needpayinterest;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  需要支付的利息
	 */
	public void setNeedpayinterest(java.lang.Double needpayinterest){
		this.needpayinterest = needpayinterest;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  应该支付的日期
	 */
	@Column(name ="DATETOBEPAID",nullable=true,length=32)
	public java.util.Date getDatetobepaid(){
		return this.datetobepaid;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  应该支付的日期
	 */
	public void setDatetobepaid(java.util.Date datetobepaid){
		this.datetobepaid = datetobepaid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=32)
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
}
