package _03_ListDrinks.dao;

import java.util.List;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;

public interface CategoryDao {

	CategoryBean findById(Integer category_id);

	List<CategoryBean> findByCompanyId(String companyId);
	
	List<CategoryBean> findByCategoryNameAndCompanyId(String category_name, String company_id);
	
	void save(CategoryBean categoryBean);

	void updateCategory(CategoryBean categoryBean);

	void deleteCategory(int category_id);


}