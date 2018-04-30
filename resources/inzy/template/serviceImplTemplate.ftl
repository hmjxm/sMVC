<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage.service.impl};
import ${bussiPackage}.${entityPackage}.service.${entityName}ServiceI;
<#else>
package ${bussiPackage}.service.impl.${entityPackage};
import ${bussiPackage}.service.${entityPackage}.${entityName}ServiceI;
</#if>

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;

@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl extends CommonServiceImpl implements ${entityName}ServiceI {
	
}