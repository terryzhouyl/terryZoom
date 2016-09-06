$(function(){
	//初始化声音
	soundManager.useFlashBlock = false;
	soundManager.url = ctx+"/resource/plus/js/"
	soundManager.debugMode = false;
	soundManager.consoleOnly = false;
	soundManager.setup({preferFlash: false});
});

function playMusic(musicLink) {
	soundManager.play("mySound11",ctx+musicLink);
}

//栏目收起展开
$(function(){
	$('.ruleTit em').click(function(){
		if($(this).html()=="展开"){
		$(this).parent().parent().find('.editRule').show();	
		$(this).parent().parent().find('.ruleCon').hide();	
		$(this).html("收起");
		}else{
		$(this).parent().parent().find('.editRule').hide();	
		$(this).parent().parent().find('.ruleCon').show();
		$(this).html("展开");
		}
	});
});

//打开新建栏目
$(function(){
	$('.newRule a').click(function(){
		$('.theNewRule').show();
		$('.theNewRule .editRule').show();
	});
});

//修改栏目名
function modflyRuleName(typeId){
	var ownWin = null;
	
	if(typeId==null) {
		ownWin = $(".newreplyform");
	} else {
		ownWin = $("#replyKeywordTypeForm_"+typeId);
	}
	
	var title = ownWin.find("input[name=title]").val();
	title = trimStr(title);
	if(title == ""){
		alert("请先添加规则名！");
		return;
	}
	if(title.length>30){
		alert("规则名不能大于30字！");
		return;
	}
	var url = ctx+"/admin/replytype/ajaxsave.json";
	
	
	
	var parms = {weixinId:weixinId, title:title};
	if(typeId!=null) {
		parms.id = typeId;
	}
	
	$.post(url,parms,function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			if(typeId==null) {
				ownWin.find("input[name=id]").val(result.data.id);
				ownWin.attr("id", "replyKeywordTypeForm_"+result.data.id);
				alert("添加成功");
			} else {
				alert("修改成功");
			}
		} 
	});
}

//打开添加关键字win
function keywordAdd(id, typeId, o) {
	openWin(".popBox.addKeys");
	if(typeId==null){
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	if(id==null) {
		$(".popBox.addKeys").find("input[name=keywordId]").val("");
		$(".popBox.addKeys").find("textarea").val("");
	} else {
		$(".popBox.addKeys").find("input[name=keywordId]").val(id);
		$(".popBox.addKeys").find("input[name=keyword]").val($(o).parent().find("p").html());
		$(".popBox.addKeys").find("textarea").val($(o).parent().find("p").html());
	}
	
	$(".popBox.addKeys").find("input[name=typeId]").val(typeId);
}

//保存关键字
function keywordSave(){
	var typeId = $(".popBox.addKeys").find("input[name=typeId]").val();
	var id = $(".popBox.addKeys").find("input[name=keywordId]").val();
	var oKeyword = $(".popBox.addKeys").find("input[name=keyword]").val();
	if(typeId=="") {
		alert("请先添加规则名！");
		return false;
	}
	
	var keyword = trimStr($(".popBox.addKeys").find("textarea").val());
	if(keyword=="") {
		alert("关键字不能为空！");
		return;
	}else if(keyword.length>15) {
		alert("关键字不能大于15个字！");
		return;
	}
	var url = ctx+"/admin/reply/checkKeyword.json";
	var parms = {keyword:keyword};
	if(id!=null||id=="") {
		parms.id=id;
	}
	
	if(oKeyword!=keyword) {
		$.post(url,parms,function(result){
			result = eval("("+result+")");
			if(result.status == "true" || result.status == true){
				alert(result.msg);
			} else {
				parms = {keyword:keyword,weixinId:weixinId,typeId:typeId};
				url = ctx+"/admin/replykeyword/ajaxsave.json";
				if(id!=null||id=="") {
					parms.id=id;
				}
				
				$.post(url,parms,function(result){
					result = eval("("+result+")");
					if(result.status == "true" || result.status == true){
						alert(result.msg);
						if(id==null||id=="") {
							var html = '<input type="hidden" name="keywordsList[]" value="'+result.data.id+'"/><li class="fixed" rel="'+result.data.id+'"><input type="checkbox" value="'+result.data.id+'"/><p>'+keyword+'</p><a href="javascript:void(0);" onclick="keywordAdd('+result.data.id+','+result.data.typeId+',this);">编辑</a><span onclick="setMatchType('+result.data.id+',2,this);" class="cur">已全匹配</span></li>';
							$("#replyKeywordTypeForm_"+typeId).find(".keyEdit ul").append(html);
						}else {
							$("#replyKeywordTypeForm_"+typeId).find(".keyEdit ul li[rel="+result.data.id+"] p").html(keyword);
						}
						
						closeWin();
					} 
				});
			}
		});
	}else {
		closeWin();
	}
}

//设置关键字回复类型
function setMatchType(id, matchType, o){
	alert(id);
	alert(matchType);
	alert(o);
	$.post(ctx+"/admin/replykeyword/ajaxChanageMatchType.json",{id:id,matchType:matchType},function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			if(matchType == 2) {
				$(o).html("全匹配");
				$(o).attr("onclick","setMatchType("+id+",1,this);");
				$(o).removeClass("cur");
			} else {
				$(o).attr("onclick","setMatchType("+id+",2,this);");
				$(o).addClass("cur");
				$(o).html("已全匹配");
			}
		} 
	});	
}

