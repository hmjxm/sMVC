<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>债权表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
	//初始化控件
	function init() {
		var isopen = "${debt.status}";
		$("input[name='status'][value='" + isopen + "']").attr("checked", true);
	}
  </script>
 </head>
 <body onload="init()">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="debtController.do?saveop" tiptype="1">
					<input name="investproject.id" value="${functionId}" type="hidden" id="pid">
					<input id="id" name="id" type="hidden" value="${debt.id }">
					<input id="createUser" name="createUser" type="hidden" value="${debt.createUser }">
					<input id="createTime" name="createTime" type="hidden" value="${debt.createTime }">
					<input id="updateUser" name="updateUser" type="hidden" value="${debt.updateUser }">
					<input id="updateTime" name="updateTime" type="hidden" value="${debt.updateTime }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
					<tr>
						<td align="right">
							<label class="Validform_label">
								会员名称:
							</label>
						</td>
						<td class="value">
						<select id="holderid" name="holderid">
						<c:forEach items="${personTypeList}" var="person">
							<option value="${person.id }"
								${person.id==debt.holderid?'selected':''}>${person.fullname}</option>
						</c:forEach>
				</select>
						
						   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">会员id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								购买金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${debt.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">债权购买金额</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								购入日期:
							</label>
						</td>
						<td class="value">
						
						<input id="buydate" name="buydate" value='${debt.buydate}' class="easyui-datetimebox combo-f datetimebox-f" comboname="time" style="display: none;">
						
						     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">购入日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								已还本金:
							</label>
						</td>
						<td class="value">
						     	 <input id="paidprincipal" name="paidprincipal" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${debt.paidprincipal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已还本金</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								已还利息:
							</label>
						</td>
						<td class="value">
						     	 <input id="paidinterest" name="paidinterest" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${debt.paidinterest}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已还利息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								待还本金:
							</label>
						</td>
						<td class="value">
						     	 <input id="payableprincipal" name="payableprincipal" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${debt.payableprincipal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">待还本金</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								待还利息:
							</label>
						</td>
						<td class="value">
						     	 <input id="payableinterest" name="payableinterest" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${debt.payableinterest}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">待还利息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否启用:
							</label>
						</td>
						<td class="value">
						
						<input type="radio"
					name="status" value="1" />是&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="status" value="0" checked="checked" />否
						   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/inzyme/p2p/projects/projectcheck/debt.js"></script>		