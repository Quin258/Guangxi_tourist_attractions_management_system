package com.ncvt.servlet.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mysql.cj.util.StringUtils;
import com.ncvt.entity.AdminEntity;
import com.ncvt.server.AdminServer;
import com.ncvt.entity.UserEntity;
import com.ncvt.server.UserServer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		System.out.println("LoginServlet()....");

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * µ«¬Ω—È÷§
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginType = request.getParameter("loginType");
//		System.out.println("µ±«∞µ«¬º”√ªß¿‡–Õ£∫" + loginType);
		if ("admin".equals(loginType)) {
			AdminEntity admin = new AdminEntity();
			admin.setUsername(username);
			admin.setPassword(password);
			AdminServer adminServer = new AdminServer();
			AdminEntity dbUser = adminServer.queryAdminWithName(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("loginType", loginType);
			if (dbUser != null && dbUser.getPassword().equals(password) && dbUser.getUsername().equals(username)) {
				request.getRequestDispatcher("CityListServlet").forward(request, response);
			} else {
				request.setAttribute("info", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ,«Î÷ÿ ‘");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else if ("user".equals(loginType)) {
			UserEntity user = new UserEntity();
			user.setUsername(username);
			user.setPassword(password);
			UserServer userServer = new UserServer();
			UserEntity dbUser = userServer.queryUserWithName(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("loginType", loginType);
			if (dbUser != null && dbUser.getPassword().equals(password) && dbUser.getUsername().equals(username)) {
				request.getRequestDispatcher("CityListServlet").forward(request, response);
			} else {
				request.setAttribute("info", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ,«Î÷ÿ ‘");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (StringUtils.isNullOrEmpty(action)) {
			action = "login";
		}

		if ("login".equals(action)) {
			// LoginServlet?action=login
			doLogin(request, response);
		} else if ("logout".equals(action)) {
			request.getSession().setAttribute("username", null);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
