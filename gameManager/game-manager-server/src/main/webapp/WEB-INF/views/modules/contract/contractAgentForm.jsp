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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/contract/contractAgent/">代理列表</a></li>
		<li class="active"><a href="${ctx}/contract/contractAgent/form?id=${contract.id}">代理<shiro:hasPermission name="contract:agent:contract:edit">${not empty contract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="contract:agent:contract:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="contract" action="${ctx}/contract/contractAgent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">机构id：</label>
			<div class="controls">
				<form:input path="office.id" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称：</label>
			<div class="controls">
				<form:input path="orgName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户id：</label>
			<div class="controls">
				<form:input path="user.id" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户登录名称：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户id：</label>
			<div class="controls">
				<form:input path="accountId" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:input path="openType" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
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
		<div class="control-group">
			<label class="control-label">平台租金：</label>
			<div class="controls">
				<form:input path="rentAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户费：</label>
			<div class="controls">
				<form:input path="openAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签约周期：</label>
			<div class="controls">
				<form:input path="contractTime" htmlEscape="false" maxlength="4" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">分红设置：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>以元为单位，界面上格式化万</th>
								<th>以元为单位，界面上格式化万</th>
								<th>分红比例，小数存储，如0.5</th>
								<shiro:hasPermission name="contract:agent:contract:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="contractConfigList">
						</tbody>
						<shiro:hasPermission name="contract:agent:contract:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#contractConfigList', contractConfigRowIdx, contractConfigTpl);contractConfigRowIdx = contractConfigRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="contractConfigTpl">//<!--
						<tr id="contractConfigList{{idx}}">
							<td class="hide">
								<input id="contractConfigList{{idx}}_id" name="contractConfigList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="contractConfigList{{idx}}_delFlag" name="contractConfigList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="contractConfigList{{idx}}_rangeStart" name="contractConfigList[{{idx}}].rangeStart" type="text" value="{{row.rangeStart}}" class="input-small required"/>
							</td>
							<td>
								<input id="contractConfigList{{idx}}_rangeEnd" name="contractConfigList[{{idx}}].rangeEnd" type="text" value="{{row.rangeEnd}}" class="input-small required"/>
							</td>
							<td>
								<input id="contractConfigList{{idx}}_beniftRate" name="contractConfigList[{{idx}}].beniftRate" type="text" value="{{row.beniftRate}}" class="input-small required"/>
							</td>
							<shiro:hasPermission name="contract:agent:contract:edit"><td class="text-center" width="10">
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
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="contract:agent:contract:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>