<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>玩法基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/lottery/lotteryPlayConfig/">玩法基本信息列表</a>
		</li>
		<shiro:hasPermission name="lottery:lotteryPlayConfig:edit">
			<li>
				<a href="${ctx}/lottery/lotteryPlayConfig/form">玩法基本信息添加</a>
			</li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryPlayConfig" action="${ctx}/lottery/lotteryPlayConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li>
				<label>彩票代码：</label>
				<form:select path="lotteryCode" class="input-medium">
					<form:option value="" label="-- 请选择 --" />
					<form:options items="${lotteryTypeList}" itemLabel="name" itemValue="code" htmlEscape="false" />
				</form:select>
			</li>
			<li>
				<label>玩法名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium" />
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>玩法名称</th>
				<th>彩种名称</th>
				<th>玩法模式</th>
				<th style="width: 20%;">中奖概率</th>
				<th>最大抽水</th>
				<th>最小抽水</th>
				<th>单人单期投注倍数限制</th>
				<th>是否启用</th>
				<shiro:hasPermission name="lottery:lotteryPlayConfig:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="lotteryPlayConfig">
				<tr>
					<td>
						<a href="${ctx}/lottery/lotteryPlayConfig/form?id=${lotteryPlayConfig.id}"> ${lotteryPlayConfig.name} </a>
					</td>
					<td>${lotteryPlayConfig.lotteryCode.name}</td>
					<td>${fns:getDictLabel(lotteryPlayConfig.playType, 'play_type', '')}</td>
					<td style="text-align: right;">
						<c:choose>
							<c:when test="${null != lotteryPlayConfig.winningProbability && '' != lotteryPlayConfig.winningProbability}">
								${lotteryPlayConfig.winningProbability}
							</c:when>
							<c:otherwise>
								<c:forEach items="${lotteryPlayConfig.lotteryPlayMultList}" var="mult" varStatus="status">
									【场景${status.index+1}】${mult.explain}；概率：${mult.winningProbability}
									<br />
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align: right;">${lotteryPlayConfig.commissionRateMax}</td>
					<td style="text-align: right;">${lotteryPlayConfig.commissionRateMin}</td>
					<td style="text-align: right;">${lotteryPlayConfig.betRateLimit}</td>
					<td>${fns:getDictLabel(lotteryPlayConfig.isEnable, 'yes_no', '')}</td>
					<shiro:hasPermission name="lottery:lotteryPlayConfig:edit">
						<td>
							<a href="${ctx}/lottery/lotteryPlayConfig/form?id=${lotteryPlayConfig.id}">修改</a>
							<a href="${ctx}/lottery/lotteryPlayConfig/delete?id=${lotteryPlayConfig.id}" onclick="return confirmx('确认要删除该玩法基本信息吗？', this.href)">删除</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
        /**
         * 清空搜索事件
         */
        $("#clearSearch").click(function() {
            $("span.select2-chosen").text("-- 请选择 --");
        });
    </script>
</body>
</html>