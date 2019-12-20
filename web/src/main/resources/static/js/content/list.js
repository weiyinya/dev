function orderNo(value, row, index) {
	  return index+1;
	}
function onFormatter(value, row, index) {
	var arr = [
		           '<button  class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')" disabled="disabled">',
		           '<i class="glyphicon glyphicon-trash"></i>',
		           '</button>',
		           ];
	return arr.join('');
}

function onType(value, row, index) {
	var arr = [];
	if (row.type == 1) {
		arr.push("广告");
	} else if (row.type == 2){
		arr.push("置顶");
	}
	return arr.join('');
}
//定时倒计时
var timer = [];
function onRelease(value, row, index) {
	var display = '';
	if (row.publish == 1) {
		display = 
//			'<button class="btn btn-default btn-xs publishBut" type="button" onclick="updateRelease(' + row.contentId + ')" href="javascript:void(0)" title="修改" disabled="disabled">'
        			'已发布'; 
//        			+ '</button>';
	} else if (row.publish == 0) {
		display = 
			'<button class="btn btn-default btn-xs" type="button" onclick="updateRelease(' + row.id + ')" href="javascript:void(0)" title="修改">'+
					'未发布'
					+ '</button>';
	} else if (row.publish == 2) {
		var timestamp =Date.parse(new Date()) - 8*60*60*1000;
		display = '定时发布<div id="timer'+index+'"></div>';
		timer.push("timer"+index + "-" + (row.delivery-timestamp)/1000);
	}
	var arr = [display
	           ];
	return arr.join('');
}

function onDate(value, row, index) {
	var arr = [];
	var date = "";
	if (row.date != null) {
		date = dateToString(row.date, "yyyy-MM-dd");
	}
	arr.push(date);
	return arr.join('');
}
function onDelivery(value, row, index) {
	var arr = [];
	var date = "";
	if (row.publish != 0) {
//		date = dateToString(row.delivery, "yyyy-MM-dd HH:mm");
		date = row.deliveryStr;
	}
	arr.push(date);
	return arr.join('');
}

function onDetails(value, row, index) {
	var arr = ['<div class="thumbnail">'+
	           '<img alt="' + row.title + '" src="'+row.imageUrl+'">'+
	           '</div>'];
	return arr.join('');
}

//搜索条件
function countParams(params) {
	var time  = $("#dateSearch").val();
	if (time != '请选择日期') {
		params["time"] = time;
	}
	return params;
}

//未认证的加样式
function rowStyle(row, index){
	if(row.publish==2){
		return {
            classes: 'warning'
        };
	}
	if(row.publish==1){
		return {
			classes: 'info'
		};
	}
	if(row.publish==0){
		return {
			classes: 'default'
		};
	}
	return {};	
}

function recordSearch() {
	refreshTable("contentTable", 'list.do?menuId='+$("#menuId").val());
}

function findByID(id) {
	location.href = "toUpdate.do?id="+id+"&menuId="+GetQueryString().menuId+"&menuName="+GetQueryString().name;
}

function deleteById(id) {
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.post("delete.do", {
			"id" : id
		}, function(data) {
			refreshTable("contentTable", "list.do?menuId="+$("#menuId").val());
			if (data) {
				showMsg("删除成功");
			} else {
				showErrorMsg("删除失败");
			}
		});
	}
}

function gameEmpty() {
	$("#id").val(0);
	$("#gamename").val("");
	$("#downloadUrl").val("");
	$("#content").val("");
	$('#mark').val("");
	$('#fileSize').val("");
	$('#icon').attr('src',"");
	$("#content2").summernote('code', "");
	selectValue("type", "0");
}

function articleEmpty() {
	$("#id").val(0);
	$("#title").val("");
	$("#header").val("");
	$("#content").val("");
	$('#origin').val("");
	$('#image').attr('src',"");
	$('#imageMin').attr('src',"");
	$('#banner').attr('src',"");
	$("#content1").summernote('code', "");
	selectValue("type", "");
}

function imageEmpty() {
	$("#imageid").val(0);
	$("#imagetitle").val("");
	$('#image_url').attr('src',"");
	selectValue("publish", 0);
	emptyInputFile("image_img");
}

function getHtml(flag) {
	openNewDiv();
	var sHTML = $("#content" + flag).summernote('code');
	$("#content").val(String(sHTML));
	return true;
}

function updateRelease(id) {
	$.get("updateRelease.do?id=" + id,function(data){
		if(data){
			refreshTable("contentTable", "list.do?menuId="+$("#menuId").val());
		}
	});
}

function updateContent(id) {
	$.get("update.do?id=" + id,function(data){
		if(data){
			refreshTable("contentTable", "list.do?menuId="+$("#menuId").val());
		}
	});
}

