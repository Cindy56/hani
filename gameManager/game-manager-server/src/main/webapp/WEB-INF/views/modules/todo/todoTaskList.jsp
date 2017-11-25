<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务管理</title>
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
		<li class="active"><a href="${ctx}/todo/todoTask/">待办任务列表</a></li>
		<shiro:hasPermission name="todo:todoTask:edit"><li><a href="${ctx}/todo/todoTask/form">待办任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="todoTask" action="${ctx}/todo/todoTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>任务类型：</label>
				<form:radiobuttons path="type" items="${fns:getDictList('todo_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>任务状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('todo_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务标题</th>
				<th>任务正文</th>
				<th>任务类型</th>
				<th>申请者</th>
				<th>处理者</th>
				<th>任务状态</th>
				<shiro:hasPermission name="todo:todoTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="todoTask">
			<tr>
				<td><a href="${ctx}/todo/todoTask/form?id=${todoTask.id}">
					${todoTask.title}
				</a></td>
				<td>
					${todoTask.content}
				</td>
				<td>
					${fns:getDictLabel(todoTask.type, 'todo_type', '')}
				</td>
				<td>
					${todoTask.senderId.id}
				</td>
				<td>
					${todoTask.receiverId.id}
				</td>
				<td>
					${fns:getDictLabel(todoTask.status, 'todo_status', '')}
				</td>
				<shiro:hasPermission name="todo:todoTask:edit"><td>
    				<a href="${ctx}/todo/todoTask/form?id=${todoTask.id}">修改</a>
					<a href="${ctx}/todo/todoTask/delete?id=${todoTask.id}" onclick="return confirmx('确认要删除该待办任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>