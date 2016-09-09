<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>我的</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/resource/module/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/jquery-1.10.1.min.js" ></script>
</head>

<body>

<!--昵称-->
<div class="nickname">
	<dl>
		<dt>
            <a href="${ctx}/user/userInfo"><img src="${SESSION_WEIXIN.headimgurl}" alt="" /></a>
		</dt>
		<dd><h3>${SESSION_WEIXIN.nickname}(ID:${userInfo.id})</h3></dd>
		<dd>店铺:${buildingStore.title}</dd>
	</dl>
</div>

<div class="userList">
	<c:if test="${buildingStore != null}">
		<ul>  
			<li ><a href="${ctx}/user/getQRCode" ><i class="iconfont userList-ico1">&#xe607;</i>我的店铺二维码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="${ctx}/phone/buildingMyCenter/goodsManage.htm?storeId=${buildingStore.id}"><i class="iconfont userList-ico2">&#xe60f;</i>商品管理</a></li>
			<li><a href="${ctx}/phone/buildingMyCenter/caseManage.htm?storeId=${buildingStore.id}"><i class="iconfont userList-ico3">&#xe610;</i>案例管理</a></li>
		</ul>
		<ul>
			<li><a href="${ctx}/phone/buildingMall/storeInfo.htm?storeId=${buildingStore.id}"><i class="iconfont userList-ico4">&#xe60b;</i>查看店铺</a></li>
		</ul>
		<ul>
			<li><a href="${ctx}/phone/buildingMyCenter/storeConfigIndex.htm?storeId=${buildingStore.id}"><i class="iconfont userList-ico5">&#xe608;</i>店铺设置</a></li>
		</ul>
	</c:if>
	<c:if test="${buildingStore == null}">
		<ul>
			<li><a href="javascript:applyStore()"><i class="iconfont userList-ico4">&#xe60b;</i>激活店铺</a></li>
		</ul>
	</c:if>
</div>

<!--二维码弹出框-->
<div id="light" class="white_content">
	<img src="images/userEr.jpg" alt="" />
	<p>
		XXX瓷砖专卖
		<span>长按关注</span>
	</p>
</div> 
<div id="fade" class="black_overlay" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"></div> 
		

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单 开始-->
<jsp:include page="../footer.jsp" />
<!--底部菜单 结束-->
 
 <script>
 $(function(){	
	 addFooterClass(3);
 })
 
	//申请店铺		
	function applyStore(){
		$.ajax({				
	        type: "post",
	        url: "${ctx}/phone/buildingMyCenter/applyStore.json",
	       // async:false,
	        data: {},
     success: function(result){
     	result = eval("("+result+")");
     	if(result.status == "true"){
     		alert("申请成功");
     		window.location.reload();
     	}
     	else{
     		alert(result.msg);
     	}
     }
	  });
	}	
</script> 
</body>
</html>
