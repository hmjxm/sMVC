function getType(typeSelect,selectName,typecode){
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
			$("#"+selectName).val(typeSelect);
		}
	});
}