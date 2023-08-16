<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="com.mysql.cj.util.StringUtils"%>
<%@ page import="com.ncvt.server.*"%>
<%@ page import="com.ncvt.entity.*"%>
<%
String loginType = (String) request.getSession().getAttribute("loginType");
String username = (String) request.getSession().getAttribute("username");
UserServer userServer = new UserServer();
UserEntity rows_forId = userServer.queryUserWithName((String) username);
int uid = 1;
if (rows_forId != null) {
	uid = rows_forId.getId();
}
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8" />
<title>广西壮族自治区旅游业管理系统</title>
<script type="text/javascript" src="static/js/jquery-3.6.0.min.js"></script>
<script src="static/js/echarts.min.js"></script>
<script src="static/js/Guangxi.js"></script>
<link rel="stylesheet"
	href="static/plugins/bootstrap-3.4.1/dist/css/bootstrap.min.css" />

<style type="text/css">
@IMPORT url("static/plugins/bootstrap-3.4.1/dist/css/bootstrap.min.css")
	;
</style>
<style>
.navbar {
	border-radius: 0;
}

nav.navbar.navbar-default {
	background-image: url('static/img/nav_bg.png');
}

div.collapse.navbar-collapse {
	background-color: rgba(51, 80, 131, 0.7);
}

.navbar-default { .navbar-brand { color:white;
	
}

}
.header {
	padding: 0 0;
	margin: 0 0;
	width: 100%;
	height: 228px;
	background-image: url('static/img/header_bg.png');
}

.title_box {
	position: absolute;
	top: 30px;
	left: 10px;
}

.title {
	font-family: STXingkai;
	font-size: 60px;
}

.line1 {
	margin-top: 20px;
	margin-left: 100px;
}

.line2 {
	margin-top: 40px;
	margin-left: 200px;
}

.bigbox {
	border-width: 1px;
	border-style: solid;
	width: 1000px;
	height: 700px;
	margin: 0px auto;
	margin-bottom: 20px;
	padding: 0 0;
}

.bigbox .bb_hr {
	margin-top: 0px;
	border-color: black;
}

.bigbox span {
	display: inline-block;
	width: 100px;
	height: 50px;
	background-color: white;
}

.bigbox span a {
	display: inline-block;
	text-decoration: none;
	text-align: center;
	line-height: 50px;
	width: 100px;
	height: 50px;
	color: black;
}

.footer {
	position: relative;
	padding: 0 0;
	margin: 0 0;
	margin-top: auto;
	width: 100%;
	height: 160px;
	background-color: rgb(38, 84, 166);
}

.footer .Llogo {
	position: absolute;
	width: 300px;
	height: 150px;
	background-image: url('static/img/logo.png');
	background-size: 300px 150px;
	margin-right: 0px;
}

.footer .footer_text {
	position: absolute;
	margin: 0px auto;
	margin-top: 10px;
	margin-left: 360px;
	margin-right: 0px;
	width: 1000px;
	height: 140px;
}

.footer .footer_text ul {
	float: left;
	border-right: 1px solid rgb(162, 163, 164);
	padding: 0 60px;
	margin-bottom: 0px;
}

.footer .footer_text li {
	list-style: none;
	line-height: 25px;
	text-align: center;
}

.footer .footer_text li a {
	color: white;
	font-size: 18px;
}

.footer .person {
	position: absolute;
	top: 20px;
	right: 70px;
	line-height: 40px;
	text-align: center;
	color: white;
}

.bigbox span .now_box {
	color: white;
	background-color: rgb(38, 84, 165);
}

.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus,
	.navbar-default .navbar-nav>.open>a:hover {
	background-color: rgb(38, 84, 165);
}

.dropdown-menu li form input {
	border: none;
	background-color: RGB(255, 255, 255)
}
</style>
</head>
<body>
	<div class="header">
		<div class="title_box">
			<span class="title line1">秀甲天下</span> <br /> <span
				class="title line2">壮美广西</span>
		</div>
	</div>
	<nav class="navbar navbar-default">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="color: white">广西壮族自治区旅游业管理系统</a>
			</div>


			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"
						style="color: white">城市<span class="caret"></span></a><span
						class="sr-only">(current)</span>
						<ul class="dropdown-menu">
							<li><a href="CityListServlet">已有城市信息</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"
						style="color: white">景点<span class="caret"></span><span
							class="sr-only">(current)</span></a>
						<ul class="dropdown-menu">
							<li><a href="AttrListServlet">已有景点信息</a></li>
							<li><a href="AttrServlet?flag=AttrAddView">添加景点</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"
						style="color: white">导游<span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a href="GuideListServlet">已有导游信息</a></li>
							<li><a href="GuideServlet?flag=GuideAddView">添加导游</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li><a href="DrawServlet?flag=Default" style="color: white">可视化系统</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false" style="color: white">${username}<span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<%
							if ("admin".equals(loginType)) {
							%>
							<li><a href="AdminListServlet">管理员相关</a></li>
							<li><a href="UserListServlet">用户相关</a></li>
							<li><a href="LoginServlet?action=logout">注销</a></li>
							<%
							} else {
							%>
							<li><a href="UserServlet?flag=UserEditView&id=<%=uid%>">编辑账号</a></li>
							<li><a href="LoginServlet?action=logout">注销</a></li>
							<%
							}
							%>
						</ul>
				</ul>
			</div>
		</div>
	</nav>