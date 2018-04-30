function getType(appCodeSelect,selectName,typecode){
   	$.ajax({
		type : "post",
		url : "advertController.do?getType",
		data : {
			typeCode : typecode
		},
		dataType : "json",
		success : function(data) {
			$.each(data.attributes, function (i, item) {
				$("#"+selectName).append("<option value="+i+">"+item+"</option>");
			});
			$("#"+selectName).val(appCodeSelect);
		}
	});
}

function showName(){
    var appCode = $("#appcode").val();
    if(appCode == "" || appCode == null){
    	$("#appname").val("");
    	return;
    }
    $.ajax({
		type : "post",
		url : "appissueController.do?getAppName",
		data : {
			typeGroupCode : "appCn",
			typeCode : appCode
		},
		dataType : "json",
		success : function(data) {
			$("#appname").val(data.obj);
		}
	});
}