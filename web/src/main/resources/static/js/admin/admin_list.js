function orderNo(value, row, index) {
	  return index+1;
	}
function onFormatter(value, row, index) {
	var arr = ['<button class="btn btn-default btn-xs updateBut" data-toggle="modal" data-backdrop="static" data-target="#toUpdate" type="button" onclick="setrole('+row.id+')"  disabled="disabled" href="javascript:void(0)" title="修改" >',
		           '<i class="fa fa-pencil"></i>',
		           '</button>',
		           row.id > 1 ? '<button class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')"  disabled="disabled">' +
				           '<i class="glyphicon glyphicon-trash"></i>' +
				           '</button>' :
		           '<button class="btn btn-default btn-xs" style="margin-left: 10px;" disabled href="javascript:void(0)" disabled="disabled">' +
		           '<i class="glyphicon glyphicon-trash"></i>' +
		           '</button>'
		           ];
	return arr.join('');
}


function deleteById(id) {
	$.post("del.do", {
		"id" : id
	}, function(data) {
		refreshTable("adminTable", "list.do");
		if (data) {
			showMsg("删除成功");
		} else {
			showErrorMsg("删除失败");
		}
	});
}


