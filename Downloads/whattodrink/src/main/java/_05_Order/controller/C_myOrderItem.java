package _05_Order.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/C_myOrderItem")
public class C_myOrderItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(C_myOrderItem.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("顧客訂單頁面_目前訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
//		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		String order_id = request.getParameter("receipt"); // 取得該筆訂單編號

		OrderService orderService = new OrderServiceImpl();
		OrderBean orderBean = orderService.findById(order_id);

		Map<String, Object> map1 = new HashMap<String, Object>();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		String datesString = sdf.format(orderBean.getOrder_date());
		map1.put("ordTime", datesString);
		list.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("total", orderBean.getOrder_total());
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("payment", orderBean.getPayment());
		list.add(map3);

		ItemService itemService = new ItemServiceImpl();
		List<ItemBean> itemBeans = itemService.findByOrderId(order_id);
		for (ItemBean itemBean : itemBeans) {
			Map<String, Object> mapFuther = new HashMap<String, Object>();
			mapFuther.put("itemName", itemBean.getDrinkBean().getProduct_name());
			mapFuther.put("capacity", itemBean.getCapacity());
			mapFuther.put("sugar", itemBean.getSugarLevelBean().getSugar_level());
			mapFuther.put("temp", itemBean.getTempLevelBean().getTemp_level());
			mapFuther.put("quantity", itemBean.getQuantity());
			mapFuther.put("sticker", itemBean.getMessage());
			mapFuther.put("note", itemBean.getNote());
			list.add(mapFuther);
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
