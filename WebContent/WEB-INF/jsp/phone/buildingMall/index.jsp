<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>建材直选</title>
	<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resource/system/common/css/common.css" rel="stylesheet" type="text/css" />
    <!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wqBXfIN3HkpM1AHKWujjCdsi"></script> -->
    <script type="text/javascript" src="${ctx}/resource/system/common/js/jquery-1.10.1.min.js" ></script>
	<script>
		var ctx = "${ctx}";
	</script>		
    <script type="text/javascript" src="${ctx}/resource/system/phone/buildingMall/js/index.js" ></script>
	<script>
	$.ajax({
		type: "post",
		url : "${ctx}/weixin/getUserInfo.json",
		dataType: "json",
		data:{},
		success: function(result) {
			if(result.status == true||result.status == "true"){// 获取用户成功			
				console.log(result.data);
			}
			else {  //进行授权登录操作
				var hrefUrl = window.location.href;
				window.location.href="${ctx}/weixin/wxbaseLogin.htm?returnUrl="+hrefUrl;
			}
		}	
	})
</script>	
</head>

<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<h2>享居派</h2>
</div> -->
<!--头部的结束-->

<div class="areaTitle">
	<a href="" class="areaLink"><span id="addr_dis">定位中</span></a>
<!-- 	<input type="hidden" id="addr" name="addr"  size="20"  value="-99" placeholder="地址" />
	<input type="hidden" id="jingdu" name="jingdu"  size="20"  value="-99" placeholder="经度" />
	<input type="hidden" id="weidu" name="weidu"  size="20"  value="-99" placeholder="纬度" /> -->
		
	<!-- 建材类型  -->
	<div class="areaRight" id="buildingType">
		<ul>
			<!-- status.count为 集合的size -->
			<c:forEach items="${typeList}" var="type" varStatus="status">
				<c:if test="${status.count == 1}">
				<li class="hover" id="bt_${type.id}" onclick="findMerchantList('${type.id}',1)"><a>${type.typeName}</a></li>
				</c:if>
				<c:if test="${status.count != 1}">
				<li class="" id="bt_${type.id}" onclick = "findMerchantList('${type.id}',1)"><a>${type.typeName}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>

<!-- 列表内容区 -->
<div class="productList" id="productList">	
</div>

<!--列表加载js-->
<div class="jiazai">
</div>



<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<jsp:include page="../footer.jsp" />

</body>
</html>
