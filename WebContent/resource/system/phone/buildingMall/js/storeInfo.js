
 	var pageNum=1;  //加载第几页  
    var finished=1; //本次加载是否完成
    var sover=0; //  是否所有数据都加载完        
    var typeId = 1;
    var lastClickTab = 'case';
    
    //页面滚动执行事件
    $(window).scroll(function(){
        loadmore($(this));
    });

            
    //初始化数据
    function initData(){
    	pageNum = 1;
    	finished = 1;
    	sover = 0;
    	$(".practiceCase-list").empty();
    	$(".contentbox>ul").empty();
    }
    
    //加载更多
    function loadmore(obj){
        if(finished==1 && sover==0)
        {
            var scrollTop = $(obj).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(obj).height();            
            //插入加载中提示
            if($(".loadmore").length==0)
            {
                var txt='<div class="loadmore"><span class="loading"></span>加载中..</div>';
                $(".jiazai").append(txt);
            }
            
            if (scrollTop + windowHeight -scrollHeight<=50 ) {
            	//*********需要自己定义*********            	
            	/*if($(".practiceCase-list").hasClass("on")){ //
            		//initData();
            		console.log("全部商品被选中");
            		loadGoods(pageNum,5);  		            		
            	}
            	else {             		
            		//initData();
            		console.log("实物案例被选中");            		
            		loadCase(pageNum,5); 
            	}      */
            	if($(".hctabs_nav li:eq(0)").hasClass("hover")){
            		loadCase(pageNum,5);
            	}
            	else {
            		loadGoods(pageNum,10);
            	}
            }
        }
    }
    
    //加载完
    function loadover(){
        if(sover==1)
        {	
            var overtext="没有更多了";
            $(".loadmore").remove();
            if($(".loadover").length>0)
            {
                $(".loadover span").eq(0).html(overtext);
            }
            else
            {
                var txt='<div class="loadover"><span>'+overtext+'</span></div>';
                $(".jiazai").append(txt);
            }
        }
    }
  
    //加载案例
    function loadCase(pageN,pageSize) {        	    	
   	 		pageNum = pageN;				     	 	
   	 		//本次加载未完成
   	 		finished=0; 
   	 		
   		   	$.ajax({   
   			type : "POST",   
   			url : ctx+"/phone/buildingMall/getStoreCaseOrGoods.json", 
   			data : {	
   				"pageNum":pageN,
   				"pageSize":pageSize,
   				"type":1,  //加载案例
   				"storeId":storeId
   			 }, 
   			dataType: "json",   
   			success : function(result) {    				   				
   				//解析JSON列表,并显示到页面中				
   				if(result.status == true||result.status == "true") {					
   					var datas = result.rows;
   					var resultValue='';
   					for(var i=0; i<datas.length; i++){  
   	                    var obj = datas[i];	                    
   						resultValue+="<dl>";
   						resultValue+="<dd><h3>"+obj.title+"</h3></dd>";
   						resultValue+="<dd>"+obj.description+"</dd>"; 	      
   						resultValue+="<dd>";
   						//此处循环获取图片
   						var picJson = JSON.parse(obj.pictureUrl);
   						for(var j=0;j<picJson.length;j++) {
     						resultValue+="<span><img width='160' height='160' class='fangd' src='"+ctx+"/"+picJson[j].cutPicUrl+"'  onerror=\"javascript:this.src='"+ctx+"/resource/system/common/image/default.png'\" /></span>";
   						}
   						resultValue+="</dd>";
   						resultValue+="</dl>";
   					}
   				   $('.practiceCase-list').append(resultValue);   
   				   
                   $(".loadmore").remove(); //去掉加载更多                    
                   finished=1; //本次加载完成
                   //最后一页                                          
                   if(pageNum>=result.totalPage)
                   {
                       sover=1;
                       loadover();
                   }
                   pageNum+=1;
   				}
   				
   			}  
   		}); 
    	
    }
             
    //加载商品
    function loadGoods(pageN,pageSize) {    	    	
	 		pageNum = pageN;				     	 	
   	 		//本次加载未完成
   	 		finished=0; 
   	 		
   		   	$.ajax({   
   			type : "POST",   
   			url : ctx+"/phone/buildingMall/getStoreCaseOrGoods.json", 
   			data : {	
   				"pageNum":pageN,
   				"pageSize":pageSize,
   				"type":2,
   				"storeId":storeId
   			}, 
   			dataType: "json",   
   			success : function(result) { 
   				//解析JSON列表,并显示到页面中				
   				if(result.status == true||result.status == "true") {					
   					var datas = result.rows;
   					var resultValue='';
   					for(var i=0; i<datas.length; i++){  
   	                    var obj = datas[i];	                    
   	                    resultValue += "<li>";
   	                 	resultValue += "<img width=340 height=250 src='"+obj.smallPicUrl+"'  onerror=\"javascript:this.src='"+ctx+"/resource/system/common/image/default.png'\" />";
   	              		resultValue += "<p>"+obj.goodsName+"</p>";
   	           			resultValue += "<span>"+obj.goodsPrice+"</span>";
   	           			resultValue += "</li>";
   					}
   				   $('.contentbox').append(resultValue);   
   				   
                   $(".loadmore").remove(); //去掉加载更多                    
                   finished=1; //本次加载完成
                   //最后一页                                          
                   if(pageNum>=result.totalPage)
                   {
                       sover=1;
                       loadover();
                   }
                   pageNum+=1;
   				}   				
   			}  
   		});     	
    }
    


/*********动态效果***************/
$(function(){	
	tabs(".hctabs_nav","hover",".hctabs_info");
})

//tab切换时数据初始化
function changeData(type){
	if(type == "case"){
		if(lastClickTab !='case') {			
			initData();
			loadCase(1,5);
			lastClickTab = 'case';
		}
	}
	else if(type =="goods"){
		if(lastClickTab!='goods'){			
			initData();
			loadGoods(1,10);
			lastClickTab = 'goods';
		}
	}
}

$(function(){
	//加载数据
	loadCase(pageNum,5);
})

 