<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div id="system_function_functionList" class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<t:datagrid name="debtList" title="债权表" actionUrl="debtController.do?projectid&functionId=${functionId}" idField="id">
	<t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建者"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新者"  field="updateUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新时间"  field="updateTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目id"  field="projectid"  hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会员名称"  field="holderid"  replace="${personReplace}"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="金额"  field="amount"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="购入日期"  field="buydate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="已还本金"  field="paidprincipal"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="已还利息"  field="paidinterest"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="待还本金"  field="payableprincipal"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="待还利息"  field="payableinterest"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="是否启用"  field="status"  replace="未启用_0,启用_1"   queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="60"></t:dgCol>
   <t:dgDelOpt title="删除" url="debtController.do?doDel&id={id}" />
   <t:dgFunOpt exp="status#eq#0" operationCode="isopen" funname="isopen(id)" title="启用" />
   <t:dgFunOpt exp="status#eq#1" operationCode="isclosed" funname="isclosed(id)" title="禁用" />
   <t:dgToolBar title="录入" icon="icon-add" url="debtController.do?addorupdateop&functionId=${functionId}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="debtController.do?addorupdateop&functionId=${functionId}" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="debtController.do?addorupdateop" funname="detail"></t:dgToolBar>
</t:datagrid></div>
</div>
<script type="text/javascript">
	
		//启用
	function isopen(id) {
	$.ajax({
			type : "post",
			url : "debtController.do?updatestatus&flag=1&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#debtList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
	
	
		//禁用
	function isclosed(id) {
	$.ajax({
			type : "post",
			url : "debtController.do?updatestatus&flag=2&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#debtList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
	</script>