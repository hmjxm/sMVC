package com.inzyme.p2p.message.innermsg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 内部消息
 * @author Auto-generator
 * @date 2015-07-14 14:06:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_innermsg", schema = "")
@SuppressWarnings("serial")
public class InnermsgEntity extends IdEntity implements java.io.Serializable {
	/**标题*/
	private java.lang.String title;
	/**内容*/
	private java.lang.String content;
	/**发送时间*/
	private java.util.Date sendtime;
	/**发送人*/
	private java.lang.String sender;
	/**接收人*/
	private java.lang.String reciever;
	/**状态*/
	private java.lang.String status;
	/**邮箱*/
	private java.lang.String mailbox;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发送时间
	 */
	@Column(name ="SENDTIME",nullable=true,length=20)
	public java.util.Date getSendtime(){
		return this.sendtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发送时间
	 */
	public void setSendtime(java.util.Date sendtime){
		this.sendtime = sendtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发送人
	 */
	@Column(name ="SENDER",nullable=true,length=50)
	public java.lang.String getSender(){
		return this.sender;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发送人
	 */
	public void setSender(java.lang.String sender){
		this.sender = sender;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接收人
	 */
	@Column(name ="RECIEVER",nullable=true,length=50)
	public java.lang.String getReciever(){
		return this.reciever;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接收人
	 */
	public void setReciever(java.lang.String reciever){
		this.reciever = reciever;
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
	 *@return: java.lang.String  邮箱
	 */
	@Column(name ="MAILBOX",nullable=true,length=100)
	public java.lang.String getMailbox(){
		return this.mailbox;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setMailbox(java.lang.String mailbox){
		this.mailbox = mailbox;
	}
}
