<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link href="${ctx}/resource/system/weixin/css/weixin.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/resource/system/admin/js/menuIndex.js">
<script type="text/javascript">
/* var mySound;
$(function(){
	$("#ownmenu_reply").addClass("cur");
	//初始化声音
	$(".iconCeshi").click(function(){
		var musicLink = $(this).attr("rel");
		soundManager.play("mySound11","${ctx}"+musicLink);
	});
	var type = $(".iactive").attr("rel");
	if(type != 1){
		$("#save").css("display","none");
	}
}); */

var ctx = '${ctx}';


function deleteMenu(o){
	var id = $(o).parent().attr("rel");
	if(confirm("确定要删除吗？")) {
		$.post("${ctx }/admin/custommenu/delete.json",{id:id},function(result){
			result = eval("("+result+")");
			alert(result.msg);
			setTimeout(function(){window.location.href="${ctx }/admin/custommenu/index.htm";},1000);
		});
	}
}

function menuSend(){
	if(confirm("确定要发布吗？")) {
		$.post("${ctx }/admin/custommenu/customMenuSend.json",null,function(result){
			result = eval("("+result+")");
			if(result.status==true||result.status=="true") {
				alert(result.msg);
			} else {
				alert(result.msg);
			}
		});				
	} 
}

</script>


<div class="conTit">
	<p>自定义菜单</p>
</div>

<div class="secTit">
	<button class="addBtn" onclick="addRoot()">添加菜单</button>
</div>

<div class="main_table" style="margin:0;">
<div class="newReplyPart">
  <div class="wrap_div fixed hasLeft">
    <%--左侧menu--%>
    <div class="hasLeftMenu" style="padding-bottom:20px;">
   	<ul class="huifu_navgtion" id="menu_navgtion" rel="0">
      <c:forEach items="${listCustomMenu }" var="o">
		  <li rel="${o.id }">
		  	<c:choose>
		  		<c:when test="${o.childrenListSize==0 }">
		  			<span>${o.menuName }</span>
		  			<button class="add" onclick="addChild(this)">添加</button>
		  			<button class="modfly" onclick="toEdit(this)">修改</button>
		  			<%--<button class="del" onclick="deleteMenu(this);">删除</button>--%>
		  			<a href="javascript:void(0);" class="yes" onclick="editMenu(${o.id}, 0, this)">确定</a>
		  		</c:when>
		  		<c:otherwise>
		  			<span>${o.menuName }</span>
		  			<button class="add" onclick="addChild(this)">添加</button>
		  			<button class="modfly" onclick="toEdit(this)">修改</button>
		  			<a href="javascript:void(0);" class="yes" onclick="editMenu(${o.id}, 0, this)">确定</a>
		  			<ul style="margin-left:20px;" rel="${o.id }">
		  				<c:forEach items="${o.listChildren }" var="n">		  				
		  					 <li rel="${n.id }">
		  					 	 <span style="display:none">${n.id}</span>		  					 
			  					 <span>${n.menuName }</span>
			  					 <span style="display:none">${n.menuKey}</span>
			  					 <span style="display:none">${n.eventType}</span>		  					 
			  					 <span style="display:none">${n.respType}</span>		  					 
			  					 <span style="display:none">${n.respInfo}</span>		  					 
			  					 <button class="modfly" onclick="toEdit(this)">修改</button>
			  					 <button class="del" onclick="deleteMenu(this);">删除</button>
			  					 <a href="javascript:void(0);" class="yes" onclick="editMenu(${n.id}, ${o.id}, this)">确定</a>
		  					 </li>
		  				</c:forEach>
		  			</ul>
		  		</c:otherwise>
		  	</c:choose>
		  </li>
      </c:forEach>
	</ul>
    <button class="msgsend greenBtn" onclick="menuSend()">发布</button>
    </div>
    
    <!-- 自定义菜单回复内容部分 开始 -->
     <div class="cutomMenuBox">
     
   	 <div class="inputArea">
	     <label>menukey:<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="text" id="menuKey" name="menuKey" value="" maxlength="50"/>
	     </div>
	 </div>
     
     <div class="inputArea">
	     <label>菜单类型 :<font style="color: red">*</font></label>
	     <div class="inputLine">
	          <input type="radio" id="eventType1" name="openOrClose" style="width: auto" value="click" /> click 	  	 	  
	  	 	  <input type="radio" id="eventType2" name="openOrClose" style="width: auto" value="view" /> view
	     </div>
	 </div>
    
