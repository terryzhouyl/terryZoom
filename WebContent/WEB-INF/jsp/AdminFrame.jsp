<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <%--   
	   后台管理权限
	  管理员(所有权) admin
	  线上运营(微信功能,订单管理，后台管理) userone
	  线下运营(账号管理，订单管理) usertwo
	  财务(订单管理) userthree
	 电销(一键下单管理) userfour
  --%>
	<title>后台管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="${ctx}/resource/system/admin/css/elusive-webfont.css" />
	<link rel="stylesheet" href="${ctx}/resource/system/admin/css/style.css" />
	<script src="${ctx}/resource/system/common/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="${ctx}/resource/system/admin/js/funUtils.js"></script>
	<script src="${ctx}/resource/system/admin/js/public.js"></script>
	<!--[if lte IE 7]><link rel="stylesheet" src="${ctx}/resource/system/admin/css/elusive-webfont-ie7.css" /><![endif]-->
	<!--[if lte IE 7]><script src="${ctx}/resource/system/admin/js/lte-ie7.js"></script><![endif]-->
	<style>
		.tishi{display:none; position:absolute; width:250px; left:50%; margin-left:-125px; background-color: #FFA914;border-radiu:3px;color: #FFF;text-align: center; font-size: 16px;padding: 10px;z-index: 9999}
	</style>
	<script>
		var ctx = "${ctx}";
		/*
		function alert(tishi,times){
			if(typeof times === "undefined") {
				times = 2000;
			}
		    var windowH=$(document).height();
			var scrollTopH=$(window).scrollTop();
			$(".tishi").css({top:(scrollTopH+windowH)/2 -180});
			$(".tishi").html(tishi);
			$(".tishi").fadeIn();
			setTimeout(function(){
				$(".tishi").fadeOut();
				$(".tishi").empty();
			},times);
		}
		*/
		function closeDialog() {
			$(".dialog").hide();
		}
		
		function openDialog() {
			$("#changePwd").find("input[name=opassword]").val("");
			$("#changePwd").find("input[name=password]").val("");
			$("#changePwd").find("input[name=password1]").val("");
			$(".dialog").show();
		}
		
		function savePwd() {
			var success = true;
			var opassword = $("#changePwd").find("input[name=opassword]").val();
			var password = $("#changePwd").find("input[name=password]").val();
			var password1 = $("#changePwd").find("input[name=password1]").val();
			if(opassword == "") {
				success = false;
				alert("请输入登录密码");
			}else if(password ==""){
				success = false;
				alert("请输入新密码");
			}else if(password1 ==""){
				success = false;
				alert("请输入确认密码");
			}else if(password != password1) {
				success = false;
				alert("两次输入的密码不一致");
			}
			
			if(success){
				$.post("${ctx}/admin/administrator/changePwd.json",
					  {opassword:opassword,password:password,password1:password1},
					  function(result){
							result = eval("("+result+")");
							if(result.status == "true" || result.status == true){
								closeDialog();
								alert(result.msg);
							}else{
								alert(result.msg);
							}
				});
			}
		}
		
		
		var curWwwPath=window.document.location.href;
		$(function(){
			$(".secMenu").find("li a").each(function(){
				if(curWwwPath.indexOf($(this).attr("href")) !=-1){
					$(this).parent().addClass("cur");
				}
			})
		})
	</script>
</head>

<body>
	<c:if test="${msg!=null }">
		<script>alert("${msg}");</script>
	</c:if>
	
	<%-- alert样式 --%>
    <div class="tishi" ></div>
    <%-- alert样式 --%>
    
    <%-- 顶部 --%>
    <div id="header" class="fixed" style="background: #fff">
    	<a href="${ctx }/admin/administrator/index.htm"  style="background: #fff" class="logo_index">
    		<img src="${ctx}/resource/system/admin/images/logo.png" />
    	</a>
    	 
 		<ul class="user_leader fixed">
        	<li><a href="javascript:void(0);" class="usename"  style="color:#000;background:none;width: 200px ">${SESSION_ADMIN.username}</a></li>
        	<li><a href="javascript:void(0);" class="edit" onclick="openDialog();">修改密码</a></li>
            <li><a href="${ctx }/admin/administrator/logout.htm" class="exit" onclick="logout()">退出</a></li>
        </ul>
    </div>
    <%-- 顶部 --%>
    
    <%-- 左侧菜单 --%>
    <div class="warp fixed">
    	<div class="leftBox">
            <ul class="menu">
            	<li id="weixinMenu">
                	<h2><a href="javascript:void(0);">微信功能</a></h2>
                	<ul class="secMenu">
                		<%--
               			<li><a href="${ctx }/admin/batch/index.htm">群发消息</a></li>
                		 --%>
               			<li><a href="${ctx }/admin/replyKeyword/attention.htm">欢迎语</a></li>
               			<li><a href="${ctx }/admin/replyKeyword/auto.htm">自动回复</a></li>
               			<li><a href="${ctx }/admin/replyKeyword/replyIndex.htm">关键字回复</a></li>
               			<li><a href="${ctx }/admin/customMenu/menuIndex.htm">自定义菜单</a></li>
               			<li><a href="${ctx }/admin/replydata/index.htm">素材管理</a></li>
                	</ul>
                </li>                
                <li id="buildingMallMenu">
                	<h2><a href="javascript:void(0);">建材市场</a></h2>
                	<ul class="secMenu">                		
                		<li><a href="${ctx}/admin/buildingMall/index.htm">商家管理</a></li>                		                		
                	</ul>
                </li>                
                <li id="systemconfigMenu">
                	<h2><a href="javascript:void(0);">系统设置</a></h2>
                	<ul class="secMenu">
                		<li><a href="${ctx }/admin/systemconfig/editItem.htm?configKey=question_answer">常见问题设置</a></li>
                		<li><a href="${ctx }/admin/systemconfig/app.htm">App版本控制</a></li>
                		<li><a href="${ctx }/admin/systemconfig/editItem.htm?configKey=about_xjp">关于享居派</a></li>
                		<li><a href="${ctx }/admin/systemconfig/editItem.htm?configKey=service_terms">服务条款</a></li>
                		<li><a href="${ctx }/admin/systemconfig/index.htm">系统设置</a></li>
						<li><a href="${ctx }/admin/systemconfig/editItem.htm?configKey=service_agreement">服务协议</a></li>
                		<li><a href="${ctx }/admin/systemconfig/editItem.htm?configKey=function_intro">功能介绍</a></li>
                	</ul>
                </li>
               
            </ul>
        </div>
        <div class="rightBox">
        	<div class="theCon">
        		<%-- 右侧主体内容 --%>
        		<decorator:body></decorator:body>
        		<%-- 右侧主体内容 --%>
            </div>
        </div>
    </div>
    <%-- 左侧菜单 --%>

	<%-- 密码修改弹出框 --%>
	<div class="dialog" style="display:none;top:20%">
		<div class="tit">
	    	<i class="icon-remove-sign" onclick="closeDialog();"></i>
	    	<h3>修改密码</h3>
	    </div>
	    <div class="con">
			<form id="changePwd">
	        	<div class="inputArea">
					<label>旧密码:</label>
					<div class="inputLine"><input  name="opassword" placeholder="旧密码" type="password" maxlength="20"></div>
				</div>
	            <div class="inputArea">
					<label>新密码:</label>
					<div class="inputLine"><input name="password" placeholder="新密码" type="password" maxlength="20"></div>
				</div>
	            <div class="inputArea">
					<label>再次输入新密码:</label>
					<div class="inputLine"><input name="password1" placeholder="再次输入新密码" type="password" maxlength="20"></div>
				</div>
			</form>
	        <div class="save">
	            <a href="javascript:void(0);" class="backBtn" onclick="savePwd();">确定</a>
	            <a href="javascript:void(0);" class="delateBtn" onclick="closeDialog();">取消</a>
	        </div>
	    </div>
	</div>
	<div class="blackBg" style="display:none;"></div>
	<%-- 密码修改弹出框 --%>
</body>
</html>