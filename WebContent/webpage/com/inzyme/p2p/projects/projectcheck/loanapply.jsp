<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>融资申请</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
	function getarg(id) {
		if (id == '') {
			$('#demo').html("");
			return;
		}

		var url = "loanapplyController.do?getArg&id="
				+ encodeURIComponent(encodeURIComponent(id));
		$.ajax({
			type : 'POST',
			url : url,
			success : function(data) {
				createShowingTable(data);
			}
		});
	}

	//动态的创建一个table，同时将后台获取的数据动态的填充到相应的单元格
	function createShowingTable(data) {
		//获取后台传过来的jsonData,并进行解析
		var dataArray = $.parseJSON(data);

		var args = $("#args").val();
		var strs = new Array();
		strs = args.split(";");
		//此处需要让其动态的生成一个table并填充数据
		var tableStr = "<table>";
		tableStr = tableStr + "<thead><td>参数名</td><td>参数</td></thead>";
		var len = dataArray.length;
		var interesttypeid = "${loanapplyPage.interesttypeid}";

		for ( var i = 0; i < len; i++) {
			if (interesttypeid == dataArray[i].interesttypeid) {
				tableStr = tableStr
						+ "<tr><td >"
						+ dataArray[i].argnames
						+ "</td>"
						+ "<td><input type='text'name='arg' id='arg' value="+strs[i]+"> </td></tr>";
			} else {
				tableStr = tableStr
						+ "<tr><td >"
						+ dataArray[i].argnames
						+ "</td>"
						+ "<td><input type='text'name='arg' id='arg' > </td></tr>";
			}
		}

		tableStr = tableStr + "</table>";
		//将动态生成的table添加的事先隐藏的div中.
		$("#demo").html(tableStr);
	}
	function say() {
		var valArr = new Array;
		$("input[name='arg']").each(function(i) {
			valArr[i] = $(this).val();
		});
		var priv = valArr.join(';');
		$("#args").val(priv);
	}

	//动态的创建一个table，同时将后台获取的数据动态的填充到相应的单元格
	function createTable() {
		var id = $("#inid").val();

		getarg(id);
	}
</script>
</head>
<body onload="createTable();">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		beforeSubmit="say();" layout="div"
		action="loanapplyController.do?save">
		<input id="id" name="id" type="hidden" value="${loanapplyPage.id }">
		<input id="args" name="args" type="hidden"
			value='${loanapplyPage.args}'>
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"> 标题: </label> <input name="titles"
					id="titles" class="inputxt" value="${loanapplyPage.titles}"
					datatype="s2-50"> <span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 标的种类: </label> <select
					id="bidtypeid" name="bidtypeid">
					<c:forEach items="${bidTypeList}" var="bid">
						<option value="${bid.id }"
							${bid.id==loanapplyPage.bidtypeid?'selected':''}>${bid.bidtypename}</option>
					</c:forEach>
				</select> <span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 会员名称: </label> <select id="memberid"
					name="memberid">
					<c:forEach items="${personTypeList}" var="person">
						<option value="${person.id }"
							${person.id==loanapplyPage.memberid?'selected':''}>${person.fullname}</option>
					</c:forEach>
				</select> <span class="Validform_checktip"></span>
			</div>


			<div class="form">
				<label class="Validform_label"> 用途: </label> <input name="usages"
					id="usages" class="inputxt" value="${loanapplyPage.usages}"
					datatype="s2-50"> <span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 金额: </label> <input name="amounts"
					id="amounts" class="inputxt" value="${loanapplyPage.amounts}"
					datatype="*"> <span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 计息方式: </label>
				<c:if test="${functype != '1'}">
					<select id="interesttypeid" name="interesttypeid"
						onchange="getarg(this.value);">
						<option vlaue="">请选择</option>
						<c:forEach items="${itTypeList}" var="it">
							<option value="${it.id }"
								${it.id==loanapplyPage.interesttypeid?'selected':''}>${it.inerestname}</option>
						</c:forEach>
						<input id="inid" name="inid" type="hidden" value='${inid}'>
					</select>
				</c:if>
				<c:if test="${functype != '2'}">
					<select id="interesttypeid" name="interesttypeid"
						onchange="getarg(this.value);">
						<option vlaue="">请选择</option>
						<c:forEach items="${itTypeList}" var="it">
							<option value="${it.id }"
								${it.id==loanapplyPage.interesttypeid?'selected':''}>${it.inerestname}</option>
						</c:forEach>
					</select>
				</c:if>


				<span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 计息参数: </label>

				<div id="demo"></div>

				<span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 利率: </label> <input name="rate"
					id="rate" class="inputxt" value="${loanapplyPage.rate}"
					datatype="s2-50"> <span class="Validform_checktip"></span>
			</div>

			<div class="form">
				<label class="Validform_label"> 还款期限: </label> <input
					name="returnterm" id="returnterm" class="inputxt"
					value="${loanapplyPage.returnterm}" datatype="s2-50"> <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"> 描述: </label>
				<textarea rows="15" cols="80" name="description" id="description">${loanapplyPage.description}</textarea>
				<span class="Validform_checktip"></span>
			</div>


		</fieldset>
	</t:formvalid>
</body>
<script src="webpage/com/inzyme/p2p/projects/projectcheck/loanapply.js"></script>