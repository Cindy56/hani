<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>彩种基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            // 表单校验
            $("#inputForm").validate({
                submitHandler: function(form){
                    // 提交前给隐藏的节点赋值
                    $(".lotteryCode").val($("#code").val());
                    $("#name").val($("span.select2-chosen").get(1).innerHTML);
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请在修改后再次提交。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                        if (!$("#companyIdName").val()) {
                            $("#companyIdName").parent().append($("<label id='companyErrId' for='code' class='error'>必填信息</label>"));
                        } else {
                            $("#companyErrId").parent().remove();
                        }
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
        // 添加一行开奖方案输入框
        function addRow(list, idx, tpl, row){
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list+idx).find("select").each(function(){
                $(this).val($(this).attr("data-value"));
            });
            $(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
                var ss = $(this).attr("data-value").split(',');
                for (var i=0; i<ss.length; i++){
                    if($(this).val() == ss[i]){
                        $(this).attr("checked","checked");
                    }
                }
            });
        }
        // 删除一行开奖方案输入框
        function delRow(obj, prefix){
            var caseCount = $("#lotteryTypeTimeList").children().length;
            // 最后一行不允许删除
            if (caseCount == 1) {
                return;
            }
            var id = $(prefix + "_id");
            var delFlag = $(prefix+"_delFlag");
            if (id.val() == ""){
                $(obj).parent().parent().remove();
            }else if(delFlag.val() == "0"){
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            }else if(delFlag.val() == "1"){
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
        }
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
            <label class="control-label">彩种名称：</label>
            <div class="controls">
                <form:select id="code" path="code" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList(lotteryType.parentCode)}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <%-- 彩种名称 --%>
        <div hidden="true">
        	<form:input id="name" path="name"/>
        </div>

        <%-- 是否自动开奖 --%>
        <div class="control-group">
            <label class="control-label">是否自动开奖：</label>
            <div class="controls">
                <form:radiobutton path="isAuto" label="是" value="1" htmlEscape="false" class="required" checked="true"/>
                <form:radiobutton path="isAuto" label="否" value="0" htmlEscape="false" class="required"/>
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

        <%-- 每日开奖期数 --%>
        <div class="control-group">
            <label class="control-label">每日开奖期数：</label>
            <div class="controls">
                <form:input path="times" type="number" min="0" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font></span>
            </div>
        </div>

        <%-- 每期投注最高金额 --%>
        <div class="control-group">
            <label class="control-label">每期投注最高金额：</label>
            <div class="controls">
                <form:input path="amountMaxBet" type="number" min="0" htmlEscape="false" class="input-xlarge "/>
            </div>
        </div>

        <%-- 开奖方案 --%>
        <div class="control-group">
            <label class="control-label">开奖方案：</label>
            <div class="controls">
                    <table id="contentTable" style="max-width: 40%;" class="table table-striped table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th class="hide"></th>
                                <th>
                                	开始时间
                                	<span class="help-inline"><font color="red">*</font></span>
                                </th>
                                <th>
                                	截止时间
                                	<span class="help-inline"><font color="red">*</font></span>
                                </th>
                                <th>
                                	开奖周期时间
                                	<span class="help-inline"><font color="red">*</font></span>
                                </th>
                                <th>
                                	每期封单时间
                                	<span class="help-inline"><font color="red">*</font></span>
                                </th>
                                <shiro:hasPermission name="lottery:lotteryType:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
                            </tr>
                        </thead>
                        <tbody id="lotteryTypeTimeList">
                        </tbody>
                        <shiro:hasPermission name="lottery:lotteryType:edit"><tfoot>
                            <tr><td colspan="7"><a href="javascript:" onclick="addRow('#lotteryTypeTimeList', lotteryTypeTimeRowIdx, lotteryTypeTimeTpl);lotteryTypeTimeRowIdx = lotteryTypeTimeRowIdx + 1;" class="btn">新增方案</a></td></tr>
                        </tfoot></shiro:hasPermission>
                    </table>
					<%-- 开奖方案输入框生成模板 --%>
                    <script type="text/template" id="lotteryTypeTimeTpl">//<!--
                        <tr id="lotteryTypeTimeList{{idx}}">
                            <td class="hide">
                                <input id="lotteryTypeTimeList{{idx}}_id" name="lotteryTypeTimeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="lotteryTypeTimeList{{idx}}_delFlag" name="lotteryTypeTimeList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input name="lotteryTypeTimeList[{{idx}}].lotteryCode" class="lotteryCode" type="hidden" />
                            </td>
                            <td>
                                <input id="lotteryTypeTimeList{{idx}}_startTime" name="lotteryTypeTimeList[{{idx}}].startTime" type="text" value="{{row.startTime}}" maxlength="10" onclick="WdatePicker({dateFmt:'HH:mm'})" class="input-small required"/>
                            </td>
                            <td>
                                <input id="lotteryTypeTimeList{{idx}}_endTime" name="lotteryTypeTimeList[{{idx}}].endTime" type="text" value="{{row.endTime}}" maxlength="10" onclick="WdatePicker({dateFmt:'HH:mm'})" class="input-small required"/>
                            </td>
                            <td>
                                <input id="lotteryTypeTimeList{{idx}}_periodTotalTime" name="lotteryTypeTimeList[{{idx}}].periodTotalTime" type="number" min="0" value="{{row.periodTotalTime}}" maxlength="6" class="input-small required"/>
                            </td>
                            <td>
                                <input id="lotteryTypeTimeList{{idx}}_periodHaltTime" name="lotteryTypeTimeList[{{idx}}].periodHaltTime" type="number" min="0" value="{{row.periodHaltTime}}" maxlength="6" class="input-small required"/>
                            </td>
                            <shiro:hasPermission name="lottery:lotteryType:edit"><td class="text-center" width="10">
                                {{#delBtn}}<span class="close" onclick="delRow(this, '#lotteryTypeTimeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
                            </td></shiro:hasPermission>
                        </tr>//-->
                    </script>
					<%-- 用于进入页面时生成方案展示，如果是新增，默认一条方案新增输入框 --%>
                    <script type="text/javascript">
                        var lotteryTypeTimeRowIdx = 0, lotteryTypeTimeTpl = $("#lotteryTypeTimeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                        $(document).ready(function() {
                            var data = ${fns:toJson(lotteryType.lotteryTypeTimeList)};
                            for (var i=0; i<data.length; i++){
                                addRow('#lotteryTypeTimeList', lotteryTypeTimeRowIdx, lotteryTypeTimeTpl, data[i]);
                                lotteryTypeTimeRowIdx = lotteryTypeTimeRowIdx + 1;
                            }
                            // 如果进入页面没有方案数据（新增），默认显示一条方案新增输入框
                            if (0 == data.length) {
                                addRow('#lotteryTypeTimeList', lotteryTypeTimeRowIdx, lotteryTypeTimeTpl, "");
                            }
                        });
                    </script>
                </div>
            </div>

        <%-- 保存或返回 --%>
        <div class="form-actions">
            <shiro:hasPermission name="lottery:lotteryType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
    <%-- 彩种类型于彩种代码级联事件 --%>
    <script type="text/javascript">
        $("#parentCode").change(function() {
            $.ajax({
                type: "post",
                async: false,
                url: ctx+"/lottery/lotteryType/findPlayCode",
                dataType: "json",
                data: "lotterytype="+this.value,
                success: function(data) {
                    // 如果相应数据不为空则继续解析
                    if (data) {
                        var playCode = data.lotteryPlayCode;
                        if (playCode) {
                            var tmpStr = "<option checked='checked'>-- 请选择 --</option>";
                            for (var i = 0;i < playCode.length; i++) {
                                tmpStr += "<option value='" + playCode[i].value + "'>" + playCode[i].label + "</option>";
                            }
                            // 新数据直接覆盖
                            $("#code").html(tmpStr);
                            // 清空原值选择
                            $(".select2-chosen").get(1).innerHTML = '-- 请选择 --';
                        }
                    }
                }
            });
        });
    </script>
</body>
</html>