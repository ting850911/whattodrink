package _04_ShoppingCart.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.c_01_register.model.CustomerBean;
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
import edu.emory.mathcs.backport.java.util.Collections;

@WebServlet("/_04_ShoppingCart/saveOrderServlet")
public class saveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(saveOrderServlet.class);
	OrderService orderService = new OrderServiceImpl();
	ItemService itemService = new ItemServiceImpl();
	DrinkService drinkService = new DrinkServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("完成訂單儲存Order之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		CustomerBean bean = null;

		orderService.save(cart.getOrderBean());
		for(ItemBean b : drinkService.findAllItemBeanInItemToppingBean()) {
			drinkService.updateAllItemIdByItemBean(b);
		}
		//如果有使用好友優惠，要扣掉InvitationCount
		if(cart.getOrderBean().getInvitationDiscount().equals("已折扣50元")) {
			bean = customerService.findByCustomerId(cart.getOrderBean().getCustomer_id());
			bean.setInvitationCount(bean.getInvitationCount() - 1);
			customerService.updateCustomer(bean);
//			session.setAttribute("CLoginOK", bean);
		}
		
		
		OrderBean tempOrderBean = new OrderBean();
		List<ItemBean> items = new LinkedList<>();
		tempOrderBean = cart.getOrderBean();
		Timestamp ts = tempOrderBean.getOrder_date();
		String date = new SimpleDateFormat("E yyyy-MM-dd ahh:mm:ss").format(ts);
		Map<Long, List<String>> longMap = new LinkedHashMap<Long, List<String>>();
//		Map<Integer, List<String>> itemToppings = new LinkedHashMap<Integer, List<String>>();
		Map<Integer, List<String>> itemToppingsTemp = new LinkedHashMap<Integer, List<String>>();
		itemToppingsTemp.putAll(cart.getItemToppings());
		itemToppingsTemp.forEach((key, toppings) -> {
			longMap.put(Long.valueOf(key), toppings);
		});
		tempOrderBean.getItems().forEach(temp -> {
			items.add(temp);
		});
		request.setAttribute("orderBean",tempOrderBean);
		request.setAttribute("date", date);
		request.setAttribute("itemSet", items);
		request.setAttribute("itemToppingsMap", longMap);
		
		System.out.println("tempOrderBean: " + tempOrderBean);
		System.out.println("date: " + date);
		System.out.println("itemSet: " + items);
		System.out.println("itemToppingsMap: "+ longMap);
		
//		session.removeAttribute("ShoppingCart");
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/_04_ShoppingCart/orderSuccess.jsp");
		rd.forward(request, response);
		return;
		
//		${orderBean}
//		${date}
//			<c:choose>
//			<c:when test="${itemSet != null}">
//				<c:forEach var="cart" varStatus="stVar"  items="${itemSet}">
//					${itemSet}====
//					${itemSet[0]}
//				</c:forEach>
//			</c:when>
//			<c:otherwise>沒有item</c:otherwise>
//		</c:choose>
//		${fn:length(itemSet)}
//		---------
//		${fn:length(itemToppingsMap)}
//		---------
//		<c:choose>
//			<c:when test="${itemToppingsMap != null}">
//				<c:forEach var="cart" varStatus="stVar"  items="${itemToppingsMap}">
//					toppingList: ${fn:length(cart.value)}<br>
//					topping: ${cart.value}<br>
//					topping[0]: ${cart.value[0]}<br>
//				</c:forEach>
//			</c:when>
//			<c:otherwise>沒有配料</c:otherwise>
//		</c:choose>		
		
		
		
		
		
		
	}

}
