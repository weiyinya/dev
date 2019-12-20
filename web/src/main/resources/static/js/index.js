$(function() {
	var imgs = "";
	var videourl = "";
	var url = document.location.href;
	var list = url.split("?");
	console.log(list)

	console.log(list[1]);
	var listname = list[1].split("=");
	var sc = listname[1]
	console.log(decodeURI(sc));

	$.ajax({
			// url: 'http://132.232.121.223:8080/hgcms/content/getH5ContentId',
			url: 'http://134.175.187.249:8080/hgcms/content/getH5ContentId?limit=10&offset=0',
			// url: 'http://www.hygoedm.com:8080/hgcms/content/getH5ContentId?limit=10&offset=0',
			type: 'post',
			data: {
				      contentId:sc
//				contentId: 2835
			},
			dataType: 'jsonp', //jsonp格式访问
			jsonpCallback: 'callback' //回调函数
		})
		.done(function(data) {
			console.log(data);
			var num = data.thumbUp;
			if(num == false) {
				//	点赞
				imgs = "img/dianzicon@2x.png";

			} else if(num == true) {
				imgs = "img/dianzicon2@2x.png"
			}
		

			function myFun(result) {
				var cityName = result.name;

				console.log("当前定位城市:" + cityName);
				console.log(data.map.img);
				if(data.map.img == undefined) {
					videourl = data.map.video_url;
					//	js视频
					console.log(videourl)

					$(".hotdoor").append('<div class="hot" id="' + data.userId + '">' +

						'<div class="txt1">' +
						'<ul>' +
						'<li class="nove">' +
						'<div class="shbo">' +

						'<img src="' + data.userImage + '" />' +
						'</div>' +

						'</li>' +
						'<li>' +
						'<p class="name">' + data.userName + '</p>' +
						'<p>' +


						'<span>' + data.tbContentCity + '</span>' +
						'</p>' +
						'</li>' +
						
						'<li>' +
						'<img src="img/图层248@3x.png" class="more"/>' +
						'</li>' +
						'</ul>' +
						'</div>' +

						'<div class="txt">' +
						'<div class="shipin">' +
						'<div class="centerInfo">' +
						'<div id="testVideo"></div>' +
						'</div>' +
						'</div>' +
						'</div>' +
						'<div class="txt2">' +
						'<img src="' + imgs + '" />' +
						'<img src="img/评论icon@2x.png" />' +
						'<img src="img/shoucicon@2x.png" />' +
						'</div>' +

                        '<div class="txt3">'+ data.likeNum+
                        '赞</div>'+
                        '<div class="txt4">'+
                        '<span>'+data.content+'</span>'+
                        '<span></span>'+
                        '</div>'+
                        '<div class="txt5">'+
                        '共'+ data.commentNum +'条评论'+
                        '</div>'+
						'<div class="txt6">' + data.map.refresh_date + '</div>' +
						'</div>')
					videowrap();
				} else {
					$(".hotdoor").append('<div class="hot" id="' + data.userId + '">' +

						'<div class="txt1">' +
						'<ul>' +
						'<li class="nove">' +
						'<div class="shbo">' +

						'<img src="' + data.userImage + '" />' +
						'</div>' +

						'</li>' +
						'<li>' +
						'<span class="name">' + data.userName + '</span>' +
						'<span>' + data.tbContentCity + '</span>' +
						
						'</li>' +
						
						'<li>' +
						'<img src="img/图层248@3x.png" class="more"/>' +
						'</li>' +
						'</ul>' +
						'</div>' +

						'<div class="swiper-container">' +
						'<div class="swiper-wrapper">' +
						'<div class="swiper-slide">' +
						'<img src="' + data.map.img + '" />' +
						'</div>' +

						'</div>' +

						'<div class="swiper-pagination"></div>' +

						'</div>' +

						'<div class="txt2">' +
						'<img src="' + imgs + '" />' +
						'<img src="img/评论icon@2x.png" />' +
						'<img src="img/shoucangicon@2x.png" />' +
						'</div>' +

                        '<div class="txt3">'+ data.likeNum+
                        '赞</div>'+
                        '<div class="txt4">'+
                        '<span>'+data.content+'</span>'+
                        '<span></span>'+
                        '</div>'+
                        '<div class="txt5">'+
                        '共'+ data.commentNum +'条评论'+
                        '</div>'+
						'<div class="txt6">' + data.map.refresh_date + '</div>' +
						'</div>')
				}

			}

			var myCity = new BMap.LocalCity();
			myCity.get(myFun);

		})
	//  .fail(function() {
	//      alert('服务器超时，请重试！');
	//  });

	function callback(data) {
		console.log(data)
	}

	function videowrap() {
		
		var videoWrap = document.getElementById('testVideo')
	var fullScreen = document.getElementById('getFullScreen')
	var video = new Dvideo({
		ele: '#testVideo',
		title: 'Pneumatic Tokyo - EnV',
		nextVideoExtend: function() {
			alert('您点击了下一页')
		},
		showNext: true,
		width: '580px',
		height: '292px',
		src: videourl,
		autoplay: true,
		setVideoDefinition: function(type, e, current) {
			if(type === '0') {
				alert('你点击了标清')
				// video.setVideoInfo('這是標清','这里填写视频的标清地址',current)
			}
			if(type === '1') {
				alert('你点击了标清')
				// video.setVideoInfo('這是標清','这里填写视频的高清地址',current)
			}
			if(type === '2') {
				alert('你点击了标清')
				// video.setVideoInfo('這是標清','这里填写视频的超清地址',current)
			}
			video.showLoading(false)

			// setTimeout(function () {
			// 	video.videoEle.currentTime = current
			// 	video.videoPlay()
			// 	video.showLoading(false)
			// }, 3000)
		},
	})
	}

	// 全屏
	function setFullScreen() {
		video.launchFullScreen(videoWrap)
	}

	// 播放
	function play() {
		video.videoPlay()
	}

	// 暂停
	function pause() {
		video.videoPause()
	}

	// 播放暂停
	function playpause() {
		video.videoPlayPause()
	}

	function setVolume(v) {
		video.updateVolume(v)
	}

	function setBackRate(index) {
		video.setPlayBackRate(index)
	}

	function setVideoForward() {
		video.videoForward(10)
	}

	function setVideoRewind() {
		video.videoRewind(10)
	}

	function showLoading() {
		video.showLoading(true, '视频清晰度切换中，请稍等')
	}

	function showTopBottomCtrlNotClose() {
		video.showTopBottomCtrl()
	}

	function hideTopBottomCtrlLi() {
		video.hideTopBottomCtrl(true)
	}

	function showTopBottomCtrl() {
		video.showTopBottomCtrl(true)
	}

	function hideTopBottomCtrl() {
		video.hideTopBottomCtrl()
	}

	function setVideoSize() {
		video.updateVideoSize(720, 480)
	}

})