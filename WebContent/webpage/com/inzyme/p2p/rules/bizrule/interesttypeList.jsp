<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="interesttypeList" checkbox="false" fitColumns="false" title="计息种类" actionUrl="interesttypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="计息名称"  field="inerestname"  hidden="false"   width="120"></t:dgCol>
   <t:dgCol title="参数"  field="argnums"  hidden="true"    width="120"></t:dgCol>
   <t:dgCol title="Bean对象名称"  field="caclbean"    width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="图标"  field="iconimg" hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="是否启用"  field="status"   replace="未启用_0,启用_1"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="interesttypeController.do?del&id={id}" />
     <t:dgFunOpt exp="status#eq#0" operationCode="isopen" funname="isopen(id)" title="启用" />
  	 <t:dgFunOpt exp="status#eq#1" operationCode="isclosed" funname="isclosed(id)" title="禁用" />
   
   <t:dgToolBar title="录入" icon="icon-add" url="interesttypeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="interesttypeController.do?addorupdate" funname="update"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/inzyme/p2p/rules/bizrule/interesttypeList.js"></script>		
<script type="text/javascript">
	
		//启用
	function isopen(id) {
	$.ajax({
			type : "post",
			url : "interesttypeController.do?updatestatus&flag=1&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#interesttypeList").datagrid("reload");
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
			url : "interesttypeController.do?updatestatus&flag=2&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#interesttypeList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
	</script>