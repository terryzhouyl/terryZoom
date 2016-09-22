
	var storeId = $("#storeId").val();	//商店id	
	var datas;
	var imgArr = []; 
	var uploadImgPath = ''; //传值到后台的图片路径
	var description ;	//商品名称

//添加底部菜单样式
$(function(){
	addFooterClass(2);
})	
	
	
//保存案例	
$(function(){		
	$('#upload').on('click',function(){
		////submit
		description = $("#desc").val();  //描述
		if(description == null || description == ""){
			alert("案例描述不能为空");
			return;
		}						
		else{
			uploadImgPath = uploadImgPath.substring(0,uploadImgPath.length-1);
			alert(uploadImgPath);
			
			$("#wait").html('<img src= "'+ctx+'/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;"><p style="line-height:25px;float:none;color:#78A200;font-size:15px;">文件上传中,请耐心等待...</p>');
			$.ajax({				
				type: 'POST',
				url: ctx+'/phone/buildingMyCenter/saveCase.json',
				data: {
					imagePath: uploadImgPath,
					storeId: storeId,
					description: description
				},
				success: function(result){
					result = eval("("+result+")");
					$("#wait").empty();
					if(result.status == true || result.status == "true"){
						//alert("案例添加成功");
						layer.alert("案例添加成功");
						//window.location.href = ctx+'/phone/buildingMyCenter/caseManage.htm?storeId='+storeId;
					}
					else {
						layer.alert("案例添加失败");
					}
				}
				
			})
		}
	})
	
})


//绑定上传图片事件
$(function(){	
	//点击上传文件按钮
	$('#addBtn').click(function(){
		$('input[type="file"]').click();
	});
	
	$('input[type="file"]').change(uploadPic);
	
})

//上传图片方法
function uploadPic(){
		var fileName = $('input[type="file"]').val().toLowerCase();
	//	alert(fileName);
	if(fileName.indexOf(".png")>=0 || fileName.indexOf(".jpg")>=0 || fileName.indexOf(".jpeg")>=0 || fileName.indexOf(".gif")>=0) {
	 $("#wait").html('<img src= "'+ctx+'/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;"><p style="line-height:25px;float:none;color:#78A200;font-size:15px;">文件上传中,请耐心等待...</p>');
	  $.ajaxFileUpload({				  
		  url:ctx+'/phone/buildingMyCenter/uploadCasePic.json', 
		  //用于文件上传的服务器端请求地址
		  secureuri:false, //是否需要安全协议，一般设置为false
		  fileElementId:'uploadFile',  //文件上传域的ID
		  dataType: 'json',//返回值类型 一般设置为json
		  success: function(data,status){					  
			  if(data.status==true || data.status=="true") {
				  var imgHtml = '';
				  imgArr = data.data;
				  console.log(imgArr);
				  for(var i=0;i<imgArr.length;i++){
					  imgHtml += "<img style='max-width: 160x;max-height: 160px; margin-right:10px' src='"+imgPath+"/"+imgArr[i].realPath+"' />";	
					  uploadImgPath += imgArr[i]+","
			  	  }				  
				  //$(".picList").append(imgHtml);
				  $(imgHtml).insertBefore('#addBtn');
			  }else {
				  alert(data.msg);
			  }
			  $("#wait").empty();	  
			  //重新绑定file监听事件
			  $('input[type="file"]').change(uploadPic);
		  },
	  });
	  
	}else {
		//$("#thumb1").empty();
		alert("请上传正确格式的图片！");
	}	
 }
	
/*function addPicToS(image){
	$(image).insertBefore('#addBtn');
}*/
