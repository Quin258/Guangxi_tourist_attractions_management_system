<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
CityEntity city = (CityEntity) request.getAttribute("city");
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
<div class="panel panel-default"
	style="margin: 0px auto; margin-bottom: 20px; width: 600px; height: auto">
	<div class="panel-heading">
		<h3 class="panel-title">城市添加</h3>
	</div>
	<div class="panel-body">
		<!--写一个表单-->
		<form method="post"
			action="CityServlet?flag=CityEdit&cid=<%=city.getId()%>"  onsubmit="return check()">
			<div class="form-group">
				<label>城市名称</label> <input type="text" name="name"
					class="form-control" value="<%=city.getName()%>"
					style="width: 200px;" disabled="disabled">
			</div>
			<div class="form-group">
				<label>总收入*</label> <input type="number" name="Total_revenue" required="required"
				 min="0.1" max="9999999999" step="0.0000001"
					id="Total_revenue" class="form-control" placeholder="总收入"
					style="width: 200px;" value="<%=city.getTotal_revenue()%>">
			</div>
			<button type="submit" class="btn btn-primary">提交</button>
			<a class="btn btn-primary" href="CityListServlet">返回</a>
		</form>
	</div>
</div>
<script>

	
	function check() {
		var reg = /^(\d+|\d+\.\d+)$/;
		if (reg.test(Total_revenue.value) == false) {
			Total_revenue.setAttribute('placeholder', '必填,且仅能小数点和数字');
			Total_revenue.setAttribute('class', 'form-control change');
			return false;
		}else{
			Total_revenue.setAttribute('class', 'form-control');
		}
		
		return true;
	}
</script>
<%@ include file="/public_html/footer.jsp"%>