package _07_Others.dao;

import java.util.List;
import _07_Others.model.FavoriteBean;

public interface FavoriteDao {

	void saveFavorite(FavoriteBean favoriteBean);
	
//	void addFavorite(Integer customer_id, String company_id);

	void deleteFavorite(Integer customer_id, String company_id);

	List<FavoriteBean> findByCustomer_id(Integer customer_id);
	
	boolean existFavorite(Integer customer_id, String company_id);
}
