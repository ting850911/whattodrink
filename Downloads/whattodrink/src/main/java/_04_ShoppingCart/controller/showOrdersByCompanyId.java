package _04_ShoppingCart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;


@WebServlet("/_04_ShoppingCart/showOrdersByCompanyId")
public class showOrdersByCompanyId extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
//		OrderService orderService = new OrderServiceImpl();
//		List<OrderBean> list = orderService.findByCompanyId("A01");
//		
//		request.setAttribute("getOrdersByCompanyIdTest", list);
//		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//		rd.forward(request, response);
//		return;
		
		
		
	}
}
