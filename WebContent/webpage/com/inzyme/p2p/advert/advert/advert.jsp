<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>广告表</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/advert/advert/advert.js"></script>
<script type="text/javascript">
//初始化控件
function init(){
   var status = "${advert.status}";
   $("input[name='status'][value='" + status + "']").attr("checked", true);
	
   var adttypeSelect = $("#adtType").val();
   var templateidSelect = $("#templateId").val();
   //初始化广告类型
   getType(adttypeSelect,"adttype","adtType");
   //初始化模版类型
   getType(templateidSelect,"templateid","smsTplType");
}
</script>
</head>
<body onload="init()">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="advertController.do?doSave" tiptype="1">
		<input id="id" name="id" type="hidden" value="${advert.id }">
		<input id="createUser" name="createUser" type="hidden"
			value="${advert.createUser }">
		<input id="updateUser" name="updateUser" type="hidden"
			value="${advert.updateUser }">
		<input id="createTime" name="createTime" type="hidden"
			value="${advert.createTime }">
		<input id="updateTime" name="updateTime" type="hidden"
			value="${advert.updateTime }">
		<input id="adtType" name="adtType" type="hidden"
			value="${advert.adttype }">
		<input id="templateId" name="templateId" type="hidden"
			value="${advert.templateid }">
		<table style="width: 780px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right"><label class="Validform_label"> 广告名称: </label></td>
				<td class="value"><input id="adtname" name="adtname"
					type="text" style="width: 200px" class="inputxt" value='${advert.adtname}'> 
				</td>
				<td align="right"><label class="Validform_label"> 模块编码:</label></td>
				<td class="value"><input id="spacecode" name="spacecode"
					type="text" style="width: 200px" class="inputxt" value='${advert.spacecode}'> 
				</td>
			</tr>
			<tr>
				
				<td align="right"><label class="Validform_label"> 状态: </label></td>
				<td class="value">	
					<input type="radio" name="status" value="0" checked="checked" />启用&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="status" value="1" />禁用
				</td>
				<td align="right"><label class="Validform_label"> 生效日期:</label></td>
				<td class="value"><input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 200px" id="effectivedate" name="effectivedate"
			        value="<fmt:formatDate value='${advert.effectivedate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"> 
				</td>
			</tr>
			
			<tr>
				<td align="right"><label class="Validform_label"> 图片地址: </label></td>
				<td class="value"><input id="imgurl" name="imgurl" type="text"
					style="width: 200px" class="inputxt" value='${advert.imgurl}'>
				</td>
				<td align="right"><label class="Validform_label"> 链接地址:</label></td>
				<td class="value"><input id="linkurl" name="linkurl"
					type="text" style="width: 200px" class="inputxt" value='${advert.linkurl}'>
				</td>
			<tr>
			<tr>
			    <td align="right"><label class="Validform_label">广告类型: </label></td>
				<td class="value">
				    <select id="adttype" name="adttype" style="width: 206px">
				       <option value="">---请选择---</option>
				    </select>
				</td>
				<td align="right"><label class="Validform_label"> 模版类型:</label></td>
				<td class="value"> 
				    <select id="templateid" name="templateid" style="width: 206px">
				       <option value="">---请选择---</option>
				    </select>
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 摘要:</label></td>
				<td class="value" colspan="3">
				  <textarea style="width: 680px;height: 100px;" name="remark"
						class="inputxt">${advert.remark}</textarea>
				</td>
			</tr>
			
			<tr>
				<td align="right"><label class="Validform_label"> 正文:</label></td> 
				<td class="value" colspan="3">
				  <t:ckeditor name="content" isfinder="true" value="${advert.content}" type="width:680,height:400"></t:ckeditor>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>