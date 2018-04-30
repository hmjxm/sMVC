<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="investbidtypeList" checkbox="false" fitColumns="false" title="投资标的种类" actionUrl="investbidtypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="投资名称"  field="bidtypename"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图标路径"  field="iconimg"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题路径"  field="titleimg" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="description"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="允许个人投资"  field="allowperson"  hidden="false" replace="是_Y,否_N"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="允许企业投资"  field="allowcompany"  hidden="false" replace="是_Y,否_N"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="验证计算对象"  field="checkbean"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否启用"  field="status"  hidden="false"  queryMode="single"  replace="未启用_0,启用_1"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
      <t:dgDelOpt title="删除" url="investbidtypeController.do?doDel&id={id}" />
  	  <t:dgFunOpt exp="status#eq#0" operationCode="isopen" funname="isopen(id)" title="启用" />
  	  <t:dgFunOpt exp="status#eq#1" operationCode="isclosed" funname="isclosed(id)" title="禁用" />
   <t:dgToolBar title="录入" icon="icon-add" url="investbidtypeController.do?addorupdate&functype=1" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="investbidtypeController.do?addorupdate&functype=2" funname="update"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/inzyme/p2p/rules/bizrule/investbidtypeList.js"></script>		
<script type="text/javascript">
	
		//启用
	function isopen(id) {
	$.ajax({
			type : "post",
			url : "investbidtypeController.do?updatestatus&flag=1&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#investbidtypeList").datagrid("reload");
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
			url : "investbidtypeController.do?updatestatus&flag=2&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#investbidtypeList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
	</script>