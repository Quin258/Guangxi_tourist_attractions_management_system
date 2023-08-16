package com.ncvt.servlet.Draw;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ncvt.server.DrawServer;

/**
 * Servlet implementation class QueryListServlet
 */
@WebServlet("/DrawServlet")
public class DrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// String pageIndex = "1";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DrawServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap map = null;
		request.setCharacterEncoding("utf-8");
		String optFlag = request.getParameter("flag");
		DrawServer drawServer = new DrawServer();

		if ("AttrCount".equals(optFlag) || "Default".equals(optFlag)) {
			map = drawServer.queryAttrCount();
			request.setAttribute("maps", map);
			request.getRequestDispatcher("/draw/draw_attr_count.jsp").forward(request, response);
		}

		else if ("GuideGender".equals(optFlag)) {
			map = drawServer.queryGuideGender();
			request.setAttribute("maps", map);
			request.getRequestDispatcher("/draw/draw_guide_gender.jsp").forward(request, response);
		}

		else if ("GuideLevel".equals(optFlag)) {
			map = drawServer.queryGuideLevel();
			request.setAttribute("maps", map);
			request.getRequestDispatcher("/draw/draw_guide_level.jsp").forward(request, response);
		}

		else if ("CityTotal".equals(optFlag)) {
			map = drawServer.queryCityTotal();
			request.setAttribute("maps", map);
			request.getRequestDispatcher("/draw/draw_city_total.jsp").forward(request, response);
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
