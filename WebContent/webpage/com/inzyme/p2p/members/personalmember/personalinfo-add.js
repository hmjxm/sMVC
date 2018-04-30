$(function() {
	$("#firstname,#lastname").bind("keyup", function() {
		$("#fullname").val($("#firstname").val() + $("#lastname").val());
	});
});
var clicktime = 0;
function addordel() {
	var $addrows = $("<tr id='loginsiterow'>"
			+ "<td align='right'>"
			+ "<label class='Validform_label'>第三方登录站点:</label>"
			+ "</td>"
			+ "<td class='value'>"
			+ "<input id='thirdloginsite' name='thirdloginsite' type='text' style='width: 150px' class='inputxt'>"
			+ "</td>"
			+ "</tr>"
			+ "<tr id='loginnamerow'>"
			+ "<td align='right'>"
			+ "<label class='Validform_label'>第三方登录账号:</label>"
			+ "</td>"
			+ "<td class='value'>"
			+ "<input id='thirdloginname' name='thirdloginname' type='text' style='width: 150px' class='inputxt'>"
			+ "</td>" + "</tr>");
	var yesorno = $(":radio:checked").val();
	if (yesorno == "yes" && clicktime == 0) {
		$("#isthirdloginrow").after($addrows);
		clicktime = 1;
	} else if (yesorno == "no") {
		$("#loginsiterow").remove();
		$("#loginnamerow").remove();
		clicktime = 0;
	}
}
function uniqchk() {
	var loginname = $("#loginname").val();
	$.ajax( {
		type : "post",
		url : "personalinfoController.do?isexist",
		data : {
			loginname : loginname
		},
		dataType : "json",
		success : function(data) {
			tip(data.msg);
			if (data.msg == "用户名已存在") {
				$("#loginname").val("");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
}