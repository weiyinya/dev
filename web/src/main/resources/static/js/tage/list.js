function orderNo(value, row, index) {
	  return index+1;
	}
function onFormatter(value, row, index) {
	var arr = ['<button class="btn btn-default btn-xs updateBut" type="button" onclick="toUpdate('+row.id+','+row.parentId+')"  disabled="disabled" href="javascript:void(0)" title="修改" >',
		           '<i class="fa fa-pencil"></i>',
		           '</button>',
		           (tageParentTableId==""?
		           ('<button class="btn btn-default btn-xs" style="margin-left: 10px;" type="button" onclick="toParent('+row.id+')" href="javascript:void(0)" title="配置" >'+
                   '<i class="fa fa-cogs"></i>'+
                   '</button>')
                   :
            	   ('<button class="btn btn-default btn-xs" style="margin-left: 10px;" type="button" onclick="del('+row.id+')" href="javascript:void(0)" title="配置" >'+
                   '<i class="glyphicon glyphicon-trash"></i>'+
                   '</button>'))
		           ];
	return arr.join('');
}

function save() {
	var name = $("#tageName").val();
	if (name!="") {
		$.post("save.do", {
			"id" : $("#tageId").val(),
			"parentId" : $("#tageParentTableId").val(),
			"name" : $("#tageName").val(),
			"describe" : $("#tageDescribe").val(),
		}, function(data) {
			refreshTable("tageTable", "findAll.do?parentId=" + $("#tageParentTableId").val());
			hideModal("tageAdd");
			upEmpty();
			if (data) {
				showMsg("操作成功");
			} else {
				showErrorMsg("操作失败");
			}
		});
	} else {
		showErrorMsg("名称不能为空");
	}
}

function del(id) {
	$.post("delete.do", {
		"id" : id
	}, function (data) {
		var url = tageParentTableId==""? "findAll.do" :"findAll.do?parentId=" + tageParentTableId;
		refreshTable("tageTable", url);
		if (data) {
			showMsg("操作成功");
		} else {
			showErrorMsg("操作失败");
		}
	});
}

function saveParent() {
	var name = $("#tageParentName").val();
	if (name!="") {
		$.post("save.do", {
			"id" : $("#tageParentId").val(),
			"describe" : $("#tageParentDescribe").val(),
			"name" : $("#tageParentName").val(),
			"tableId" : $("#tageParentTabel").val()
		}, function(data) {
			refreshTable("tageTable", "findAll.do");
			hideModal("tageAddParent");
			upEmpty();
			if (data) {
				showMsg("操作成功");
			} else {
				showErrorMsg("操作失败");
			}
		});
	} else {
		showErrorMsg("名称不能为空");
	}
}

function toUpdate(id, parentId) {
	if (parentId != null) {
		$.post("findById.do", {
			"id" : id,
		}, function(data) {
			showModal("tageAdd");
			if (data != null) {
				$("#tageId").val(data.id);
				$("#tageName").val(data.name);
				$("#tageDescribe").val(data.describes);
			} else {
				showErrorMsg("查询错误，id不存在");
			}
		});
	} else {
		$.post("findById.do", {
			"id" : id,
		}, function(data) {
			showModal("tageAddParent");
			if (data != null) {
				$("#tageParentId").val(data.id);
				$("#tageParentName").val(data.name);
				$("#tageParentDescribe").val(data.describe);
				selectValue("tageParentTabel", data.tableId);
			} else {
				showErrorMsg("查询错误，id不存在");
			}
		});
		
	}
}

function upEmpty() {
	$("#tageId").val("");
	$("#tageName").val("");
	$("#tageDescribe").val("");
}
function upEmptyParent() {
	$("#tageParentId").val("");
	$("#tageParentName").val("");
	$("#appcountry").val("");
	$("#tageParentDescribe").val("");
	selectValue("tageParentTabel", "");
}
var tageParentTableId = "";
function toParent(parentId) {
	tageParentTableId = parentId;
	$("#tageParentTableId").val(parentId);
	refreshTable("tageTable", "findAll.do?parentId=" + parentId);
}

function showAdd() {
	if ($("#tageParentTableId").val() != "") {
		showModal("tageAdd");
	} else {
		showModal("tageAddParent");
	}
}
