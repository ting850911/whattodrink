package _07_Others.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.utils.HibernateUtils;
import _07_Others.dao.FavoriteDao;
import _07_Others.model.FavoriteBean;

public class FavoriteDaoImpl implements FavoriteDao {
	
	SessionFactory factory;

	public FavoriteDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public void saveFavorite(FavoriteBean favoriteBean) {
		Session session = factory.getCurrentSession();
		session.save(favoriteBean);
	}
	
	@Override
	public void deleteFavorite(Integer customer_id, String company_id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM FavoriteBean f WHERE f.customer_id = :customer_id AND f.company_id = :company_id";
		session.createQuery(hql) 
			   .setParameter("customer_id", customer_id)
			   .setParameter("company_id", company_id)
			   .executeUpdate();	
	}

	@Override
	public List<FavoriteBean> findByCustomer_id(Integer customer_id) {
		Session session = factory.getCurrentSession();
		List<FavoriteBean> favoriteList = new ArrayList<FavoriteBean>();
		
		String hql = "FROM FavoriteBean f WHERE f.customer_id = :customer_id";
		favoriteList = session.createQuery(hql, FavoriteBean.class)
								.setParameter("customer_id", customer_id)
								.getResultList();		
		return favoriteList;
	}

	@Override
	public boolean existFavorite(Integer customer_id, String company_id) {
		boolean exist = false;
		String hql = "FROM FavoriteBean f WHERE f.customer_id = :customer_id AND f.company_id = :company_id ";
		Session session = factory.getCurrentSession();
		try {
			FavoriteBean favoriteBean = (FavoriteBean) session.createQuery(hql)
					.setParameter("customer_id", customer_id)
					.setParameter("company_id", company_id)
					.getSingleResult();
			if (favoriteBean != null) {
				exist = true;
			}
		} catch (NoResultException e) {
			exist = false;
		} catch (NonUniqueResultException e) {
			exist = true;
		}
		return exist;
	}


}
