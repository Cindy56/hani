<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<script type="text/javascript">
$(function(){
/*  	if("${memberAccount.id}" == ""){
		//注册
		
		
	}else{
		alert("修改");
	} 
	 */
})



</script>

<style type="text/css">
		#one {
     width: 125px;

    float:left;     
}

#two {
    width: 125px;

  
    
}
	
	</style>
	<title>会员管理管理</title>
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
	</script>
</head>
<body>
 	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/memberAccount/form?id=${memberAccount.id}">会员基础信息<shiro:hasPermission name="member:memberAccount:edit">${not empty memberAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="member:memberAccount:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/member/memberAccount/rebate">返点信息</a></li>
	</ul><br/> 
	<form:form id="inputForm" modelAttribute="memberAccount" action="${ctx}/member/memberAccount/save?${memberAccount.user.id}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
<!-- 用户信息 -->	
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${memberAccount.user.loginName}">
				<form:input path="user.loginName" htmlEscape="false" maxlength="50" class="required userName" placeholder="请输入登录名" id="loginName"/>
				<span class="help-inline"><font color="red">*</font> </span> 
			</div>
		</div>
		
		
		<c:if test="${empty memberAccount.id}">
		
				<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPassword" name="user.newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}" placeholder="请输入密码"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="user.newPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" placeholder="请输入确认密码"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>	
				<div class="control-group">
			<label class="control-label">安全密码：</label>
			<div class="controls">
				<form:input path="secPassword" htmlEscape="false" maxlength="50" class="input-xlarge required" placeholder="请输入安全密码"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		</c:if>
	
			

			
	
		<!-- 会员信息 -->			
		<div class="control-group">
			<label class="control-label">会员类型：</label>
			<div class="controls">
				<form:radiobuttons path="accountType" items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		
		
		
		
		
		
		
		
		
		
		
		
		
<%-- 		
		
		
		<div class="control-group">
			<div style="display:inline">
			<label class="control-label">开户行：</label>
				<div class="controls" style="width: 20%;display:inline;margin-left: 20px;">
					<form:select path="bankCode" class="input-xlarge required" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div  style="display:inline">
			&nbsp;&nbsp;&nbsp;&nbsp;
				<label>开户行省份：</label>
				<form:select path="bankBranchProvince" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			
		
		<div class="control-group">
			<div style="display:inline">
				<label class="control-label">开户行城市：</label>
				<div class="controls" style="width: 20%;display:inline;margin-left: 20px;">
				<form:select path="bankBranchCity" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
 			<div  style="display:inline">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>开户行全称：</label>
			
				<form:input path="bankBranchName" htmlEscape="false" maxlength="500" class="input-xlarge " placeholder="请输入开户行全称"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
			
		
		<div >
			<form:input path="orgId" htmlEscape="false" maxlength="500" class="input-xlarge " value="123" />
			<form:input path="parentAgentId" htmlEscape="false" maxlength="500" class="input-xlarge " value="123" />
			<form:input path="user" htmlEscape="false" maxlength="500" class="input-xlarge " value="123" />
		</div> 
		
	
		
		<div class="control-group">
			<label class="control-label">银行卡账号：</label>
			<div class="controls">
				<form:input path="bankCardNo" htmlEscape="false" maxlength="50" class="input-xlarge required" placeholder="请输入银行卡账号"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			<label class="control-label">开卡人名称：</label>
			<div class="controls">
				<form:input path="bankCardHolder" htmlEscape="false" maxlength="500" class="input-xlarge required" placeholder="请输入开卡人名称"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>			
		</div> --%>
		<div class="control-group">
			<label class="control-label">qq号码：</label>
			<div class="controls">
				<form:input path="qqNo" htmlEscape="false" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入QQ号码"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="mobileNo" htmlEscape="false" maxlength="50"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入手机号码"/>
			</div>
		</div>
		<div class="control-group" id="typeRadiobuttons">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="member:memberAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>