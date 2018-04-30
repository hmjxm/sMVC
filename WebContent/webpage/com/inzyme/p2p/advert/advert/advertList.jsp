<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="advertList" checkbox="false" fitColumns="false" title="广告表" actionUrl="advertController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建者"  field="createUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新者"  field="updateUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新时间"  field="updateTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="广告名称"  field="adtname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="广告类型"  field="adttype"    queryMode="single"  width="120"
   replace="文字广告_0,图片广告_0_1,Flash广告_2,视频广告_3,图集广告_4"></t:dgCol>
   <t:dgCol title="图片地址"  field="imgurl" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="链接地址"  field="linkurl" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生效日期"  field="effectivedate" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status" queryMode="single"  width="120" replace="启用_0,禁用_1" query="true"></t:dgCol>
   <t:dgCol title="备注"  field="remark" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="advertController.do?doDel&id={id}" />
   <t:dgFunOpt exp="status#eq#0"  funname="isStatusTrue(id)" title="禁用" />
   <t:dgFunOpt exp="status#eq#1"  funname="isStatusFalse(id)" title="启用" /> 
   <t:dgToolBar title="录入" icon="icon-add" funname="addAdvert"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" funname="updateAdvert"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/inzyme/p2p/advert/advert/advertList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
    //给时间控件加上样式
	$("#advertListtb").find("input[name='createTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#advertListtb").find("input[name='updateTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 </script>