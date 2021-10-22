package _04_ShoppingCart.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import _03_ListDrinks.model.ToppingBean;

@Entity
@Table(name = "item_topping")
public class ItemToppingBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer i_t_seq;
	private Integer item_id;
	private Integer topping_id;

	
 	@ManyToOne
	@JoinColumn(name="FK_ItemBean_item_id")
	private ItemBean itemBean;
 	
 	@ManyToOne
	@JoinColumn(name="FK_ToppingBean_topping_id")
	private ToppingBean toppingBean;

 	
 	
	public ItemToppingBean() {
		super();
	}





	public ItemToppingBean(Integer i_t_seq, Integer item_id, Integer topping_id) {
		super();
		this.i_t_seq = i_t_seq;
		this.item_id = item_id;
		this.topping_id = topping_id;
	}





	public Integer getI_t_seq() {
		return i_t_seq;
	}



	public void setI_t_seq(Integer i_t_seq) {
		this.i_t_seq = i_t_seq;
	}




	public ItemBean getItemBean() {
		return itemBean;
	}



	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}



	public ToppingBean getToppingBean() {
		return toppingBean;
	}



	public void setToppingBean(ToppingBean toppingBean) {
		this.toppingBean = toppingBean;
	}





	public Integer getItem_id() {
		return item_id;
	}





	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}





	public Integer getTopping_id() {
		return topping_id;
	}





	public void setTopping_id(Integer topping_id) {
		this.topping_id = topping_id;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemToppingBean [i_t_seq=");
		builder.append(i_t_seq);
		builder.append(", item_id=");
		builder.append(item_id);
		builder.append(", topping_id=");
		builder.append(topping_id);
		builder.append(", itemBean=");
		builder.append(itemBean);
		builder.append(", toppingBean=");
		builder.append(toppingBean);
		builder.append("]");
		return builder.toString();
	}


 	
}
