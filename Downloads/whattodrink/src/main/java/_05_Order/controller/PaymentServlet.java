package _05_Order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ShoppingCart;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/_05_Order/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(PaymentServlet.class);
	CustomerService customerService = new CustomerServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	DrinkService drinkService = new DrinkServiceImpl();
	ItemService itemService = new ItemServiceImpl();
	CompanyService companyService = new CompanyServiceImpl(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("確認付款方式以及確認是否有好友折扣之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		//點選"下訂單" =>  /_05_Order/preConfirmOrderServlet
		//scheduled_time值:  "now"  or "2021-12-12 12:00:00" 
		//CustomerBean的 invitaionCount欄位必須 > 0 才能選擇是否使用好友邀請碼優惠
		String scheduled_timeTemp = request.getParameter("scheduled_time");
		System.out.println("scheduled_timeTemp: " + scheduled_timeTemp);
		cart.addScheduledTime(scheduled_timeTemp);
		
		CustomerBean bean = (CustomerBean) session.getAttribute("CLoginOK");
		
		//通知前端是否有登入狀態
		if(bean != null) {
			response.getWriter().print(gson.toJson("yeslogin"));
			return;
		}
		
		response.getWriter().print(gson.toJson("nologin"));
		
	}

}
