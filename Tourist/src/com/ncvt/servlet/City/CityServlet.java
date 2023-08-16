package com.ncvt.servlet.City;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.CityEntity;
import com.ncvt.server.CityServer;

/**
 * tourist_city的表操作 Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String optFlag = request.getParameter("flag");
		String cidStr = request.getParameter("cid");
		int cid = Integer.parseInt(cidStr);
		CityServer cityServer = new CityServer();
		
		String loginType = (String) request.getSession().getAttribute("loginType");
		if (!"admin".equals(loginType)) {
			request.setAttribute("info", "无权访问");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_str", "返回");
			request.setAttribute("back_list","");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("CityEditView".equals(optFlag)) {
			CityEntity city = cityServer.queryCityId(cid);
			request.setAttribute("city", city);
			request.getRequestDispatcher("/edit/city_edit.jsp").forward(request, response);
		}
		if ("CityEdit".equals(optFlag)) {
			String name = request.getParameter("name");
			float Total_revenue = Float.parseFloat(request.getParameter("Total_revenue"));
			CityEntity city = cityServer.queryCityId(cid);
			city.setId(cid);
			city.setName(name);
			city.setTotal_revenue(Total_revenue);
			int res = cityServer.updateCity(city);
			if (res == 1) {
				request.setAttribute("info", "编辑成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "CityServlet?flag=CityEditView&cid="+cid);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"CityListServlet\">返回城市列表</a>");
			} else {
				request.setAttribute("info", "编辑失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "CityServlet?flag=CityEditView&cid="+cid);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"CityListServlet\">返回城市列表</a>");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
