/**
 * Created by Administrator on 2017/9/14.
 */
function orderNo(value, row, index) {
    return index+1;
}

function onFormatter(value, row, index) {
	var message = "";
	var butHtml = "";
	var butHtmlEnd = "";
    if (row.isRead) {
        butHtml = '<p style="margin-bottom: 0px;">已处理</p>';
    } else {
        butHtml = '<a class="btn btn-xs btn-warning" type="button" onclick="udpateTable(' + row.id + ')" href="javascript:void(0)">'
        butHtmlEnd = '</a>';
        message = '待处理';
        // butHtml = '<button  class="btn btn-default btn-xs deleteBut" style="margin-left: 10px;" href="javascript:void(0)" onclick="deleteById(' + row.id + ')" disabled="disabled">'
        //     +'<i class="glyphicon glyphicon-trash"></i>'
        //     +'</button>';
    }
    var arr = [
    	butHtml + '<p style="margin-bottom: 0px;">' + message + '</p>' + butHtmlEnd
        ];
    return arr.join('');
}

function udpateTable(id) {
    var r = window.confirm("确定已经处理了吗？");
    if (r) {
        $("#notifyReportId").val(id);
        update();
    }
}

function update() {
	var notifyReportId = $("#notifyReportId").val();
    if (notifyReportId != "") {
		$.post("update", {
			"id" : notifyReportId,
		}, function(data) {
			refreshTable("notifyReportTable", "getNotifyReports");
			if (data) {
				showMsg("修改成功");
			} else {
				showErrorMsg("修改失败");
			}
		});
	}
}

function onDate(value, row, index) {
    var arr = [];
    var date = "";
    if (row.createdAt != null) {
        date = dateToString(row.createdAt, "yyyy-MM-dd");
    }
    arr.push(date);
    return arr.join('');
}

function onTargetType(value, row, index) {
	var arr = [];
	var data = "";
	if (row.targetType == 'content') {
		data = "资讯";
	} else if (row.targetType == 'user') {
		data = "用户";
	} else if (row.targetType == 'activity') {
		data = "活动";
	} else {
	    data = "未知";
    }
	arr.push(data);
	return arr.join('');
}

function onTargetName(value, row, index) {
	var arr = ['<p style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;margin:0;width:85%">',
		row.targetName,
		'</p>']
	return arr.join('');
}

function onContent(value, row, index) {
	var arr = ['<p style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;margin:0;width:85%">',
		row.content,
		'</p>']
	return arr.join('');
}

function onLook(value, row, index) {
	var arr = [
        '<button  class="btn btn-default btn-xs" href="javascript:void(0)" onclick="lookThere(' + "'" + row.targetType + "', " + row.target + ", " + row.id+ ')">',
        '<i class="fa fa-eye"></i>',
        '</button>',
		           ];
	return arr.join('');
}

