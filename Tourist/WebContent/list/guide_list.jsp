<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/public_html/header.jsp"%>
<div class="container">
	<%
	List<GuideEntity> rows = (ArrayList) request.getAttribute("rows");
	String find = (String) request.getAttribute("find_str");
	int level = (int) request.getAttribute("level");
	int pageIndex = (int) request.getAttribute("pageIndex");
	int totalPage = (int) request.getAttribute("totalPage");
	int index_num = 1;
	%>

	<div style="margin-bottom: 10px">
		<a class="btn btn-primary" href="GuideServlet?flag=GuideAddView">添加导游</a>
		<div style="float: right; width: 600px">
			<form method="post" action="GuideListServlet">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" name="pageIndex" style="display: none"
							value=<%=pageIndex%> /> <input type="text" name="totalPage"
							style="display: none" value=<%=totalPage%> /> <input type="text"
							name="level" class="level_now" style="display: none"
							value=<%=level%> id="level_now" /> <input type="text"
							name="find" class="form-control" placeholder="导游名字查找" /> <span
							class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			导游信息
		</div>
		<form method="post" id="level_bar_form" action="GuideListServlet">
			<input type="text" name="pageIndex" style="display: none"
				value=<%=pageIndex%> /> <input type="text" name="totalPage"
				style="display: none" value=<%=totalPage%> />
			<button name="level" type="submit" class="btn btn-xs" value="0">
				默认</button>
			<button name="level" type="submit" class="btn btn-xs" value="1">
				初级</button>
			<button name="level" type="submit" class="btn btn-xs" value="2">
				中级</button>
			<button name="level" type="submit" class="btn btn-xs" value="3">
				高级</button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>序号</th>
					<th>导游ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>导游证号</th>
					<th>语种</th>
					<th>等级</th>
					<th>所在机构</th>
					<th>电话</th>
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
				for (GuideEntity row : rows) {
				%>
				<tr>
					<td><%=index_num++%></td>
					<td><%=row.getId()%></td>
					<td><%=row.getName()%></td>
					<c:set var="gid" scope="session" value="<%=row.getGender()%>" />
					<c:choose>
						<c:when test="${gid==1}">
							<td>男</td>
						</c:when>
						<c:when test="${gid==2}">
							<td>女</td>
						</c:when>
						<c:otherwise>
							<td>未知</td>
						</c:otherwise>
					</c:choose>
					<td><%=row.getGuide_id()%></td>
					<td><%=row.getLang()%></td>
					<c:set var="level_t" scope="session" value="<%=row.getLevel()%>" />
					<c:choose>
						<c:when test="${level_t==1}">
							<td>初级</td>
						</c:when>
						<c:when test="${level_t==2}">
							<td>中级</td>
						</c:when>
						<c:when test="${level_t==3}">
							<td>高级</td>
						</c:when>
						<c:otherwise>
							<td>未知</td>
						</c:otherwise>
					</c:choose>
					<td><%=row.getInstitutions()%></td>
					<td><%=row.getPhone()%></td>
					<%
					if ("admin".equals(loginType)) {
					%>
					<td><a class="btn btn-primary btn-xs"
						href="GuideServlet?flag=GuideEditView&id=<%=row.getId()%>"> 编辑
					</a> <a class="btn btn-danger btn-xs"
						href="GuideServlet?flag=GuideDel&id=<%=row.getId()%>"
						onClick="return confirm('删除后将无法找回该数据\n确定删除?');">删除</a></td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div style="text-align: center">
			<div style="display: inline-block; *display: inline; zoom: 1">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a
							href="GuideListServlet?pageIndex=1&find=<%=find%>&level=<%=level%>">首页</a></li>
						<li><a
							href="GuideListServlet?pageIndex=<%=pageIndex == 1 ? 1 : pageIndex - 1%>&find=<%=find%>&level=<%=level%>">上一页</a></li>
						<li><a><%=pageIndex%>/<%=totalPage%></a></li>
						<li><a
							href="GuideListServlet?pageIndex=<%=pageIndex == totalPage ? totalPage : pageIndex + 1%>&find=<%=find%>&level=<%=level%>">下一页</a></li>
						<li><a
							href="GuideListServlet?pageIndex=<%=totalPage%>&find=<%=find%>&level=<%=level%>">尾页</a></li>
						<div style="float: right; width: 100px">
							<form method="post" action="GuideListServlet">
								<div class="input-group">
									<input type="text" name="find" class="order_now"
										value="<%=find%>" style="display: none" /> <input type="text"
										name="level" class="level_now" value="<%=level%>"
										style="display: none" /> <input type="text" name="pageIndex"
										class="form-control" value="<%=pageIndex%>"> <span
										class="input-group-btn">
										<button class="btn btn-default" type="submit">跳转</button>
									</span>
								</div>
							</form>
						</div>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>
<script>
	var now = document.getElementById('level_now').value;
	var sort_bar_form = document.getElementById('level_bar_form');
	var input_class = sort_bar_form.childNodes;
	for (var i = 0; i < input_class.length; i++) {
		if (input_class[i].value == now) {
			input_class[i].className += ' btn-primary';
		}
	}
</script>
<%@ include file="/public_html/footer.jsp"%>