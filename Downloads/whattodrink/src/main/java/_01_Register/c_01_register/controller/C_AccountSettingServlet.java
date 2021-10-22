package _01_Register.c_01_register.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

//import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;


@WebServlet("/C_AccountSettingServlet")
public class C_AccountSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session = null;
	CustomerService customerService = new CustomerServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contextPath = request.getContextPath();
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		// 使用逾時
		if (session == null) {      
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/_00_Index/index.jsp"));
			return;
		}
		// 取出session物件內的CustomerBean物件
		CustomerBean customerBean = (CustomerBean)session.getAttribute("CLoginOK");
		if (customerBean == null) {
			// 如果找不到CustomerBean物件，沒有必要往下執行->導向會員登入頁面
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/_01_Register/c_01_register/LoginRegister.jsp"));			
			return;
		}
		String customer_name = request.getParameter("name");
//		String birthday = request.getParameter("birthday");		
		String temp = request.getParameter("birthday");
		Date birthday = Date.valueOf(temp);
//		Timestamp birthday = new Timestamp(date.getTime()); //Bean改成Date (原本是Timestamp)
		
		Double weight = Double.parseDouble(request.getParameter("weight"));
		String customer_address = request.getParameter("address");
		String email = request.getParameter("email");		
		
		
		customerBean.setCustomer_name(customer_name);
		customerBean.setBirthday(birthday);
		customerBean.setWeight(weight);
		customerBean.setCustomer_address(customer_address);
		customerBean.setEmail(email);
		customerService.updateCustomer(customerBean);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/_07_Others/c__07_others_acount/myAccount.jsp");
		rd.forward(request, response);
	}

}
