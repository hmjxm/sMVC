<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="appissueList" checkbox="false" fitColumns="false" title="APP发布表" actionUrl="appissueController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="App中文名"  field="appname"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="App种类"  field="apptype"    queryMode="single"  width="120"
   replace="安卓_1,IOS_2,WP_2,黑莓_2"></t:dgCol>
   <t:dgCol title="生效日期"  field="effectivedate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="issueversion"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single"  width="120" replace="启用_0,禁用_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="appissueController.do?doDel&id={id}" />
   <t:dgFunOpt exp="status#eq#0"  funname="isStatusTrue(id)" title="禁用" />
   <t:dgFunOpt exp="status#eq#1"  funname="isStatusFalse(id)" title="启用" />
   <t:dgToolBar title="录入" icon="icon-add" funname="addApp"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" funname="updateApp"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/inzyme/p2p/app/issueapp/appissueList.js"></script>		
