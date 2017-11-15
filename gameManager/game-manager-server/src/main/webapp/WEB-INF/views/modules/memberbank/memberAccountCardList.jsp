<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员银行卡管理</title>
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
		<li class="active"><a href="${ctx}/memberbank/memberAccountCard/">会员银行卡列表</a></li>
		<!--<shiro:hasPermission name="memberbank:memberAccountCard:edit"><li><a href="${ctx}/memberbank/memberAccountCard/form">会员银行卡添加</a></li></shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="memberAccountCard" action="${ctx}/memberbank/memberAccountCard/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户id：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行代码：</label>
				<form:select path="bankCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>银行卡账号：</label>
				<form:input path="bankCardNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>银行卡户名：</label>
				<form:input path="bankCardHolder" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行省份：</label>
				<form:select path="bankBranchProvince" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开户行城市：</label>
				<form:select path="bankBranchCity" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开户行全称：</label>
				<form:input path="bankBranchName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>银行卡状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bank_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${memberAccountCard.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${memberAccountCard.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>会员类型</th>
				<th>qq号码</th>
				<th>手机号码</th>
				<th>创建时间</th>
				<th>开户行代码</th>
				<th>银行卡账号</th>
				<th>银行卡户名</th>
				<th>开户行省份</th>
				<th>开户行城市</th>
				<th>开户行全称</th>
				<th>银行卡状态</th>
				<shiro:hasPermission name="memberbank:memberAccountCard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberAccountCard">
			<tr>
				<td>
					${fns:getDictLabel(memberAccountCard.accountType, "account_type", "")}
				</td>
				<td>
					${memberAccountCard.qqNo}
				</td>
				<td>
					${memberAccountCard.mobileNo}
				</td>
				<td>
					<fmt:formatDate value="${memberAccountCard.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><a href="${ctx}/memberbank/memberAccountCard/form?id=${memberAccountCard.id}">
					${fns:getDictLabel(memberAccountCard.bankCode, '', '')}
				</a></td>
				<td>
					${memberAccountCard.bankCardNo}
				</td>
				<td>
					${memberAccountCard.bankCardHolder}
				</td>
				<td>
					${fns:getDictLabel(memberAccountCard.bankBranchProvince, '', '')}
				</td>
				<td>
					${fns:getDictLabel(memberAccountCard.bankBranchCity, '', '')}
				</td>
				<td>
					${memberAccountCard.bankBranchName}
				</td>
				<td>
					${fns:getDictLabel(memberAccountCard.status, 'bank_status', '')}
				</td>
				<shiro:hasPermission name="memberbank:memberAccountCard:edit"><td>
    				<c:if test="${memberAccountCard.status eq '1' || memberAccountCard.status eq '2'}">
						<a href="${ctx}/memberbank/memberAccountCard/changeStatus?id=${memberAccountCard.id}" onclick="return confirmx('确认要${memberAccountCard.status eq '1'?'冻结':memberAccountCard.status eq '2'?'启用':''}该会员银行卡吗？', this.href)">${memberAccountCard.status eq '1'?'冻结':memberAccountCard.status eq '2'?'启用':''}</a>
					</c:if>
					<c:if test="${memberAccountCard.status ne '1' && memberAccountCard.status ne '2'}">
						审核中
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>