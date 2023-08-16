package com.ncvt.servlet.Guide;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.*;
import com.ncvt.server.*;

/**
 * tourist_guide的表操作 Servlet implementation class UserServlet
 */
@WebServlet("/GuideServlet")
public class GuideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuideServlet() {
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
		GuideServer guideServer = new GuideServer();

		String loginType = (String) request.getSession().getAttribute("loginType");
		if (!"admin".equals(loginType)) {
			request.setAttribute("info", "无权访问");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_list_1", "GuideListServlet");
			request.setAttribute("back_str", "返回");
			request.setAttribute("back_list_2", "");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("GuideAddView".equals(optFlag)) {
			request.getRequestDispatcher("add/guide_add.jsp").forward(request, response);
		}
		if ("GuideAdd".equals(optFlag)) {
			GuideEntity guide = new GuideEntity();
			String name = request.getParameter("g_name");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String guide_id = request.getParameter("guide_id");
			String lang = request.getParameter("lang");
			int level = Integer.parseInt(request.getParameter("level"));
			String institutions = request.getParameter("institutions");
			String phone = request.getParameter("phone");
			guide.setName(name);
			guide.setGender(gender);
			guide.setGuide_id(guide_id);
			guide.setLang(lang);
			guide.setLevel(level);
			guide.setInstitutions(institutions);
			guide.setPhone(phone);
			int res = guideServer.addGuide(guide);
			if (res == 1) {
				request.setAttribute("info", "添加成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "GuideServlet?flag=GuideAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"GuideListServlet\">返回导游列表</a>");
			} else {
				request.setAttribute("info", "添加失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideServlet?flag=GuideAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"GuideListServlet\">返回导游列表</a>");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}

		if ("GuideDel".equals(optFlag)) {
			String gidStr = request.getParameter("id");
			int aid = Integer.parseInt(gidStr);
			int res = guideServer.delGuide(aid);
			if (res == 1) {
				request.setAttribute("info", "删除成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "GuideListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			} else {
				request.setAttribute("info", "删除失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("GuideEditView".equals(optFlag)) {
			String gidStr = request.getParameter("id");
			int gid = Integer.parseInt(gidStr);
			GuideEntity guide = guideServer.queryGuide(gid);
			request.setAttribute("guide", guide);
			request.getRequestDispatcher("edit/guide_edit.jsp").forward(request, response);
		}
		if ("GuideEdit".equals(optFlag)) {
			String gidStr = request.getParameter("id");
			int aid = Integer.parseInt(gidStr);
			GuideEntity guide = guideServer.queryGuide(aid);
			String name = request.getParameter("g_name");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String guide_id = request.getParameter("guide_id");
			String lang = request.getParameter("lang");
			int level = Integer.parseInt(request.getParameter("level"));
			String institutions = request.getParameter("institutions");
			String phone = request.getParameter("phone");
			guide.setName(name);
			guide.setGender(gender);
			guide.setGuide_id(guide_id);
			guide.setLang(lang);
			guide.setLevel(level);
			guide.setInstitutions(institutions);
			guide.setPhone(phone);
			int res = guideServer.updateGuide(guide);
			if (res == 1) {
				request.setAttribute("info", "编辑成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "GuideServlet?flag=GuideEditView&id="+gidStr);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"GuideListServlet\">返回导游列表</a>");
			} else {
				request.setAttribute("info", "编辑失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideServlet?flag=GuideEditView&id="+gidStr);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"GuideListServlet\">返回导游列表</a>");
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
