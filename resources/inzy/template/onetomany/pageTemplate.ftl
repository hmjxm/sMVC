<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage}.page;
<#else>
package ${bussiPackage}.page.${entityPackage};
</#if>

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

<#if inzy_primary_key_policy == "default">
import org.inzy.framework.core.common.entity.IdEntity;
</#if>

<#if packageStyle == "service">
<#list subTab as sub>
import ${bussiPackage}.${sub.entityPackage}.entity.${sub.entityName}Entity;
</#list>
<#else>
<#list subTab as sub>
import ${bussiPackage}.entity.${sub.entityPackage}.${sub.entityName}Entity;
</#list>
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
@SuppressWarnings("serial")
<#if inzy_primary_key_policy == "default">
public class ${entityName}Page extends IdEntity implements java.io.Serializable {
<#else>
public class ${entityName}Page implements java.io.Serializable {
</#if>
	<#list subTab as sub>
	/**保存-${sub.ftlDescription}*/
	private List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List = new ArrayList<${sub.entityName}Entity>();
	public List<${sub.entityName}Entity> get${sub.entityName}List() {
		return ${sub.entityName?uncap_first}List;
	}
	public void set${sub.entityName}List(List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List) {
		this.${sub.entityName?uncap_first}List = ${sub.entityName?uncap_first}List;
	}
	</#list>


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
	@Column(name ="${po.fieldDbName}",nullable=<#if po.nullable == 'Y'>true<#else>false</#if><#if po.precision != ''>,precision=${po.precision}</#if><#if po.scale != ''>,scale=${po.scale}</#if><#if po.charmaxLength != ''&& po.charmaxLength?length lte 8 >,length=${po.charmaxLength}</#if>)
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
