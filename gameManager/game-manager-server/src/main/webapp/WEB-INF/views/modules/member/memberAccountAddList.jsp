<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员信息管理</title>
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
		<shiro:hasPermission name="memberadd:memberAccount:edit"><li><a href="${ctx}/memberadd/memberAccount/form">会员开户添加</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/memberadd/memberAccount/">返点信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="memberAccount" action="${ctx}/memberadd/memberAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号码：</label>
				<form:input path="mobileNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>上级代理账号</th>
				<th>用户id</th>
				<th>机构、盘口id</th>
				<th>会员类型</th>
				<th>qq号码</th>
				<th>手机号码</th>
				<th>余额</th>
				<th>冻结余额</th>
				<th>账户状态</th>
				<shiro:hasPermission name="memberadd:memberAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberAccount">
			<tr>
				<td><a href="${ctx}/memberadd/memberAccount/form?id=${memberAccount.id}">
					${memberAccount.parentAgentId}
				</a></td>
				<td>
					${memberAccount.user.id}
				</td>
				<td>
					${memberAccount.orgId}
				</td>
				<td>
					${fns:getDictLabel(memberAccount.accountType, 'member_type', '')}
				</td>
				<td>
					${memberAccount.qqNo}
				</td>
				<td>
					${memberAccount.mobileNo}
				</td>
				<td>
					${memberAccount.blance}
				</td>
				<td>
					${memberAccount.blanceFrozen}
				</td>
				<td>
					${fns:getDictLabel(memberAccount.status, '', '')}
				</td>
				<shiro:hasPermission name="memberadd:memberAccount:edit"><td>
    				<a href="${ctx}/memberadd/memberAccount/form?id=${memberAccount.id}">修改</a>
					<a href="${ctx}/memberadd/memberAccount/delete?id=${memberAccount.id}" onclick="return confirmx('确认要删除该会员信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- <div class="pagination">${page}</div> -->
</body>
</html>