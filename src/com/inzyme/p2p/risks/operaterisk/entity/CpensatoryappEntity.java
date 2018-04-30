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
 * @Description: 逾期垫付申请
 * @author Auto-generator
 * @date 2015-07-15 17:52:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_cpensatoryapp", schema = "")
@SuppressWarnings("serial")
public class CpensatoryappEntity extends IdEntity implements java.io.Serializable {

	/**债务编号*/
	@Excel(name="债务编号")
	private java.lang.String debtid;
	/**债主编号*/
	@Excel(name="债主编号")
	private java.lang.String holderid;
	/**需支付本金*/
	@Excel(name="需支付本金")
	private java.lang.Integer payprincipal;
	/**需支付利息*/
	@Excel(name="需支付利息")
	private java.lang.Double payinterest;
	/**申请时间*/
	@Excel(name="申请时间")
	private java.util.Date applydate;
	/**支付时间*/
	@Excel(name="支付时间")
	private java.util.Date paydate;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  债务编号
	 */
	@Column(name ="DEBTID",nullable=true,length=32)
	public java.lang.String getDebtid(){
		return this.debtid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债务编号
	 */
	public void setDebtid(java.lang.String debtid){
		this.debtid = debtid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  债主编号
	 */
	@Column(name ="HOLDERID",nullable=true,length=32)
	public java.lang.String getHolderid(){
		return this.holderid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债主编号
	 */
	public void setHolderid(java.lang.String holderid){
		this.holderid = holderid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  需支付本金
	 */
	@Column(name ="PAYPRINCIPAL",nullable=true,length=11)
	public java.lang.Integer getPayprincipal(){
		return this.payprincipal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  需支付本金
	 */
	public void setPayprincipal(java.lang.Integer payprincipal){
		this.payprincipal = payprincipal;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  需支付利息
	 */
	@Column(name ="PAYINTEREST",nullable=true,length=32)
	public java.lang.Double getPayinterest(){
		return this.payinterest;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  需支付利息
	 */
	public void setPayinterest(java.lang.Double payinterest){
		this.payinterest = payinterest;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请时间
	 */
	@Column(name ="APPLYDATE",nullable=true,length=32)
	public java.util.Date getApplydate(){
		return this.applydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setApplydate(java.util.Date applydate){
		this.applydate = applydate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  支付时间
	 */
	@Column(name ="PAYDATE",nullable=true,length=32)
	public java.util.Date getPaydate(){
		return this.paydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  支付时间
	 */
	public void setPaydate(java.util.Date paydate){
		this.paydate = paydate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=20)
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
	@Column(name ="REMARK",nullable=true,length=1000)
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
