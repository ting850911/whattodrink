package _05_Order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@WebServlet("/C_myOrderHistory")
public class C_myOrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(C_myOrderHistory.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("顧客訂單頁面_歷史訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		List<Map<String, Object>> list = customerService
				.findAllHistoryOrdersByCustomerId(customerBean.getCustomer_id());

		CommentService commentService = new CommentServiceImpl();
		for (Map<String, Object> map : list) {
			String ordId = (String) map.get("ordId");
			if (commentService.findCommentBeansByOrderId(ordId).size() != 0) {
				map.put("review", "已評價");
				list.set(list.size() - 1, map);
			} else {
				map.put("review", "去評價");
				list.set(list.size() - 1, map);
			}
		}
		System.out.println("=================================================");
		System.out.println(list);
		System.out.println("=================================================");
		response.getWriter().print(JSON.toJSONString(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
