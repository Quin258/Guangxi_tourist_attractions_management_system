package com.ncvt.servlet.Attr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.AttrEntity;
import com.ncvt.server.AttrServer;

/**
 * Servlet implementation class AttrJudgeServlet
 */
@WebServlet("/AttrJudgeServlet")
public class AttrJudgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttrJudgeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String a_name = request.getParameter("a_name");
		AttrServer attrServer = new AttrServer();
		List<AttrEntity> attr = attrServer.queryAttr();
		List<String> attrList = new ArrayList<String>(); // 景区名列表
		for(int i = 0;i< attr.size();i++) {
			attrList.add(attr.get(i).getName());
		}
//		System.out.println(cityList);
		if (attrList.contains(a_name)) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
