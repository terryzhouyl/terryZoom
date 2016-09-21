<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>个人设置</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
</head>

 
<body>
<div class="setUp">
	<ul>
		<li>
			<a href="#">
				<span>头像</span>
                <img width="70" height="70" src="#" alt="" onerror="javascript:this.src='${ctx}/resource/system/common/image/default.png'" />				
			</a>
		</li>
		<li>
			<a href="${ctx}/user/settingInfo">
				<span>昵称</span><label>${userInfo.nickname}</label>
			</a>
		</li>
		
		<%-- <form id="validateForm" class="form-horizontal" method="post" action="${ctx}/user/updateUserInfo/${userInfo.id}" > --%>
		<li>
			<span>性别</span>
			<cite>				
				<a  href="javascript:void(0)" onclick="return doSubmit(1);" class="${userInfo.sex==1?'hover':''}">男</a>
				<a  href="javascript:void(0)" onclick="return doSubmit(0);" class="${userInfo.sex==0?'hover':''}">女</a>								
			</cite>
		</li>
		<!-- </form> -->
	</ul>
</div>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />

 <script>
	$(function(){
		$('.setUp li cite a').click(function(){ 
			$(this).addClass('hover').siblings().removeClass('hover')
		})
	}) 
	 
	 //修改性别
	 function doSubmit(sex){			
			$.ajax({				
		        type: "post",
		        url: "${ctx}/phone/buildingMyCenter/saveUserInfo.json",
		       // async:false,
		        data: {'userId':'${userInfo.id}','sex':sex},
		    	success: function(result){
		    	result = eval("("+result+")");
			    	if(result.status == "true"){		    		
			    		//window.location.reload();
			    	}
			    	else{
			    		layer.alert(result.msg);
			    	}
		    	}
		  });
	 }
	 
</script> 
</body>
</html>
