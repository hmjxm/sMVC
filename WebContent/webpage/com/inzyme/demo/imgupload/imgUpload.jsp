<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>上传下载示例</title>
<t:base type="jquery,easyui,tools"></t:base>

<script type="text/javascript">

$(document).ready(function() {

	$("#previewImgWindow").window({
		//title: "图片预览",
		//width: 400,
		//height: 400,
		closed : true
	});
});

// 弹出页面
function previewImgWindowClick(imgUrl, imgTitle, imgWidth, imgHeight) {

	alert(imgUrl + "..." + imgWidth + "..." + imgHeight);
	var url = "imgUploadController.do?previewImg&filepath=" + imgUrl
			+ "&imgWidth=" + imgWidth + "&imgHeight=" + imgHeight;
	
	var iWidth = imgWidth;
	var iHeight = imgHeight;
	
	if (iWidth > 600) {
		iHeight = iHeight * 600 / iWidth;
		iWidth = 600;
	}
		
	$("#previewImgWindow").window({
		title : "【" + imgTitle + "】照片预览",
		top : 0,
		left : 0,
		width : iWidth + 20,
		height : iHeight + 42
	}).window("open").window("refresh", url);
} 

//上传文件
function uploadFile(data) {

	/*
	var msg = data.msg;
	alert(msg);
	var flag = msg.substr(0, 2);
	if (flag == "1;") {
		alert(msg.substr(2));
	} else {
		alert(msg);
	} */

	alert(data.msg);
	$("#id").val(data.obj.id);
	alert(data.obj.id);
	if ($("#uploadfile").children().length > 0) {
		alert(1);
		upload();
	} else {
		alert(2);
		frameElement.api.opener.reloadTable();
		setTimeout(frameElement.api.close(), 1000);
	}
}

</script>

</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" beforeSubmit="upload">
		<input id="id" name="id" type="hidden" value="${imgUploadPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="120px"> <label class="Validform_label">温馨提示:</label></td>
				<td class="value" width="480px" colspan="2">目前本系统，List页面要引入“[t:base]”标签，否则列表不显示！</td>
			</tr>
			<tr>
				<td align="right" width="120px"><label class="Validform_label">
						<label style="color: red">*</label>用户名:
				</label></td>
				<td class="value" width="480px" colspan="2"><input
					class="inputxt" id="userName" name="userName"
					value="${imgUploadPage.userName}" datatype="*" nullmsg="请输入用户名">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 性别: </label></td>
				<td class="value" colspan="2"><t:dictSelect id="sex"
						field="sex" typeGroupCode="sex" defaultVal="${imgUploadPage.sex}"
						hasLabel="false" style="width: 155px"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 年龄: </label></td>
				<td class="value" colspan="2"><input class="inputxt" id="age" name="age"
					value="${imgUploadPage.age}" datatype="n" errormsg="年龄格式不正确"
					ignore="ignore"> <span class="Validform_checktip"></span></td>
			</tr>
			<c:if test="${functype != '3'}">
				<tr>
					<td align="right"><label class="Validform_label">
							照片上传：</label></td>
					<td class="value">
						<div class="form">
							<t:upload name="imgUrl" id="imgUrl" buttonText="上传文件"
								uploader="imgUploadController.do?uploadFile" extend="pic"
								formData="id,userName,sex,age" queueID="uploadfile"></t:upload>
						</div>
						<div class="form" id="filediv" style="height: 5px"></div>
					</td>
					<td id="uploadfile" class="value"></td>
				</tr>
			</c:if>
			<c:if test="${functype != '1'}">
				<tr height="230px">
					<td align="right"><label class="Validform_label">
							照片预览：</label></td>
					<td class="value" align="left" valign="top" colspan="2">
						<div style="width: 90px; margin: 0 auto; position: absolute;">
							<img src=".${imgUploadPage.imgUrl}" width="${iWidth}" height="${iHeight}"
								onclick="previewImgWindowClick('${imgUploadPage.imgUrl}', '${imgUploadPage.userName}', ${imgUploadPage.imgWidth}, ${imgUploadPage.imgHeight})" />
						</div>
					</td>
				</tr>
			</c:if>
			<div id="previewImgWindow"></div>
		</table>
	</t:formvalid>
</body>