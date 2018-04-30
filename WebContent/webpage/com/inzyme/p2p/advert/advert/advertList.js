function addAdvert(){
   createwindow("添加", "advertController.do?goAdd", 800, 500);
}

function updateAdvert(){
    var node = $('#advertList').datagrid('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请先选择一条数据");
		return;
	}
   createwindow("添加", "advertController.do?goAdd&id="+node.id, 800, 500);
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
		url : "advertController.do?saveStatus",
		data : {
			advertId : id,
			status : status
		},
		dataType : "json",
		success : function(data) {
			tip(data.msg);
			$("#advertList").datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
}