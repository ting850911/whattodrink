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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/B_orderHistoryItemDetail")
public class B_orderHistoryItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_orderHistoryItemDetail.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家歷史訂單顯示訂單明細之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrderService orderService = new OrderServiceImpl();
		ItemService itemService = new ItemServiceImpl();
		DrinkService drinkService = new DrinkServiceImpl();
		String order_id = request.getParameter("order_id");
		OrderBean orderBean = orderService.findById(order_id);
//		List<ItemBean> items = itemService.findByOrderId(orderBean.getOrder_id());
		List<ItemBean> items = itemService.findByOrderId(order_id);
		System.out.println("order_id:" + order_id);
		System.out.println("items:" + items);
		List<Map<String, Object>> list = new LinkedList<>();
		Map<String, Object> useDiscountMap = new HashMap<>();
//		Map<String, Object> lengthMap = new HashMap<>();
//		lengthMap.put("length", items.size());
//		list.add(lengthMap);
		for(ItemBean b : items) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("product_name", b.getDrinkBean().getProduct_name());
			map.put("capacity", b.getDrinkBean().getCapacity());
			map.put("temp_level", b.getTempLevelBean().getTemp_level());
			map.put("sugar_level", b.getSugarLevelBean().getSugar_level());
			map.put("toppings", drinkService.findToppingNamesByItemBean(b));
			map.put("quantity", b.getQuantity());
			map.put("price", b.getPrice());
			map.put("message", b.getMessage());
			map.put("note", b.getNote());
			list.add(map);
		}
		useDiscountMap.put("useDiscount", (orderBean.getInvitationDiscount().equals("已折扣50元") ? "已折扣50元" : "無折扣"));
		list.add(useDiscountMap);
		
		response.getWriter().print(JSON.toJSONString(list));
	}
	
	
	//[{length=2}, 表示這筆訂單有幾筆細項
	// {product_name=珍珠伯爵紅茶拿鐵, capacity=L, temp_level=正常冰, sugar_level=無糖, toppings=[仙草], quantity=4, price=400, message=, note=}, 
	// {product_name=珍珠伯爵紅茶拿鐵, capacity=L, temp_level=正常冰, sugar_level=無糖, toppings=[綠茶凍], quantity=3, price=285, message=, note=}, 
	// {useDiscount=已折扣50元}] 有使用折扣顯示: 已折扣50元; 無使用折扣顯示: 無折扣

}
