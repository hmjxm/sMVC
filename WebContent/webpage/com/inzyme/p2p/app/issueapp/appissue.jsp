<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>APP发布表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/app/issueapp/appissue.js"></script>
<script type="text/javascript">
//初始化控件
function init(){
	
   var status = "${appissue.status}";
   $("input[name='status'][value='" + status + "']").attr("checked", true);
   
   var appCodeSelect = $("#appCode").val();
   //初始化app英文名
   getType(appCodeSelect,"appcode","appEn");
   
   var appTypeSelect = $("#appType").val();
   getType(appTypeSelect,"apptype","appType");
   
}
</script>
</head>
<body onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="appissueController.do?doAdd" tiptype="1">
		<input id="id" name="id" type="hidden" value="${appissue.id }">
		<input id="appCode" name="appCode" type="hidden" value="${appissue.appcode }">
		<input id="appType" name="appType" type="hidden" value="${appissue.apptype }">
		<table style="width: 680px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right">
				    <label class="Validform_label"> App英文名: </label>
				</td>
				<td class="value">
				    <select name="appcode" id="appcode" style="width: 206px" onchange="showName()">
				       <option value="">---请选择---</option>
				    </select>
				</td>
				
				<td align="right">
				    <label class="Validform_label"> App中文名: </label>
				</td>
				<td class="value">
				    <input id="appname" name="appname" type="text" style="width: 200px" readonly="readonly" class="inputxt" value="${appissue.appname}"> 
				</td>
			</tr>
			<tr>
			    <td align="right">
			       <label class="Validform_label">App种类: </label>
				</td>
				<td class="value">
				   <select name="apptype" id="apptype" style="width: 206px">
				       <option value="">---请选择---</option>
				   </select>
				</td>
			    <td align="right">
			       <label class="Validform_label"> 生效日期:</label>
				</td>
				<td class="value">
				   <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 203px" id="effectivedate" name="effectivedate"
			        value="<fmt:formatDate value='${appissue.effectivedate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"> 
				</td>
			</tr>
			<tr>
			    <td align="right"><label class="Validform_label"> 图标: </label> </td>
				<td class="value">
				   <input id="iconimg" name="iconimg" type="text" style="width: 200px" class="inputxt" value="${appissue.iconimg}"> 
				</td>
				
				<td align="right"><label class="Validform_label"> 下载路径: </label> </td>
				<td class="value">
				   <input id="downloadurl" name="downloadurl" type="text" style="width: 200px" class="inputxt" value="${appissue.downloadurl}"> 
				</td>
			</tr>
			<tr>
			    <td align="right"><label class="Validform_label"> 版本: </label> </td>
				<td class="value">
				   <input id="issueversion" name="issueversion" type="text" style="width: 200px" class="inputxt" value="${appissue.issueversion}"> 
				</td>
				
				<td align="right"><label class="Validform_label"> 状态: </label></td>
				<td class="value">	
					<input type="radio" name="status" value="0" checked="checked" />启用&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="status" value="1" />禁用
				</td>
			</tr>
			<tr>
			    <td align="right"><label class="Validform_label"> 备注:</label></td>
				<td class="value" colspan="3">
				    <textarea style="width: 550px;height: 100px;" name="remark" class="inputxt">${appissue.remark}</textarea>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>