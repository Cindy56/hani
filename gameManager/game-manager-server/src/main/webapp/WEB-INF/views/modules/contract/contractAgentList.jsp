<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理管理</title>
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
		<li class="active"><a href="${ctx}/contract/contractAgent/">代理列表</a></li>
		<shiro:hasPermission name="contract:agent:contract:edit"><li><a href="${ctx}/contract/contractAgent/form">代理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="contract" action="${ctx}/contract/contractAgent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构id：</label>
				<form:input path="office.id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>机构名称：</label>
				<form:input path="orgName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户id：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户登录名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户id：</label>
				<form:input path="accountId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:input path="openType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>分红模式：</label>
				<form:select path="benefitType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contract_model')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>分红周期：</label>
				<form:select path="benefitCycle" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contract_period')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>签约周期：</label>
				<form:input path="contractTime" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${contract.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${contract.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<!-- <th>机构id</th> -->
				<th>机构名称</th>
				<!-- <th>用户id</th> -->
				<th>用户登录名称</th>
				<!-- <th>账户id</th> -->
				<th>类型</th>
				<th>分红模式</th>
				<th>分红周期</th>
				<th>平台租金</th>
				<th>开户费</th>
				<th>签约周期</th>
				<th>备注</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>状态</th>
				<shiro:hasPermission name="contract:agent:contract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="contract">
			<tr>
				<%-- <td><a href="${ctx}/contract/contractAgent/form?id=${contract.id}">
					${contract.office.id}
				</a></td> --%>
				<td>
					${contract.orgName}
				</td>
				<%-- <td>
					${contract.user.id}
				</td> --%>
				<td>
					${contract.userName}
				</td>
				<%-- <td>
					${contract.accountId}
				</td> --%>
				<td>
					${contract.openType}
				</td>
				<td>
					${fns:getDictLabel(contract.benefitType, 'contract_model', '')}
				</td>
				<td>
					${fns:getDictLabel(contract.benefitCycle, 'contract_period', '')}
				</td>
				<td>
					${contract.rentAmount}
				</td>
				<td>
					${contract.openAmount}
				</td>
				<td>
					${contract.contractTime}
				</td>
				<td>
					${contract.remark}
				</td>
				<td>
					<fmt:formatDate value="${contract.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${contract.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(contract.status, 'contract_status', '')}
				</td>
				<shiro:hasPermission name="contract:agent:contract:edit"><td>
    				<a href="${ctx}/contract/contractAgent/form?id=${contract.id}">修改</a>
					<a href="${ctx}/contract/contractAgent/delete?id=${contract.id}" onclick="return confirmx('确认要删除该代理吗？', this.href)">删除</a>
					<c:if test="${contract.status eq '3' || contract.status eq '4'}">
						<a href="${ctx}/contract/contractCompany/updateStatus?id=${contract.id}" onclick="return confirmx('确认要${contract.status eq '3'?'冻结':contract.status eq '4'?'启用':''}该公司吗？', this.href)">${contract.status eq '3'?'冻结':contract.status eq '4'?'启用':''}</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>