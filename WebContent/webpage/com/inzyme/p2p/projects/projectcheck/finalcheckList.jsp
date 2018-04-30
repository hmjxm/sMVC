<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="finalcheckList" checkbox="false" fitColumns="false" title="立项终审" actionUrl="finalcheckController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="titles"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标的种类"  field="bidtypeid"   replace="${bidReplace}" width="120"></t:dgCol>
   <t:dgCol title="会员名称"  field="memberid" replace="${personReplace}"   width="120"></t:dgCol>
   <t:dgCol title="用途"  field="usages"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amounts"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款利率"  field="rate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款方式"  field="interesttypeid" replace="${argReplace}" hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参数"  field="args" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款期限"  field="returnterm"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="description"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt  operationCode="ischeck" funname="ischeck(id)" title="审批" />
  </t:datagrid>
  </div>
 </div>
	<script type="text/javascript">
	
		//审批
	function ischeck(id) {
	$.ajax({
			type : "post",
			url : "finalcheckController.do?upStatus&id="+id,
			data : {cataId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#finalcheckList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
		</script>
