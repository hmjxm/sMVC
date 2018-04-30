package com.inzyme.p2p.app.issueapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: APP发布表
 * @author Auto-generator
 * @date 2015-07-15 14:39:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_appissue", schema = "")
@SuppressWarnings("serial")
public class AppissueEntity extends IdEntity implements java.io.Serializable {
	/**App编码*/
	private java.lang.String appcode;
	/**App中文名*/
	private java.lang.String appname;
	/**App支持系统*/
	private java.lang.String apptype;
	/**版本*/
	private java.lang.String issueversion;
	/**图标*/
	private java.lang.String iconimg;
	/**下载路径*/
	private java.lang.String downloadurl;
	/**生效日期*/
	private java.util.Date effectivedate;
	/**状态*/
	private java.lang.String status;
	/**备注*/
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  App编码
	 */
	@Column(name ="APPCODE",nullable=true,length=50)
	public java.lang.String getAppcode(){
		return this.appcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  App编码
	 */
	public void setAppcode(java.lang.String appcode){
		this.appcode = appcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  App中文名
	 */
	@Column(name ="APPNAME",nullable=true,length=50)
	public java.lang.String getAppname(){
		return this.appname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  App中文名
	 */
	public void setAppname(java.lang.String appname){
		this.appname = appname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  App支持系统
	 */
	@Column(name ="APPTYPE",nullable=true,length=2)
	public java.lang.String getApptype(){
		return this.apptype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  App支持系统
	 */
	public void setApptype(java.lang.String apptype){
		this.apptype = apptype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本
	 */
	@Column(name ="ISSUEVERSION",nullable=true,length=10)
	public java.lang.String getIssueversion(){
		return this.issueversion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setIssueversion(java.lang.String issueversion){
		this.issueversion = issueversion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标
	 */
	@Column(name ="ICONIMG",nullable=true,length=50)
	public java.lang.String getIconimg(){
		return this.iconimg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标
	 */
	public void setIconimg(java.lang.String iconimg){
		this.iconimg = iconimg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下载路径
	 */
	@Column(name ="DOWNLOADURL",nullable=true,length=100)
	public java.lang.String getDownloadurl(){
		return this.downloadurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下载路径
	 */
	public void setDownloadurl(java.lang.String downloadurl){
		this.downloadurl = downloadurl;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日期
	 */
	@Column(name ="EFFECTIVEDATE",nullable=true,length=20)
	public java.util.Date getEffectivedate(){
		return this.effectivedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日期
	 */
	public void setEffectivedate(java.util.Date effectivedate){
		this.effectivedate = effectivedate;
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
