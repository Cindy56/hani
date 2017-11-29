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
		<li ><a href="${ctx}/lottery/lotteryTimeNum/batchView">批量修改时刻</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryTimeNum" action="${ctx}/lottery/lotteryTimeNum/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>彩票代码：</label>
				<form:select path="lotteryCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${dictLis}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开奖期号：</label>
				<form:input path="lotteryIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开奖号码：</label>
				<form:input path="openNum" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('draw_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="初始化开奖计划"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="新增6合彩开奖时刻"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="手工开奖"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="批量修改时刻"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="……"/></li>
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
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lotteryTimeNum">
			<tr>
				<td>
					
					${lotteryTimeNum.lotteryCodeName}
				</td>
				<td>
					${lotteryTimeNum.lotteryIssueNo}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betHaltDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lotteryTimeNum.openNum}
				</td>
				<td>
					${fns:getDictLabel(lotteryTimeNum.status, 'draw_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>