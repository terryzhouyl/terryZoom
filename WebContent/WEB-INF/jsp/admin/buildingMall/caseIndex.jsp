<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#buildingMallMenu").addClass("cur");
	});	
	function confirmConsumption(caseId) {
		if(confirm("确认要删除吗？")) {
			if(caseId == ""){
				alert("请选择要删除的案例");
			}else {
				$.post("${ctx}/admin/buildingMall/delCase.json",{caseId:caseId},function(result){
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
	<p>案例管理</p>
		<div class="fr"><a href="${ctx }/admin/buildingMall/editCase.htm?storeId=${storeId}" class="backBtn">新增</a></div>
</div>

<%-- <div class="secTit searchBox">
    <form action="${ctx }/admin/city/index.htm" name="searchForm" id="searchForm">
            城市名称：
            <input type="text" value="${name }" name="name" maxlength="16" />
            <a href="javascript:search()" class="backBtn">查询</a>
    </form>
</div> --%>

<table class="theTable">
    <thead>
        <tr>
        	<th width="8%">序号</th>
        	<th>案例标题</th>
        	<th>案例描述</th>
        	<th width="400">操作</th>
        </tr>
    </thead>
    <tbody>
     	<c:forEach items="${page.rows }" var="o" varStatus="status">
	        <tr>
	        	<td>${(page.currentPage-1)*page.pageSize + status.count}</td>
	        	<td>${o.title}</td>
	        	<td>${o.description}</td>
		        <td>
		        	<a href="${ctx}/admin/buildingGoods/editCase.htm?storeId=${storeId}&buildingCaseId=${o.id}" class="backBtn" >编辑</a>
		        	<a href="javascript:void(0)" class="backBtn" onclick="confirmConsumption('${o.id}')">删除</a>
		        </td>
	        </tr>
        </c:forEach>
    </tbody>
</table>
