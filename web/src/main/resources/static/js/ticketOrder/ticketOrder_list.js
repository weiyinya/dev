function orderNo(value, row, index) {
    return index+1;
}
function onFormatter(value, row, index) {
    var arr = [
        // '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="toUpdate(' + row.ticketOrderId + ')">',
        // '<i class="fa fa-pencil"></i>',
        // '</button>',
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.ticketOrderId + ')">',
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
    // var arr = [
    //     '<a class="btn btn-xs '+classType1+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.articleId + ',1, ' + row.state + ')" href="javascript:void(0)">',
    //     '<p style="margin-bottom: 0px;">未发布</p>',
    //     '</a>',
    //     '<a class="btn btn-xs '+classType2+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.articleId + ',2, ' + row.state + ')" href="javascript:void(0)">',
    //     '<p style="margin-bottom: 0px;">已发布</p>',
    //     '</a>',
    //     // '<a class="btn btn-xs '+classType3+'" style="margin-left: 10px;" type="button" onclick="updateState('+ row.activityId + ',3, ' + row.state + ')" href="javascript:void(0)">',
    //     // '<p style="margin-bottom: 0px;">未通过</p>',
    //     // '</a>'
    // ];
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
    refreshTable("ticketOrderTable", 'list.do?menuId='+$("#menuId").val());
}

function deleteById(ticketOrderId) {
    var r = window.confirm("确定要删除吗？");
    if (r) {
        $.post("delTicketOrder", {
            "ticketOrderId" : ticketOrderId,
            "delState" : 1
        }, function(data) {
            refreshTable("ticketOrderTable", "listAll");
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
            "ticketOrderId" : id,
            "state" : state
        }, function(data) {
            refreshTable("ticketOrderTable", "listAll");
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
    $("#ticketOrderTable").bootstrapTable({
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
                "ticketOrderId" : row.ticketOrderId,
                "recommend" : row.recommend
//                	"time" : row.delivery
            },function (data) {
                if (data) {
                    refreshTable("ticketOrderTable", "listAll");
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
            title: '账户名',
            field: 'appUserName',
            align: 'center',
        },
        {
            title: '用户名',
            field: 'userName',
            align: 'center',
        }, {
            title: '手机号',
            field: 'phoneNum',
            align: 'center'
        }, {
            title: '购票量',
            field: 'ticketNum',
            align: 'center'
        }, {
            title: '票单价',
            align: 'center',
            field:'ticketPrice'
        }, {
            title: '票类型名',
            align: 'center',
            field:'ticketTypeName'
        },
            {
                title: '总价',
                align: 'center',
                field:'countPrice'
            },
            {
                title: '地址',
                align: 'center',
                field:'address'
            },
            {
                title: '时间',
                align: 'center',
                field:'ticketTimeStr'
            },
            {
                title: '支付方式',
                align: 'center',
                field:'payment'
            },
            {
            title: '操作',
            formatter: 'onFormatter',
            align: 'center',
            width: '10%'
        }]
    });
});

function toUpdate(ticketOrderId) {
    location.href = 'toUp?ticketOrderId=' + ticketOrderId + "&menuId=" + GetQueryString().menuId + "&name=" + GetQueryString().name;
}