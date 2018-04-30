SELECT * FROM inzy_minidao WHERE 1=1
<#if inzyMinidao.userName ?exists && inzyMinidao.userName ?length gt 0>
	and user_name = :inzyMinidao.userName
</#if>
<#if inzyMinidao.mobilePhone ?exists && inzyMinidao.mobilePhone ?length gt 0>
	and mobile_phone = :inzyMinidao.mobilePhone
</#if>
<#if inzyMinidao.officePhone ?exists && inzyMinidao.officePhone ?length gt 0>
	and office_phone = :inzyMinidao.officePhone
</#if>
<#if inzyMinidao.email ?exists && inzyMinidao.email ?length gt 0>
	and email = :inzyMinidao.email
</#if>
<#if inzyMinidao.age ?exists && inzyMinidao.age ?length gt 0>
	and age = :inzyMinidao.age
</#if>
<#if inzyMinidao.salary ?exists && inzyMinidao.salary ?length gt 0>
	and salary = :inzyMinidao.salary
</#if>
<#if inzyMinidao.sex ?exists && inzyMinidao.sex ?length gt 0>
	and sex = :inzyMinidao.sex
</#if>
<#if inzyMinidao.status ?exists && inzyMinidao.status ?length gt 0>
	and status = :inzyMinidao.status
</#if>
