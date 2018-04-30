<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>逾期垫付申请</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
		<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
	</head>
	<body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password"
			layout="table" action="cpensatoryappController.do?doAdd" tiptype="1">
			<input id="id" name="id" type="hidden"
				value="${cpensatoryappPage.id }">
			<input id="createUser" name="createUser" type="hidden"
				value="${cpensatoryappPage.createUser }">
			<input id="createTime" name="createTime" type="hidden"
				value="${cpensatoryappPage.createTime }">
			<input id="updateUser" name="updateUser" type="hidden"
				value="${cpensatoryappPage.updateUser }">
			<input id="updateTime" name="updateTime" type="hidden"
				value="${cpensatoryappPage.updateTime }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							债权编号:
						</label>
					</td>
					<td class="value">
						<select id="debtid" name="debtid">
							<option value="">
								请选择
							</option>
						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							债权编号
						</label>
					</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								投资者:
							</label>
						</td>
						<td class="value">
							<input id="holderid" name="holderid" type="text"
								style="width: 150px" class="inputxt">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">
								投资者
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								需支付本金:
							</label>
						</td>
						<td class="value">
							<input id="payprincipal" name="payprincipal" type="text"
								style="width: 150px" class="inputxt">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">
								需支付本金
							</label>
						</td>
						<tr>
							<td align="right">
								<label class="Validform_label">
									需支付利息:
								</label>
							</td>
							<td class="value">
								<input id="payinterest" name="payinterest" type="text"
									style="width: 150px" class="inputxt">
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">
									需支付利息
								</label>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="Validform_label">
									申请时间:
								</label>
							</td>
							<td class="value">
								<input id="applydate" name="applydate" type="text"
									style="width: 150px" class="inputxt">
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">
									申请时间
								</label>
							</td>
							<tr>
								<td align="right">
									<label class="Validform_label">
										支付时间:
									</label>
								</td>
								<td class="value">
									<input id="paydate" name="paydate" type="text"
										style="width: 150px" class="inputxt">
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">
										支付时间
									</label>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										状态:
									</label>
								</td>
								<td class="value">
									<input id="status" name="status" type="text"
										style="width: 150px" class="inputxt">
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">
										状态
									</label>
								</td>
								<tr>
									<td align="right">
										<label class="Validform_label">
											备注:
										</label>
									</td>
									<td class="value">
										<input id="remark" name="remark" type="text"
											style="width: 150px" class="inputxt">
										<span class="Validform_checktip"></span>
										<label class="Validform_label" style="display: none;">
											备注
										</label>
									</td>
								</tr>
			</table>
		</t:formvalid>
	</body>
	<script src="webpage/com/inzyme/p2p/risks/operaterisk/cpensatoryapp.js">
</script>