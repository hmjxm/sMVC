<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>个人会员认证信息</title>
		<t:base type="jquery,easyui,tools"></t:base>
		<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js">
</script>
		<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js">
</script>
	</head>
	<body>
		<t:formvalid formid="formobj1" layout="div" dialog="true"
			action="personalcertController.do?doAdd&personalinfoid=${personalinfoid}"
			tiptype="1">
			<input name="id" id="id" type="hidden" value="${personalcert.id}">
			<table style="width: 700px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<fieldset class="step">
					<tr>
						<td align="right">
							<label class="Validform_label">
								真实姓名:
							</label>
						</td>
						<td class="value">
							<input name="realname" id="realname" datatype="*"
								value="${personalcert.realname}" class="inputxt">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								身份证号:
							</label>
						</td>
						<td class="value">
							<input name="idcardno" id="idcardno" datatype="n18-18"
								value="${personalcert.idcardno}" class="inputxt">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								身份证号认证状态:
							</label>
						</td>
						<td class="value">

							<t:dictSelect field="idcardnocertstatus"
								typeGroupCode="certstatus" hasLabel="false" title=""
								defaultVal="${personalcert.idcardnocertstatus}"></t:dictSelect>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								电话号码:
							</label>
						</td>
						<td class="value">
							<input name="telno" id="telno" value="${personalcert.telno}"
								datatype="m" class="inputxt">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								电话号码认证状态:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="telcert" field="telnocertstatus"
								typeGroupCode="certstatus" hasLabel="false" title=""
								defaultVal="${personalcert.telnocertstatus}"></t:dictSelect>

						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								电子邮箱:
							</label>
						</td>
						<td class="value">
							<input name="email" id="email" value="${personalcert.email}"
								datatype="e" class="inputxt">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								电子邮箱认证状态:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="emailcertstatus" typeGroupCode="certstatus"
								hasLabel="false" title=""
								defaultVal="${personalcert.emailcertstatus}"></t:dictSelect>
						</td>
					</tr>
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
							<input name="remark" id="remark" value="${personalcert.remark}"
								class="inputxt">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
				</fieldset>
			</table>
		</t:formvalid>
	</body>
</html>
