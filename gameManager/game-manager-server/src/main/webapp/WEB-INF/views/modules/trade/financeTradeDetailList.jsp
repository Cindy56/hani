<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账变流水管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/financeTradeDetail/">账变流水列表</a></li>
		<shiro:hasPermission name="trade:financeTradeDetail:edit"></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeTradeDetail" action="${ctx}/trade/financeTradeDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>用户名称</th>
				<th>业务编号</th>
				<th>账变交易类型</th>
				<th>账变金额</th>
				<th>账变前金额</th>
				<th>账变后金额</th>
				<th>创建</th>
				<th>创建人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeTradeDetail">
			<tr>
				<td><a href="${ctx}/trade/financeTradeDetail/form?id=${financeTradeDetail.id}">
					${financeTradeDetail.id}
				</a></td>
				<td>
					${financeTradeDetail.userName}
				</td>
				<td>
					${financeTradeDetail.busiNo}
				</td>
				<td>
					${financeTradeDetail.tradeType}
				</td>
				<td>
					${financeTradeDetail.amount}
				</td>
				<td>
					${financeTradeDetail.accountBlanceBefore}
				</td>
				<td>
					${financeTradeDetail.accountBlanceAfter}
				</td>
				<td>
					<fmt:formatDate value="${financeTradeDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeTradeDetail.createBy.id}
				</td>
				<!-- 
				<td>
					${fns:getDictLabel(financeTradeDetail.delFlag, 'del_flag', '')}
				</td>
				
				<shiro:hasPermission name="financetrade:financeTradeDetail:edit"><td>
    				<a href="${ctx}/financetrade/financeTradeDetail/form?id=${financeTradeDetail.id}">修改</a>
					<a href="${ctx}/financetrade/financeTradeDetail/delete?id=${financeTradeDetail.id}" onclick="return confirmx('确认要删除该账变流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission> -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>