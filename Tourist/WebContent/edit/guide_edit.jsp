<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
GuideEntity guide = (GuideEntity) request.getAttribute("guide");
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
        	var resp = JSON.parse(xmlHttp.responseText);
         var guide_id_judge = resp.guide_id_judge;
         var phone_judge = resp.phone_judge;
         
         var original_guide_id = "<%=guide.getGuide_id() %>";
var original_phone = <%=guide.getPhone() %>;
         var guide_id = document.getElementById('guide_id');
         var phone = document.getElementById('phone');
         
         // 如果后台比对结果是有已存在
            if(guide_id_judge) {
            	// 对比原值和现值
            	if(guide_id.value==original_guide_id){
            		document.getElementById("GuideIdError_Massage").innerHTML = "";
        			guide_id.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			guide_id_judge = false;
            	}else{
            		document.getElementById("GuideIdError_Massage").innerHTML = "已存在！";
                    document.all("submit_btn").disabled=true;
            	}
            }else {
            	var isNullReg = /^\s*$/
         		if (isNullReg.test(guide_id.value) == true) {
         			guide_id.setAttribute('placeholder', '禁止为空');
         			guide_id.setAttribute('class', 'form-control change');
         			document.all("submit_btn").disabled=true;
         			guide_judge = true;
         		} else {
         			document.getElementById("GuideIdError_Massage").innerHTML = "";
         			guide_id.setAttribute('class', 'form-control');
         			document.all("submit_btn").disabled=false;
         			guide_id_judge = false;
         		}
            }  
            
            if(phone_judge) {
            	if(phone.value==original_phone){
            		document.getElementById("PhoneError_Massage").innerHTML = "";
        			phone.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			phone_judge = false;
            	}else{
            		document.getElementById("PhoneError_Massage").innerHTML = "已存在！";
                    document.all("submit_btn").disabled=true;
            	}
            }else {
            	var reg = /^1[3|4|5|7|8|9]\d{9}$/;
            	if (reg.test(phone.value)) {
            		document.getElementById("PhoneError_Massage").innerHTML = "";
        			phone.setAttribute('class', 'form-control');
        			document.all("submit_btn").disabled=false;
        			phone_judge = false;
        		} else {
        			phone.setAttribute('placeholder', '格式错误');
        			phone.setAttribute('class', 'form-control change');
        			document.all("submit_btn").disabled=true;
        			phone_judge = true;
        		}
            }
            console.log(phone_judge);
            console.log(guide_id_judge);
            if(!phone_judge&&!guide_id_judge){
            	document.all("submit_btn").disabled=false;
            }else{
            	document.all("submit_btn").disabled=true;
            }
        }
    };
    
    var method = "POST";
    var guide_id = document.getElementById("guide_id").value;
    var phone = document.getElementById("phone").value;
    var url="GuideJudgeServlet?guide_id="+guide_id+"&phone="+phone;
    xmlHttp.open(method, url, true);       
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var data = {
    		"guide_id": guide_id,
    		 "phone" : phone
    		};
    var jsonData = JSON.stringify(data);
    xmlHttp.send(jsonData);
}
</script>

