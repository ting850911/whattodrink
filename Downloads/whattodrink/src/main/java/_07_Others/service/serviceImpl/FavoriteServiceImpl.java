package _07_Others.service.serviceImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.utils.HibernateUtils;
import _07_Others.dao.FavoriteDao;
import _07_Others.dao.daoImpl.FavoriteDaoImpl;
import _07_Others.model.CommentBean;
import _07_Others.model.FavoriteBean;
import _07_Others.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

	SessionFactory factory;
	FavoriteDao favoriteDao;
	
	public FavoriteServiceImpl(){
		this.favoriteDao = new FavoriteDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public void saveFavorite(FavoriteBean favoriteBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			favoriteDao.saveFavorite(favoriteBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void deleteFavorite(Integer customer_id, String company_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			favoriteDao.deleteFavorite(customer_id, company_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<FavoriteBean> findByCustomer_id(Integer customer_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<FavoriteBean> favoriteBeans = null;
		try {
			tx = session.beginTransaction();
			favoriteBeans = favoriteDao.findByCustomer_id(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return favoriteBeans;
	}

	@Override
	public boolean existFavorite(Integer customer_id, String company_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean exist = false;
		try {
			tx = session.beginTransaction();
			exist = favoriteDao.existFavorite(customer_id, company_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		
		return exist;
	}

}
