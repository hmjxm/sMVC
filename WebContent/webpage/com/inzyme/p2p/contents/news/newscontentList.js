var iframe;
// 页面初始化
$(function() {
			// 绘制左边树
			$("#cateLogTree").tree({
						url : "newscatelogController/getCatelogTree.do",
						method : 'post',
						onLoadSuccess : function() {

						},
						onSelect : function(node) {
							$("#contentList").datagrid("reload", {
										catelogId : node.id
									});
						}
					});

		});

function refreshCataLogTree() {

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
		url : "newscontentController.do?saveStatus",
		data : {
			contentId : id,
			status : status
		},
		dataType : "json",
		success : function(data) {
			tip(data.msg);
			$("#contentList").datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
}

function addContent() {
	var node = $('#cateLogTree').tree('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请先选择新闻栏目");
		return;
	}
	createwindow("添加", "newscontentController.do?addorupdate&catelogId="
					+ node.id + "&catelogtitle=" + node.text, 800, 500);
}

function opendialog() {
	var node = $('#cateLogTree').tree('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请先选择新闻栏目");
		return;
	}
	$.dialog({
		content : "url:"+"newscontentController.do?addorupdate&catelogId=" + node.id + "&catelogtitle=" + node.text,
		lock : true,
		width : 800,
		height : 500,
		title : "添加",
		opacity : 0.3,
		cache : false,
        ok : function() {
        	iframe = this.iframe.contentWindow;
        	$('#btn_sub', iframe.document).click();
            //$("#contentList").datagrid("reload");
            //$(this).dialog('close');
            return false;
        },
		cancelVal : '关闭',
		cancel : true
    })

}

function updateContent() {
	var node = $('#cateLogTree').tree('getSelected');
	if (node == null) {
		$.messager.alert("提示消息", "请先选择新闻栏目");
		return;
	}

	var nodeGrid = $("#contentList").datagrid("getSelected");
	if (nodeGrid == null) {
		$.messager.alert("提示信息", "请选择一条新闻");
		return;
	}
	var contentId = nodeGrid.id;
	createwindow("修改", "newscontentController.do?addorupdate&id=" + contentId
					+ "&catelogId=" + node.id + "&catelogtitle=" + node.text,
			800, 500);
}

function moveup() {
	move(true);
}

function movedown() {
	move(false);
}

function move(isUp) {
	var dt = $("#contentList");
	var selections = dt.datagrid("getSelections");
	if (selections.length == 0) {
		return;
	}
	if (selections.length > 1) {
		$.message.show("一次只能移动一条数据。", "warning", 2);
		return;
	}
	var $view = $('div.datagrid-view');
	var index = dt.datagrid('getRowIndex', selections[0]);
	var $row = $view.find('tr[datagrid-row-index=' + index + ']');
	if (isUp) {
		$row.each(function() {
					var prev = $(this).prev();
					prev.length && $(this).insertBefore(prev);
				});
	} else {
		$row.each(function() {
					var next = $(this).next();
					next.length && $(this).insertAfter(next);
				});
	}
}
