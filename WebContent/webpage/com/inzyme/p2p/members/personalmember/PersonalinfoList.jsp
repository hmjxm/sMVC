<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="personalinfoViewList" checkbox="true" fitColumns="false" title="个人会员基本信息" actionUrl="personalinfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="登录账号"  field="loginname" query="true"   queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="姓名"  field="fullname"    queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="出生日期"  field="birth"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="是否允许第三方登录"  field="isthirdlogin"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="第三方登录站点"  field="thirdloginsite"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="第三方登录账号"  field="thirdloginname"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="状态"  field="status"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="200"></t:dgCol>
   <t:dgFunOpt funname="cancellation(id)" title="注销" />
   <t:dgFunOpt funname="setstatus(id)" title="冻结" exp="status#eq#正常"/>
   <t:dgFunOpt funname="setstatus(id)" title="回复" exp="status#eq#冻结"/>
   <t:dgFunOpt funname="repwd(id)" title="重置密码" exp="status#ne#注销"/>
   <t:dgFunOpt funname="opencert(id)" title="认证信息" exp="status#ne#注销"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="personalinfoController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="personalinfoController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="personalinfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javaScript">
function setstatus(id) {
		$.ajax({
			type : "post",
			url : "personalinfoController.do?setstatus",
			data : {operid:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				$("#personalinfoViewList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
	}
function cancellation(id) {
		$.ajax({
			type : "post",
			url : "personalinfoController.do?cancelstatus",
			data : {operid:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
				if(data.msg=="已注销")
				   $("#personalinfoViewList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
	}	
function repwd(id) {
		$.ajax({
			type : "post",
			url : "personalinfoController.do?resetpwd",
			data : {operid:id},
			dataType : "json",
			success : function(data) {
				tip(data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
		});
		
	}
function opencert(id){
       createwindow("录入/编辑","personalcertController.do?addorupdate&operid="+id,800,400);
       }	
</script>