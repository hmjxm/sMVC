package com.inzyme.p2p.finance.account.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;

import com.inzyme.p2p.finance.transcation.entity.TranscationEntity;

/**   
 * @Title: Entity
 * @Description: 帐户表
 * @author Auto-generator
 * @date 2015-07-16 10:01:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_account", schema = "")
@SuppressWarnings("serial")
public class AccountEntity extends IdEntity implements java.io.Serializable {
	/**帐号*/
	private java.lang.String accountno;
	/**户主*/
	private java.lang.String holderid;
	/**账户类型*/
	private java.lang.String accounttype;
	/**账户名称*/
	private java.lang.String accountname;
	/**余额*/
	private java.lang.Double balance;
	/**状态*/
	private java.lang.String status;
	/**备注*/
	private java.lang.String remark;
	
	private List<TranscationEntity> transcationEntity = new ArrayList<TranscationEntity>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountEntity")
	public List<TranscationEntity> getTranscationEntity() {
		return transcationEntity;
	}

	public void setTranscationEntity(List<TranscationEntity> transcationEntity) {
		this.transcationEntity = transcationEntity;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  帐号
	 */
	@Column(name ="ACCOUNTNO",nullable=true,length=32)
	public java.lang.String getAccountno(){
		return this.accountno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帐号
	 */
	public void setAccountno(java.lang.String accountno){
		this.accountno = accountno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户主
	 */
	@Column(name ="HOLDERID",nullable=true,length=32)
	public java.lang.String getHolderid(){
		return this.holderid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户主
	 */
	public void setHolderid(java.lang.String holderid){
		this.holderid = holderid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户类型
	 */
	@Column(name ="ACCOUNTTYPE",nullable=true,length=2)
	public java.lang.String getAccounttype(){
		return this.accounttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类型
	 */
	public void setAccounttype(java.lang.String accounttype){
		this.accounttype = accounttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户名称
	 */
	@Column(name ="ACCOUNTNAME",nullable=true,length=50)
	public java.lang.String getAccountname(){
		return this.accountname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户名称
	 */
	public void setAccountname(java.lang.String accountname){
		this.accountname = accountname;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  余额
	 */
	@Column(name ="BALANCE",nullable=true,length=32)
	public java.lang.Double getBalance(){
		return this.balance;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  余额
	 */
	public void setBalance(java.lang.Double balance){
		this.balance = balance;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=2)
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
