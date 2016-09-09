<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" errorPage="/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@	taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!-- 上下文 -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="imgPath" value="http://localhost:8888/enjoylife" />
<script>
	var ctx = '${ctx}';
	var imgPath = '${imgPath}';
</script>
