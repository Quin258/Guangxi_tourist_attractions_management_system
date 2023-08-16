package com.ncvt.servlet.Guide;

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
@WebServlet("/GuideListServlet")
public class GuideListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuideListServlet() {
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
		String level_str = request.getParameter("level");
		int level = 0;

		if (StringUtils.isNullOrEmpty(pageIndex)) {
			pageIndex = "1";
		}

		if (StringUtils.isNullOrEmpty(find_str)) {
			find_str = "default";
		}

		if (StringUtils.isNullOrEmpty(level_str)) {
			level_str = "0";
		}
		level = Integer.parseInt(level_str);

//		System.out.println("前端页码索引:"+pageIndex);
//		System.out.println("前端页码总数:"+totalPage);
//		System.out.println("前端搜索字符串:"+find_str);
//		System.out.println("前端等级:"+level_str);

		GuideServer guideServer = new GuideServer();
		int totalRow = guideServer.queryGuideCount(find_str, level);

		if (totalRow == 0) {
			request.setAttribute("info", "搜索不到内容");
			request.setAttribute("status", "alert-danger");
			request.setAttribute("btn_type", "btn-danger");
			request.setAttribute("back_list_1", "GuideListServlet");
			request.setAttribute("back_str", "返回");
			request.setAttribute("back_list_2", "");
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		Page page = new Page(Integer.parseInt(pageIndex), totalRow);
//		System.out.println("新页码总数:"+page.getTotalPage());

		if (StringUtils.isNullOrEmpty(totalPage)) {
			page = guideServer.queryGuideList(page, find_str, level);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "搜索不到内容");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("level", level);
			request.setAttribute("totalPage", page.getTotalPage());
			request.setAttribute("pageIndex", page.getPageIndex());
		} else if (Integer.parseInt(totalPage) != page.getTotalPage()) {
			pageIndex = "1";
			page = new Page(Integer.parseInt(pageIndex), totalRow);
			page = guideServer.queryGuideList(page, find_str, level);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "搜索不到内容");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("level", level);
			request.setAttribute("totalPage", page.getTotalPage());
			request.setAttribute("pageIndex", page.getPageIndex());
		} else {
			page = guideServer.queryGuideList(page, find_str, level);
			request.setAttribute("rows", page.getRows());
			if (page.getRows() == null) {
				request.setAttribute("info", "搜索不到内容");
				request.setAttribute("status", "alert-danger");
				request.setAttribute("btn_type", "btn-danger");
				request.setAttribute("back_list_1", "GuideListServlet");
				request.setAttribute("back_str", "返回");
				request.setAttribute("back_list_2", "");
				request.getRequestDispatcher("/info/info.jsp").forward(request, response);
			}
			request.setAttribute("find_str", find_str);
			request.setAttribute("level", level);
			request.setAttribute("totalPage", page.getTotalPage());
			request.setAttribute("pageIndex", page.getPageIndex());
		}

		request.getRequestDispatcher("/list/guide_list.jsp").forward(request, response);

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
