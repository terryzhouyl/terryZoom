<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>修改店铺信息</title>
<link href="${ctx}/resource/system/phone/buildingMall/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/system/phone/buildingMall/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/system/common/js/common.js" ></script>
</head>


<body>
<form id="validateForm" class="form-horizontal" method="post" action="${ctx}/store/updateStoreInfo/${info.id}" >
	<div class="storeName">
<%-- 		<ul>
			<li>							
				<c:if test="${key == 'title'}">
					<span>店铺名称：<input type="text" class="main" name="storeName" id="title" value = "${info.title}" /></span><i class="iconfont" onclick="doClear('title')">&#xe60c;</i>
				</c:if>																						
			</li>									
		</ul> --%>
		<ul>
			<li>
				<select id="province" onchange="getCity()">
					<option value="0">请选择省</option>
				</select>
			</li>	
			<li>
				<select id="city" name="cityId">
					<option value="0">请选择市</option>
				</select>
			</li>
			<li>							
				<span>详细地址：<input type="text" class="main" name="detailAddress" id="detailAddress" value = "${info.title}" /></span><i class="iconfont" onclick="doClear('title')">&#xe60c;</i>
			</li>											
		</ul> 
	</div>

	<div class="storeName-button">
		<a href="javascript:void(0)" onclick="return doSubmit();">保存</a>
	</div>
</form>


<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单-->
<jsp:include page="../footer.jsp" />
 
 
 <script>
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
 
 
	 function doSubmit(){	
		 var success = true;
		//$('#validateForm').submit();
		var cityId = $("#city").val();
		
		if(cityId == 0){
			alert("请选择省市");
			success = false;
		}		
		var detailAddress = $("#detailAddress").val();
		if($.trim(detailAddress) == ""){
			alert("请输入详细地址");
			success = false;
		}		
		
		if(success) {
			var param = {
					'province':$("#province>option:selected").html(),
					'cityId':cityId,
					'city':$("#city>option:selected").html(),
					'detailAddress':detailAddress
				};
							
			$.ajax({				
		        type: "post",
		        url: "${ctx}/phone/buildingMyCenter/saveAddressInfo.json",
		        data: param,
		     	success: function(result){
		     	result = eval("("+result+")");
		     	if(result.status == "true"){
		     		window.location.href = "${ctx}/phone/buildingMyCenter/storeConfigIndex.htm";
		     	}
		     	else{
		     		alert(result.msg);
		     	}
		      }
			});
		}		
	 }

	//清空文本框 
	function doClear(s_name){  	
		$('#'+s_name).val(""); 
	}
 </script>
</body>
</html>
