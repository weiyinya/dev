function orderNo1(value, row, index) {
	return index + 1;
}
function operateFormatter(value, row, index) {
	return [
			'<button title="修改" class="btn btn-default btn-xs updateBut" data-backdrop="static" data-toggle="modal" data-target="#update" type="button" onclick="updaterole('
					+ row.id + ')" disabled="disabled">',
			'<i class="fa fa-pencil"></i>',
			'</button>',
			'<button title="设置权限" style="margin-left: 10px;" class="btn btn-default btn-xs" type="button" onclick="hrefToRoleMenus('
			+ row.id + ')">',
			'<i class="fa fa-sitemap"></i>',
			'</button>',
			row.id > 1 ? '<button title="删除" style="margin-left: 10px;" class="btn btn-default btn-xs deleteBut" type="button" onclick="del('
					+ row.id + ')" disabled="disabled">'
					: '<button title="删除" style="margin-left: 10px;" class="btn btn-default btn-xs deleteBut" type="button" disabled>',
			'<i class="fa fa-trash-o"></i>', '</button>' ].join('');
}

//删除
function del(id) {
	var r = window.confirm("确定要删除该条信息吗？");
	if (r) {
		$.post("checkRoles.do?id=" + id,
				function(data) {
					if (data) {
					$.post("del.do?id=" + id, 
							function(date) {
								if (date) {
									showMsg("操作成功");
									refreshTable("tbl", "list.do");
								} else {
									showErrorMsg("操作失败，请联系管理员");
								}
						});
					} else {
						showErrorMsg("操作失败，正有用户使用这个角色");
					}
		});
	}
}
function showmessage(text) {
	if ($('#top_alert span').text().length > 0) {
		$('#top_alert').empty().append('<span>' + text + '</span>');
		$('#top_alert').css('display', 'block');
	} else {
		$('.content')
				.prepend(
						'<div style="z-index:9999;width:250px;margin-top:25%;padding:10px 0;background:#fefcef;border:1px solid #f8efb0;color:#e69041;position:absolute;text-align:center;left:30%;top:0;" id="top_alert"><span>'
								+ text + '</span></div>');

	}
}

function hrefToRoleMenus(id) {
	location.href = "role_menu.jsp?roleId="+id;
}

