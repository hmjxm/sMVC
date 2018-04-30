$(function() {
	$('#fileupload').fileupload( {
		dataType : 'json',
		done : function(e, data) {
			$.each(data.result, function(index, file) {
				$("#headimg").val(file.headimg);
			});
		},
		progressall : function(e, data) {
		},
		dropZone : $('#dropzone')
	});
});
