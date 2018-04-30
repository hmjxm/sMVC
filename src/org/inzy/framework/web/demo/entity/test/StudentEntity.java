package org.inzy.framework.web.demo.entity.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;

import javax.persistence.SequenceGenerator;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 学生
 * @author jueyue
 * @date 2013-08-31 22:53:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "inzy_demo_student", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class StudentEntity extends IdEntity implements java.io.Serializable {
	
	/**学生姓名*/
	@Excel(name="学生姓名")
	private java.lang.String name;
	/**学生性别*/
	@Excel(name="学生性别",replace = {"女生_1","男生_0"})
	private java.lang.String sex;
	
	@Excel(name="出生日期",exportFormat="yyyy-MM-dd HH:mm:ss",importFormat="yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	/**课程主键*/
	private CourseEntity course;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学生姓名
	 */
	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学生姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学生性别
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学生性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}
	
	@Column(name ="BIRTHDAY",nullable=true)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
