<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<link href="${ctx}/resource/system/common/css/pagination.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/resource/system/common/js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		$("#buildingMallMenu").addClass("cur");
	});	
	/* function confirmConsumption(cityId) {
		if(confirm("确认要删除吗？")) {
			if(cityId == ""){
				alert("请选择要删除的城市");
			}else {
				$.post("${ctx}/admin/city/delete.json",{cityId:cityId},function(result){
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
	} */
	
	
	function handlePaginationClick(new_page_index, pagination_container) {	   	    
	   window.location.href = "${ctx}/admin/buildingMall/index.htm?pageNum="+(new_page_index+1)+"&pageSize="+5;
	    return false;  
	}
</script>

<script>
	//分页
	$(function(){
		$("#News-Pagination").pagination(
			'${page.total}', 			
			{items_per_page:'${page.pageSize}', // 每页显示多少条记录
				current_page:'${page.currentPage}' - 1, // 当前显示第几页数据
				num_display_entries:8, // 分页显示的条目数
				next_text:"下一页",
				prev_text:"上一页",
				num_edge_entries:2, // 连接分页主体，显示的条目数
				callback:handlePaginationClick
			}
		);		
	});
</script>

<div class="conTit fixed">
	<p>建材商城</p>
		<div class="fr"><a href="${ctx}/admin/buildingMall/editStore.htm" class="backBtn">新增</a></div>
</div>

<table class="theTable">
    <thead>
        <tr>
        	<th width="8%">序号</th>
        	<th>商家ID</th>
        	<th>标题</th>
        	<th>地址</th>
        	<th>联系电话</th>
        	<th>建材类型</th>
        	<th width="400">操作</th>
        </tr>
    </thead>
    <tbody>
     	<c:forEach items="${page.rows }" var="o" varStatus="status">
	        <tr>
	        	<td>${(page.currentPage-1)*page.pageSize + status.count}</td>
	        	<td>${o.storeNo}</td>
	        	<td>${o.title}</td>
	        	<td>${o.province}${o.city}</td>
	        	<td>${o.contactPhone}</td>
	        	<td>${o.buildingTypeName}</td>	        	
		        <td>
		        	<a href="${ctx }/admin/buildingMall/editStore.htm?storeId=${o.id}" class="backBtn" >编辑</a>
		        	<a href="${ctx }/admin/buildingGoods/goodsIndex.htm?storeId=${o.id}" class="backBtn">商品管理</a>
		        	<a href="${ctx }/admin/buildingGoods/caseIndex.htm?storeId=${o.id}" class="backBtn">案例管理</a>
		        	<%-- <a href="javascript:void(0)" class="backBtn" onclick="confirmConsumption('${o.id}')">删除</a> --%>
		        </td>
	        </tr>
        </c:forEach>        
    </tbody>
</table>
<div id="News-Pagination"></div>
