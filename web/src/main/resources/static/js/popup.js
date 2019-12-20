//遮罩层
function openNewDiv() {
	// 新激活图层
	var newDiv = document.createElement("div");
	newDiv.id = "newDIV";
	newDiv.style.zIndex = "9999";
	newDiv.style.position = "absolute";
	newDiv.style.left = (parseInt(document.body.scrollWidth) - 300) / 2 + "px"; // 屏幕居中
	var x = document.documentElement.clientHeight;
	var y = document.documentElement.clientWidth;
	// mask图层
	var m = "mask";
	var newMask = document.createElement("div");
	newMask.style.position = "absolute";
	_scrollWidth = Math.max(document.body.scrollWidth,
			document.documentElement.scrollWidth);
	_scrollHeight = Math.max(document.body.scrollHeight,
			document.documentElement.scrollHeight);
	newMask.style.width = _scrollWidth + "px";
	newMask.style.height = _scrollHeight + "px";
	newMask.style.top = "0px";
	newMask.style.left = "0px";
	newMask.style.background = "#33393C";
	newMask.style.filter = "alpha(opacity=40)";
	newMask.style.opacity = "0.12";
	newMask.style.zIndex = "20";
	newMask.innerHTML = '<div style="position:absolute;top:'
			+ (x / 2 - 32)
			+ 'px;left:'
			+ (y / 2 - 32)
			+ 'px;color:#FFF;z-index:50"><i class="fa fa-spinner fa-spin fa-4x"></i></div>';
	document.body.appendChild(newDiv);
	document.body.appendChild(newMask);
}
// 撤销遮罩层
function delOpenDiv() {
	$("#newDIV").next().remove();
	$("#newDIV").remove();
}

// 提示
function showMsg(text) {
	if ($('#top_alert1 span').text().length > 0) {
		$('#top_alert1').empty().append('<span>' + text + '</span>');
		$('#top_alert1').css('display', 'block');
	} else {
		$('body')
				.prepend(
						'<div style="z-index:9999;width:350px;margin-top:25%;padding:10px 0;background:#90EE90;border:1px solid #f8efb0;color:#e69041;position:fixed;text-align:center;left:40%;top:0;" id="top_alert1"><span>'
								+ text + '</span></div>');

	}
	$('#top_alert1').fadeOut(3000);
}
// 提示
function showErrorMsg(text) {
	if ($('#top_alert span').text().length > 0) {
		$('#top_alert').empty().append('<span>' + text + '</span>');
		$('#top_alert').css('display', 'block');
	} else {
		$('body')
				.prepend(
						'<div style="z-index:9999;width:350px;margin-top:25%;padding:10px 0;background:#EE2C2C;border:1px solid #f8efb0;color:#e69041;position:fixed;text-align:center;left:40%;top:0;" id="top_alert"><span>'
								+ text + '</span></div>');

	}
	$('#top_alert').fadeOut(3000);
}
// js打开bootstrap模态框
function showModal(id) {
	$("#" + id).modal('show', {
		backdrop : true
	});
}
// js关闭bootstrap模态框
function hideModal(id) {
	$("#" + id).modal('hide');
}
// 用于bootstrap-table刷新表
function refreshTable(tableID, url) {
	$("#" + tableID).bootstrapTable('refresh', {
		url : '' + url
	});
}
// 用于bootstrap-table删除时使用的刷新表
function refreshRemoveTable(tableID, url) {
	$("#" + tableID).bootstrapTable('refreshRemove', {
		url : '' + url
	});
}
// 用于bootstrap-table增加时使用的刷新表
function refreshAddTable(tableID, url) {
	$("#" + tableID).bootstrapTable('refreshAdd', {
		url : '' + url
	});
}
// 用于bootstrap-table删除时使用的刷新表
function refreshArrayRemove(tableID, url) {
	$("#" + tableID).bootstrapTable('refreshArrayRemove', {
		url : '' + url
	});
}
// 用于bootstrap-table刷新当前页面
function refreshNewTable(tableID, url) {
	$("#" + tableID).bootstrapTable('refreshNew', {
		url : '' + url
	});
}
function chbox(tagName) {
	var arr = [];
	var inputs = document.getElementsByName(tagName);
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
		if (obj.type == 'checkbox') {
			if (obj.checked == true) {
				arr.push(obj.value);
			}
		}
	}
	return arr;
}
function emptyCheckbox(tagName) {
	var inputs = document.getElementsByName(tagName);
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
		if (obj.type == 'checkbox') {
			if (obj.checked == true) {
				obj.checked = false;
			}
		}
	}
}

function radioChecked(tagName, value) {
	var inputs = document.getElementsByName(tagName);
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
        if (obj.value == value) {
			if (obj.checked == false) {
				obj.checked = true;
			}
        } else {
            obj.checked = false;
		}
    }
}

function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
// 设置下拉框默认值
function selectValue(sId, value) {
	var s = document.getElementById(sId);
	var ops = s.options;
	for ( var i = 0; i < ops.length; i++) {
		var tempValue = ops[i].value;
		if (tempValue == value) {
			ops[i].selected = true;
			break;
		}
	}
}

/**
 * @param docID	需要添加的表格ID
 * @param value	需要添加的值
 * @param type	添加input的类型
 * @param name 	需要上传的元素名
 */
