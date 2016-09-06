
/**
 * 动态效果
 */

var respInfo= "";
var id = "";
var respType = "";
//点击左侧边栏显示数据
$(function(){
	
	$("#weixinMenu").addClass("cur");
		
	$("#menu_navgtion ul li").click(function(){	
				
		var replyInfo =	$(this).find("span");
		id = $(replyInfo[0]).html();
		var menuName = $(replyInfo[1]).html();
		var menuKey = $(replyInfo[2]).html();
		var eventType = $(replyInfo[3]).html();
		respType = $(replyInfo[4]).html();
		respInfo = $(replyInfo[5]).html();
		
		if(eventType == "click") {
			$("#menuKey").val(menuKey);
			$("#eventType1").attr("checked","checked");	
			
			//显示tab页
			$(".writeURL").hide();
			$("#replyFrame").show(); 
			
			if(respType == "text") {
				$("#wenzi").addClass('iactive').siblings().removeClass('iactive');
				$('.wzArea ul li').eq(0).show().siblings().hide();
				$("#textareaContent").html(respInfo);
				$("#methodText").val("");
			}
			else if(respType == "method") {
				$("#method").addClass('iactive').siblings().removeClass('iactive');
				$('.wzArea ul li').eq(1).show().siblings().hide();
				$("#methodText").val(respInfo);
				$("#textareaContent").html("");
			}
			else {
				$("#wenzi").addClass('iactive').siblings().removeClass('iactive');
				$('.wzArea ul li').eq(0).show().siblings().hide();
				$("#textareaContent").html("");
			}			
		}
		else if(eventType == "view") {
			$("#eventType2").attr("checked","checked");				
			$("#replyFrame").hide();  
			$(".writeURL").show();
		}
		else {
			//初始化面板
			$("#menuKey").val("");
			//$("#eventType1").attr("checked","checked");				
			//显示tab页
			$(".writeURL").hide();
			$("#textareaContent").val("");
			$("#methodText").val("");
			$("#replyFrame").show(); 
		}
						
		$(this).addClass('cur').siblings().removeClass('cur');
	})
}) 


$(function(){
	
	//菜单类型的 填写框切换
	$("input[name=openOrClose]").click(function(){
		
		var sel = $("input[name=openOrClose]:checked").val();
		if(sel == "view"){
			$("#replyFrame").hide();  
			$(".writeURL").show();
		}
		else {
			$(".writeURL").hide();
			$("#replyFrame").show(); 
		}
	})
	
	
	//点击文字显示
	$('#wenzi').click(function(){
		//$("#save").show();
		var thisIndex=$(this).index();
		$(this).addClass('iactive').siblings().removeClass('iactive');
		$('.wzArea ul li').eq(thisIndex).show().siblings().hide();
		
		if(respType == "text") {			
			$("#textareaContent").html(respInfo);				
		}	
			else {
			$("#textareaContent").html("");	
		}
	});

		//点击方法显示
	$("#method").click(function(){
		//$("#save").show();
		var thisIndex=$(this).index();
		newReplyMsgType = $(this).attr("rel");
		$(this).addClass('iactive').siblings().removeClass('iactive');
		$('.wzArea ul li').eq(thisIndex).show().siblings().hide();
		
		if(respType == "method") { 
			//alert(replyContent);
			$("#methodText").html(respInfo);	
		}
		else {
			$("#methodText").val("");
		}
	});
	
	
})

//菜单鼠标移上添加 图标
$(function(){
	$('#menu_navgtion li span').hover(function(){
		$(this).parent('li').find('button').show();
		$(this).parent('li').find('ul li button').hide();
		},
		function(){
			$(this).parent('li').find('button').hide();
			$(this).parent('li').find('ul li button').hide();
		});
	$('#menu_navgtion li button').hover(function(){
		$(this).parent('li').find('button').show();
		$(this).parent('li').find('ul li button').hide();
		},
		function(){
			$(this).parent('li').find('button').hide();
			$(this).parent('li').find('ul li button').hide();
		});
	})


//添加一级菜单
function addRoot(){
	if($("#menu_navgtion").children("li").length>2) {
		alert("一级菜单不能超过三个");
	} else {
		$("#menu_navgtion").append('<li><input type="text" value=""/><a class="yes" style="display:block;" href="javascript:void(0)" onclick="editMenu(null, 0, this);">确定</a></li>');
	}
}

