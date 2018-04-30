<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加内容</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

<script src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
<script src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
<script src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
<script src="webpage/com/inzyme/p2p/contents/news/imgshow.js"></script>
<script src="webpage/com/inzyme/p2p/contents/news/imgupload.js"></script>
<link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
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
<script type="text/javascript">
	//初始化控件
	function init() {
		var status = "${newscontent.status}";
		$("input[name='status'][value='" + status + "']").attr("checked", true);
		
		var topleve = "${newscontent.topleve}";
		$("input[name='topleve'][value='" + topleve + "']").attr("checked", true);
	}
</script>
</head>
<body style="overflow-y: hidden" scroll="no" onload="init()">
	<t:formvalid formid="formobj" dialog="true" action="newscontentController.do?saveContent" usePlugin="password"
		layout="table">
		<input id="id" name="id" type="hidden" value="${newscontent.id }">
		<input id="newscatelogEntity.id" name="newscatelogEntity.id"
			type="hidden" value="${catelogId }">
		<input id="createName" name="createName" type="hidden"
			value="${newscontent.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${newscontent.createBy }">
		<input id="createDate" name="createDate" type="hidden"
			value="${newscontent.createDate }">
		<input id="updateName" name="updateName" type="hidden"
			value="${newscontent.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${newscontent.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${newscontent.updateDate }">
		<div style="width:800px; height:450px; overflow:scroll;">
			<table style="width: 780px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<tr>
					<td align="right" width="100px"><label class="Validform_label">
							标题: </label></td>
					<td class="value"><input id="title" name="title" type="text" style="width: 200px" class="inputxt"
						value="${newscontent.title}"> <span
						class="Validform_checktip"></span>
					</td>
					<td align="right" width="100"><label class="Validform_label"> 副标题:
					</label>
					</td>
					<td class="value"><input id="subtitle" name="subtitle"
						type="text" style="width: 200px" class="inputxt"
						value="${newscontent.title}"> <span
						         
						class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 栏目:
					</label></td>
					<td class="value"><input id="newscatalogEntity.title"
						name="newscatalogEntity.title" type="text"
						style="width: 200px" class="inputxt" value="${catelogtitle }"
						readonly="readonly"> <span class="Validform_checktip"></span>
					</td>
					<td align="right"><label class="Validform_label"> 作者:
					</label></td>
					<td class="value"><input id="author"
						name="author" type="text"
						style="width: 200px" class="inputxt" value="${newscontent.author }"> 
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="100px"><label class="Validform_label">
							新闻来源: </label></td>
					<td class="value"><input id="newsfrom" name="newsfrom"
						 type="text" style="width: 200px" class="inputxt"
						value="${newscontent.newsfrom}"> <span
						class="Validform_checktip"></span>
					</td>
					<td align="right"><label class="Validform_label"> 发布日期:
					</label>
					</td>
					<td class="value"><input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 200px" id="publishdate" name="publishdate"
				        value="<fmt:formatDate value='${newscontent.publishdate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"> 
				        <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 关键字:
					</label>
					</td>
					<td class="value"><input id="keyword" name="keyword"
						type="text" style="width: 200px" class="inputxt"
						value="${newscontent.keyword}"> <span
						class="Validform_checktip"></span>
					</td>
					
					<td align="right"><label class="Validform_label"> 序号:
					</label>
					</td>
					<td class="value"><input id="orderno" name="orderno"
						type="text" style="width: 200px" class="inputxt"
						value="${newscontent.orderno}"> <span
						class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 状态:
					</label></td>
					<td class="value">	
						<input type="radio" name="status" value="0" />是&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="status" value="1" checked="checked" />否
					</td>
					
					
					<td align="right"><label class="Validform_label"> 是否顶级:
					</label></td>
					<td class="value">	
						<input type="radio" name="topleve" value="0" />是&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="topleve" value="1" checked="checked" />否
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 摘要:
					</label></td>
					<td class="value" colspan="3"><textarea
							style="width: 650px;height: 100px;" name="abstracts"
							class="inputxt">${newscontent.abstracts}</textarea> <span
						class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right"><label class="Validform_label"> 图片上传:
					</label></td>
					<td colspan="3">
                         <table cellpadding="0" style="width: 500px" cellspacing="0" class="formtable">
                            <tr>
                               <td colspan="2" rowspan="2">
                                 <div style="width: 200">
							        <img id="ImgPr" src="${newscontent.titleimg}" width="100" height="100"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							     </div>
                               </td>
                               <td rowspan="2">
                                 <div class="file">上传图片
		                          <input id="fileupload" type="file" data-url="newscontentController.do?upload">
		                        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               </td>
                                <td align="right">宽度:</td>
								<td><input type="text" id="titleimgwidth"
									name="titleimgwidth" value="${newscontent.titleimgwidth}" />
								</td>
                            </tr>
                            <tr>
                                <td align="right">高度:</td>
								<td><input type="text" id="titleimgheight"
									name="titleimgheight" value="${newscontent.titleimgheight}" />
								</td>
                            </tr>
                         </table>
                         
                         <input name="titleimg" class="inputxt" type="hidden" value="${newscontent.titleimg}" id="titleimg" readonly="readonly"/>
                         
                     </td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 正文:
					</label></td> 
					<td class="value" colspan="3"><t:ckeditor name="content" isfinder="true"
							value="${newscontent.content}" type="width:670,height:400"></t:ckeditor>
						<span class="Validform_checktip"></span></td>
				</tr>
			</table>
		</div>
		</t:formvalid>
</body>