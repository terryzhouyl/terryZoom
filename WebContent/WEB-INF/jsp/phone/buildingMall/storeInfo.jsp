<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>${info.title}</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/common/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/system/common/js/jquery-1.10.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js"></script> 
<script type="text/javascript" src="${ctx}/resource/system/phone/buildingMall/js/storeInfo.js"></script> 
<script>
var storeId = '${info.id}';
</script>
</head>

<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>享居派</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div> -->
<!--头部的结束-->

 <div class="shopTitle">
	<img src="${ctx}/${info.coverPictureUrl}" style="width:100%; height:300px;" alt="" onerror="javascript:this.src='${ctx}/resource/system/common/image/default.png'" />
	<div class="shopTitle-in">
		<h3>${info.title}</h3>
		<p>${info.mainBusiness}</p>
	</div>
</div>

<div class="shoPromotions">
	<i>促</i>${info.promotion}
</div>

<div class="shopAbout">
	<dl>
		<dt>
			<c:if test="${info.isFocus==1}">
				<i class="iconfont">&#xe60e;</i>
				<p>已关注</p>
				<p><span>粉丝</span>1000</p>			
			</c:if>

			<c:if test="${info.isFocus==0}">
				<i class="iconfont">&#xe60d;</i>
				<p>关注</p>
				<p><span>粉丝</span>1000</p>
			</c:if>
		</dt>
		<dd>
			<p><i class="iconfont">&#xe601;</i><span>${info.province}${info.city}${info.district}${info.detailAddress}</span></p>
			<p><i class="iconfont">&#xe60a;</i><span>${info.contactPhone}</span></p>
			<p><i class="iconfont">&#xe609;</i><span>${info.businessTime}</span></p>
		</dd>
	</dl>
</div> 

<!---->
<div class="practiceCase">

	<div class="practiceCase-title">
		<ul class="hctabs_nav">
			<li class="hover"><a href="javaScript:changeData('case');" title="实物案例">实物案例</a></li>
			<li><a href="javaScript:changeData('goods');" title="全部商品">全部商品</a></li>
		</ul>
	</div>
	
	<div class="hctabs_info">
		<!--实物案例-->
		<div class="practiceCase-list">
<%-- 		   <c:forEach items="${cases}" var="cases" varStatus="status">
			<dl>
				<dt>
					<c:if test="${cases.storeInfo.imgUrl!=''}">
						<a href="${pageContext.request.contextPath}/store/storeDetail/${cases.storeInfo.id}"><img width="160" height="160" src="http://images.sunjoypai.com/store/${cases.storeInfo.photoPath}160X160_${cases.storeInfo.imgUrl}" alt="" /></a>
					</c:if>
					<c:if test="${cases.storeInfo.imgUrl==''}">
						<img width="160" height="160" src="http://images.sunjoypai.com/store/default_storeInfo.png" alt="" />
					</c:if>
				</dt>
				<dd><h3>${info.storeName}</h3></dd>
				<dd>${cases.describe}</dd>
				<dd>
					<c:forEach items="${cases.imgs}" var="img" varStatus="status">
						<span><img width="160" height="160" class="fangd" src="http://images.sunjoypai.com/case/${cases.path}${cases.id}/160X160_${img.imgUrl}" alt=""/></span>
					</c:forEach>
				</dd>
			</dl>
			</c:forEach> --%>   
		</div>
		<!--全部商品-->
		<div class="contentbox none">
			<ul>
<%-- 				<c:forEach items="${catalogs}" var="catalog" varStatus="status">
				<li>
					<a href="" title=""><img width="340" height="250" src="http://images.sunjoypai.com/product/${catalog.path}340X250_${catalog.picUrl}" alt=""></a>
					<a href="" title=""><p>${catalog.catalogName}</p></a>
					<span>￥${catalog.salePrice}</span>
				</li>
				</c:forEach> --%>
			</ul>
		</div>
	</div>
</div>


<!--列表加载js-->
<div class="jiazai">
</div>

<!--入驻-->
<div class="settled">
	<span>高效推广，点此急速入驻 >>></span>
	<a href="">入驻</a>
</div>

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>
 
</body>
</html>
