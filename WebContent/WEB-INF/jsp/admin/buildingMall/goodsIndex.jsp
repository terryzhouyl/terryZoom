<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#buildingMallMenu").addClass("cur");
	});	
	function putAwayGoods(goodsId) {
		if(confirm("确认要修改么？")) {
			if(goodsId == ""){
				alert("请选择要修改的商品");
			}else {
				$.post("${ctx}/admin/buildingMall/putAwayGoods.json",{goodsId:goodsId},function(result){
					result = eval("("+result+")");
					if(result.status == "true" || result.status == true){
						alert(result.msg);
						setTimeout(function(){
		    				window.location.reload();
						},500);
					}else{
						alert(result.msg);
					}
				});
			}
		}
	}
	
	function search(){
		 $("#searchForm").submit();
	}
</script>

<div class="conTit fixed">
	<p>商品管理</p>
		<div class="fr"><a href="${ctx }/admin/buildingMall/editGoods.htm?storeId=${buildingStoreId}" class="backBtn">新增</a></div>
</div>

<table class="theTable">
    <thead>
        <tr>
        	<th width="8%">序号</th>
        	<th>商品名称</th>
        	<th>商品价格</th>
        	<th width="400">操作</th>
        </tr>
    </thead>
    <tbody>
     	<c:forEach items="${page.rows}" var="o" varStatus="status">
	        <tr>
	        	<td>${(page.currentPage-1)*page.pageSize + status.count}</td>
	        	<td>${o.goodsName}</td>
	        	<td>${o.goodsPrice}${o.unit}</td>
		        <td>
		        	<a href="${ctx}/admin/buildingMall/editGoods.htm?storeId=${buildingStoreId}&goodsId=${o.id}" class="backBtn">编辑</a>		        	
		        	<a href="javascript:void(0)" class="backBtn" onclick="putAwayGoods('${o.id}')">
		        	<c:if test="${o.putAwayStatus == 1}">下架</c:if>
		        	<c:if test="${o.putAwayStatus == 0}">上架</c:if>
		        	</a>
		        </td>
	        </tr>
        </c:forEach>
    </tbody>
</table>
