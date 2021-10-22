package _01_Register.b_01_register.service;

import java.util.List;
import java.util.Map;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _04_ShoppingCart.model.OrderBean;


public interface CompanyService {
	
	void save(CompanyBean companyBean);
	
	boolean existsByAccount(String account);
	
	CompanyBean findByCompanyAccount(String company_account);
	
	CompanyBean findByCompanyAccountAndPassword(String company_account, String password);
	
	void updateCompany(CompanyBean companyBean);

	String getCompanyId(String companyName);	
	
	boolean checkByCompanyEmailAndInvitation(String email, String invitation);

	CompanyBean findByCompanyEmail(String email);
	
	CompanyBean findByCompanyInvitation(String invitation);
	
	List<CategoryBean> findByCompanyId(String companyId);
	
	
	List<CompanyBean> findAll() ;

	CompanyBean findById(String companyId) ;

	List<CompanyBean> findCompanyHitRank();
	
	List<CompanyBean> findAllByHitRank();

	List<Map<String, Object>> findAllUnfinishedOrdersByCompanyId(String company_id);

	List<Map<String, Object>> findAllHistoryOrdersByCompanyId(String company_id);


	
//	CompanyBean findById(Integer id) ;

//	String getSelectTag(String tagName, int selected);
	
}