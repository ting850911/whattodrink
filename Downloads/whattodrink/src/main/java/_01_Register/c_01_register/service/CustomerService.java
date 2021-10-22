package _01_Register.c_01_register.service;

import java.util.List;
import java.util.Map;

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.OrderBean;

public interface CustomerService {

	void save(CustomerBean customerBean);

	boolean existsByCustomerAccount(String customer_account);
	
	CustomerBean findByCustomerId(int customer_id);

	CustomerBean findByCustomerAccount(String customer_account);

	CustomerBean findByCustomerAccountAndPassword(String customer_account, String customer_password);

	CustomerBean findByCustomerVerification(String customer_account, String customer_verification);

	void updateCustomer(CustomerBean customerBean);

	boolean existsByCustomerEmail(String email);

	boolean checkInvited(String customer_account);
	
	
	public boolean existsByInvitation(String invitation);
	
	public CustomerBean findByInvitation(String invitation);
	
	public void updateCustomers(CustomerBean... customerBean);

	public boolean existsByAccountAndVerificationCode(String customer_account, String verificationCode);
	
	public boolean existsByEmailAndVerificationCode(String email, String verificationCode);

	public CustomerBean findByEmail(String email);
	
	// 0919
	//	
	List<Map<String, Object>> findAllHistoryOrdersByCustomerId(Integer customer_id);

	List<Map<String, Object>> findAllCurrentOrdersByCustomerId(Integer customer_id);

	List<OrderBean> findTodayOrdersByCustomerId(Integer customer_id);
	
	List<OrderBean> findAllDeleteOrdersByCustomerId(Integer customer_id);
}