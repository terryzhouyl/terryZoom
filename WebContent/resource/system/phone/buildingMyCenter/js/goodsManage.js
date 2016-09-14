	var pageNum=1;  //加载第几页  
    var finished=1; //本次加载是否完成
    var sover=0; //  是否所有数据都加载完
    var pageSize = 5;      //每页加载数据
    
    var putAwayStatus = 1;  //上线商品
    
    //添加底部菜单样式
    $(function(){
    	addFooterClass(0);
    })	
        
    //页面滚动执行事件
    $(window).scroll(function(){
        loadmore($(this));
    });   
    
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
            	getGoodsPage(putAwayStatus);
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
    
        
//根据选择端商户分类AJAX获取商户列表，并按经纬度排序，由近到远
 function getGoodsPage(putAwayStatus) {				  
	 		
	 		//本次加载未完成
	 		finished=0; 
	 		
		   	$.ajax({   
			type : "POST",   
			url : ctx+"/phone/buildingMyCenter/getGoodsPage.json", 
			data : {	
				"storeId":storeId,
				"pageNum":pageNum,
				"pageSize":5,
				"putAwayStatus":putAwayStatus
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
	                    var realPath = imgPath + "/" + obj.filePath + "/" + obj.smallPicUrl;
	                    resultValue +="<a href='#' title=''><img width='340' height='250' src='"+realPath+"' alt=''  onerror='javascript:this.src=\""+ctx+"/resource/system/common/image/default.png\"' /></a>";
	                    resultValue +="<a href='#' title=''><p>"+obj.goodsName+"</p></a>";
	                    resultValue +="<span>";
	                    resultValue +="￥"+obj.goodsPrice+obj.unit;
	                    if(obj.putAwayStatus == 1) {	                    	
	                    	resultValue +="<a href='javascript:putAwayGoods("+obj.id+")' class='contentbox-link2'>下架</a>";
	                    }
	                    else {
	                    	resultValue +="<a href='javascript:putAwayGoods("+obj.id+")' class='contentbox-link2'>上架</a>";
	                    }
	                    resultValue +="	<a href='javascript:deleteGoods("+obj.id+")' class='contentbox-link1'>删除</a>";
	                    resultValue +=" </span>";
	                    resultValue +="</li>";	   	    				
					}					
					$("#content").append(resultValue);
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
  
  /**
   * 商品上下架
   */
  function putAwayGoods(goodsId) {
		$.ajax({   
   			type : "POST",   
   			url : ctx+"/phone/buildingMyCenter/putAwayGoods.json", 
   			data : {	
   				'goodsId' : goodsId 
   			}, 
   			dataType: "json",   
   			success : function(result){
   				if(result.status){
   					layer.alert("操作成功");
   					window.location.reload();
   				} 
   				else {
   					layer.alert(result.msg);
   				}
   			}
   			
   		});				
  }
 
  /**
   * 删除商品
   */
  function deleteGoods(goodsId) {
	  $.ajax({   
 			type : "POST",   
 			url : ctx+"/phone/buildingMyCenter/deleteGoods.json", 
 			data : {	
 				'goodsId' : goodsId 
 			}, 
 			dataType: "json",   
 			success : function(result){
 				if(result.status){
 					layer.alert("删除成功");
 					window.location.reload();
 				} 
 				else {
 					layer.alert(result.msg);
 				}
 			}
 			
 		});			
  }
  
 /**
  * 初始化数据
  */
 function initData() {
	 pageNum = 1;
	 sover = 0;
	 finished = 1;	 
 }
 
 /**
  * tab页切换  今天怎么连这么个玩意儿都不行呢。。。。。暂时先放着吧
  * @param putAwayStatus
  */
 function changeGoodsInfo(putAwayStatus){
	 		
	 initData();
	 getGoodsPage(putAwayStatus);	
	 	 	 
	 if(putAwayStatus == 1) { //上架
		 $(".m_back li:eq(0)").addClass("hover").siblings().removeClass("hover");
	 }
	 else{
		 $(".m_back li:eq(1)").addClass("hover").siblings().removeClass("hover");
	 }
	 //$(this).parent().addClass("hover").siblings().removeClass("hover");
	 $("#content").empty();
 }
 
 
 
//页面加载完毕执行此js
$(document).ready(function() {	
	//默认加载默认分类显示所有商户
	//setTimeout(function(){findMerchantList(1,1);},500); 
	getGoodsPage(1);
});
 