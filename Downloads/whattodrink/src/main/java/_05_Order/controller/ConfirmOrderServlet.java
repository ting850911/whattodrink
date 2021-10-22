package _05_Order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.ShoppingCart;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;
import example.ExampleAllInOne;

@WebServlet("/_05_Order/ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ConfirmOrderServlet.class);
	OrderService orderService = new OrderServiceImpl();
	DrinkService drinkService = new DrinkServiceImpl();
	CompanyService companyService = new CompanyServiceImpl(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("最終確認下訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar nowTime = Calendar.getInstance();
		String nowDate = sdf.format(nowTime.getTime());
		Timestamp order_date = Timestamp.valueOf(nowDate); 
		String scheduled_time;
		int order_quantity = 0;
		Set<ItemBean> items = new LinkedHashSet<>();
		
		//刪除preConfirmOrder的session
		session.removeAttribute("items");
		session.removeAttribute("itemToppingsMap");

		
		//取得購物車內容
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
		int customer_id = customerBean.getCustomer_id();
		String companyId = 	drinkService.findById(cart.getShoppingCart().get(cart.getShoppingCart().keySet().iterator().next()).getProduct_id()).getCompany_id();
		String order_id = cart.createOrderIdByCompanyName(companyId);
		Integer order_total = (int) cart.getCartSubTotal();
		
		//是否有統一編號 付款方式 
		String scheduled_timeTemp = cart.getScheduledTime();
		String payment = cart.getPayment();
		String taxId = cart.getTaxId();
		String invitationDiscount = cart.getInvitationDiscount();
		
		if(invitationDiscount.equalsIgnoreCase("yes")) {
			invitationDiscount = "已折扣50元";
			order_total -= 50;
		}else {
			invitationDiscount = "無折扣";
		}
		if(scheduled_timeTemp.equals("now")) { //填現在呈現2021-08-31 13:15 ~ 13:30 (預設等15分鐘-30分鐘)
			nowTime.add(Calendar.MINUTE, 15);
			String scheduled_time1 = sdf2.format(nowTime.getTime()); 
			nowTime.add(Calendar.MINUTE, 15);
			String scheduled_time2 = sdf2.format(nowTime.getTime());  
			scheduled_time = scheduled_time1 + " ~ " + scheduled_time2.substring(scheduled_time2.length()-5);   
		}else{
			scheduled_time = scheduled_timeTemp.substring(0, 16); //買家自選時間 2021-08-31 13:22
		}
			
			
		OrderBean orderBean = new OrderBean(order_id, customer_id, order_date, null, scheduled_time, companyId, order_quantity, order_total, payment, "待確認", null, taxId, null, invitationDiscount);

		Set<Integer> cartKey = cart.getShoppingCart().keySet();
		for(Integer key : cartKey){
			ItemBean bean = cart.getCartItemBean(key);
			order_quantity += bean.getQuantity();
			bean.setItem_cal(bean.getAdd_to_health() * bean.getItem_cal());
			bean.setOrder_id(order_id);
			bean.setOrderBean(orderBean);
			items.add(bean);
		}
		
		orderBean.setOrder_quantity(order_quantity);
		orderBean.setCompanyBean(companyService.findById(companyId));
		orderBean.setCustomerBean(customerBean);
		orderBean.setItems(items);
		cart.addOrderBeanToCart(orderBean);
		
		
		//信用卡付款或現金支付
		if(payment.equals("信用卡")) {
			ExampleAllInOne.initial();
			response.getWriter().print(ExampleAllInOne.genAioCheckOutALL(orderBean));
		}else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_04_ShoppingCart/saveOrderServlet"));
		}
		
		
	}

}
