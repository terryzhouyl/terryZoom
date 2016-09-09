<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>修改店铺信息</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
<script type="text/javascript">
	 function doSubmit(){	    	
   		//$('#validateForm').submit();
   		var key = '${key}';
   		var value =	$('#'+key).val();  
   		var param = {};
   		param[key] = value;
   		
   		
		$.ajax({				
	        type: "post",
	        url: "${ctx}/phone/buildingMyCenter/saveStoreInfo.json",
	        data: param,
	     success: function(result){
	     	result = eval("("+result+")");
	     	if(result.status == "true"){
	     		window.location.href = "${ctx}/phone/buildingMyCenter/storeConfigIndex.htm";
	     	}
	     	else{
	     		alert(result.msg);
	     	}
	      }
		});
   	 }

	//清空文本框 
	function doClear(s_name){  	
  		$('#'+s_name).val(""); 
 	}
</script>
</head>


<body>
<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/store/updateStoreInfo/${sInfo.id}" >
	<div class="storeName">
		<ul>
			<li>
				<c:if test="${key == title}">
					<span>店铺名称：<input type="text" class="main" name="storeName" id="title" value = "${info.title}" /></span><i class="iconfont" onclick="doClear('title')">&#xe60c;</i>
				</c:if>	
				<c:if test="${key == promotion}">
					<span>促销信息：<input type="text" class="main" name="promotion" id="promotion" value = "${info.promotion}" /></span><i class="iconfont" onclick="doClear('promotion')">&#xe60c;</i>
				</c:if>	
				<c:if test="${key == contactPhone}">
					<span>联系电话：<input type="text" class="main" name="contactPhone" id="contactPhone" value = "${info.contactPhone}" /></span><i class="iconfont" onclick="doClear('contactPhone')">&#xe60c;</i>
				</c:if>		
				<c:if test="${key == businessTime}">
					<span>营业时间：<input type="text" class="main" name="businessTime" id="businessTime" value = "${info.businessTime}" /></span><i class="iconfont" onclick="doClear('businessTime')">&#xe60c;</i>
				</c:if>			
				<c:if test="${key == mainBusiness}">
					<span>主营：<input type="text" class="main" name="mainBusiness" id="mainBusiness" value = "${info.mainBusiness}" /></span><i class="iconfont" onclick="doClear('mainBusiness')">&#xe60c;</i>
				</c:if>																			
			</li>
		</ul>
	</div>

	<div class="storeName-button">
		<a href="javascript:void(0)" onclick="return doSubmit();">保存</a>
	</div>
</form>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />
 
</body>
</html>
