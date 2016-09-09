<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<script type="text/javascript" src="${ctx }/resource/plugin/my97/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(function(){
	$("#buildingMallMenu").addClass("cur");
});
</script>

<style>
	#thumb div{
		margin-bottom : 20px;
	}
</style>

<div class="conTit fixed">
    <p>
   		<c:choose>
			<c:when test="${buildingCase.id ==null}">新增案例</c:when>
			<c:otherwise>修改案例</c:otherwise>
		</c:choose>
    </p>
    <div class="fr"><a href="javascript:void(0);" class="backBtn" onclick="window.history.back(-1);">返回</a></div>
</div>

<div style="margin-left: 10px;">
  <form id="carrierForm" action="" method="post" enctype="multipart/form-data">
	<div class="inputArea">
	     <label>案例标题:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="title" name="title" value="${buildingCase.title}" maxlength="16"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>案例描述:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="description" name="description" value="${buildingCase.description}"/>
	     </div>
	</div>	
	<div class="inputArea02">
	  <label>风格图片:建议尺寸：640*320 <font style="color: red">*</font></label>
	    <input type="hidden" id="pictureUrl" name="pictureUrl" value=""/>
	    <div class="inputLine">
	    	   上传风格图片<input id="uploadfile" name="cmfile" type="file" onchange="triggerUpload()" multiple/>
	    </div>
	    <div id="imgPreView" >
	        <div id="preview" >
		      	 <div id="thumb" style="margin-left:21%; margin-top:30px;">
		      		<%-- <c:if test="${buildingCase.pictureUrl !=null && buildingCase.pictureUrl !='' }">		   
		      			<c:forEach items="${pictureList}" var="o" varStatus="status">
		      				<div><img src="${ctx }/${o}" style="max-width: 200px;max-height: 200px;"/> <a href="javascript:delPicture('${status.index}')">删除</a></div>
		      			</c:forEach>
				    </c:if> --%>
				 </div>
			</div>
		</div>
	</div>	
				
	<div class="save">
	     <a href="javascript:void(0);" class="backBtn" onclick="saveCarrier()">保存</a>
	</div>
    <input type="hidden" name="id" id="caseId" value="${buildingCase.id}" />
  </form>
</div>
		
<script type="text/javascript">

	$(function(){
		//加载图片
		var picStr = '${buildingCase.pictureUrl}';
		var picObj = JSON.parse(picStr);
		var picStrValue = '';
		var str ='';
		for(var i in picObj) {
			var url = picObj[i].phonePicUrl;
			picStrValue+=picObj[i].originalPicUrl+",";
			str+="<div><img src='${imgPath}/"+url+"' style='max-width: 200px;max-height: 200px;'/></div>";
		}	
		picStrValue = picStrValue.substring(0,picStrValue.length-1);
		$("#thumb").html(str);
		$("#pictureUrl").val(picStrValue);
	})

 	function saveCarrier() {
		var success = true;
 		var id = $("#caseId").val();
		var storeId = '${storeId}';
		var title =	$("#title").val();
		var description = $("#description").val();
		var pictureUrl = $("#pictureUrl").val();
		
		if($.trim(title) == ''){
			succss = false;
			alert("输入案例标题");			
		}
		else if($.trim(description) == ''){
			success = false;
			alert("请输入描述");
		}
		else if($.trim(pictureUrl) == ''){
			success = false;
			alert("请上传封面图片");
		}		
		
		
		if(success){
			$.post("${ctx}/admin/buildingGoods/saveCase.json",{
					id:id,
					storeId:storeId,
					title:title,
					description:description,
					pictureUrl:pictureUrl
				},
		       function(result){
		        	result = eval("("+result+")");
		    		if(result.status == "true" || result.status == true){
		    			alert(result.msg);
		    			setTimeout(function(){
		    				window.location.reload();
		    			},300);
		    		}else{
		    			alert(result.msg);
		    		}
		        });
		}
	} 
 	
 	function delPicture(index){
 		
 		if('${buildingCase.id}' == ''){
 			alert("您还未保存");
 			return;
 		}
 		
		$.post("${ctx}/admin/buildingMall/delPicture.json",{
				id:'${buildingCase.id}',
				index:index
			},
	       function(result){
	        	result = eval("("+result+")");
	    		if(result.status == "true" || result.status == true){
	    			alert(result.msg);
	    			setTimeout(function(){
	    				window.location.reload();
	    			},300);
	    		}else{
	    			alert(result.msg);
	    		}
	    });
	}
 	
    function triggerUpload(){
  	  $("#imgPreView").show();
  	  $("#thumb").prepend('<img class="wait" src="${ctx}/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;">'+'<p class="wait" style="line-height:25px;float:none;color:#78A200;font-size:12px;">文件上传中,请耐心等待...</p>');
		  var fileName = $("#uploadfile").val().toLowerCase();
		  if(fileName.indexOf(".png")>=0 || fileName.indexOf(".jpg")>=0 || fileName.indexOf(".gif")>=0) {
			$.ajaxFileUpload({
				url:'${ctx}/admin/buildingGoods/uploadCasePic.json', 
				//用于文件上传的服务器端请求地址
				secureuri:false, //是否需要安全协议，一般设置为false
				fileElementId:'uploadfile',  //文件上传域的ID
				data:{storeId:'${buildingStore.storeId}'},
				dataType: 'json',//返回值类型 一般设置为json
				success: function (data, status) {
					$("img").remove(".wait");
					$("p").remove(".wait");
					if(data.status==true || data.status=="true") {		
						//返回图片的json格式
						var imgArr = data.data;
						
						var  imgPath = '';
						for(var i=0;i<imgArr.length;i++) {
							imgPath+=imgArr[i]+",";
						}
						imgPath = imgPath.substring(0,imgPath.length-1);
						
						var picUrl = $("#pictureUrl").val();
						if($.trim(picUrl) == ''){
							$("#pictureUrl").val(imgPath);
						}else{						
							$("#pictureUrl").val($("#pictureUrl").val()+","+imgPath);													
						}												
						var imgHtml = '';						
						for(var i = 0;i<imgArr.length;i++){
							imgHtml+='<img style="max-width: 200px;max-height: 200px; margin-right:30px" src="${imgPath}/'+imgArr[i]+'"/>';
						}						
						$("#thumb").append(imgHtml);
					}else {
						$("#thumb").html(data.msg);
					}
				},
				/* error: function (data, status, e) {
					$("#thumb").empty();
					alert("上传失败，请重新尝试。");
				} */
			});
			} else {
				$("#thumb").empty();
				alert("请上传正确格式的图片！");
			}	
	 }
 	
</script>
