package _04_ShoppingCart.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

public class ShoppingCart {

	private Map<Integer, ItemBean> cart = new LinkedHashMap<>();
	DrinkService drinkService = new DrinkServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	Map<Integer, List<String>> itemToppings = new LinkedHashMap<>();
	OrderBean orderBean;
	String scheduled_time;
	String payment;
	String taxId;
	String invitationDiscount;
	String companyName;
	String companyTradeName;
	String companyAddress;
	
	public ShoppingCart() {
	}

	public Map<Integer, ItemBean> getShoppingCart() { // ${ShoppingCart.shoppingCart}
		return cart;
	}
	public Map<Integer, List<String>> getItemToppings() {
		return itemToppings;
	}

	public void addToCart(ItemBean itemBean) {
		if (cart.isEmpty()) {
			cart.put(1, itemBean);
		} else {
			// 如果cart已有相同冰度 甜度 配料 傳情 備註內容的飲料資料，則『加購』
			for (Integer key : cart.keySet()) {
				if (itemBean.getProduct_id().equals(cart.get(key).getProduct_id()) &&
					itemBean.getSugar_id().equals(cart.get(key).getSugar_id()) 	&& 
					itemBean.getTemp_id().equals(cart.get(key).getTemp_id()) && 
					itemBean.getMessage().trim().equals(cart.get(key).getMessage().trim()) 	&& 
					itemBean.getNote().trim().equals(cart.get(key).getNote().trim())) {

					if (itemBean.getItemToppings().size() == cart.get(key).getItemToppings().size()) {
						Collection<Integer> oldList = new ArrayList<>();
						for (ItemToppingBean bean : cart.get(key).getItemToppings()) {
							oldList.add(bean.getTopping_id());
						}
						Collection<Integer> newList = new ArrayList<>();
						for (ItemToppingBean bean : itemBean.getItemToppings()) {
							newList.add(bean.getTopping_id());
						}
						if (oldList.containsAll(newList)) {
							cart.get(key).setQuantity(cart.get(key).getQuantity() + itemBean.getQuantity());
							cart.get(key).setPrice(cart.get(key).getPrice() + itemBean.getPrice());
							return;
						}
					}
				}
			}
			cart.put(Integer.valueOf(cart.keySet().toArray()[cart.keySet().size() - 1].toString()) + 1, itemBean);

		}
	}
	
	public void addToCart(int key, List<String> list) {
		itemToppings.put(key, list);
	}
	public void deleteItemToppings(int key) {
		if (itemToppings.get(key) != null) {
			itemToppings.remove(key);
		}
	}

	public ItemBean getCartItemBean(int key) {
		ItemBean bean = null;
		if (cart.get(key) != null) {
			bean = cart.get(key);
		}
		return bean;
	}

	public void deleteItemBean(int key) {
		if (cart.get(key) != null) {
			cart.remove(key);
		}
	}

	// 計算購物車項目數量 (同規格算1項，並非飲料總數量)
	public int getCartSum() { // ShoppingCart.cartSum
		return cart.size();
	}

	// 計算購物車內所有飲料的合計金額
	public double getCartSubTotal() {
		double subTotal = 0;
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			ItemBean itemBean = cart.get(n);
			subTotal += itemBean.getPrice();
		}
		return subTotal;
	}

	public String createOrderIdByCompanyName(String companyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int newDate = Integer.valueOf(sdf.format(System.currentTimeMillis()));
		String oldOrderId = orderService.getLastOrderIdByCompanyId(companyId);
		System.out.println("oldOrderId:" + oldOrderId);
		
		if(oldOrderId != null) {
			int oldDate = Integer.valueOf(oldOrderId.substring(3, 11));
			if(newDate == oldDate) {
				return companyId + newDate + String.format("%03d", Integer.valueOf(oldOrderId.substring(oldOrderId.length()-3)) + 1);
			}else if(newDate > oldDate) {
				return companyId + String.valueOf(newDate) + String.format("%03d", 1);
			}
		}
		return companyId + newDate + String.format("%03d", 1);
	}
	
	
	public void addScheduledTime(String scheduled_time) {
		this.scheduled_time = scheduled_time;
	}
	public String getScheduledTime() {return scheduled_time;}; 
	
	public void addPayment(String payment) {
		this.payment = payment;
	}
	public String getPayment() {return payment;}; 
	
	public void addTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getTaxId() {return taxId;};
	
	public void addInvitationDiscount(String invitationDiscount) {
		this.invitationDiscount = invitationDiscount;
	}
	public String getInvitationDiscount() {return invitationDiscount;}; 
	
	
	public void addOrderBeanToCart(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	public OrderBean getOrderBean() {return orderBean;}; 

	
	public String getCompanyName() {return getShoppingCart().get(getShoppingCart().keySet().iterator().next()).getDrinkBean().getCompanyBean().getCompany_name();}; 
	public String getCompanyTradeName() {return getShoppingCart().get(getShoppingCart().keySet().iterator().next()).getDrinkBean().getCompanyBean().getTrade_name();}; 
	public String getCompanyAddress() {return getShoppingCart().get(getShoppingCart().keySet().iterator().next()).getDrinkBean().getCompanyBean().getCompany_address();}; 

	
	
	//test
	public boolean isSame(ItemBean itemBean) {
		if (cart.isEmpty()) {
			return false;
		} else {
			// 如果cart已有相同冰度 甜度 配料 傳情 備註內容的飲料資料，則true
			for (Integer key : cart.keySet()) {
				if (itemBean.getProduct_id().equals(cart.get(key).getProduct_id()) &&
					itemBean.getSugar_id().equals(cart.get(key).getSugar_id()) 	&& 
					itemBean.getTemp_id().equals(cart.get(key).getTemp_id()) && 
					itemBean.getMessage().trim().equals(cart.get(key).getMessage().trim()) 	&& 
					itemBean.getNote().trim().equals(cart.get(key).getNote().trim())) {

					if (itemBean.getItemToppings().size() == cart.get(key).getItemToppings().size()) {
						Collection<Integer> oldList = new ArrayList<>();
						for (ItemToppingBean bean : cart.get(key).getItemToppings()) {
							oldList.add(bean.getTopping_id());
						}
						Collection<Integer> newList = new ArrayList<>();
						for (ItemToppingBean bean : itemBean.getItemToppings()) {
							newList.add(bean.getTopping_id());
						}
						if (oldList.containsAll(newList)) {
							return true;
						}
					}
				}
			}
			return false;

		}
	}
}
