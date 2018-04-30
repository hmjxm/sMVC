package com.inzyme.p2p.projects.bids.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;


/**   
 * @Title: Entity
 * @Description: 投资项目表
 * @author Auto-generator
 * @date 2015-07-14 10:35:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_investproject", schema = "")
@SuppressWarnings("serial")
public class InvestprojectEntity implements java.io.Serializable {
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
	/**投资标的种类*/
	@Excel(name="投资标的种类")
	private java.lang.String bidtypeid;
	/**标题*/
	@Excel(name="标题")
	private java.lang.String titles;
	/**借款人的会员ID*/
	@Excel(name="借款人的会员ID")
	private java.lang.String memberid;
	/**用途*/
	@Excel(name="用途")
	private java.lang.String usages;
	/**金额*/
	@Excel(name="金额")
	private java.lang.Double amounts;
	/**利率*/
	@Excel(name="利率")
	private java.lang.String interestrate;
	/**计息名称*/
	@Excel(name="计息名称")
	private java.lang.String interesttypeid;
	/**参数*/
	@Excel(name="参数")
	private java.lang.String args;
	/**还款期限*/
	@Excel(name="还款期限")
	private java.lang.String returnterm;
	/**描述*/
	@Excel(name="描述")
	private java.lang.String description;
	/**借款申请ID*/
	@Excel(name="借款申请ID")
	private java.lang.String loanapplyid;
	/**投标期限*/
	@Excel(name="投标期限")
	private java.lang.String bidterm;
	/**投标发布日*/
	@Excel(name="投标发布日")
	private java.util.Date issuedate;
	/**记账日*/
	@Excel(name="记账日")
	private java.util.Date accountdate;
	/**项目状态*/
	@Excel(name="项目状态")
	private java.lang.Integer status;
	/**已投标金额*/
	@Excel(name="已投标金额")
	private java.lang.String biddenamount;
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
	/**版本号*/
	@Excel(name="版本号")
	private java.lang.String versions;
	
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
	 *@return: java.lang.String  投资标的种类
	 */
	@Column(name ="BIDTYPEID",nullable=true,length=36)
	public java.lang.String getBidtypeid(){
		return this.bidtypeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  投资标的种类
	 */
	public void setBidtypeid(java.lang.String bidtypeid){
		this.bidtypeid = bidtypeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLES",nullable=true,length=200)
	public java.lang.String getTitles(){
		return this.titles;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitles(java.lang.String titles){
		this.titles = titles;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款人的会员ID
	 */
	@Column(name ="memberid",nullable=true,length=36)
	public java.lang.String getMemberid(){
		return this.memberid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款人的会员ID
	 */
	public void setMemberid(java.lang.String memberid){
		this.memberid = memberid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用途
	 */
	@Column(name ="USAGES",nullable=true,length=200)
	public java.lang.String getUsages(){
		return this.usages;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用途
	 */
	public void setUsages(java.lang.String usages){
		this.usages = usages;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */
	@Column(name ="AMOUNTS",nullable=true,length=32)
	public java.lang.Double getAmounts(){
		return this.amounts;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  金额
	 */
	public void setAmounts(java.lang.Double amounts){
		this.amounts = amounts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率
	 */
	@Column(name ="INTERESTRATE",nullable=true,length=50)
	public java.lang.String getInterestrate(){
		return this.interestrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setInterestrate(java.lang.String interestrate){
		this.interestrate = interestrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息名称
	 */
	@Column(name ="INTERESTTYPEID",nullable=true,length=36)
	public java.lang.String getInteresttypeid(){
		return this.interesttypeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息名称
	 */
	public void setInteresttypeid(java.lang.String interesttypeid){
		this.interesttypeid = interesttypeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */
	@Column(name ="ARGS",nullable=true,length=200)
	public java.lang.String getArgs(){
		return this.args;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setArgs(java.lang.String args){
		this.args = args;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  还款期限
	 */
	@Column(name ="RETURNTERM",nullable=true,length=50)
	public java.lang.String getReturnterm(){
		return this.returnterm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款期限
	 */
	public void setReturnterm(java.lang.String returnterm){
		this.returnterm = returnterm;
	}
	/**
	 *方法: 取得java.sql.Blob
	 *@return: java.sql.Blob  描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=2000)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款申请ID
	 */
	@Column(name ="LOANAPPLYID",nullable=true,length=36)
	public java.lang.String getLoanapplyid(){
		return this.loanapplyid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款申请ID
	 */
	public void setLoanapplyid(java.lang.String loanapplyid){
		this.loanapplyid = loanapplyid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  投标期限
	 */
	@Column(name ="BIDTERM",nullable=true,length=32)
	public java.lang.String getBidterm(){
		return this.bidterm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  投标期限
	 */
	public void setBidterm(java.lang.String bidterm){
		this.bidterm = bidterm;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  投标发布日
	 */
	@Column(name ="ISSUEDATE",nullable=true,length=32)
	public java.util.Date getIssuedate(){
		return this.issuedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  投标发布日
	 */
	public void setIssuedate(java.util.Date issuedate){
		this.issuedate = issuedate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  记账日
	 */
	@Column(name ="ACCOUNTDATE",nullable=true,length=32)
	public java.util.Date getAccountdate(){
		return this.accountdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  记账日
	 */
	public void setAccountdate(java.util.Date accountdate){
		this.accountdate = accountdate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  项目状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  项目状态
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已投标金额
	 */
	@Column(name ="BIDDENAMOUNT",nullable=true,length=50)
	public java.lang.String getBiddenamount(){
		return this.biddenamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已投标金额
	 */
	public void setBiddenamount(java.lang.String biddenamount){
		this.biddenamount = biddenamount;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号
	 */
	@Column(name ="VERSIONS",nullable=true,length=36)
	public java.lang.String getVersions(){
		return this.versions;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号
	 */
	public void setVersions(java.lang.String versions){
		this.versions = versions;
	}
}
