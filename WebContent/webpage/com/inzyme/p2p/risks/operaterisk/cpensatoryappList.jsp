<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cpensatoryappList" checkbox="true" fitColumns="false" title="逾期垫付申请" actionUrl="cpensatoryappController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建者"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新者"  field="updateUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新时间"  field="updateTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="债务编号"  field="debtid"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="债主编号"  field="holderid"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="需支付本金"  field="payprincipal"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="需支付利息"  field="payinterest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请时间"  field="applydate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付时间"  field="paydate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cpensatoryappController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cpensatoryappController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cpensatoryappController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cpensatoryappController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cpensatoryappController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/inzyme/p2p/risks/operaterisk/cpensatoryappList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#cpensatoryappListtb").find("input[name='createTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#cpensatoryappListtb").find("input[name='updateTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cpensatoryappController.do?upload', "cpensatoryappList");
}

//导出
function ExportXls() {
	InzyExcelExport("cpensatoryappController.do?exportXls","cpensatoryappList");
}

//模板下载
function ExportXlsByT() {
	InzyExcelExport("cpensatoryappController.do?exportXlsByT","cpensatoryappList");
}
 </script>