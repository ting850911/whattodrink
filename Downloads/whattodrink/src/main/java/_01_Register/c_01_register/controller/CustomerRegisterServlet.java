package _01_Register.c_01_register.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CustomerService cs = new CustomerServiceImpl();
		
		String customer_phone = request.getParameter("userID_register");
		String customer_email = request.getParameter("email");
		String customer_password = request.getParameter("password1_register");

//		CustomerBean customerBean = cs.findByCustomerAccount(customer_phone);
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomer_account(customer_phone);
		customerBean.setEmail(customer_email);
		customerBean.setCustomer_password(customer_password);
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		customerBean.setSign_date(now);
		customerBean.setAlter_date(now);
		String allChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String invitation = "";
		while (invitation.length() != 8) {
			invitation += allChars.charAt((int) (Math.random() * allChars.length()));
			if (invitation.length() == 8) {
				CustomerService customerService = new CustomerServiceImpl();
				if (customerService.existsByInvitation(invitation)) {
					invitation = "";
				}
			}
		}
		customerBean.setInvitation(invitation);
		customerBean.setInvitationCount(0);
		String customer_verification = (String) session.getAttribute("customer_verification");
		session.removeAttribute("customer_verification");
		customerBean.setCustomer_verification(customer_verification);
		cs.save(customerBean);
//		updateCustomer(customerBean);
		
		response.getWriter().write("0");
		return;
	}

}
