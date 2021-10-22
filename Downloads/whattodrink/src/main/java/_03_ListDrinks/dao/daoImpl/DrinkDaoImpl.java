package _03_ListDrinks.dao.daoImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.HibernateUtils;
import _03_ListDrinks.dao.DrinkDao;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.model.ToppingBean;
import _04_ShoppingCart.model.ItemBean;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLevelBean;
import _07_Others.model.TempLimitBean;

public class DrinkDaoImpl implements Serializable, DrinkDao {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(DrinkDaoImpl.class);
	
	SessionFactory factory;
	

	
	public DrinkDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	
	
	@Override
	public void save(DrinkBean drinkBean) {
		
		Session session = factory.getCurrentSession();
		session.save(drinkBean);
		log.info("飲品-維護功能-DAO: 新增drinkBean： " + drinkBean);
	}
	
	
	@Override
	public 	void save(TagBean tagBean) {
		
		Session session = factory.getCurrentSession();
		session.save(tagBean);
		log.info("飲品-維護功能-DAO: 新增drinkBean： " + tagBean);
	};

	@Override
	public List<DrinkBean> findByTagName(String tagName) {
		
		Session session = factory.getCurrentSession();
		List<Integer> prouct_idList = new ArrayList<Integer>();
		DrinkBean temp ;
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();
		
		String hql = "SELECT product_id FROM TagBean t WHERE t.tag_name = :tag_name";
		prouct_idList = session.createQuery(hql, Integer.class)
					  .setParameter("tag_name", tagName)
					  .getResultList();
				
		String hql2 = "FROM DrinkBean d WHERE d.product_id = :product_id";
		for (Integer integer : prouct_idList) {
			temp = (DrinkBean) session.createQuery(hql2, DrinkBean.class) 
					.setParameter("product_id", integer)
					.getSingleResult();
			if(temp.getEnabled() == true) {
				drinkList.add(temp);
			}
		}
		
		return drinkList;
	}

	@Override
	public List<DrinkBean> findByProductName(String productName) {
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();
		String nativeString = "%" + productName + "%";

//		這樣寫會掛
//		String hql = "FROM DrinkBean d WHERE d.product_name LIKE :productName";
//		drinkList = session.createQuery(hql, DrinkBean.class) 
//					  	.setParameter("productName", productName)
//					  	.getResultList();
		
		String hql = "FROM DrinkBean d WHERE d.product_name LIKE :productName AND d.enabled = true";
		drinkList = session.createQuery(hql, DrinkBean.class)
						.setParameter("productName", nativeString)
					  	.getResultList();
		return drinkList;

	}

	@Override
	public List<DrinkBean> findByCompanyId(String companyId) {
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();

		String hql = "FROM DrinkBean d WHERE d.company_id = :company_id AND d.enabled = true";
		drinkList = session.createQuery(hql, DrinkBean.class) 
						   .setParameter("company_id", companyId)
						   .getResultList();		
		return drinkList;
	}
	
	@Override
	public List<DrinkBean> orderDrinksByPriceAndTagName(String tagName) {
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();

		String hql = "SELECT t.drinkBean FROM TagBean t WHERE t.tag_name = :tag_name AND t.drinkBean.enabled = true ORDER BY t.drinkBean.product_price";
		drinkList = session.createQuery(hql, DrinkBean.class).setParameter("tag_name", tagName).getResultList();		
		return drinkList;		
	}

	@Override
	public List<DrinkBean> orderDrinksByPriceAndKeyword(String keyword){
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();
		String nativeString = "%" + keyword + "%";

		String hql = "FROM DrinkBean d WHERE d.product_name LIKE :keyword AND d.enabled = true ORDER BY d.product_price";
		drinkList = session.createQuery(hql, DrinkBean.class).setParameter("keyword", nativeString).getResultList();
		return drinkList;
	};

