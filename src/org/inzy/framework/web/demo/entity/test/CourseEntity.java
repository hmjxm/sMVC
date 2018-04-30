package org.inzy.framework.web.demo.entity.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;
import org.inzy.framework.poi.excel.annotation.ExcelCollection;
import org.inzy.framework.poi.excel.annotation.ExcelEntity;
import org.inzy.framework.poi.excel.annotation.ExcelTarget;


import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 课程
 * @author jueyue
 * @date 2013-08-31 22:53:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "inzy_demo_course", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("courseEntity")
public class CourseEntity extends IdEntity implements java.io.Serializable {
	
	/**课程名称*/
	@Excel(name="课程名称",orderNum="1",needMerge=true)
	private java.lang.String name;
	/**老师主键*/
	@ExcelEntity()
	private TeacherEntity teacher;
	
	@ExcelCollection(name="选课学生",orderNum="4")
	private List<StudentEntity> students;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  课程名称
	 */
	@Column(name ="NAME",nullable=true,length=25)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  课程名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  老师主键
	 */
	@ManyToOne(cascade=CascadeType.REMOVE)
	public TeacherEntity getTeacher() {
		return teacher;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  老师主键
	 */
	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	
	@OneToMany(mappedBy="course",cascade=CascadeType.REMOVE)
	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}
}
