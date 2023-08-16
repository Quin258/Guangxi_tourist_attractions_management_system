<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String info = (String) request.getAttribute("info");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>广西壮族自治区旅游业管理系统</title>
<link rel="stylesheet"
	href="static/plugins/bootstrap-3.4.1/css/bootstrap.min.css">
<style>
body {
	width: 100%;
	height: 100%;
	background-size: cover;
	background-image: url('static/img/login.png');
}

.account {
	background-color: rgba(255, 255, 255, 0.7);
	width: 400px;
	border: 1px solid #dddddd;
	border-radius: 4px;
	box-shadow: 5px 5px 20px #aaa;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	padding: 20px 40px;
}

.account h2 {
	margin-top: 10px;
	text-align: center;
}
</style>
</head>

<body>
	<div class="account">
		<h2>广西壮族自治区旅游业管理系统登录</h2>
		<form method="post" action="LoginServlet?action=login">
			<div class="form-group">
				<label>账号</label> <input type="text" name="username"
					class="form-control" placeholder="账号" id="id_username">
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" name="password"
					class="form-control" placeholder="密码" id="id_password">
			</div>
			<div class="form-group">
				<label>登录用户类型</label> <select name="loginType" class="form-control">
					<option value="user" selected="selected">普通用户</option>
					<option value="admin">管理员</option>
				</select>
			</div>
			<%
			if (info != null && info != "") {
			%>
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span>
				${info}
			</div>
			<%
			}
			%>
			<input type="submit" value="登 录" class="btn btn-primary">
		</form>
	</div>

</body>
</html>