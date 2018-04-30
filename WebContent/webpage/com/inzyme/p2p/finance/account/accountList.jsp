<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src = "webpage/com/inzyme/p2p/finance/account/accountList.js"></script>	
<div id="system_function_functionList" class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;">
		<t:datagrid name="accountList" checkbox="true" fitColumns="true"
			title="帐户表" actionUrl="accountController.do?datagrid" idField="id" queryMode="group">
			<t:dgCol title="主键" field="id" hidden="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="创建者" field="createUser" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd"
				hidden="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="更新者" field="updateUser" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd"
				hidden="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="帐号" field="accountno" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="户主" field="holderid" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="账户名称" field="accountname" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="账户类型" field="accounttype" query="true"
				queryMode="single" width="120" replace="平台账户_0,个人账户_1,虚拟账户_2"></t:dgCol>
			<t:dgCol title="余额" field="balance" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="状态" field="status" queryMode="single" width="120"
				replace="正常_0,冻结_1"></t:dgCol>
			<t:dgCol title="备注" field="remark" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="200"></t:dgCol>
			<t:dgConfOpt url="accountController.do?doDel&id={id}"
				message="该账户存在交易记录,是否删除?" title="删除"></t:dgConfOpt>
			<t:dgFunOpt exp="status#eq#0"  funname="isStatusTrue(id)" title="冻结" />
            <t:dgFunOpt exp="status#eq#1"  funname="isStatusFalse(id)" title="启用" />
			<t:dgFunOpt funname="detail(id)" title="查看详情"></t:dgFunOpt>
			<t:dgToolBar title="录入" icon="icon-add"
				url="accountController.do?addorupdate&type=add" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" icon="icon-edit"
				url="accountController.do?addorupdate&type=update" funname="update"></t:dgToolBar>
			<t:dgToolBar title="刷新" icon="icon-reload" onclick="reloadAccount()"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<div
	data-options="region:'east',title:'交易明细',collapsed:true,split:true,border:false,
	    onExpand : function(){
		   li_east = 1;
	    },
	    onCollapse : function() {
	       li_east = 0;
	    }"
	style="width: 400px; overflow: hidden;">
	<div class="easyui-panel" style="padding: 1px;" fit="true"
		border="false" id="transcationpanel"></div>
</div>
