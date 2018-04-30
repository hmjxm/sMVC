package org.inzy.framework.web.demo.entity.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.inzy.framework.poi.excel.annotation.Excel;

import javax.persistence.SequenceGenerator;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 课程老师
 * @author jueyue
 * @date 2013-08-31 22:52:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "inzy_demo_teacher", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TeacherEntity extends IdEntity implements java.io.Serializable {

	/**name*/
	@Excel(name="老师姓名",orderNum="2",needMerge=true)
	private java.lang.String name;
	
	@Excel(name="老师照片",orderNum="3",type=2,height=15,width=20)
	private java.lang.String pic;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=12)
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

	public java.lang.String getPic() {
//		if(StringUtils.isEmpty(pic)){
//			pic = "plug-in/login/images/inzy.png";
//		}
		return pic;
	}

	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
}
