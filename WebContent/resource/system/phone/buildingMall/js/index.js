
 	var pageNum=1;  //加载第几页  
    var finished=1; //本次加载是否完成
    var sover=0; //  是否所有数据都加载完
    var pageSize = 5;      
        
    var typeId = 1;
    
    
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
            	findMerchantList(typeId,pageNum);
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
 function findMerchantList(buildingTypeId,pageN) {
	 	 
	      //  doTabCont(cateId,20);
	 		typeId = buildingTypeId;
	 		pageNum = pageN;
			//var jingdu = $('#jingdu').val();	
			//var weidu = $('#weidu').val();					  
	 		
	 		//本次加载未完成
	 		finished=0; 
	 		
		   	$.ajax({   
			type : "POST",   
			url : ctx+"/phone/buildingMall/getStoreList.json", 
			data : {	
				"buildingTypeId": buildingTypeId,
				"pageNum":pageNum,
				"pageSize":5
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
						resultValue+="	<dt>";
						resultValue+="		<a href="+ctx+"/phone/buildingMall/storeInfo.htm?storeId="+obj.id+">";
						resultValue+="		<img src='"+ctx+"/"+obj.coverPictureUrl+"' onerror=\"javascript:this.src='"+ctx+"/resource/system/common/image/default.png'\" />";																		
						resultValue+="		</a>";
						resultValue+="		<p>浏览:1000</p>";
						resultValue+="	</dt>";
						resultValue+="		<a href="+ctx+"/phone/buildingMall/storeInfo.htm?storeId="+obj.id+">";
						resultValue+="	<dd><h3>"+obj.title+"</h3></dd>";
						resultValue+="	<dd>主营："+obj.mainBusiness+"</dd>";
						resultValue+="	<dd>";
						resultValue+="		<span><i class=\"iconfont\">&#xe601;</i>"+obj.detailAddress+"</span>";
						resultValue+="		<label>距离100km</label>";
						resultValue+="	</dd>";
						resultValue+="	<dd><p><i>促</i>"+obj.promotion+"</p></dd>";
						resultValue+="		</a>";
						resultValue+="</dl>";
						//显示到页面
						//$('.chartBox').append(item);
						//$('#container').append(resultValue);
						//$('#result').html(resultValue);
					}
					if(pageNum == 1) {  //如果是第一页，则清楚数据，再加载 并切换样式
						$('#productList').html(resultValue);
						$("#buildingType li").each(function(){
							var tag = $(this).attr("id");
							var id = tag.substring(tag.indexOf("_")+1);
							if(id == buildingTypeId) {
								$(this).addClass("hover").siblings().removeClass("hover");
							}
						})
					}  
					else{						
						$('#productList').append(resultValue);
					}
					
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
/*			error :function(){   
			   alert("请刷新页面，重试");
				}  */
			});
			
  }
 
  
//页面加载完毕执行此js
$(document).ready(function() {	
	//默认加载默认分类显示所有商户
	//setTimeout(function(){findMerchantList(1,1);},500);
	findMerchantList(1,1);
});
 