<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>编辑</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
		<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/imgupload.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/getTerritory.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/personalinfo-update.js"></script>
	</head>
	<body onload="getpcd()">
		<t:formvalid formid="formobj" dialog="true" usePlugin="password"
			layout="table" action="personalinfoController.do?doUpdate"
			tiptype="1">
			<input id="id" name="id" type="hidden" value="${personalinfo.id}">
			<input id="isthird" name="isthird" type="hidden" value="${personalinfo.isthirdlogin}">
			<input id="province" name="province" type="hidden" value="${personalinfo.provincecode}">
			<input id="city" name="city" type="hidden" value="${personalinfo.citycode}">
			<input id="district" name="district" type="hidden" value="${personalinfo.districtcode}">	
			<input id="headimg" name="headimg" type="hidden">
			<table style="width: 700px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<fieldset class="step">
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								登录账号:
							</label>
						</td>
						<td class="value" width="200">
							<input id="loginname" name="loginname" type="text" onChange="uniqchk()"
								style="width: 150px" class="inputxt"
								value="${personalinfo.loginname}">
							<span class="Validform_checktip"></span>
						</td>
						<td rowspan="5" width="80">
							<img src="${personalinfo.headimg}" width="200" height="200">
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								头像:
							</label>
						</td>
						<td class="value">
							<div class="form">
								<input id="fileupload" type="file"
									data-url="personalinfoController.do?upload">
							</div>
						</td>
					</tr>
					<tr id="isthirdloginrow">
						<td align="right">
							<label class="Validform_label">
								是否允许第三方登录 :
							</label>
						</td>
						<td class="value">
							<input type="radio" name="isthirdlogin" id="isthirdlogin1"
								value="yes" onClick="aboren()">
							是
							<input type="radio" name="isthirdlogin" id="isthirdlogin2"
								value="no" onClick="aboren()">
							否
						</td>
					</tr>
					<tr id="isthirdloginrow">
						<td align="right">
							<label class="Validform_label">
								第三方登录站点 :
							</label>
						</td>
						<td class="value">
							<input id="thirdloginsite" name="thirdloginsite" type="text"
								style="width: 150px" class="inputxt"
								value="${personalinfo.thirdloginsite}" />
						</td>
					</tr>
					<tr id="isthirdloginrow">
						<td align="right">
							<label class="Validform_label">
								第三方登录账号 :
							</label>
						</td>
						<td class="value">
							<input id="thirdloginname" name="thirdloginname" type="text"
								style="width: 150px" class="inputxt"
								value="${personalinfo.thirdloginname}" />
						</td>
					</tr>
					<tr>
						<td align="right" >
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value" colspan="2">
							<input id="fullname" name="fullname" type="text"
								style="width: 150px" class="inputxt" readonly="readonly"
								value="${personalinfo.fullname}" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓:
							</label>
						</td>
						<td class="value" colspan="2">
							<input id="firstname" name="firstname" type="text"
								style="width: 150px" class="inputxt" 
								value="${personalinfo.firstname}">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right" >
							<label class="Validform_label">
								名:
							</label>
						</td>
						<td class="value" colspan="2">
							<input id="lastname" name="lastname" type="text"
								style="width: 150px" class="inputxt" 
								value="${personalinfo.lastname}">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别 :
							</label>
						</td>
						<td class="value" colspan="2">
							<t:dictSelect id="sex" field="sex" typeGroupCode="sex"
								hasLabel="false" defaultVal="${personalinfo.sex}"></t:dictSelect>
						</td>
						<tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										出生日期:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="birth" name="birth" type="text" style="width: 150px"
										class="Wdate" onClick="WdatePicker()"
										value="${personalinfo.birth}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										民族:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="nation" name="nation" type="text"
										style="width: 150px" class="inputxt"
										value="${personalinfo.nation}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										电话号码:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="telno" name="telno" type="text" style="width: 150px"
										datatype="m" class="inputxt" value="${personalinfo.telno}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										电子邮箱:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="email" name="email" type="text" style="width: 150px"
										datatype="e" class="inputxt" value="${personalinfo.email}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										省码:
									</label>
								</td>
								<td class="value" colspan="2" >
									<select id="provincecode" name="provincecode"
										onChange="getcterritory()">
										<option value="">
											请选择
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										地址:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="address" name="address" type="text"
										style="width: 150px" class="inputxt"
										value="${personalinfo.address}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										备注:
									</label>
								</td>
								<td class="value" colspan="2">
									<input id="remark" name="remark" type="text"
										style="width: 150px" class="inputxt"
										value="${personalinfo.remark}">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
				</fieldset>
			</table>
		</t:formvalid>
	</body>
</html>
