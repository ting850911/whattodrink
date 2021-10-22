package _05_Order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/B_orderHistoryDelete")
public class B_orderHistoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_orderHistoryDelete.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家歷史訂單刪除資料之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		OrderService orderService = new OrderServiceImpl();
		String order_id = request.getParameter("order_id");
		OrderBean orderBean = orderService.findById(order_id);
		System.out.println(order_id);
		orderBean.setOrder_text("商家刪除資料");
		orderService.updateOrderBean(orderBean);
		response.getWriter().print("yes");
	}
}
