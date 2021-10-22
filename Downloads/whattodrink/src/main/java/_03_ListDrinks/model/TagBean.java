package _03_ListDrinks.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tag_product")
public class TagBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tg_seq;
	private Integer product_id;
	private String tag_name;
	
	
	@ManyToOne
	@JoinColumn(name="FK_DrinkBean_product_id")
	private DrinkBean drinkBean;
	
	
	public TagBean() {
		super();
	}

	public TagBean(Integer tg_seq, Integer product_id, String tag_name) {
		super();
		this.tg_seq = tg_seq;
		this.product_id = product_id;
		this.tag_name = tag_name;
	}

	public Integer getTg_seq() {
		return tg_seq;
	}

	public void setTg_seq(Integer tg_seq) {
		this.tg_seq = tg_seq;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
	

	public DrinkBean getDrinkBean() {
		return drinkBean;
	}

	public void setDrinkBean(DrinkBean drinkBean) {
		this.drinkBean = drinkBean;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TagBean [tg_seq=");
		builder.append(tg_seq);
		builder.append(", product_id=");
		builder.append(product_id);
		builder.append(", tag_name=");
		builder.append(tag_name);
		builder.append(", drinkBean=");
		builder.append(drinkBean);
		builder.append("]");
		return builder.toString();
	}


	
}
