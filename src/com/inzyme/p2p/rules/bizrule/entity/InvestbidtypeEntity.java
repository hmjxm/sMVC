package com.inzyme.p2p.rules.bizrule.entity;

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
 * @Description: 投资标的种类
 * @author onlineGenerator
 * @date 2015-07-04 18:06:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_investbidtype", schema = "")
@SuppressWarnings("serial")
public class InvestbidtypeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**投资名称*/
	private java.lang.String bidtypename;
	/**图标路径*/
	private java.lang.String iconimg;
	/**标题路径*/
	private java.lang.String titleimg;
	/**描述*/
	private java.lang.String description;
	/**允许个人投资*/
	private java.lang.String allowperson;
	/**允许企业投资*/
	private java.lang.String allowcompany;
	/**验证计算对象*/
	@Excel(name="验证计算对象")
	private java.lang.String checkbean;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	/**状态*/
	@Excel(name="状态")
	private java.lang.Integer status;

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
	 *@return: java.lang.String  投资名称
	 */
	@Column(name ="BIDTYPENAME",nullable=true,length=50)
	public java.lang.String getBidtypename(){
		return this.bidtypename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  投资名称
	 */
	public void setBidtypename(java.lang.String bidtypename){
		this.bidtypename = bidtypename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标路径
	 */
	@Column(name ="ICONIMG",nullable=true,length=200)
	public java.lang.String getIconimg(){
		return this.iconimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标路径
	 */
	public void setIconimg(java.lang.String iconimg){
		this.iconimg = iconimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题路径
	 */
	@Column(name ="TITLEIMG",nullable=true,length=200)
	public java.lang.String getTitleimg(){
		return this.titleimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题路径
	 */
	public void setTitleimg(java.lang.String titleimg){
		this.titleimg = titleimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
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
	 *@return: java.lang.String  允许个人投资
	 */
	@Column(name ="ALLOWPERSON",nullable=true,length=50)
	public java.lang.String getAllowperson(){
		return this.allowperson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  允许个人投资
	 */
	public void setAllowperson(java.lang.String allowperson){
		this.allowperson = allowperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  允许企业投资
	 */
	@Column(name ="ALLOWCOMPANY",nullable=true,length=50)
	public java.lang.String getAllowcompany(){
		return this.allowcompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  允许企业投资
	 */
	public void setAllowcompany(java.lang.String allowcompany){
		this.allowcompany = allowcompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验证计算对象
	 */
	@Column(name ="CHECKBEAN",nullable=true,length=250)
	public java.lang.String getCheckbean(){
		return this.checkbean;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验证计算对象
	 */
	public void setCheckbean(java.lang.String checkbean){
		this.checkbean = checkbean;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	
}
