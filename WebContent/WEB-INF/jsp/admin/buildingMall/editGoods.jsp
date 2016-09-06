<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(function(){
	$("#buildingMallMenu").addClass("cur");
});
</script>

<div class="conTit fixed">
    <p>
   		<c:choose>
			<c:when test="${buildingGoods.id ==null}">新增商品</c:when>
			<c:otherwise>修改商品</c:otherwise>
		</c:choose>
    </p>
    <div class="fr"><a href="javascript:void(0);" class="backBtn" onclick="window.history.back(-1);">返回</a></div>
</div>

<div style="margin-left: 10px;">
  <form id="carrierForm" action="" method="post" enctype="multipart/form-data">
	<div class="inputArea">
	     <label>商品名称:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="goodsName" name="goodsName" value="${buildingGoods.goodsName}" maxlength="16"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>商品价格:<font style="color: red"></font></label>
	     <div class="inputLine">
	          <input type="text" id="goodsPrice" name="goodsPrice" value="${buildingGoods.goodsPrice}"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>商品单位:<font style="color: red"></font></label>
	     <div class="inputLine">
	          <input type="text" id="unit" name="unit" value="${buildingGoods.unit}"/>
	     </div>
	</div>
	<div class="inputArea02">
	  <label>风格图片:建议尺寸：640*320 <font style="color: red">*</font></label>
	    <input type="hidden" id="originalPicUrl" name="originalPicUrl" value="${buildingGoods.originalPicUrl}"/>
	    <div class="inputLine">
	    	   上传风格图片<input id="uploadfile" name="cmfile" type="file" onchange="triggerUpload()" />
	    </div>
	    <div id="imgPreView" >
	        <div id="preview" >
		      	 <div id="thumb" style="margin-left:21%; margin-top:30px;">
		      		<c:if test="${buildingGoods.phonePicUrl !=null && buildingGoods.phonePicUrl!='' }">
		      			<img src="${ctx }/${buildingGoods.phonePicUrl }" style="max-width: 200px;max-height: 200px;"/>
				    </c:if>
				 </div>
			</div>
		</div>
	</div>	
				
	<div class="save">
	     <a href="javascript:void(0);" class="backBtn" onclick="saveCarrier()">保存</a>
	</div>
    <input type="hidden" name="id" id="goodsId" value="${buildingGoods.id}" />
  </form>
</div>
		
<script type="text/javascript">
 	function saveCarrier() {
		var success = true;
 		var id = $("#goodsId").val();
		var storeId = '${buildingStoreId}';
		var goodsName =	$("#goodsName").val();
		var originalPicUrl = $("#originalPicUrl").val();
		var goodsPrice = $("#goodsPrice").val();
		var unit = $("#unit").val();
		
		if($.trim(goodsName) == ''){
			succss = false;
			alert("输入商品名称");			
		}
		else if($.trim(originalPicUrl) == ''){
			success = false;
			alert("请上传封面图片");
		}								
		
		if(success){
			
	    $.ajax({
			type:"post",
			url:"${ctx}/admin/buildingMall/saveGoods.json",
			async:false,
			data:{
				id:id,
				storeId:storeId,
				goodsName:goodsName,				
				originalPicUrl:originalPicUrl,
				goodsPrice:goodsPrice,
				unit:unit
			},
			success :function (result){
				result = eval("("+result+")");
	        	if(result.status == "true" || result.status == true){
	        		alert(result.msg);
	    			setTimeout(function(){
	    				window.location.reload();
	    				//location.href = "${ctx }/admin/buildingMall/goodsIndex.htm?storeId=${buildingStoreId}";
	    			},300);
	        	}
	        	else{
	        		alert(result.msg);
	        	}
			}
		});	
		}
	} 
 	
 	
    function triggerUpload(){
  	  $("#imgPreView").show();
  	  $("#thumb").html('<img src="${ctx}/resource/system/admin/images/loading.gif" width="25" style="display:inline-block;float:left;margin:0 5px 0 0;">'+'<p style="line-height:25px;float:none;color:#78A200;font-size:12px;">文件上传中,请耐心等待...</p>');
		  var fileName = $("#uploadfile").val().toLowerCase();
		  if(fileName.indexOf(".png")>=0 || fileName.indexOf(".jpg")>=0 || fileName.indexOf(".gif")>=0) {
			$.ajaxFileUpload({
				url:'${ctx}/file/upload.htm', 
				//用于文件上传的服务器端请求地址
				secureuri:false, //是否需要安全协议，一般设置为false
				fileElementId:'uploadfile',  //文件上传域的ID
				data:{imgWidth:320,type:2},
				dataType: 'json',//返回值类型 一般设置为json
				success: function (data, status) {
					$("#thumb").empty();
					if(data.status==true || data.status=="true") {
						$("#originalPicUrl").val(data.data.filepath);
						$("#thumb").html('<img style="max-width: 200px;max-height: 200px;" src="${ctx}/'+data.data.filepath+'"/>');
					}else {
						$("#thumb").html(data.msg);
					}
				},
				error: function (data, status, e) {
					$("#thumb").empty();
					alert("上传失败，请重新尝试。");
				}
			});
			} else {
				$("#thumb").empty();
				alert("请上传正确格式的图片！");
			}	
	 }
 	
</script>
