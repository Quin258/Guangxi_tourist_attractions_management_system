<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
HashMap<Integer, Integer> maps = null;
maps = (HashMap) request.getAttribute("maps");
%>
<div>
	<div class="bigbox">
		<span><a href="DrawServlet?flag=AttrCount">景区数量</a></span>
		<span><a href="DrawServlet?flag=GuideGender">导游性别数量</a></span>
		<span><a href="DrawServlet?flag=GuideLevel" class="now_box">导游等级</a></span>
		<span><a href="DrawServlet?flag=CityTotal">城市总收入对比</a></span>
		<hr class="bb_hr" />

		<div
			id="main"
			style="width: 990px; height: 640px; margin: 0px auto; padding: 0 0"
		></div>
	</div>

	<script type="text/javascript">
		var sources = [];
		sources[0] = ["初级",<%=maps.get(1) %>];
		sources[1] = ["中级",<%=maps.get(2) %>];
		sources[2] = ["高级",<%=maps.get(3) %>];
		sources[3] = ["未知",<%=maps.get(6) %>];
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
			color: ['rgb(50,153,219)'],
			title: {
				left: 'center',
				textStyle: { color: 'black' },
				text: '导游等级',
			},
			tooltip: {
				trigger: 'axis',
			},
			legend: {
				left: '250px',
				textStyle: { color: 'black' },
			},
			dataset: [
				{
					dimensions: ['等级', '人数'],
					source: sources,
				},
				{
					transform: {
						type: 'sort',
						config: { dimension: '人数', order: 'desc' },
					},
				},
			],
			xAxis: {
				type: 'category',
		              axisLabel:{show:true, color: "black",
		                    fontSize: 20},
				axisLine: { lineStyle: { color: 'black' } },
			},
			yAxis: {
				axisLine: { lineStyle: { color: 'black' } },
		              splitLine: {
					lineStyle: {
						// 使用深浅的间隔色
						color: ['#aaa', '#ddd']
					}
				}
			},
			series: {
				type: 'bar',
				name: '人数',
				datasetIndex: 1,
		              showBackground: true,
				backgroundStyle: {
					color: 'rgba(180, 180, 180, 0.2)'
				  },
			},
		};

		myChart.setOption(option);
	</script>
</div>
<!-- 以上部分为主代码,下面为引用页脚 -->
<%@ include file="/public_html/footer.jsp"%>
