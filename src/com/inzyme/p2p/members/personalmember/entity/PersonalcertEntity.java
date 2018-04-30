package com.inzyme.p2p.members.personalmember.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.inzy.framework.core.common.entity.IdEntity;

/**
 * 会员信息表 Provider chenhuagen
 */
@Entity
@Table(name = "p2p_t_personalcert")
public class PersonalcertEntity extends IdEntity implements java.io.Serializable {
	private String telno;
	private String telnocertstatus;
	private String idcardno;
	private String idcardnocertstatus;
	private String realname;
	private String email;
	private String emailcertstatus;
	private String remark;
	private String personalinfoid;
	
    @Column(name ="telno",nullable=true,length=11)
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	
	@Column(name ="telnocertstatus",nullable=true,length=11)
	public String getTelnocertstatus() {
		return telnocertstatus;
	}
	public void setTelnocertstatus(String telnocertstatus) {
		this.telnocertstatus = telnocertstatus;
	}
	
	@Column(name ="idcardno",nullable=true,length=20)
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	
	@Column(name ="idcardnocertstatus",nullable=true,length=11)
	public String getIdcardnocertstatus() {
		return idcardnocertstatus;
	}
	public void setIdcardnocertstatus(String idcardnocertstatus) {
		this.idcardnocertstatus = idcardnocertstatus;
	}
	
	@Column(name ="realname",nullable=true,length=50)
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Column(name ="email",nullable=true,length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name ="emailcertstatus",nullable=true,length=11)
	public String getEmailcertstatus() {
		return emailcertstatus;
	}
	public void setEmailcertstatus(String emailcertstatus) {
		this.emailcertstatus = emailcertstatus;
	}
	
	@Column(name ="remark",nullable=true,length=1000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name ="personalinfoid",nullable=true,length=32)
	public String getPersonalinfoid() {
		return personalinfoid;
	}
	public void setPersonalinfoid(String personalinfoid) {
		this.personalinfoid = personalinfoid;
	}
	
	
	
	
}