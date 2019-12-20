function orderNo(value, row, index) {
	  return index+1;
	}
function onFormatter(value, row, index) {
	var arr = [
	           '<a  class="btn btn-default btn-xs" href="javascript:void(0)" onclick="details('+row.id+', '+"'"+row.tableName+"'"+')" title="预览">',
	           '详情',
	           '</a>',
		           '<a  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')">',
		           '<i class="glyphicon glyphicon-trash"></i>',
		           '</a>',
		           ];
	return arr.join('');
}

function details(id, tableName) {
	location.href='list_contentType.jsp?menuId=6&tableId=' + id + "&tableName=" + (encodeURIComponent(tableName));
}

function deleteById(id) {
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.post("deleteTable.do", {
			"tableId" : id
		}, function(data) {
			refreshTable("tableTypeTable", "tableTypeList.do");
			if (data) {
				showMsg("删除成功");
			} else {
				showErrorMsg("删除失败");
			}
		});
	}
}

function saveTable() {
	var table = $("#table").val();
	var tableName = $("#tableName").val();
	if(table != "" && tableName != "") {
		$.post("setContent.do", {
			"table":table,
			"tableName" : tableName
		}, function (data) {
			refreshTable("tableTypeTable", "tableTypeList.do");
			hideModal("add");
			empty();
			if (data) {
				showMsg("添加成功");
			} else {
				showErrorMsg("添加失败");
			}
		});
	}
}

function empty() {
	$("#table").val("");
	$("#tableName").val("");
}