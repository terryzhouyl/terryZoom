<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<title>Insert title here</title>
</head>
<script>
</script>
<body>
	yonghu${session.SESSION_WEIXIN}
	<c:if test="${sessionScope.SESSION_WEIXIN == null}">
		<a href="${ctx}/weixin/wxuserLogin.htm?returnUrl=http://enjoylife.tunnel.qydev.com/terryZoom/public/index.htm">请登录</a>
	</c:if>
	<c:if test="${sessionScope.SESSION_WEIXIN!=null}">
		登录成功
	</c:if>
</body>
</html>