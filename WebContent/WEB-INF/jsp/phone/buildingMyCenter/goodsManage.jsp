<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>商品管理</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/module/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js"></script>
<script>
	var storeId = '${storeId}';
</script>
<script type="text/javascript" src="${ctx}/resource/system/phone/buildingMyCenter/js/goodsManage.js"></script>  
</head>

<body>
<div class="attentionTop">
	<i class="m_back"></i>
	<ul>
		<li class="hover"><a href="#" onclick="changeGoodsInfo(1,this)">已上架</a></li>
		<li><a href="#" onclick="changeGoodsInfo(0,this)">已下架</a></li>
	</ul>
	<a href="${ctx}/phone/buildingMyCenter/addGoods.htm?storeId=${storeId}" class="r_back">增加</a>
</div>


<div class="contentbox">
	<ul id="content">				
	</ul>
</div>

<!--列表加载js-->
<div class="jiazai">
</div>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />
 
</body>
</html>
