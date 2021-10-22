package _07_Others.service;

import java.math.BigDecimal;
import java.util.List;

import _07_Others.model.CommentBean;

public interface CommentService {
	
	List<CommentBean> findAllCommentBeans();
	
	void save(CommentBean commentBean);
	
	BigDecimal avgStarForDrink(Integer product_id);

	List<CommentBean> findCommentBeansById(Integer product_id);

	List<CommentBean> findCommentBeansByOrderId(String order_id);
}
