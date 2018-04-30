package org.inzy.framework.web.demo.entity.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.SequenceGenerator;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 乐观锁测试
 * @author Goodman Zhang
 * @date 2013-06-24 14:46:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "OPTIMISTIC_LOCKING", schema = "")
@SuppressWarnings("serial")
public class OptimisticLockingEntity extends IdEntity implements java.io.Serializable {

	/**name*/
	private java.lang.String name;
	/**age*/
	private java.lang.Integer age;
	/**account*/
	private java.lang.Integer account;
	/**ver*/
	private java.lang.Integer ver;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=85)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  age
	 */
	@Column(name ="AGE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  age
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  account
	 */
	@Column(name ="ACCOUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAccount(){
		return this.account;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  account
	 */
	public void setAccount(java.lang.Integer account){
		this.account = account;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  ver
	 */
	 @Version
	 @Column(name="VER")
	public java.lang.Integer getVer(){
		return this.ver;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  ver
	 */
	public void setVer(java.lang.Integer ver){
		this.ver = ver;
	}
}