//初始化窗口关闭事件
$(function(){
	$(".closeBtn.levelA").click(function(){
		closeWin();
	});
	
	$(".popBox .gray_btn").click(function(){
		closeWin();
	});
});

//打开回复文字窗口
function openReplyWZWin(typeId, id) {
	openWin(".popBox.addReply");
	if(typeId==null){
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	if(id!=null) {
		$(".popBox.addReply").find("input[name=id]").val(id);
		$(".popBox.addReply").find("textarea").val($("#replyKeywordTypeForm_"+typeId).find(".replyEdit ul li[rel="+id+"] p").html());
	}else {
		$(".popBox.addReply").find("input[name=id]").val("");
		$(".popBox.addReply").find("textarea").val("");
	}
	
	$(".popBox.addReply").find("input[name=typeId]").val(typeId);		
}

//打开回复图文窗口
function openReplyTTWin(typeId) {
	openWin(".popBox.tt");
	if(typeId==null){
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	$(".popBox.tt").find("input[name=typeId]").val(typeId);		
}

//打开回复音乐窗口
function openReplyYYWin(typeId) {
	openWin(".popBox.yy");
	if(typeId==null){
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	$(".popBox.yy").find("input[name=typeId]").val(typeId);		
}

//打开回复视频窗口
function openReplySPWin(typeId) {
	openWin(".popBox.sp");
	if(typeId==null){
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	$(".popBox.sp").find("input[name=typeId]").val(typeId);		
}

//保存图文，音乐，视频按钮事件
$(function(){
	$(".popBox").find("a.green_btn").click(function(){
		var msgType = $(this).attr("rel");
		if(msgType > 0) {
			var ownWin = null;
			var url = ctx+"/admin/replycontent/ajaxsave.json";
			var parms = {msgType:msgType,weixinId:weixinId};
			var dataId = null;
			var id = null;
			
			if(msgType==1) {
				ownWin = $(".popBox.addReply");
				parms.textContent = ownWin.find("textarea").val();
				if(ownWin.find("input[name=id]").val()!="") {
					id=ownWin.find("input[name=id]").val();
					parms.id = ownWin.find("input[name=id]").val();
				}
				
				if(trimStr(ownWin.find("textarea").val())=="") {
					alert("回复内容不能为空！");
					return;
				} else {
					if(ownWin.find("textarea").val().length > 2048){
						alert("回复内容不能大于300字！");
						return;
					}
				}
			} else if(msgType==2){
				ownWin = $(".popBox.tt");
				dataId = ownWin.find("iframe").contents().find("#selectDataId").val();
			} else if(msgType==3){
				ownWin = $(".popBox.yy");
				dataId = ownWin.find("iframe").contents().find("#selectDataId").val();
			} else if(msgType==4){
				ownWin = $(".popBox.sp");
				dataId = ownWin.find("iframe").contents().find("#selectDataId").val();
			}
			
			if(dataId!=null) {
				parms.dataId=dataId;
			}
			
			var typeId = ownWin.find("input[name=typeId]").val();
			if(typeId=="") {
				alert("请先添加规则名！");
				return false;
			}
			parms.typeId = typeId;
			$.post(url,parms,function(result){
				result = eval("("+result+")");
				if(result.status == "true" || result.status == true){
					var html = "";
					if(msgType==1) {
						if(id==null) {
							var html = '<input type="hidden" name="keywordContentList[]" value="'+result.data.id+'"/><li class="fixed" rel="'+result.data.id+'"><input type="checkbox" value="'+result.data.id+'"/><p>'+result.data.textContent+'</p><a href="javascript:void(0);" onclick="openReplyWZWin('+result.data.typeId+','+result.data.id+')">编辑</a><a href="javascript:void(0);" onclick="setDefaultReply('+result.data.typeId+','+result.data.id+')" class="moren">默认</a></li>';
							var count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(0).find("span").text());
							$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(0).find("span").text(count+1);
						} else {
							$("#replyKeywordTypeForm_"+typeId).find(".replyEdit ul.replyContentList li[rel="+result.data.id+"] p").html(result.data.textContent);
							closeWin();
							return;
						}
					} else if(msgType==2) {
						var html = '<li class="fixed" rel="'+result.data.id+'"><input type="checkbox" rel="2" value="'+result.data.replyData.id+'"/><p>'+result.data.replyData.title+'</p><div style="clear:both;"></div><div class="videoPlay"><img width="320" height="160" src="'+ctx+'/'+result.data.replyData.picUrl+'" /></div><a href="javascript:void(0);" onclick="setDefaultReply('+result.data.typeId+','+result.data.id+')" class="moren">默认</a></li>';
						var count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(1).find("span").text());
						$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(1).find("span").text(count+1);
					} else if(msgType==3) {
						var html = '<li class="fixed" rel="'+result.data.id+'"><input type="checkbox" rel="3" value="'+result.data.replyData.id+'"/><p>'+result.data.replyData.title+'</p><div style="clear:both;"></div><div rel="'+result.data.replyData.musicLink+'" class="audioPlay" onclick="playMusic(this);">点击播放&nbsp;&nbsp;</div><a href="javascript:void(0);" onclick="setDefaultReply('+result.data.typeId+','+result.data.id+')" class="moren">默认</a></li>';
						var count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(2).find("span").text());
						$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(2).find("span").text(count+1);
					} else if(msgType==4) {
						var html = '<li class="fixed" rel="'+result.data.id+'"><input type="checkbox" rel="4" value="'+result.data.replyData.id+'"/><p>'+result.data.replyData.title+'</p><div style="clear:both;"></div><div class="videoPlay"><video style="width:158px;height:116px;" src="'+ctx+result.data.replyData.videoLink+'" controls="controls">您的浏览器不支持</video></div><a href="javascript:void(0);" onclick="setDefaultReply('+result.data.typeId+','+result.data.id+')" class="moren">默认</a></li>';
						var count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(3).find("span").text());
						$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(3).find("span").text(count+1);
					}
					
					$("#replyKeywordTypeForm_"+typeId).find(".replyContentList").append(html);
					closeWin();
				} 
			});
		}
	});
});

