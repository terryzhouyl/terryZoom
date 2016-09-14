<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html>
<html>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
		<title>home</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/system/phone/buildingMyCenter/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/system/phone/buildingMyCenter/css/upload.css"/>
		<%-- <link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" /> --%>
		<%-- <script src="${ctx}/resource/system/phone/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script> --%>
		<script src="${ctx}/resource/system/phone/buildingMyCenter/js/addCase.js"></script>
		<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
	<body>
		<input type="hidden" id="storeId" value="${storeId }" />
		<input type="file" style="display: none;" accept="image/jpeg,image/png,image/jpg" multiple="multiple" name="cmfile" id = "uploadFile" />
		<div class="title">
			<!-- 发送按钮 -->
			<div id="upload"><img src="${ctx}/resource/system/phone/buildingMyCenter/image/publish.png" alt="" /></div>
		</div>
		<div class="content">
			<textarea name="desc" id="desc" placeholder="请输入案例描述，12字内……" maxlength="13"></textarea>
			<div style="width:300px;margin:10px auto" id="wait">
			</div>
			<div class="picList clearfix">
				<img src="${ctx}/resource/system/phone/buildingMyCenter/image/addPic.jpg" alt="" id="addBtn" class="pics" />
			</div>
		</div>
		<!-- <div id="foot" style="bottom:114px;position: absolute;">CopyRight©2016 享居派 All Rights Reserved</div> -->
		<jsp:include page="../footer.jsp" />		
	</body>
	
</html>
