<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户充值管理</title>
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
		<li class="active"><a href="${ctx}/recharge/financeRecharge/">账户充值列表</a></li>
		<shiro:hasPermission name="recharge:financeRecharge:edit"><li><a href="${ctx}/recharge/financeRecharge/form">账户充值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeRecharge" action="${ctx}/recharge/financeRecharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户id：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公司id：</label>
				<form:input path="companyId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>充值单编号：</label>
				<form:input path="rechargeNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>充值时间：</label>
				<input name="beginRechargeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.beginRechargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endRechargeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.endRechargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户id</th>
				<th>用户名</th>
				<th>公司id</th>
				<th>充值单编号</th>
				<th>充值金额</th>
				<th>验证码</th>
				<th>充值时间</th>
				<th>audit_user_name</th>
				<th>audit_date</th>
				<th>状态:0支付中、1取消支付，2第三方平台到账，3财务审核中，4财务审核通过，5平台到账 )</th>
				<th>create_date</th>
				<th>create_by</th>
				<th>update_date</th>
				<th>update_by</th>
				<th>del_flag</th>
				<shiro:hasPermission name="recharge:financeRecharge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeRecharge">
			<tr>
				<td><a href="${ctx}/recharge/financeRecharge/form?id=${financeRecharge.id}">
					${financeRecharge.user.id}
				</a></td>
				<td>
					${financeRecharge.userName}
				</td>
				<td>
					${financeRecharge.companyId}
				</td>
				<td>
					${financeRecharge.rechargeNo}
				</td>
				<td>
					${financeRecharge.rechargeAmount}
				</td>
				<td>
					${financeRecharge.validateCode}
				</td>
				<td>
					<fmt:formatDate value="${financeRecharge.rechargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeRecharge.auditUserName}
				</td>
				<td>
					<fmt:formatDate value="${financeRecharge.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeRecharge.status}
				</td>
				<td>
					<fmt:formatDate value="${financeRecharge.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeRecharge.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${financeRecharge.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeRecharge.updateBy.id}
				</td>
				<td>
					${fns:getDictLabel(financeRecharge.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="recharge:financeRecharge:edit"><td>
    				<a href="${ctx}/recharge/financeRecharge/form?id=${financeRecharge.id}">修改</a>
					<a href="${ctx}/recharge/financeRecharge/delete?id=${financeRecharge.id}" onclick="return confirmx('确认要删除该账户充值吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>