package _04_ShoppingCart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.ShoppingCart;


@WebServlet("/_04_ShoppingCart/UpdateItemServlet")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger log = LoggerFactory.getLogger(UpdateItemServlet.class);
	DrinkService drinkService = new DrinkServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("修改購物車之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}
		
		//取得購物車內容
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		int cartKey = Integer.valueOf(request.getParameter("cartKey"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		ItemBean itemBean = cart.getCartItemBean(cartKey);
		
		//刪除或更新購物車列資訊
		if(quantity == 0) {
			cart.deleteItemBean(cartKey);
			cart.deleteItemToppings(cartKey);
			return;
		}else {
			//從前端取得該筆飲料
			int product_id = Integer.valueOf(request.getParameter("productId"));
			DrinkBean drinkBean = drinkService.findById(product_id);
			
			
			int temp_id = Integer.valueOf(request.getParameter("tempId"));
			int sugar_id = Integer.valueOf(request.getParameter("sugarId"));
			String message = request.getParameter("message");
			String note = request.getParameter("note");
			
			//ItemCal 紀錄每杯的熱量 (飲料 + 配料們) 小數點忽略
			double factor = drinkService.findFactorBySugarId(sugar_id); 
			int item_cal = (int) (drinkBean.getProduct_cal() * factor);
			int price = drinkBean.getProduct_price();
			
			Set<ItemToppingBean> itemToppingBeanSet = new HashSet<>();
			List<String> listTemp = new LinkedList<>();
			Object[] toppings = JSONArray.parseArray(request.getParameter("addon")).toArray();
			for(int i = 0; i < toppings.length; i++) {
				int topping_id = Integer.valueOf(String.valueOf(toppings[i]));
				//save ItemToppingBean時set ItemId及ItemBean
				//save ItemToppingBean時set ItemId及ItemBean
				ItemToppingBean itemToppingBean = new ItemToppingBean(null, null, topping_id);
				ToppingBean toppingBean = drinkService.findToppingBeanById(topping_id);
				itemToppingBean.setToppingBean(toppingBean);
				itemToppingBean.setItem_id(itemBean.getItem_id());
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
			
			
			//每杯飲料的價格(已加配料) x 數量
			int totalPrice = price*quantity;
			itemBean.setTemp_id(temp_id);
			itemBean.setSugar_id(sugar_id);
			itemBean.setQuantity(quantity);
			itemBean.setMessage(message);
			itemBean.setNote(note);
			
			itemBean.setPrice(totalPrice);
			itemBean.setItem_cal(item_cal);		
			itemBean.setItemToppings(itemToppingBeanSet);
			itemBean.setDrinkBean(drinkBean);
			itemBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(temp_id));
			itemBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(sugar_id));
			log.info("修改特定購物車列完畢，修改後itemBean為=" + itemBean);
			
			
			
			// 如果cart已有相同冰度 甜度 配料 傳情 備註內容的飲料資料，就合併
			for (Integer key : cart.getShoppingCart().keySet()) {
				if (itemBean.getProduct_id().equals(cart.getShoppingCart().get(key).getProduct_id()) &&
					itemBean.getSugar_id().equals(cart.getShoppingCart().get(key).getSugar_id()) 	&& 
					itemBean.getTemp_id().equals(cart.getShoppingCart().get(key).getTemp_id()) && 
					itemBean.getMessage().trim().equals(cart.getShoppingCart().get(key).getMessage().trim()) 	&& 
					itemBean.getNote().trim().equals(cart.getShoppingCart().get(key).getNote().trim()) &&
					cartKey != key) {

					if (itemBean.getItemToppings().size() == cart.getShoppingCart().get(key).getItemToppings().size()) {
						Collection<Integer> oldList = new ArrayList<>();
						for (ItemToppingBean bean : cart.getShoppingCart().get(key).getItemToppings()) {
							oldList.add(bean.getTopping_id());
						}
						Collection<Integer> newList = new ArrayList<>();
						for (ItemToppingBean bean : itemBean.getItemToppings()) {
							newList.add(bean.getTopping_id());
						}
						if (oldList.containsAll(newList)) {
							cart.getShoppingCart().get(key).setQuantity(cart.getShoppingCart().get(key).getQuantity() + itemBean.getQuantity());
							cart.getShoppingCart().get(key).setPrice(cart.getShoppingCart().get(key).getPrice() + itemBean.getPrice());
							cart.deleteItemBean(cartKey);
							
							JSONArray priceAndCal = new JSONArray();
							//修改後的規格和之前的相同時，傳key，不同的傳0
							priceAndCal.add(cartKey);
							priceAndCal.add(totalPrice);
							priceAndCal.add(item_cal);
							priceAndCal.add(cart.getCartSubTotal());
							
							response.getWriter().println(JSON.toJSONString(priceAndCal));
							return;
						}
					}
				}
			}
						
			
			
			cart.addToCart(cartKey, listTemp);
			
			JSONArray priceAndCal = new JSONArray();
			priceAndCal.add(0);
			priceAndCal.add(totalPrice);
			priceAndCal.add(item_cal);
			priceAndCal.add(cart.getCartSubTotal());
			
			response.getWriter().println(JSON.toJSONString(priceAndCal));
			
		}
	}

}