	@Override
	public List<DrinkBean> orderDrinksByCalAndTagName(String tagName){
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();

		String hql = "SELECT t.drinkBean FROM TagBean t WHERE t.tag_name = :tag_name AND t.drinkBean.enabled = true ORDER BY t.drinkBean.product_cal";
		drinkList = session.createQuery(hql, DrinkBean.class).setParameter("tag_name", tagName).getResultList();		
		return drinkList;
	};
	
	@Override
	public List<DrinkBean> orderDrinksByCalAndKeyword(String keyword){
		
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<DrinkBean>();
		String nativeString = "%" + keyword + "%";

		String hql = "FROM DrinkBean d WHERE d.product_name LIKE :keyword AND d.enabled = true  ORDER BY d.product_cal";
		drinkList = session.createQuery(hql, DrinkBean.class).setParameter("keyword", nativeString).getResultList();
		return drinkList;	
	};

	@Override
	public void updateDrink(DrinkBean drinkBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(drinkBean);
		log.info("飲品-維護功能-DAO: 修改drinkBean： " + drinkBean);
	}
	
	@Override
	public void deleteDrink(Integer product_id) {
		Session session = factory.getCurrentSession();

//		String hql = "DELETE FROM DrinkBean d WHERE d.product_id = :product_id";
		String hql = "UPDATE DrinkBean d SET d.enabled = FALSE WHERE d.product_id = :product_id";

		session.createQuery(hql) 
			   .setParameter("product_id", product_id)
			   .executeUpdate();	
		log.info("飲品-維護功能-DAO: 刪除product_id為 " + product_id + " 的drinkBean");
	}
	
	@Override
	public void deleteToppingById(Integer topping_id) {
		Session session = factory.getCurrentSession();

//		String hql = "DELETE FROM ToppingBean t WHERE t.topping_id = :topping_id";
		String hql = "UPDATE ToppingBean t SET t.enabled = FALSE WHERE t.topping_id = :topping_id";
		session.createQuery(hql) 
			   .setParameter("topping_id", topping_id)
			   .executeUpdate();	
		log.info("飲品-維護功能-DAO: 刪除topping_id為 " + topping_id + " 的ToppingBean");
	};



	@Override
	public DrinkBean findById(Integer product_id) {
		Session session = factory.getCurrentSession();		
		DrinkBean drinkBean = session.get(DrinkBean.class, product_id);
		log.info("DrinkBean=" + drinkBean);
		return drinkBean;
	}

	@Override
	public CategoryBean findByCategory_id(Integer category_id) {
		Session session = factory.getCurrentSession();		
		CategoryBean categoryBean = session.get(CategoryBean.class, category_id);
		log.info("categoryBean = " + categoryBean);
		return categoryBean;
	};


	@Override
	public List<DrinkBean> findByCompanyIdAndDrinkName(String companyId, String productName) {
		Session session = factory.getCurrentSession();
		List<DrinkBean> drinkList = new ArrayList<>();

		String hql = "FROM DrinkBean d WHERE d.company_id = :id AND d.product_name = :name AND d.enabled = true ORDER BY d.product_price ASC";
		drinkList = session.createQuery(hql, DrinkBean.class) 
					  	   .setParameter("id", companyId)
					  	   .setParameter("name", productName)
					  	   .getResultList();
		return drinkList;
	}



	@Override
	public void updateTagProductFK(Integer productId, DrinkBean drinkBean) {
		log.info("TagProductFK-維護功能-DAO: 修改drinkBean： " + drinkBean);
		Session session = factory.getCurrentSession();
		String hql = "UPDATE TagBean t SET t.drinkBean = :bean WHERE t.product_id = :id";
		session.createQuery(hql) 
		  	   .setParameter("bean", drinkBean)
		  	   .setParameter("id", productId)
		  	   .executeUpdate();
		
	}



