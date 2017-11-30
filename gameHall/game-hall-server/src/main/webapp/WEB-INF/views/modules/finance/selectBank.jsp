<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户充值管理</title>
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
		
		function getContextPath(){   
		    var pathName = document.location.pathname;   
		    var index = pathName.substr(1).indexOf("/");   
		    var result = pathName.substr(0,index+1);   
		    return result;   
		} 
		
		
		
		
		function theNext(){
		
			$.ajax({
			    url:"http://localhost:8110/hall/a/finance/financeRecharge/bankSelect",    //请求的url地址
			    dataType:"json",   //返回格式为json
			    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			    data:{"bankSelect":$("#bankSelect").val()},    //参数值
			    type:"GET",   //请求方式
			    success:function(req){
			        //请求成功时处理
			        alert("成功");
			        debugger;
				$("#receive").val(req[0].bankCardHolder); 
				$("#receiveBank").val(req[0].bankBranchName); 
				$("#receiveNo").val(req[0].bankCardNo); 
				
       
			    }  ,error:function(){
			        //请求出错处理
			    	 alert("失败");
			    }
   
			});		
		}
		 
		


		$(function(){
			//提交表单的时候发送ajax到后台
			
/* 			$("#bankSelect").change(function(){
				theNext();
				}); */
			//alert($("#bankSelect").val());
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finance/financeRecharge/">账户充值列表</a></li>
		<li class="active"><a href="${ctx}/finance/financeRecharge/rechargeForm">账户充值<shiro:hasPermission name="trade:financeRecharge:edit">${not empty financeRecharge.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="trade:financeRecharge:edit"></shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="financeRecharge" action="${ctx}/finance/financeRecharge/confirmForm" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		


		<div class="control-group">
		<label class="control-label">银行卡充值选择：</label>
		<div class="controls">
		<select id="bankSelect" name="bankSelect">
			<option value="1">中国工商银行</option>
			<option value="2">中国农业银行</option>
			<option value="3">中国银行</option>
			<option value="4">中国建设银行</option>
		</select>
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">充值金额：</label>
			<div class="controls">
				<form:input path="rechargeAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		

		
<!-- 		<div class="control-group">
			<label class="control-label">收款人：</label>
			<div class="controls">
			                                                                                                                                                                                                                                                                            
				<input id="receive" htmlEscape="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收款人银行：</label>
			<div class="controls">
				<input id="receiveBank" htmlEscape="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收款人账号：</label>
			<div class="controls">
				<input id="receiveNo" htmlEscape="false"/>
			</div>
		</div> -->
		
	
		
		<div class="form-actions">
			<%-- <shiro:hasPermission name="trade:financeRecharge:edit"> --%>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" />
		<%-- 	&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	

		
		
</body>
</html>