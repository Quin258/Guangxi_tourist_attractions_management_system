<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
AdminEntity admin = (AdminEntity) request.getAttribute("admin");
String loginUsername = (String) request.getSession().getAttribute("username");
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
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status == 200) {  //判断xmlHttp的状态是否完毕
        	var admin_name_judge = xmlHttp.responseText
        	var admin_name = document.getElementById('admin_name');
        	var original_admin_name ="<%=admin.getUsername() %>";
        	console.log(admin_name.value);
        	console.log(original_admin_name);
        	// 如果后台比对结果是有已存在
            if(admin_name_judge == "true") {
            	// 对比原值和现值
            	if(admin_name.value == original_admin_name){
            		document.getElementById("AdminName_Error_Massage").innerHTML = "";
            		admin_name.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			admin_name_judge = "false";
            	}else{
            		document.getElementById("AdminName_Error_Massage").innerHTML = "已存在！";
                    document.all("submit_btn").disabled=true;
            	}
            }else {
            	var isNullReg = /^\s*$/
         		if (isNullReg.test(admin_name.value) == true) {
         			admin_name.setAttribute('placeholder', '禁止为空');
         			admin_name.setAttribute('class', 'form-control change');
         			document.all("submit_btn").disabled=true;
         			guide_judge = true;
         		} else {
         			document.getElementById("AdminName_Error_Massage").innerHTML = "";
         			admin_name.setAttribute('class', 'form-control');
         			document.all("submit_btn").disabled=false;
         			admin_name_judge = false;
         		}
            }  
		}
    };
    
    var method = "POST";
    var admin_name = document.getElementById("admin_name").value;
    var url="AdminJudgeServlet?admin_name="+admin_name;
    console.log(url);
    xmlHttp.open(method, url, true);       
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send("admin_name=" + admin_name);
}
</script>

<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">管理员编辑</h3>
	</div>
	<div class="panel-body">
		<form method="post"
			action="AdminServlet?flag=AdminEdit&id=<%=admin.getId()%>"
			onsubmit="return check()">
			<div class="form-group">
				<label>管理员名称</label> <input type="text" name="admin_name" id="admin_name" onblur="send()"
					class="form-control" value="<%=admin.getUsername()%>"
					style="width: 200px;">
					<span style="color: red" id="AdminName_Error_Massage"> </span>
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" name="u_password"
					id="u_password" class="form-control"
					style="width: 200px;">
			</div>

			<button type="submit" class="btn btn-primary" name="submit_btn">提交</button>
			<a class="btn btn-primary" href="AdminListServlet">返回</a>

		</form>
	</div>
</div>
<script>
password = document.getElementById('u_password');
password.onblur = function() {
	var isNullReg = /^\s*$/
	if (isNullReg.test(password.value)) {
		password.setAttribute('placeholder', '禁止为空');
		password.setAttribute('class', 'form-control change');
	} else {
		password.setAttribute('class', 'form-control');
	}
};

	function check() {
		var reg = /^\s*$/;
		if (reg.test(password.value) == true) {
			password.setAttribute('placeholder', '禁止为空');
			password.setAttribute('class', 'form-control change');
			return false;
		} else {
			password.setAttribute('class', 'form-control');
		}
		return true;
	}
</script>
<%@ include file="/public_html/footer.jsp"%>