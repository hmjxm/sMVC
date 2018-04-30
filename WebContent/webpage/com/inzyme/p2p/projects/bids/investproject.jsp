<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>投资项目表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="investprojectController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${investprojectPage.id }">
					<input id="createUser" name="createUser" type="hidden" value="${investprojectPage.createUser }">
					<input id="createTime" name="createTime" type="hidden" value="${investprojectPage.createTime }">
					<input id="updateUser" name="updateUser" type="hidden" value="${investprojectPage.updateUser }">
					<input id="updateTime" name="updateTime" type="hidden" value="${investprojectPage.updateTime }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							投资标的种类:
						</label>
					</td>
					<td class="value">
					     	 <input id="bidtypeid" name="bidtypeid" type="text" style="width: 150px" class="inputxt"  
								                 value="${investprojectPage.bidtypeid }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投资标的种类</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="titles" name="titles" type="text" style="width: 150px" class="inputxt"  
								                  value="${investprojectPage.titles }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							借款人的会员ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="menberid" name="menberid" type="text" style="width: 150px" class="inputxt"  
								               value="${investprojectPage.memberid }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款人的会员ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							用途:
						</label>
					</td>
					<td class="value">
					     	 <input id="usages" name="usages" type="text" style="width: 150px" class="inputxt"  
								               value="${investprojectPage.usages }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用途</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="amounts" name="amounts" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.amounts }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							利率:
						</label>
					</td>
					<td class="value">
					     	 <input id="interestrate" name="interestrate" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.interestrate }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计息名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="interesttypeid" name="interesttypeid" type="text" style="width: 150px" class="inputxt"  
								                 value="${investprojectPage.interesttypeid }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计息名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							参数:
						</label>
					</td>
					<td class="value">
					     	 <input id="args" name="args" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.args }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							还款期限:
						</label>
					</td>
					<td class="value">
					     	 <input id="returnterm" name="returnterm" type="text" style="width: 150px" class="inputxt"  
								                  value="${investprojectPage.returnterm }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款期限</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  
								               value="${investprojectPage.description }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							借款申请ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="loanapplyid" name="loanapplyid" type="text" style="width: 150px" class="inputxt"  
								                 value="${investprojectPage.loanapplyid }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款申请ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							投标期限:
						</label>
					</td>
					<td class="value">
					     	 <input id="bidterm" name="bidterm" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.bidterm }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投标期限</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							投标发布日:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuedate" name="issuedate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" 
								                value="${investprojectPage.issuedate }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投标发布日</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							记账日:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountdate" name="accountdate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"
								                  value="${investprojectPage.accountdate }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">记账日</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
								               value="${investprojectPage.status }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							已投标金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="biddenamount" name="biddenamount" type="text" style="width: 150px" class="inputxt"  
								                   value="${investprojectPage.biddenamount }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已投标金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							已还本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="paidprincipal" name="paidprincipal" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.paidprincipal }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已还本金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							已还利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="paidinterest" name="paidinterest" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.paidinterest }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已还利息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							待还本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="payableprincipal" name="payableprincipal" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.payableprincipal }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">待还本金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							待还利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="payableinterest" name="payableinterest" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.payableinterest }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">待还利息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号:
						</label>
					</td>
					<td class="value">
					     	 <input id="versions" name="versions" type="text" style="width: 150px" class="inputxt"  
								                value="${investprojectPage.versions }"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本号</label>
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
  <script src = "webpage/com/inzyme/p2p/projects/bids/investproject.js"></script>		