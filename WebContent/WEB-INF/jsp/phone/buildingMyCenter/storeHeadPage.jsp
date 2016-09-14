<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>我的个人头图</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js"></script> 
<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
</head>


<body>

<form id="validateForm">
	<div class="headPortrait">
		<ul>
			<li>
                <div id="preview" class="headPortrait-img" onclick="clickFile()">
                	<img src="${imgPath}/${coverPictureUrl}" alt="" width="160" height="160" onerror="javascript:this.src='${ctx}/resource/system/common/image/default.png'"/>
                </div>
				<p>点击头像更换</p>
				<input id="uploadFile" style="display:none;" name="cmfile" type="file" />
			</li>
			<li><a href="javascript:saveHeadImage()" class="headPortrait-link1">上传</a></li>
		</ul>
	</div>
</form>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />
 <script type="text/javascript">
	 
   $(function(){
	   
	    //单图片上传预览
		$('input[type="file"]').change(function(e){
			var files = this.files;
			if(files.length) {
				//图片预览
				var previewHtml = "<img src='#previewImgSrc' alt='' width='160' height='160' />";
				//上传图片方法
				checkFile(this.files,"#preview",previewHtml);
			}
		});
	   
   })
	
	
	function clickFile(){
		 $("#uploadFile").click();		 
	}
	
  //保存店铺图片	 
  function saveHeadImage(){		
	 var param = {'buildingStoreId':'${buildingStoreId}'}; 	
	 
		var fileName = $('input[type="file"]').val().toLowerCase();
		console.log(fileName);
	 
	 $.ajaxFileUpload({		 
			url:'${ctx}/phone/buildingMyCenter/saveHeadImage.json', 
			//用于文件上传的服务器端请求地址
			secureuri:false, //是否需要安全协议，一般设置为false
			fileElementId:'uploadFile',  //文件上传域的ID
			data:param,
			dataType: 'json',//返回值类型 一般设置为json
			success: function (result) {
				if(result.status == true|| result.status == "true") {
					window.location.href = "${ctx}/phone/buildingMyCenter/storeConfigIndex.htm";
				}	
				else {
					alert(result.msg);
				}
			},				
		});
	} 
	
</script>
 
</body>
</html>
