package _01_Register.c_01_register.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;

/**
 * Servlet implementation class C_RegisterUpdateServlet
 */
@WebServlet("/C_RegisterUpdateServlet")
public class C_RegisterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CustomerService cs = new CustomerServiceImpl();
		CustomerBean customerBean = new CustomerBean();
		
		// 註冊測試 抓三表單的值
		String customer_phone = request.getParameter("userID_register");
		String customer_email = request.getParameter("email");
		String customer_password = request.getParameter("password1_register");
		customerBean.setCustomer_account(customer_phone);
		customerBean.setEmail(customer_email);
		customerBean.setCustomer_password(customer_password);
		cs.save(customerBean);
		
		
//		String userPhone_login = request.getParameter("userPhone_login");
//		String password_login = request.getParameter("password_login");
//		customerBean.setCustomer_account(userPhone_login);
//		customerBean.setCustomer_password(password_login);
//		cs.save(customerBean);
		
//		response.sendRedirect("/whattodrink/Success.jsp");
//		return;
	}

}
