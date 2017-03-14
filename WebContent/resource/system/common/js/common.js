
//点击tab页 切换
/**
 * tabTit Tab标题栏
 * tabCon Tab具体内容
 * on tab标题栏的样式
 */
function tabs(tabTit,on,tabCon){
	$(tabCon).each(function(){
	  $(this).children().eq(0).show();
	  });
	
	$(tabTit).each(function(){
	  $(this).children().eq(0).addClass(on);
	  });
     $(tabTit).children().click(function(){
        $(this).addClass(on).siblings().removeClass(on);
         var index = $(tabTit).children().index(this);
         $(tabCon).children().eq(index).show().siblings().hide();
    });
}

//获取后台数据
function getBackData(url,data) {
	var returnData;
	$.ajax({
		type:"post",
		url:url,
		data:data,
		async:false,
		success :function (result){
			result = eval("("+result+")");
        	if(result.status == "true" || result.status == true){
        		returnData = result.data;
        	}
        	else {
        		alert(result.msg);
        	}
		}
	});
	return returnData;
}


//获取城市Json数据
function getCitysJson(provinceId) {			
	return getBackData(ctx+"/common/getCities.json",{provinceId:provinceId});
}

//获取省Json数据
function getProvincesJson() {	     	   
	return getBackData(ctx+"/common/getProvinces.json",{});
}


/**
 * 单图片处理
 * files 文件对象
 * parentNodeStr 父节点 #node形式
 * previewImgHtml html代码，例：<img src='#previewImgSrc' alt='img' style='max-width: 300px;max-height: 300px;' /> 必须有#previewImgSrc
 */
function checkFile(files,parentNodeStr,previewImgHtml) {
	var file = files[0];
	var reader = new FileReader();
	//show表示<div>
	if(!/image\/\w+/.test(file.type)) {
		alert("请确保文件为图片类型");
		return false;
	}
	//onload是异步操作
	reader.onload = function(e) {
		previewImgHtml = previewImgHtml.replace("#previewImgSrc",e.target.result);
		$(parentNodeStr).html(previewImgHtml);
		//$("#preview").html("<img src='#previewImgSrc' alt='img' style='max-width: 300px;max-height: 300px;' />");			
	}
	reader.readAsDataURL(file);
}


function login() {
	$.ajax({
		type:"post",
		url:url,
		data:data,
		async:false,
		success :function (result){
			result = eval("("+result+")");
        	if(result.status == "true" || result.status == true){
        		returnData = result.data;
        	}
        	else {
        		alert(result.msg);
        	}
		}
	});
	return returnData;
}







    
