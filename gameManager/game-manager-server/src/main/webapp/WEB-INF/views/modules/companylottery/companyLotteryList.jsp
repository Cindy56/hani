<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司产片管理</title>
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
		<li class="active"><a href="${ctx}/companylottery/companyLottery/">公司产片列表</a></li>
		<shiro:hasPermission name="companylottery:companyLottery:edit"><li><a href="${ctx}/companylottery/companyLottery/form">公司产片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyLottery" action="${ctx}/companylottery/companyLottery/" method="post" class="breadcrumb form-search">
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
				<th>公司id</th>
				<th>彩票代码</th>
				<th>彩票玩法</th>
				<shiro:hasPermission name="companylottery:companyLottery:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyLottery">
			<tr>
				<td><a href="${ctx}/companylottery/companyLottery/form?id=${companyLottery.id}">
					${companyLottery.companyId}
				</a></td>
				<td>
					${fns:getDictLabel(companyLottery.lotteryCode, 'lottery_category', '')}
				</td>
				<td>
					${companyLottery.lotteryPlay}
				</td>
				<shiro:hasPermission name="companylottery:companyLottery:edit"><td>
    				<a href="${ctx}/companylottery/companyLottery/form?id=${companyLottery.id}">修改</a>
					<a href="${ctx}/companylottery/companyLottery/delete?id=${companyLottery.id}" onclick="return confirmx('确认要删除该公司产片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>