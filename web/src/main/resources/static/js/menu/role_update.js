//修改
//实现动态获取信息
function updaterole(id) {

	$.post('toUpdate.do', {
				"id" : id
			}, function(data) {
				var data = eval('(' + data + ')');
				$("#uprolid").val(data.id);
				$("#uprolname").val(data.name);
				$("#origname").val(data.name);
				$("#uproldescri").val(data.descri);
			}	);

}
//权限全选
function uptoogle_checkbox(el, id) {
	var spanel = $('#uptable' + id);
	var checked = el.checked;
	spanel.find('input[type=checkbox]').each(function() {
		var checkboxel = this;
		checkboxel.checked = checked;
	});
	upparentcheck(id);
}
//子类有选中，父权限设置选中
function upparentcheck(id) {
	var spanel = $("#uptable" + id).find("input[type=checkbox]:checked").size();
	if (spanel > 0) {
		$("#uprol" + id).attr("name", "rol");
	} else {
		$("#uprol" + id).removeAttr("name");
		$("#upinput" + id).removeAttr("checked");
	}
}

//校验 
//var name_flag = false;
//function check_upname() {
//	var name = document.getElementById("uprolname");
//	name_flag = trimFrom(name,"输入名称不能为空");
//	if(name_flag){
//		name_flag = CHNFrom(name, "请输入汉字");
//		if(name_flag){
//			var origname = $('#origname').val();
//			$.ajax( {
//				type : "POST",
//				url : 'roles_checkRoleName.action',
//				async : false,
//				data : {
//					'name' : name.value,
//					'origname' : origname
//				},
//				success : function(date) {
//					if (!date) {
//						name_flag = false;
//						setPrompt(name, "名字已存在", name_flag);
//						return;
//					} else {
//						name_flag = true;
//						setPrompt(name, "", name_flag);
//					}
//				}
//			});
//		}
//	}
//}
var descri_flag = false;
function check_updescri() {
	var name = document.getElementById("uproldescri");
//	name_flag = trimFrom(name,"输入名称不能为空");
//	if(name_flag){
		descri_flag = CHNFrom(name, "请输入汉字");
		if(descri_flag){
		setPrompt(name, "", descri_flag);
	}
}
//var choose_flag = false;
//function check_choose() {
//	var num = $("#upchoose INPUT[name='rol']:checked").size();
//	if(num>0){
//		choose_flag = true;
//	}else{
//		choose_flag = false;
//		window.confirm("请至少选择一项权限");
//		return;
//	}
//}
function upCheck() {
//	check_upname();
//	check_updescri();
	//	check_upchoose();
	var choose_flag = false;
	var num = [];
	var per = [];
	$('input[name="rol"]:checked').each(function(){
	    num.push($(this).val());
	    var s = $(this).parent().parent().parent().parent().prev().val();
	    if (per.indexOf(s) == -1) {
	    	per.push(s);
	    }
	  });
	for ( var i = 0; i < per.length; i++) {
		num.push(per[i]);
	}
	if (num.length > 0) {
		choose_flag = true;
	} else {
		choose_flag = false;
		//		var info = '请至少选择一项权限！';
		//		showmessage(info);
		//		$('#top_alert').fadeOut(5000);
				window.confirm("请至少选择一项权限");
		return false;
	}
	if (choose_flag) {
//		openNewDiv('newDiv');
		$.post("save.do", {
			"id" : $("#uprolid").val(),
			"name" : $("#uprolname").val(),
			"descri" : $("#uproldescri").val(),
			"rols" : num.join(",")
		}, function (data) {
			hideModal("update");
			if (data) {
				showMsg("修改成功");
				refreshAddTable("tbl", "../roles/list.do");
			} else {
				showErrorMsg("修改失败");
			}
		});
	}
	return false;
}
function upEmpty() {
	$("#uprolid").val("");
	$("#uprolname").val("");
	$("#uproldescri").val("");
	$("#upchoose").find('input[type=checkbox]').each(function() {
		$(this).removeAttr("checked");

	});
	$("#upchoose").find('input .par').each(function() {
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
