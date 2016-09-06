
	var storeId = $("#storeId").val();	//商店id	
	var datas;
	var imgArr = [];
	var goodsName ;	//商品名称
	
$(function(){		
	$('#upload').on('click',function(){
		////submit
		goodsName = $("#desc").val()
		if(goodsName == null || goodsName == ""){
			alert("案例描述不能为空");
			return;
		}
		else{
			$("#wait").html('<img src= "'+ctx+'/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;"><p style="line-height:25px;float:none;color:#78A200;font-size:15px;">文件上传中,请耐心等待...</p>');
			$.ajax({				
				type: 'POST',
				url: ctx+'/phone/buildingMyCenter/add_case_sql.json',
				data: {
					imgpath: imgpath,
					storeId: storeId,
					goodsName: goodsName
				},
				success: function(result){
					result = eval("("+result+")");
	//				alert(result.status);
					if(result.status == true || result.status == "true"){
						alert("案例添加成功");
						window.location.href = ctx+'/phone/buildingMyCenter/caseManage.htm?storeId='+storeId;
					}
					$("#wait").empty();
				}
				
			})
		}
	})
	
})


$(function(){
	
	//点击上传文件按钮
	$('#addBtn').click(function(){
		$('input[type="file"]').click();
	});
	
	$('input[type="file"]').change(function(e){
		
		var fileName = $('input[type="file"]').val().toLowerCase();
//				alert(fileName);
		  if(fileName.indexOf(".png")>=0 || fileName.indexOf(".jpg")>=0 || fileName.indexOf(".jpeg")>=0 || fileName.indexOf(".gif")>=0) {
		     $("#wait").html('<img src= "'+ctx+'/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;"><p style="line-height:25px;float:none;color:#78A200;font-size:15px;">文件上传中,请耐心等待...</p>');
			  $.ajaxFileUpload({				  
				  url:ctx+'/file/multi_upload.htm', 
				  //用于文件上传的服务器端请求地址
				  secureuri:false, //是否需要安全协议，一般设置为false
				  fileElementId:'uploadFile',  //文件上传域的ID
				  dataType: 'json',//返回值类型 一般设置为json
				  success: function(data,status){					  
					  if(data.status==true || data.status=="true") {
						  var imgHtml = '';
						  imgArr = data.data;
						  for(var i=0;i<imgArr.length;i++){
							  imgHtml = '<img style="max-width: 200px;max-height: 200px; margin-right:30px" src="'+imgPath+'/'+imgArr[i]+'"/>';							  
					  	  }
						  $(".picList").append(imgHtml);
					  }else {
						  alert(data.msg);
					  }
					  $("#wait").empty();					  
				  },
			  });
			  
		  }else {
				//$("#thumb1").empty();
				alert("请上传正确格式的图片！");
				window.location.reload();
		  }	
	});
	
})
	
/*function addPicToS(image){
	$(image).insertBefore('#addBtn');
}*/
