<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理管理</title>
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
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
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
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
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
		
		function contractShow(event){
			if($("#contract").is(":hidden")){
				$(event).addClass("active");
				$(event).siblings().removeClass("active");
				$("#contract").show();
				$("#fd").hide();
			}
		}
		function fdShow(event){
			if($("#fd").is(":hidden")){
				$(event).addClass("active");
				$(event).siblings().removeClass("active");
				$("#contract").hide();
				$("#fd").show();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/contract/contractAgent/">代理列表</a></li>
		<!-- <li class="active"><a href="${ctx}/contract/contractAgent/form?id=${contract.id}">代理<shiro:hasPermission name="contract:agent:contract:edit">${not empty contract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="contract:agent:contract:edit">查看</shiro:lacksPermission></a></li> -->
		<li class="active" onclick="contractShow(this)"><a href="javascript:void(0);">代理<shiro:hasPermission name="contract:agent:contract:edit">${not empty contract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="contract:agent:contract:edit">查看</shiro:lacksPermission></a></li>
		<li class="" onclick="fdShow(this)"><a href="javascript:void(0);">返点信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="contract" action="${ctx}/contract/contractAgent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="contract">	
			<table style="width: 80%;">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">代理名称：</label>
						<div class="controls">
							<form:input path="orgName" htmlEscape="false" maxlength="20" class="input-xlarge required" placeholder="请输入代理名称"  value=""/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">登录名称：</label>
						<div class="controls">
							<form:input path="userName" htmlEscape="false" maxlength="8" class="input-xlarge required" placeholder="请输入登录名称" value=""/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<c:if test="${empty contract.id}">
			<tr><td>
				<div class="control-group">
						<label class="control-label">登录密码：</label>
						<div class="controls">
							<form:input id="newPassword" path="user.password" type="password" value="" maxlength="20" minlength="6" class="input-xlarge ${empty user.id?'required':''}" placeholder="请输入密码"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
				</div>
				</td>
				<td>
				
					<div class="control-group">
						<label class="control-label">确认密码：</label>
						<div class="controls">
							<input id="confirmNewPassword" type="password" value="" maxlength="20" minlength="6" equalTo="#newPassword" class="input-xlarge required" placeholder="请输入确认密码"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label">安全密码：</label>
						<div class="controls">
								<form:input path="secPassword" htmlEscape="false" maxlength="20" minlength="6" class="input-xlarge required" placeholder="请输入安全密码"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			</c:if>
		
		<c:if test="${not empty contract.id}">
			<form:input id="newPassword" path="user.password" type="hide" class="hide"/>
			<form:input path="secPassword" type="hide" class="hide"/>
			<form:input path="status" type="hide" class="hide"/>
		</c:if>
		<c:if test="${empty contract.id}">
			<tr><td>
			<div class="control-group">
					<label class="control-label">邮箱：</label>
					<div class="controls">
						<form:input path="user.email" htmlEscape="false" maxlength="100" placeholder="请输入邮箱" class="input-xlarge email" value=""/>
					</div>
				</div></td><td>
				<div class="control-group">
					<label class="control-label">电话：</label>
					<div class="controls">
						<form:input path="user.phone" htmlEscape="false" maxlength="100" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入电话" value=""/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td>
				<div class="control-group">
					<label class="control-label">qq号码：</label>
					<div class="controls">
						<form:input path="qqNo" htmlEscape="false" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入QQ号码" value=""/>
					</div>
				</div>
				</td>
				<td>
				<div class="control-group">
					<label class="control-label">手机号码：</label>
					<div class="controls">
						<form:input path="mobileNo" htmlEscape="false" maxlength="50"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入手机号码" value=""/>
					</div>
				</div>
				</td>
			</tr>
		</c:if>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label">分红周期：</label>
					<div class="controls">
						<form:select path="benefitCycle" class="input-xlarge required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('contract_period')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label">保底服务费（元/月）：</label>
					<div class="controls">
						<form:input path="rentAmount" htmlEscape="false" class="input-xlarge required" placeholder="请输入平台租金（单位：元/月）" onkeyup="value=value.replace(/[^\d]/g,'') " value="" min="0"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<div class="control-group">
				<label class="control-label">开户费（元）：</label>
				<div class="controls">
					<form:input path="openAmount" htmlEscape="false" class="input-xlarge required" placeholder="请输入开户费（单位：元）" onkeyup="value=value.replace(/[^\d]/g,'') " value="" min="0"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			</td>
			<td>
			<div class="control-group">
				<label class="control-label">签约周期（年）：</label>
				<div class="controls">
					<form:input path="contractTime" htmlEscape="false" maxlength="4" class="input-xlarge required" placeholder="请输入签约周期（单位：年）" onkeyup="value=value.replace(/[^\d]/g,'') " value="" min="0"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="control-group">
					<label class="control-label">分红模式：</label>
					<div class="controls">
						<form:select path="benefitType" class="input-xlarge required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('contract_model')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</td>
		</tr>
		</table>
			<div class="control-group">
				<label class="control-label">分红设置：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 45rem;"><!-- style="width: 45rem;" -->
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="width: 6rem;"></th>
								<th>金额（单位：万）</th>
								<th>金额（单位：万）</th>
								<th>分红比例（单位：%）</th>
								<shiro:hasPermission name="contract:company:contract:edit"><!-- <th width="10">&nbsp;</th> --></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="contractConfigList" >
						</tbody>
						<shiro:hasPermission name="contract:company:contract:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#contractConfigList', contractConfigRowIdx, contractConfigTpl);contractConfigRowIdx = contractConfigRowIdx + 1;" class="btn" id="xz">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table><!-- contractConfigTpl --> 
					<script type="text/template" id="contractConfigTpl">//<!--
						<tr id="contractConfigList{{idx}}">
							<td class="hide">
								<input id="contractConfigList{{idx}}_id" name="contractConfigList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="contractConfigList{{idx}}_delFlag" name="contractConfigList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="contractConfigList{{idx}}_contractId_id" name="contractConfigList[{{idx}}].contractId.id" type="hidden" value="{{row.contractId.id}}"/>
								<input name="submitFlag" type="hidden" value="true"/>
							</td>
							<td>
								流水/盈利额：
							</td>
							<td>
								<input id="contractConfigList{{idx}}_rangeStart" name="contractConfigList[{{idx}}].rangeStart" type="number" value="{{row.rangeStart}}" class="input-small required" onchange="checkMoneyA(this)" min="0"/>
							</td>
							<td>
								<input id="contractConfigList{{idx}}_rangeEnd" name="contractConfigList[{{idx}}].rangeEnd" type="number" value="{{row.rangeEnd}}" class="input-small required" onchange="checkMoneyB(this)" min="0"/>
							</td>
							<td>
								<input id="contractConfigList{{idx}}_beniftRate" name="contractConfigList[{{idx}}].beniftRate" type="number" value="{{row.beniftRate}}" class="input-small required" min="0" maxlength="4" onkeyup="if(isNaN(value))execCommand('undo');if(this.value>100){this.value=100}"/>
							</td>
							<shiro:hasPermission name="contract:company:contract:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#contractConfigList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var contractConfigRowIdx = 0, contractConfigTpl = $("#contractConfigTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(contract.contractConfigList)};
							for (var i=0; i<data.length; i++){
								addRow('#contractConfigList', contractConfigRowIdx, contractConfigTpl, data[i]);
								contractConfigRowIdx = contractConfigRowIdx + 1;
							}
							if($("#contractConfigList").find("tr").length<=0){
								$("#xz").click();
							}
						});
						function checkMoneyA(e){
							var tr=$(e).parent().next("td").find("input");
							if(tr.val()){
								if(parseInt(tr.val())<=parseInt($(e).val())){
									alert("需小于截至金额："+parseInt(tr.val()));
									$(e).focus();
									$(e).parent().parent().find("input[name=submitFlag]").val("false");
								}else{
									$(e).parent().parent().find("input[name=submitFlag]").val("true");
								}
							}
							var prevTr=$(e).parent().parent().prev("tr");
							var td=prevTr.find("td")[3];
							var st=$(td).find("input").val();
							if(st){
								if(parseInt($(e).val())<parseInt(st)){
									alert("需要大于金额："+st);
									$(e).focus();
									$(e).parent().parent().find("input[name=submitFlag]").val("false");
								}else{
									$(e).parent().parent().find("input[name=submitFlag]").val("true");
								}
							}
						}
						function checkMoneyB(e){
							var tr=$(e).parent().prev("td").find("input");
							if(tr.val()){
								if(parseInt(tr.val())>=parseInt($(e).val())){
									alert("需大于起始金额："+parseInt(tr.val()));
									$(e).focus();
									$(e).parent().parent().find("input[name=submitFlag]").val("false");
								}else{
									$(e).parent().parent().find("input[name=submitFlag]").val("true");
								}
							}
							var prevTr=$(e).parent().parent().next("tr");
							var td=prevTr.find("td")[2];
							var st=$(td).find("input").val();
							if(st){
								if(parseInt($(e).val())>parseInt(st)){
									alert("需要小于金额："+st);
									$(e).focus();
									$(e).parent().parent().find("input[name=submitFlag]").val("false");
								}else{
									$(e).parent().parent().find("input[name=submitFlag]").val("true");
								}
							}
						}
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注：</label>
				<div class="controls">
					<form:textarea path="remark" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
				</div>
			</div>
		</div>
		<!-- 返点信息start -->
		<div id="fd" style="display: none;">
			<!-- <ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0);">返点信息</a>
				</li>
			</ul> -->
			<div style="margin-left: 20px">
				<c:set var="index" value="0" />
				<c:forEach items="${contract.map}" var="repeatMap" >
					<!-- 彩种名称 -->
					<a href="javascript:void(0);" onclick="$(this).next('div').toggle();"><div style="">${repeatMap.key}:</div></a>
						<div style="display: block;margin-left: 20px">
							<c:forEach items="${repeatMap.value}" var="LotteryPlayConfig">
								<span>
									<input type="hidden" name="playList[${index}].lotteryCode.isNewRecord" value="${LotteryPlayConfig.lotteryCode.isNewRecord}"/>
									<input type="hidden" name="playList[${index}].lotteryCode.name" value="${repeatMap.key}"/>
									<input type="hidden" name="playList[${index}].lotteryCode.code" value="${LotteryPlayConfig.lotteryCode.code}"/>
									<input type="hidden" name="playList[${index}].isNewRecord" value="${LotteryPlayConfig.isNewRecord}"/>
									<input type="hidden" name="playList[${index}].playCode" value="${LotteryPlayConfig.playCode}"/>
									<input type="hidden" name="playList[${index}].name" value="${LotteryPlayConfig.name}"/>
									<input type="hidden" name="playList[${index}].playType" value="${LotteryPlayConfig.playType}"/>
									<input type="hidden" name="playList[${index}].winningProbability" value="${LotteryPlayConfig.winningProbability}"/>
									<input type="hidden" name="playList[${index}].betRateLimit" value="${LotteryPlayConfig.betRateLimit}"/>
									<input type="hidden" name="playList[${index}].commissionRateMin" value="${LotteryPlayConfig.commissionRateMin}"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${LotteryPlayConfig.name}:
									<select name="playList[${index}].commissionRateMax">
										<c:forEach items="${LotteryPlayConfig.map.awardList}" var="awardList">
											<option  name="${LotteryPlayConfig.playCode}" value="${awardList.commissionRate}">${awardList.awardMoney}(${awardList.commissionRate*100}%)</option>
										</c:forEach>
									</select> 
									<c:set var="index" value="${index+1}" />  
								</span>
							</c:forEach>
						</div>
					<br>
				</c:forEach>
			</div>
		</div>
		<!-- 返点信息end -->
		<div class="form-actions">
			<shiro:hasPermission name="contract:agent:contract:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>