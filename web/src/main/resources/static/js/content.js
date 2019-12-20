function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

$(function() {

	var url = document.location.href;

	console.log(url);
	 var id = getQueryString('contentId')
	console.log(id)


	var currentValue = $('#currentValue');

	$('#defaultSlider').change(function() {
		currentValue.html(this.value);
	});

	$('#defaultSlider').change();

	$.ajax({
			// url: 'http://132.232.121.223:8080/hgcms/article/listToPhoneCS',
			url: 'http://134.175.187.249:8080/hgcms/article/listToPhoneCS',
			type: 'post',
			data: {
				contentId: id
//contentId:33
			},
			dataType: 'jsonp', //jsonp格式访问
			jsonpCallback: 'callback' //回调函数
		})
		.done(function(data) {
			console.log(data);

			var add = data.rows;
			console.log(add)
			var list = "";
			for(var i = 0; i < add.length; i++) {
				list += '<div class="content" id="' + add[i].articleId + '">' +
					'<h3 >'  + add[i].title+'</h3>' +
					'<div class="adminname">'+
					'<span>'+ add[i].adminName +'</span>'+
					'<span>'+ add[i].timeStr +'</span>'+
					'</div>'+
					'<div class = "text">' + add[i].articleText + '</div>' + '</div>'

			}
			$('.home').html(list)
		})
		.fail(function() {
			alert('服务器超时，请重试！');
		});

	function callback(data) {
		console.log(data)
	}

})