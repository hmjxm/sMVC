package com.inzyme.p2p.members.personalmember.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;
import org.inzy.framework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 会员表
 * @author onlineGenerator
 * @date 2015-07-02 15:59:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_member", schema = "")
@Inheritance(strategy=InheritanceType.JOINED)
@SuppressWarnings("serial")
public class MemberEntity extends IdEntity implements java.io.Serializable {
	/**登录账号*/
	@Excel(name="登录账号")
	private java.lang.String loginname;
	/**登录密码*/
	@Excel(name="登录密码")
	private java.lang.String password;
	/**头像URL*/
	private java.lang.String headimg;
	/**是否允许第三方登录*/
	@Excel(name="是否允许第三方登录")
	private java.lang.String isthirdlogin;
	/**第三方登录站点*/
	@Excel(name="第三方登录站点")
	private java.lang.String thirdloginsite;
	/**第三方登录账号*/
	@Excel(name="第三方登录账号")
	private java.lang.String thirdloginname;
	/**上次登录时间*/
	@Excel(name="上次登录时间")
	private java.util.Date lastlogintime;
	/**上次登录IP*/
	@Excel(name="上次登录IP")
	private java.lang.String lastloginip;
	/**会员账号状态*/
	@Excel(name="会员账号状态")
	private java.lang.String status;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录账号
	 */
	@Column(name ="LOGINNAME",nullable=true,length=20)
	public java.lang.String getLoginname(){
		return this.loginname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录账号
	 */
	public void setLoginname(java.lang.String loginname){
		this.loginname = loginname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录密码
	 */
	@Column(name ="PASSWORD",nullable=true,length=20)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像URL
	 */
	@Column(name ="HEADIMG",nullable=true,length=100)
	public java.lang.String getHeadimg(){
		return this.headimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像URL
	 */
	public void setHeadimg(java.lang.String headimg){
		this.headimg = headimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否允许第三方登录
	 */
	@Column(name ="ISTHIRDLOGIN",nullable=true,length=5)
	public java.lang.String getIsthirdlogin(){
		return this.isthirdlogin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否允许第三方登录
	 */
	public void setIsthirdlogin(java.lang.String isthirdlogin){
		this.isthirdlogin = isthirdlogin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第三方登录站点
	 */
	@Column(name ="THIRDLOGINSITE",nullable=true,length=20)
	public java.lang.String getThirdloginsite(){
		return this.thirdloginsite;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方登录站点
	 */
	public void setThirdloginsite(java.lang.String thirdloginsite){
		this.thirdloginsite = thirdloginsite;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第三方登录账号
	 */
	@Column(name ="THIRDLOGINNAME",nullable=true,length=20)
	public java.lang.String getThirdloginname(){
		return this.thirdloginname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方登录账号
	 */
	public void setThirdloginname(java.lang.String thirdloginname){
		this.thirdloginname = thirdloginname;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上次登录时间
	 */
	@Column(name ="LASTLOGINTIME",nullable=true,length=30)
	public java.util.Date getLastlogintime(){
		return this.lastlogintime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上次登录时间
	 */
	public void setLastlogintime(java.util.Date lastlogintime){
		this.lastlogintime = lastlogintime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上次登录IP
	 */
	@Column(name ="LASTLOGINIP",nullable=true,length=30)
	public java.lang.String getLastloginip(){
		return this.lastloginip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上次登录IP
	 */
	public void setLastloginip(java.lang.String lastloginip){
		this.lastloginip = lastloginip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员账号状态
	 */
	@Column(name ="STATUS",nullable=true,length=5)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员账号状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
