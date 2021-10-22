package _05_Order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@WebServlet("/C_myurrentOrderNumber")
public class C_myurrentOrderNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(C_myurrentOrderNumber.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("顧客訂單頁面_歷史訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		String newOrdId = request.getParameter("newOrdId");
		OrderService orderService = new OrderServiceImpl();
		OrderBean orderBean = orderService.findById(newOrdId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newOrdId", newOrdId);
		map.put("newOrdTime", orderBean.getScheduled_time());	
		System.out.println("=================================================");
		System.out.println(map);
		System.out.println("=================================================");
		response.getWriter().print(JSON.toJSONString(map));	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
