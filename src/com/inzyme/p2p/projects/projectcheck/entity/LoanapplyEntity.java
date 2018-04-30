package com.inzyme.p2p.projects.projectcheck.entity;

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

/**   
 * @Title: Entity
 * @Description: 融资申请
 * @author onlineGenerator
 * @date 2015-07-04 23:35:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_loanapply", schema = "")
@SuppressWarnings("serial")
public class LoanapplyEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**融资类型id*/
	private java.lang.String bidtypeid;
	/**标题*/
	private java.lang.String titles;
	/**人员id*/
	private java.lang.String memberid;
	/**年龄*/
	private java.lang.String usages;
	/**总计*/
	private java.lang.Double amounts;
	/**计息id*/
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
	/**利率*/
	@Excel(name="利率")
	private java.lang.String rate;
	/**审批状态*/
	@Excel(name="审批状态")
	private java.lang.Integer approvalstatus;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
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
	 *@return: java.lang.String  融资类型id
	 */
	@Column(name ="BIDTYPEID",nullable=true,length=50)
	public java.lang.String getBidtypeid(){
		return this.bidtypeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资类型id
	 */
	public void setBidtypeid(java.lang.String bidtypeid){
		this.bidtypeid = bidtypeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLES",nullable=true,length=50)
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
	 *@return: java.lang.String  人员id
	 */
	@Column(name ="MEMBERID",nullable=true,length=36)
	public java.lang.String getMemberid(){
		return this.memberid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员id
	 */
	public void setMemberid(java.lang.String memberid){
		this.memberid = memberid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */
	@Column(name ="USAGES",nullable=true,length=50)
	public java.lang.String getUsages(){
		return this.usages;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
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
	 *@return: java.lang.String  计息id
	 */
	@Column(name ="INTERESTTYPEID",nullable=true,length=36)
	public java.lang.String getInteresttypeid(){
		return this.interesttypeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息id
	 */
	public void setInteresttypeid(java.lang.String interesttypeid){
		this.interesttypeid = interesttypeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */
	@Column(name ="ARGS",nullable=true,length=32)
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
	@Column(name ="RETURNTERM",nullable=true,length=32)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=32)
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
	 *@return: java.lang.String  利率
	 */
	@Column(name ="RATE",nullable=true,length=32)
	public java.lang.String getRate(){
		return this.rate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setRate(java.lang.String rate){
		this.rate = rate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批状态
	 */
	@Column(name ="APPROVALSTATUS",nullable=true,length=32)
	public java.lang.Integer getApprovalstatus(){
		return this.approvalstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批状态
	 */
	public void setApprovalstatus(java.lang.Integer approvalstatus){
		this.approvalstatus = approvalstatus;
	}
	

}
