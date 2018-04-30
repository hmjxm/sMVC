package com.inzyme.p2p.projects.projectcheck.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;
import com.inzyme.p2p.projects.bids.entity.InvestprojectEntity;


/**   
 * @Title: Entity
 * @Description: 债权表
 * @author Auto-generator
 * @date 2015-07-14 10:36:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_debt", schema = "")
@SuppressWarnings("serial")
public class DebtEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建者*/
	private java.lang.String createUser;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新者*/
	private java.lang.String updateUser;
	/**更新时间*/
	private java.util.Date updateTime;
	
	/**会员id*/
	@Excel(name="会员id")
	private java.lang.String holderid;
	/**债权购买金额*/
	@Excel(name="债权购买金额")
	private java.lang.Double amount;
	/**购入日期*/
	@Excel(name="购入日期")
	private java.util.Date buydate;
	/**已还本金*/
	@Excel(name="已还本金")
	private java.lang.String paidprincipal;
	/**已还利息*/
	@Excel(name="已还利息")
	private java.lang.String paidinterest;
	/**待还本金*/
	@Excel(name="待还本金")
	private java.lang.String payableprincipal;
	/**待还利息*/
	@Excel(name="待还利息")
	private java.lang.String payableinterest;
	/**状态*/
	@Excel(name="状态")
	private java.lang.Integer status;
	
	private InvestprojectEntity Investproject = new InvestprojectEntity();
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  创建者
	 */
	@Column(name ="CREATE_USER",nullable=true,length=32)
	public java.lang.String getCreateUser(){
		return this.createUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建者
	 */
	public void setCreateUser(java.lang.String createUser){
		this.createUser = createUser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true,length=20)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新者
	 */
	@Column(name ="UPDATE_USER",nullable=true,length=32)
	public java.lang.String getUpdateUser(){
		return this.updateUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新者
	 */
	public void setUpdateUser(java.lang.String updateUser){
		this.updateUser = updateUser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_TIME",nullable=true,length=20)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员id
	 */
	@Column(name ="HOLDERID",nullable=true,length=36)
	public java.lang.String getHolderid(){
		return this.holderid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员id
	 */
	public void setHolderid(java.lang.String holderid){
		this.holderid = holderid;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  债权购买金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=50)
	public java.lang.Double getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  债权购买金额
	 */
	public void setAmount(java.lang.Double amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  购入日期
	 */
	@Column(name ="BUYDATE",nullable=true,length=32)
	public java.util.Date getBuydate(){
		return this.buydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  购入日期
	 */
	public void setBuydate(java.util.Date buydate){
		this.buydate = buydate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已还本金
	 */
	@Column(name ="PAIDPRINCIPAL",nullable=true,length=50)
	public java.lang.String getPaidprincipal(){
		return this.paidprincipal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已还本金
	 */
	public void setPaidprincipal(java.lang.String paidprincipal){
		this.paidprincipal = paidprincipal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已还利息
	 */
	@Column(name ="PAIDINTEREST",nullable=true,length=50)
	public java.lang.String getPaidinterest(){
		return this.paidinterest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已还利息
	 */
	public void setPaidinterest(java.lang.String paidinterest){
		this.paidinterest = paidinterest;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  待还本金
	 */
	@Column(name ="PAYABLEPRINCIPAL",nullable=true,length=50)
	public java.lang.String getPayableprincipal(){
		return this.payableprincipal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  待还本金
	 */
	public void setPayableprincipal(java.lang.String payableprincipal){
		this.payableprincipal = payableprincipal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  待还利息
	 */
	@Column(name ="PAYABLEINTEREST",nullable=true,length=50)
	public java.lang.String getPayableinterest(){
		return this.payableinterest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  待还利息
	 */
	public void setPayableinterest(java.lang.String payableinterest){
		this.payableinterest = payableinterest;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectid")
	public InvestprojectEntity getInvestproject() {
		return Investproject;
	}

	public void setInvestproject(InvestprojectEntity investproject) {
		Investproject = investproject;
	}
	
}
