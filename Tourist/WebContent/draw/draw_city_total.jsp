<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
HashMap<Integer, Float> maps = null;
maps = (HashMap) request.getAttribute("maps");
%>
<div>
	<div class="bigbox">
		<span><a href="DrawServlet?flag=AttrCount">景区数量</a></span> <span><a
			href="DrawServlet?flag=GuideGender">导游性别数量</a></span> <span><a
			href="DrawServlet?flag=GuideLevel">导游等级</a></span> <span><a
			href="DrawServlet?flag=CityTotal" class="now_box">城市总收入对比</a></span>
		<hr class="bb_hr" />

		<div id="main"
			style="width: 990px; height: 640px; margin: 0px auto; padding: 0 0">
		</div>
		
	</div>

	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));
		var sources = [
			['桂林市',<%=maps.get(1)%>],
			['来宾市',<%=maps.get(2)%>],
			['贵港市',<%=maps.get(3)%>],
			['梧州市',<%=maps.get(4)%>],
			['防城港市',<%=maps.get(5)%>],
			['柳州市',<%=maps.get(6)%>],
			['百色市',<%=maps.get(7)%>],
			['北海市',<%=maps.get(8)%>],
			['钦州市',<%=maps.get(9)%>],
			['河池市',<%=maps.get(10)%>],
			['南宁市',<%=maps.get(11)%>],
			['崇左市',<%=maps.get(12)%>],
			['玉林市',<%=maps.get(13)%>],
			['贺州市',<%=maps.get(14)%>]
			]
			console.log(sources)
		var option = {
			color: ['rgb(50,153,219)'],
			title: {
				left: 'center',
				textStyle: { color: 'black' },
				text: '广西壮族自治区城市旅游总收入排行',
				subtext: "单位:亿元"
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
					dimensions: ['城市', '总收入'],
					source: sources,
				},
				{
					transform: {
						type: 'sort',
						config: { dimension: '总收入', order: 'desc' },
					},
				},
			],
			xAxis: {
				type: 'category',
				axisLabel: { show: true, color: 'black', fontSize: 20 ,interval: 0, rotate: 30},
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
				name: '总收入',
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
