package _04_ShoppingCart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.ShoppingCart;

@WebServlet("/_04_ShoppingCart/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(AddToCartServlet.class);
	DrinkService drinkService = new DrinkServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("加入購物車之Controller: 開始");
		Gson gson = new Gson();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}
		
		//從前端取得該筆飲料
		int product_id = Integer.valueOf(request.getParameter("productId"));
		DrinkBean drinkBean = drinkService.findById(product_id);
		
		//取得購物車內容
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		//第一次Add 新建購物車物件
		if (cart == null) {
			cart = new ShoppingCart();
			log.info("加入購物車之Controller: 新建ShoppingCart物件");
			session.setAttribute("ShoppingCart", cart);
		} else if(cart != null && cart.getCartSum() > 0) {
			//先確認是否和購物車內的是不同公司的飲料
			String companyId = cart.getShoppingCart().get(cart.getShoppingCart().keySet().iterator().next()).getDrinkBean().getCompany_id();
			System.out.println("****購物車內的公司Id: " + companyId + "，而新飲料的公司Id: " + drinkBean.getCompany_id());
			if(!companyId.equals(drinkBean.getCompany_id())) {
				System.out.println("****公司ID不同，回應給前端呈現model詢問買家是否清空購物車");
				response.getWriter().println(gson.toJson("differentCompany"));
				return;
			}
			
		}

		int temp_id = Integer.valueOf(request.getParameter("tempId"));
		int sugar_id = Integer.valueOf(request.getParameter("sugarId"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		String message = request.getParameter("message");
		String note = request.getParameter("note");
		String capacity = drinkBean.getCapacity();

		
		
		//ItemCal 紀錄每杯的熱量 (飲料 + 配料們) 小數點忽略
		double factor = drinkService.findFactorBySugarId(sugar_id); 
		int item_cal = (int) (drinkBean.getProduct_cal() * factor);
		int price = drinkBean.getProduct_price();
		
		
		//set ItemBean相關bean
		ItemBean itemBean = new ItemBean(null, null, product_id, temp_id, sugar_id, capacity, item_cal, price, message, 0, "待製作", quantity, note);
		
		//set ItemToppingBean 相關的bean 同時加總該飲料的配料價格與cal
		Set<ItemToppingBean> itemToppingBeanSet = new HashSet<>();
		List<String> listTemp = new LinkedList<>();
		Object[] toppings = JSONArray.parseArray(request.getParameter("addon")).toArray();
		for(int i = 0; i < toppings.length; i++) {
			int topping_id = Integer.valueOf(String.valueOf(toppings[i]));
			//save ItemToppingBean時set ItemId及ItemBean
			ItemToppingBean itemToppingBean = new ItemToppingBean(null, null, topping_id);
			ToppingBean toppingBean = drinkService.findToppingBeanById(topping_id);
			itemToppingBean.setToppingBean(toppingBean);
			itemToppingBean.setItem_id(itemToppingBean.getItem_id());
			itemToppingBean.setItemBean(itemBean);
			itemToppingBean.setTopping_id(topping_id);
			item_cal += toppingBean.getTopping_cal();
			price += toppingBean.getTopping_price();
			itemToppingBeanSet.add(itemToppingBean);
		}
		for(ItemToppingBean it : itemToppingBeanSet) {
			if(it.getToppingBean() != null) {
				listTemp.add(it.getToppingBean().getTopping_name());
			}
		}
		
		
		
		//set 加上配料的總價格與此杯總cal  cal:修改購物車時前端給add_to_health的杯數值 乘上item_cal(預設是每杯)再set回item_cal
		itemBean.setPrice(price*quantity);
		itemBean.setItem_cal(item_cal);		
		itemBean.setItemToppings(itemToppingBeanSet);
		itemBean.setDrinkBean(drinkBean);
		itemBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(temp_id));
		itemBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(sugar_id));
		cart.addToCart(itemBean);
		int key = Integer.valueOf(cart.getShoppingCart().keySet().toArray()[cart.getShoppingCart().keySet().size() - 1].toString());
		cart.addToCart(key, listTemp);
		System.out.println("toppings: " + cart.getItemToppings());
		
		session.setAttribute("CartKeyList", cart.getShoppingCart().keySet());

		System.out.println("--------------------");
		System.out.println(cart.getShoppingCart().keySet());
		System.out.println(cart.getShoppingCart());
		System.out.println(cart.getShoppingCart().get(1).getItemToppings());
		System.out.println(cart.getCartSum());
		System.out.println("--------------------");
		
		
	}

}
