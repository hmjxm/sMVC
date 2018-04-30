<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账户交易记录</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/finance/transcation/transcation.js"></script>
<script type="text/javascript">
	function init() {
		var type = "${type}";
		if (type == "update") {
			//金额不允许修改
			$("#amount").attr("readonly", "readonly");
		}
	}
</script>
</head>
<body style="overflow-y: hidden" scroll="no" onclick="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="div" action="transcationController.do?doAdd" tiptype="1">
		<input id="id" name="id" type="hidden" value="${tran.id }">
		<input name="accountEntity.id" value="${accountId}" type="hidden">
		<input id="createUser" name="createUser" type="hidden"
			value="${tran.createUser }">
		<input id="updateUser" name="updateUser" type="hidden"
			value="${tran.updateUser }">
		<input id="createTime" name="createTime" type="hidden"
			value="${tran.createTime }">
		<input id="updateTime" name="updateTime" type="hidden"
			value="${tran.updateTime }">
		<fieldset class="step">
			<t:dictSelect field="title" typeGroupCode="trade" title="交易科目" defaultVal="${tran.title}"></t:dictSelect>
			<div class="form">
				<label class="Validform_label"> 借贷方向: </label> <select
					name="direction" id="direction">
					<option value="1"
						<c:if test="${tran.direction=='1'}">selected="selected"</c:if>>收入</option>
					<option value="2"
						<c:if test="${tran.direction=='2'}">selected="selected"</c:if>>支出</option>
				</select>
			</div>
			<div class="form">
				<label class="Validform_label"> 交易金额: </label><input name="amount"
					id="amount" class="inputxt"
					datatype="/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/"
					value="${tran.amount}"> <span class="Validform_checktip">
					必须为数字</span>
			</div>
			<input name="TSAccount.id" value="${accountId}" type="hidden">
			<div class="form">
				<label class="Validform_label"> 交易备注: </label>
				<textarea style="width: 450px;height: 100px;" name="remark" class="inputxt">${tran.remark}</textarea>
			</div>
		</fieldset>
	</t:formvalid>
</body>
</html>