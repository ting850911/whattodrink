package _07_Others.service;

import java.util.List;
import _07_Others.model.FavoriteBean;

public interface FavoriteService {

	void saveFavorite(FavoriteBean favoriteBean);

	void deleteFavorite(Integer customer_id, String company_id);

	List<FavoriteBean> findByCustomer_id(Integer customer_id);
	
	boolean existFavorite(Integer customer_id, String company_id);

}
