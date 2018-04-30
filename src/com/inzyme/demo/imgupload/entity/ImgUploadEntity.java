package com.inzyme.demo.imgupload.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 上传下载示例
 * @author zhangdaihao
 * @date 2014-08-07 15:41:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "inzy_demo_user", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ImgUploadEntity extends IdEntity implements java.io.Serializable {
	/**部门ID*/
	private java.lang.String depId;
	/**用户名*/
	private java.lang.String userName;
	/**性别*/
	private java.lang.String sex;
	/**年龄*/
	private java.lang.Integer age;
	/**工资*/
	private BigDecimal salary;
	/**生日*/
	private java.util.Date birthday;
	/**手机号码*/
	private java.lang.String mobilePhone;
	/**办公电话*/
	private java.lang.String officePhone;
	/**常用邮箱*/
	private java.lang.String email;
	/**状态*/
	private java.lang.String status;
	/**备注*/
	private java.lang.String content;
	/**照片路径*/
	private java.lang.String imgUrl;
	/**imgWidth*/
	private java.lang.Integer imgWidth;
	/**imgHeight*/
	private java.lang.Integer imgHeight;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门ID
	 */
	@Column(name ="DEP_ID",nullable=true,length=32)
	public java.lang.String getDepId(){
		return this.depId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门ID
	 */
	public void setDepId(java.lang.String depId){
		this.depId = depId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名
	 */
	@Column(name ="USER_NAME",nullable=false,length=100)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年龄
	 */
	@Column(name ="AGE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年龄
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  工资
	 */
	@Column(name ="SALARY",nullable=true,precision=19,scale=2)
	public BigDecimal getSalary(){
		return this.salary;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  工资
	 */
	public void setSalary(BigDecimal salary){
		this.salary = salary;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生日
	 */
	@Column(name ="BIRTHDAY",nullable=true)
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生日
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码
	 */
	@Column(name ="MOBILE_PHONE",nullable=true,length=100)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码
	 */
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公电话
	 */
	@Column(name ="OFFICE_PHONE",nullable=true,length=100)
	public java.lang.String getOfficePhone(){
		return this.officePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办公电话
	 */
	public void setOfficePhone(java.lang.String officePhone){
		this.officePhone = officePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  常用邮箱
	 */
	@Column(name ="EMAIL",nullable=true,length=200)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  常用邮箱
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=1)
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
	@Column(name ="CONTENT",nullable=true,length=300)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  照片路径
	 */
	@Column(name ="IMG_URL",nullable=true,length=255)
	public java.lang.String getImgUrl(){
		return this.imgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  照片路径
	 */
	public void setImgUrl(java.lang.String imgUrl){
		this.imgUrl = imgUrl;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  imgWidth
	 */
	@Column(name ="IMG_WIDTH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getImgWidth(){
		return this.imgWidth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  imgWidth
	 */
	public void setImgWidth(java.lang.Integer imgWidth){
		this.imgWidth = imgWidth;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  imgHeight
	 */
	@Column(name ="IMG_HEIGHT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getImgHeight(){
		return this.imgHeight;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  imgHeight
	 */
	public void setImgHeight(java.lang.Integer imgHeight){
		this.imgHeight = imgHeight;
	}
}
