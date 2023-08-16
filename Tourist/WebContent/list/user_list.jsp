<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<div class="container">
	<%
	Object e_username = request.getSession().getAttribute("username");
	List<UserEntity> rows = (ArrayList) request.getAttribute("rows");
	System.out.println(rows);
	int index_num = 1;
	%>
	<div style="margin-bottom: 10px">
		<a class="btn btn-primary" href="UserServlet?flag=UserAddView">添加用户</a>
	</div>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			用户信息
		</div>
		<table class="table">
			<thead>
				<tr>
					<td>序号</td>
					<td>id</td>
					<td>账号名</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<% for (UserEntity row : rows) { %>
				<tr>
					<td><%=index_num++%></td>
					<td><%=row.getId()%></td>
					<td><%=row.getUsername()%></td>
					<% if("admin".equals(loginType)){ %>
					<td><a class="btn btn-primary btn-xs"
						href="UserServlet?flag=UserEditView&id=<%=row.getId()%>&username=<%=row.getUsername() %>">
							编辑 </a>
							<a class="btn btn-danger btn-xs"
						href="UserServlet?flag=UserDel&id=<%=row.getId()%>"
						onClick="return confirm('删除后将无法找回该数据\n确定删除?');">删除</a>
						</td>
					<%} else if (e_username.equals(row.getUsername())) {
					%>
					<td><a class="btn btn-primary btn-xs"
						href="UserServlet?flag=UserEditView&id=<%=row.getId()%>&username=<%=row.getUsername() %>">
							编辑 </a></td>
					<% }else{ %>
					<td><a href="" class="btn btn-danger btn-xs" style="pointer-events:none;">无法编辑</a></td>
					<% } %>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="/public_html/footer.jsp"%>