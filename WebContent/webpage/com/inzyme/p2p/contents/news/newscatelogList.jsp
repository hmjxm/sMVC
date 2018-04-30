<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;">
		<t:datagrid name="newscatelogList" checkbox="true" showRefresh="true"
			fitColumns="false" title="新闻栏目"
			actionUrl="newscatelogController.do?cateloggrid" idField="id"
			fit="true" treegrid="true" pagination="false">
			<t:dgCol title="主键" field="id" treefield="id" hidden="true" width="120"></t:dgCol>
			<t:dgCol title="标题" field="title" width="120" treefield="text"></t:dgCol>
			<t:dgCol title="描述" field="description" width="120" treefield="src"></t:dgCol>
			<t:dgCol title="栏目代码" field="catelogcode" width="120" treefield="fieldMap.catelogcode"></t:dgCol>
			<t:dgCol title="栏目类型" field="catelogtype" width="120" treefield="fieldMap.catelogtype" replace="专栏_1,视频_2,新闻_3"></t:dgCol>
			<t:dgCol title="栏目地址" field="catelogurl" width="120" treefield="fieldMap.catelogurl"></t:dgCol>
			<t:dgCol title="摘要" field="abstracts" width="120" treefield="fieldMap.abstracts"></t:dgCol>
			<t:dgCol title="是否启用" field="isopen" width="120" treefield="fieldMap.isopen" replace="是_0,否_1"></t:dgCol>
			<t:dgCol title="操作" field="opt"></t:dgCol>
			<t:dgDelOpt title="删除" url="newscatelogController.do?doDel&id={id}" />
			<t:dgFunOpt funname="template(id)" title="配置模版" />
			<t:dgFunOpt funname="isopens(id)" title="启用" />
			<%--<t:dgFunOpt exp="isopen#eq#0"  funname="isopens(id)" title="禁用" />
  	        <t:dgFunOpt exp="isopen#eq#1"  funname="isopens(id)" title="发布" /> 
			--%>
			<t:dgToolBar title="录入" icon="icon-add" url="newscatelogController.do?addorupdate" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" icon="icon-edit" url="newscatelogController.do?addorupdate" funname="update"></t:dgToolBar>
			<t:dgToolBar title="上移" icon="icon-collapse" funname="moveup"></t:dgToolBar>
			<t:dgToolBar title="下移" icon="icon-expand" funname="movedown"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	//配置模版
	function template(id) {
        
	}
	//启用
	function isopens(id) {
		$.ajax({
			type : "post",
			url : "newscatelogController.do?saveIsOpen",
			data : {cateId:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#newscatelogList").treegrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
	
	
	function moveup() {
		move(true);
	}

	function movedown() {
		move(false);
	}

	function move(isUp) {
		var dt = $("#newscatelogList");
		var selections = dt.treegrid("getSelections");
		if (selections.length == 0) {
			return;
		}
		if (selections.length > 1) {
			$.message.show("一次只能移动一条数据。", "warning", 2);
			return;
		}
		var view = $("div.treegrid-view");
		var index = dt.treegrid("getRowIndex", selections[0]);
		var row = view.find("tr[treegrid-row-index=" + index + "]");
		if (isUp) {
			row.each(function() {
						var prev = $(this).prev();
						prev.length && $(this).insertBefore(prev);
					});
		} else {
			row.each(function() {
						var next = $(this).next();
						next.length && $(this).insertAfter(next);
					});
		}
	}
	
</script>