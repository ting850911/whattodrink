package _02_Login.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.utils.CreateVerificationCode;
import _00_init.utils.SendingEmail;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerService customerService = new CustomerServiceImpl();
		response.setCharacterEncoding("UTF-8");
		int step = Integer.parseInt(request.getParameter("step"));
		if (step == 1) {
			String email = request.getParameter("email");
			if (customerService.existsByCustomerEmail(email)) {
				CustomerBean customerBean = customerService.findByEmail(email);
				session.setAttribute("customerForgetPassword", customerBean);
				response.getWriter().write("0");
				return;
			} else {
				response.getWriter().write("1");
				return;
			}
		}
		
		if (step == 2) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerForgetPassword");
			String newVerificationCode = CreateVerificationCode.getVerificationCode();
			
			System.out.println(newVerificationCode);
			customerBean.setCustomer_verification(newVerificationCode);
			customerService.updateCustomer(customerBean);
			SendingEmail.SendVerificationCodeTo(customerBean.getEmail(), newVerificationCode);
			return;
		}
		
		if (step == 3) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerForgetPassword");
			String verificationCode = request.getParameter("code");
			if (customerService.existsByEmailAndVerificationCode(customerBean.getEmail(), verificationCode)) {
				response.sendRedirect(request.getContextPath() + "/_02_Login/c_02_login/Loginforgetpassword2.jsp");
				return;
			} else {
				request.setAttribute("errorCode", "驗證碼輸入錯誤");
				request.getRequestDispatcher("/_02_Login/c_02_login/Loginforgetpassword.jsp")
				.forward(request, response);
				return;
			}
		}
		
		if (step == 4) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerForgetPassword");
			String newPassword = request.getParameter("password");
			customerBean.setCustomer_password(newPassword);
			customerBean.setAlter_date(new Timestamp(System.currentTimeMillis()));
			customerService.updateCustomer(customerBean);
			session.removeAttribute("beanForgetPassword");
			response.sendRedirect(request.getContextPath() + "/_02_Login/c_02_login/Loginforgetpassword3.jsp");
			return;
		}
	}
}
