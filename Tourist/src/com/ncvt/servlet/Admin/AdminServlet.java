package com.ncvt.servlet.Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.AdminEntity;
import com.ncvt.server.AdminServer;

/**
 * tourist_admin�ı���� Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		AdminServer adminServer = new AdminServer();

		String loginType = (String) request.getSession().getAttribute("loginType");
		if (!"admin".equals(loginType)) {
			request.setAttribute("info", "��Ȩ����");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_list_1", "AdminttrListServlet");
			request.setAttribute("back_str", "����");
			request.setAttribute("back_list_2", "");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("AdminAddView".equals(optFlag)) {
			request.getRequestDispatcher("add/admin_add.jsp").forward(request, response);
		}
		if ("AdminAdd".equals(optFlag)) {
			AdminEntity admin = new AdminEntity();
			String username = request.getParameter("admin_name");
			String password = request.getParameter("password");
			admin.setUsername(username);
			admin.setPassword(password);
			int res = adminServer.addAdmin(admin);
			if (res == 1) {
				request.setAttribute("info", "��ӳɹ�");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AdminServlet?flag=AdminAddView");
				request.setAttribute("back_str", "�������");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"AdminListServlet\">���ع���Ա�б�</a>");
			} else {
				request.setAttribute("info", "���ʧ��");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AdminServlet?flag=AdminAddView");
				request.setAttribute("back_str", "�������");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"AdminListServlet\">���ع���Ա�б�</a>");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("AdminDel".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			int res = adminServer.delAdmin(aid);
			if (res == 1) {
				request.setAttribute("info", "ɾ���ɹ�");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AdminListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2","");
			} else {
				request.setAttribute("info", "ɾ��ʧ��");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AdminListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2","");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("AdminEditView".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
//			System.out.println(now_username);
			if(!"admin".equals(loginType)) {
				request.setAttribute("info", "��Ȩ����");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AdminListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			AdminEntity admin = adminServer.queryAdminWithId(aid);
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("edit/admin_edit.jsp").forward(request, response);
		}
		if ("AdminEdit".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			String username = request.getParameter("admin_name");
//			System.out.println(username);
			String password = request.getParameter("u_password");
			AdminEntity admin = adminServer.queryAdminWithId(aid);
			admin.setUsername(username);
			admin.setPassword(password);
			int res = adminServer.updateAdmin(admin);
			if (res == 1) {
				request.setAttribute("info", "�༭�ɹ�");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AdminServlet?flag=AdminEditView&id="+aidStr);
				request.setAttribute("back_str", "���ر༭");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"AdminListServlet\">���ع���Ա�б�</a>");
			} else {
				request.setAttribute("info", "�༭ʧ��");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AdminServlet?flag=AdminEditView&id="+aidStr);
				request.setAttribute("back_str", "���ر༭");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"AdminListServlet\">���ع���Ա�б�</a>");
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
