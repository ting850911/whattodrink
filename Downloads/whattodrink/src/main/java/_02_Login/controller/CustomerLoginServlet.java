package _02_Login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/indexItem")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String requestURI = (String) session.getAttribute("requestURI");
		
		String userPhone_login = request.getParameter("userPhone_login");
		String password_login = request.getParameter("password_login");
		
		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = customerService.findByCustomerAccountAndPassword(userPhone_login, password_login);
		if(customerBean == null) {
			request.setAttribute("Error", "手機號碼或密碼錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/_01_Register/c_01_register/LoginRegister.jsp");
			rd.forward(request, response);
			return;
		}else{
//			response.sendRedirect("/whattodrink/" + requestURI);
			
			response.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + ";HttpOnly;Secure;SameSite=None");
			session.setAttribute("CLoginOK", customerBean);
			response.sendRedirect(request.getContextPath() + "/_00_Index/index.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
