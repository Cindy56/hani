<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>彩种基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请在修改后再次提交。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/lottery/lotteryType/">彩种基本信息列表</a></li>
        <li class="active"><a href="${ctx}/lottery/lotteryType/form?id=${lotteryType.id}">彩种基本信息<shiro:hasPermission name="lottery:lotteryType:edit">${not empty lotteryType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lottery:lotteryType:edit">查看</shiro:lacksPermission></a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="lotteryType" action="${ctx}/lottery/lotteryType/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>
        
        <%-- 彩种类型 --%>
        <div class="control-group">
            <label class="control-label">彩种类型：</label>
            <div class="controls">
                <form:select path="parentCode" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList('lottery_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 彩种代码 --%>
        <div class="control-group">
            <label class="control-label">彩种代码：</label>
            <div class="controls">
                <form:input path="code" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 彩种名称 --%>
        <div class="control-group">
            <label class="control-label">彩种名称：</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 是否自动开奖 --%>
        <div class="control-group">
            <label class="control-label">是否自动开奖：</label>
            <div class="controls">
                <form:radiobuttons path="isAuto" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 是否有效 --%>
        <div class="control-group">
            <label class="control-label">是否有效：</label>
            <div class="controls">
                <form:radiobuttons path="isEnable" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 每日开售时间 --%>
        <div class="control-group">
            <label class="control-label">每日开售时间：</label>
            <div class="controls">
                <form:input path="startDate" htmlEscape="false" maxlength="50" class="input-xlarge "/>
            </div>
        </div>
        
        <%-- 每日停售时间 --%>
        <div class="control-group">
            <label class="control-label">每日停售时间：</label>
            <div class="controls">
                <form:input path="endDate" htmlEscape="false" maxlength="50" class="input-xlarge "/>
            </div>
        </div>
        
        <%-- 每日期数 --%>
        <div class="control-group">
            <label class="control-label">每日期数：</label>
            <div class="controls">
                <form:input path="times" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
                <span class="help-inline">期 <font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 开奖周期 --%>
        <div class="control-group">
            <label class="control-label">开奖周期：</label>
            <div class="controls">
                <form:input path="periodTotalTime" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
                <span class="help-inline">分钟 <font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 封单时间 --%>
        <div class="control-group">
            <label class="control-label">封单时间：</label>
            <div class="controls">
                <form:input path="periodHaltTime" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
                <span class="help-inline">秒钟 <font color="red">*</font> </span>
            </div>
        </div>
        
        <%-- 每期投注最高金额 --%>
        <div class="control-group">
            <label class="control-label">每期投注最高金额：</label>
            <div class="controls">
                <form:input path="amountMaxBet" htmlEscape="false" class="input-xlarge" value="0" />
                <span class="help-inline"> 元</span>
            </div>
        </div>
        
        <%-- 当前期号 --%>
        <div class="control-group">
            <label class="control-label">当前期号：</label>
            <div class="controls">
                <form:input path="currentIssueNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
            </div>
        </div>
        
        <%-- 下期期号 --%>
        <div class="control-group">
            <label class="control-label">下期期号：</label>
            <div class="controls">
                <form:input path="nextIssueNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
            </div>
        </div>
        
        <%-- 操作按钮 --%>
        <div class="form-actions">
            <shiro:hasPermission name="lottery:lotteryType:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
</body>
</html>