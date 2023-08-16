<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<div class="container">
	<%
	Object n_username = request.getSession().getAttribute("username");
	List<AdminEntity> rows = (ArrayList) request.getAttribute("rows");
	System.out.println(rows);
	int index_num = 1;
	%>
	<%
	if(n_username.equals("admin")) {
	%>
	<div style="margin-bottom: 10px">
		<a class="btn btn-primary" href="AdminServlet?flag=AdminAddView">添加管理员</a>
	</div>
	<%
	}
	%>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			管理员信息
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
				<%
				for (AdminEntity row : rows) {
				%>
				<tr>
					<td><%=index_num++%></td>
					<td><%=row.getId()%></td>
					<td><%=row.getUsername()%></td>
					<%
					if (n_username.equals(row.getUsername())) {
					%>
					<td><a class="btn btn-primary btn-xs"
						href="AdminServlet?flag=AdminEditView&id=<%=row.getId()%>&username=<%=row.getUsername() %>">
							编辑 </a></td>
					<%
					}else if(n_username.equals("admin")){
					%>
					<td><a class="btn btn-primary btn-xs"
						href="AdminServlet?flag=AdminEditView&id=<%=row.getId()%>&username=<%=row.getUsername() %>">
							编辑 </a>
							<a class="btn btn-danger btn-xs"
						href="AdminServlet?flag=AdminDel&id=<%=row.getId()%>">
							删除 </a></td>
					<% 
					} else {
					%>
					<td><a href="" class="btn btn-danger btn-xs" style="pointer-events:none;">无法编辑</a></td>
				</tr>
					<%
					}
					%>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="/public_html/footer.jsp"%>