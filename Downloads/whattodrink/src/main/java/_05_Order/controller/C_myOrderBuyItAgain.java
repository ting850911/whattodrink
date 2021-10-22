package _05_Order.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.model.ShoppingCart;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;

@WebServlet("/C_myOrderBuyItAgain")
public class C_myOrderBuyItAgain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}
		ShoppingCart cart = new ShoppingCart();

		ItemService itemService = new ItemServiceImpl();
		DrinkService drinkService = new DrinkServiceImpl();

		// 從前端取得訂單編號
		String order_id = request.getParameter("order_id");
		System.out.println("===============================");
		System.out.println("order_id " + order_id);
		System.out.println("===============================");

		// 從訂單編號找到對應的細項
		List<ItemBean> itemBeans = itemService.findByOrderId(order_id);
		for (ItemBean itemBean : itemBeans) {
			// 每一筆細項都建立一筆新的細項(沒有真正寫入資料庫)。
			ItemBean newItemBean = new ItemBean();

			Integer product_id = itemBean.getProduct_id();
			DrinkBean drinkBean = drinkService.findById(product_id);
			Integer productPrice = drinkBean.getProduct_price();

			// 單杯細項的價格與熱量加總計算
			Integer toppingPrice = 0;
			Integer toppingCal = 0;
			Integer item_id = itemBean.getItem_id();
			List<ItemToppingBean> itemToppingBeans = itemService.findByItemId(item_id);
			// 提供配料名稱給前端"確認訂單"頁面。
			List<String> toppingNamesList = new LinkedList<String>();

			// 轉換型態以便提供給後端。
			Set<ItemToppingBean> toppingBeanSet = new HashSet<ItemToppingBean>(itemToppingBeans);
			newItemBean.setItemToppings(toppingBeanSet);

			// 單杯細項的價格與熱量加總計算+配料名稱處理。
			for (ItemToppingBean itemToppingBean : itemToppingBeans) {
				toppingPrice = toppingPrice + itemToppingBean.getToppingBean().getTopping_price();
				toppingCal = toppingCal + itemToppingBean.getToppingBean().getTopping_cal();
				toppingNamesList.add(itemToppingBean.getToppingBean().getTopping_name());
			}

			Integer quantity = itemBean.getQuantity();
			Integer price = (productPrice + toppingPrice) * quantity;

			// 固定不變的數值
			String capacity = itemBean.getCapacity();
			String message = itemBean.getMessage();
			String note = itemBean.getNote();
			Integer sugar_id = itemBean.getSugar_id();
			Integer temp_id = itemBean.getTemp_id();

			// item_cal 紀錄每杯的熱量 (飲料 + 配料們) 小數點忽略
			Double factor = drinkService.findFactorBySugarId(sugar_id);
			Integer item_cal = (int) ((drinkBean.getProduct_cal() * factor) + toppingCal);

			// 設定新的細項(沒有真正寫入資料庫)。
			newItemBean.setCapacity(capacity);
			newItemBean.setItem_cal(item_cal);
			newItemBean.setMessage(message);
			newItemBean.setNote(note);
			newItemBean.setPrice(price);
			newItemBean.setProduct_id(product_id);
			newItemBean.setDrinkBean(drinkBean);
			newItemBean.setQuantity(quantity);
			newItemBean.setSugar_id(sugar_id);
			newItemBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(sugar_id));
			newItemBean.setTemp_id(temp_id);
			newItemBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(temp_id));
			// 補上狀態
			newItemBean.setItemStatus("待製作");

			// 將新的細項加入購物車。
			cart.addToCart(newItemBean);
			System.out.println("newItemBean " + newItemBean);
			
			// 提供配料名稱給前端"確認訂單"頁面。
			int key = Integer.valueOf(
					cart.getShoppingCart().keySet().toArray()[cart.getShoppingCart().keySet().size() - 1].toString());
			cart.addToCart(key, toppingNamesList);
		}
		session.setAttribute("ShoppingCart", cart);
		RequestDispatcher rd = request.getRequestDispatcher("/_04_ShoppingCart/shoppingcart.jsp");
		rd.forward(request, response);
	}
}
