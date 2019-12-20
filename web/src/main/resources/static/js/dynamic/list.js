var key = ''
function orderNo(value, row, index) {
	  return index+1;
}
function onFormatter(value, row, index) {

    var dynamic = JSON.stringify(row);
    console.log(dynamic)
    var arr = [
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick='+ "'" + 'lookDetail(' + dynamic + ')' + "'" + '>',
        '<i class="fa fa-eye"></i>',
        '</button>',
/*        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick='+ "'" + 'update(' + dynamic + ')' + "'" + '>',
        '<i class="fa fa-pencil"></i>',
        '</button>'*/
    ];
    return arr.join('');
}

/**
 * 搜索
 */
function recordSearch() {
    $("#dynamicTable").bootstrapTable('refresh')
}

/**
 * 查看详情
 * @param dynamic
 */
function lookDetail(object) {
    var imgsDivs = "", imgsLis = "";
    //图片
    var imgsArr = object.contentResource.split(",");
    for(var i=0;i<imgsArr.length;i++) {
        var divClass = "";
        var divAlt = "";
        if (i == 0) {
            divClass = 'active';
            divAlt = 'alt="First slide"';
        }
        imgsDivs = imgsDivs + '<div class="item ' + divClass + '"><img src="' + imgsArr[i] + '" ' + divAlt + '></div>';
        imgsLis =  imgsLis + '<li data-target="#myCarousel" data-slide-to="0" class="' + divClass + '"></li>';
    }
    $("#activityImgsOl").html(imgsLis);
    $("#activityImgsDiv").html(imgsDivs);
    $("#dynamicTitle").html(object.title);
    $("#dynamicThumbNum").html(object.clickNum);
    $("#dynamicCommentNum").html(object.commentNum);
    $("#dynamicDate").html(object.timeStr);
    $("#dynamicId").val(object.id);
    showModal("preview");
}

/**
 * 添加点赞量
 */
function addClickNum(){

	//关闭弹窗
    hideModal('preview')
	var clickNum = $("#clickNum").val()
	var contentId = $("#dynamicId").val()
	//alert(clickNum + ' ' + contentId)
    $.ajax({
        type: 'POST',
        url: 'addClickNum',
        dataType: 'json',
        data: {
        	'contentId':contentId,
			'clickNum':clickNum
		},
        success: function (data) {
            showMsg('添加成功')
        }
    });

	//刷新页面
    recordSearch();
}

/**
 * 更新
 */
function toUpdate(contentId) {
    $.get("update.do?contentId=" + contentId);
}

//搜索条件
function countParams(params) {
	var time  = $("#dateSearch").val();
	if (time != '请选择日期') {
		params["time"] = time;
	}
	return params;
}

function imageEmpty() {
	$("#imageid").val(0);
	$("#imagetitle").val("");
	$('#image_url').attr('src',"");
	selectValue("publish", 0);
	emptyInputFile("image_img");
}
function updateContent(id) {
	$.get("update.do?id=" + id,function(data){
		if(data){
			refreshTable("contentTable", "list.do?menuId="+$("#menuId").val());
		}
	});
}

function queryParams(params) {
    return {
        //pageNumber: params.pageSize,
        pageindex:this.pageNumber,//当前页码
        limit: params.limit, //当前页数
        key: $("#key").val()
    };
}

$(function() {
	var menuName = GetQueryString().name;
	$("#menuName").append("<li class='active'>"+menuName+"</li>");
	$("#menuName_art").val(menuName);
	$("#menuName_game").val(menuName);
	$("#dynamicTable").bootstrapTable({
		url: 'listAll.do?menuId='+$("#menuId").val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        queryParams:queryParams,			//搜索条件的添加
    	rowStyle:"rowStyle",		 		//自定义表格每行的样式
    	onLoadSuccess:function () {
    		console.log('success')
    	},
		columns:[{
			title: '序号',
			formatter: 'orderNo',
			align: 'center',
			width: '5%'
		}, {
            title: '用户名',
            field: 'userName',
            align: 'center',
            width: '20%',
        }, {
			title: '内容',
			field: 'title',
			align: 'center',
			width: '20%',
		}, {
			title: '点赞量',
			field: 'clickNum',
			align: 'center',
			width: '15%',
		}, {
			title: '评论量',
			field: 'commentNum',
			align: 'center',
			width: '15%',
		}, {
            title: '发布时间',
            field: 'timeStr',
            align: 'center',
            width: '20%'
        },{
			title: '操作',
			formatter: 'onFormatter',
			align: 'center',
			width: '15%'
		}]
	});
});