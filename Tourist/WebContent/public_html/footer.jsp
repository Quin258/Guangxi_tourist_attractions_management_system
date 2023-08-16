<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		<div class="footer">
			<div class="Llogo"></div>

			<div class="footer_text">
				<ul>
					<li>
						<span
							href="#"
							style="color: white; font-weight: 600; font-size: 22px"
							>城市</span
						>
					</li>
					<li><a href="CityListServlet">城市列表</a></li>
					<li><a href="#">XXXX</a></li>
					<li><a href="#">XXXX</a></li>
					<li><a href="#">XXXX</a></li>
				</ul>
				<ul>
					<li>
						<span href="#" style="color: white; font-weight: 600; font-size: 22px">景点</span>
					</li>
					<li><a href="AttrListServlet">景点列表</a></li>
					<li><a href="AttrServlet?flag=AttrAddView">添加景点</a></li>
					<li><a href="#">XXXX</a></li>
					<li><a href="#">XXXX</a></li>
				</ul>
				<ul>
					<li>
						<span href="#" style="color: white; font-weight: 600; font-size: 22px">导游</span>
					</li>
					<li><a href="GuideListServlet">导游列表</a></li>
					<li><a href="GuideServlet?flag=GuideAddView">添加导游</a></li>
					<li><a href="#">XXXX</a></li>
					<li><a href="#">XXXX</a></li>
				</ul>
				<ul style="border-right: none">
					<li>
						<span
							href="#"
							style="color: white; font-weight: 600; font-size: 22px"
							>可视化</span
						>
					</li>
					<li><a href="DrawServlet?flag=AttrCount">景区数量</a></li>
					<li><a href="DrawServlet?flag=GuideGender">导游性别</a></li>
					<li><a href="DrawServlet?flag=GuideLevel">导游等级</a></li>
					<li><a href="DrawServlet?flag=CityTotal">城市收入</a></li>
				</ul>
			</div>
			<div class="person">
				<span>版权所有:廖安杰 冯孔谊</span><br />
				<span>联系方式:232209008@qq.com</span>
				<br />
				<span>联系地址:广西南宁市大学西路169号</span>
			</div>
		</div>
		
		
		<script src="static/plugins/bootstrap-3.4.1/dist/js/bootstrap.min.js"></script>
	</body>
</html>