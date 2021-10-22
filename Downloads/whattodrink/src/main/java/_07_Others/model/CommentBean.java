package _07_Others.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _01_Register.c_01_register.model.CustomerBean;
import _03_ListDrinks.model.DrinkBean;
import _04_ShoppingCart.model.OrderBean;

@Entity
@Table(name = "comments")
public class CommentBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comment_id;
	
	private String order_id;

	private Integer product_id;
	
	private Integer customer_id;
	
	private Timestamp comment_date;
	
	private String 	comment_picpath;
	
	private String 	comment_picname;
	
	private BigDecimal star;
	
	private String feedback;
	
	
 	@ManyToOne
	@JoinColumn(name="FK_OrderBean_order_id")
	private OrderBean orderBean;
 	
 	@ManyToOne
	@JoinColumn(name="FK_CustomerBean_customer_id")
	private CustomerBean customerBean;
 	
 	@ManyToOne
	@JoinColumn(name="FK_DrinkBean_product_id")
	private DrinkBean drinkBean;

 	
	public CommentBean() {
		super();
	}





	public CommentBean(Integer comment_id, String order_id, Integer product_id, Integer customer_id,
			Timestamp comment_date, String comment_picpath, String comment_picname, BigDecimal star, String feedback) {
		super();
		this.comment_id = comment_id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.customer_id = customer_id;
		this.comment_date = comment_date;
		this.comment_picpath = comment_picpath;
		this.comment_picname = comment_picname;
		this.star = star;
		this.feedback = feedback;
	}





	public Integer getComment_id() {
		return comment_id;
	}




	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}




	public String getOrder_id() {
		return order_id;
	}





	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}





	public DrinkBean getDrinkBean() {
		return drinkBean;
	}





	public void setDrinkBean(DrinkBean drinkBean) {
		this.drinkBean = drinkBean;
	}





	public Integer getProduct_id() {
		return product_id;
	}




	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}




	public Integer getCustomer_id() {
		return customer_id;
	}




	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}




	public Timestamp getComment_date() {
		return comment_date;
	}




	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}




	public String getComment_picpath() {
		return comment_picpath;
	}




	public void setComment_picpath(String comment_picpath) {
		this.comment_picpath = comment_picpath;
	}




	public String getComment_picname() {
		return comment_picname;
	}




	public void setComment_picname(String comment_picname) {
		this.comment_picname = comment_picname;
	}




	public BigDecimal getStar() {
		return star;
	}




	public void setStar(BigDecimal star) {
		this.star = star;
	}




	public String getFeedback() {
		return feedback;
	}




	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}




	public OrderBean getOrderBean() {
		return orderBean;
	}




	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}




	public CustomerBean getCustomerBean() {
		return customerBean;
	}




	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentBean [comment_id=");
		builder.append(comment_id);
		builder.append(", order_id=");
		builder.append(order_id);
		builder.append(", product_id=");
		builder.append(product_id);
		builder.append(", customer_id=");
		builder.append(customer_id);
		builder.append(", comment_date=");
		builder.append(comment_date);
		builder.append(", comment_picpath=");
		builder.append(comment_picpath);
		builder.append(", comment_picname=");
		builder.append(comment_picname);
		builder.append(", star=");
		builder.append(star);
		builder.append(", feedback=");
		builder.append(feedback);
		builder.append(", orderBean=");
		builder.append(orderBean);
		builder.append(", customerBean=");
		builder.append(customerBean);
		builder.append(", drinkBean=");
		builder.append(drinkBean);
		builder.append("]");
		return builder.toString();
	}




 	
 	
 	

}
