package com.inzyme.p2p.rules.bizrule.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 计息种类参数
 * @author onlineGenerator
 * @date 2015-07-04 20:08:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_interestarg", schema = "")
@SuppressWarnings("serial")
public class InterestargEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**计息id*/
	private java.lang.String interesttypeid;
	/**参数名*/
	private java.lang.String argnames;
	/**参数类型*/
	private java.lang.String argtypes;
	/**参数长度*/
	private java.lang.String arglengths;
	/**备注*/
	private java.lang.String remark;
	
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
	 *@return: java.lang.String  参数名
	 */
	@Column(name ="ARGNAMES",nullable=true,length=50)
	public java.lang.String getArgnames(){
		return this.argnames;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数名
	 */
	public void setArgnames(java.lang.String argnames){
		this.argnames = argnames;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数类型
	 */
	@Column(name ="ARGTYPES",nullable=true,length=50)
	public java.lang.String getArgtypes(){
		return this.argtypes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数类型
	 */
	public void setArgtypes(java.lang.String argtypes){
		this.argtypes = argtypes;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数长度
	 */
	@Column(name ="ARGLENGTHS",nullable=true,length=250)
	public java.lang.String getArglengths(){
		return this.arglengths;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数长度
	 */
	public void setArglengths(java.lang.String arglengths){
		this.arglengths = arglengths;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=50)
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
