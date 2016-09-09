<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"  isErrorPage="true"%>
<%@ page import="java.io.PrintStream" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx }/resource/system/common/css/common.css" rel="stylesheet" />
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
var sUserAgent = navigator.userAgent.toLowerCase();
if(sUserAgent.toLowerCase().match(/android/i) == "android"){
	document.write("<div class=\"warp error\" ></div>");
}else if(sUserAgent.toLowerCase().match(/iphone/i) == "iphone"){
	document.write("<div class=\"warp error\" ></div>");
}else{
	document.write("<div class=\"error\"><img src=\"${ctx }/resource/system/common/image/phoneError.png\" /><div class=\"error_back\"><a href=\"http://www.sunjoypai.com\">返回首页</a></div></div>");
}
</script>
<body>
	<div class="error"><img src="${ctx}/resource/system/common/image/phoneError.png" /></div>
	
	<a href="javascript:history.back(-1)">返回上一页</a>
	<a href="javascript:checkError()"/> 错误详情 </a>
	<div id = "errorContent" style="display:none">
	<%
		exception.printStackTrace(new java.io.PrintWriter(out));
	%>
	</div>
</body>
<script>
	 function checkError() {		 
		 $("#errorContent").toggle();
	 }
</script>
</html>
