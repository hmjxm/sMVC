<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage}.entity;
<#else>
package ${bussiPackage}.entity.${entityPackage};
</#if>

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

<#if inzy_primary_key_policy == "default">
import org.inzy.framework.core.common.entity.IdEntity;
</#if>

/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author Auto-generator
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Entity
@Table(name = "${tableName}", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
<#if inzy_primary_key_policy == "default">
public class ${entityName}Entity extends IdEntity implements java.io.Serializable {
<#else>
public class ${entityName}Entity implements java.io.Serializable {
</#if>
	<#list originalColumns as po>
	<#if inzy_primary_key_policy != "default" || po.fieldName != inzy_table_id>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#if>
	</#list>
	
	<#list originalColumns as po>
	<#if inzy_primary_key_policy != "default" || po.fieldName != inzy_table_id>
	/**
	 *方法: 取得${po.fieldType}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	<#if po.fieldName == inzy_table_id>
	
	<#if inzy_primary_key_policy == 'uuid'>
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	</#if>
	<#if inzy_primary_key_policy == 'identity'>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	<#if inzy_primary_key_policy == 'sequence'>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sequence")
	@SequenceGenerator(name="sequence",sequenceName="${inzy_sequence_code}",allocationSize=1)
	</#if>
	</#if>
	@Column(name ="${po.fieldDbName}",nullable=<#if po.nullable == 'Y'>true<#else>false</#if><#if po.precision != ''>,precision=${po.precision}</#if><#if po.scale != ''>,scale=${po.scale}</#if><#if po.charmaxLength != ''>,length=${po.charmaxLength}</#if>)
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.fieldType}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#if>
	</#list>
}
