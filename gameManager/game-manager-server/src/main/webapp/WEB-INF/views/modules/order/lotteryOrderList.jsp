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
			<li><label>订单编号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="50" class="input-medium"/>
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
			<li><label>投注类型：</label>
				<form:input path="betType" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>玩法模式：</label>
				<form:input path="playModeMoneyType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>注单来源：</label>
				<form:input path="orderSource" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>注单类型：</label>
				<form:input path="orderType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>注单状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>create_date：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lotteryOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<!-- <th>id</th> -->

				<th>订单编号</th>
				<th>用户名</th>
				<!-- <th>user_id</th> -->
				<th>机构id</th>
				<th>彩票名称</th>
				<th>投注期号</th>
				<!-- <th>账户id</th> -->
				<th>投注类型：</th>
				<th>投注内容</th>
				<th>投注金额</th>
				<th>投注倍数</th>
				<th>奖金模式</th>
				<th>返点比例</th>
				<th>玩法模式</th>
				<th>注单来源</th>
				<th>注单类型</th>
				<th>中奖金额</th>
				<th>撤单金额</th>
				<th>注单状态</th>
				<th>创建日期</th>
				<shiro:hasPermission name="order:lotteryOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lotteryOrder">
			<tr>
				<%-- <td><a href="${ctx}/order/lotteryOrder/form?id=${lotteryOrder.id}">
					${lotteryOrder.id}
				</a></td> --%>
				<td>
					${lotteryOrder.orderNo}
				</td>
				<td>
					${lotteryOrder.user.name}
				</td>
				<%-- <td>
					${lotteryOrder.user.id}
				</td> --%>
				<td>
					${lotteryOrder.orgName}
				</td>
				<td>					
					${fns:getDictLabel(lotteryOrder.lotteryCode, 'SSC', '')}
				</td>
				<td>
					${lotteryOrder.betIssueNo}
				</td>
				<%-- <td>
					${lotteryOrder.accountId}
				</td> --%>
				<td>					
					${fns:getDictLabel(lotteryOrder.betType, 'lottery_play_code', '')}
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
					${fns:getDictLabel(lotteryOrder.playModeMoneyType, 'play_type', '')}
				</td>
				<td>					
					${fns:getDictLabel(lotteryOrder.orderSource, 'order_source', '')}
				</td>
				<td>					
					${fns:getDictLabel(lotteryOrder.orderType, 'order_type', '')}
				</td>
				<td>
					${lotteryOrder.winAmount}
				</td>
				<td>
					${lotteryOrder.withdrawAmount}
				</td>
				<td>					
					${fns:getDictLabel(lotteryOrder.status, 'order_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${lotteryOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(lotteryOrder.delFlag, 'del_flag', '')}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>