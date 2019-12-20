function addError(divID, str) {
	var info = divID + "_info";
	document.getElementById(info).style.dispaly="";
	document.getElementById(info).style.color="red";
	document.getElementById(info).style.font="bold 12px/22px 宋体";
	document.getElementById(info).style.margin="-15px 0 0 0";
	document.getElementById(info).innerHTML=str;
}

function removeError(divID) {
	var info = divID + "_info";
	document.getElementById(info).innerHTML="";
	document.getElementById(info).style.margin="0";
}

/**
 * obj:校验的对象
 * str:校验结果的提示语
 * che:校验的结果
 */
function setPrompt(obj,str){
	var info = obj.id+"_info";
	obj.setCustomValidity(str);
}

function activeFlag() {
	//加上选中的菜单对应的权限
	if (typeof(activeMenu) != "undefined") {
		activeMenu.saveRole ? 
				$(".saveBut").removeAttr("disabled")
			:
				$(".saveBut").attr("disabled","disabled");
		activeMenu.updateRole ? 
				$(".updateBut").removeAttr("disabled")
			:
				$(".updateBut").attr("disabled","disabled");
		activeMenu.deleteRole ? 
				$(".deleteBut").removeAttr("disabled")
			:
				$(".deleteBut").attr("disabled","disabled");
		activeMenu.publishRole ? 
				$(".publishBut").removeAttr("disabled")
			:
				$(".publishBut").attr("disabled","disabled");
	}
}

function checkLogin() {
	var url = window.top.document.URL;
	url = url.substring(0,url.lastIndexOf('/'));
	url = url.substring(0,url.lastIndexOf('/')) + '/admin/checklogin.do';
	$.get(url, function(data) {
		if (!data) {
			window.location.href = "/login.html";   
		}
	})
}