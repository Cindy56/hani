<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	
	function getContextPath(){   
	    var pathName = document.location.pathname;   
	    var index = pathName.substr(1).indexOf("/");   
	    var result = pathName.substr(0,index+1);   
	    return result;   
	}  
	
		$(function(){ 
/* 			$.ajax({
			    url:getContextPath()+"/member/personalData",    //请求的url地址
			    data:{"id":"bb715f789b1143c295b2214acbb0432d"}, 
			    dataType:"json",   //返回格式为json
			    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			    type:"GET",   //请求方式
			    success:function(res){
			      	alert(res.data[0].qq_no)
			    }, error:function(){
			    
			    }
			});	  */
			
/* 			$.ajax({
			    url:getContextPath()+"/member/modifyMemberAccount",    //请求的url地址
			    data:{"userId":"570003e4194f4d5a92c952b5cc8f1384"}, 
			    dataType:"json",   //返回格式为json
			    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			    type:"GET",   //请求方式
			    success:function(res){
			     
			    }, error:function(){
			  
			    }
			});	  */
			});
	</script>
	
	
</head>


<body>
<h1>充值页面</h1>
			 	<form action="/game-hall-server/member/modifyMemberAccount">
					<table>
					<tr>
						<td>
						${map.userId}
							<input name="userId" >
						</td>
					</tr>	
					<tr>
						<td>
						选择充值方式
							<select>
								<option>请选择</option>
								<option>银行</option>
								<option>支付宝</option>
								<option>微信</option>
							</select>
						</td>
					</tr>
					<tr>	
						<td>
						充值金额
							<input name="amount">
						</td>
					</tr>
					</table>		
					<input type="submit">	
				</form>
</body>
</html>