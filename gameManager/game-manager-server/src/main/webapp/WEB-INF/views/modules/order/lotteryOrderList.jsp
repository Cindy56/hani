<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细管理</title>
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
		<li class="active"><a href="${ctx}/order/lotteryOrder/">订单明细列表</a></li>
		<shiro:hasPermission name="order:lotteryOrder:edit"><li><a href="${ctx}/order/lotteryOrder/form">订单明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryOrder" action="${ctx}/order/lotteryOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户ID：</label>
				<sys:treeselect id="user" name="user.id" value="${lotteryOrder.user.id}" labelName="user.name" labelValue="${lotteryOrder.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>彩票代码：</label>
				<form:input path="lotteryCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>投注期号：</label>
				<form:input path="betIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>投注类型：</label>
				<form:input path="betType" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>用户ID</th>
				<th>彩票代码</th>
				<th>投注期号</th>
				<th>投注类型</th>
				<th>投注内容</th>
				<th>投注金额</th>
				<th>投注倍数</th>
				<th>奖金模式</th>
				<th>佣金比例</th>
				<th>玩法模式</th>
				<th>注单来源</th>
				<th>注单类型</th>
				<th>方案外键</th>
				<th>中奖金额</th>
				<th>撤单金额</th>
				<th>注单状态</th>
				<th>创建日期</th>
				<th>创建人</th>
				<th>更新日期</th>
				<th>更新人</th>
				<shiro:hasPermission name="order:lotteryOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lotteryOrder">
			<tr>
				<td><a href="${ctx}/order/lotteryOrder/form?id=${lotteryOrder.id}">
					${lotteryOrder.id}
				</a></td>
				<td>
					${lotteryOrder.user.name}
				</td>
				<td>
					${lotteryOrder.lotteryCode}
				</td>
				<td>
					${lotteryOrder.betIssueNo}
				</td>
				<td>
					${lotteryOrder.betType}
				</td>
				<td>
					${lotteryOrder.betDetail}
				</td>
				<td>
					${lotteryOrder.betAmount}
				</td>
				<td>
					${lotteryOrder.betRate}
				</td>
				<td>
					${lotteryOrder.playModeMoney}
				</td>
				<td>
					${lotteryOrder.playModeCommissionRate}
				</td>
				<td>
					${lotteryOrder.playModeMoneyType}
				</td>
				<td>
					${lotteryOrder.orderSource}
				</td>
				<td>
					${lotteryOrder.orderType}
				</td>
				<td>
					${lotteryOrder.schemaId}
				</td>
				<td>
					${lotteryOrder.winAmount}
				</td>
				<td>
					${lotteryOrder.withdrawAmount}
				</td>
				<td>
					${lotteryOrder.status}
				</td>
				<td>
					<fmt:formatDate value="${lotteryOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lotteryOrder.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${lotteryOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lotteryOrder.updateBy.id}
				</td>
				<shiro:hasPermission name="order:lotteryOrder:edit"><td>
    				<a href="${ctx}/order/lotteryOrder/form?id=${lotteryOrder.id}">修改</a>
					<a href="${ctx}/order/lotteryOrder/delete?id=${lotteryOrder.id}" onclick="return confirmx('确认要删除该订单明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>