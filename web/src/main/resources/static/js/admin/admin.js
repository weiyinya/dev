/**
 * Created by Administrator on 2017/9/12.
 */
function check_login() {
    if ($("#username").val() == "") {
        alert("用户名不能为空");
        return;
    }
    if ($("#password").val() == "") {
        alert("密码不能为空");
        return;
    }
    if ($("#auth").val() == "") {
    	alert("验证码不能为空");
    	return;
    }
    $.post("login", {
        "username" : trim($("#username").val()),
        "password" : trim($("#password").val()),
        "auth" : trim($("#auth").val()),
    }, function(data) {
        if (data == 0) {
            //校验通过
            location.href = "./success.html";
        } else if (data == 1) {
            //账号错误
            alert("账号不存在.");
        } else if (data == 2) {
            //密码错误
            alert("密码错误.");
        } else if (data == 3) {
        	//密码错误
        	alert("验证码错误.");
        }
    });
}

$(document).keydown(function(event){
    if(event.keyCode == 13){ //绑定回车
        $('#login-submit').click();
    }
});