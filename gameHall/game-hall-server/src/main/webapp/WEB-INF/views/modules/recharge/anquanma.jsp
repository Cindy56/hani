<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>安全码验证</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">

	function getContextPath(){   
    var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);   
    return result;   
} 
</script>
</head>

<body>
		<h1>安全码验证</h1>
		
		<form action="/game-hall-server/recharge/financeRecharge/reChargeForm">
			<table>
				<tr>
					<td>请输入用户id</td>
				</tr>	
				<tr>
					<td><input type="text" name="userId"/></td>
				</tr>
				
				<tr>
					<td>请输入安全码</td>
				</tr>	
				<tr>
					<td><input type="text" name="secPassWord"/></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
</body>
</html>