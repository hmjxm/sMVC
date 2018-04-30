<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" id="system_function_functionList">
  <div region="center" style="padding:1px;">
  <t:datagrid name="investprojectList" checkbox="false" fitColumns="false" title="招标项目发布表" actionUrl="investprojectController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="创建者"  field="createUser"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="更新者"  field="updateUser"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="更新时间"  field="updateTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single" ></t:dgCol>
   <t:dgCol title="标题"  field="titles"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="标的种类"  field="bidtypeid"  replace="${bidReplace}"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款人"  field="memberid"  replace="${personReplace}"   queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="用途"  field="usages" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amounts"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="借款利率"  field="interestrate"   queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="还款方式"  field="interesttypeid"  replace="${argReplace}"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参数"  field="args" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款期限"  field="returnterm"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="描述"  field="description"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款申请ID"  field="loanapplyid"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="投标期限"  field="bidterm"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="投标发布日"  field="issuedate"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="记账日"  field="accountdate"  hidden="true"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="项目状态"  field="status" hidden="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="已投标金额"  field="biddenamount"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="已还本金"  field="paidprincipal"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="已还利息"  field="paidinterest"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="待还本金"  field="payableprincipal"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="待还利息"  field="payableinterest"    queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="版本号"  field="versions"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="investprojectController.do?doDel&id={id}" />
   <t:dgFunOpt funname="investProjectDetail(id)" title="查看详情"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="investprojectController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="investprojectController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="investprojectController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'查看详情',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 700px; overflow: hidden;">
<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="investprojectDetailpanel"></div>
</div>


<script type="text/javascript">

$(function() {
	var li_east = 0;
});
function investProjectDetail(functionId)
{

	if(li_east == 0){
	   $("#system_function_functionList").layout("expand","east"); 
	}
	$("#investprojectDetailpanel").panel("refresh", "investprojectController.do?operation&functionId=" +functionId);
}



</script>