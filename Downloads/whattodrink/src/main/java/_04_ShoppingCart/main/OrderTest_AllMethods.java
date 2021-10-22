package _04_ShoppingCart.main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;
import edu.emory.mathcs.backport.java.util.Collections;

public class OrderTest_AllMethods {

	public static void main(String[] args) {

//SaveOrder
		CompanyService companyService = new CompanyServiceImpl();
//		OrderService orderService = new OrderServiceImpl();
//		
//		
//		String 	      order_id 	      = orderService.getOrderIdByCompanyName("五十嵐");
//		Timestamp     order_date 	  = new Timestamp(System.currentTimeMillis());
//		Timestamp 	  pickup_date 	  = null;
//		String 		  scheduled_time  = "20210812 09:10:10";
//		Integer 	  order_quantity  = 5;
//		Integer 	  order_total 	  = 500;
//		String 		  payment 		  = "現金支付";
//		String 		  orderStatus 	  = "確認訂單中";
//		String 		  company_id      = companyService.getCompanyId("五十嵐");
//		Integer 	  customer_id     = null;
//		String 		  receipt		  = null;
//		String 		  order_text      = null;
//		CustomerBean  customerBean    = null;
//		CompanyBean   companyBean     = companyService.findById(company_id);

//建構子		
//		OrderBean orderBean = new OrderBean(order_id, customer_id, order_date, pickup_date, scheduled_time,company_id, order_quantity, order_total, payment, orderStatus, receipt, order_text);

//包含bean建構子		
//		OrderBean orderBean = new OrderBean(order_id, order_date, pickup_date, order_quantity, order_total, payment, orderStatus, receipt, order_text, customerBean, companyBean);

//		orderBean.setItems(null);
//		orderBean.setCompanyBean(companyBean);
//		orderBean.setComments(null);
//		orderBean.setCustomerBean(null);

//		orderService.save(orderBean);

//DeleteByOrderId
//		DrinkService orderService = new OrderServiceImpl();
//		orderService.deleteOrderById("A0120210810007");

//FindOrderBeanByCompanyBean => null (因為存Order時沒存CompanyBean，只存companyId，就無法用CompanyBean 去get多方的訂單資訊 companyBean.getOrders)
//		CompanyService companyService = new CompanyServiceImpl();
//		CompanyBean cb = companyService.findById("A01");
//		Set<OrderBean> list = cb.getOrders();
//		for(OrderBean ob : list) {
//			System.out.println(ob);
//		}

//FindOrderByCompanyId
//		DrinkService orderService = new OrderServiceImpl();
//		List<OrderBean> list = orderService.findByCompanyId("A01");
//		for(OrderBean ob : list) {
//			System.out.println(ob);
//		}

//FindOrderByCustomerId
//		DrinkService orderService = new OrderServiceImpl();
//		List<OrderBean> list = orderService.findByCustomerId(1);
//		for(OrderBean ob : list) {
//			System.out.println(ob); 			
//		}

//UpdateOrderStatus
//		DrinkService orderService = new OrderServiceImpl();
//		OrderBean orderBean = orderService.findById("A0120210810004");
//		orderService.updateOrderStatus(orderBean, "已領取");

//findByTagName
//		DrinkService ds = new DrinkServiceImpl();
//		List<DrinkBean> list = ds.findByTagName("奶茶");
//		for(DrinkBean db : list) {
//			System.out.println(db);
//		}

//findCompanyHitRank
//		List<CompanyBean> companyIdList = companyService.findCompanyHitRank();	
//		for(CompanyBean bean : companyIdList) {
//			System.out.println(bean);
//		}

//購物車test		

//		item_id,  order_id,  product_id,  temp_id,  sugar_id,  capacity,  item_cal,  price,  message, X,  X,  quantity, note

//		ShoppingCart cart = new ShoppingCart();
//		
//		ItemBean itemBean = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "HI", null, null, 1, "");
//		ItemBean itemBean2 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "HI", null, null, 1, "");
//		ItemBean itemBean21 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "HI", null, null, 1, "");
//		ItemBean itemBean3 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "BYE", null, null, 1, "");
//		ItemBean itemBean4 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "BYE", null, null, 1, "");
//		ItemBean itemBean5 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "HI", null, null, 1, "");
//		ItemBean itemBean6 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "BYE", null, null, 1, "");
//		ItemBean itemBean7 = new ItemBean(null, null, 1, 1, 1, "M", 1, 10, "HI", null, null, 1, "");
//		
//		cart.addToCart(itemBean);
//		
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		cart.addToCart(itemBean2);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		cart.addToCart(itemBean21);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		cart.addToCart(itemBean3);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");			
//		
//		
//		
//		cart.getShoppingCart().remove(2);
//		
//		
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(key);			
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("已經刪除第二筆-------");
//
//		
//		cart.addToCart(itemBean4);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		cart.addToCart(itemBean4);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		
//		cart.addToCart(itemBean5);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		cart.getShoppingCart().remove(1);
//		
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(key);			
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("已經刪除第一筆-------");
//		
//		
//		cart.addToCart(itemBean6);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		cart.addToCart(itemBean7);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");
//		
//		
//		cart.addToCart(itemBean7);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("測試購物車功能: 總計-------");
//		
//		System.out.println(cart.getCartSubTotal());
//		
//		System.out.println("測試購物車功能: 修改後-------");
//		
////		原本 [temp_id=1, sugar_id=1, message=BYE, quantity=3, note=]
//		ItemBean itemBean8 = new ItemBean(null, null, 1, 2, 1, "M", 1, 10, "YO", null, null, 10, "");
//		cart.modifyItemBean(2, itemBean8);
////		後來 [temp_id=2, sugar_id=1, message=YO, quantity=10, note=]
//		System.out.println(cart.getCartSubTotal());
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("測試購物車功能: 刪除後-------");
//		
//
//		cart.deleteItemBean(3);
//		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(cart.getShoppingCart().get(key));			
//		}
//		System.out.println("-------");		
//		for(Integer key : cart.getShoppingCart().keySet()) {
//			System.out.println(key);			
//		}
//		System.out.println("-------");
		
		
		
//		OrderService orderService = new OrderServiceImpl();
//		ItemService itemService = new ItemServiceImpl();
//		List<ItemBean> itemBean = itemService.findByOrderId("E0120210905002");
//		for(ItemBean b : itemBean) {
//			
//		}
		
//		List<String> ids = new ArrayList<String>();
//		OrderBean bean = new OrderBean();
//		bean.setCompany_id("U01");
//		ids.add(bean.getCompany_id());
//		orderService.deleteOrderById(bean.getOrder_id());
//		ids.forEach(id ->System.out.println(id));
		
//		List<ItemBean> itemList = itemService.findByOrderId("F0120210905002");
//		for(ItemBean b : itemList) {
//			Set<ItemToppingBean> set = itemService.findAllToppingsByItemBean(b);
//			for(ItemToppingBean bean : set) {
//				System.out.println(bean.getToppingBean().getTopping_name());
//			}
//		}
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		String date = new SimpleDateFormat("E yyyy-MM-dd ahh:mm:ss").format(ts);
//		System.out.println(date);

	}

}
