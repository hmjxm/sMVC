$(function() {
	// 绘制左边树
	$("#msgTree").tree({
		data: [{
				id: '-1',
				text: '消息',
				state: 'open',
				children: [{
		            id: "0",
					text: '系统公共邮箱'
			    },{
			  	    id: "1",
					text: '个人收件箱'
			    }]
		}],
		onLoadSuccess : function() {

		},
		onSelect : function(node) {
			if(node.id == -1){
			   return;
			}else{
			   $("#innermsgList").datagrid("reload", {
						mailbox : node.id
			   });
			}
		}
	});

});



function addMsg(){
    createwindow("发送消息", "innermsgController.do?goAdd", 800, 500);
}

function detail(){
    var node = $('#innermsgList').datagrid('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请选择一封邮件查看");
		return;
	}
	changeStatus(node);
	openwindow("修改", "innermsgController.do?goDetail&id=" + node.id,"",800, 500);
	
}

function changeStatus(node){
    $.ajax({
			type : "post",
			url : "innermsgController.do?changeStatus",
			data : {id:node.id},
			dataType : "json",
			success : function() {
				$("#innermsgList").datagrid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				tip("操作失败");
			}
	});
}