<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>添加商品</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js"></script> 
<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
		

	 function doSubmit(){
		    var goodsName = $('#goodsName').val();
		    if($.trim(goodsName)==""){ 
				alert("请输入商品名称！"); 		 
				return false; 
			}
	  	
		    var description = $('#description').val();
		    if($.trim(description)==""){ 
				alert("请输入商品描述！"); 		 
				return false; 
			}
	   	     	
		    var goodsPrice = $("#goodsPrice").val(); 
		    if(isNaN(goodsPrice)){
		    	alert("价格格式不正确");
		    	return false;
		    }		    
		    
		    var param = {'goodsName':goodsName,'description':description,'goodsPrice':goodsPrice};
		    $.ajaxFileUpload({
				url:'${ctx}/phone/buildingMyCenter/saveGoods.json', 
				//用于文件上传的服务器端请求地址
				secureuri:false, //是否需要安全协议，一般设置为false
				fileElementId:'uploadFile',  //文件上传域的ID
				data:param,
				dataType: 'json',//返回值类型 一般设置为json
				success: function (result) {
					if(result.status == true|| result.status == "true") {
						layer.alert("保存成功");	
						window.location.href = "";
					}	
					else {
						layer.alert(result.msg);
					}
				},				
			});
	  }			 				
	 
	 
	function clickFile(){		
	   $("#uploadFile").click();		 
    }	 
	 
   $(function(){
	   
	    //单图片上传预览
		$('input[type="file"]').change(function(e){
			var files = this.files;
			if(files.length) {
				//图片预览
				var previewHtml = "<img src='#previewImgSrc' alt='' width='80' height='80' />";
				//上传图片方法
				checkFile(this.files,"#preview",previewHtml);
			}
		});
	   
   })
	 
</script>
</head>

<body>
<!--头部的开始
<div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>享居派</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
头部的结束-->
<form id="validateForm" class="form-horizontal">
	<input type="file" style="display: none;" name="uploadFile" id="uploadFile"/>
	<div class="attentionTop">
		<i class="m_back"></i>
		<h2>添加商品</h2>
		<a href="javascript:void(0)" onclick="return doSubmit();" class="r_back">上架</a>
	</div>
	
	<div class="upload">
		商品名称：<input type="text" name="goodsName" id="goodsName" placeholder="请输入商品名称" /><br/><br/>
		<textarea name="description"  placeholder="输入商品描述，12字内..." id="description"></textarea>
		<div class="uploadImg" id="preview">
			<ul>
				<li><a href="javascript:void(0)" onclick="clickFile()"><i class="iconfont">&#xe611;</i></a></li>
			</ul>
		</div>
	</div>
	
	<div class="priceSelected">
		<span>商品价格（选填）：</span>
		<label><input name="goodsPrice" type="" id = "goodsPrice" placeholder="0.00" />元</label>
	</div>
</form>
<!--版权-->
<div class="copyright mb50">  
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单 -->
<jsp:include page="../footer.jsp" />
</body>
</html>
