<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
HashMap<Integer, Integer> maps = null;
maps = (HashMap) request.getAttribute("maps");
%>
<div>
	<div class="bigbox">
		<span><a href="DrawServlet?flag=AttrCount">景区数量</a></span> <span><a
			href="DrawServlet?flag=GuideGender" class="now_box">导游性别数量</a></span> <span><a
			href="DrawServlet?flag=GuideLevel">导游等级</a></span> <span><a
			href="DrawServlet?flag=CityTotal">城市总收入对比</a></span>
		<hr class="bb_hr" />

		<div id="main"
			style="width: 990px; height: 640px; margin: 0px auto; padding: 0 0"></div>
	</div>

	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));
		option = {
			title : {
				text : '广西壮族自治区登记在案导游性别数量',
				left : 'center',
			},
			tooltip : {
				trigger : 'item',
				formatter : '性别：{b}<br/>人数：{c}人'
			},
			legend : {
				x : '50px',
				textStyle : {
					fontSize : 10,
				},
				orient : 'vertical',
				left : 'left',
			},
			series : [ {
				name : '性别',
				type : 'pie',
				radius : '50%',
				data : [ {
					value :
	<%=maps.get(1)%>
		,
					name : '男性'
				}, {
					value :
	<%=maps.get(2)%>
		,
					name : '女性'
				}, {
					value :
	<%=maps.get(3)%>
		,
					name : '未知'
				} ],
				emphasis : {
					itemStyle : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)',
					},
				},
			}, ],
		};
		myChart.setOption(option)
	</script>
</div>
<%@ include file="/public_html/footer.jsp"%>
