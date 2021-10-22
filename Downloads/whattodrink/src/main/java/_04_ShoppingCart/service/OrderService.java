package _04_ShoppingCart.service;

import java.util.List;
import java.util.Map;

import _04_ShoppingCart.model.OrderBean;

public interface OrderService {

	void save(OrderBean orderBean);

	OrderBean findById(String orderId);

	OrderBean findByOrderId(String orderId); 

	List<OrderBean> findByCompanyId(String companyId);
	
	List<OrderBean> findByCustomerAccount(String customerAccount);
	
	List<OrderBean> findByCustomerId(int customerId);
	
	void updateOrderStatus(OrderBean orderBean, String orderStatus);

	void deleteOrderById(String orderId);
	
	String getOrderIdByCompanyName(String companyName);

	String getLastOrderIdByCompanyId(String companyId);
	
	void updateOrderBean(OrderBean orderBean);

	
	
	
	Map<String, Object> createDailyReport(String companyName);


	
	
	
//	void preCheckStock(ShoppingCart shoppingCart);
}
