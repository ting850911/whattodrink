package _03_ListDrinks.service;

import java.util.List;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;

public interface CategoryService {
	
	CategoryBean findById(Integer category_id);

	List<CategoryBean> findByCompanyId(String companyId);
		
	void save(CategoryBean categoryBean);

	void updateCategory(CategoryBean categoryBean);

	void deleteCategory(int category_id);

	List<CategoryBean> findByCategoryNameAndCompanyId(String category_name, String company_id);
}
