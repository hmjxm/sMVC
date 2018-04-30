<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="imgUploadList" title="【上传下载示例】"
			actionUrl="imgUploadController.do?datagrid" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true" ></t:dgCol>
			<t:dgCol title="照片URL" field="imgUrl" hidden="true" ></t:dgCol>
			<t:dgCol title="用户名" field="userName"></t:dgCol>
			<t:dgCol title="性别" field="sex" dictionary="sex" align="center" width="15"></t:dgCol>
			<t:dgCol title="年龄" field="age" align="center" width="15"></t:dgCol>
			<t:dgCol title="更新时间" field="createTime"
				formatter="yyyy-MM-dd hh:mm:ss" align="center" ></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgDelOpt title="删除" url="imgUploadController.do?del&id={id}" />
			<t:dgDefOpt title="照片下载" url="imgUploadController.do?downloadNet&id={id}" exp="imgUrl#ne#"></t:dgDefOpt>
			<t:dgToolBar title="录入" icon="icon-add" url="imgUploadController.do?addorupdate&functype=1" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" icon="icon-edit" url="imgUploadController.do?addorupdate&functype=2" funname="update"></t:dgToolBar>
			<t:dgToolBar title="查看" icon="icon-search" url="imgUploadController.do?addorupdate&functype=3" funname="detail"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>