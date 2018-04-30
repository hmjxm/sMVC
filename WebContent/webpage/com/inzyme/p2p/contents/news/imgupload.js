$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result, function (index, file) {
                $("#titleimg").val(file.titleimg);
                $("#titleimgwidth").val(100);
                $("#titleimgheight").val(100);
            });
        },
        progressall: function (e, data) {
   		},
		dropZone: $('#dropzone')
    });
});