//定义回复保存按钮
function replySave(typeId){
	if(typeId==null) {
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	if(typeId=="") {
		alert("请先添加规则名！");
		return;
	} else {
		window.location.reload();
	}
}

//定义回复删除按钮
function replyDelete(typeId){
	if(typeId==null) {
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
	if(typeId=="") {
		window.location.reload();
		return;
	} else {
		$.post(ctx+"/admin/replytype/ajaxDelete.json",{id:typeId},function(result){
			result = eval("("+result+")");
			if(result.status == "true" || result.status == true){
			    window.location.reload();
			} 
		});
	}
}

//删除关键字
function keywordDelete(typeId){
	if(typeId==null) {
		typeId=$(".newreplyform").find("input[name=id]").val();
	}
	
  	var ids = '';
	$("#replyKeywordTypeForm_"+typeId).find(".keyEdit ul li input").each(function(){
		if($(this).attr("checked") == "checked") {
			ids += $(this).val()+","
		}
	});

	if(ids != '') {
		ids = ids.substring(0, ids.length-1); 
		$.post(ctx+"/admin/replykeyword/btnAjaxDelete.json",{ids:ids},function(result){
			result = eval("("+result+")");
			if(result.status == "true" || result.status == true){
				$("#replyKeywordTypeForm_"+typeId).find(".keyEdit ul li input").each(function(){
					if($(this).attr("checked") == "checked") {
						$(this).parent().remove();
					}
				});
			} 
		});
	}	
}

//删除回复
function replyContentDelete(typeId) {
	if(typeId==null) {
		typeId=$(".newreplyform").find("input[name=id]").val();
	}	
	
    var ids = '';
    var textCount = 0;
    var articleCount = 0;
    var musicCount = 0;
    var videoCount = 0;
    var msgType = 1;
    
    $("#replyKeywordTypeForm_"+typeId).find(".replyEdit ul.replyContentList li input").each(function(){
		if($(this).attr("checked") == "checked") {
			ids += $(this).val()+","
			msgType = $(this).attr("rel");
			
			if(msgType == 1) {
				textCount++;
			}else if(msgType == 2) {
				articleCount++;
			}else if(msgType == 3) {
				musicCount++;
			}else if(msgType == 4) {
				videoCount++;
			}
		}
	});
    
    if(ids != '') {
		ids = ids.substring(0, ids.length-1); 
		$.post(ctx+"/admin/replycontent/btnAjaxDelete.json",{ids:ids},function(result){
			result = eval("("+result+")");
			if(result.status == "true" || result.status == true){
				$("#replyKeywordTypeForm_"+typeId).find(".replyEdit ul.replyContentList li input").each(function(){
					if($(this).attr("checked") == "checked") {
						$(this).parent().remove();
					}
				});
				
				var count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(0).find("span").text());
				$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(0).find("span").text(count-textCount);
				
				count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(1).find("span").text());
				$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(1).find("span").text(count-articleCount);
				
				count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(2).find("span").text());
				$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(2).find("span").text(count-musicCount);
				
				count = parseInt($("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(3).find("span").text());
				$("#replyKeywordTypeForm_"+typeId).find(".replyTypeT p").eq(3).find("span").text(count-videoCount);
			} 
		});
	}	    
}

//设置默认回复
function setDefaultReply(typeId,id){
	if(typeId==null) {
		typeId=$(".newreplyform").find("input[name=id]").val();
	}	
	$.post(ctx+"/admin/replytype/sendDefaultReply.json",{id:id,typeId:typeId},function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			alert(result.msg);
			$("#replyKeywordTypeForm_"+typeId).find(".replyEdit a.moren").css("color","#B2B2B2");
			$("#replyKeywordTypeForm_"+typeId).find(".replyEdit ul.replyContentList li[rel="+id+"] a.moren").css("color","#FF0000");
		} 
	});
}

//打开窗口
function openWin(element) {
	$(element).show();
	var popWrap_H = $(element).height();
    var window_H = $(window).height();
	if(popWrap_H>window_H){
		$(element).css("margin-top","0");
	}else{
		var hh = (window_H - popWrap_H)/2;
		$(element).css("margin-top", hh );
	}	
	
	
	$(element).find(".zhezhao").width($(window).width());
	$(element).find(".zhezhao").height($(window).height());
}

//关闭窗口
function closeWin() {
	$(".popBox").hide();
}