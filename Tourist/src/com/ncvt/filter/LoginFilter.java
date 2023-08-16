package com.ncvt.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	private String sessionKey;
	private String redirectUrl;
	private String uncheckedUrls;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
//	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getServletPath();
		List<String> urls = Arrays.asList(uncheckedUrls.split(","));
		System.out.println(uri);
		if (urls.contains(uri)) {
			chain.doFilter(req, resp);
		} else {
			Object user = req.getSession().getAttribute("username");
			if (user == null) {
				req.setAttribute("info", "未登录，请先登录");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
//				resp.sendRedirect(req.getContextPath() + File.separator + redirectUrl);
			} else {
				chain.doFilter(req, resp);
				
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
//	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext = fConfig.getServletContext();
		// 获取XML文件中配置参数
		sessionKey = servletContext.getInitParameter("userSessionKey");
		// System.out.println("sessionKey======" + sessionKey);//调试用
		redirectUrl = servletContext.getInitParameter("redirectPage");
		// System.out.println("redirectPage======" + redirectUrl);
		uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
		// System.out.println("uncheckedUrls=====" + uncheckedUrls);
	}

}
