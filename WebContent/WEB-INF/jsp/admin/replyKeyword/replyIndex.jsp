<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link href="${ctx}/resource/system/weixin/css/weixin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var mySound;

$(function(){
	$("#weixinMenu").addClass("cur");
	//初始化声音
/* 	$(".iconCeshi").click(function(){
		var musicLink = $(this).attr("rel");
		soundManager.play("mySound11","${ctx}"+musicLink);
	});
	var type = $(".iactive").attr("rel");
	if(type != 1){
		$("#save").css("display","none");
	} */
});

//新增编辑保存 关键字
function editMenu(id,key) {
	
	var param = {};
	var keyword = "";
	if(id == null) {  //新增
		keyword = $(key).parent().find("input").val();
	}
	else {
		keyword = $(key).val();
		param.id = id;
	}
	
	
	if($.trim(keyword)=="") {
		alert("关键词不能为空！");
		return;
	} else if(keyword.length>15) {
		alert("关键词名不能大于15个汉字！");
		return;
	} 
	param.keyword = keyword;
	
	
	$.post("${ctx }/admin/replyKeyword/savekey.json",param,function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			alert("保存成功");
			setTimeout(function(){window.location.reload();},300);
		}else{
			alert(result.msg);
		}
	});
}

//添加菜单
function addRoot(){
	$("#menu_navgtion").append('<li><input type="text" value="" /><a class="yes" style="display:block;" href="javascript:void(0)" onclick="editMenu(null,this);">确定</a></li>');
}

//修改 编辑菜单
function toEdit(o){
	var name = $(o).parent().find("span").html();
	var id = $(o).parent().find("p:eq(0)").html();
	$(o).parent().find("span:eq(0)").after('<input type="text" maxLength="15" onchange="editMenu('+id+', this);" value="'+name+'"/>');
	$(o).parent().find("span:eq(0)").hide();
	$(o).parent().find("button").hide();
	$(o).parent().find("a:eq(0)").show();
}

//删除关键字
function deleteMenu(o){
	var id = $(o).parent().attr("rel");
	if(confirm("确定要删除吗？")) {
		$.post("${ctx }/admin/replyKeyword/delete.json",{id:id},function(result){
			result = eval("("+result+")");
			alert(result.msg);
			setTimeout(function(){window.location.reload();},1000);
		});
	}
}

/* function toMenuContent(id,keyword,replyType,replyContent){
	//window.location = "${ctx}/admin/replyKeyword/index.htm?keywordId="+id+"#s_"+id;
	
	alert(replyContent);
	$("#textareaContent").html(replyContent);
	
	
} */



</script>
<div class="conTit fixed">
	<p>关键字回复</p>
</div>
<div class="secTit">
	<button class="addBtn" onclick="addRoot()">添加关键字</button>
