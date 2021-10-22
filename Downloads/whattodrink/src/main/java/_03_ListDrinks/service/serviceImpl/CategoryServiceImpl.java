package _03_ListDrinks.service.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.utils.HibernateUtils;
import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.dao.CategoryDao;
import _03_ListDrinks.dao.daoImpl.CategoryDaoImpl;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.service.CategoryService;

public class CategoryServiceImpl implements Serializable, CategoryService {

	private static final long serialVersionUID = 1L;
	SessionFactory factory;
	CategoryDao categoryDao;

	public CategoryServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		categoryDao = new CategoryDaoImpl();
	}

	@Override
	public void save(CategoryBean categoryBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			categoryDao.save(categoryBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void updateCategory(CategoryBean categoryBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			categoryDao.updateCategory(categoryBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void deleteCategory(int category_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			categoryDao.deleteCategory(category_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

	}

	@Override
	public CategoryBean findById(Integer category_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		CategoryBean cb;
		try {
			tx = session.beginTransaction();
			cb = categoryDao.findById(category_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return cb;
	}

	@Override
	public List<CategoryBean> findByCompanyId(String companyId) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<CategoryBean> list;
		try {
			tx = session.beginTransaction();
			list = categoryDao.findByCompanyId(companyId);
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
	public List<CategoryBean> findByCategoryNameAndCompanyId(String category_name, String company_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<CategoryBean> list;
		try {
			tx = session.beginTransaction();
			list = categoryDao.findByCategoryNameAndCompanyId(category_name, company_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}

}
