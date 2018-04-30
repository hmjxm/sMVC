package com.inzyme.p2p.members.personalmember.entity;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;
import org.inzy.framework.core.common.entity.IdEntity;

/**
 * @Title: Entity
 * @Description: 会员个人信息
 * @author onlineGenerator
 * @date 2015-07-02 15:01:50
 * @version V1.0
 * 
 */
@Entity
@Table(name = "p2p_t_personalinfo", schema = "")
//@PrimaryKeyJoinColumn(name = "id")
public class PersonalinfoEntity extends MemberEntity implements
		java.io.Serializable {
	/** 姓名 */
	@Excel(name = "姓名")
	private java.lang.String fullname;
	/** 最后一个名 */
	@Excel(name = "最后一个名")
	private java.lang.String lastname;
	/** 第一个名 */
	@Excel(name = "第一个名")
	private java.lang.String firstname;
	/** 性别 */
	@Excel(name = "性别")
	private java.lang.String sex;
	/** 出生日期 */
	@Excel(name = "出生日期")
	private java.util.Date birth;
	/** 国家 */
	@Excel(name = "国家")
	private java.lang.String nation;
	/** 电话号码 */
	@Excel(name = "电话号码")
	private java.lang.String telno;
	/** 电子邮件 */
	@Excel(name = "电子邮件")
	private java.lang.String email;
	/** 国家代码 */
	@Excel(name = "国家代码")
	private java.lang.String countrycode;
	/** 省码 */
	@Excel(name = "省码")
	private java.lang.String provincecode;
	/** 城市区号 */
	@Excel(name = "城市区号")
	private java.lang.String citycode;
	/** 地区代码 */
	@Excel(name = "地区代码")
	private java.lang.String districtcode;
	/** 通讯地址 */
	@Excel(name = "通讯地址")
	private java.lang.String address;
	/** 备注 */
	@Excel(name = "备注")
	private java.lang.String remark;

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 姓名
	 */
	@Column(name = "FULLNAME", nullable = true, length = 50)
	public java.lang.String getFullname() {
		return this.fullname;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 姓名
	 */
	public void setFullname(java.lang.String fullname) {
		this.fullname = fullname;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 最后一个名
	 */
	@Column(name = "LASTNAME", nullable = true, length = 30)
	public java.lang.String getLastname() {
		return this.lastname;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 最后一个名
	 */
	public void setLastname(java.lang.String lastname) {
		this.lastname = lastname;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 第一个名
	 */
	@Column(name = "FIRSTNAME", nullable = true, length = 20)
	public java.lang.String getFirstname() {
		return this.firstname;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 第一个名
	 */
	public void setFirstname(java.lang.String firstname) {
		this.firstname = firstname;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 性别
	 */
	@Column(name = "SEX", nullable = true, length = 2)
	public java.lang.String getSex() {
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 性别
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 *方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 出生日期
	 */
	@Column(name = "BIRTH", nullable = true, length = 20)
	public java.util.Date getBirth() {
		return this.birth;
	}

	/**
	 *方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 出生日期
	 */
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 国家
	 */
	@Column(name = "NATION", nullable = true, length = 32)
	public java.lang.String getNation() {
		return this.nation;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 国家
	 */
	public void setNation(java.lang.String nation) {
		this.nation = nation;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 电话号码
	 */
	@Column(name = "TELNO", nullable = true, length = 32)
	public java.lang.String getTelno() {
		return this.telno;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 电话号码
	 */
	public void setTelno(java.lang.String telno) {
		this.telno = telno;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 电子邮件
	 */
	@Column(name = "EMAIL", nullable = true, length = 50)
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 电子邮件
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 国家代码
	 */
	@Column(name = "COUNTRYCODE", nullable = true, length = 20)
	public java.lang.String getCountrycode() {
		return this.countrycode;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 国家代码
	 */
	public void setCountrycode(java.lang.String countrycode) {
		this.countrycode = countrycode;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 省码
	 */
	@Column(name = "PROVINCECODE", nullable = true, length = 20)
	public java.lang.String getProvincecode() {
		return this.provincecode;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 省码
	 */
	public void setProvincecode(java.lang.String provincecode) {
		this.provincecode = provincecode;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 城市区号
	 */
	@Column(name = "CITYCODE", nullable = true, length = 20)
	public java.lang.String getCitycode() {
		return this.citycode;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 城市区号
	 */
	public void setCitycode(java.lang.String citycode) {
		this.citycode = citycode;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 地区代码
	 */
	@Column(name = "DISTRICTCODE", nullable = true, length = 20)
	public java.lang.String getDistrictcode() {
		return this.districtcode;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 地区代码
	 */
	public void setDistrictcode(java.lang.String districtcode) {
		this.districtcode = districtcode;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 通讯地址
	 */
	@Column(name = "ADDRESS", nullable = true, length = 100)
	public java.lang.String getAddress() {
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 通讯地址
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	/**
	 *方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 备注
	 */
	@Column(name = "REMARK", nullable = true, length = 50)
	public java.lang.String getRemark() {
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}	
}
