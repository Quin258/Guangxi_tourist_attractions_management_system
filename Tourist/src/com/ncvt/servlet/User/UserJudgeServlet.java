package com.ncvt.servlet.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.UserEntity;
import com.ncvt.server.UserServer;

/**
 * Servlet implementation class UserJudgeServlet
 */
@WebServlet("/UserJudgeServlet")
public class UserJudgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserJudgeServlet() {
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
		String u_name = request.getParameter("u_name");
		UserServer userServer = new UserServer();
		List<UserEntity> user = userServer.queryAllUser();
		List<String> nameList = new ArrayList<String>();
		for(int i = 0;i< user.size();i++) {
			nameList.add(user.get(i).getUsername());
		}
//		System.out.println(nameList);
		if (nameList.contains(u_name)) {
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
