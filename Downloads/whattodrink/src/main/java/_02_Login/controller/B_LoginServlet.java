package _02_Login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _00_init.utils.GlobalService;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@WebServlet("/_02_Login/B_LoginServlet.do")
public class B_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_LoginServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("商家登入功能之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		CompanyService companyService = new CompanyServiceImpl();
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		CompanyBean companyBean = null;
		response.setCharacterEncoding("UTF-8");
		String requestURI = (String) session.getAttribute("requestURI");

		//取得帳密
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		
		//確認帳號
		if(companyService.existsByAccount(username) == true) {
			response.getWriter().println(gson.toJson("yes"));
			//確認密碼並取得companyBean
			if(password != null && password.trim().length() > 0) { 
					companyBean = companyService.findByCompanyAccountAndPassword(username, password);
					if (companyBean != null) {
						response.getWriter().println(gson.toJson("LoginOK"));
						session.setAttribute("BLoginOK", companyBean);
						session.removeAttribute("successEmail");
						log.info("會員登入功能之Controller: 登入成功");
						if(companyBean.getStart_time() != null && companyBean.getEnd_time() != null) {
							if (requestURI != null) {
								requestURI = (requestURI.length() == 0 ? (request.getContextPath() + "/_05_Order/b_05_order/1_business_orders.jsp") : requestURI);
								response.sendRedirect(response.encodeRedirectURL(requestURI));
								return;
							} else {
								response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_05_Order/b_05_order/1_business_orders.jsp"));
								return;
							}
						}else {
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_06_Maintain/b_06_maintain/1_business_company_info.jsp"));
							return;
						}
						
					} else {
						request.setAttribute("LoginError", "密碼錯誤");
						RequestDispatcher rd = request.getRequestDispatcher("/_02_Login/b_02_login/1_business_login.jsp");
						rd.forward(request, response);
						return;
					}
			}
		}
	}

}
