package _03_ListDrinks.dao.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.utils.HibernateUtils;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _03_ListDrinks.dao.CategoryDao;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;

public class CategoryDaoImpl implements Serializable, CategoryDao {

	private static final long serialVersionUID = 1L;

	SessionFactory factory;
	
	public CategoryDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void save(CategoryBean categoryBean) {
		Session session = factory.getCurrentSession();
		session.persist(categoryBean);
	}

	@Override
	public void updateCategory(CategoryBean categoryBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(categoryBean);
	}

	@Override
	public void deleteCategory(int category_id) {
		Session session = factory.getCurrentSession();
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setCategory_id(category_id);
		session.delete(categoryBean);
	}

	@Override
	public CategoryBean findById(Integer category_id) {
		Session session = factory.getCurrentSession();		
		CategoryBean categoryBean = session.get(CategoryBean.class, category_id);
		return categoryBean;
	}
	
	@Override
	public List<CategoryBean> findByCompanyId(String companyId) {
		
		Session session = factory.getCurrentSession();
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		
		String hql = "FROM CategoryBean c WHERE c.company_id = :company_id";
		categoryList = session.createQuery(hql, CategoryBean.class) 
								.setParameter("company_id", companyId)
								.getResultList();		
		return categoryList;
	}
	
	@Override
	public List<CategoryBean> findByCategoryNameAndCompanyId(String category_name, String company_id) {
		
		Session session = factory.getCurrentSession();
		List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();

		String hql = "FROM CategoryBean c WHERE c.category_name LIKE :category_name AND c.company_id = :company_id";
		categoryBeans = session.createQuery(hql, CategoryBean.class)
						.setParameter("category_name", category_name)
						.setParameter("company_id", company_id)
					  	.getResultList();
		return categoryBeans;
	}
	

}
