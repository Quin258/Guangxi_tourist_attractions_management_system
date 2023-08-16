<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/public_html/header.jsp"%>
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
<%
Date d = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
String now = df.format(d);
%>

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
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status == 200) {  //判断xmlHttp的状态是否完毕
        	var a_name = document.getElementById('a_name');
            if(xmlHttp.responseText == "true") {
                document.getElementById("AttrName_Error_Massage").innerHTML = "已存在！";
                document.all("submit_btn").disabled=true;
            }else {                 
            	var reg = /^\s*$/;
        		if (reg.test(a_name.value) == true) {
        			a_name.setAttribute('placeholder', '禁止为空！');
        			a_name.setAttribute('class', 'form-control change');
        		} else {
        			document.getElementById("AttrName_Error_Massage").innerHTML = "";
        			a_name.setAttribute('class', 'form-control');
                    document.all("submit_btn").disabled=false;
        		}
                
            }
        }
    };
    
    var method = "POST";
    var a_name = document.getElementById("a_name").value;
    var url="AttrJudgeServlet?a_name="+a_name;
    xmlHttp.open(method, url, true);       
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send("a_name=" + a_name);
}
</script>

<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">景区添加</h3>
	</div>
	<div class="panel-body">
		<!--写一个表单-->
		<form method="post" action="AttrServlet?flag=AttrAdd"
			onsubmit="return check()">

			<div class="form-group">
				<label>景区名称</label> <input type="text" name="a_name" id="a_name"
					class="form-control" value="" style="width: 200px;" onblur="send()">
				<span style="color: red" id="AttrName_Error_Massage"> </span>
			</div>

			<div class="form-group">
				<label>景区等级</label> <select name="level" class="form-control"
					style="width: 200px;">
					<option value="1">1A</option>
					<option value="2">2A</option>
					<option value="3">3A</option>
					<option value="4">4A</option>
					<option value="5">5A</option>
					<option value="6">未知</option>
				</select>
			</div>

			<div class="form-group">
				<label>景区创建时间</label> <input type="text" name="create_time"
					value="<%=now%>" class="form-control" style="width: 200px;"
					id="id_create_time"> <span style="color: red"> </span>
			</div>

			<div class="form-group">
				<label>景区所在城市</label> <select name="city_id" class="form-control"
					style="width: 200px;">
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
					<option value="14">贺州</option>
				</select> <span style="color: red"> </span>
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
	var a_name = document.getElementById('a_name');
	function check() {
		var reg = /^\s*$/;
		if (reg.test(a_name.value) == true) {
			a_name.setAttribute('placeholder', '禁止为空！');
			a_name.setAttribute('class', 'form-control change');
			return false;
		} else {
			a_name.setAttribute('class', 'form-control');
		}

		return true;
	}
</script>
<%@ include file="/public_html/footer.jsp"%>