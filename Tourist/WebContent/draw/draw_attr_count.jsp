<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public_html/header.jsp"%>
<%
HashMap<Integer, Integer> maps = (HashMap) request.getAttribute("maps");
%>
<div>
	<div class="bigbox">
		<span><a href="DrawServlet?flag=AttrCount" class="now_box">景区数量</a></span> <span><a
			href="DrawServlet?flag=GuideGender">导游性别数量</a></span> <span><a
			href="DrawServlet?flag=GuideLevel">导游等级</a></span> <span><a
			href="DrawServlet?flag=CityTotal">城市总收入对比</a></span>
		<hr class="bb_hr" />

		<div id="main"
			style="width: 990px; height: 640px; margin: 0px auto; padding: 0 0"></div>
	</div>

	<script type="text/javascript">
		// 注册地图
		echarts.registerMap('Guangxi', Guangxi);
		
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption({
			title : {
				text : '广西壮族自治区旅游景区数量统计可视化',
				x : 'center',
				textStyle : {
					fontSize : 18,
					color : 'black',
				},
			},
			tooltip : {
				//这里设置提示框
				trigger : 'item', //数据项图形触发
				backgroundColor : 'white', //提示框浮层的背景颜色。
				//字符串模板(地图): {a}（系列名称），{b}（区域名称），{c}（合并数值）,{d}（无）
				formatter : '地区：{b}<br/>景区数量：{c}',
			},
			visualMap : {
				//左侧小导航图标
				show : true,
				x : '50px',
				y : '30px',
				textStyle : {
					fontSize : 10,
				},
				splitList : [ {
					start : 1,
					end : 10
				}, {
					start : 11,
					end : 20
				}, {
					start : 21,
					end : 30
				}, {
					start : 31,
					end : 40
				}, {
					start : 41,
					end : 50
				}, {
					start : 51,
					end : 60
				}, {
					start : 61,
					end : 70
				}, {
					start : 71,
					end : 80
				}, {
					start : 81,
					end : 90
				}, {
					start : 91,
					end : 99
				} ],
			},
			series : [ {
				name : '景区数量',
				type : 'map',
				map : 'Guangxi',
				roam : true, //是否开启鼠标缩放和平移漫游
				itemStyle : {
					//地图区域的多边形 图形样式
					normal : {
						//是图形在默认状态下的样式
						label : {
							show : true, //是否显示标签
							textStyle : {
								color : 'black',
							},
						},
					},
					zoom : 1, //地图缩放比例,默认为1
					emphasis : {
						//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
						label : {
							show : true
						},
					},
				},
				top : '10%', //组件距离容器的距离
				data : [ {
					name : '桂林市',
					value : <%=maps.get(1)%>
				}, {
					name : '来宾市',
					value : <%=maps.get(2)%>
				}, {
					name : '贵港市',
					value : <%=maps.get(3)%>
				}, {
					name : '梧州市',
					value : <%=maps.get(4)%>
				}, {
					name : '防城港市',
					value : <%=maps.get(5)%>
				}, {
					name : '柳州市',
					value : <%=maps.get(6)%>
				}, {
					name : '百色市',
					value : <%=maps.get(7)%>
				}, {
					name : '北海市',
					value : <%=maps.get(8)%>
				}, {
					name : '钦州市',
					value : <%=maps.get(9)%>
				}, {
					name : '河池市',
					value : <%=maps.get(10)%>
				}, {
					name : '南宁市',
					value : <%=maps.get(11)%>
				}, {
					name : '崇左市',
					value : <%=maps.get(12)%>
				}, {
					name : '玉林市',
					value : <%=maps.get(13)%>
				}, {
					name : '贺州市',
					value : <%=maps.get(14)%>
				} ],
			}, ],
		});
	</script>
</div>
<%@ include file="/public_html/footer.jsp"%>