</div>
<div class="main_table">
<div class="newReplyPart">
  <div class="wrap_div hasLeft">
  	<div class="hasLeftMenu" style="height: 400px; overflow-x:hidden">
	<ul class="huifu_navgtion" id="menu_navgtion" rel="0">
	      <c:forEach items="${keywordList }" var="o">
			  <li>
			  	  <span style="width:120px; overflow:hidden;text-overflow:ellipsis;">${o.keyword}</span>
			  	  <button class="modfly" onclick="toEdit(this)">修改</button>
			  	  <button class="del" onclick="deleteMenu(this);">删除</button>
			  	  <a class="yes" onclick="editMenu(${o.id},this)">确定</a>
			  	  <p style="display:none">${o.id}</p>
			  	  <p style="display:none">${o.keyword}</p>
			  	  <p style="display:none">${o.replyType}</p>
			  	  <p style="display:none">${o.replyContent}</p>
			  </li>
	      </c:forEach>
	</ul>
    </div>
    <!-- 自定义菜单回复内容部分 开始 -->
    <div class="cutomMenuBox">
    <!--被关注时回复 start -->
      <ul id="nav_ul">
          <li id="wenzi" rel="1">文字</li>
          <li id="fangfa" rel="2">方法</li>
        <%-- <li 
        <c:if test="${replyData.msgType==2 }">class="iactive"</c:if>id="tuwen" rel="2">图文</li> --%>
        <%-- 
        <li 
        <c:if test="${replyData.msgType==3 }">class="iactive"</c:if>
        id="yuyin" rel="3">语音
        </li>
        <li 
        <c:if test="${replyData.msgType==4 }">class="iactive"</c:if>
        id="shipin" rel="4">视频
        </li>
        --%>
      </ul>
      <%--文字 start--%>
      <div class="wzArea styleTop">
      <%--输入框 start--%>
      <ul class="ceshilist huifuArea">
        <li class="wenziText">
        <textarea id="textareaContent"></textarea>
        </li>
        <li>
       	<input type="text" value="" style="background-color:white;border:1px solid #d7d6d6;height:40px;width:400px;padding-left:10px"  id="methodText" />
        </li>
      <%--   <li class="tuwenText" 
        <c:if test="${replyData.msgType==2 }">style="display:block;"</c:if>
        >
        <div class="fixed"> 
         <c:choose>
          	  		<c:when test="${replyData.childrenData.size() == 0}">
			            <div class="del tuw">
						   <div class="delmargin">
			                <h1>${replyData.title }</h1>
			                <p><fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
			                <div class="tuwenPic"><img width="320" src="${ctx}/${replyData.picUrl}" /></div>
                            <p class="miaoshu">${replyData.summary }</p>
			              </div>		
			            </div>
			            <!-- 单图文end -->
            	  		</c:when>
          	  		 <c:otherwise>
			            <!-- 多图文end -->
			            <div class="del tuw">
			              <div class="delmargin">
			                <h1>${replyData.title }</h1>
			                <p><fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
			                <div class="tuwenPic"><img width="320" src="${ctx}/${replyData.picUrl}" /></div>
			              </div>
			              <ul class="duoTWlist">
			                <c:forEach items="${replyData.childrenData}" var="n">
				              <li class="fixed">
			                  <h4>${n.title }</h4>
			                  <div class="tuwenPic"><img width="70" src="${ctx}/${n.picUrl}" /></div>
			                  </li>
			                </c:forEach>
			              </ul>
			            </div>
			            <!-- 多图文end -->
            	</c:otherwise>
          	  	</c:choose>
        </div>
        </li> --%>
        <li class="yuyinText"  
        <c:if test="${replyData.msgType==3 }">style="display:block;"</c:if>
        >
        <div>
          <c:if test="${replyData.msgType==3 }">
            <div class="tit fixed"><span>${replyData.title }</span> </div>
            <div class="iconinfo fixed">
              <%-- <div class="iconCeshi" rel="${replyData.musicLink }">点击播放&nbsp;&nbsp;</div> --%>
              <audio src="${ctx}${replyData.musicLink }" controls="controls">您的浏览器不支持</audio>
              <div class="informaition">
                <p>${replyData.fileSizekb } kb</p>
                <p>
                  <fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/>
                </p>
              </div>
            </div>
          </c:if>
        </div>
        </li>
        <li class="shipinText" 
        <c:if test="${replyData.msgType==4 }">style="display:block;"</c:if>
        >
        <div>
          <c:if test="${replyData.msgType==4 }">
            <div class="tit fixed"><span>${replyData.title }</span> </div>
            <div class="iconinfo fixed">
              <div class="autoView">
                <video style="width:152px;height:110px;" src="${ctx }${replyData.videoLink}" controls="controls">您的浏览器不支持</video>
              </div>
              <div class="informaition">
                <p>${replyData.fileSizekb } kb</p>
                <p>
                  <fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/>
                </p>
              </div>
            </div>
          </c:if>
        </div>
        </li>
      </ul>
      <%--输入框 end--%>
      <input type="hidden" id="textContent" value="${replyKeyword.replyContent}">
      <div><a href="javascript:saveReply();" class="green_btn" >保存</a><!-- <a href="javascript:void(0);" class="gray_btn">删除</a>--> </div>
    </div>
    <%--文字 end--%>
    <%--图文start--%>
    <div class="popBox tt" >
      <div class="popWrap tt"  >
        <div class="tuwen">
          <div class="titleDiv">
            <div class="title">选择图文</div>
            <div class="closeBtn levelA"></div>
          </div>
          <iframe src="${ctx}/admin/replydata/getArticle.json" marginwidth="0" marginheight="0" frameborder="0" scrolling="none" class="tanchuFrame"></iframe>
          <div class="footDiv"> <a href="javascript:void(0);" class="green_btn">确定</a> <a href="javascript:void(0);" class="gray_btn">取消</a> </div>
        </div>
      </div>
      <div class="zhezhao"></div>
    </div>
    <%--图文end--%>
  <%--被关注时回复 end--%>
  </div>
  <!-- 自定义菜单回复内容部分 结束 -->
