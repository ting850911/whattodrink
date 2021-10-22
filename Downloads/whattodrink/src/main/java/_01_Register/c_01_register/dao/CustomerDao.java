package _01_Register.c_01_register.dao;

import java.util.List;
import java.util.Map;

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.OrderBean;

public interface CustomerDao {
	
	// 儲存CustomerBean物件，將參數companyBean新增到customer表格內。
	public void saveCustomer(CustomerBean customerBean);
	
	// 判斷參數（顧客帳號）是否已經被使用，如果是傳回true(此id不能使用)，否則傳回false(此id可使用)。
	public boolean existsByCustomerAccount(String customer_account);
	
	// 由參數（顧客帳號）到customer表格中 取得某個會員的所有資料，傳回值為一個CustomerBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	public CustomerBean findByCustomerAccount(String customer_account);
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的CustomerBean物件，否則傳回 null。
	public CustomerBean findByCustomerAccountAndPassword(String customer_account, String customer_password) ;
	
	// 檢查使用者在註冊時輸入的驗證碼是否正確。如果正確，傳回該帳號所對應的CustomerBean物件，否則傳回 null。
	public CustomerBean findByCustomerVerification(String customer_account, String customer_verification) ;

	public void updateCustomer(CustomerBean customerBean);

	public boolean existsByCustomerEmail(String email);

	public boolean checkInvited(String customer_account);
	
	public CustomerBean findByCustomerId(int customer_id);

	
	public boolean existsByInvitation(String invitation);
	
	public CustomerBean findByInvitation(String invitation);
	
	public void updateCustomers(CustomerBean... customerBean);
	
	public boolean existsByAccountAndVerificationCode(String customer_account, String verificationCode);
	
	public boolean existsByEmailAndVerificationCode(String email, String verificationCode);

	public CustomerBean findByEmail(String email);

	
	//0919 Yu
	
	List<Map<String, Object>> findAllHistoryOrdersByCustomerId(Integer customer_id);
	
	List<Map<String, Object>> findAllCurrentOrdersByCustomerId(Integer customer_id);
	
	List<OrderBean> findTodayOrdersByCustomerId(Integer customer_id);
	
	 List<OrderBean> findAllDeleteOrdersByCustomerId(Integer customer_id);
}