<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">导游编辑</h3>
	</div>
	<div class="panel-body">
		<!--写一个表单-->
		<form method="post"
			action="GuideServlet?flag=GuideEdit&id=<%=guide.getId()%>"
			onsubmit="return check()">
			<div class="form-group">
				<label>导游姓名</label> <input type="text" name="g_name" id="g_name"
					class="form-control" value="<%=guide.getName()%>"
					style="width: 200px;"> <span style="color: red"> </span>
			</div>

			<div class="form-group">
				<label>导游性别</label>
				<c:set var="gid" scope="session" value="<%=guide.getGender()%>" />
				<c:choose>
					<c:when test="${gid==1}">
						<select name="gender" class="form-control" style="width: 200px;">
							<option value="1" selected="selected">男</option>
							<option value="2">女</option>
							<option value="3">未知</option>
						</select>
					</c:when>
					<c:when test="${gid==2}">
						<select name="gender" class="form-control" style="width: 200px;">
							<option value="1">男</option>
							<option value="2" selected="selected">女</option>
							<option value="3">未知</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="gender" class="form-control" style="width: 200px;">
							<option value="1">男</option>
							<option value="2">女</option>
							<option value="3" selected="selected">未知</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label>导游证号</label> <input type="text" name="guide_id" id="guide_id"
					onblur="send()" class="form-control"
					value="<%=guide.getGuide_id()%>" style="width: 200px;"> <span
					style="color: red" id="GuideIdError_Massage"> </span>
			</div>

			<div class="form-group">
				<label>导游语种</label> <input type="text" name="lang" id="lang"
					class="form-control" value="<%=guide.getLang()%>"
					style="width: 200px;">
			</div>

			<div class="form-group">
				<label>导游等级</label>
				<c:set var="level" scope="session" value="<%=guide.getLevel()%>" />
				<c:choose>
					<c:when test="${level==1}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1" selected="selected">一级</option>
							<option value="2">二级</option>
							<option value="3">三级</option>
							<option value="4">四级</option>
							<option value="5">五级</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==2}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">一级</option>
							<option value="2" selected="selected">二级</option>
							<option value="3">三级</option>
							<option value="4">四级</option>
							<option value="5">五级</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==3}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">一级</option>
							<option value="2">二级</option>
							<option value="3" selected="selected">三级</option>
							<option value="4">四级</option>
							<option value="5">五级</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==4}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">一级</option>
							<option value="2">二级</option>
							<option value="3">三级</option>
							<option value="4" selected="selected">四级</option>
							<option value="5">五级</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:when test="${level==5}">
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">一级</option>
							<option value="2">二级</option>
							<option value="3">三级</option>
							<option value="4">四级</option>
							<option value="5" selected="selected">五级</option>
							<option value="6">未知</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="level" class="form-control" style="width: 200px;">
							<option value="1">一级</option>
							<option value="2">二级</option>
							<option value="3">三级</option>
							<option value="4">四级</option>
							<option value="5">五级</option>
							<option value="6" selected="selected">未知</option>
						</select>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label>所在机构</label> <input type="text" name="institutions"
					id="institutions" class="form-control"
					value="<%=guide.getInstitutions()%>" style="width: 200px;">
			</div>

			<div class="form-group">
				<label>导游电话</label> <input type="text" name="phone" id="phone"
					class="form-control" value="<%=guide.getPhone()%>" onblur="send()" style="width: 200px;"> <span style="color: red" id="PhoneError_Massage"> </span>
			</div>
			<button type="submit" class="btn btn-primary" name="submit_btn">提交</button>
			<a class="btn btn-primary" href="GuideListServlet">返回</a>
		</form>
	</div>
</div>

<script>
	g_name = document.getElementById('g_name');
	g_name.onblur = function() {
		var isNullReg = /^\s*$/
		if (isNullReg.test(g_name.value)) {
			g_name.setAttribute('placeholder', '禁止为空');
			g_name.setAttribute('class', 'form-control change');
		} else {
			g_name.setAttribute('class', 'form-control');
		}
	};

	lang = document.getElementById('lang');
	lang.onblur = function() {
		var isNullReg = /^\s*$/
		if (isNullReg.test(lang.value)) {
			lang.setAttribute('placeholder', '禁止为空');
			lang.setAttribute('class', 'form-control change');
		} else {
			lang.setAttribute('class', 'form-control');
		}
	};

	institutions = document.getElementById('institutions');
	institutions.onblur = function() {
		var isNullReg = /^\s*$/
		if (isNullReg.test(institutions.value)) {
			institutions.setAttribute('placeholder', '禁止为空');
			institutions.setAttribute('class', 'form-control change');
		} else {
			institutions.setAttribute('class', 'form-control');
		}
	};

	function check() {
		var isNullReg = /^\s*$/
		if (isNullReg.test(g_name.value) == true) {
			g_name.setAttribute('placeholder', '禁止为空');
			g_name.setAttribute('class', 'form-control change');
			return false;
		} else {
			g_name.setAttribute('class', 'form-control');
		}

		if (isNullReg.test(lang.value) == true) {
			lang.setAttribute('placeholder', '禁止为空');
			lang.setAttribute('class', 'form-control change');
			return false;
		} else {
			lang.setAttribute('class', 'form-control');
		}

		if (isNullReg.test(institutions.value) == true) {
			institutions.setAttribute('placeholder', '禁止为空');
			institutions.setAttribute('class', 'form-control change');
			return false
		} else {
			institutions.setAttribute('class', 'form-control');
		}
		return true;
	}
</script>
<%@ include file="/public_html/footer.jsp"%>