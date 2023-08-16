package com.ncvt.servlet.Attr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;
import com.ncvt.server.*;
import com.ncvt.utils.Page;

/**
 * Servlet implementation class QueryListServlet
 */
@WebServlet("/AttrListServlet")
public class AttrListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// String pageIndex = "1";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttrListServlet() {
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
		String pageIndex = request.getParameter("pageIndex");
		String totalPage = request.getParameter("totalPage");
		String find_str = request.getParameter("find");
//		System.out.println(find_str);
		String order_s = request.getParameter("order");
		int order = 0;

		if (StringUtils.isNullOrEmpty(pageIndex)) {
			pageIndex = "1";
		}

		if (StringUtils.isNullOrEmpty(find_str)) {
			find_str = "default";
		}
		if (StringUtils.isNullOrEmpty(order_s)) {
			order_s = "0";
		}
		order = Integer.parseInt(order_s);

//		System.out.println("ǰ��ҳ������:" + pageIndex);
//		System.out.println("ǰ��ҳ������:" + totalPage);
//		System.out.println("ǰ�������ַ���:" + find_str);
//		System.out.println("����ȼ�:" + order);

		AttrServer attrServer = new AttrServer();
		int totalRow = attrServer.queryAttrCount(find_str, order);

		if (totalRow == 0) {
			request.setAttribute("info", "������������");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_list_1", "AttrListServlet");
			request.setAttribute("back_str", "����");
			request.setAttribute("back_list_2", "");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		Page page = new Page(Integer.parseInt(pageIndex), totalRow);

		if (StringUtils.isNullOrEmpty(totalPage)) {
			page = attrServer.queryAttrList(page, find_str, order);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "������������");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AtrListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("order", order);
			request.setAttribute("pageIndex", page.getPageIndex());
			request.setAttribute("totalPage", page.getTotalPage());
		} else if (Integer.parseInt(totalPage) != page.getTotalPage()) {
			pageIndex = "1";
			page = new Page(Integer.parseInt(pageIndex), totalRow);
			page = attrServer.queryAttrList(page, find_str, order);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "������������");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AtrListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("order", order);
			request.setAttribute("pageIndex", page.getPageIndex());
			request.setAttribute("totalPage", page.getTotalPage());
		} else {
			page = attrServer.queryAttrList(page, find_str, order);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "������������");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "AtrListServlet");
				request.setAttribute("back_str", "����");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("order", order);
			request.setAttribute("pageIndex", page.getPageIndex());
			request.setAttribute("totalPage", page.getTotalPage());
		}

		request.getRequestDispatcher("/list/attr_list.jsp").forward(request, response);

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
