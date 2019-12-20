function orderNo(value, row, index) {
    return index+1;
}
function onFormatter(value, row, index) {
    var activity = JSON.stringify(row);
    var arr = [
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick='+ "'" + 'lookActivity(' + activity + ')' + "'" + '>',
        '<i class="fa fa-eye"></i>',
        '</button>',
        '<button  class="btn btn-default btn-xs" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.activityId + ')">',
        '<i class="glyphicon glyphicon-trash"></i>',
        '</button>',
    ];
    return arr.join('');
}

function onFlag(value, row, index) {
    var classType1 = 'btn-default';
    var classType2 = 'btn-default';
    var classType3 = 'btn-default';
    if (row.flag == 1) {
        classType1 = 'btn-warning'
    } else if (row.flag == 2){
        classType2 = 'btn-success';
    } else if (row.flag == 3) {
        classType3 = 'btn-danger';
    }
    var arr = [
        '<a class="btn btn-xs '+classType1+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ row.companyId + ',1, ' + row.flag + ')" href="javascript:void(0)">',
        '<p style="margin-bottom: 0px;">等待</p>',
        '</a>',
        '<a class="btn btn-xs '+classType2+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ row.companyId + ',2, ' + row.flag + ')" href="javascript:void(0)">',
        '<p style="margin-bottom: 0px;">通过</p>',
        '</a>',
        '<a class="btn btn-xs '+classType3+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ row.companyId + ',3, ' + row.flag + ')" href="javascript:void(0)">',
        '<p style="margin-bottom: 0px;">未通过</p>',
        '</a>'];
    return arr.join('');
}

function onDate(value, row, index) {
    var arr = [];
    var date = "";
    if (row.updateTime != null) {
        date = dateToString(row.updateTime, "yyyy-MM-dd");
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
    if(row.flag==1){
        return {
            classes: 'warning'
        };
    }
    if(row.flag==2){
        return {
            classes: 'success'
        };
    }
    if(row.flag==3){
        return {
            classes: 'danger'
        };
    }
    return {};
}

function recordSearch() {
    refreshTable("companyTable", 'getCompanys');
}

function deleteById(id) {
    var r = window.confirm("确定要删除吗？");
    if (r) {
        $.post("delete.do", {
            "id" : id
        }, function(data) {
            refreshTable("companyTable", "getCompanys");
            if (data) {
                showMsg("删除成功");
            } else {
                showErrorMsg("删除失败");
            }
        });
    }
}

function updateFlag(id, flag, oldFlag) {
    if (flag != oldFlag) {
        $.post("updateFlag.do", {
            "id" : id,
            "flag" : flag
        }, function(data) {
            refreshTable("companyTable", "getCompanys");
            hideModal("preview");
            if (data) {
                showMsg("修改成功");
            } else {
                showErrorMsg("修改失败");
            }
        });
    }
}

function lookActivity(company) {
    $("#companyName").val(company.userName);
    $("#abbreviation").val(company.abbreviation);
    $("#companyUrl").val(company.companyUrl);
    $("#companyProofImg").attr("src", company.companyProof);
    $("#personalProofImg").attr("src", company.personalProof);
    var classType1 = 'btn-default';
    var classType2 = 'btn-default';
    var classType3 = 'btn-default';
    if (company.flag == 1) {
        classType1 = 'btn-warning'
    } else if (company.flag == 2){
        classType2 = 'btn-success';
    } else if (company.flag == 3) {
        classType3 = 'btn-danger';
    }
     var buts = '<a class="btn btn-xs '+classType1+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ company.companyId + ',1, ' + company.flag + ')" href="javascript:void(0)">' +
        '<p style="margin-bottom: 0px;">等待</p>' +
        '</a>' +
        '<a class="btn btn-xs '+classType2+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ company.companyId + ',2, ' + company.flag + ')" href="javascript:void(0)">' +
        '<p style="margin-bottom: 0px;">通过</p>' +
        '</a>' +
        '<a class="btn btn-xs '+classType3+'" style="margin-left: 10px;" type="button" onclick="updateFlag('+ company.companyId + ',3, ' + company.flag + ')" href="javascript:void(0)">' +
        '<p style="margin-bottom: 0px;">未通过</p>' +
        '</a>';
    $("#butDiv").html(buts);
    showModal("preview");
}

$(function() {
    $("#companyTable").bootstrapTable({
        url: 'getCompanys',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        // queryParams:"countParams",			//搜索条件的添加
        rowStyle:"rowStyle",				//自定义表格每行的样式
//         onEditableSave: function (field, row, oldValue, $el) {
//             $.post("updateTop.do",{
//                 "id" : row.activityId,
//                 "top" : row.top,
// //                	"time" : row.delivery
//             },function (data) {
//                 if (data) {
//                     refreshTable("activityTable", "getCompanys");
//                     showMsg("编辑成功");
//                 } else {
//                     showErrorMsg("编辑失败");
//                 }
//             });
//         },
        columns:[{
            title: '序号',
            formatter: 'orderNo',
            align: 'center',
        // }, {
        //     title: '排序',
        //     field: 'top',
        //     align: 'center',
        //     editable:{
        //     }
        }, {
            title: '名称',
            field: 'userName',
            align: 'center',
        }, {
            title: '简称',
            field: 'abbreviation',
            align: 'center',
        }, {
            title: '官网',
            field: 'companyUrl',
            align: 'center',
        }, {
            title: '申请时间',
            formatter: 'onDate',
            align: 'center',
        }, {
            title: '状态',
            formatter: 'onFlag',
            align: 'center',
        }, {
            title: '操作',
            formatter: 'onFormatter',
            align: 'center',
            width: '25%',
        }]
    });
});
