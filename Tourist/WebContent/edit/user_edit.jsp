<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

UserEntity user = (UserEntity) request.getAttribute("user");
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
        	var u_name_judge = xmlHttp.responseText
        	var u_name = document.getElementById('u_name');
        	var original_u_name ="<%=user.getUsername() %>";
        	console.log(u_name.value);
        	console.log(original_u_name);
        	// 如果后台比对结果是有已存在
            if(u_name_judge == "true") {
            	// 对比原值和现值
            	console.log("here");
            	if(u_name.value == original_u_name){
            		console.log("here");
            		document.getElementById("UserName_Error_Massage").innerHTML = "";
            		u_name.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			u_name_judge = "false";
            	}else{
            		document.getElementById("UserName_Error_Massage").innerHTML = "已存在！";
                    document.all("submit_btn").disabled=true;
            	}
            }else {
            	var isNullReg = /^\s*$/
         		if (isNullReg.test(u_name.value) == true) {
         			u_name.setAttribute('placeholder', '禁止为空');
         			u_name.setAttribute('class', 'form-control change');
         			document.all("submit_btn").disabled=true;
         			guide_judge = true;
         		} else {
         			document.getElementById("UserName_Error_Massage").innerHTML = "";
         			u_name.setAttribute('class', 'form-control');
         			document.all("submit_btn").disabled=false;
         			u_name_judge = false;
         		}
            }  
		}
    };
    
    var method = "POST";
    var u_name = document.getElementById("u_name").value;
    var url="UserJudgeServlet?u_name="+u_name;
    console.log(url);
    xmlHttp.open(method, url, true);       
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send("u_name=" + u_name);
}
</script>

<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">用户编辑</h3>
	</div>
	<div class="panel-body">
		<form method="post"
			action="UserServlet?flag=UserEdit&id=<%=user.getId()%>"
			onsubmit="return check()">
			<div class="form-group">
				<label>用户名称</label> <input type="text" name="u_name" id="u_name" onblur="send()"
					class="form-control" value="<%=user.getUsername()%>"
					style="width: 200px;">
					<span style="color: red" id="UserName_Error_Massage"> </span>
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" name="password"
					id="u_password" class="form-control" value="<%=user.getPassword()%>"
					style="width: 200px;">
			</div>

			<button type="submit" class="btn btn-primary" name="submit_btn">提交</button>
			<a class="btn btn-primary" href="CityListServlet">返回主页</a>

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