<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>彩种基本信息管理</title>
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
        <li class="active"><a href="${ctx}/lottery/lotteryType/">彩种基本信息列表</a></li>
        <shiro:hasPermission name="lottery:lotteryType:edit"><li><a href="${ctx}/lottery/lotteryType/form">彩种基本信息添加</a></li></shiro:hasPermission>
    </ul>
    <form:form id="searchForm" modelAttribute="lotteryType" action="${ctx}/lottery/lotteryType/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li>
                <label>彩种类型：</label>
                <form:select path="parentCode" class="input-medium">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList('lottery_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>彩种名称：</label>
                <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>彩种名称</th>
                <th>彩种类型</th>
                <th>是否自动开奖</th>
                <th>是否有效</th>
                <th>每日开售时间</th>
                <th>每日停售时间</th>
                <th>每日期数(期)</th>
                <th>开奖周期(分钟)</th>
                <th>封单时间(秒钟)</th>
                <th>每期投注最高金额(元)</th>
                <shiro:hasPermission name="lottery:lotteryType:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="lotteryType">
            <tr>
                <td><a href="${ctx}/lottery/lotteryType/form?id=${lotteryType.id}">
                    ${lotteryType.name}
                </a></td>
                <td>
                    ${lotteryType.parentCode}
                </td>
                <td>
                    ${lotteryType.isAuto}
                </td>
                <td>
                    ${lotteryType.isEnable}
                </td>
                <td>
                    ${lotteryType.startDate}
                </td>
                <td>
                    ${lotteryType.endDate}
                </td>
                <td>
                    ${lotteryType.times}
                </td>
                <td>
                    ${lotteryType.periodTotalTime}
                </td>
                <td>
                    ${lotteryType.periodHaltTime}
                </td>
                <td>
                    ${lotteryType.amountMaxBet}
                </td>
                <shiro:hasPermission name="lottery:lotteryType:edit"><td>
                    <a href="${ctx}/lottery/lotteryType/form?id=${lotteryType.id}">修改</a>
                    <a href="${ctx}/lottery/lotteryType/delete?id=${lotteryType.id}" onclick="return confirmx('确认要删除该彩种基本信息吗？', this.href)">删除</a>
                </td></shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>