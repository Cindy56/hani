<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
		<li ><a href="${ctx}/lottery/lotteryTimeNum/">开奖时刻列表</a></li>
		<shiro:hasPermission name="lottery:lotteryTimeNum:edit"><li><a href="${ctx}/lottery/lotteryTimeNum/form">开奖时刻添加</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/lottery/lotteryTimeNum/batchView">批量修改时刻</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="lotteryTimeNum" action="${ctx}/lottery/lotteryTimeNum/batchView" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>彩票代码：</label>
				<form:select path="lotteryCode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('SSC')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开奖期号：</label>
				<form:input path="lotteryIssueNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('draw_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="批量修改时刻"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="jqGridId"></table>
	<div id="pager5"></div> 
<script type="text/javascript">
$(function(){
	  pageInit();
	});
	function pageInit(){
		$("#jqGridId").jqGrid({   
		    url:"${ctx}/lottery/lotteryTimeNum/batchList",   
		    datatype:"local", //为local时初始化不加载，支持json，xml等   
		    mtype: "POST",   
		    colNames:['编号', '作者', 'ISBN','重量','描述'], //表头   
		    colModel:[ //这里会根据index去解析jsonReader中root对象的属性，填充cell   
		        {name:'id', index:'id', width:55, align:"center",sortable:false},  
		        {name:'author', index:'author', width:100, sortable:false},   
		        {name:'isbn', index:'isbn', width:120,align:"right", sortable:false},   
		        {name:'weight', index:'weight', width:80,align:"center", sortable:false},   
		        {name:'wareDesc', index:'wareDesc', width:400, sortable:false}   
		    ],   
		    width: 'auto', //数字 & 'auto','100%'   
		    height: 'auto',   
		    rowNum: 50, //每页记录数   
		    rowList:[50, 100,300,500], //每页记录数可选列表   
		    pager: '#pager5', //分页标签divID   
		    viewrecords: true, //显示记录数信息，如果这里设置为false,下面的不会显示 recordtext: "第{0}到{1}条, 共{2}条记录", //默认显示为{0}-{1} 共{2}条 scroll: false, //滚动翻页，设置为true时翻页栏将不显示  
		    /**这里是排序的默认设置，这些值会根据列表header点击排序时覆盖*/ sortable: false,   
		    sortname: "warename",   
		    sortorder: "desc",   
		  
		 //   caption:"商品列表", //显示查询结果表格标题   
		    rownumbers: true, //设置列表显示序号,需要注意在colModel中不能使用rn作为index   
		    rownumWidth: 30, //设置显示序号的宽度，默认为25   
		    multiselect: true, //多选框   
		    multiboxonly: true, //在点击表格row时只支持单选，只有当点击checkbox时才多选，需要multiselect=true是有效   
		    viewrecords : true,  
	        shrinkToFit : true,  
	        rownumbers: true,  
	        autowidth: true,  
	        autoScroll: true,  
		    prmNames : { //如当前查询实体为ware，这些可以在查询对象的superObject中设定   
		        page: "wareDetail.page",   
		        rows: "wareDetail.rows",   
		        sort: "wareDetail.sidx",   
		        order: "wareDetail.sord",   
		        search: "wareDetail.search"   
		    },   
		    jsonReader:{ //server返回Json解析设定   
		        root: "list", //对于json中数据列表   
		        page: "page",   
		        total: "totalPage",   
		        records: "totalCount",  
		        repeatitems: false,   
		    }   
		});   
	}

</script>	
</body>


</html>