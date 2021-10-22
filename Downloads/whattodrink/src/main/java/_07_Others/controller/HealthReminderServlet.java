package _07_Others.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;

@WebServlet("/HealthReminderServlet")
public class HealthReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String contextPath = request.getContextPath();
		// 使用逾時
		if (session == null) {      
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/_00_Index/index.jsp"));
			return;
		}
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
		ItemService itemService = new ItemServiceImpl();
		String healthReminder = itemService.getHealthReminderByCustomerId(customerBean.getCustomer_id());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(healthReminder);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
