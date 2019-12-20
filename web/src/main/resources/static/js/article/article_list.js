function orderNo(value, row, index) {
    return index+1;
}
function onFormatter(value, row, index) {
    var arr = [
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="lookActivity(' + row.articleId + ', '+ "'"+ row.adminName +"'"+ ')">',
        '<i class="fa fa-eye"></i>',
        '</button>',
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="toUpdate(' + row.articleId + ')">',
        '<i class="fa fa-pencil"></i>',
        '</button>',
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.articleId + ')">',
        '<i class="glyphicon glyphicon-trash"></i>',
        '</button>',
    ];
    return arr.join('');
}

function onFlag(value, row, index) {
    var classType1 = 'btn-default';
    var classType2 = 'btn-default';
    // var classType3 = 'btn-default';
    if (row.state == 1) {
        classType1 = 'btn-warning'
    } else if (row.state == 2){
        classType2 = 'btn-success';
    // } else if (row.state == 3) {
    //     classType3 = 'btn-danger';
    }
    var arr = [
        '<a class="btn btn-xs '+classType1+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.articleId + ',1, ' + row.state + ')" href="javascript:void(0)">',
        '<p style="margin-bottom: 0px;">未发布</p>',
        '</a>',
        '<a class="btn btn-xs '+classType2+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.articleId + ',2, ' + row.state + ')" href="javascript:void(0)">',
        '<p style="margin-bottom: 0px;">已发布</p>',
        '</a>',
        // '<a class="btn btn-xs '+classType3+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.activityId + ',3, ' + row.state + ')" href="javascript:void(0)">',
        // '<p style="margin-bottom: 0px;">未通过</p>',
        // '</a>'
    ];
    return arr.join('');
}

function onDate(value, row, index) {
    var arr = [];
    var date = "";
    if (row.state == 2) {
        if (row.stateTime != null) {
            date = dateToString(row.stateTime, "yyyy-MM-dd");
        }
    }
    arr.push(date);
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
    if(row.state==1){
        return {
            classes: 'warning'
        };
    }
    if(row.flag==2){
        return {
            classes: 'success'
        };
    }
    // if(row.flag==3){
    //     return {
    //         classes: 'danger'
    //     };
    // }
    return {};
}

function recordSearch() {
    refreshTable("activityTable", 'list.do?menuId='+$("#menuId").val());
}

function deleteById(articleId) {
    var r = window.confirm("确定要删除吗？");
    if (r) {
        $.post("update", {
            "articleId" : articleId,
            "delState" : 1
        }, function(data) {
            refreshTable("articleTable", "listAll");
            if (data) {
                showMsg("删除成功");
            } else {
                showErrorMsg("删除失败");
            }
        });
    }
}

function updateState(id, state, oldState) {
    if (state != oldState) {
        $.post("update", {
            "articleId" : id,
            "state" : state
        }, function(data) {
            refreshTable("articleTable", "listAll");
            if (data) {
                showMsg("修改成功");
            } else {
                showErrorMsg("修改失败");
            }
        });
    }
}

function lookActivity(id, name) {
    window.open("look/" + id);
}

$(function() {
    $("#articleTable").bootstrapTable({
        url: 'listAll',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        // queryParams:"countParams",			//搜索条件的添加
        rowStyle:"rowStyle",				//自定义表格每行的样式
        sortable:true,
        sortName:"article_id",
        onEditableSave: function (field, row, oldValue, $el) {
            $.post("update",{
                "articleId" : row.articleId,
                "recommend" : row.recommend,
//                	"time" : row.delivery
            },function (data) {
                if (data) {
                    refreshTable("articleTable", "listAll");
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
        }, {
            title: '排序',
            field: 'recommend',
            align: 'center',
            sortable:true,
            editable:{
            }
        }, {
            title: '发布者',
            field: 'adminName',
            align: 'center',
        }, {
            title: '标题',
            field: 'title',
            align: 'center',
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
            title: '发布时间',
            formatter: 'onDate',
            sortable:true,
            align: 'center',
            field:'state_time'
        }, {
            title: '状态',
            formatter: 'onFlag',
            align: 'center',
            field:'state',
            sortable:true
        }, {
            title: '操作',
            formatter: 'onFormatter',
            align: 'center',
            width: '25%',
        }]
    });
});

function toUpdate(articleId) {
    location.href = 'toUp?articleId=' + articleId + "&menuId=" + GetQueryString().menuId + "&name=" + GetQueryString().name;
}