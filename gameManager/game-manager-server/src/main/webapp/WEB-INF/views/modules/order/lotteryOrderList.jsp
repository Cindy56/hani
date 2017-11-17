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
			<li><label>user_id：</label>
				<sys:treeselect id="user" name="user.id" value="${lotteryOrder.user.id}" labelName="user.name" labelValue="${lotteryOrder.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>lottery_code：</label>
				<form:input path="lotteryCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>投注期号：</label>
				<form:input path="betIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>投注类型：比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式：</label>
				<form:input path="betType" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>投注内容：</label>
				<form:input path="betDetail" htmlEscape="false" maxlength="4500" class="input-medium"/>
			</li>
			<li><label>投注金额，单位为元：</label>
				<form:input path="betAmount" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>投注倍数：</label>
				<form:input path="betRate" htmlEscape="false" maxlength="6" class="input-medium"/>
			</li>
			<li><label>奖金模式：1800,1700,1960，需要在服务端做校验：</label>
				<form:input path="playModeMoney" htmlEscape="false" maxlength="6" class="input-medium"/>
			</li>
			<li><label>佣金比例，返点比例，返水比例需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式：</label>
				<form:input path="playModeCommissionRate" htmlEscape="false" maxlength="6" class="input-medium"/>
			</li>
			<li><label>玩法模式：0元1角2分：</label>
				<form:input path="playModeMoneyType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>注单来源：1浏览器2移动app：</label>
				<form:input path="orderSource" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>注单类型：1正常投注2追号注单2合买注单：</label>
				<form:input path="orderType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>方案外键：如果是追号、合买，查看注单详情的时候链接到对应的方案：</label>
				<form:input path="schemaId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>中奖金额，单位为元：</label>
				<form:input path="winAmount" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>撤单金额：</label>
				<form:input path="withdrawAmount" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>注单状态：0等待开奖1已中奖2未中奖3已撤单：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>create_date：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lotteryOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>create_by：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>update_date：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lotteryOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>update_by：</label>
				<form:input path="updateBy.id" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>user_id</th>
				<th>lottery_code</th>
				<th>投注期号</th>
				<th>投注类型：比如重庆时时彩后三直选，不同的类型对应到不同的算奖模式</th>
				<th>投注内容</th>
				<th>投注金额，单位为元</th>
				<th>投注倍数</th>
				<th>奖金模式：1800,1700,1960，需要在服务端做校验</th>
				<th>佣金比例，返点比例，返水比例需要在应用服务器做校验，看看奖金模式和返佣比率是否符合公式</th>
				<th>玩法模式：0元1角2分</th>
				<th>注单来源：1浏览器2移动app</th>
				<th>注单类型：1正常投注2追号注单2合买注单</th>
				<th>方案外键：如果是追号、合买，查看注单详情的时候链接到对应的方案</th>
				<th>中奖金额，单位为元</th>
				<th>撤单金额</th>
				<th>注单状态：0等待开奖1已中奖2未中奖3已撤单</th>
				<th>create_date</th>
				<th>create_by</th>
				<th>update_date</th>
				<th>update_by</th>
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