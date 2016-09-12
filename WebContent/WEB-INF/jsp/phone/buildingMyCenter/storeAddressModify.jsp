<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>修改店铺信息</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/system/common/js/jquery-1.10.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js" ></script>
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
<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/store/updateStoreInfo/${info.id}" >
	<div class="storeName">
<%-- 		<ul>
			<li>							
				<c:if test="${key == 'title'}">
					<span>店铺名称：<input type="text" class="main" name="storeName" id="title" value = "${info.title}" /></span><i class="iconfont" onclick="doClear('title')">&#xe60c;</i>
				</c:if>																						
			</li>									
		</ul> --%>
		<div class="inputLine" style="width:8%">
			<select id="province" onchange="getCity()">
				<option value="0">请选择省</option>
			</select>
		</div>	
		<div class="inputLine" style="width:8%;">
			<select id="city" name="cityId">
				<option value="0">请选择市</option>
			</select>
		</div>
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
