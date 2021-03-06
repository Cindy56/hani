<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>玩法基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
        <li><a href="${ctx}/lottery/lotteryPlayConfig/">玩法基本信息列表</a></li>
        <li class="active"><a href="${ctx}/lottery/lotteryPlayConfig/form?id=${lotteryPlayConfig.id}">玩法基本信息<shiro:hasPermission name="lottery:lotteryPlayConfig:edit">${not empty lotteryPlayConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lottery:lotteryPlayConfig:edit">查看</shiro:lacksPermission></a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="lotteryPlayConfig" action="${ctx}/lottery/lotteryPlayConfig/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>

        <%-- 彩种代码 --%>
        <div class="control-group">
            <label class="control-label">彩种代码：</label>
            <div class="controls">
                <form:select id="lotteryCode" path="lotteryCode.code" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${lotteryTypeList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 玩法代码 --%>
        <div class="control-group">
            <label class="control-label">玩法代码：</label>
            <div class="controls">
                <form:select path="playCode" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList('lottery_play_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 玩法名称 --%>
        <div class="control-group">
            <label class="control-label">玩法名称：</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required" placeholder="请输入长度为1-50的玩法名称..."/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 玩法模式 --%>
        <div class="control-group">
            <label class="control-label">玩法模式：</label>
            <div class="controls">
                <form:select path="playType" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList('play_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 中奖概率 --%>
        <div class="control-group">
            <label class="control-label">中奖概率：</label>
            <div class="controls">
                <form:input path="winningProbability" htmlEscape="false" maxlength="10" class="input-xlarge required" onchange="this.value=this.value.replace(/[^0-9.]/g,'')" placeholder="请输入长度为1-10的小数形式中奖概率..."/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 返水级别 --%>
        <div class="control-group">
            <label class="control-label">返水级别：</label>
            <div class="controls">
                <form:input path="commissionRateMax" htmlEscape="false" maxlength="6" class="input-xlarge" onchange="this.value=this.value.replace(/[^0-9.]/g,'')" placeholder="请输入长度为1-6的小数形式返水级别..."/>
            </div>
        </div>

        <%-- 最低返水级别 --%>
        <div class="control-group">
            <label class="control-label">最低返水级别：</label>
            <div class="controls">
                <form:input path="commissionRateMin" htmlEscape="false" maxlength="6" class="input-xlarge" onchange="this.value=this.value.replace(/[^0-9.]/g,'')" placeholder="请输入长度为1-6的小数形式最低返水级别..."/>
            </div>
        </div>

        <%-- 担任单注投注倍数限制 --%>
        <div class="control-group">
            <label class="control-label">单人单期投注倍数限制：</label>
            <div class="controls">
                <form:input path="betRateLimit" htmlEscape="false" maxlength="6" class="input-xlarge" placeholder="请输入单人单期投注倍数限制..."/>
            </div>
        </div>

        <%-- 是否启用 --%>
        <div class="control-group">
            <label class="control-label">是否启用：</label>
            <div class="controls">
                <form:radiobutton path="isEnable" label="是" value="1" htmlEscape="false" class="required" checked="true"/>
                <form:radiobutton path="isEnable" label="否" value="0" htmlEscape="false" class="required"/>
            </div>
        </div>

        <%-- 玩法说明 --%>
        <div class="control-group">
            <label class="control-label">玩法说明：</label>
            <div class="controls">
                <form:textarea path="explain" htmlEscape="false" rows="4" maxlength="5000" class="input-xxlarge" placeholder="请输入字数5000以内的玩法说明..."/>
            </div>
        </div>

        <%-- 玩法示例 --%>
        <div class="control-group">
            <label class="control-label">玩法示例：</label>
            <div class="controls">
                <form:textarea path="example" htmlEscape="false" rows="4" maxlength="5000" class="input-xxlarge" placeholder="请输入字数5000以内的玩法示例..."/>
            </div>
        </div>

        <%-- 保存提交或退出 --%>
        <div class="form-actions">
            <shiro:hasPermission name="lottery:lotteryPlayConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
</body>
</html>