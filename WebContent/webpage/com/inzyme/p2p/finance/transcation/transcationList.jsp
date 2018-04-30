<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
     function reloadTranscation(){
    	 $("#transcationList").datagrid("reload");
     }
     function delTran(){
    	var node = $("#transcationList").datagrid("getSelected");
    	if (node == null) {
    		$.messager.alert("提示消息", "选择一条数据删除");
    		return;
    	}
    	$.messager.confirm("提示框","是否确认删除", function (r) {  
   	        if (r) {  
   	        	$.ajax({
	   	     		url:"transcationController.do?doDel",
	   	     		data:{id:node.id},
	   	     		onSuccess:function(){
	   	     			reloadTranscation();
	   	     		},
	   	     	    complete:function(){
	   	     	        $("#accountList").datagrid("reload");
	   	     	        reloadTranscation();
	   	     	    }
	   	     	});  
   	         }  
   	     }); 
     } 
     
</script>
<t:datagrid name="transcationList" fitColumns="false" title="" actionUrl="transcationController.do?datagrid&accountId=${accountId}"
	idField="id" fit="true" queryMode="group">
	<t:dgCol title="主键" field="id" hidden="true" queryMode="single"
		width="120"></t:dgCol>
	<t:dgCol title="创建者" field="createUser" hidden="true"
		queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="更新者" field="updateUser" hidden="true"
		queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd"
		hidden="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd"
		hidden="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="科目" field="title" queryMode="single" width="120"
	replace="放款_1,提现_2,投资_3"></t:dgCol>
	<t:dgCol title="借贷方向" field="direction" queryMode="single" width="120"
		replace="支出_1,收入_2"></t:dgCol>
	<t:dgCol title="合计" field="amount" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="账单日期" field="accountdate" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="备注" field="remark" queryMode="single" width="120"></t:dgCol>
	<t:dgToolBar title="录入" icon="icon-add"
		url="transcationController.do?addorupdate&type=add&accountId=${accountId}" funname="add"></t:dgToolBar>
	<t:dgToolBar title="编辑" icon="icon-edit"
		url="transcationController.do?addorupdate&type=update&accountId=${accountId}" funname="update"></t:dgToolBar>
	<t:dgToolBar title="删除" icon="icon-remove" funname="delTran"></t:dgToolBar>
	<t:dgToolBar title="刷新" icon="icon-reload" onclick="reloadTranscation()"></t:dgToolBar>
</t:datagrid>