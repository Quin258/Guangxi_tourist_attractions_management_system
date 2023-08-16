<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/public_html/header.jsp"%>


<div class="container">
	<%
	List<AttrEntity> rows = (ArrayList) request.getAttribute("rows");
	String find = (String) request.getAttribute("find_str");
	int order = (int) request.getAttribute("order");
	int pageIndex = (int) request.getAttribute("pageIndex");
	int totalPage = (int) request.getAttribute("totalPage");
	int index_num = 1;
	%>
	<div style="margin-bottom: 10px">
		<a class="btn btn-primary" href="AttrServlet?flag=AttrAddView">添加景区</a>
		<div style="float: right; width: 600px">
			<form method="post" action="AttrListServlet">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" name="pageIndex" style="display: none"
							value=<%=pageIndex%> /> <input type="text" name="totalPage"
							style="display: none" value=<%=totalPage%> /> <input type="text"
							name="order" class="order_now" style="display: none"
							value="${order}" id="order_now" /> <input type="text"
							name="find" class="form-control" placeholder="景区名关键词查找" /> <span
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
			景区信息
		</div>
		<div class="sort_bar">
			<span>按城市筛选(排名按按首字母)</span>
			<form method="post" id="order_bar_form" action="AttrListServlet">
				<input type="text" name="totalPage" style="display: none"
					value=<%=totalPage%> />
				<button name="order" type="submit" class="btn btn-xs" value="0">
					默认</button>
				<button name="order" type="submit" class="btn btn-xs" value="1">
					桂林</button>
				<button name="order" type="submit" class="btn btn-xs" value="2">
					来宾</button>
				<button name="order" type="submit" class="btn btn-xs" value="3">
					贵港</button>
				<button name="order" type="submit" class="btn btn-xs" value="4">
					梧州</button>
				<button name="order" type="submit" class="btn btn-xs" value="5">
					防城港</button>
				<button name="order" type="submit" class="btn btn-xs" value="6">
					柳州</button>
				<button name="order" type="submit" class="btn btn-xs" value="7">
					百色</button>
				<button name="order" type="submit" class="btn btn-xs" value="8">
					北海</button>
				<button name="order" type="submit" class="btn btn-xs" value="9">
					钦州</button>
				<button name="order" type="submit" class="btn btn-xs" value="10">
					河池</button>
				<button name="order" type="submit" class="btn btn-xs" value="11">
					南宁</button>
				<button name="order" type="submit" class="btn btn-xs" value="12">
					崇左</button>
				<button name="order" type="submit" class="btn btn-xs" value="13">
					玉林</button>
				<button name="order" type="submit" class="btn btn-xs" value="14">
					贺州</button>
			</form>
		</div>
		<table class="table">
			<thead>
				<tr>
					<td>序号</td>
					<td>景区ID</td>
					<td>景区名</td>
					<td>景区等级</td>
					<td>景区创建时间</td>
					<td>景区所在城市</td>
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
				for (AttrEntity row : rows) {
				%>
				<tr>
					<td><%=index_num++%></td>
					<td><%=row.getId()%></td>
					<td><%=row.getName()%></td>
					<c:set var="level" scope="session" value="<%=row.getLevel()%>" />
					<c:choose>
						<c:when test="${level==1}">
							<td>1A</td>
						</c:when>
						<c:when test="${level==2}">
							<td>2A</td>
						</c:when>
						<c:when test="${level==3}">
							<td>3A</td>
						</c:when>
						<c:when test="${level==4}">
							<td>4A</td>
						</c:when>
						<c:when test="${level==5}">
							<td>5A</td>
						</c:when>
						<c:otherwise>
							<td>未知</td>
						</c:otherwise>
					</c:choose>
					<td><%=row.getCreate_time()%></td>
					<c:set var="cid" scope="session" value="<%=row.getCity_id()%>" />
					<c:choose>
						<c:when test="${cid==1}">
							<td>桂林</td>
						</c:when>
						<c:when test="${cid==2}">
							<td>来宾</td>
						</c:when>
						<c:when test="${cid==3}">
							<td>贵港</td>
						</c:when>
						<c:when test="${cid==4}">
							<td>梧州</td>
						</c:when>
						<c:when test="${cid==5}">
							<td>防城港</td>
						</c:when>
						<c:when test="${cid==6}">
							<td>柳州</td>
						</c:when>
						<c:when test="${cid==7}">
							<td>百色</td>
						</c:when>
						<c:when test="${cid==8}">
							<td>北海</td>
						</c:when>
						<c:when test="${cid==9}">
							<td>钦州</td>
						</c:when>
						<c:when test="${cid==10}">
							<td>河池</td>
						</c:when>
						<c:when test="${cid==11}">
							<td>南宁</td>
						</c:when>
						<c:when test="${cid==12}">
							<td>崇左</td>
						</c:when>
						<c:when test="${cid==13}">
							<td>玉林</td>
						</c:when>
						<c:when test="${cid==14}">
							<td>贺州</td>
						</c:when>
						<c:otherwise>
							<td>未知</td>
						</c:otherwise>
					</c:choose>
					<%
					if ("admin".equals(loginType)) {
					%>
					<td><a class="btn btn-primary btn-xs"
						href="AttrServlet?flag=AttrEditView&id=<%=row.getId()%>"> 编辑 </a>
						<a class="btn btn-danger btn-xs"
						href="AttrServlet?flag=AttrDel&id=<%=row.getId()%>"
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
							href="AttrListServlet?pageIndex=1&find=<%=find%>&order=${order}">首页</a></li>
						<li><a
							href="AttrListServlet?pageIndex=<%=pageIndex == 1 ? 1 : pageIndex - 1%>&find=<%=find%>&order=${order}">上一页</a></li>
						<li><a>${pageIndex}/${totalPage}</a></li>
						<li><a
							href="AttrListServlet?pageIndex=<%=pageIndex == totalPage ? totalPage : pageIndex + 1%>&find=<%=find%>&order=${order}">下一页</a></li>
						<li><a
							href="AttrListServlet?pageIndex=${totalPage}&find=<%=find%>&order=${order}">尾页</a></li>
						<div style="float: right; width: 100px">
							<form method="post" action="AttrListServlet">
								<div class="input-group">
									<input type="text" name="find" value="<%=find%>"
										style="display: none" /> <input type="text" name="order"
										value="${order}" style="display: none" /> <input type="text"
										name="pageIndex" class="form-control" value="${pageIndex}">
									<span class="input-group-btn">
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