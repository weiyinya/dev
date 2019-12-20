/**
 * Created by Administrator on 2017/9/13.
 */
function orderNo(value, row, index) {
    return index+1;
}
/*function searchVideo(name) {
	 $.get("listname", {
	       "name":name
	   }, function (data) {
	       $("#name").val(data.name);
	     
	   })
	}*/

function onVip(value, row, index) {
    var display = '';
    var classType = '';
    if (row.vip) {
        display = 'VIP用户';
        classType = 'text-success';
    } else {
        display = '普通用户';
        classType = 'text-danger';
    }
    var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateByVip('+row.id+')" href="javascript:void(0)" title="修改" >',
        '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
        '</a>'];
    return arr.join('');
}

function onPush(value, row, index) {
    var display = '';
    var classType = '';
    if (row.push) {
        display = '推荐';
        classType = 'text-success';
    } else {
        display = '一般';
        classType = 'text-danger';
    }
    var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateByPush('+row.id+')" href="javascript:void(0)" title="修改" >',
        '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
        '</a>'];
    return arr.join('');
}

function onYhType(value, row, index) {
    var display = '';
    var classType = '';
    if (row.yhType) {
        //0
        display = '冻结';
        classType = 'text-success';
    } else {
        //1
        display = '正常';
        classType = 'text-danger';
    }
    var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateByYhType('+row.id+')" href="javascript:void(0)" title="修改" >',
        '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
        '</a>'];
    return arr.join('');
}

function onFormatter(value, row, index) {
    var arr = [
        '<button class="btn btn-default btn-xs updateBut" data-toggle="modal" data-backdrop="static" data-target="#toUpdate" type="button" onclick="setrole('+row.id+')"  disabled="disabled" href="javascript:void(0)" title="修改" >',
        '<i class="fa fa-pencil"></i>'+
        '</button>',
        '<button class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')"  disabled="disabled">' +
            '<i class="glyphicon glyphicon-trash"></i>' +
        '</button>',
        '<button  class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="setroleow(' + row.id + ')" disabled="disabled">'
        +'刷粉'
        +'</button>'
    ];
    return arr.join('');
}

function updateByVip(id) {
    $.get("updateByVip",{
        "id": id
    }, function (data) {
        refreshTable("userTable", "list");
        if (data) {
            showMsg("修改成功");
        } else {
            showErrorMsg("修改失败");
        }
    });
}
function onfolle2r(id,offset) {
    $.get("listByfoller",{
        "id": id,
        "foel":foel
    }, function (data) {
    	   $("#foel").val(data.foel);
    });
}


function updateByPush(id) {
    $.get("updateByPush",{
        "id": id
    }, function (data) {
        refreshTable("userTable", "list");
        if (data) {
            showMsg("修改成功");
        } else {
            showErrorMsg("修改失败");
        }
    });
}

function updateByYhType(id) {
    $.get("updateByYhType",{
        "id": id
    }, function (data) {
        refreshTable("userTable", "list");
        if (data) {
            showMsg("修改成功");
        } else {
            showErrorMsg("修改失败");
        }
    });
}
function deleteById(id) {
    $.get("delete", {
        "id" : id,
        
    }, function (data) {
        refreshTable("userTable", "list");
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
function setrole(id) {
	   $.get("toUpdate", {
	       "id":id
	   }, function (data) {
	       // var data = eval('(' + data + ')');
	       $("#user_id").val(data.id);
	       $("#offset").val(data.offset);
	       $("#user_name").val(data.name);
	       $("#user_phone").val(data.phone);
	       $("#user_password").val(data.password);
	       $("#user_imei").val(data.imei);
	       $("#user_imsi").val(data.imsi);
	       $("#user_country").val(data.country);
	       $("#user_province").val(data.province);
	       $("#user_city").val(data.city);
	       $("#user_area").val(data.area);
	       $("#user_signature").val(data.signature);
	       radioChecked("gender", data.gender);
	  
	       showModal("userAdd");
	       $("#user_img").attr('src',data.img);
	   })
	}



function setroleow(id) {
   $.get("toUpdate", {
       "id":id
   }, function (data) {
       // var data = eval('(' + data + ')');
       $("#userId").val(data.id);
       $("#fansNum").val(data.fansNum);
       $("#user_name").val(data.name);
       $("#user_phone").val(data.phone);
       $("#user_password").val(data.password);
       $("#user_imei").val(data.imei);
       $("#user_imsi").val(data.imsi);
       $("#user_country").val(data.country);
       $("#user_province").val(data.province);
       $("#user_city").val(data.city);
       $("#user_area").val(data.area);
       $("#user_signature").val(data.signature);
       radioChecked("gender", data.gender);
  
       showModal("forller");
       $("#user_img").attr('src',data.img);
   })
}



function searchByKey() {
    $("#userTable").bootstrapTable('refresh')
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
	$("#userTable").bootstrapTable({
        url: 'list',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        queryParams:queryParams,			//搜索条件的添加
        rowStyle:"rowStyle",
        //自定义表格每行的样式
        columns:[{
            title: '序号',
            formatter: 'orderNo',
            align: 'center',
        }, {
            title: '名称',
            field: 'name',
            
            align: 'center',
            editable:{
            }
        }, {
            title: '手机',
            field: 'phone',
            align: 'center',
        }, {
            title: '性别',
            field: 'gender',
            // formatter:'onGender',
            align: 'center',
        },
        // {
        //     title: '设备号',
        //     field: 'imei',
        //     align: 'center',
        // },
        // {
        //     title: 'SIM卡号',
        //     field: 'imsi',
        //     align: 'center',
        // },
        // {
        //     title: '国家',
        //     field: 'country',
        //     align: 'center',
        // },
        // {
        //     title: '省份',
        //     field: 'province',
        //     align: 'center',
        // },
        // {
        //     title: '城市',
        //     field: 'city',
        //     align: 'center',
        // }, {
        //     title: '地区',
        //     field: 'area',
        //     align: 'center',
        // },
        
        {
            title: '注册时间',
            field: 'refreshDate',
            formatter:'onDate',
            align: 'center',
        },
        {
            title: 'vip',
            field: 'vip',
            formatter:'onVip',
            align: 'center',
        },
        {
            title: '推荐',
            field: 'push',
            formatter:'onPush',
            align: 'center',
        },
        {
            title: '等级',
            field: 'lv',
            align: 'center',
        }, 
        {
            title: '用户状态',
            field: 'yhtype',
            formatter:'onYhType',
            align: 'center',
        }, 
        {
            title: '操作',
            formatter: 'onFormatter',
            align: 'center',
            width: '20%',
        }]
	});
});
