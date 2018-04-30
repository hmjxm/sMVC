<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script
	src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
<script
	src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
<script
	src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
//初始化控件
$(function () {
    $("#iconupload").fileupload({
        dataType: "json",
        done: function (e, data) {
            $.each(data.result, function (index, icon) {
            	$("#iconimg").val("upload/"+icon.fileName);
            }); 
        },
    });
   
});
	function init() {
		var isopen = "${interesttypePage.status}";
		$("input[name='status'][value='" + isopen + "']").attr("checked", true);
	}
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
 </script>
 <style type="text/css">
.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
<body onload="init()" >
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="interesttypeController.do?save">
	<input id="id" name="id" type="hidden" value="${interesttypePage.id}">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right"><label class="Validform_label"> 计息名称: </label></td>
			<td class="value"><input class="inputxt" id="inerestname" name="inerestname" datatype="*" validType="p2p_t_interesttype,inerestname,id"  value="${interesttypePage.inerestname}" datatype="s2-15"></td>
		</tr><tr>	
			<td align="right"><label class="Validform_label"> 图标: </label></td>
			<td class="value">
		                        <img alt="图标" src="${interesttypePage.iconimg }" width="100px"
					height="100px"> 
			 <div class="file"> 
			 上传图片 <input id="iconupload" type="file" data-url="interesttypeController.do?upload" accept="image/*">
		                        </div>
		                        <input type="hidden" id="iconimg" name="iconimg" size="40" readonly="readonly" value="${interesttypePage.iconimg }"/>
			
			
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> bean类型 </label></td>
			<td class="value"><input class="inputxt" id="caclbean" name="caclbean" datatype="*" value="${interesttypePage.caclbean}"></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label"> 是否启用</label></td>
			<td class="value">
			<input type="radio"
					name="status" value="1" />是&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="status" value="0" checked="checked" />否	
			
			
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label"> 备注: </label></td>
			<td class="value"><input class="inputxt" id="remark" name="remark" datatype="*" value="${interesttypePage.remark}"></td>
				
	</table>
	<div style="width: auto; height: 200px;"><%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
	<div style="width: 500px; height: 1px;"></div>
	<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
		 <t:tab href="interesttypeController.do?interestargList&id=${interesttypePage.id}" icon="icon-search" title="计息种类参数" id="interestarg"></t:tab>
	</t:tabs></div>
</t:formvalid>
<!-- 添加 产品明细 模版 -->
<table style="display: none">
	<tbody id="add_interestarg_table_template">
		<tr>
			<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
			<td align="left"><input name="interestargList[#index#].argnames" maxlength="50" type="text" value="${poVal.argnames }" style="width: 120px;" datatype="*"></td>
					<td align="left"><input name="interestargList[#index#].argtypes" maxlength="32" type="text" value="${poVal.argtypes }" style="width: 120px;" datatype="*"></td>
					<td align="left"><input name="interestargList[#index#].arglengths" maxlength="32" type="text" value="${poVal.arglengths }" style="width: 120px;" datatype="*"></td>
					<td align="left"><input name="interestargList[#index#].remark" maxlength="100" type="text" value="${poVal.remark }" style="width: 120px;" datatype="*"></td>

		
		</tr>
	</tbody>
	
</table>

</body>