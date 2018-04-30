<#if packageStyle == "service">
package ${bussiPackage}.${entityPackage}.service;
<#else>
package ${bussiPackage}.service.${entityPackage};
</#if>

import org.inzy.framework.core.common.service.CommonService;

public interface ${entityName}ServiceI extends CommonService{

}
