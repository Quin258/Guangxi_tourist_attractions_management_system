package com.ncvt.servlet.Guide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncvt.entity.GuideEntity;
import com.ncvt.server.GuideServer;

/**
 * Servlet implementation class GuideJudgeServlet
 */
@WebServlet("/GuideJudgeServlet")
public class GuideJudgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuideJudgeServlet() {
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
		String guide_id = request.getParameter("guide_id");
//		System.out.println("guide_id£º"+guide_id);
		String phone = request.getParameter("phone");
//		System.out.println("phone£º"+phone);
		
		GuideServer guideServer = new GuideServer();
		List<GuideEntity> guide = guideServer.queryGuide();
//		System.out.println(guide);
		List<String> guide_idList = new ArrayList<String>();
		for (int i = 0; i < guide.size(); i++) {
			guide_idList.add(guide.get(i).getGuide_id());
		}
//		System.out.println(guide_idList);

		List<String> guidePhoneList = new ArrayList<String>();
		for (int i = 0; i < guide.size(); i++) {
			guidePhoneList.add(guide.get(i).getPhone());
		}

		JSONObject jsonObject = new JSONObject();
		
		if (guide_idList.contains(guide_id)) {
			jsonObject.put("guide_id_judge", true);
		} else {
			jsonObject.put("guide_id_judge", false);
		}

		if (guidePhoneList.contains(phone)) {
			jsonObject.put("phone_judge", true);
		} else {
			jsonObject.put("phone_judge", false);
		}

		String json = jsonObject.toString();
//		System.out.println(json);
		response.getWriter().print(json);
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