</div>
</div>
</div>
<script type="text/javascript">
$(".menuList #appreciation").addClass("currentMenu");
$('.punBoxWarp a').click(function(){
	$('.punBox').hide();
	});
$('#huifu_navgtion .huifuB').addClass('active');
 
 	var id = ""
 	var replyType = "";
 	var replyContent = "";
 	var keyword = "";
 	
 	//点击左侧边栏显示数据
	$("#menu_navgtion li").click(function(){	
		var replyInfo =	$(this).find("p");
		id = $(replyInfo[0]).html();
		keyword = $(replyInfo[1]).html();
		replyType = $(replyInfo[2]).html();
		replyContent = $(replyInfo[3]).html();
		
		if(replyType == "text") {
			$("#wenzi").addClass('iactive').siblings().removeClass('iactive');
			$('.wzArea ul li').eq(0).show().siblings().hide();
			$("#textareaContent").html(replyContent);
			$("#methodText").val("");
		}
		else if(replyType == "method") {
			$("#fangfa").addClass('iactive').siblings().removeClass('iactive');
			$('.wzArea ul li').eq(1).show().siblings().hide();
			$("#methodText").val(replyContent);
			$("#textareaContent").html("");
		}
		else {
			$("#wenzi").addClass('iactive').siblings().removeClass('iactive');
			$('.wzArea ul li').eq(0).show().siblings().hide();
			$("#textareaContent").html("");
		}
				
		$(this).addClass('cur').siblings().removeClass('cur');
	})
 
	//点击文字显示
	$('#wenzi').click(function(){
		//$("#save").show();
		var thisIndex=$(this).index();
		$(this).addClass('iactive').siblings().removeClass('iactive');
		$('.wzArea ul li').eq(thisIndex).show().siblings().hide();
		
		if(replyType == "text") {			
			$("#textareaContent").html(replyContent);				
		}	
 		else {
			$("#textareaContent").html("");	
		}
	});
	
 	//点击方法显示
	$("#fangfa").click(function(){
		//$("#save").show();
		var thisIndex=$(this).index();
		newReplyMsgType = $(this).attr("rel");
		$(this).addClass('iactive').siblings().removeClass('iactive');
		$('.wzArea ul li').eq(thisIndex).show().siblings().hide();
		
		if(replyType == "method") { 
			//alert(replyContent);
			$("#methodText").html(replyContent);	
		}
		else {
			$("#methodText").val("");
		}
	})

	//保存回复内容
	function saveReply() {
	var rType = "";
	var rContent = "";
	if($("#wenzi").hasClass("iactive")) { //
		rType="text";
		rContent = $("#textareaContent").val();
	}
	else {
		rType = "method";
		rContent = $("#methodText").val();
	}
	
	if($.trim(rContent) == ""){
		alert("回复内容不能空！"); 
			return;
	}    	
	
	var params = {id: id,keyword:keyword,replyContent:rContent,replyType:rType};    	    	    		

	
	//alert(params);
	
    $.post("${ctx}/admin/replyKeyword/keywordSave.json",params,function(result){
		result = eval("("+result+")");
		if(result.status == "true" || result.status == true){
			alert(result.msg);
			setTimeout(function(){window.location.reload();},300);
		}else{
			alert(result.msg);
		}
	});
}
	

    $("#tuwen").click(function(){
        $(".popBox.tt").show();
        var popWrap_H = $(".popWrap.tt").height();
        var window_H = $(window).height();
        if(popWrap_H>window_H){
            $(".popWrap.tt").css("margin-top","0");
        }else{
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
		if(newReplyDataId ==''){
        	alert("请选择图文！"); 
        }else{
        	$("#textContent").val("");
        	$(".popBox").css("display","none");
        	onSave(2);
        }
    });
	$(".popBox .gray_btn").click(function(){
        $(".popBox").hide();
    });
	
    $(".closeBtn.wron").click(function(){
        $(".gshiWrong").hide();
    });
    $(".zhezhao").width($(window).width());
    $(".zhezhao").height($(window).height());     
	
</script> 
<script type="text/javascript">
$(function(){
	//鼠标移上，出现编辑图标
	$('#menu_navgtion li').hover(function(){
		
		$(this).find('button').show();
		//$(this).find('ul li button').hide();
		},function(){
			//$(this).parent('li').find('button').hide();
			//$(this).parent('li').find('ul li button').hide();
			$(this).find('button').hide();
		});
});





/**
 * 模拟点击事件 加载页面则选中第一栏
 */
$(function(){
	$("#menu_navgtion li:eq(0)").trigger("click");
})


</script> 