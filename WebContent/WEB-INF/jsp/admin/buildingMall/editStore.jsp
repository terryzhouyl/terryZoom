<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/resource/system/common/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js"></script>
<script type="text/javascript">
$(function(){
	$("#buildingMallMenu").addClass("cur");
});
</script>

<div class="conTit fixed">
    <p>
   		<c:choose>
			<c:when test="${buildingStore.id ==null}">新增商家</c:when>
			<c:otherwise>修改商家</c:otherwise>
		</c:choose>
    </p>
    <div class="fr"><a href="javascript:void(0);" class="backBtn" onclick="window.history.back(-1);">返回</a></div>
</div>

<div style="margin-left: 10px;">
  <form id="carrierForm" action="" method="post">
	<div class="inputArea">
		<label>建材类别:<font style="color: red">*</font></label>
		<div class="inputLine">
			<select id="buildingTypeId" name="buildingTypeId">
				<option value="">请选择建材类型</option>
				<c:forEach items="${buildingTypeList}" var="btl" varStatus="status">
					<option value="${btl.id}"  <c:if test="${buildingStore.buildingTypeId == btl.id}">selected=selected</c:if>>${btl.typeName}</option>					
     			</c:forEach> 
			</select>
		</div>
	</div>
	<div class="inputArea">
	     <label>商家名称:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="title" name="title" value="${buildingStore.title}" maxlength="16"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>商家ID:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="storeNo" name="storeNo" value="${buildingStore.storeNo}"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>商家描述:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="description" name="description" value="${buildingStore.description}"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>商家主营业务:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="mainBusiness" name="mainBusiness" value="${buildingStore.mainBusiness}"/>
	     </div>
	</div>
	<div class="inputArea">
		<label>商家地区:<font style="color: red">*</font></label>
		<div class="inputLine" style="width:8%">
			<select id="province" onchange="getCity()">
				<option value="0">请选择省</option>
			</select>
		</div>	
		<div class="inputLine" style="width:8%;">
			<select id="city" name="cityId">
				<option value="0">请选择市</option>
			</select>
		</div>
<%-- 		<div class="inputLine" style="width:8%;">	
			<select id="district" name="district">
				<option value="">请选择区</option>
				<c:forEach items="${districtList}" var="dl" varStatus="status">
					<option value="${dl}" <c:if test="${buildingStore.district == dl}">selected=selected</c:if>>${dl}</option>					
     			</c:forEach> 								
			</select>			
		</div>  --%>
		<div class="inputLine" style="width:43%;">
			 <input type="text" id="detailAddress" name="detailAddress" value="${buildingStore.detailAddress}" placeholder="填写街道详细地址"/>
		</div>			
	</div>
 	<div class="inputArea">
	     <label>促销信息:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="promotion" name="promotion" value="${buildingStore.promotion}"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>营业时间:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="businessTime" name="businessTime" value="${buildingStore.businessTime}"/>
	     </div>
	</div>
	<div class="inputArea">
	     <label>联系电话:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="contactPhone" name="contactPhone" value="${buildingStore.contactPhone}"/>
	     </div>
	</div>
	<div class="inputArea02">
	  <label>风格图片:建议尺寸：640*320 <font style="color: red">*</font></label>
	    <div class="inputLine">
	    	   上传风格图片<input id="uploadfile" name="cmfile" type="file" />	    	   
	    </div>
	    <div id="imgPreView" >
	        <div id="preview" style="margin-left:21%; margin-top:30px;width:640px">
		      	 <div id="thumb" style="margin-left:21%; margin-top:30px;">
		      		<c:if test="${buildingStore.originalPicUrl !=null && buildingStore.originalPicUrl !='' }">
		      			<img src="${imgPath}/${buildingStore.coverPictureUrl}" style="max-width: 640px;max-height: 320px;"/>
				    </c:if>
				 </div>
			</div>
		</div>
	</div>	
		
		
	<div class="save">
	     <a href="javascript:void(0);" class="backBtn" onclick="saveCarrier()">保存</a>
	</div>
  </form>
</div>
		
