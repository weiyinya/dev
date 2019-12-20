function orderNo(value, row, index) {
	  return index+1;
	}
function onFormatter(value, row, index) {
	var arr = [
	           '<a  class="btn btn-default btn-xs " style="margin-left: 10px;" data-toggle="modal" data-backdrop="static" data-target="#toUpdate" type="button" onclick="findByID('+row.id+')" href="javascript:void(0)" title="修改" >',
		           '<i class="fa fa-pencil"></i>',
		           '</a>',
		           index > 6 ?
		           ('<a  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')">',
		           '<i class="glyphicon glyphicon-trash"></i>',
		           '</a>'):"",
		           ];
	return arr.join('');
}

function saveField() {
	var fieldName = $("#fieldName").val();
	var field = $("#field").val();
	var type = $("#fieldSel").val();
	if (field != '' && type != '' && fieldName != '') {
		$.post('insertTableField.do', {
			'fieldName' : fieldName,
			"field" : field,
			"type" : type,
			"tableId" : GetQueryString().tableId
		}, function(data) {
			refreshTable("contentTypeTable", "contentTypeList.do?tableTypeId="+GetQueryString().tableId);
			hideModal("addField");
			saveEmpty();
			if (data) {
				showMsg("添加成功");
			} else {
				showErrorMsg("添加失败");
			}
		});
	} else {
		showErrorMsg("参数不能为空");
	}
}

function saveEmpty() {
	$("#fieldName").val("");
	$("#field").val("");
	selectValue("fieldSel", "");
}

function findByID(id) {
	$.post("getContentType.do?id="+id, function(data) {
		$("#upFieldId").val(id);
		$("#upFieldName").val(data.fieldName);
		showModal("updateField");
	});
	
}

function updateField() {
	var fieldName = $("#upFieldName").val();
	if (fieldName != '') {
		$.post("updateType.do", {
			"id" : $("#upFieldId").val(),
			"fieldName" : fieldName
		}, function (data) {
			refreshTable("contentTypeTable", "contentTypeList.do?tableTypeId="+GetQueryString().tableId);
			hideModal("updateField");
			if (data) {
				showMsg("修改成功");
			} else {
				showErrorMsg("修改失败");
			}
		});
	} else{
		showErrorMsg("参数不能为空");
	}
}

function deleteById(id) {
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.post("deleteTableField.do", {
			"fieldId" : id
		}, function(data) {
			refreshTable("contentTypeTable", "contentTypeList.do?tableTypeId="+GetQueryString().tableId);
			if (data) {
				showMsg("删除成功");
			} else {
				showErrorMsg("删除失败");
			}
		});
	}
}