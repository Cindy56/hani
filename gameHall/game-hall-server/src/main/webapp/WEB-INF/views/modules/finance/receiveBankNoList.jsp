<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款人信息管理管理</title>
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
		<li class="active"><a href="${ctx}/finance/receiveBankNo/">收款人信息管理列表</a></li>
		<shiro:hasPermission name="finance:receiveBankNo:edit"><li><a href="${ctx}/finance/receiveBankNo/form">收款人信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="receiveBankNo" action="${ctx}/finance/receiveBankNo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="receiveBankNo">
			<tr>
				<td>
				${receiveBankNo.bankNo}
				</td>
				<td>
				${receiveBankNo.userName}
				</td>
				<td>
				${receiveBankNo.bankCardNo}
				</td>
				<td>
    				<a href="${ctx}/finance/receiveBankNo/form?id=${receiveBankNo.id}">修改</a>
					<a href="${ctx}/finance/receiveBankNo/delete?id=${receiveBankNo.id}" onclick="return confirmx('确认要删除该收款人信息管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>