	@Override
	public List<TagBean> findByProductId(int productId) {
		Session session = factory.getCurrentSession();
		List<TagBean> tagList = new ArrayList<>();

		String hql = "FROM TagBean t WHERE t.product_id = :id";
		tagList = session.createQuery(hql, TagBean.class) 
					  	   .setParameter("id", productId)
					  	   .getResultList();
		return tagList;
	}



	@Override
	public DrinkBean findDrinkBeanByProductId(int productId) {
		Session session = factory.getCurrentSession();		
		DrinkBean drinkBean = session.get(DrinkBean.class, productId);
		log.info("DrinkBean=" + drinkBean);
		return drinkBean;
	}



	@Override
	public void saveTempLevelBean(TempLevelBean tempLevelBean) {
		Session session = factory.getCurrentSession();
		session.save(tempLevelBean);
		log.info("tempLevelBean-DAO: 新增tempLevelBean： " + tempLevelBean);		
	}



	@Override
	public TempLevelBean findTempLevelBeanByTempId(int tempId) {
		Session session = factory.getCurrentSession();		
		TempLevelBean tempLevelBean = session.get(TempLevelBean.class, tempId);
		log.info("tempLevelBean=" + tempLevelBean);
		return tempLevelBean;
	}



	@Override
	public void saveTempLimitBean(TempLimitBean tempLimitBean) {
		Session session = factory.getCurrentSession();
		session.save(tempLimitBean);
		log.info("tempLimitBean-DAO: 新增tempLimitBean： " + tempLimitBean);	
		
	}



	@Override
	public void saveSugarLevelBean(SugarLevelBean sugarLevelBean) {
		Session session = factory.getCurrentSession();
		session.save(sugarLevelBean);
		log.info("tempLimitBean-DAO: 新增SugarLevelBean： " + sugarLevelBean);			
	}



	@Override
	public void saveSugarLimitBean(SugarLimitBean sugarLimitBean) {
		Session session = factory.getCurrentSession();
		session.save(sugarLimitBean);
		log.info("SugarLimitBean-DAO: 新增SugarLimitBean： " + sugarLimitBean);	
	}



	@Override
	public SugarLevelBean findSugarLevelBeanBySugarId(int sugarId) {
		Session session = factory.getCurrentSession();		
		SugarLevelBean sugarLevelBean = session.get(SugarLevelBean.class, sugarId);
		log.info("sugarLevelBean=" + sugarLevelBean);
		return sugarLevelBean;
	}



	@Override
	public List<TempLimitBean> findTempLimitsByProductId(int productId) {
		Session session = factory.getCurrentSession();
		List<TempLimitBean> list = new ArrayList<>();

		String hql = "FROM TempLimitBean t WHERE t.product_id = :id AND t.enabled = true ";
		list = session.createQuery(hql, TempLimitBean.class) 
					  .setParameter("id", productId)
					  .getResultList();
		return list;
	}



	@Override
	public List<SugarLimitBean> findSugarLimitsByProductId(int productId) {
		Session session = factory.getCurrentSession();
		List<SugarLimitBean> list = new ArrayList<>();

		String hql = "FROM SugarLimitBean t WHERE t.product_id = :id AND t.enabled = true";
		list = session.createQuery(hql, SugarLimitBean.class) 
					  .setParameter("id", productId)
					  .getResultList();
		return list;
	}



	@Override
	public void saveToppingBean(ToppingBean toppingBean) {
		Session session = factory.getCurrentSession();
		session.save(toppingBean);
		log.info("飲品-維護功能-DAO: 新增toppingBean： " + toppingBean);		
	}




	@Override
	public List<ToppingBean> findToppingBeansByCompanyId(String company_id) {
		Session session = factory.getCurrentSession();
		List<ToppingBean> list = new ArrayList<>();

		String hql = "FROM ToppingBean t WHERE t.company_id = :id AND t.enabled = true ";
		list = session.createQuery(hql, ToppingBean.class) 
					  .setParameter("id", company_id)
					  .getResultList();
		return list;
	}



