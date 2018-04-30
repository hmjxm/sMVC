<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>投资标的种类</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="plug-in/jquery-plugs/fileupload/js/jquery.1.9.1.min.js"></script>
<script
	src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
<script
	src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
<script
	src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>



<script type="text/javascript">
	$(function() {
		$('#titleupload').fileupload({
			dataType : 'json',
			done : function(e, data) {
				$(".table1 tr:has(td)").remove();
				$.each(data.result, function(index, title) {
					$("#titleimg").val("upload/" + title.fileName);
				});
			},
		});
		$('#iconupload').fileupload({
			dataType : 'json',
			done : function(e, data) {
				$.each(data.result, function(index, icon) {
					$("#iconimg").val("upload/" + icon.fileName);
				});
			},
		})
	});
	
	//初始化控件
	function init() {
		var isopen = "${investbidtypePage.status}";
		$("input[name='status'][value='" + isopen + "']").attr("checked", true);
		var istrue = "${investbidtypePage.allowperson}";
		$("input[name='allowperson'][value='" + istrue + "']").attr("checked",
				true);
		var isfalse = "${investbidtypePage.allowcompany}";
		$("input[name='allowcompany'][value='" + isfalse + "']").attr(
				"checked", true);
	}
</script>

<style type="text/css">
.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 2px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
</head>
<body onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="div" action="investbidtypeController.do?saveInvest">
		<input id="id" name="id" type="hidden"
			value="${investbidtypePage.id }">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"> 投资标的种类: </label> <input
					name="bidtypename" validType="p2p_t_investbidtype,bidtypename,id"
					class="inputxt" value="${investbidtypePage.bidtypename}"
					datatype="s2-50"> <span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 图标路径: </label>


				<img alt="图标" src="${investbidtypePage.iconimg }" width="100px"
					height="100px">
				<div class="file">
					上传图片 <input id="iconupload" type="file"
						data-url="investbidtypeController.do?upload" accept="image/*">
				</div>
					 <input type="hidden" id="iconimg"
					name="iconimg" size="40" readonly="readonly"
					value="${investbidtypePage.iconimg }" class="inputxt" /> <span
					class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 标题路径: </label>

				<img alt="标题" src="${investbidtypePage.titleimg }" width="100px"
					height="100px"> 
				<div class="file">
					上传图片 <input id="titleupload" type="file"
						data-url="investbidtypeController.do?titleUpload" accept="image/*">
				</div>
					<input type="hidden" id="titleimg"
					name="titleimg" size="40" readonly="readonly"
					value="${investbidtypePage.titleimg }" class="inputxt" /> <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 允许个人投资: </label> <input type="radio"
					name="allowperson" value="Y" />是&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="allowperson" value="N" checked="checked" />否 <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 允许企业投资: </label> <input type="radio"
					name="allowcompany" value="Y" />是&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="allowcompany" value="N" checked="checked" />否 <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> Bean名称: </label> <input
					id="checkbean" name="checkbean" type="text" class="inputxt"
					datatype="s2-15" nullmsg="请输入bean类型名称"
					value='${investbidtypePage.checkbean}'> <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 是否启用: </label> <input type="radio"
					name="status" value="1" />是&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="status" value="0" checked="checked" />否<span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 描述: </label>
				<textarea rows="15" cols="80" name="description" id="description">${investbidtypePage.description}</textarea>
				<span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 备注: </label>
				<textarea rows="3" cols="80" name="remark" id="remark">${investbidtypePage.remark}</textarea>
				<span class="Validform_checktip"></span>
			</div>
		</fieldset>
	</t:formvalid>
</body>
<script
	src="webpage/com/inzyme/p2p/projects/projectcheck/p2pInvestbidtype.js"></script>