$(function() {
	var menuName = GetQueryString().name;
	$("#menuName").append("<li class='active'>"+menuName+"</li>");
	$("#menuName_art").val(menuName);
	$("#menuName_game").val(menuName);
	$("#contentTable").bootstrapTable({
		url: 'list.do?menuId='+$("#menuId").val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        queryParams:"countParams",			//搜索条件的添加
    	rowStyle:"rowStyle",				//自定义表格每行的样式
    	onLoadSuccess:function () {
    		for ( var i = 0; i < timer.length; i++) {
    		var params = timer[i].split("-");
    		addTimer(params[0], params[1]);
    	}
    },
		onEditableSave: function (field, row, oldValue, $el) {
			$.post("update.do",{
                	"id" : row.id,
                	"type" : row.type,
//                	"time" : row.delivery
                },function (data) {
                	refreshTable("contentTable", "list.do?menuId="+$("#menuId").val());
                    if (data) {
                    	showMsg("编辑成功");
                    } else {
                    	showErrorMsg("编辑失败");
                    }
            });
        },
		columns:[{
			title: '序号',  
			formatter: 'orderNo',  
            align: 'center',  
            width: '5%'
		}, {
			title: '排序',  
            field: 'type',  
            align: 'center',  
            width: '10%',
            editable:{
            }
		}, {
			title: '内容类型',  
			field: 'tableName',  
            align: 'center', 
            width: '10%',
//		}, {
//			title: '发布时间',  
//			field: 'delivery',  
//            align: 'center',
//            editable: {
//            	type:'combodate',
//                format: 'YYYY-MM-DD HH:mm',    
//                viewformat: 'YYYY 年  MMMD 日  HH 时 mm 分',    
//                template: ' YYYY 年  MMM D 日   HH 时 mm 分',
//                combodate: {
//                    minYear: 2010,
//                    maxYear: 2025,
//                    minuteStep: 1
//               }
//                }
		}, {
			title: '标题',  
			field: 'title',  
            align: 'center',  
            width: '20%',
		}, {
			title: '状态',  
			formatter: 'onRelease',  
			align: 'center',  
			width: '10%',
		}, {
			title: '发布时间',  
			formatter: 'onDelivery',  
			align: 'center',  
			width: '20%',
		}, {
			title: '操作',  
			formatter: 'onFormatter',  
            align: 'center', 
            width: '25%',
		}]
	});
	
	//取日期
	$.post("getDates.do",{
		"menuId" : $("#menuId").val()
	}, function(data){
		$("#dateSelect").html("");
		var dates = [];
		dates.push("<option data-tokens=''>请选择日期</option>");
        var data = eval('(' + data + ')');
		$.each(data, function (id, node) {
			dates.push("<option data-tokens="+node+">"+node+"</option>");
		});
		$("#dateSearch").html(dates.join(''));
		$('#dateSearch').selectpicker('render');
        $('#dateSearch').selectpicker('refresh');
        $("#dateSelect").html(dates.join(''));
        $('#dateSelect').selectpicker('render');
        $('#dateSelect').selectpicker('refresh');
	});
	$("#image_img").change(function(){
		var objUrl = getObjectURL(this.files[0]) ;
		console.log("objUrl = "+objUrl) ;
		if (objUrl) {
			$("#image_url").attr("src", objUrl) ;
		}
	}) ;
	
	$.post("../contentType/list.do", function(data) {
        var data = eval(data);
		$.each(data, function(id, node) {
            console.log(node.id)
			$("#tableTypes").append("<option value="+node.id+">"+node.tableName+"</option>");
		});
	});
	
});
//建立一個可存取到該file的url	为了实现上传图片的预览效果
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

var myDate = new Date();
var today =myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes();
function setTime(id) {
    $("#" + id).val(today);
}

function toPreview() {
	var time = $("#dateSelect").val();
	if (time != "请选择日期") {
	  //官网欢迎页
	  layer.open({
		  type: 2,
		  area: ['950px', '800px'],
		  title: '预览效果图',
		  fix: false, //不固定
		  maxmin: true,
	    content: 'preview.jsp?menuId=' + $("#menuId").val() + "&time="+time,
	  });
	} else {
		showErrorMsg("请选择需要预览的日期");
	}
}

function upTime(flag){
	if (flag!=2) {
		$("#imageTime").css("display","none");
	} else {
		$("#imageTime").css("display", "inline");
	}
}
function upTimeForH5(flag){
	if (flag!=2) {
		$("#H5_imageTime").css("display","none");
	} else {
		$("#H5_imageTime").css("display", "inline");
	}
}

var addTimer = function addTimer() {     
    var list = [],     
        interval;     

    return function (id, time) {     
        if (!interval)     
            interval = setInterval(go, 1000);     
        list.push({ ele: document.getElementById(id), time: time });     
    }     

    function go() {     
        for (var i = 0; i < list.length; i++) {     
            list[i].ele.innerHTML = getTimerString(list[i].time ? list[i].time -= 1 : 0);     
            if (!list[i].time)     
                list.splice(i--, 1);     
        }     
    }     

    function getTimerString(time) {     
            d = Math.floor(time / 86400),     
            h = Math.floor((time % 86400) / 3600),     
            m = Math.floor(((time % 86400) % 3600) / 60),     
            s = Math.floor(((time % 86400) % 3600) % 60);     
        if (time>0)     
            return d + "天" + h + "小时" + m + "分" + s + "秒";       
        else return "时间到";     
    }     
} ();  