<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        	var admin_name = document.getElementById('admin_name');
            if(xmlHttp.responseText == "true") {
                document.getElementById("AdminName_Error_Massage").innerHTML = "已存在！";
                document.all("submit_btn").disabled=true;
            }else {                 
            	var reg = /^\s*$/;
        		if (reg.test(admin_name.value) == true) {
        			admin_name.setAttribute('placeholder', '禁止为空！');
        			admin_name.setAttribute('class', 'form-control change');
        		} else {
        			document.getElementById("AdminName_Error_Massage").innerHTML = "";
        			admin_name.setAttribute('class', 'form-control');
                    document.all("submit_btn").disabled=false;
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
		<h3 class="panel-title">管理员添加</h3>
	</div>
	<div class="panel-body">
		<!--写一个表单-->
		<form method="post" action="AdminServlet?flag=AdminAdd" onsubmit="return check()">

			<div class="form-group">
				<label>管理员名称</label> <input type="text" name="admin_name" id="admin_name"
					class="form-control" style="width: 200px;" onblur="send()">
					<span style="color: red" id="AdminName_Error_Massage"> </span>
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" name="password"
					id="u_password" class="form-control" style="width: 200px;">
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