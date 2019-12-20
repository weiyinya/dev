//实现动态获取信息
function setrole(id) {
	$.post('../admin/toUpdate.do', {
		"id" : id
	}, function(data) {
        var data = eval('(' + data + ')');
		$("#adminId").val(data.id);
		$("#nikename").val(data.nikename);
		$("#username").val(data.username);
		$("#password").val(data.password);
		selectValue("rolesId", data.rolesId);
		showModal("adminAdd");
	});

}

function upEmpty() {
	$("#adminId").val("");
	$("#nikename").val("");
	$("#username").val("");
	$("#password").val("");
	selectValue("rolesId", "");
}

function save() {
	var nikename = $("#nikename").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var rolesId = $("#rolesId").val();
	if (nikename == '' || username == '' || password == '' || rolesId == '') {
		showErrorMsg("参数不能为空");
	} else {
		$.post("checkUsername.do", {
			"id" : $("#adminId").val(),
			"username" : username
		}, function(data) {
			if (data) {
				$.post("save.do", {
					"id" : $("#adminId").val(),
					"nikename" : nikename,
					"username" : username,
					"password" : password,
					"rolesId" : rolesId
				}, function(data) {
					upEmpty();
					hideModal("adminAdd");
					if (data) {
						showMsg("操作成功");
						refreshTable("adminTable", "list.do");
					} else {
						showErrorMsg("操作失败，请联系管理员");
					}
				});
			} else {
				showErrorMsg("有相同账号存在");
			}
		});
		
	}
}