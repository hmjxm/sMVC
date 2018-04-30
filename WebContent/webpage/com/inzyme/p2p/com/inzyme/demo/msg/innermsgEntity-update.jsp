<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>内部消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="innermsgEntityController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${innermsgEntityPage.id }">
					<input id="createUser" name="createUser" type="hidden" value="${innermsgEntityPage.createUser }">
					<input id="createTime" name="createTime" type="hidden" value="${innermsgEntityPage.createTime }">
					<input id="updateUser" name="updateUser" type="hidden" value="${innermsgEntityPage.updateUser }">
					<input id="updateTime" name="updateTime" type="hidden" value="${innermsgEntityPage.updateTime }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								内容:
							</label>
						</td>
						<td class="value">
						     	 <input id="content" name="content" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.content}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="sendtime" name="sendtime" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.sendtime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送时间</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送人:
							</label>
						</td>
						<td class="value">
						     	 <input id="sender" name="sender" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.sender}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接收人:
							</label>
						</td>
						<td class="value">
						     	 <input id="reciever" name="reciever" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.reciever}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								邮箱:
							</label>
						</td>
						<td class="value">
						     	 <input id="mailbox" name="mailbox" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${innermsgEntityPage.mailbox}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/inzyme/p2p/com/inzyme/demo/msg/innermsgEntity.js"></script>		