package com.ncvt.servlet.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.AdminEntity;
import com.ncvt.server.AdminServer;

/**
 * Servlet implementation class AdminJudgeServlet
 */
@WebServlet("/AdminJudgeServlet")
public class AdminJudgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminJudgeServlet() {
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
		String admin_name = request.getParameter("admin_name");
		AdminServer adminServer = new AdminServer();
		List<AdminEntity> admin = adminServer.queryAllAdmin();
		List<String> nameList = new ArrayList<String>();
		for(int i = 0;i< admin.size();i++) {
			nameList.add(admin.get(i).getUsername());
		}
//		System.out.println(nameList);
		if (nameList.contains(admin_name)) {
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
