package _05_Order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.apache.commons.codec.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.OrderBean;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;
import ch.qos.logback.core.joran.conditional.IfAction;

@WebServlet("/C_myOrderDelete")
public class C_myOrderDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		System.out.println(customerService.findAllDeleteOrdersByCustomerId(customerBean.getCustomer_id()));

		Map<String, String> map = new LinkedHashMap<String, String>();
		if (customerService.findAllDeleteOrdersByCustomerId(customerBean.getCustomer_id()).size() != 0) {
			List<OrderBean> list = customerService.findAllDeleteOrdersByCustomerId(customerBean.getCustomer_id());
			OrderBean lastDeleteOrderBean = list.get(0);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd");
			Timestamp orderDateTimestamp = lastDeleteOrderBean.getOrder_date();
			String orderDate= sdf.format(orderDateTimestamp);

			Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
			String nowDate= sdf.format(nowTimestamp);

			if (orderDate.compareTo(nowDate) == 0) {
				// 只有當日被取消的訂單會顯示
				String orderId = lastDeleteOrderBean.getOrder_id();
				map.put("orderId", orderId);
				map.put("orderStatus", "取消");
			}else {
				map.put("orderId", "");
				map.put("orderStatus", "");
			}

		} else {
			map.put("orderId", "");
			map.put("orderStatus", "");
		}

		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
