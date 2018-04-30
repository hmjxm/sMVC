<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>消息列表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script src="webpage/com/inzyme/p2p/message/innermsg/innermsgList.js"></script>
</head>
<body>

    <div id="subgrid-tb" style="display: none;">
				<table>
					<tr>
						<td>
							<span>分类名称:</span> 
							<span>
							   <input type="text" id="" name="" value="" style="width: 120px;" />
							</span>
						</td>
						<td>
						   <a onclick="" href="javascript:void(0);" data-options="plain:false,iconCls:'icon-search'">查询</a> 
						</td>
					</tr>
				</table>
	</div>

	<div class="easyui-layout" data-options="fit:true,border:true">
		<div split="true" title="消息列表" style="padding:5px;width:220px;"
			data-options="region:'west',tools:[{
	                    handler:function(){
	                        
	                    }
	                }]">
			<ul id="msgTree"></ul>
		</div>
		<div data-options="region:'center'" style="padding: 5px;">
			<t:datagrid name="innermsgList" checkbox="true" fitColumns="false"
				title="内部消息" actionUrl="innermsgController.do?datagrid" idField="id"
				fit="true" queryMode="group"> 
				<t:dgCol title="主键" field="id" hidden="true" queryMode="single"
					width="120"></t:dgCol>
				<t:dgCol title="创建者" field="createUser" hidden="true"
					queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd"
					hidden="true" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="更新者" field="updateUser" hidden="true"
					queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd"
					hidden="true" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="状态"  field="status"  
				replace=<img src=\"webpage/com/inzyme/p2p/message/innermsg/image/folder.gif\"/>_0,<img src=\"webpage/com/inzyme/p2p/message/innermsg/image/file.gif\"/>_1>
				</t:dgCol>
				<t:dgCol title="标题" field="title" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="发送时间" field="sendtime" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="发送人" field="sender" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="接收人" field="reciever" queryMode="single" width="120"></t:dgCol>
				<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
				<t:dgDelOpt title="删除" url="innermsgController.do?doDel&id={id}" />
				<t:dgToolBar title="发送消息" icon="icon-ok" funname="addMsg"></t:dgToolBar>
				<t:dgToolBar title="查看" icon="icon-search" url="innermsgController.do?goUpdate" funname="detail"></t:dgToolBar>
			</t:datagrid>
		</div>
	</div>
</body>
</html>
