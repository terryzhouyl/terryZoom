<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>店铺设置</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />

</head>


<body>
<div class="setUp">
	<ul>
		<li>
			<a href="${ctx}/store/settingStorePic">
				<span>店铺头图</span>
				<label>
<%-- 				<c:if test="${info.imgUrl == null || info.imgUrl ==''}">
					<img id="preview" width="70" height="70" src="${ctx}/images/headPortrait.jpg" alt="" />
				</c:if>
				<c:if test="${info.imgUrl != null && info.imgUrl !=''}">
					<img id="preview" width="70" height="70" src="http://images.sunjoypai.com/store/${info.photoPath}160X160_${info.imgUrl}?rnd=<%=Math.random()%>" />
				</c:if> --%>
					<img id="preview" width="70" height="70" src="${imgPath}/${info.smallPicUrl}" onerror="javascript:this.src='${ctx}/resource/system/common/image/headPortrait.jpg" />								
				</label>
			</a>
		</li>
	</ul>
	<ul>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=title">
				<span>店名</span><label>${info.title}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=promotion">
				<span>促销信息</span><label>${info.promotion}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=address">
				<span>地址</span><label>${info.province}${info.city}${info.district}${info.detailAddress}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=contactPhone">
				<span>联系电话</span><label>${info.contactPhone}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=buinessTime">
				<span>营业时间</span><label>${info.businessTime}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${ctx}/phone/buildingMyCenter/storeInfoModify.htm?key=mainBusiness">
				<span>主营</span><label>${info.mainBusiness}&nbsp;</label>
			</a>
		</li>		
	</ul>
	<ul>
		<li>
			<span>店铺ID</span><p align="right">${info.storeNo}</p>
		</li>
	</ul>
</div>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />

<script>
	//添加底部样式
	$(function(){	
		 addFooterClass(3);
	})
</script>
</body>
</html>
