<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/taglibs.jsp"%>
<link href="${ctx}/resource/system/weixin/css/weixin.css" rel="stylesheet" type="text/css" />
<div class="conTit fixed">
	  <p>素材管理</p>
</div>
<div class="newReplyPart">
  <div class="wrap_div fixed" id="wrap_div">
    <%--左侧menu
    <%@ include file="../reply/replyLeftMenu.jsp"%>
    --%>
    <%--图文消息 start--%>
      <div class="conIndex fixed" id="tuwenMessage" > 
	        <div class="topTool" style="padding:15px 0;margin:0 15px 10px;height: 25px;">
	            <input type="hidden" id="pageInfo" currPage="${page.currentPage}" totalPage="${page.totalPage}"/>
	            <div class="updownPage" style="height: 20px;">
		              <em rel="${page.currentPage-1}">上一页</em>
		              <span>${page.currentPage}/${page.totalPage}</span>
		              <em rel="${page.currentPage+1}">下一页</em>
		              <input value="${page.currentPage}"/>
		              <a href="javascript:void(0);">跳到</a>
	            </div>
	        </div>
	        
	        <div class="twDetail martop fixed">
	          <div class="addtwMes"> <a href="${ctx }/admin/replydata/articleOne.htm"><img src="${ctx}/resource/system/weixin/images/danTW.png" /><span>新建单图文消息</span></a> <A href="${ctx }/admin/replydata/articleMore.htm"><img src="${ctx}/resource/system/weixin/images/duoTW.png" /><span>新建多图文消息</span></A></div>
	          <c:set var="i" value="0"/>  
	          <c:forEach items="${listReplyData.rows}" var="o">
				 <c:if test="${i<2 }">
	          	  	<c:choose>
	          	  		<c:when test="${o.childrenData.size() > 0}">
				            <div class="del tuw">
				              <div class="delmargin">
				                <h1>${o.title }</h1>
				                <p><fmt:formatDate value="${o.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
				                <div class="tuwenPic"><img width="320" src="${ctx}/${o.picUrl}" /></div>
				              </div>
				              <ul class="duoTWlist">
				              	<c:forEach items="${o.childrenData}" var="n">
					              	<li class="fixed">
					                	<h4>${n.title }</h4>
					                	<div class="tuwenPic"><img width="70" src="${ctx}/${n.picUrl}" /></div>
					                </li>
				                </c:forEach>
				              </ul>
				              <div class="editOrDel">
				                <ul class="fixed">
				                  <li><img onclick="toEdit(${o.id}, ${o.childrenData.size()})" src="${ctx}/resource/system/weixin/images/pencial.png" /></li>
				                  <li class="last"><img onclick="toDelete(${o.id})" src="${ctx}/resource/system/weixin/images/rubbish.png" /></li>
				                </ul>
				              </div>
				            </div>
	          	  		</c:when>
	          	  		<c:otherwise>
	          	  			<div class="del tuw">
				              <div class="delmargin">
				                <h1>${o.title }</h1>
				                <p><fmt:formatDate value="${o.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
				                <div class="tuwenPic"><img width="320" src="${ctx}/${o.picUrl}" /></div>
				                <p class="miaoshu">${o.summary }</p>
				              </div>
				              <div class="editOrDel">
				                <ul class="fixed">
				                  <li><img onclick="toEdit(${o.id}, ${o.childrenData.size()})" src="${ctx}/resource/system/weixin/images/pencial.png" /></li>
				                  <li class="last"><img onclick="toDelete(${o.id})" src="${ctx}/resource/system/weixin/images/rubbish.png" /></li>
				                </ul>
				              </div>
				            </div>
	          	  		</c:otherwise>
	          	  	</c:choose>
	              </c:if>
		            <c:set var="i" value="${i+1 }"/>
	          </c:forEach>
	        </div>
	     
	        <div class="twDetail martop fixed">
	          <c:set var="i" value="0"/>  
	          <c:forEach items="${listReplyData.rows}" var="o">
	          	  <c:if test="${i>1 }">
	          	  	<c:choose>
	          	  		<c:when test="${o.childrenData.size() > 0}">
				             <div class="del tuw">
				              <div class="delmargin">
				                <h1>${o.title }</h1>
				                <p><fmt:formatDate value="${o.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></p>
				                <div class="tuwenPic"><img width="320" src="${ctx}/${o.picUrl}" /></div>
				              </div>
				              <ul class="duoTWlist">
				              	<c:forEach items="${o.childrenData}" var="n">
					              	<li class="fixed">
					                	<h4>${n.title }</h4>
					                	<div class="tuwenPic"><img width="70" src="${ctx}/${n.picUrl}" /></div>
					                </li>
				                </c:forEach>
				              </ul>
				              <div class="editOrDel">
				                <ul>
				                  <li><img onclick="toEdit(${o.id}, ${o.childrenData.size()})" src="${ctx}/resource/system/weixin/images/pencial.png" /></li>
				                  <li class="last"><img onclick="toDelete(${o.id})" src="${ctx}/resource/system/weixin/images/rubbish.png" /></li>
				                </ul>
				              </div>
				            </div>
	          	  		</c:when>
	          	  		<c:otherwise>
	          	  			<div class="del tuw">
				              <div class="delmargin">
				                <h1>${o.title }</h1>
				                <p>
				                  <fmt:formatDate value="${o.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/>
				                </p>
				                <div class="tuwenPic"><img width="320" src="${ctx}/${o.picUrl}" /></div>
				                <p class="miaoshu">${o.summary }</p>
				              </div>
				              <div class="editOrDel">
				                <ul>
				                  <li><img onclick="toEdit(${o.id}, ${o.childrenData.size()})" src="${ctx}/resource/system/weixin/images/pencial.png" /></li>
				                  <li class="last"><img onclick="toDelete(${o.id})" src="${ctx}/resource/system/weixin/images/rubbish.png" /></li>
				                </ul>
				              </div>
				            </div>
	          	  		</c:otherwise>
	          	  	</c:choose>
	              </c:if>
	             <c:set var="i" value="${i+1 }"/>
	          </c:forEach>
	        </div>
    </div>
    <%--图文消息 end--%>
  </div>
