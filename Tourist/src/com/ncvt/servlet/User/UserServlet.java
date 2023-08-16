package com.ncvt.servlet.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;
import com.ncvt.entity.UserEntity;
import com.ncvt.server.UserServer;

/**
 * tourist_user的表操作 Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
		String uidStr = request.getParameter("id");
		UserServer userServer = new UserServer();
		int uid = 0;
		String pageName = null;
		if(StringUtils.isNullOrEmpty(uidStr)) {
			System.out.println("uid空，不查询");
		}else {
			uid = Integer.parseInt(uidStr);
			pageName = userServer.queryUserWithId(uid).getUsername();
		}
		
//		System.out.println("pageName:" + pageName);
		String loginType = (String) request.getSession().getAttribute("loginType");
//		System.out.println("loginType:" + loginType);
		String loginUsername = (String) request.getSession().getAttribute("username");
//		System.out.println("loginUsername:" + loginUsername);
		
		if ("admin".equals(loginType)) {
		    System.out.println("登录类型是admin");
		} else {
		    if (pageName.equals(loginUsername)) {
		        System.out.println("当前登录用户和页面上的用户名一致");
		    } else {
		        System.out.println("当前登录用户和页面上的用户名不一致");
		        request.setAttribute("info", "无权访问");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "CityListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		    }
		}

		if ("UserAddView".equals(optFlag)) {
			request.getRequestDispatcher("add/user_add.jsp").forward(request, response);
		}
		if ("UserAdd".equals(optFlag)) {
			UserEntity user = new UserEntity();
			String username = request.getParameter("u_name");
			String password = request.getParameter("u_password");
			user.setUsername(username);
			user.setPassword(password);
			int res = userServer.addUser(user);
			if (res == 1) {
				request.setAttribute("info", "添加成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "UserServlet?flag=UserAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"UserListServlet\">返回用户列表</a>");
			} else {
				request.setAttribute("info", "添加失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "UserServlet?flag=UserAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"UserListServlet\">返回用户列表</a>");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		if ("UserDel".equals(optFlag)) {
			uidStr = request.getParameter("id");
			uid = Integer.parseInt(uidStr);
			int res = userServer.delUser(uid);
			if (res == 1) {
				request.setAttribute("info", "删除成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "UserListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			} else {
				request.setAttribute("info", "删除失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "UserListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}

		if ("UserEditView".equals(optFlag)) {
			uidStr = request.getParameter("id");
			uid = Integer.parseInt(uidStr);
			UserEntity user = userServer.queryUserWithId(uid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("edit/user_edit.jsp").forward(request, response);
		}
		if ("UserEdit".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			String username = request.getParameter("u_name");
			String password = request.getParameter("password");
			UserEntity user = userServer.queryUserWithId(aid);
			user.setUsername(username);
			user.setPassword(password);
			int res = userServer.updateUser(user);
			if (res == 1) {
				request.setAttribute("info", "编辑成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "UserServlet?flag=UserEditView&id=" + aidStr);
				request.setAttribute("back_str", "返回编辑");
				if ("user".equals(loginType)) {
					request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"CityListServlet\">返回</a>");
				} else {
					request.setAttribute("back_list_2","<a class=\"btn btn-primary\" href=\"UserListServlet\">返回用户列表</a>");
				}
			} else {
				request.setAttribute("info", "编辑失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "UserServlet?flag=UserEditView&id=" + aidStr);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"UserListServlet\">返回用户列表</a>");
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
