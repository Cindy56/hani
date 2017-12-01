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
                    // 提交前给隐藏的节点赋值
                    $("input.lotteryCode").val($("#code").val());
                    $("input.playCode").val($("#playCode").val());
                    $("#name").val($("span.select2-chosen").get(1).innerHTML);

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
            var caseCount = $("#lotteryPlayMultList").children().length;
            // 最后2行不允许删除，多奖金，至少2中概率
            if (caseCount == 2) {
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
        <li><a href="${ctx}/lottery/lotteryPlayConfig/">玩法基本信息列表</a></li>
        <li class="active"><a href="${ctx}/lottery/lotteryPlayConfig/form?id=${lotteryPlayConfig.id}">玩法基本信息<shiro:hasPermission name="lottery:lotteryPlayConfig:edit">${not empty lotteryPlayConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lottery:lotteryPlayConfig:edit">查看</shiro:lacksPermission></a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="lotteryPlayConfig" action="${ctx}/lottery/lotteryPlayConfig/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>

        <%-- 彩种代码 --%>
        <div class="control-group">
            <label class="control-label">所属彩种：</label>
            <div class="controls">
                <form:select id="lotteryCode" path="lotteryCode.code" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${lotteryTypeList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 玩法编号 --%>
        <div class="control-group">
            <label class="control-label">玩法名称：</label>
            <div class="controls">
                <form:select path="playCode" class="input-xlarge required">
                    <form:option value="" label="-- 请选择 --"/>
                    <form:options items="${fns:getDictList('lottery_play_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 玩法名称 --%>
        <div hidden="true">
            <form:input id="name" path="name"/>
        </div>

        <%-- 玩法模式 --%>
        <div class="control-group">
            <label class="control-label">玩法模式：</label>
            <div class="controls">
                <form:select path="playType" class="input-xlarge required" onchange="hideOrShow();">
                    <form:options items="${fns:getDictList('play_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 中奖概率 --%>
        <div id="winningProbabilityDiv" class="control-group">
            <label class="control-label">中奖概率：</label>
            <div class="controls">
                <form:input path="winningProbability" type="number" max="1" step="0.00001" min="0" htmlEscape="false" maxlength="10" class="input-xlarge required" placeholder="请输入玩法中奖概率..."/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>

        <%-- 多奖金、多概率 --%>
        <div id="lotteryPlayMultDiv" hidden="true" class="control-group">
            <label class="control-label">多奖金设置：</label>
            <div class="controls">
                <table id="contentTable" style="max-width: 50%;" class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th class="hide"></th>
                            <th>
                            	奖金键值
                            	<span class="help-inline"><font color="red">*</font></span>
                            </th>
                            <th>
                            	中奖概率
                            	<span class="help-inline"><font color="red">*</font></span>
                            </th>
                            <th>
                            	奖金说明
                            	<span class="help-inline"><font color="red">*</font></span>
                            </th>
                            <th>
                            	投注示例
                            	<span class="help-inline"><font color="red">*</font></span>
                            </th>
                            <shiro:hasPermission name="lottery:lotteryPlayConfig:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
                        </tr>
                    </thead>
                    <tbody id="lotteryPlayMultList">
                    </tbody>
                    <shiro:hasPermission name="lottery:lotteryPlayConfig:edit"><tfoot>
                        <tr><td colspan="7"><a onclick="addRow('#lotteryPlayMultList', lotteryPlayMultRowIdx, lotteryPlayMultTpl);lotteryPlayMultRowIdx++;" class="btn">新增概率</a></td></tr>
                    </tfoot></shiro:hasPermission>
                </table>
				<%-- 开奖方案输入框生成模板 --%>
                <script type="text/template" id="lotteryPlayMultTpl">//<!--
                    <tr id="lotteryPlayMultList{{idx}}">
                        <td class="hide">
                            <input id="lotteryPlayMultList{{idx}}_id" name="lotteryPlayMultList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                            <input id="lotteryPlayMultList{{idx}}_delFlag" name="lotteryPlayMultList[{{idx}}].delFlag" type="hidden" value="0"/>
							<input name="lotteryPlayMultList[{{idx}}].lotteryCode" class="lotteryCode" type="hidden" />
							<input name="lotteryPlayMultList[{{idx}}].playCode" class="playCode" type="hidden" />
                        </td>
                        <td>
                            <input id="lotteryPlayMultList{{idx}}_number" name="lotteryPlayMultList[{{idx}}].number" type="text" value="{{row.number}}" maxlength="50" class="input-large required"/>
                        </td>
                        <td>
                            <input id="lotteryPlayMultList{{idx}}_winningProbability" name="lotteryPlayMultList[{{idx}}].winningProbability" type="number" max="1" step="0.00001" min="0" maxlength="10" value="{{row.winningProbability}}" class="input-small required"/>
                        </td>
                        <td>
                            <input id="lotteryPlayMultList{{idx}}_explain" name="lotteryPlayMultList[{{idx}}].explain" type="text" min="0" value="{{row.explain}}" maxlength="4000" class="input-large required"/>
                        </td>
                        <td>
                            <input id="lotteryPlayMultList{{idx}}_example" name="lotteryPlayMultList[{{idx}}].example" type="text" min="0" value="{{row.example}}" maxlength="4000" class="input-large required"/>
                        </td>
                        <shiro:hasPermission name="lottery:lotteryPlayConfig:edit"><td class="text-center" width="10">
                            {{#delBtn}}<span class="close" onclick="delRow(this, '#lotteryPlayMultList{{idx}}')" title="删除">&times;</span>{{/delBtn}}</td>
						</shiro:hasPermission>
                    </tr>//-->
                </script>
            </div>
        </div>

        <%-- 最高抽水 --%>
        <div class="control-group">
            <label class="control-label">最高抽水：</label>
            <div class="controls">
                <form:input path="commissionRateMax" type="number" max="1" step="0.005" min="0" htmlEscape="false" maxlength="6" class="input-xlarge" placeholder="请输入最高抽水..."/>
            </div>
        </div>

        <%-- 最低抽水 --%>
        <div class="control-group">
            <label class="control-label">最低抽水：</label>
            <div class="controls">
                <form:input path="commissionRateMin" type="number" max="1" step="0.005" min="0" htmlEscape="false" maxlength="6" class="input-xlarge" placeholder="请输入最低抽水..."/>
            </div>
        </div>

        <%-- 单注金额 --%>
        <div class="control-group">
            <label class="control-label">单注金额：</label>
            <div class="controls">
                <form:input path="betUnit" type="number" min="0" htmlEscape="false" maxlength="6" class="input-xlarge" placeholder="请输入单注金额..."/>
            </div>
        </div>

        <%-- 担任单注投注倍数限制 --%>
        <div class="control-group">
            <label class="control-label">单人单期投注倍数限制：</label>
            <div class="controls">
                <form:input path="betRateLimit" type="number" min="0" htmlEscape="false" maxlength="6" class="input-xlarge" placeholder="请输入单人单期投注倍数限制..."/>
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
                <form:textarea path="explain" htmlEscape="false" rows="4" maxlength="5000" class="input-xxlarge" placeholder="请输入玩法说明..."/>
            </div>
        </div>

        <%-- 玩法示例 --%>
        <div class="control-group">
            <label class="control-label">玩法示例：</label>
            <div class="controls">
                <form:textarea path="example" htmlEscape="false" rows="4" maxlength="5000" class="input-xxlarge" placeholder="请输入玩法示例..."/>
            </div>
        </div>

        <%-- 保存提交或退出 --%>
        <div class="form-actions">
            <shiro:hasPermission name="lottery:lotteryPlayConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
    <script type="text/javascript">
	    /*
	     * 用于进入页面时生成多奖金数据展示，如果是新增，默认两条多奖金新增输入框
	     */
	    var lotteryPlayMultRowIdx = 0, lotteryPlayMultTpl = $("#lotteryPlayMultTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	    $(document).ready(function() {
	        var data = ${fns:toJson(lotteryPlayConfig.lotteryPlayMultList)};
	        for (var i=0; i<data.length; i++){
	            debugger;
	            addRow('#lotteryPlayMultList', lotteryPlayMultRowIdx, lotteryPlayMultTpl, data[i]);
	            lotteryPlayMultRowIdx++;
	        }
	        // 如果进入页面没有数据（新增），默认显示两条新增输入框
	        if (0 == data.length) {
	            addRow('#lotteryPlayMultList', lotteryPlayMultRowIdx++, lotteryPlayMultTpl, "");
	            addRow('#lotteryPlayMultList', lotteryPlayMultRowIdx++, lotteryPlayMultTpl, "");
	        }
	        
	        // 根据选择的玩法模式，决定中奖概率的录入方式
	        hideOrShow();
	    });
	    
	    /*
	     * 玩法概率录入方式切换
	     */
	    function hideOrShow() {
	        if ("1" == $("#playType").val()) {
	            $("#winningProbabilityDiv").hide();
	            $("#lotteryPlayMultDiv").show();
	        } else {
	            $("#winningProbabilityDiv").show();
	            $("#lotteryPlayMultDiv").hide();
	        }
	    }
	    
	    /*
	     * 彩种类型于彩种代码级联事件
	     */
        $("#lotteryCode").change(function() {
            $.ajax({
                type: "post",
                async: false,
                url: ctx+"/lottery/lotteryPlayConfig/findPlayCode",
                dataType: "json",
                data: "lotterytype="+this.value,
                success: function(data) {
                    // 如果相应数据不为空则继续解析
                    if (data) {
                        var playCode = data.lotteryPlayCode;
                        var isCon = false;
                        var tmpStr = "<option>-- 请选择 --</option>";
                        var orgiValue = $("span.select2-chosen").get(1).innerHTML; 
                        if (playCode && playCode.length != 0) {
                            for (var i = 0;i < playCode.length; i++) {
                                var tmpValue = playCode[i].label;
                                // 如果变更的玩法里还有原来的玩法，依然选中
                                if (tmpValue == orgiValue) {
                                    isCon = true;
                                    orgiValue = playCode[i].value;
                                }
                                tmpStr += "<option value='" + playCode[i].value + "'>" + tmpValue + "</option>";
                            }
                            // 新数据直接覆盖
                        } else {
                            $("span.select2-chosen").get(1).innerHTML = '-- 请选择 --';
                        }
                        $("#playCode").html(tmpStr);
                        if (isCon) {
                            $("#playCode").val(orgiValue);
                        }
                    }
                }
            });
        });
    </script>
</body>
</html>