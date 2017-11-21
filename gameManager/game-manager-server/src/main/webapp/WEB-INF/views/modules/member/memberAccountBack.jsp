<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					//sub();
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function sub(){
			var a=$("#inputForm").serialize();
			console.log(a);
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <shiro:hasPermission name="memberadd:memberAccount:edit"><li><a href="${ctx}/memberadd/memberAccount/form">会员开户添加</a></li></shiro:hasPermission> --%>
		<li class="active"><a href="${ctx}/memberadd/memberAccount/">会员开户添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="memberAccountOpenDto" action="${ctx}/memberadd/memberAccount/save?${memberOpenAccount.user.id}" method="post" class="form-horizontal">
		<%-- <form:hidden path="account.id"/> --%>
		<sys:message content="${message}"/>	
		<!-- 用户信息start -->	
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<!-- <input id="oldLoginName" name="user.loginName" type="hidden" value=""> -->
					<form:input path="user.loginName" htmlEscape="false" maxlength="50" class="required userName" placeholder="请输入登录名" id="loginName"/>
				<span class="help-inline"><font color="red">*</font> </span> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPassword" name="user.password" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}" placeholder="请输入密码"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="user.password2" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" class="required" placeholder="请输入确认密码"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">安全密码：</label>
			<div class="controls">
					<form:input path="account.secPassword" htmlEscape="false" maxlength="50" class="input-xlarge required" placeholder="请输入安全密码"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!-- 用户信息 end-->
		
		<!-- 会员信息start -->			
		<div class="control-group">
			<label class="control-label">会员类型：</label>
			<div class="controls">
					 <form:radiobuttons path="account.accountType" items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
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
				<form:input path="account.qqNo" htmlEscape="false" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入QQ号码"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="account.mobileNo" htmlEscape="false" maxlength="50"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge " placeholder="请输入手机号码"/>
			</div>
		</div>
		<div class="control-group" id="typeRadiobuttons">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="account.status" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!-- 会员信息end -->
		<!-- 返点信息start -->
		<div>
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="javascript:void(0);">返点信息</a>
				</li>
			</ul>
			<div style="margin-left: 20px">
				<c:set var="index" value="0" />
				<c:forEach items="${repeatMap}" var="repeatMap" >
					<!-- 彩种名称 -->
					<a href="javascript:void(0);" onclick="$(this).next('div').toggle();"><div style="">${repeatMap.key}:</div></a>
						<div style="display: block;margin-left: 20px">
							<c:forEach items="${repeatMap.value}" var="LotteryPlayConfig">
									<input type="hidden" name="playList[${index}].lotteryCode.isNewRecord" value="${LotteryPlayConfig.lotteryCode.isNewRecord}"/>
									<input type="hidden" name="playList[${index}].lotteryCode.name" value="${repeatMap.key}"/>
									<input type="hidden" name="playList[${index}].lotteryCode.code" value="${LotteryPlayConfig.lotteryCode.code}"/>
									<input type="hidden" name="playList[${index}].isNewRecord" value="${LotteryPlayConfig.isNewRecord}"/>
									<input type="hidden" name="playList[${index}].playCode" value="${LotteryPlayConfig.playCode}"/>
									<input type="hidden" name="playList[${index}].name" value="${LotteryPlayConfig.name}"/>
									<input type="hidden" name="playList[${index}].playType" value="${LotteryPlayConfig.playType}"/>
									<input type="hidden" name="playList[${index}].winningProbability" value="${LotteryPlayConfig.winningProbability}"/>
									<input type="hidden" name="playList[${index}].betRateLimit" value="${LotteryPlayConfig.betRateLimit}"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${LotteryPlayConfig.name}:
									<select name="playList[${index}].commissionRateMax">
										<c:forEach items="${LotteryPlayConfig.map.awardList}" var="awardList">
											<option  name="${LotteryPlayConfig.playCode}" value="${awardList.commissionRate}">${awardList.awardMoney}(${awardList.commissionRate}%)</option>
										</c:forEach>
									</select> 
									<c:set var="index" value="${index+1}" />  
							</c:forEach>
						</div>
					<br>
				</c:forEach>
			</div>
		</div>
		<!-- 返点信息end -->
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="member:memberAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<!-- ------------------------------------------------------------- -->
	<!-- <div class="pagination">${page}</div> -->
</body>
</html>