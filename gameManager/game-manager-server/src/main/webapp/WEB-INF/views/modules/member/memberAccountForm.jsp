<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var arr = new Array();
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
		
		
		$(function(){
			$.ajax({
			    url:"${ctx}/member/memberAccount/select",    //请求的url地址
			    dataType:"json",   //返回格式为json
			    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			    type:"GET",   //请求方式
		
			    success:function(data){
			   	for(var i = 0 ; i<data.list.length ; i++){
			   			var str  = data.list[i].id = data.list[i].id.substring(1,data.list[i].id.length - 1);
			   			//arr[i] = JSON.stringify(str);
			    		//alert(str);
			    		$("#selectId").append("<option value='"+str+"'>"+str+"</option>");
			    	} 
			    	
			
			        //请求成功时处理
			        // alert(JSON.stringify(data.list[0].id));
			    },
			    error:function(){
			        //请求出错处理
			    }
			});
			
			
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/memberAccount/">会员管理列表</a></li>
		<li class="active"><a href="${ctx}/member/memberAccount/form?id=${memberAccount.id}">会员管理<shiro:hasPermission name="member:memberAccount:edit">${not empty memberAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="member:memberAccount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberAccount" action="${ctx}/member/memberAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上级代理账号id：</label>
			<div class="controls">
				<form:input path="parentAgentId" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户表主键id：</label>
			<div class="controls">
			
		     	<form:select path="user.id" class="input-xlarge required" id="selectId">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>  
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构id，盘口id：</label>
			<div class="controls">
				<form:input path="orgId" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会员类型：</label>
			<div class="controls">
				<form:select path="accountType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('member_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安全密码：</label>
			<div class="controls">
				<form:input path="secPassword" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行代码：</label>
			<div class="controls">
				<form:input path="bankCode" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡账号：</label>
			<div class="controls">
				<form:input path="bankCardNo" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡开卡人名称：</label>
			<div class="controls">
				<form:input path="bankCardHolder" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行省份：</label>
			<div class="controls">
				<form:input path="bankBranchProvince" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行城市：</label>
			<div class="controls">
				<form:input path="bankBranchCity" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行全称：</label>
			<div class="controls">
				<form:input path="bankBranchName" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qq号码，用户：</label>
			<div class="controls">
				<form:input path="qqNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="mobileNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">余额：</label>
			<div class="controls">
				<form:input path="blance" htmlEscape="false" class="input-xlarge required"/>
				元
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冻结余额：</label>
			<div class="controls">
				<form:input path="blanceFrozen" htmlEscape="false" class="input-xlarge "/>
			元
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冻结,正常：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
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