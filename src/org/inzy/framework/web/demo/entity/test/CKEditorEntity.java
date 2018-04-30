package org.inzy.framework.web.demo.entity.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import org.inzy.framework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: HTML 编辑器
 * @author Goodman Zhang
 * @date 2013-07-08 16:19:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ck_editor", schema = "")
@SuppressWarnings("serial")
public class CKEditorEntity extends IdEntity implements java.io.Serializable {

	/**contents*/
	private byte[] contents;
	/**
	 *方法: 取得byte[]
	 *@return: byte[]  contents
	 */
	@Column(name ="CONTENTS",nullable=true,length=65535)
	public byte[] getContents(){
		return this.contents;
	}

	/**
	 *方法: 设置byte[]
	 *@param: byte[]  contents
	 */
	public void setContents(byte[] contents){
		this.contents = contents;
	}
}
