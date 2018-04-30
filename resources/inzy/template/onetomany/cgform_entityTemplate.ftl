<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage}.entity;
<#else>
package ${bussiPackage}.entity.${entityPackage};
</#if>
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

<#if cgformConfig.cgFormHead.jformPkType?if_exists?html == "DEFAULT">
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
<#if cgformConfig.cgFormHead.jformPkType?if_exists?html == "SEQUENCE">
@SequenceGenerator(name="SEQ_GEN", sequenceName="${cgformConfig.cgFormHead.jformPkSequence}")  
</#if>
@SuppressWarnings("serial")
<#if cgformConfig.cgFormHead.jformPkType?if_exists?html == "DEFAULT">
public class ${entityName}Entity extends IdEntity implements java.io.Serializable {
<#else>
public class ${entityName}Entity implements java.io.Serializable {
</#if>
	<#list columns as po>
	<#if cgformConfig.cgFormHead.jformPkType?if_exists?html != "DEFAULT" || po.fieldName != inzy_table_id>
	/**${po.content}*/
	<#if po.type == "javax.xml.soap.Text">
	private java.lang.String ${po.fieldName};
	</#if>
	<#if po.type != "javax.xml.soap.Text">
	private ${po.type} ${po.fieldName};
	</#if>
	</#if>
	</#list>
	
	<#list columns as po>
	<#if cgformConfig.cgFormHead.jformPkType?if_exists?html != "DEFAULT" || po.fieldName != inzy_table_id>
	/**
	 *方法: 取得${po.type}
	 *@return: ${po.type}  ${po.content}
	 */
	<#if po.fieldName == inzy_table_id>
	<#if cgformConfig.cgFormHead.jformPkType?if_exists?html == "UUID">
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	<#elseif cgformConfig.cgFormHead.jformPkType?if_exists?html == "NATIVE">
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	<#elseif cgformConfig.cgFormHead.jformPkType?if_exists?html == "SEQUENCE">
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	<#else>
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	</#if>
	</#if>
	
	<#if po.type == "javax.xml.soap.Text">
	@Column(name ="${fieldMeta[po.fieldName]}",nullable=<#if po.isNull == 'Y'>true<#else>false</#if><#if po.pointLength != 0>,scale=${po.pointLength}</#if>,length=1000)
	public java.lang.String get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ${po.content}
	 */
	public void set${po.fieldName?cap_first}(java.lang.String ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#if>
	<#if po.type != "javax.xml.soap.Text">
	@Column(name ="${fieldMeta[po.fieldName]}",nullable=<#if po.isNull == 'Y'>true<#else>false</#if><#if po.pointLength != 0>,scale=${po.pointLength}</#if><#if po.length !=0>,length=${po.length?c}</#if>)
	public ${po.type} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.type}
	 *@param: ${po.type}  ${po.content}
	 */
	public void set${po.fieldName?cap_first}(${po.type} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#if>
	
	</#if>
	</#list>
}
