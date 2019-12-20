//添加
var name_flag = false;
function check_name(){
	var name = document.getElementById("menuname");
	name_flag = trimFrom(name,"输入名称不能为空");
	if(name_flag){
//    	var check = /[^\u4e00-\u9fa5]/g;
//         if(name.value.match(check)){
//      	     name_flag = false;
//      	     setPrompt(name,"请输入汉字",name_flag);
//      	     return;
    	 setPrompt(name,"",name_flag);
     }
}


function addCheck(){
	check_name();
	if(name_flag){
		openNewDiv('newDiv');
		return true;
	}
	return false;
}

function addEmpty() {
		$("#menuname").val("");
		$("#url").val("");
}
//修改
//实现动态获取信息
function getupMenu(id) {
	$.post('toUpdate.do', {
		"id" : id
	}, function(data) {
		$("#upid").val(data.id);
		$("#upmenuname").val(data.name);
		$("#upurl").val(data.url);
		$("#upparentid").val(data.parentid);
	});

}

function check_upname(){
	var name = document.getElementById("upmenuname");
    name_flag = trimFrom(name,"输入姓名不能为空");	
	if(name_flag){
//    	var check = /[^\u4e00-\u9fa5]/g;
//         if(name.value.match(check)){
//      	     name_flag = false;
//      	     setPrompt(name,"请输入汉字",name_flag);
//      	     return;
    	 setPrompt(name,"",name_flag);
     }
}
function updateCheck(){
	check_upname();
	if(name_flag){
		openNewDiv('newDiv');
		return true;
	}
	return false;
}
function upEmpty() {
	    $("#upid").val("");
		$("#upmenuname").val("");
		$("#upurl").val("");
}


//删除
function del(id){
	 var r = window.confirm("确定要删除吗？");
	     if(r) {
	     	$.get("del.do?id="+id,function (data) {
	     		if (data) {
	     			location.href = location.href;
	     		} else {
	     			showErrorMsg("删除失败");
	     		}
	     	});
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
