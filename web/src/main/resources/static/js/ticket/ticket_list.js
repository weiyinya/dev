function orderNo(value, row, index) {
    return index+1;
}
function onFormatter(value, row, index) {
    var arr = [
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="toUpdate(' + row.id + ')">',
        '<i class="fa fa-pencil"></i>',
        '</button>',
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')">',
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
    refreshTable("ticketTable", 'list.do?menuId='+$("#menuId").val());
}

function deleteById(articleId) {
    var r = window.confirm("确定要删除吗？");
    if (r) {
        $.post("update", {
            "ticketId" : ticketId,
            "delState" : 1
        }, function(data) {
            refreshTable("ticketTable", "listAll");
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
            "ticketId" : id,
            "state" : state
        }, function(data) {
            refreshTable("ticketTable", "listAll");
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
    $("#ticketTable").bootstrapTable({
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
        sortName:"id",
        onEditableSave: function (field, row, oldValue, $el) {
            $.post("update",{
                "ticketId" : row.ticketId,
                "recommend" : row.recommend
//                	"time" : row.delivery
            },function (data) {
                if (data) {
                    refreshTable("ticketTable", "listAll");
                    showMsg("编辑成功");
                } else {
                    showErrorMsg("编辑失败");
                }
            });
        },
        columns:[
            {
            title: '序号',
            formatter: 'orderNo',
            align: 'center'
            },
            {
            title: '所属活动',
            field: 'head',
            align: 'center',
        }, {
            title: '票类型名',
            field: 'ticket_type_name',
            align: 'center'
        }, {
            title: '单价',
            field: 'ticket_price',
            align: 'center'
        }, {
            title: '总票量',
            align: 'center',
            field:'ticket_count'
        }, {
            title: '剩余量',
            align: 'center',
            field:'ticket_surplus'
        }, {
            title: '操作',
            formatter: 'onFormatter',
            align: 'center',
            width: '25%'
        }]
    });
});

function toUpdate(ticketId) {
    location.href = 'toUp?ticketId=' + ticketId + "&menuId=" + GetQueryString().menuId + "&name=" + GetQueryString().name;
}