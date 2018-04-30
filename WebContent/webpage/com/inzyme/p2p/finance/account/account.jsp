<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>帐户表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/finance/account/account.js"></script>
<script type="text/javascript">
//初始化
function init(){
    var type = "${type}";
	if(type == "update"){//readonly="readonly" 
	    //账户、金额不允许修改
		$("#accountno").attr("readonly","readonly");
		$("#balance").attr("readonly","readonly");
	 }
}
</script>
</head>
<body style="overflow-y: hidden" scroll="no" onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="div" action="accountController.do?doAdd" tiptype="1">
		<input id="id" name="id" type="hidden" value="${account.id }">
		<input id="createUser" name="createUser" type="hidden"
			value="${account.createUser }">
		<input id="createTime" name="createTime" type="hidden"
			value="${account.createTime }">
		<input id="updateUser" name="updateUser" type="hidden"
			value="${account.updateUser }">
		<input id="updateTime" name="updateTime" type="hidden"
			value="${account.updateTime }">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"> 账户: </label> <input name="accountno"
					datatype="s2-10" id="accountno" value="${account.accountno}"
					class="inputxt"> <span class="Validform_checktip">名称范围2~10位字符,且不为空</span>
			</div>
			<div class="form">
				<label class="Validform_label"> 账户名称: </label> <input
					name="accountname" id="accountname" value="${account.accountname}"
					datatype="s2-10" class="inputxt"> <span
					class="Validform_checktip">名称范围2~10位字符,且不为空</span>
			</div>
			<div class="form">
				<label class="Validform_label"> 户主: </label> <input name="holderid"
					id="holderid" datatype="s2-10" value="${account.holderid}"
					class="inputxt"> <span class="Validform_checktip">名称范围2~10位字符,且不为空</span>
			</div>
			<div class="form">
				<label class="Validform_label"> 账户类型: </label> 
				 <select
					name="accounttype" id="accounttype">
					<option value="1"
						<c:if test="${account.accounttype=='1'}">selected="selected"</c:if>>储蓄账户</option>
					<option value="2"
						<c:if test="${account.accounttype=='2'}">selected="selected"</c:if>>虚拟账户</option>
						<option value="2"
						<c:if test="${account.accounttype=='3'}">selected="selected"</c:if>>平台账户</option>
				</select>
			</div>
			<div class="form">
				<label class="Validform_label"> 账户余额: </label> <input name="balance"
					id="balance" datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/"
					value="${account.balance}" class="inputxt"><span
					class="Validform_checktip">只能为数字</span>
			</div>
			<div class="form">
				<label class="Validform_label"> 备注: </label>
				<textarea style="width: 450px;height: 100px;" name="remark" class="inputxt">${account.remark}</textarea>
			</div>
		</fieldset>
	</t:formvalid>
</body>
</html>