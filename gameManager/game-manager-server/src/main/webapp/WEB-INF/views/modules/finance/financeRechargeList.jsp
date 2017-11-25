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
		<li class="active"><a href="${ctx}/finance/financeRecharge/">账户充值列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="financeRecharge" action="${ctx}/finance/financeRecharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>公司名称：</label>
				<form:input path="orgId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>充值单编号：</label>
				<form:input path="rechargeNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>充值方式：</label>
				<form:input path="paymentChannelId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>充值时间：</label>
				<input name="beginRechargeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.beginRechargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endRechargeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.endRechargeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>凭证单号：</label>
				<form:input path="thirdPayNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>到账时间：</label>
				<input name="thirdPayDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.thirdPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>审核人：</label>
				<form:input path="auditUserName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>审核时间：</label>
				<input name="beginAuditDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.beginAuditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endAuditDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeRecharge.endAuditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>公司名</th>
				<th>充值单编号</th>
				<th>充值银行名称</th>
				<th>充值银行卡号</th>
				<th>充值金额</th>
				<th>验证码</th>
				<th>充值时间</th>
				<th>充值通道</th>
				<th>第三方支付凭证</th>
				<th>第三方支付平台到账时间</th>
				<th>审核人</th>
				<th>审核日期</th>
				<th>状态</th>
				<th>创建者</th>
				<shiro:hasPermission name="trade:financeRecharge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeRecharge">
			<tr>
				<td><a href="${ctx}/finance/financeRecharge/form?id=${financeRecharge.id}">
					${financeRecharge.userName}
				</a></td>
				<td>${financeRecharge.orgId}</td>
				<td>${financeRecharge.rechargeNo}</td>
				<td>
					${financeRecharge.bankName}
				</td>
				<td>
					${financeRecharge.bankCardNo}
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
					${financeRecharge.paymentChannelId}
				</td>
				<td>
					${financeRecharge.thirdPayNo}
				</td>
				<td>
					<fmt:formatDate value="${financeRecharge.thirdPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
				<shiro:hasPermission name="trade:financeRecharge:edit">
					<td><a href="${ctx}/finance/financeRecharge/audit?id=${financeRecharge.id}">审核</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>