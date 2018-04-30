$(function(){
	$.ajax( {
		type : "post",
		url : "cpensatoryappController.do?getdebtid",
		data : {
			
		},
		dataType : "json",
		success : function(data) {
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
});