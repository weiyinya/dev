/**
 * Created by Administrator on 2017/9/14.
 */
function orderNo(value, row, index) {
    return index+1;
}

function onFormatter(value, row, index) {
    var arr = ['<button class="btn btn-default btn-xs updateBut" data-toggle="modal" data-backdrop="static" data-target="#toUpdate" type="button" onclick="setrole('+row.id+')"  disabled="disabled" href="javascript:void(0)" title="修改" >',
        '<i class="fa fa-pencil"></i>',
        '</button>',
        '<button class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')"  disabled="disabled">' +
        '<i class="glyphicon glyphicon-trash"></i>' +
        '</button>'
    ];
    return arr.join('');
}

function deleteById(id) {
    $.get("delete", {
        "id" : id
    }, function (data) {
        refreshTable("newsTable", "list");
        if (data) {
            showMsg("修改成功");
        } else {
            showErrorMsg("修改失败");
        }
    })
}

function onDate(value, row, index) {
    var arr = [];
    var date = "";
    if (row.refreshDate != null) {
        date = dateToString(row.refreshDate, "yyyy-MM-dd");
    }
    arr.push(date);
    return arr.join('');
}