<script type="text/javascript">

	//单图片上传预览
	$('input[type="file"]').change(function(e){
		var files = this.files;
		if(files.length) {
			//图片预览
			var previewHtml = "<img src='#previewImgSrc' alt='img' style='max-width: 300px;max-height: 300px;' />";
			//上传图片方法
			checkFile(this.files,"#preview",previewHtml);
		}
	});
	
	//保存店铺信息
 	function saveCarrier() {
		var success = true;
		
		var buildingTypeId = $("#buildingTypeId").val();				
		var description = $("#description").val();
		var mainBusiness = $("#mainBusiness").val();
		var cityId = $("#city").val();
		var district = $("#district").val();
		var detailAddress = $("#detailAddress").val();
		var promotion = $("#promotion").val();
		var contactPhone = $("#contactPhone").val();
		var businessTime = $("#businessTime").val();					
	
		if(cityId == 0){
			alert("请选择省市");
			success = false;
		}				
		if($.trim(buildingTypeId) ==""){
			alert("请选择建材类型");
			success = false;
		}
		if($.trim(description) ==""){
			alert("请输入商家描述");
			success = false;
		}
		if($.trim(mainBusiness) ==""){
			alert("请输入主营业务");
			success = false;
		}
		if($.trim(detailAddress) == ""){
			alert("请输入详细地址");
			success = false;
		}
		if($.trim(promotion) == ""){
			alert("请输入促销信息");
			success = false;
		}
		if($.trim(contactPhone) == ""){
			alert("请输入联系电话");
			success = false;
		}
		if($.trim(businessTime) == ""){
			alert("请输入营业时间");
			success = false; 
		}
 		
 		 var fileName = $("#uploadfile").val().toLowerCase();
 		 console.log(fileName);
/* 		 if(fileName.indexOf(".png")<0 && fileName.indexOf(".jpg")<0 && fileName.indexOf(".gif")<0 && fileName.indexOf(".jpeg")<0){ 
			  alert("请上传正确格式的图片");
			  success = false;
		 }  */
								
		var param= {'buildingTypeId':buildingTypeId,'description':description,'mainBusiness':mainBusiness,'cityId':cityId,'city':$("#city>option:selected").html(),'district':district
				,'detailAddress':detailAddress,'promotion':promotion,'contactPhone':contactPhone,'coverPictureUrl':'${buildingStore.coverPictureUrl}','businessTime':businessTime,
				'province':$("#province>option:selected").html(),'title':$("#title").val(),'storeNo':$("#storeNo").val(),'id':'${buildingStore.id}','memberId':'${buildingStore.memberId}',
				'smallPicUrl':'${buildingStore.smallPicUrl}','originalPicUrl':'${buildingStore.originalPicUrl}'
		}
		
		 
		 
		 
		
 	  if(success){		  		  		  
			if(fileName) {  				
				//上传图片
				param.originalPicUrl = '${buildingStore.originalPicUrl}';				
				$.ajaxFileUpload({
					url:'${ctx}/admin/buildingMall/save.json', 
					//用于文件上传的服务器端请求地址
					secureuri:false, //是否需要安全协议，一般设置为false
					fileElementId:'uploadfile',  //文件上传域的ID
					data:param,
					dataType: 'json',//返回值类型 一般设置为json
					success: function (result) {
						if(result.status == true|| result.status == "true") {
							alert("保存成功");	
							window.location.reload();
						}	
						else {
							alert(result.msg);
						}
					},				
				});
			} 		  
			else { //未上传图片
				$.ajax({
					type:"post",
					url:'${ctx}/admin/buildingMall/saveNoPic.json',
					data:param,
					dataType: 'json',//返回值类型 一般设置为json
					success: function (result) {
						if(result.status == true|| result.status == "true") {
							alert("保存成功");	
							window.location.reload();
						}	
						else {
							alert(result.msg);
						}
					},				
				});
			}  
	   } 		 
	}   
   
   $(function() {	   
	   //获取省份数据
	   var provinceData = getProvincesJson();	
	   var pro = '';
	   for(var i=0;i<provinceData.length;i++){	
			if(provinceData[i].provincename == '${buildingStore.province}'){	        				
   			pro+='<option selected="selected" value="'+provinceData[i].id+'">'+provinceData[i].provincename+'</option>' ;	
   			proId = provinceData[i].id;
			}
			else {
				pro+='<option value="'+provinceData[i].id+'">'+provinceData[i].provincename+'</option>' ;
			}
		}
	   $("#province").append(pro);
	   getCity();	   	   
   })
   
   
  	function getCity() {
	   var provinceId = $("#province>option:selected").val();			
	   var cityData = getCitysJson(provinceId);
		var city = '';
		for(var i=0;i<cityData.length;i++){	
			if(cityData[i].id == '${buildingStore.cityId}'){
				city+='<option selected value="'+cityData[i].id+'">'+cityData[i].cityname+'</option>' ;	
			}
			else {
				city+='<option value="'+cityData[i].id+'">'+cityData[i].cityname+'</option>' ;	        				
			}
		}	        		
		var ti = "<option value=0>请选择市</option>";
		$("#city").html(ti+city);
    }	
 	
</script>
