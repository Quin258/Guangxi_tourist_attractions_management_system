package com.ncvt.servlet.Attr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.*;
import com.ncvt.server.*;

/**
 * tourist_attractions的表操作 Servlet implementation class UserServlet
 */
@WebServlet("/AttrServlet")
public class AttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttrServlet() {
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
		AttrServer attrServer = new AttrServer();
		String loginType = (String) request.getSession().getAttribute("loginType");
//		System.out.println(loginType);
		if (!"admin".equals(loginType)) {
			request.setAttribute("info", "无权访问");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_list_1", "AttrListServlet");
			request.setAttribute("back_str", "返回");
			request.setAttribute("back_list_2", "");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}

		if ("AttrAddView".equals(optFlag)) {
			request.getRequestDispatcher("add/attr_add.jsp").forward(request, response);
		}
		if ("AttrAdd".equals(optFlag)) {
			AttrEntity attr = new AttrEntity();
			String name = request.getParameter("name");
			int level = Integer.parseInt(request.getParameter("level"));
			String create_time = request.getParameter("create_time");
			int city_id = Integer.parseInt(request.getParameter("city_id"));
			attr.setName(name);
			attr.setLevel(level);
			attr.setCreate_time(create_time);
			attr.setCity_id(city_id);
			int res = attrServer.addAttr(attr);
			if (res == 1) {
				request.setAttribute("info", "添加成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AttrServlet?flag=AttrAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"AttrListServlet\">返回景区列表</a>");
			} else {
				request.setAttribute("info", "添加失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AttrServlet?flag=AttrAddView");
				request.setAttribute("back_str", "返回添加");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"AttrListServlet\">返回景区列表</a>");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}

		if ("AttrDel".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			int res = attrServer.delAttr(aid);
			if (res == 1) {
				request.setAttribute("info", "删除成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AttrListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			} else {
				request.setAttribute("info", "删除失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AttrListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2","");
			}
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		
		if ("AttrEditView".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			AttrEntity attr = attrServer.queryAttr(aid);
			request.setAttribute("attr", attr);
			request.getRequestDispatcher("edit/attr_edit.jsp").forward(request, response);
		}
		if ("AttrEdit".equals(optFlag)) {
			String aidStr = request.getParameter("id");
			int aid = Integer.parseInt(aidStr);
			String name = request.getParameter("a_name");
			int level = Integer.parseInt(request.getParameter("level"));
			String create_time = request.getParameter("create_time");
			int city_id = Integer.parseInt(request.getParameter("city_id"));
			AttrEntity attr = attrServer.queryAttr(aid);
			attr.setName(name);
			attr.setLevel(level);
			attr.setCreate_time(create_time);
			attr.setCity_id(city_id);
			int res = attrServer.updateAttr(attr);
			if (res == 1) {
				request.setAttribute("info", "编辑成功");
				request.setAttribute("status", "alert-success");
				request.setAttribute("btn_type", "btn-success");
				request.setAttribute("back_list_1", "AttrServlet?flag=AttrEditView&id="+aidStr);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list_2", "<a class=\"btn btn-primary\" href=\"AttrListServlet\">返回景区列表</a>");

			} else {
				request.setAttribute("info", "编辑失败");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AttrServlet?flag=AttrEditView&id="+aidStr);
				request.setAttribute("back_str", "返回编辑");
				request.setAttribute("back_list", "<a class=\"btn btn-primary\" href=\"AttrListServlet\">返回景区列表</a>");
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