	@Override
	public double findFactorBySugarId(int sugar_id) {
		Session session = factory.getCurrentSession();

		String hql = "SELECT s.factor FROM SugarLevelBean s WHERE s.sugar_id = :id";
		BigDecimal temp = session.createQuery(hql, BigDecimal.class) 
					  .setParameter("id", sugar_id)
					  .getSingleResult();
		
		return temp.doubleValue();
	}



	@Override
	public ToppingBean findToppingBeanById(int topping_id) {
		Session session = factory.getCurrentSession();		
		ToppingBean bean = session.get(ToppingBean.class, topping_id);
		log.info("ToppingBean=" + bean);
		return bean;
	}


	@Override
	public void updateAllItemIdByItemBean(ItemBean itemBean) {
		log.info("ItemToppingBean-維護功能-DAO: 修改ItemToppingBean的ItemId");
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ItemToppingBean i SET i.item_id = :id WHERE i.itemBean = :ib";
		session.createQuery(hql) 
			   .setParameter("ib", itemBean)
		  	   .setParameter("id", itemBean.getItem_id())
		  	   .executeUpdate();
	}



	@Override
	public List<ItemBean> findAllItemBeanInItemToppingBean() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT itemBean FROM ItemToppingBean";
		List<ItemBean> list = session.createQuery(hql, ItemBean.class) 
					  .getResultList();
		return list;
	}



	@Override
	public List<String> findToppingNamesByItemBean(ItemBean b) {
		Session session = factory.getCurrentSession();
		List<String> list = new ArrayList<>();
		String hql = "SELECT i.toppingBean FROM ItemToppingBean i WHERE i.itemBean = :bean GROUP BY i.itemBean";
		List<ToppingBean> toppings = session.createQuery(hql, ToppingBean.class) 
									  	    .setParameter("bean", b)
									  	    .getResultList();
		for(ToppingBean t : toppings) {
			list.add(t.getTopping_name());
		}
		return list;
	}

	@Override
	public void updateTopping(ToppingBean toppingBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(toppingBean);
		log.info("飲品-維護功能-DAO: 修改toppingBean： " + toppingBean);
	};
	
	@Override
	public void updateSugarLimitBean(SugarLimitBean sugarLimitBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(sugarLimitBean);
		log.info("飲品-維護功能-DAO: 修改sugarLimitBean： " + sugarLimitBean);
	};

	@Override
	public void updateTempLimitBean(TempLimitBean tempLimitBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(tempLimitBean);
		log.info("飲品-維護功能-DAO: 修改tempLimitBean： " + tempLimitBean);
	};
	
	@Override
	public void deleteTagByProductId(Integer product_id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM TagBean t WHERE t.product_id = :product_id";

		session.createQuery(hql) 
			   .setParameter("product_id", product_id)
			   .executeUpdate();	
		log.info("飲品-維護功能-DAO: 刪除product_id為 " + product_id + " 的tagBean");
	};
	
	@Override
	public Long determineTempLimitsByProductId(Integer productId) {
		Session session = factory.getCurrentSession();
		Long count;
		
		String hql = "SELECT COUNT(*) FROM TempLimitBean t WHERE t.product_id = :id AND t.enabled = true ";
		count = session.createQuery(hql, Long.class) 
					  .setParameter("id", productId)
					  .getSingleResult();
		return count;		
	};

	
	@Override
	public List<Integer> determineSugarLimitsByProductId(Integer productId) {
		Session session = factory.getCurrentSession();
		List<Integer> list = new ArrayList<>();

		String hql = "SELECT t.sugar_id FROM SugarLimitBean t WHERE t.product_id = :id AND t.enabled = true";
		list = session.createQuery(hql, Integer.class) 
					  .setParameter("id", productId)
					  .getResultList();
		return list;
	};

}
