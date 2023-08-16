<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
String info = (String)request.getAttribute("info");
String status = (String)request.getAttribute("status");
String btn_type = (String)request.getAttribute("btn_type");
String back_list_1 = (String)request.getAttribute("back_list_1");
String back_str = (String)request.getAttribute("back_str");
String back_list_2 = (String)request.getAttribute("back_list_2");
%>
<div class="content">
	<div class="alert ${status} " role="alert">
		${info}
		<a class="btn ${btn_type}" href="${back_list_1}">${back_str}</a>
		${back_list_2} 
	</div>
</div>
<%@ include file="/public_html/footer.jsp"%>