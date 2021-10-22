package _01_Register.b_01_register.dao;

import java.util.List;
import java.util.Map;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _04_ShoppingCart.model.OrderBean;


public interface CompanyDao {
	
	void save(CompanyBean companyBean);
	
	boolean existsByAccount(String account);
	
	CompanyBean findByCompanyAccount(String company_account);
	
	void updateCompany(CompanyBean companyBean);
	
	CompanyBean findByCompanyAccountAndPassword(String company_account, String password);

	boolean checkByCompanyEmailAndInvitation(String email, String invitation);
	
	

	List<CompanyBean> findAll();

	CompanyBean findById(String companyId);

	CompanyBean findByCompanyEmail(String email);

	CompanyBean findByCompanyInvitation(String invitation);

	List<CategoryBean> findByCompanyId(String companyId);

	List<CompanyBean> findCompanyHitRank();
	
	List<CompanyBean> findAllByHitRank();
	
	List<Map<String, Object>> findAllUnfinishedOrdersByCompanyId(String company_id);

	List<Map<String, Object>> findAllHistoryOrdersByCompanyId(String company_id);
	
//	CompanyBean findById(Integer id);

	

}