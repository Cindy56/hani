<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理管理</title>
	<meta name="decorator" content="default"/>
	
	
	<script type="text/javascript">  
	/* $(document).ready(function () {
		 $('.nav li').removeClass("active");
         $(this).addClass("active");

		}); */

	
	
//===========================点击展开关闭效果====================================  
function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){  
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;  
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;  
var openTip = oOpenTip || "";  
var shutTip = oShutTip || "";  
if(targetObj.style.display!="none"){  
   if(shutAble) return;  
   targetObj.style.display="none";  
   if(openTip  &&  shutTip){  
    sourceObj.innerHTML = shutTip;   
   }  
} else {  
   targetObj.style.display="block";  
   if(openTip  &&  shutTip){  
    sourceObj.innerHTML = openTip;   
   }  
}   
}  
</script>  


	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/member/memberAccount/form">会员基础信息</a></li>
		<li class="active"><a href="${ctx}/member/memberAccount/rebate">返点信息</a></li>
	</ul><br/> 

	<sys:message content="${message}"/>
	
	
	
	
	
	<c:forEach items="${page.list}" var="memberAccount" varStatus="status">
	设置"${memberAccount.user.id}"返点 <p id="aaa"><a href="###" onclick="openShutManager(this,'${status.index}')">点击展开</a></p>  
	
	<table id="${status.index}" class="table table-striped table-bordered table-condensed" style="border: 0px;display: none;" >
		<thead >
			<tr>
				<th style="border: 0px;width: 380px">彩种玩法</th>
				<th style="border: 0px;width: 380px">选择返点</th>		
				<th style="border: 0px;width: 380px">彩种玩法</th>
				<th style="border: 0px;width: 380px">选择返点</th>		
			</tr>
		</thead>
		<tbody>
		<c:forEach  items="${page.list}" var="obj" varStatus="i">
			<tr>
				<td style="border: 0px">
					"${memberAccount.user.loginName}"
					<%-- ${memberAccount.user.loginName} --%>
				</td>
				<td style="border: 0px">
					<select>
					     <option value="0" selected>请选择：</option>
					     <c:forEach var="abc" items="${page.list}" varStatus="i">
					     
        			     <option value="${i.count}">${abc.user.loginName}</option>
  				     </c:forEach>
					</select>				
					
				</td>
				
				<td style="border: 0px">
				 	"${memberAccount.user.loginName}"
				</td>
				<td style="border: 0px">
					<select>
					     <option value="0" selected>请选择：</option>
					     <c:forEach var="abc" items="${page.list}" varStatus="i">
        			     <option value="${i.count}">${abc.user.loginName}</option>
  				     </c:forEach>
					</select>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	</c:forEach>
	
	<div class="pagination">${page}</div>
</body>
</html>