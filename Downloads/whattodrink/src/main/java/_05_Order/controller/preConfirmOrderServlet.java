package _05_Order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.ShoppingCart;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/_05_Order/preConfirmOrderServlet")
public class preConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(preConfirmOrderServlet.class);
	OrderService orderService = new OrderServiceImpl();
	DrinkService drinkService = new DrinkServiceImpl();
	ItemService itemService = new ItemServiceImpl();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("確認付款方式以及確認是否有好友折扣之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		//payment值: "現金" or "信用卡"
		//taxId值: "" or "統一編號，如: 88888888"
		//invitationDiscount值: "no" or "yes"
		//再次確認訂單.jsp 可以把 以上值 以及 領取時間 呈現出來 
		String payment =  request.getParameter("payment");
		System.out.println("payment" + payment);
		String taxId =  request.getParameter("taxId");
		System.out.println("taxId" + taxId);
		String invitationDiscount =  request.getParameter("invitationDiscount");
		System.out.println("invitationDiscount" + invitationDiscount);
		
		
//		String payment = "信用卡";
//		String payment = "現金";
//		String taxId = "";
//		String invitationDiscount = "no";
		
		cart.addPayment(payment);
		cart.addTaxId(taxId);
		cart.addInvitationDiscount(invitationDiscount);
		
		
		//給前端session值 confirmOrderPage.jsp 
		Map<Long, List<String>> longMap = new LinkedHashMap<Long, List<String>>();
		List<ItemBean> items = new LinkedList<>();
		Map<Integer, List<String>> itemToppingsTemp = new LinkedHashMap<Integer, List<String>>();
		itemToppingsTemp.putAll(cart.getItemToppings());
		itemToppingsTemp.forEach((key, toppings) -> {
			longMap.put(Long.valueOf(key), toppings);
		});
		for(Integer key : cart.getShoppingCart().keySet()) {
			items.add(cart.getCartItemBean(key));
		}
		session.setAttribute("itemToppingsMap", longMap);
		session.setAttribute("items", items);
		
		System.out.println(items);
		System.out.println(longMap);
		
		
//{1=[仙草, 珍珠, 西谷米], 3=[蜜香凍]} 配料字串陣列順序(數字並非編號，而是key) = ItemBean順序
//[ItemBean [item_id=null, order_id=null, product_id=376, temp_id=4, sugar_id=5, capacity=L, item_cal=618, price=140, message=, add_to_health=0, itemStatus=待製作, quantity=2, note=, orderBean=null, drinkBean=DrinkBean [product_id=376, company_id=F01, product_name=四季珍椰青, product_price=40, capacity=L, product_cal=273, category_id=26, product_picname=四季珍椰青.jpg, product_picpath=images/F01/四季珍椰青.jpg, add_date=2021-08-23 00:39:18.193, alter_date=2021-08-23 00:39:18.193, companyBean=CompanyBean [company_id=F01, company_name=CoCo都可, company_account=account_6, company_password=Password6, company_owner=owner_6, company_owner_email=owner_email_6@gmail.com, start_time=09:00:00, end_time=22:00:00, tel=0276429178, company_address=台北市中正區青島東路21之10號, website=https://www.coco-tea.com/, company_iconpath=images/F01/F01.jpeg, company_filename=F01.jpeg, tax_id_number=66666666, register_date=2021-08-09 22:55:15.0, alter_date=2021-08-09 22:55:15.0, company_owner_phone=0966666666, trade_name=青島店, bg_iconpath=images/F01/F01_bg.jpg, bg_filename=F01_bg.jpg, invitation=Ytsiq16A], category=CategoryBean [category_id=26, category_name=醇香茶品, company_id=F01, companyBean=CompanyBean [company_id=F01, company_name=CoCo都可, company_account=account_6, company_password=Password6, company_owner=owner_6, company_owner_email=owner_email_6@gmail.com, start_time=09:00:00, end_time=22:00:00, tel=0276429178, company_address=台北市中正區青島東路21之10號, website=https://www.coco-tea.com/, company_iconpath=images/F01/F01.jpeg, company_filename=F01.jpeg, tax_id_number=66666666, register_date=2021-08-09 22:55:15.0, alter_date=2021-08-09 22:55:15.0, company_owner_phone=0966666666, trade_name=青島店, bg_iconpath=images/F01/F01_bg.jpg, bg_filename=F01_bg.jpg, invitation=Ytsiq16A]]], tempLevelBean=TempLevelBean [temp_id=4, temp_level=去冰], sugarLevelBean=SugarLevelBean [sugar_id=5, sugar_level=全糖, factor=1.00]], 
// ItemBean [item_id=null, order_id=null, product_id=412, temp_id=3, sugar_id=3, capacity=L, item_cal=487, price=80, message=, add_to_health=1, itemStatus=待製作, quantity=1, note=, orderBean=null, drinkBean=DrinkBean [product_id=412, company_id=F01, product_name=珍珠鮮奶茶, product_price=70, capacity=L, product_cal=547, category_id=30, product_picname=珍珠鮮奶茶.jpg, product_picpath=images/F01/珍珠鮮奶茶.jpg, add_date=2021-08-23 00:39:20.414, alter_date=2021-08-23 00:39:20.414, companyBean=CompanyBean [company_id=F01, company_name=CoCo都可, company_account=account_6, company_password=Password6, company_owner=owner_6, company_owner_email=owner_email_6@gmail.com, start_time=09:00:00, end_time=22:00:00, tel=0276429178, company_address=台北市中正區青島東路21之10號, website=https://www.coco-tea.com/, company_iconpath=images/F01/F01.jpeg, company_filename=F01.jpeg, tax_id_number=66666666, register_date=2021-08-09 22:55:15.0, alter_date=2021-08-09 22:55:15.0, company_owner_phone=0966666666, trade_name=青島店, bg_iconpath=images/F01/F01_bg.jpg, bg_filename=F01_bg.jpg, invitation=Ytsiq16A], category=CategoryBean [category_id=30, category_name=濃醇鮮乳, company_id=F01, companyBean=CompanyBean [company_id=F01, company_name=CoCo都可, company_account=account_6, company_password=Password6, company_owner=owner_6, company_owner_email=owner_email_6@gmail.com, start_time=09:00:00, end_time=22:00:00, tel=0276429178, company_address=台北市中正區青島東路21之10號, website=https://www.coco-tea.com/, company_iconpath=images/F01/F01.jpeg, company_filename=F01.jpeg, tax_id_number=66666666, register_date=2021-08-09 22:55:15.0, alter_date=2021-08-09 22:55:15.0, company_owner_phone=0966666666, trade_name=青島店, bg_iconpath=images/F01/F01_bg.jpg, bg_filename=F01_bg.jpg, invitation=Ytsiq16A]]], tempLevelBean=TempLevelBean [temp_id=3, temp_level=微冰], sugarLevelBean=SugarLevelBean [sugar_id=3, sugar_level=半糖, factor=0.70]]]
		
		
		
		
		
		
		
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/_04_ShoppingCart/confirmOrderPage.jsp");
//		rd.forward(request, response);
//		return;
		
	}

}
