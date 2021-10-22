package _07_Others.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.utils.HibernateUtils;
import _07_Others.dao.CommentDao;
import _07_Others.dao.daoImpl.CommentDaoImpl;
import _07_Others.model.CommentBean;
import _07_Others.service.CommentService;

public class CommentServiceImpl implements CommentService {

	CommentDao commentDao;
	SessionFactory factory;
	
	public CommentServiceImpl() {
		this.commentDao = new CommentDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<CommentBean> findAllCommentBeans() {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<CommentBean> commentBeans = null;
		try {
			tx = session.beginTransaction();
			commentBeans = commentDao.findAllCommentBeans();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return commentBeans;
	}
	
	@Override
	public void save(CommentBean commentBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			commentDao.save(commentBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public BigDecimal avgStarForDrink(Integer product_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		BigDecimal avgStar = null;
		try {
			tx = session.beginTransaction();
			avgStar = commentDao.avgStarForDrink(product_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
		return avgStar;
	}

	@Override
	public List<CommentBean> findCommentBeansById(Integer product_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<CommentBean> list = null;
		try {
			tx = session.beginTransaction();
			list = commentDao.findCommentBeansById(product_id);
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
	public List<CommentBean> findCommentBeansByOrderId(String order_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<CommentBean> list = null;
		try {
			tx = session.beginTransaction();
			list = commentDao.findCommentBeansByOrderId(order_id);
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
