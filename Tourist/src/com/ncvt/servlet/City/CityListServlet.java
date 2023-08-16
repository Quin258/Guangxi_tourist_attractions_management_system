package com.ncvt.servlet.City;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;
import com.ncvt.entity.CityEntity;
import com.ncvt.server.CityServer;

/**
 * Servlet implementation class QueryListServlet
 */
@WebServlet("/CityListServlet")
public class CityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CityListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String order_s = request.getParameter("order");
		String pageIndex = request.getParameter("pageIndex");
		int order = 0;

		if (StringUtils.isNullOrEmpty(pageIndex)) {
			pageIndex = "1";
		}

		if (StringUtils.isNullOrEmpty(order_s)) {
			order_s = "0";
		}
		order = Integer.parseInt(order_s);
//		System.out.println(order);

		CityServer cityServer = new CityServer();
		List<CityEntity> rows = cityServer.queryCity(order);

		request.setAttribute("rows", rows);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/list/city_list.jsp").forward(request, response);

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