</div>
<script type="text/javascript">
$('#huifu_navgtion .huifuD').addClass('active');
$('.sec_nav .sucaikuA').addClass('active');
$(".menuList #areplay").addClass("currentMenu");
/*------单选框效果-----*/
$('.radioIput').click(function(){
$(this).addClass('cur').parent().parent('li').siblings('li').find('.radioIput').removeClass('cur');
});

$(function(){
	$("#weixinMenu").addClass("cur");
	
	//上一页，下一页事件
	$(".topTool>div.updownPage>em").click(function(){
		var topage = $(this).attr("rel");
		var curr =  $("#pageInfo").attr('currPage');
		var total =  $("#pageInfo").attr('totalPage');
		if(topage>total){
			return;
		}
		if(topage>0) {
			window.location="${ctx}/admin/replydata/index.htm?msgType=${msgType}&offset="+topage;
		}
	});
	
	
 
	
	$(".topTool>div.updownPage>a").click(function(){
		var topage = $(".topTool>div.updownPage>input").val();
		var curr =  $("#pageInfo").attr('currPage');
		var total =  $("#pageInfo").attr('totalPage');
		if(topage>total){
			return;
		}
		if(topage>0) {
			window.location="${ctx}/admin/replydata/index.htm?msgType=${msgType}&offset="+topage;
		}
	});
});

function toEdit(id, childrenSize) {
	if(childrenSize > 0) {
		window.location.href="${ctx}/admin/replydata/articleMore.htm?id="+id;
	}else {
		window.location.href="${ctx}/admin/replydata/articleOne.htm?id="+id;
	}
}

function toDelete(id) {
	if(confirm("确定要删除吗？")) {
		$.post("${ctx}/admin/replydata/ajaxdelete.json",{id:id},function(result){
			result = eval("("+result+")");
			if(result.status == "true" || result.status == true){
			    window.location.reload();
			} 
		});
	}
}
/*---------*/
$('.currentTitle ul li').click(function(){
	$(this).addClass('cur').siblings('li').removeClass('cur');
	});
</script> 
