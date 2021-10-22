package _03_ListDrinks.dao;

import java.util.List;
import java.util.Set;

import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.model.ToppingBean;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLevelBean;
import _07_Others.model.TempLimitBean;

public interface DrinkDao {

	void save(DrinkBean drinkBean);
	
	void save(TagBean tagBean);
	
	List<DrinkBean> findByTagName(String tagName);

	List<DrinkBean> findByProductName(String productName);

	List<DrinkBean> findByCompanyId(String companyId);
	
	List<DrinkBean> orderDrinksByPriceAndTagName(String tagName);
	
	List<DrinkBean> orderDrinksByPriceAndKeyword(String keyword);
	
	List<DrinkBean> orderDrinksByCalAndTagName(String tagName);
	
	List<DrinkBean> orderDrinksByCalAndKeyword(String keyword);

	void updateDrink(DrinkBean drinkBean);

	void updateTopping(ToppingBean toppingBean);
	
	void updateSugarLimitBean(SugarLimitBean sugarLimitBean);

	void updateTempLimitBean(TempLimitBean tempLimitBean);
	
	void deleteTagByProductId(Integer product_id);

	void deleteDrink(Integer product_id);

	void deleteToppingById(Integer topping_id);
	
	DrinkBean findById(Integer product_id);
	
	CategoryBean findByCategory_id(Integer category_id);
	
	Long determineTempLimitsByProductId(Integer productId);

	List<Integer> determineSugarLimitsByProductId(Integer productId);
	
	
	
	
	
	//B insert

	List<DrinkBean> findByCompanyIdAndDrinkName(String companyId, String productName);

	void updateTagProductFK(Integer productId, DrinkBean drinkBean);

	List<TagBean> findByProductId(int productId);

	DrinkBean findDrinkBeanByProductId(int productId);

	void saveTempLevelBean(TempLevelBean tempLevelBean);

	TempLevelBean findTempLevelBeanByTempId(int tempId);

	void saveTempLimitBean(TempLimitBean tempLimitBean);

	void saveSugarLevelBean(SugarLevelBean sugarLevelBean);

	void saveSugarLimitBean(SugarLimitBean sugarLimitBean);

	SugarLevelBean findSugarLevelBeanBySugarId(int sugarId);

	List<TempLimitBean> findTempLimitsByProductId(int productId);

	List<SugarLimitBean> findSugarLimitsByProductId(int productId);

	void saveToppingBean(ToppingBean toppingBean);

	List<ToppingBean> findToppingBeansByCompanyId(String company_id);

	double findFactorBySugarId(int sugar_id);

	ToppingBean findToppingBeanById(int topping_id);

	void updateAllItemIdByItemBean(ItemBean itemBean);

	List<ItemBean> findAllItemBeanInItemToppingBean();

	List<String> findToppingNamesByItemBean(ItemBean b);



	

}