$(function() {
	$("#roleMenuTable").bootstrapTable({
		url: 'getRoleMenus.do?adminId='+$("#roleId").val(),         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        pagination: false,                   //是否显示分页（*）
//		onEditableSave: function (field, row, oldValue, $el) {
//			$.post("updateRole.do",{
//                	"id" : $("#roleId").val(),
//                	"menuId" : row.id,
//                	"saveRole" : row.saveRole,
//                	"updateRole" : row.updateRole,
//                	"deleteRole" : row.deleteRole,
//                	"publishRole" : row.publishRole,
//                },function (data) {
//                	refreshTable("roleMenuTable", "getRoleMenus.do?adminId="+$("#roleId").val());
//                    if (data) {
//                    	showMsg("编辑成功");
//                    } else {
//                    	showErrorMsg("编辑失败");
//                    }
//            });
//        },
		columns:[{
			title: '序号',  
			formatter: 'orderNo',  
            align: 'center',  
            width: 100
		}, {
			title: '父菜单',  
			field: 'parentname',  
			align: 'center', 
		}, {
			title: '菜单',  
			field: 'menuName',  
            align: 'center', 
		}, {
			title: '添加权限',  
			formatter: 'onSaveRole',  
            align: 'center',
//            editable: {
//            	type:"select",
//                source: [
//                      {value: true, text: '允许'},
//                      {value: false, text: '禁止'}
//                   ]
//               }
		}, {
			title: '修改权限',  
			formatter: 'onUpdateRole',  
			align: 'center',
//			editable: {
//				type:"select",
//				source: [
//				         {value: true, text: '允许'},
//				         {value: false, text: '禁止'}
//				         ]
//			}
		}, {
			title: '删除权限',  
			formatter: 'onDeleteRole',  
			align: 'center',
//			editable: {
//				type:"select",
//				source: [
//				         {value: true, text: '允许'},
//				         {value: false, text: '禁止'}
//				         ]
//			}
// 		}, {
// 			title: '发布权限',
// 			formatter: 'onPublishRole',
// 			align: 'center',
//			editable: {
//				type:"select",
//				source: [
//				         {value: true, text: '允许'},
//				         {value: false, text: '禁止'}
//				         ]
//			}
		}]
	});
});

function orderNo(value, row, index) {
	return index+1;
}

function onSaveRole(value, row, index) {
	var display = '';
	var classType = '';
	if (row.saveRole) {
		display = '允许';
		classType = 'text-success';
	} else {
		display = '禁止';
		classType = 'text-danger';
	}
	var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateRelease('+row.id+','+row.saveRole+',1)" href="javascript:void(0)" title="修改" >',
	           '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
	           '</a>'];
	return arr.join('');
}
function onUpdateRole(value, row, index) {
	var display = '';
	var classType = '';
	if (row.updateRole) {
		display = '允许';
		classType = 'text-success';
	} else {
		display = '禁止';
		classType = 'text-danger';
	}
	var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateRelease('+row.id+','+row.updateRole+',2)" href="javascript:void(0)" title="修改" >',
	           '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
	           '</a>'];
	return arr.join('');
}
function onDeleteRole(value, row, index) {
	var display = '';
	var classType = '';
	if (row.deleteRole) {
		display = '允许';
		classType = 'text-success';
	} else {
		display = '禁止';
		classType = 'text-danger';
	}
	var arr = ['<a class="btn btn-default btn-xs " type="button" onclick="updateRelease('+row.id+','+row.deleteRole+',3)" href="javascript:void(0)" title="修改" >',
	           '<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>',
	           '</a>'];
	return arr.join('');
}
function onPublishRole(value, row, index) {
	var display = '';
	var classType = '';
	if (row.publishRole == 1) {
		display = '允许';
		classType = 'text-success';
	} else if (row.publishRole == 0 || row.publishRole == null) {
		display = '禁止';
		classType = 'text-danger';
		
	}
	var but = '';
	if (display != '') {
		but = '<a class="btn btn-default btn-xs " type="button" onclick="updateRelease('+row.id+','+row.publishRole+',4)" href="javascript:void(0)" title="修改" >'+
				'<p class="'+classType+'" style="margin-bottom: 0px;">'+display+'</p>'+
				'</a>';
	}
	var arr = [];
	arr.push(but);
	return arr.join('');
}

function updateRelease(menuId, role, flag) {
	var url = "updateRole.do?id="+$("#roleId").val()+"&menuId="+menuId;
	if (role == 1) {
		role = 0;
	} else {
		role = 1;
	}
	if (flag == 1) {
		url = url + "&saveRole=" + role;
	} else if (flag == 2) {
		url = url + "&updateRole=" + role;
	} else if (flag == 3) {
		url = url + "&deleteRole=" + role;
	} else if (flag == 4) {
		url = url + "&publishRole=" + role;
	}
	$.get(url, function (data) {
    	refreshTable("roleMenuTable", "getRoleMenus.do?adminId="+$("#roleId").val());
        if (data) {
        	showMsg("编辑成功");
        } else {
        	showErrorMsg("编辑失败");
        }
    });
}
