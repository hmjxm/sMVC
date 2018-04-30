package org.inzy.framework.web.demo.entity.test;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: WebOffice例子
 * @author Goodman Zhang
 * @date 2013-07-08 10:54:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doc", schema = "")
@SuppressWarnings("serial")
public class WebOfficeEntity extends IdEntity implements java.io.Serializable {
	
	/**docid*/
	private java.lang.String docid;
	/**doctitle*/
	private java.lang.String doctitle;
	/**doctype*/
	private java.lang.String doctype;
	/**docdate*/
	private java.util.Date docdate;
	/**doccontent*/
	private Blob doccontent;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  docid
	 */
	@Column(name ="DOCID",nullable=true,length=255)
	public java.lang.String getDocid(){
		return this.docid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  docid
	 */
	public void setDocid(java.lang.String docid){
		this.docid = docid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  doctitle
	 */
	@Column(name ="DOCTITLE",nullable=true,length=255)
	public java.lang.String getDoctitle(){
		return this.doctitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  doctitle
	 */
	public void setDoctitle(java.lang.String doctitle){
		this.doctitle = doctitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  doctype
	 */
	@Column(name ="DOCTYPE",nullable=true,length=255)
	public java.lang.String getDoctype(){
		return this.doctype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  doctype
	 */
	public void setDoctype(java.lang.String doctype){
		this.doctype = doctype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  docdate
	 */
	@Column(name ="DOCDATE",nullable=true)
	public java.util.Date getDocdate(){
		return this.docdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  docdate
	 */
	public void setDocdate(java.util.Date docdate){
		this.docdate = docdate;
	}
	/**
	 *方法: 取得Blob
	 *@return: Blob  doccontent
	 */
	@Column(name ="DOCCONTENT",nullable=true)
	public Blob getDoccontent(){
		return this.doccontent;
	}

	/**
	 *方法: 设置Blob
	 *@param: Blob  doccontent
	 */
	public void setDoccontent(Blob doccontent){
		this.doccontent = doccontent;
	}
}
