package _07_Others.dao;

import java.math.BigDecimal;
import java.util.List;

import _07_Others.model.CommentBean;

public interface CommentDao {
	
	List<CommentBean> findAllCommentBeans();

	void save(CommentBean commentBean);

	List<CommentBean> findCommentBeansByOrderId(String order_id);
	
	
	// yu
	BigDecimal avgStarForDrink(Integer product_id);
	
	List<CommentBean> findCommentBeansById(Integer product_id);
}
