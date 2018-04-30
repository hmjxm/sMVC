<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>新闻栏目</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/news/catalog/newscatelog.js"></script>
<script type="text/javascript">
	//初始化控件
	function init() {
		var isopen = "${newscatelog.isopen}";
		$("input[name='isopen'][value='" + isopen + "']").attr("checked", true);
	}
</script>
</head>
<body style="overflow-x: hidden;" onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="div" action="newscatelogController.do?saveCatelog">
		<input id="id" name="id" type="hidden" value="${newscatelog.id }">
		<input id="createName" name="createName" type="hidden"
			value="${newscatelog.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${newscatelog.createBy }">
		<input id="createDate" name="createDate" type="hidden"
			value="${newscatelog.createDate }">
		<input id="updateName" name="updateName" type="hidden"
			value="${newscatelog.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${newscatelog.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${newscatelog.updateDate }">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label">栏目代号:</label> 
				<input id="catelogcode" name="catelogcode" datatype="s2-10" type="text" style="width: 150px" class="inputxt"
					value="${newscatelog.catelogcode}"> 
				<span class="Validform_checktip">名称范围2~10位字符,且不为空</span>
			</div>
			<t:dictSelect field="catelogtype" typeGroupCode="catType" defaultVal="${newscatelog.catelogtype}"></t:dictSelect>
			<div class="form">
				<label class="Validform_label">标题:</label> 
				<input id="title" name="title" type="text" datatype="s1-10" style="width: 150px" class="inputxt" value="${newscatelog.title}"> 
				<span class="Validform_checktip">名称范围1~10位字符,且不为空</span>
			</div>
			<div class="form">
				<label class="Validform_label">上级栏目:</label>
				<t:comboTree url="newscatelogController/getCatelogTree.do" name="newscatelogEntity.id" 
				id="cateId" width="155" value="${newscatelog.newscatelogEntity.id }" multiple="false"></t:comboTree>
			</div>

			<div class="form">
				<label class="Validform_label">栏目地址:</label> 
				<input id="catelogurl" name="catelogurl" datatype="*" type="text" style="width: 150px" class="inputxt" value="${newscatelog.catelogurl}"> 
				<span class="Validform_checktip">不为空</span>
			</div>
			<div class="form">
				<label class="Validform_label">摘要:</label> 
				<input id="abstracts" name="abstracts" type="text" style="width: 150px" class="inputxt" value="${newscatelog.abstracts}">
			</div>
			
			<div class="form">
				<label class="Validform_label">是否启用:</label> 
				<input type="radio" name="isopen" value="0" />是&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="radio" name="isopen" value="1" checked="checked" />否
			</div>
			
			<div class="form">
				<label class="Validform_label">描述:</label>
				<textarea rows="3" cols="5" name="description" class="inputxt">${newscatelog.description}</textarea>
			</div>
			
		</fieldset>
	</t:formvalid>
</body>
</html>