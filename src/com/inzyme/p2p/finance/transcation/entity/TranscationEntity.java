package com.inzyme.p2p.finance.transcation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.inzy.framework.core.common.entity.IdEntity;
import org.inzy.framework.poi.excel.annotation.Excel;
import com.inzyme.p2p.finance.account.entity.AccountEntity;

/**   
 * @Title: Entity
 * @Description: 账户交易记录
 * @author Auto-generator
 * @date 2015-07-16 10:04:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_transcation", schema = "")
@SuppressWarnings("serial")
public class TranscationEntity extends IdEntity implements java.io.Serializable {
	@Excel(name="所属账户")
	private AccountEntity accountEntity = new AccountEntity();
	/**科目*/
	@Excel(name = "交易科目")
	private java.lang.String title;
	/**借贷方向*/
	@Excel(name = "借贷方向")
	private java.lang.String direction;
	/**合计*/
	@Excel(name = "交易金额")
	private java.lang.Double amount;
	/**账单日期*/
	@Excel(name = "交易日期")
	private java.util.Date accountdate;
	/**备注*/
	@Excel(name = "备注")
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属账户
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountid")
	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借贷方向
	 */
	@Column(name ="DIRECTION",nullable=true,length=2)
	public java.lang.String getDirection(){
		return this.direction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借贷方向
	 */
	public void setDirection(java.lang.String direction){
		this.direction = direction;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  合计
	 */
	@Column(name ="AMOUNT",nullable=true,length=10)
	public java.lang.Double getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  合计
	 */
	public void setAmount(java.lang.Double amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  账单日期
	 */
	@Column(name ="ACCOUNTDATE",nullable=true,length=20)
	public java.util.Date getAccountdate(){
		return this.accountdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  账单日期
	 */
	public void setAccountdate(java.util.Date accountdate){
		this.accountdate = accountdate;
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