function addNewRow(docID, value, type, name) {
	var obj = document.getElementById(docID);
	var row = obj.insertRow(-1);
	var c0 = row.insertCell(0);
	var c1 = row.insertCell(1);
	var arr;
	var n = name == "" ? "answerCall" : name;
	if (value == "") {
		arr = '<input type=' + type
				+ ' name="' + n + '" style="margin-left:1px;">';
	} else {
		arr = '<input type=' + type + ' name="' + n + '" value="' + value
				+ '" style="margin-left:1px;">';
	}
	c1.innerHTML = arr;
	c1.align = 'left';
	var c2 = row.insertCell(2);
	c2.innerHTML = '<button type="button"  onclick="removeRow(this, '
			+ "'"
			+ docID
			+ "'"
			+ ')" class="btn btn-default deleteCall" data-method="append"><a href="javascript:void(0)">[删除]</a></button>';
	changeFlag = true;
	try {
		comm_set_page_height();
	} catch (e) {
	}
}
/**
 * @param docID	需要添加的表格ID
 * @param name 	需要上传的元素名
 */
function addNewRowFile(docID, name) {
    var obj = document.getElementById(docID);
    var row = obj.insertRow(-1);
    var c0 = row.insertCell(0);
    var c1 = row.insertCell(1);
    var arr = '<input type="file" name="' + name
        + '" style="margin-left:1px;" onchange="setImagePreviews(this)">'
        + '<div></div>';
    c1.innerHTML = arr;
    c1.align = 'left';
    var c2 = row.insertCell(2);
    c2.innerHTML = '<button type="button"  onclick="removeRow(this, '
        + "'"
        + docID
        + "'"
        + ')" class="btn btn-default deleteCall" data-method="append"><a href="javascript:void(0)">[删除]</a></button>';
    changeFlag = true;
    try {
        comm_set_page_height();
    } catch (e) {
    }
}
/**
 * @param fontobj
 * @param docID
 */
function removeRow(fontobj, docID) {
	var obj = document.getElementById("" + docID);
	var n = fontobj.parentNode.parentNode.rowIndex;
	obj.deleteRow(n);
}

function removeArray(docID) {
	$("#" + docID + " tr").remove();
}

function chboxDefult(tagName, values) {
	var inputs = document.getElementsByName(tagName);
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
		var isChecked = false;
		for ( var j = 0; j < values.length; j++) {
			if ((obj.value + "") == values[j]) {
				obj.checked = true;
				isChecked = true;
				break;
			}
		}
		if (!isChecked) {
			obj.checked = false;
		}
	}
}

function chboxDefultCheck(tagName) {
	var inputs = document.getElementsByName(tagName);
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
		obj.checked = true;
	}
}

function checkDefultUL(tagName, values) {
	var uls = $(".droptrue [name=" + tagName + "]");
	var inputs = $("[name=" + tagName + "] input");
	for ( var j = 0; j < values.length; j++) {
		for ( var i = 0; i < inputs.length; i++) {
			var obj = $(inputs[i]).val();
			if (obj == values[j]) {
				$(uls[i]).parent().parent().next().find("ul").append(
						'<li class="ui-state-default" name="' + tagName + '">'
								+ $(uls[i]).html() + '</li>');
				$(uls[i]).remove();
			}
		}
	}
}

/**
 * @returns {Object}	地址栏 ？后面的参数对象	
 * 例如：/list.jsp?id=1&name=张三
 * 返回一个object对象，object.id=1;object.name=张三		
 */
function GetQueryString() {
	var url = window.location.search; // 获取url中"?"符后的字串
	return GetQueryStringUrl(url);
}
function GetQueryStringUrl(url) {
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(url.indexOf("?")+1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
/**
 * @param time	时间戳
 * @param fmt	需要转换的格式----支持yyyy-MM-dd HH:mm:ss
 * @returns		对应格式的时间显示
 */
function dateToString(time, fmt) {
	var date = new Date(parseInt(time));
	var o = {
		"M+" : date.getMonth() + 1, // 月份
		"d+" : date.getDate(), // 日
		"H+" : date.getHours(), // 小时
		"m+" : date.getMinutes(), // 分
		"s+" : date.getSeconds(), // 秒
		"q+" : Math.floor((date.getMonth() + 3) / 3), // 季度
		"S" : date.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

/**
 * @param inputID	需要清空的按钮ID
 * 用来清空type=file的按钮
 */
function emptyInputFile(inputID) {
	var file = document.getElementById(inputID);
	if (file.outerHTML) {
        file.outerHTML = file.outerHTML;
    } else { 
        file.value = "";
    }
}

//下面用于多图片上传预览功能
function setImagePreviews(avalue) {
    var docObj = avalue;
    var dd = docObj.nextSibling;
    dd.innerHTML = "";
    var imgSrc = "";
    if (docObj.files && docObj.files[0]) {
        imgSrc = window.URL.createObjectURL(docObj.files[0]);
    } else {
        imgSrc = document.selection.createRange().text;
    }
    dd.innerHTML += "<div style='float:left' > <img src='" + imgSrc + "' width='150px' height='180px' display = 'block'/> </div>";
    return true;
}