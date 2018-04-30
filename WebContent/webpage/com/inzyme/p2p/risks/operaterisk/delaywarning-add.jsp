<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>逾期预警</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="delaywarningController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${delaywarningPage.id }">
					<input id="createUser" name="createUser" type="hidden" value="${delaywarningPage.createUser }">
					<input id="createTime" name="createTime" type="hidden" value="${delaywarningPage.createTime }">
					<input id="updateUser" name="updateUser" type="hidden" value="${delaywarningPage.updateUser }">
					<input id="updateTime" name="updateTime" type="hidden" value="${delaywarningPage.updateTime }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="projectid" name="projectid" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目编号</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会员编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="memberid " name="memberid " type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">会员编号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需要支付的本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="needpayprincipal" name="needpayprincipal" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">需要支付的本金</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需要支付的利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="needpayinterest" name="needpayinterest" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">需要支付的利息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							应该支付的日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="datetobepaid" name="datetobepaid" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">应该支付的日期</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/inzyme/p2p/risks/operaterisk/delaywarning.js"></script>		