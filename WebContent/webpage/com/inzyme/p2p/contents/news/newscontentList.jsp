<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>新闻栏目</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/contents/news/newscontentList.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:true">

		<div split="true" title="新闻栏目" style="padding:5px;width:220px;"
			data-options="region:'west',tools:[{
	                    iconCls:'icon-reload',
	                    handler:function(){
	                        refreshCataLogTree();
	                    }
	                }]">
			<ul id="cateLogTree"></ul>
		</div>
		<div data-options="region:'center'" style="padding: 5px;">
		<t:datagrid name="contentList" title="新闻列表" checkbox="false" fitColumns="false" actionUrl="newscontentController.do?datagrid" idField="id"
				fit="true" queryMode="group">
				<t:dgCol title="主键" field="id" hidden="true" queryMode="single"
					width="120"></t:dgCol>
				<t:dgCol title="标题" field="title" query="true" width="120"></t:dgCol>
				<t:dgCol title="关键字" field="keyword"  query="true" width="120"></t:dgCol>
				<t:dgCol title="作者" field="author" width="120"></t:dgCol>
				<t:dgCol title="顶置" field="topleve" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="发布日期" field="publishdate" queryMode="single"
					width="120"></t:dgCol>
				<t:dgCol title="已发布" field="status" query="true" replace="是_0,否_1" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
				<t:dgDelOpt title="删除" url="newscontentController.do?doDel&id={id}" />
				<t:dgFunOpt exp="status#eq#0"  funname="isStatusTrue(id)" title="禁用" />
  	            <t:dgFunOpt exp="status#eq#1"  funname="isStatusFalse(id)" title="发布" /> 
				<t:dgToolBar title="新增" icon="icon-add" funname="addContent"></t:dgToolBar>
				<t:dgToolBar title="编辑" icon="icon-edit" funname="updateContent"></t:dgToolBar>
				<t:dgToolBar title="预览" icon="icon-map" url="" funname=""></t:dgToolBar>
				<t:dgToolBar title="上移" icon="icon-collapse" funname="moveup"></t:dgToolBar>
				<t:dgToolBar title="下移" icon="icon-expand" funname="movedown"></t:dgToolBar>
			</t:datagrid>
		</div>
	</div>
</body>
</html>