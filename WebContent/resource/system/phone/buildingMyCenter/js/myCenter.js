	//生成二维码
			function getTwoDimensionCode(){
				var buildingStoreId = '${buildingStore.id}';
				var url = "http://www.sunjoypai.com/phone/buildingMall/storeInfo.htm?storeId="+buildingStoreId;
				window.location.href = "http://qr.topscan.com/api.php?text="+url;
			}	
			
			$(".my_top .container").on('click',function(){
				window.location.href = "${ctx}/phone/buildingMyCenter/myConfigIndex.htm";												
			})
			
			//商品管理
			function goodsManage(){
				window.location.href="${ctx}/phone/buildingMyCenter/goodsManage.htm?storeId=${buildingStore.id}";
			}
			
			//案例管理
			function caseManage(){
				window.location.href="${ctx}/phone/buildingMyCenter/caseManage.htm?storeId=${buildingStore.id}";
			}
			//查看店铺
			function storeShow(){
				window.location.href="${ctx}/phone/buildingMall/storeInfo.htm?storeId=${buildingStore.id}";
			}
			//店铺设置
			function storeSetting(){
				window.location.href="${ctx}/phone/buildingMyCenter/storeConfigIndex.htm?buildingStoreId=${buildingStore.id}";
			}
			
					
		//申请店铺		
		function applyStore(){
			$.ajax({				
		        type: "post",
		        url: "${ctx}/phone/buildingMyCenter/applyStore.json",
		       // async:false,
		        data: {},
	        success: function(result){
	        	result = eval("("+result+")");
	        	if(result.status == "true"){
	        		alert("申请成功");
	        		window.location.reload();
	        	}
	        	else{
	        		alert(result.msg);
	        	}
	        }
		  });
		}	
		
		