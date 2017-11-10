<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开奖管理</title>
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
		<li class="active"><a href="${ctx}/lottery/lotteryTimeNum/">开奖时刻列表</a></li>
		<shiro:hasPermission name="lottery:lotteryTimeNum:edit"><li><a href="${ctx}/lottery/lotteryTimeNum/form">开奖时刻添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryTimeNum" action="${ctx}/lottery/lotteryTimeNum/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>彩票代码：</label>
				<form:select path="lotteryCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('lottery_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开奖期号：</label>
				<form:input path="lotteryIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开奖号码：</label>
				<form:input path="drawNum" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('draw_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>彩票代码</th>
				<th>开奖期号</th>
				<th>投注开始时间</th>
				<th>投注截止时间</th>
				<th>封单时间(秒)</th>
				<th>开奖号码</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="lottery:lotteryTimeNum:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lotteryTimeNum">
			<tr>
				<td>
					${fns:getDictLabel(lotteryTimeNum.lotteryCode, 'lottery_category', '')}
				</td>
				<td>
					${lotteryTimeNum.lotteryIssueNo}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betStartGmt}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betEndGmt}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lotteryTimeNum.betHaltTime}
				</td>
				<td>
					${lotteryTimeNum.drawNum}
				</td>
				<td>
					${fns:getDictLabel(lotteryTimeNum.status, 'draw_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="lottery:lotteryTimeNum:edit"><td>
    				<a href="${ctx}/lottery/lotteryTimeNum/form?id=${lotteryTimeNum.id}">修改</a>
					<a href="${ctx}/lottery/lotteryTimeNum/delete?id=${lotteryTimeNum.id}" onclick="return confirmx('确认要删除该保存开奖时刻成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>