function lookThere(type, id, notifyReportId) {
    $("#notifyReportId").val(notifyReportId);
	var html = "";
	//判断举报类型
	if (type == "user") {
		$.post("../user/toUpdate.do", {
	        "id" : id,
	    }, function(data) {
            if ($.isEmptyObject(data)) {
                showErrorMsg("用户已删除");
            } else {
                $("#user_name").val(data.name);
                $("#user_phone").val(data.phone);
                // $("#user_img").attr("src", data.img)
                $("input[type=radio][name=gender][value=" + data.gender + "]").attr("checked", true);
                $("#user_signature").val(data.signature);
                $("#user_country").val(data.country);
                $("#user_province").val(data.province);
                $("#user_city").val(data.city);
                $("#user_area").val(data.area);
                $("#userId").val(id);
                refreshTable("userContentTable", "../content/getContents?uid=" + id);
                showModal("userPreview");
            }
	    });
	} else if (type == "content") {
		$.post("../content/getContent", {
	        "id" : id,
	    }, function(data) {
            if ($.isEmptyObject(data)) {
                showErrorMsg("资讯已删除");
            } else {
				$("#userImg").attr('src', data.userImage)
				$("#userNameP").html("<strong>" + data.userName + "</strong>")
				//需要同后台设置同步
				var html = "";
				if (data.tableName == "tb_video") {
					var url = "";
					if (data.map.video_file_url != null && data.map.video_file_url != "") {
						url = data.map.video_file_url;
					}
					if (data.map.video_url != null && data.map.video_url != "") {
						url = data.map.video_url;
					}
					html = '<div class="col-sm-12" style="height:160px">'
						+ '<iframe src="' + url + '" style="max-height:100%"></iframe>'
						+ '</div>'
				} else if (data.tableName == "tb_images") {
					var imgs = data.map.images.split(",");
					for (var i = 0; i < imgs.length; i++) {
						html = html + '<div class="col-sm-6 col-md-4" style="padding-right: 0px; padding-left: 0px; height: 100px;text-align:center;">'
									+ '<a href="#" class="thumbnail" style="margin-bottom: 0px; padding: 0px;">'
									+ '<img alt="" src="' + imgs[i] + '" style="max-height:100px">'
									+ '</a>'
									+ '</div>'
					}
				} else if (data.tableName == "tb_img") {
					html = '<div class="col-sm-12" style="height:500px">'
						+ '<a href="#" class="thumbnail" style="margin-bottom: 0px; padding: 0px;">'
						+ '<img alt="" src="' + data.map.img + '" style="max-height:100%">'
						+ '</a>'
						+ '</div>'
				}
				$("#contentDiv").html(html);
				$("#title").text(data.content);
				$("#contentId").val(id);
				showModal("contentPreview");
            }
		});
	} else if (type == "activity") {
		$.post("../activity/getActivityForUI.do", {
			"id" : id,
		}, function(activity) {
            if ($.isEmptyObject(activity)) {
                showErrorMsg("活动已删除");
            } else {
                var imgsDivs = "", imgsLis = "";
                var imgsArr = activity.imgs.split(",");
                for (var i = 0; i < imgsArr.length; i++) {
                    var divClass = "";
                    var divAlt = "";
                    if (i == 0) {
                        divClass = 'active';
                        divAlt = 'alt="First slide"';
                    }
                    imgsDivs = imgsDivs + '<div class="item ' + divClass + '"><img src="' + imgsArr[i] + '" ' + divAlt + '></div>';
                    imgsLis = imgsLis + '<li data-target="#myCarousel" data-slide-to="0" class="' + divClass + '"></li>';
                }
                $("#activityImgsOl").html(imgsLis);
                $("#activityImgsDiv").html(imgsDivs);
                $("#activityContent").html(activity.content);
                $("#activityUserName").html(activity.userName);
                var date = dateToString(activity.startTime, "yyyy.MM.dd") + " - " + dateToString(activity.endTime, "MM.dd");
                $("#activityDate").html(date);
                $("#activityAddress").html(activity.provincesName + "·" + activity.cityName + "·" + activity.address);
                var img = '<img src="' + activity.img + '">'
                $("#activityImg").html(img);
                $("#activityId").val(id);
                showModal("activityPreview");
            }
		});
	}
}

function delByContent() {
	var id = $("#contentId").val();
	if (id != "") {
		$.post("../content/delete", {
			"id" : id
		}, function (data) {
            //关闭弹出框
            hideModal("contentPreview");
			//提示处理结果
            if (data) {
                update();
            } else {
                showErrorMsg("修改失败");
            }
        })
	}
}

function delByUser() {
	var id = $("#userId").val();
	if (id != "") {
        $.post("../user/delete", {
            "id" : id
        }, function (data) {
            //关闭弹出框
            hideModal("userPreview");
            //提示处理结果
            if (data) {
                update();
            } else {
                showErrorMsg("修改失败");
            }
        })
	}
}

function delByActivity() {
    var id = $("#activityId").val();
    if (id != "") {
        $.post("../activity/del", {
            "id" : id
        }, function (data) {
            //关闭弹出框
            hideModal("activityPreview");
            //提示处理结果
            if (data) {
                update();
            } else {
                showErrorMsg("修改失败");
            }
        })
    }
}


function onUserContentDate(value, row, index) {
	var date = dateToString(row.delivery, "yyyy-MM-dd");
    var arr = [
    	date
        ]
    return arr.join('');
}

function onContentType(value, row, index) {
	var contentType = row.tableName;
	var cType = contentType == "tb_images" ? "多图" : (contentType == "tb_img" ? "单图" : (contentType == "tb_video" ? "视频" : "未知"));
    var arr = [
    	cType
        ]
    return arr.join('');
}

function onContentHref(value, row, index) {
    var arr = [
        '<button  class="btn btn-default btn-xs" href="javascript:void(0)" onclick="hrefThere(' +  row.contentId + ')">',
        '<i class="fa fa-eye"></i>',
        '</button>',
    ];
    return arr.join('');
}

function hrefThere(id) {
	window.open("userContentPreiew?id=" + id);
}
