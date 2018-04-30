package com.inzyme.p2p.hmjlogin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users", schema = "")
@SuppressWarnings("serial")
public class UserEntity implements java.io.Serializable{
	private java.lang.String uname;
	private java.lang.String pwd;
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name ="UNAME",nullable=false,length=32)
	public java.lang.String getUname(){
		return this.uname;
	}

	public void setUname(java.lang.String uname){
		this.uname = uname;
	}
	
	@Column(name ="PWD",nullable=false,length=32)
	public java.lang.String getPwd(){
		return this.pwd;
	}

	public void setPwd(java.lang.String pwd){
		this.pwd = pwd;
	}
}
