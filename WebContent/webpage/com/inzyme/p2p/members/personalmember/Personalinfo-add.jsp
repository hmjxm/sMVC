<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>录入</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
		<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/imgupload.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/getTerritory.js"></script>
		<script src="webpage/com/inzyme/p2p/members/personalmember/personalinfo-add.js"></script>
   </head>
	<body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password"
			layout="table" action="personalinfoController.do?doAdd" tiptype="1">
			<input id="id" name="id" type="hidden" value="${member.id }">
			<input id="password" name="password" type="hidden" value="123456">
			<input id="lastlogintime" name="lastlogintime" type="hidden" value="">
			<input id="lastloginip" name="lastloginip" type="hidden" value="">
			<input id="status" name="status" type="hidden" value="正常">
			<input id="headimg" name="headimg" type="hidden">
			<table style="width: 600px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<fieldset class="step">
					<tr>
						<td align="right" width="120">
							<label class="Validform_label">
								登录账号:
							</label>
						</td>
						<td class="value">
							<input id="loginname" name="loginname" type="text"
								style="width: 150px" class="inputxt" onChange="uniqchk()"
								datatype="/^[0-9a-zA-Z-]{6,20}$/" errormsg="请输入6到20位数字字母或下划线">
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								头像上传：
							</label>
						</td>
						<td class="value">
							<div class="form">
								<input id="fileupload" type="file"
									data-url="personalinfoController.do?upload">
							</div>
							<div class="form" id="filediv" style="height: 3px"></div>
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
								value="yes" onClick="addordel()">
							是
							<input type="radio" name="isthirdlogin" id="isthirdlogin2"
								value="no" onClick="addordel()" checked="checked">
							否
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input id="fullname" name="fullname" type="text"
								style="width: 150px" class="inputxt" onfocus="this.blur()">
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓:
							</label>
						</td>
						<td class="value">
							<input id="firstname" name="firstname" type="text"
								style="width: 150px" class="inputxt" >
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								名:
							</label>
						</td>
						<td class="value">
							<input id="lastname" name="lastname" type="text"
								style="width: 150px" class="inputxt">
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别 :
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="sex" typeGroupCode="sex" hasLabel="false"></t:dictSelect>
						</td>
						<tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										出生日期:
									</label>
								</td>
								<td class="value">
									<input id="birth" name="birth" type="text" style="width: 150px"
										class="Wdate" onClick="WdatePicker()">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										民族:
									</label>
								</td>
								<td class="value">
									<input id="nation" name="nation" type="text"
										style="width: 150px" class="inputxt">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										电话号码:
									</label>
								</td>
								<td class="value">
									<input id="telno" name="telno" type="text" style="width: 150px"
										datatype="m" class="inputxt">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										电子邮箱:
									</label>
								</td>
								<td class="value">
									<input id="email" name="email" type="text" style="width: 150px"
										datatype="e" class="inputxt">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
							<tr id="provincerow">
								<td align="right">
									<label class="Validform_label">
										地址:
									</label>
								</td>
								<td class="value">
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
								<td class="value">
									<input id="address" name="address" type="text"
										style="width: 150px" class="inputxt">
									<span class="Validform_checktip"></span>
								</td>
							</tr>
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
								</td>
							</tr>
				</fieldset>
			</table>
		</t:formvalid>
	</body>