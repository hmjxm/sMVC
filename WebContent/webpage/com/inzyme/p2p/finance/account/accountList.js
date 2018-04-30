$(document).ready(function() {
	var li_east = 0;
		// 给时间控件加上样式
	});

function detail(accountId) {
	if (li_east == 0) {
		$("#system_function_functionList").layout("expand", "east");
	}
	$("#transcationpanel").panel("refresh",
			"transcationController.do?transcation&accountId=" + accountId);
}

function reloadAccount() {
	$("#accountList").datagrid("reload");
}

// 发布 设置状态为0
function isStatusFalse(id) {
	var status = "0";
	isStatus(id, status);
}
// 禁用设置状态为1
function isStatusTrue(id) {
	var status = "1";
	isStatus(id, status);
}

function isStatus(id, status) {
	$.ajax({
		type : "post",
		url : "accountController.do?saveStatus",
		data : {
			accountId : id,
			status : status
		},
		dataType : "json",
		success : function(data) {
			tip(data.msg);
			$("#accountList").datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
}