// JavaScript Document
$(function(){
	setTimeout(function(){theH();},100);
	$('.menu h2').click(function(){
		var liC=$(this).parent('li');	
		if(liC.hasClass('cur')){
			liC.removeClass('cur');
			$(this).find('.secMenu').hide();
			$(this).find('i').removeClass('icon-minus').addClass('icon-plus');	
		}else{
			liC.addClass('cur').siblings().removeClass('cur');
			liC.siblings().find('h2 i').removeClass('icon-minus').addClass('icon-plus');
			$(this).find('.secMenu').show();	
			$(this).find('i').removeClass('icon-plus').addClass('icon-minus');
		}
		theH();
	});
	$('.secMenu li').click(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
		$(this).parents('li').siblings().find('.secMenu li').removeClass('cur');
	})
})
$(window).resize(function(){
	theH();
});
function theH(){
	var winH=$(window).height();
	var conH=$('.rightBox .theCon').height()+30;
	var leftH=$('.leftBox .menu').height()+30;
	var headerH=120;
	$('.leftBox').css({"min-height":winH-headerH-30});
	$('.rightBox').css({"min-height":winH-headerH-30});
	//alert(winH+"+"+leftH+"+"+conH);
	if(winH>conH+headerH && winH>leftH+headerH){
		$('.leftBox').css({height:winH-headerH-30});
		$('.rightBox').css({height:winH-headerH-30});
	}else{
		if(conH > leftH){
			$('.leftBox').css({height:conH-30});
			$('.rightBox').css({height:conH-30});
		}else{
			$('.rightBox').css({height:leftH-30});
			$('.leftBox').css({height:leftH-30});
		}
	}
}
/**
 * 验证数字
 * @param s
 * @returns {Boolean}
 */
function CheckNum(s) {
	if(!isNaN(s)) {
		return true;
	} else {
		return false;
	}
}

/**微信分享*/
function share(imgUrl,lineLink,descContent,shareTitle){
		var appid = '';
      function shareFriend() {
          WeixinJSBridge.invoke('sendAppMessage',{
              "appid": appid,
              "img_url": imgUrl,
              "img_width": "200",
              "img_height": "200",
              "link": lineLink,
              "desc": descContent,
              "title": shareTitle
          }, function(res) {
              //_report('send_msg', res.err_msg);
          })
      }
      function shareTimeline() {
          WeixinJSBridge.invoke('shareTimeline',{
              "img_url": imgUrl,
              "img_width": "200",
              "img_height": "200",
              "link": lineLink,
              "desc": descContent,
              "title": descContent
          }, function(res) {
                 //_report('timeline', res.err_msg);
          });
      }
      function shareWeibo() {
          WeixinJSBridge.invoke('shareWeibo',{
              "content": descContent,
              "url": lineLink
          }, function(res) {
              //_report('weibo', res.err_msg);
          });
      }
      // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
      document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
          // 发送给好友
          WeixinJSBridge.on('menu:share:appmessage', function(argv){
              shareFriend();
          });
          // 分享到朋友圈
          WeixinJSBridge.on('menu:share:timeline', function(argv){
              shareTimeline();
          });
          // 分享到微博
          WeixinJSBridge.on('menu:share:weibo', function(argv){
              shareWeibo();
          });
      }, false);
}


function SetCookie(name,value,sec) {
    var exp  = new Date();
    exp.setTime(exp.getTime() +  sec*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString() + ";path=/";
}

function getCookie(name) {
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr == null || arr ==""){
		return "";
	}else{
		return unescape(arr[2]); 
	}
}
//按钮点击之后loadstart
function loadStart(){
	var html="<div class='loading'></div>";
	$("body").append(html);
}
//按钮事件结束之后loadend
function loadEnd(){
	$(".loading").remove();
}

