<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理管理</title>
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
		<li class="active"><a href="${ctx}/member/memberAccount/">会员管理列表</a></li>
		<shiro:hasPermission name="member:memberAccount:edit"><li><a href="${ctx}/member/memberAccount/form">会员管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberAccount" action="${ctx}/member/memberAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员类型：：</label>
				<form:radiobuttons path="accountType" items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>开户行：</label>
				<form:select path="bankCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开卡人名称：</label>
				<form:input path="bankCardHolder" htmlEscape="false" maxlength="500" class="input-medium"/>
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
			<li><label>qq号码：</label>
				<form:input path="qqNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="mobileNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>创建日期：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${memberAccount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>机构id，盘口id</th>
				<th>会员类型：</th>
				<th>安全密码</th>
				<th>开户行</th>
				<th>银行卡账号</th>
				<th>开卡人名称</th>
				<th>开户行省份</th>
				<th>开户行城市</th>
				<th>开户行全称</th>
				<th>qq号码</th>
				<th>手机号码</th>
				<th>账户余额</th>
				<th>冻结金额</th>
				<th>状态</th>
				<shiro:hasPermission name="member:memberAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberAccount">
			<tr>
				<td><a href="${ctx}/member/memberAccount/form?id=${memberAccount.id}">
					${memberAccount.orgId}
				</a></td>
				<td>
					${fns:getDictLabel(memberAccount.accountType, 'member_type', '')}
				</td>
				<td>
					${memberAccount.secPassword}
				</td>
				<td>
					${fns:getDictLabel(memberAccount.bankCode, '', '')}
				</td>
				<td>
					${memberAccount.bankCardNo}
				</td>
				<td>
					${memberAccount.bankCardHolder}
				</td>
				<td>
					${fns:getDictLabel(memberAccount.bankBranchProvince, '', '')}
				</td>
				<td>
					${fns:getDictLabel(memberAccount.bankBranchCity, '', '')}
				</td>
				<td>
					${memberAccount.bankBranchName}
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
				<shiro:hasPermission name="member:memberAccount:edit"><td>
    				<a href="${ctx}/member/memberAccount/form?id=${memberAccount.id}">修改</a>
					<a href="${ctx}/member/memberAccount/delete?id=${memberAccount.id}" onclick="return confirmx('确认要删除该会员管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>