$(function() {
	var yesorno = $("#isthird").val();
	var ch = "checked";
	if (yesorno == "yes") {
		$("#isthirdlogin1").attr("checked", ch);
	} else {
		$("#isthirdlogin2").attr("checked", ch);
		$("#thirdloginsite").attr("onfocus", "this.blur()");
		$("#thirdloginname").attr("onfocus", "this.blur()");
	}
	$("#firstname,#lastname").bind("keyup", function() {
		$("#fullname").val( $("#firstname").val()+$("#lastname").val());
	});
});

function aboren() {
	var yesorno = $(":radio:checked").val();
	if (yesorno == "no") {
		$("#thirdloginsite").val("");
		$("#thirdloginname").val("");
		$("#thirdloginsite").attr("onfocus", "this.blur()");
		$("#thirdloginname").attr("onfocus", "this.blur()");
	} else {
		$("#thirdloginsite").attr("onfocus", "this.focus()");
		$("#thirdloginname").attr("onfocus", "this.focus()");
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
function getpcd() {

}