<%--     <!-- 选择类型start -->
    <c:if test="${customMenu.respType==null&&customMenu.id!=null }">
            <div class="punBoxWarp">
            <h2>选择菜单动作</h2>
            <p>请选择订阅者点击菜单后，公众账号做出的相应动作</p>
            <ul>
                <li><A href="javascript:void(0);" onclick="setType('click')">发送信息</A></li>
                <li><A href="javascript:void(0);"  onclick="setType('view')">跳转到网页</A></li>
            </ul>
            </div>
    </c:if>
    <!-- 选择类型end --> --%>
	<!-- 链接类型 start-->
   	<div class="writeURL fixed">
       <div>
           <label>请输入连接地址(http开头):</label>
           <textarea id="urlLink" name="urlLink">${customMenu.urlLink }</textarea>
       </div>
       <button class="greenBtn" onclick="setUrlLink(${customMenu.id});">保存</button>
    </div>
    <!-- 链接类型 end-->   
  <!--    被关注时回复 start -->
    <div id="replyFrame">
      <ul id="nav_ul">
        <li id="wenzi" rel="1" class="iactive">文字</li>
        <!-- <li id="tuwen" rel="2">图文</li> --> 
        <li id="method" rel="3">方法</li>            
      </ul>
      <!-- 文字 start -->
      <div class="wzArea styleTop">
      <!-- 输入框 start -->
      <ul class="ceshilist huifuArea">
        <li class="wenziText" >
        	<textarea id="textareaContent"></textarea>
        </li>
        <li>
       		<input type="text" value="" style="background-color:white;border:1px solid #d7d6d6;height:40px;width:400px;padding-left:10px"  id="methodText" />
        </li>
        <%-- <li class="tuwenText" 
        <c:if test="${replyData.msgType==2 }">style="display:block;"</c:if>
        >
        <div class="fixed"> 
         <c:choose>
         	  	<c:when test="${replyData.childrenData.size() == 0}">
		            <div class="del tuw">
					   <div class="delmargin">
		                <h1>${replyData.title }</h1>
		                <p><fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
		                <div class="tuwenPic"><img width="320" src="${ctx}/${replyData.picUrl}" /></div>
                           <p class="miaoshu">${replyData.summary }</p>
		              </div>		
		            </div>
		            <!-- 单图文end -->
           	  	</c:when>
         	   <c:otherwise>
	            <!-- 多图文end -->
	            <div class="del tuw">
	              <div class="delmargin">
	                <h1>${replyData.title }</h1>
	                <p><fmt:formatDate value="${replyData.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
	                <div class="tuwenPic"><img width="320" src="${ctx}/${replyData.picUrl}" /></div>
	              </div>
	              <ul class="duoTWlist">
	                <c:forEach items="${replyData.childrenData}" var="n">
		              <li class="fixed">
	                  <h4>${n.title }</h4>
	                  <div class="tuwenPic"><img width="70" src="${ctx}/${n.picUrl}" /></div>
	                  </li>
	                </c:forEach>
	              </ul>
	            </div>
	            <!-- 多图文end -->
           	</c:otherwise>
          </c:choose>
        </div>
        </li> --%>
       
       
      </ul>
    <!--  输入框 end -->
      <div><a href="javascript:void(0);" id="save" class="green_btn" onclick="onSave();">保存</a><!-- <a href="javascript:void(0);" class="gray_btn">删除</a>--> </div>
    </div>
    <!-- 文字 end -->
    <!-- 图文start -->
    <div class="popBox tt" >
      <div class="popWrap tt"  >
        <div class="tuwen">
          <div class="titleDiv">
            <div class="title">选择图文</div>
            <div class="closeBtn levelA"></div>
          </div>
          <iframe src="${ctx}/admin/replydata/getArticle.json" marginwidth="0" marginheight="0" frameborder="0" scrolling="none" class="tanchuFrame"></iframe>
          <div class="footDiv"> <a href="javascript:void(0);" class="green_btn">确定</a> <a href="javascript:void(0);" class="gray_btn">取消</a> </div>
        </div>
      </div>
      <div class="zhezhao"></div>
    </div>
  <!--  图文end -->
  </div>
  </div> 
  <!-- 自定义菜单回复内容部分 结束 -->
</div>
</div>
</div>
