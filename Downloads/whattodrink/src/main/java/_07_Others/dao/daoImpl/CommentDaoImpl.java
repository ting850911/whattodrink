package _07_Others.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.utils.HibernateUtils;
import _03_ListDrinks.model.DrinkBean;
import _07_Others.dao.CommentDao;
import _07_Others.model.CommentBean;

public class CommentDaoImpl implements CommentDao {

	SessionFactory factory;

	public CommentDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<CommentBean> findAllCommentBeans() {
		Session session = factory.getCurrentSession();
		List<CommentBean> list = new ArrayList<>();
		String hql = "FROM CommentBean cb ORDER BY cb.comment_date DESC ";
		list = session.createQuery(hql, CommentBean.class).getResultList();
		return list;
	}
	
	@Override
	public void save(CommentBean commentBean) {
		Session session = factory.getCurrentSession();
		session.save(commentBean);
	}


	@Override
	public BigDecimal avgStarForDrink(Integer product_id) {
		Session session = factory.getCurrentSession();
		Double avgDouble;
		String hql = "SELECT AVG(cb.star) FROM CommentBean cb WHERE cb.product_id = :product_id";

		if (session.createQuery(hql).setParameter("product_id", product_id).getSingleResult() == null) {
			avgDouble = 0.0;
		} else {
			avgDouble = (Double) session.createQuery(hql).setParameter("product_id", product_id).getSingleResult();
		}
		
		BigDecimal avgStar = new BigDecimal(avgDouble);
		return avgStar;
	}

	@Override
	public List<CommentBean> findCommentBeansById(Integer product_id) {
		Session session = factory.getCurrentSession();
		List<CommentBean> commentList = new ArrayList<>();
		
		String hql = "FROM CommentBean cb WHERE cb.product_id = :product_id";
		commentList = session.createQuery(hql, CommentBean.class).setParameter("product_id", product_id).getResultList();
		return commentList;
	}
	
	@Override
	public List<CommentBean> findCommentBeansByOrderId(String order_id) {
		Session session = factory.getCurrentSession();
		List<CommentBean> commentList = new ArrayList<>();

		String hql = "FROM CommentBean cb WHERE cb.order_id = :order_id";
		commentList = session.createQuery(hql, CommentBean.class).setParameter("order_id", order_id).getResultList();
		return commentList;
	}


}
