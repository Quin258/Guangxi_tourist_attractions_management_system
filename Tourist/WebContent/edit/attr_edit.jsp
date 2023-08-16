<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
AttrEntity attr = (AttrEntity)request.getAttribute("attr");
%>
<style>
.panel-body form {
	display: block;
	margin: 0px auto;
	width: 205px;
}

.form-control.change {
	border: 2px red solid;
	outline-color: red;
}
</style>

<script type="text/javascript">
function createXMLHttpRequest() {    //创建XMLHttpRequest
    try {
        return new XMLHttpRequest();            //直接创建对象，不适用与IE5,IE6
    } catch (e) {
        try {
            return new ActiveXObject("Msxml2.XMLHTTP");　　　//对于不适用的，创建合适的对象
        } catch (e) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
}

function send() {
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.onreadystatechange = function (){
		if (xmlHttp.readyState === 4 && xmlHttp.status == 200) { 
			//判断xmlHttp的状态是否完毕
         var a_name_judge = xmlHttp.responseText;
         var original_a_name = "<%=attr.getName() %>";
         var a_name = document.getElementById('a_name');
         
         // 如果后台比对结果是有已存在
            if(a_name_judge =="true") {
            	// 对比原值和现值
            	if(a_name.value==original_a_name){
            		document.getElementById("AttrName_Error_Massage").innerHTML = "";
        			a_name.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			a_name_judge = false;
            	}else{
            		document.getElementById("AttrName_Error_Massage").innerHTML = "已存在！";
                    document.all("submit_btn").disabled=true;
            	}
            }else {
            	var isNullReg = /^\s*$/
         		if (isNullReg.test(a_name.value) == true) {
         			a_name.setAttribute('placeholder', '禁止为空');
         			a_name.setAttribute('class', 'form-control change');
         			document.all("submit_btn").disabled=true;
         			guide_judge = true;
         		} else {
         			document.getElementById("AttrName_Error_Massage").innerHTML = "";
         			a_name.setAttribute('class', 'form-control');
         			document.all("submit_btn").disabled=false;
         			a_name_judge = false;
         		}
            }  
		}
	}
	var method = "POST";
    var a_name = document.getElementById("a_name").value;
    var url="AttrJudgeServlet?a_name="+a_name;
    xmlHttp.open(method, url, true);       
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send("a_name"+a_name);
}    
</script>

<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">景区编辑</h3>
	</div>
	<div class="panel-body">
		<form method="post"
			action="AttrServlet?flag=AttrEdit&id=<%=attr.getId() %>"
			onsubmit="return check()">

			<div class="form-group">
				<label>景区名称</label> <input type="text" name="a_name" id="a_name"
				required="required"
					class="form-control" value="<%=attr.getName() %>"
					style="width: 200px;" onblur="send()"> <span style="color: red"
					id="AttrName_Error_Massage"> </span>
			</div>

			<div class="form-group">
				<label>景区等级</label>
				<c:set var="level" scope="session" value="<%=attr.getLevel()%>" />
				<c:choose>
					<c:when test="${level==1}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1" selected="selected">1A</option>
							<option value="2">2A</option>
							<option value="3">3A</option>
							<option value="4">4A</option>
							<option value="5">5A</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==2}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">1A</option>
							<option value="2" selected="selected">2A</option>
							<option value="3">3A</option>
							<option value="4">4A</option>
							<option value="5">5A</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==3}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">1A</option>
							<option value="2">2A</option>
							<option value="3" selected="selected">3A</option>
							<option value="4">4A</option>
							<option value="5">5A</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==4}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">1A</option>
							<option value="2">2A</option>
							<option value="3">3A</option>
							<option value="4" selected="selected">4A</option>
							<option value="5">5A</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==5}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">1A</option>
							<option value="2">2A</option>
							<option value="3">3A</option>
							<option value="4">4A</option>
							<option value="5" selected="selected">5A</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">1A</option>
							<option value="2">2A</option>
							<option value="3">3A</option>
							<option value="4">4A</option>
							<option value="5">5A</option>
							<option value="6" selected="selected">未知</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label>景区创建时间</label>
				<!--   <input type="text" name="create_time" id="id_create_time" class="form-control" style="width:200px;" value="<%=attr.getCreate_time() %>"> -->
				<input type="text" name="create_time"
					value="<%=attr.getCreate_time()%>" class="form-control"
					placeholder="景点首次开放时间" style="width: 200px;" id="id_create_time">
				<span style="color: red"> </span>
			</div>

			<div class="form-group">
				<label>景区所在城市</label>
				<c:set var="city_id" scope="session" value="<%=attr.getCity_id()%>" />
				<c:choose>
					<c:when test="${city_id==1}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1" selected="selected">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==2}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2" selected="selected">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==3}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3" selected="selected">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==4}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4" selected="selected">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==5}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5" selected="selected">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==6}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6" selected="selected">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==7}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7" selected="selected">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==8}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8" selected="selected">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==9}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9" selected="selected">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==10}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10" selected="selected">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==11}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11" selected="selected">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==12}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12" selected="selected">崇左</option>
							<option value="13">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:when test="${city_id==13}">
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13" selected="selected">玉林</option>
							<option value="14">贺州</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="city_id" class="form-control" style="width: 200px;">
							<option value="1">桂林</option>
							<option value="2">来宾</option>
							<option value="3">贵港</option>
							<option value="4">梧州</option>
							<option value="5">防城港</option>
							<option value="6">柳州</option>
							<option value="7">百色</option>
							<option value="8">北海</option>
							<option value="9">钦州</option>
							<option value="10">河池</option>
							<option value="11">南宁</option>
							<option value="12">崇左</option>
							<option value="13">玉林</option>
							<option value="14" selected="selected">贺州</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>

			<button type="submit" class="btn btn-primary" name="submit_btn">提交</button>
			<a class="btn btn-primary" href="AttrListServlet">返回</a>

		</form>
	</div>
</div>

<link rel="stylesheet" href="static/css/jquery.datetimepicker.min.css" />
<script src="static/js/jquery.datetimepicker.full.min.js"></script>
<script>
	$(function() {
		$('#id_create_time').datetimepicker({
			format : 'Y-m-d',
		});
	});
</script>
<script>
	function check() {
		var reg = /^\s*$/;
		if (reg.test(a_name.value) == true) {
			a_name.setAttribute('placeholder', '禁止为空');
			a_name.setAttribute('class', 'form-control change');
			return false;
		} else {
			a_name.setAttribute('class', 'form-control');
		}

		return true;
	}
</script>
<%@ include file="/public_html/footer.jsp"%>