<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>发送消息</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/message/innermsg/innermsg.js"></script>
<script type="text/javascript">
	//编写自定义JS代码
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="innermsgController.do?doAdd">
		<input id="id" name="id" type="hidden" value="${innermsg.id }">
		<input id="createUser" name="createUser" type="hidden"
			value="${innermsg.createUser }">
		<input id="createTime" name="createTime" type="hidden"
			value="${innermsg.createTime }">
		<input id="updateUser" name="updateUser" type="hidden"
			value="${innermsg.updateUser }">
		<input id="updateTime" name="updateTime" type="hidden"
			value="${innermsg.updateTime }">
		<div style="width:800px; height:450px; overflow:scroll;">
			<table style="width: 780px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<tr>
					<td align="right">
					    <label class="Validform_label">标题: </label>
					</td>
					<td class="value">
					    <input id="title" name="title" type="text" style="width: 200px" datatype="*" class="inputxt"></td>
                </tr>
                <tr>
					<td align="right">
					   <label class="Validform_label">接收人: </label>
					</td>
					<td class="value">
                       <input name="userName" class="inputxt" id="roleName" style="width: 200px" value="${sender }" readonly="readonly" />
					   <t:choose url="userController.do?selectUser" name="userList"
                          icon="icon-search" title="用户列表" textname="userName" isclear="true"></t:choose>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 正文:
					</label>
					</td>
					<td class="value">
					  <t:ckeditor name="content" isfinder="true" value="" type="width:670,height:400"></t:ckeditor>
				</tr>
			</table>
		</div>
	</t:formvalid>
</body>
</html>