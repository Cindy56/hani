<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账变流水管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#resetBtn').click(function(){
				$("#searchForm")[0].reset();
				  $(':input','#searchForm').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked').removeAttr('selected') 
				
			})
			
			var totalSum = 0  ; //汇总金额
			
			var disbursementAmout = 0  ;//支出金额
			
			var revenueAmout = 0  ;//收入金额
			
		    $('#contentTable tr').each(function(index,element) { 
		    	$(this).find('td').each(function(){  
		        	var type = $(this).data("type");
		        	if(type != undefined){
		        		var arr = type.split("-");
		        		var amout=parseFloat(arr[1]); 
		        		if(arr[0] == 0){//投注扣款
		        			disbursementAmout += amout;  
			        	}else if(arr[0] == 1){//追号扣款
			        		disbursementAmout += amout;   
			        	} if(arr[0] == 2){//合买扣款
			        		disbursementAmout += amout;  
			        	}else if(arr[0] == 3){//投注撤单
			        		disbursementAmout += amout;  
			        	}else if(arr[0] == 4){//奖金派送
			        		
			        	}else if(arr[0] == 5){//投注返点
			        		
			        	}else if(arr[0] == 6){//活动礼金
			        		
			        	}else if(arr[0] == 7){//追号停止
			        		
			        	}
		        	}
		        	//totalSum += parseFloat($(this).text());   
		        });  
		    });  
			$("#disbursementAmout").append(disbursementAmout.toFixed(4)); 
			
			$("#revenueAmout").append(revenueAmout.toFixed(4)); 
			
			$("#totalSum").append(totalSum.toFixed(4)); 
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
		<li class="active"><a href="${ctx}/trade/financeTradeDetail/">账变流水列表</a></li>
		<shiro:hasPermission name="trade:financeTradeDetail:edit"></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeTradeDetail" action="${ctx}/trade/financeTradeDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>业务编号：</label>
				<form:input path="busiNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>交易类型：</label>
				<form:select path="tradeType" id="tradeType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('trade_Type')}" itemLabel="label" itemValue="value" htmlEscape="true"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTradeDetail.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTradeDetail.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<!-- <li class="btns"><button type="button" id="resetBtn" class="btn btn-success">重置</button></li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>用户名称</th>
				<th>业务编号</th>
				<th>账变交易类型</th>
				<th>账变金额</th>
				<th>账变前金额</th>
				<th>账变后金额</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeTradeDetail">
			<tr>
				<td>
					${financeTradeDetail.userName}
				</td>
				<td>
					${financeTradeDetail.busiNo}
				</td>
				<td data-type=${financeTradeDetail.tradeType}-${financeTradeDetail.amount}>
					${fns:getDictLabel(financeTradeDetail.tradeType, 'trade_Type', '')}
				</td>
				
				<td >
					<c:choose>
						<c:when test="${financeTradeDetail.tradeType==1 || financeTradeDetail.tradeType==2}">
							<font color="red" >${financeTradeDetail.amount}</font>
						</c:when>
						<c:otherwise>
							${financeTradeDetail.amount}
						</c:otherwise>
					</c:choose>
				</td>
				
				<td>
					${financeTradeDetail.accountBlanceBefore}
				</td>
				<td>
					${financeTradeDetail.accountBlanceAfter}
				</td>
				<td>
					<fmt:formatDate value="${financeTradeDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 
				<td>
					${fns:getDictLabel(financeTradeDetail.delFlag, 'del_flag', '')}
				</td>
				
				<shiro:hasPermission name="financetrade:financeTradeDetail:edit"><td>
    				<a href="${ctx}/financetrade/financeTradeDetail/form?id=${financeTradeDetail.id}">修改</a>
					<a href="${ctx}/financetrade/financeTradeDetail/delete?id=${financeTradeDetail.id}" onclick="return confirmx('确认要删除该账变流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission> -->
			</tr>
		</c:forEach>
		<!-- 
			<c:if test="${page.list!= null || fn:length(page.list) != 0}">
				<td colspan="4"  style="text-align:right;">
					汇总金额
				</td>
				<td id = "totalSum1">
					 <c:forEach items="${page.list}" var="financeTradeDetail">
						<c:set var="amount" value="${financeTradeDetail.amount}"></c:set>
						<c:set var="sum" value="${sum+amount}"></c:set>
					</c:forEach>
					
				</td>
				<td colspan="4">
				
				</td>
			</c:if>
			-->
		</tbody>
	</table>
	
	<div class="pagination" style="float:left">${page}</div>
	<div style="padding-top:12px;padding-right:50px" align="right">
		<span><b>当前页支出金额：</b> <font color="red" id = "disbursementAmout"></font></span>
		<span><b>当前页收入金额：</b> <font color="red" id = "revenueAmout"></font></span>
		<span><b>当前页收益金额：</b> <font  id = "totalSum"></font></span>		 
	</div>
</body>
</html>