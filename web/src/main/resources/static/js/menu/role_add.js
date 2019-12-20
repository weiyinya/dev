//添加
//权限全选
function toogle_checkbox(el, id) {
	var spanel = $('#table' + id);
	var checked = el.checked;
	spanel.find('input[type=checkbox]').each(function() {
		var checkboxel = this;
		checkboxel.checked = checked;
	});
	parentcheck(id);
}
//子类有选中，父权限设置选中
function parentcheck(id) {
	var spanel = $("#table" + id).find("input[type=checkbox]:checked").size();
	if (spanel > 0) {
		$("#rol" + id).attr("name", "rol");
	} else {
		$("#rol" + id).removeAttr("name");
		$("#input" + id).removeAttr("checked");
	}
}

//上级id
function addParentId(id) {
	$("#parentrole").val(id);
}
var descri_flag = false;
function check_descri() {
	var name = document.getElementById("roldescri");
//	name_flag = trimFrom(name,"输入名称不能为空");
//	if(name_flag){
		descri_flag = CHNFrom(name, "请输入汉字");
		if(descri_flag){
			setPrompt(name, "", descri_flag);
	}
}
function addCheck() {
	var num = [];
	var per = [];
	$('input[name="rolAdd"]:checked').each(function(){
	    num.push($(this).val());
	    var s = $(this).parent().parent().parent().parent().prev().val();
	    if (per.indexOf(s) == -1) {
	    	per.push(s);
	    }
	  });
	num.push(per);
	if (num.length > 0) {
		choose_flag = true;
	} else {
		choose_flag = false;
	}
	if (choose_flag) {
		$.post("save.do", {
			"name" : $("#rolname").val(),
			"descri" : $("#roldescri").val(),
			"rols" : num.join(",")
		}, function (data) {
			hideModal("add");
			if (data) {
				showMsg("修改成功");
				refreshAddTable("tbl", "../roles/list.do");
			} else {
				showErrorMsg("修改失败");
			}
		});
	}
}

function addEmpty() {
	$("#parentrole").val("");
	$("#rolname").val("");
	$("#roldescri").val("");
	$("#choose").find('input[type=checkbox]').each(function() {
		$(this).removeAttr("checked");

	});
	$("#choose").find('input .par').each(function() {
		$(this).removeAttr("name");
	});

}

function showmessage(text) {
	if ($('#top_alert span').text().length > 0) {
		$('#top_alert').empty().append('<span>' + text + '</span>');
		$('#top_alert').css('display', 'block');
	} else {
		$('.content')
				.prepend(
						'<div style="z-index:9999;width:250px;margin-top:25%;padding:10px 0;background:#fefcef;border:1px solid #f8efb0;color:#e69041;position:absolute;text-align:center;left:30%;top:0;" id="top_alert"><span>' + text + '</span></div>');

	}
}

//设置下拉框默认值
function selectValue(sId, value) {
	var s = document.getElementById(sId);
	var ops = s.options;
	for ( var i = 0; i < ops.length; i++) {
		var tempValue = ops[i].value;
		if (tempValue == value) {
			ops[i].selected = true;
		}
	}
}
