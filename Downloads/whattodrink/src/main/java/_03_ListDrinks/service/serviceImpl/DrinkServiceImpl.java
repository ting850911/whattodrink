package _03_ListDrinks.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.HibernateUtils;
import _03_ListDrinks.dao.DrinkDao;
import _03_ListDrinks.dao.daoImpl.DrinkDaoImpl;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLevelBean;
import _07_Others.model.TempLimitBean;


public class DrinkServiceImpl implements DrinkService {
	
	private static Logger log = LoggerFactory.getLogger(DrinkServiceImpl.class);

	SessionFactory factory;

	DrinkDao drinkdao;

	public DrinkServiceImpl() {
		drinkdao = new DrinkDaoImpl();
		factory = HibernateUtils.getSessionFactory();		
	}
		
	@Override
	public void save(DrinkBean drinkBean) {

		log.info("DrinkService-新增");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.save(drinkBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	
	@Override
	public void save(TagBean tagBean) {

		log.info("DrinkService-新增");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.save(tagBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<DrinkBean> findByTagName(String tagName) {
		
		log.info("DrinkService-依照標籤查詢品項");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findByTagName(tagName);			
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	}

	@Override
	public List<DrinkBean> findByProductName(String productName) {
		
		log.info("DrinkService-依照品名查詢品項");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findByProductName(productName);			
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	}

	@Override
	public List<DrinkBean> findByCompanyId(String companyId) {
		
		log.info("DrinkService-依照公司查詢品項");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findByCompanyId(companyId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	}

	@Override
	public List<DrinkBean> orderDrinksByPriceAndTagName(String tagName){
		
		log.info("DrinkService-標籤搜尋法:依照價格從低至高排序飲品");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.orderDrinksByPriceAndTagName(tagName);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};
	
	@Override
	public List<DrinkBean> orderDrinksByPriceAndKeyword(String keyword){
		log.info("DrinkService-自然搜尋法:依照價格從低至高排序飲品");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.orderDrinksByPriceAndKeyword(keyword);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};

	@Override
	public List<DrinkBean> orderDrinksByCalAndTagName(String tagName){
		
		log.info("DrinkService-標籤搜尋法:依照熱量從低至高排序飲品");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.orderDrinksByCalAndTagName(tagName);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};

	@Override
	public List<DrinkBean> orderDrinksByCalAndKeyword(String keyword){
		
		log.info("DrinkService-自然搜尋法:依照熱量從低至高排序飲品");
		List<DrinkBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.orderDrinksByCalAndKeyword(keyword);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};
	
	
	@Override
	public void updateDrink(DrinkBean drinkBean) {
		
		log.info("DrinkService-更新飲品");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.updateDrink(drinkBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	public void updateTopping(ToppingBean toppingBean) {
		log.info("DrinkService-更新配料");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.updateTopping(toppingBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
	};

	@Override
	public void updateSugarLimitBean(SugarLimitBean sugarLimitBean) {
		log.info("DrinkService-更新甜度限制");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.updateSugarLimitBean(sugarLimitBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	};

	@Override
	public void updateTempLimitBean(TempLimitBean tempLimitBean) {
		log.info("DrinkService-更新溫度限制");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.updateTempLimitBean(tempLimitBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	};
	
	@Override
	public void deleteTagByProductId(Integer product_id) {
		log.info("DrinkService-刪除標籤");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.deleteTagByProductId(product_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void deleteDrink(Integer product_id) {
		
		log.info("DrinkService-刪除飲品");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.deleteDrink(product_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}	
	}
	
	@Override
	public void deleteToppingById(Integer topping_id){
		log.info("DrinkService-刪除配料");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.deleteToppingById(topping_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public DrinkBean findById(Integer product_id) {
		log.info("DrinkService-查詢飲品");
		DrinkBean db = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			db = drinkdao.findById(product_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return db;
	}
	
	@Override
	public 	CategoryBean findByCategory_id(Integer category_id) {
		CategoryBean cb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cb = drinkdao.findByCategory_id(category_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return cb;
	};
	

	@Override
	public List<DrinkBean> findByCompanyIdAndDrinkName(String companyId, String productName) {
		log.info("DrinkService-依照公司ID及飲料名稱查詢DrinkBean");
		List<DrinkBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = drinkdao.findByCompanyIdAndDrinkName(companyId, productName);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}		
		return list;
	}

	@Override
	public void updateTagProductFK(Integer productId, DrinkBean drinkBean) {
		log.info("DrinkService-更新TagProductFK");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			drinkdao.updateTagProductFK(productId, drinkBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		
	}

	@Override
	public List<TagBean> findByProductId(int productId) {
		log.info("DrinkService-依照produtId找TagBean");
		List<TagBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findByProductId(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	}

	@Override //OnlyForDrinkDetail.java
	public DrinkBean findDrinkBeanByProductId(int productId) {
		log.info("DrinkService-查詢飲品byId");
		DrinkBean db = drinkdao.findDrinkBeanByProductId(productId);
		return db;
	}

	@Override
	public void saveTempLevelBean(TempLevelBean tempLevelBean) {
		log.info("DrinkService-新增TempLevelBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.saveTempLevelBean(tempLevelBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public TempLevelBean findTempLevelBeanByTempId(int tempId) {
		log.info("DrinkService-查詢TempLevelBean");
		TempLevelBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = drinkdao.findTempLevelBeanByTempId(tempId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

	@Override
	public void saveTempLimitBean(TempLimitBean tempLimitBean) {
		log.info("DrinkService-新增TempLimitBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.saveTempLimitBean(tempLimitBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public void saveSugarLevelBean(SugarLevelBean sugarLevelBean) {
		log.info("DrinkService-新增SugarLevelBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.saveSugarLevelBean(sugarLevelBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public void saveSugarLimitBean(SugarLimitBean sugarLimitBean) {
		log.info("DrinkService-新增SugarLimitBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.saveSugarLimitBean(sugarLimitBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
	}

	@Override
	public SugarLevelBean findSugarLevelBeanBySugarId(int sugarId) {
		log.info("DrinkService-查詢SugarLevelBean");
		SugarLevelBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = drinkdao.findSugarLevelBeanBySugarId(sugarId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

	@Override
	public List<TempLimitBean> findTempLimitsByProductId(int productId) {
		log.info("DrinkService-依照produtId找Set<TempLimitBean>");
		List<TempLimitBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = drinkdao.findTempLimitsByProductId(productId);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}		
		return list;
	}
	
	@Override
	public Long determineTempLimitsByProductId(Integer productId) {
		log.info("DrinkService-依照produtId搜尋結果數目判斷該產品冷熱飲");
		Long count;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			count = drinkdao.determineTempLimitsByProductId(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return count;
	}
	
	@Override
	public List<Integer> determineSugarLimitsByProductId(Integer productId){
		log.info("DrinkService-依照produtId找出對應販售的甜度List<Integer>");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<Integer> list = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.determineSugarLimitsByProductId(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};
	

	@Override
	public List<SugarLimitBean> findSugarLimitsByProductId(int productId) {
		log.info("DrinkService-依照produtId找Set<SugarLimitBean>");
		List<SugarLimitBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = drinkdao.findSugarLimitsByProductId(productId);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}		
		return list;
	}


	@Override
	public void saveToppingBean(ToppingBean toppingBean) {
		log.info("DrinkService-新增ToppingBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.saveToppingBean(toppingBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
	}


	@Override 
	public List<ToppingBean> findToppingBeansByCompanyId(String company_id) {
		log.info("DrinkService-findToppingBeansByCompanyId");
		List<ToppingBean> list = null;
				
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findToppingBeansByCompanyId(company_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}				
		return list;	
	}

	@Override
	public double findFactorBySugarId(int sugar_id) {
		log.info("DrinkService-查詢甜度factor");
		double db = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			db = drinkdao.findFactorBySugarId(sugar_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return db;
	}

	@Override
	public ToppingBean findToppingBeanById(int topping_id) {
		log.info("DrinkService-查詢findToppingBeanById");
		ToppingBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = drinkdao.findToppingBeanById(topping_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

	

	@Override
	public void updateAllItemIdByItemBean(ItemBean itemBean) {
		log.info("DrinkService-新增TempLevelBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
			drinkdao.updateAllItemIdByItemBean(itemBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<ItemBean> findAllItemBeanInItemToppingBean() {
		log.info("DrinkService-findAllItemBeanInItemToppingBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		List<ItemBean> list;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findAllItemBeanInItemToppingBean();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public List<String> findToppingNamesByItemBean(ItemBean b) {
		log.info("DrinkService-findToppingNamesByItemBean");
		Session session = factory.getCurrentSession();
		Transaction tx = null;	
		List<String> list;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findToppingNamesByItemBean(b);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}


	@Override
	public List<SugarLimitBean> findSugarLimitsByProductIdWithTx(Integer productId){		
		log.info("DrinkService-依照produtId找List<SugarLimitBean>");
		List<SugarLimitBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findSugarLimitsByProductId(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};

	
	@Override
	public List<TempLimitBean> findTempLimitsByProductIdWithTx(Integer productId){	
		log.info("DrinkService-依照produtId找List<TempLimitBean>");
		List<TempLimitBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = drinkdao.findTempLimitsByProductId(productId);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return list;
	};



}
