<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>银行卡管理管理</title>
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
		<li class="active"><a href="${ctx}/bank/companyCard/">银行卡管理列表</a></li>
		<li><a href="${ctx}/bank/companyCard/bankform">银行卡管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="companyCard" action="${ctx}/bank/companyCard/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司id：</label>
				<form:input path="companyId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>银行卡号：</label>
				<form:input path="bankCardNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户人：</label>
				<form:input path="bankCardHolder" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行省份：</label>
				<form:input path="bankBranchProvince" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行城市：</label>
				<form:input path="bankBranchCity" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行名称：</label>
				<form:input path="bankBranchName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${companyCard.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${companyCard.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>公司id</th>
				<th>银行卡号</th>
				<th>开户人</th>
				<th>开户行省份</th>
				<th>开户行城市</th>
				<th>开户行名称</th>
				<th>银行卡状态</th>
				<th>更新时间</th>			
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyCard">
			<tr>
				<td><a href="${ctx}/bank/companyCard/form?id=${companyCard.id}">
					${companyCard.companyId}
				</a></td>
				<td>
					${companyCard.bankCardNo}
				</td>
				<td>
					${companyCard.bankCardHolder}
				</td>
				<td>
					${companyCard.bankBranchProvince}
				</td>
				<td>
					${companyCard.bankBranchCity}
				</td>
				<td>
					${companyCard.bankBranchName}
				</td>
				<td>
					${companyCard.status}
				</td>
				<td>
					<fmt:formatDate value="${companyCard.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/bank/companyCard/form?id=${companyCard.id}">激活</a>
					<a href="${ctx}/bank/companyCard/disable?id=${companyCard.id}" onclick="return confirmx('确认要禁用该银行卡吗？', this.href)">禁用</a>
					<a href="${ctx}/bank/companyCard/delete?id=${companyCard.id}" onclick="return confirmx('确认要删除该银行卡吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>