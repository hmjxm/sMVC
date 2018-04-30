//添加
function addApp(){
   //url="appissueController.do?goAdd"
    createwindow("添加", "appissueController.do?goAdd", 700, 300); 
}

function updateApp(){
    var node = $('#appissueList').datagrid('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请先选择一条数据");
		return;
	}
	createwindow("添加", "appissueController.do?goUpdate&id="+node.id, 700, 300); 
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
		url : "appissueController.do?saveStatus",
		data : {
			appissueId : id,
			status : status
		},
		dataType : "json",
		success : function(data) {
			tip(data.msg);
			$("#appissueList").datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
}