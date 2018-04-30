package com.inzyme.p2p.advert.advert.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 广告表
 * @author Auto-generator
 * @date 2015-07-13 15:24:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_advert", schema = "")
@SuppressWarnings("serial")
public class AdvertEntity extends IdEntity implements java.io.Serializable {
	/**模块编码*/
	private java.lang.String spacecode;
	/**广告类型*/
	private java.lang.String adttype;
	/**广告名称*/
	private java.lang.String adtname;
	/**模版Id*/
	private java.lang.String templateid;
	/**内容*/
	private java.lang.String content;
	/**图片地址*/
	private java.lang.String imgurl;
	/**链接地址*/
	private java.lang.String linkurl;
	/**生效日期*/
	private java.util.Date effectivedate;
	/**状态*/
	private java.lang.String status;
	/**备注*/
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模块编码
	 */
	@Column(name ="SPACECODE",nullable=true,length=50)
	public java.lang.String getSpacecode(){
		return this.spacecode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块编码
	 */
	public void setSpacecode(java.lang.String spacecode){
		this.spacecode = spacecode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  广告类型
	 */
	@Column(name ="ADTTYPE",nullable=true,length=2)
	public java.lang.String getAdttype(){
		return this.adttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  广告类型
	 */
	public void setAdttype(java.lang.String adttype){
		this.adttype = adttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  广告名称
	 */
	@Column(name ="ADTNAME",nullable=true,length=50)
	public java.lang.String getAdtname(){
		return this.adtname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  广告名称
	 */
	public void setAdtname(java.lang.String adtname){
		this.adtname = adtname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模版Id
	 */
	@Column(name ="TEMPLATEID",nullable=true,length=32)
	public java.lang.String getTemplateid(){
		return this.templateid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模版Id
	 */
	public void setTemplateid(java.lang.String templateid){
		this.templateid = templateid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内容
	 */
	@Column(name ="CONTENT",nullable=true,length=2000)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片地址
	 */
	@Column(name ="IMGURL",nullable=true,length=100)
	public java.lang.String getImgurl(){
		return this.imgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片地址
	 */
	public void setImgurl(java.lang.String imgurl){
		this.imgurl = imgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  链接地址
	 */
	@Column(name ="LINKURL",nullable=true,length=100)
	public java.lang.String getLinkurl(){
		return this.linkurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  链接地址
	 */
	public void setLinkurl(java.lang.String linkurl){
		this.linkurl = linkurl;
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
