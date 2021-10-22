package _05_Order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/B_item")
public class B_item extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_item.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家未處理訂單之訂單明細頁面之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		CompanyService companyService = new CompanyServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		CompanyBean bean = (CompanyBean) session.getAttribute("BLoginOK");
		List<Map<String, Object>> itemList = new LinkedList<Map<String, Object>>();
//		Map<String, Object> lengthMap = new HashMap<>();
		List<String> iTBeanList = new LinkedList<>();

		//頁面多加欄位"數量"
		List<Map<String, Object>> orderList = companyService.findAllUnfinishedOrdersByCompanyId(bean.getCompany_id());
		int orderLength = (int) orderList.get(0).get("length");
//		int length = 0;
		for(int i = 1; i <= orderLength; i++) {
			String order_id = (String) orderList.get(i).get("order_id");
			Set<ItemBean> itemBean = orderService.findByOrderId(order_id).getItems();
			for(ItemBean b : itemBean) {
				Map<String, Object> map = new LinkedHashMap<>();
				if(!b.getItemStatus().equals("已完成")) {
					iTBeanList = new LinkedList<>();
//					length += 1;
					map.put("order_id", order_id);
					map.put("item_id", b.getItem_id());
					map.put("product_name", b.getDrinkBean().getProduct_name());
					map.put("capacity", b.getDrinkBean().getCapacity());
					map.put("temp_level", b.getTempLevelBean().getTemp_level());
					map.put("sugar_level", b.getSugarLevelBean().getSugar_level());
					for(ItemToppingBean iTBean : b.getItemToppings()) {
						iTBeanList.add(iTBean.getToppingBean().getTopping_name());
					}
					map.put("toppings", iTBeanList);
					map.put("quantity", b.getQuantity());
					map.put("itemStatus", b.getItemStatus());
					map.put("message", b.getMessage());
					map.put("note", b.getNote());
					itemList.add(map);
				}
			}
//			if(i==orderLength) {
//				lengthMap.put("length", length);
//				itemList.add(lengthMap);
//			}
		}
		System.out.println(itemList);
		response.getWriter().print(JSON.toJSONString(itemList));
		//		doGet(request, response);
		
		
		
		//[{order_id=B0120210905001, item_id: 85, product_name=蘭葉那堤, capacity=M, temp_level=去冰, sugar_level=全糖, toppings=[手工嫩仙草, 愛玉, 布丁], quantity=2, itemStatus=待製作, message=嘗鮮,note=}, 
		// {order_id=B0120210905002, item_id: 87, product_name=蔗香煙燻烏梅, capacity=L, temp_level=微冰, sugar_level=半糖, toppings=[手工嫩仙草, 黑波霸, 布丁], quantity=4, itemStatus=待製作, message=,note=}, 
		// {length=2}]
		
		
		
//		$("#Bitems").click(function(){
//			$.ajax({
//				url: "http://localhost:8080/whattodrink/B_item",
//				type: "POST",
//				dataType:"json",
//				async: false,
//				success(res){
//					console.log(res);
//					console.log(res.length);
//					console.log(res[res.length -2].order_id);
//					console.log(res[res.length -2].toppings.length);
//					console.log(res[res.length -2].toppings[0]);
//				}
//			})
//		})
		
		
	}

}
