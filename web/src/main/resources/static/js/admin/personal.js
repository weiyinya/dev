//实现动态获取信息
function setPersonal(id) {
	$.post('../admin/toUpdate.do', {
		"id" : id
	}, function(data) {
        var data = eval('(' + data + ')');
		$("#adminId").val(data.id);
		$("#personal_username").val(data.username);
		$("#personal_nikename").val(data.nikename);
		showModal("personal");
	});

}

function personalEmpty() {
	$("#adminId").val("");
	$("#nikename").val("");
	$("#username").val("");
	$("#oldPassword").val("");
	$("#password1").val("");
	$("#password2").val("");
}

function savePersonal() {
	var nikename = $("#personal_username").val();
	var username = $("#personal_nikename").val();
	var oldPassword = $("#oldPassword").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if (nikename == '' || username == '' || oldPassword == '' || password1 == '' || password2 == '') {
		showErrorMsg("参数不能为空");
	} else if (password1 != password2){
		showErrorMsg("新密码输入不一致");
	} else {
		//比较密码是否正确
		$.post("checkPassword.do", {
			"id" : $("#adminId").val(),
			"password" : oldPassword
		}, function(data) {
			if (data) {
				$.post("save.do", {
					"id" : $("#adminId").val(),
					"nikename" : nikename,
					"password" : password1,
				}, function(data) {
					personalEmpty();
					hideModal("personal");
					if (data) {
						showMsg("操作成功");
						location.href="../admin/login.jsp";
					} else {
						showErrorMsg("操作失败，请联系管理员");
					}
				});
			} else {
				showErrorMsg("旧密码不正确");
			}
		});
		
	}
}