//添加二级菜单
function addChild(o) {
	var length = $(o).parent().find("ul").children("li").length;
	if(length>4) {
		alert("二级菜单不能超过五个");
	} else {
		var parentId = $(o).parent().attr("rel");
		if($(o).parent().find("ul").length>0) {
			$(o).parent().find("ul").append('<li><input type="text" value=""/><a class="yes" style="display:block;" href="javascript:void(0)" onclick="editMenu(null, '+parentId+', this);">确定</a></li>');
		} else {
			$(o).parent().append('<ul style="margin-left: 20px;"><li><input type="text" value=""/><a class="yes" style="display:block;" href="javascript:void(0)" onclick="editMenu(null, '+parentId+', this);">确定</a></li></ul>');
		}
	}
}


//编辑菜单
function toEdit(o){
	var name = $(o).parent().find("span:eq(1)").html();
	var id = $(o).parent().attr("rel");
	var parentId = $(o).parent().parent().attr("rel");
	$(o).parent().find("span:eq(1)").after('<input type="text" onchange="editMenu('+id+', '+parentId+', this);" value="'+name+'" maxLength="15" />');
	$(o).parent().find("span:eq(1)").hide();
	$(o).parent().find("button").hide();
	$(o).parent().find("a:eq(0)").show();
}


//图文点击事件效果 start
$("#tuwen").click(function(){
	$("#save").hide(); B 
    $(".popBox.tt").show();
    var popWrap_H = $(".popWrap.tt").height();
    var window_H = $(window).height();
    if(popWrap_H>window_H)
    {
        $(".popWrap.tt").css("margin-top","0");
    }
    else
    {
        var hh = (window_H - popWrap_H)/2;
        $(".popWrap.tt").css("margin-top", hh );
    }
});

$(".closeBtn.levelA").click(function(){
    $(".popBox").hide();
	$("#save").show();
});
$(".popBox .green_btn").click(function(){
	var iframe = $(this).parent().parent().find("iframe");
	newReplyDataId = iframe.contents().find("#selectDataId").val();
	$("#textContent").val("");
    $(".popBox").hide();
    onSave();
});
$(".popBox .gray_btn").click(function(){
	$("#textareaContent").val("");
	$("#textContent").val("");
    $(".popBox").hide();
	$("#save").show();
});

$(".closeBtn.wron").click(function(){
    $(".gshiWrong").hide();
});
$(".zhezhao").width($(window).width());
$(".zhezhao").height($(window).height());
//图文点击事件效果 end


//模拟点击事件 加载页面则选中第一栏
$(function(){
	$("#menu_navgtion ul li:eq(0)").trigger("click");
})

/**
* 数据交互
*/

//编辑菜单
function editMenu(id, parentId, o) {
	var name = $(o).parent().find("input").val();
	var parms = {parentId:parentId};
	if(name=="") {
		alert("菜单名不能为空！");
		return;
	} else if(name.length>15) {
		alert("菜单名不能大于15个汉字！");
		return;
	} else {
		parms.menuName=name;
	}
	
	if(id!=null) {
		parms.id=id;
	}
	
	$.post(ctx+"/admin/customMenu/saveMenu.json",parms,function(result){
		result = eval("("+result+")");		
		alert(result.msg);
		setTimeout(function(){window.location.reload();},300);
	});
}

//保存所有信息
function onSave() {
	
	var rType = "";
	var rContent = "";
	
	var eventType = $("input[name=openOrClose]:checked").val();
	var menuKey = $("#menuKey").val();
	
	
	if($("#wenzi").hasClass("iactive")) { //
		rType="text";
		rContent = $("#textareaContent").val();
	}
	else {
		rType = "method";
		rContent = $("#methodText").val();
	}
	
	if($.trim(eventType) == "") {
		alert("请选择事件类型");
		return;
	}		
	if($.trim(rContent) == ""){
		alert("回复内容不能空！"); 
		return;
	}		
	 
	var params = {id:id,menuKey:menuKey,eventType:eventType,respType:rType,respInfo:rContent};    	    	    		
	
   $.post(ctx+"/admin/customMenu/saveMenuInfo.json",params,function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			alert(result.msg);
			setTimeout(function(){window.location.reload();},300);
		}else{
			alert(result.msg);
		}
	});
}



