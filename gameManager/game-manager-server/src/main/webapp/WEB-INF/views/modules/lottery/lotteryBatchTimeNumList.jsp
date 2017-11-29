<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>

<html>
<head>
	<title>开奖管理</title>
	<meta name="decorator" content="default"/>
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
		<li><a href="${ctx}/lottery/lotteryTimeNum/">开奖时刻列表</a></li>
		<shiro:hasPermission name="lottery:lotteryTimeNum:edit"><li><a href="${ctx}/lottery/lotteryTimeNum/form">开奖时刻添加</a></li></shiro:hasPermission>
		<li  class="active"><a href="${ctx}/lottery/lotteryTimeNum/batchView">批量修改时刻</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryTimeNum" action="${ctx}/lottery/lotteryTimeNum/batchView" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>彩票代码：</label>
				<form:select path="lotteryCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${dictLis}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开奖期号：</label>
				<form:input path="lotteryIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开奖号码：</label>
				<form:input path="openNum" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('draw_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit1" class="btn btn-primary" type="button" onclick="dialogShow()" value="批量修改时刻"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:30px">
					<input id="allboxs" onclick="allcheck()" type="checkbox"/> 全选
				</th>
				<th>彩票代码</th>
				<th>开奖期号</th>
				<th>投注开始时间</th>
				<th>投注截止时间</th>
				<th>封单时间(秒)</th>
				<th>开奖号码</th>
				<th>状态</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lotteryTimeNum">
			<tr>
				<td style="width:30px">
					<input  name="boxs" class="lotteryData" type="checkbox" value="${lotteryTimeNum.id}"/>
				</td>
				<td>
					${fns:getDictLabel(lotteryTimeNum.lotteryCode, 'SSC', '')}
				</td>
				<td>
					${lotteryTimeNum.lotteryIssueNo}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.betHaltDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lotteryTimeNum.openNum}
				</td>
				<td>
					${fns:getDictLabel(lotteryTimeNum.status, 'draw_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${lotteryTimeNum.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<div id="right-text" >
		<label>封单时间(s):</label> <input type="text" id="betHaltDate" name="betHaltDate" style="width:40px;height:20px;padding-top:5px" value="1" />
	</div>
	
	

	<script type="text/javascript">
		$("#right-text").hide();
	
		function allcheck() {
	        var nn = $("#allboxs").is(":checked"); //判断th中的checkbox是否被选中，如果被选中则nn为true，反之为false
	            if(nn == true) {
	                var namebox = $("input[name^='boxs']");  //获取name值为boxs的所有input
	                for(i = 0; i < namebox.length; i++) {
	                    namebox[i].checked=true;    //js操作选中checkbox
	                }
	            }
	            if(nn == false) {
	                var namebox = $("input[name^='boxs']");
	                for(i = 0; i < namebox.length; i++) {
	                    namebox[i].checked=false;
	                }
	            }
	        }
		
		//批量修改封单时间
		function batchupdate(){
			var data = new Array(); 
			var namebox = $("input[name^='boxs']");
			for(var i=0;i<namebox.length;i++){
				if(namebox[i].checked)
				{
					data.push(namebox[i].value)
				}
			}
			return data;
		} 
		
		function dialogShow() {
			var idsArr = batchupdate();
        	if(idsArr.length == 0){
        		alert("请勾选要修改的数据！");
        		return;
        	}
			  var submit = function (v, h, f) {
            if (v == true)
               	{
            	   $.ajax({
                       type:"post",
                       async: false,
                       url:ctx+"/lottery/lotteryTimeNum/batchUpdateHalt",
                       data:{
                    	   ids:idsArr,
                    	   betHaltDate:f.betHaltDate
                       },
                       dataType:"json",
                       success:function(data){
                    	   //location.reload();
                       }
                   });
               	}
             else
                {
            	  return true;
                }
            };
           jBox.open("id:right-text", '批量修改时刻', 450, 'auto',{ buttons: { '提交': true, '取消': false },
        	   submit:submit,
        	   closed:function () { 
        		   location.reload();
        	   } 
        	  	
           }
           );
		}
	</script>
</body>
</html>