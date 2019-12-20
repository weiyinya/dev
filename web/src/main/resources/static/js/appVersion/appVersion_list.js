/**
 * Created by Administrator on 2017/9/14.
 */
function orderNo(value, row, index) {
    return index+1;
}

function onFormatter(value, row, index) {
    var butHtml = '<button  class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="update(' + row.id + ')" disabled="disabled">'
            +'修改'
            +'</button>';
    var arr = [
        butHtml
    ];
    return arr.join('');
}

function update(id) {
    $.post("findById", {
        "id" : id,
    }, function(data) {
        $("#phoneOs").val(data.phoneOs);
        $("#version").val(data.appVersion);
        $("#appVerionId").val(data.id);
        showModal("appVersionUpdate");
    });
}

function updateSubmit() {
    var phoneOs = $("#phoneOs").val();
    var version = $("#version").val();
    var id = $("#appVerionId").val();
    if (phoneOs != "" && version != "") {
        $.post("update", {
            "id" : id,
            "phoneOs" : phoneOs,
            "version" : version,
        }, function(data) {
            refreshTable("appVersionTable", "findByAll");
            appVersionEmpt();
            hideModal("appVersionUpdate");
            if (data) {
                showMsg("修改成功");
            } else {
                showErrorMsg("修改失败");
            }
        });
    } else {
        showErrorMsg("参数不能为空");
    }
}

function appVersionEmpt() {
    $("#phoneOs").val("");
    $("#version").val("");
    $("#appVerionId").val("");
}