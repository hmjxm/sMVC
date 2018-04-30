<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>查看消息</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
<script type="text/javascript">
	function init(){
		CKEDITOR.config.readOnly = true;
	}
	function sendMsg(){
		var sender = $("#sender").val();
		createwindow("发送消息", "innermsgController.do?goAdd&sender="+sender, 800, 500);
		frameElement.api.close();
	}
	
</script>
</head>
<body onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table">
		<div style="width:800px; height:450px; overflow:scroll;">
			<table style="width: 780px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<tr>
					<td align="right">
					    <label class="Validform_label">标题: </label>
					</td>
					<td class="value">
					    <input id="title" name="title" type="text" style="width: 200px" readonly="readonly" value="${innermsg.title} " class="inputxt"></td>
                </tr>
                <tr>
					<td align="right">
					   <label class="Validform_label">发送人: </label>
					</td>
					<td class="value">
                       <input value="${innermsg.sender} " id="sender" class="inputxt" style="width: 200px" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 正文:
					</label>
					</td>
					<td class="value">
					  <t:ckeditor name="content" isfinder="true" value="${innermsg.content} " type="width:670,height:400"></t:ckeditor>
				</tr>
			</table>
			
		</div>
		<div class="ui_main">
		       <div class="ui_buttons">
		           <input type="button" value="回复" onclick="sendMsg();" class="ui_state_highlight">
		           <input type="button" value="关闭" onclick="frameElement.api.close();">
		       </div>
        </div>
	</t:formvalid>
</body>
</html>