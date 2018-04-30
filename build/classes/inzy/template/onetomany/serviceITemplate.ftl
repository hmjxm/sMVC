<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage}.service;

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
<#list subTab as sub>
import ${bussiPackage}.${sub.entityPackage}.entity.${sub.entityName}Entity;
<#else>
package ${bussiPackage}.service.${entityPackage};

import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
<#list subTab as sub>
import ${bussiPackage}.entity.${sub.entityPackage}.${sub.entityName}Entity;
</#if>

import java.util.List;
import org.inzy.framework.core.common.service.CommonService;

public interface ${entityName}ServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(${entityName}Entity ${entityName?uncap_first},
	        <#list subTab as sub>List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(${entityName}Entity ${entityName?uncap_first},
	        <#list subTab as sub>List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>);
	public void delMain (${entityName}Entity ${entityName?uncap_first});
}
