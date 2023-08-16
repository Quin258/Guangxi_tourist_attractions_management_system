<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<div class="container">
	<%
	List<CityEntity> rows = (ArrayList) request.getAttribute("rows");
	int order = (int) request.getAttribute("order");
	int index_num = 1;
	%>
	<input type="text" name="order" class="order_now" style="display: none"
		value=${order } id="order_now" />
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			城市信息
		</div>
		<div class="sort_bar">
			<span>按总收入排序:</span>
			<form method="post" id="order_bar_form" action="CityListServlet">

				<button name="order" type="submit" class="btn btn-xs" value="0">
					默认</button>
				<button name="order" type="submit" class="btn btn-xs" value="1">
					升序</button>
				<button name="order" type="submit" class="btn btn-xs" value="2">
					降序</button>
			</form>
		</div>
		<table class="table">
			<thead>
				<tr>
					<td>序号</td>
					<td>城市ID</td>
					<td>城市名</td>
					<td>年总收入</td>
					<%
					if ("admin".equals(loginType)) {
					%>
					<td>操作</td>
					<%
					}
					%>
				</tr>
			</thead>
			<tbody>
				<%
				for (CityEntity row : rows) {
				%>
				<tr>
					<td><%=index_num++%></td>
					<td><%=row.getId()%></td>
					<td><%=row.getName()%></td>
					<td><%=row.getTotal_revenue()%></td>
					<%
					if ("admin".equals(loginType)) {
					%>
					<td><a class="btn btn-primary btn-xs"
						href="CityServlet?flag=CityEditView&cid=<%=row.getId()%>"> 编辑</a>
					</td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>
<script>
	var now = document.getElementById('order_now').value;
	var sort_bar_form = document.getElementById('order_bar_form');
	var input_class = sort_bar_form.childNodes;
	for (var i = 0; i < input_class.length; i++) {
		if (input_class[i].value == now) {
			input_class[i].className += ' btn-primary';
		}
	}
</script>
<%@ include file="/public_html/footer.jsp"%>