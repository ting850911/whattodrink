package _04_ShoppingCart.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import _03_ListDrinks.model.DrinkBean;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.TempLevelBean;

@Entity
@Table(name = "item")
public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer item_id;
	private String order_id;
	private Integer product_id;
	private Integer temp_id;
	private Integer sugar_id;
	private String capacity;
	private Integer item_cal;		 //itemBean 加總配料後的每杯cal 買家點選加入健康提醒時要乘上add_to_health並重新set一次此欄位 = 要加入健康提醒的總卡路里
	private Integer price;			 //itemBean 加總配料後的熜金額
	private String message;			 // 傳情
//	private Boolean add_to_health; 
	private int add_to_health;		 //AddToCart時預設為0 要加入健康提醒的數量  (買家點選加入健康提醒時要set此值)
	private String itemStatus;

	private Integer quantity;
	private String note; // 備註

	@OneToMany(mappedBy = "itemBean", cascade = CascadeType.ALL)
	private Set<ItemToppingBean> itemToppings = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "FK_OrderBean_order_id")
	private OrderBean orderBean;

	@ManyToOne
	@JoinColumn(name = "FK_DrinkBean_product_id")
	private DrinkBean drinkBean;

	@ManyToOne
	@JoinColumn(name = "FK_TempLevelBean_temp_id")
	private TempLevelBean tempLevelBean;

	@ManyToOne
	@JoinColumn(name = "FK_SugarLevelBean_sugar_id")
	private SugarLevelBean sugarLevelBean;

	public ItemBean() {
		super();
	}

	public ItemBean(Integer item_id, String order_id, Integer product_id, Integer temp_id, Integer sugar_id,
			String capacity, Integer item_cal, Integer price, String message, int add_to_health, String itemStatus,
			Integer quantity, String note) {
		super();
		this.item_id = item_id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.temp_id = temp_id;
		this.sugar_id = sugar_id;
		this.capacity = capacity;
		this.item_cal = item_cal;
		this.price = price;
		this.message = message;
		this.add_to_health = add_to_health;
		this.itemStatus = itemStatus;
		this.quantity = quantity;
		this.note = note;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Integer getItem_cal() {
		return item_cal;
	}

	public void setItem_cal(Integer item_cal) {
		this.item_cal = item_cal;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAdd_to_health() {
		return add_to_health;
	}

	public void setAdd_to_health(int add_to_health) {
		this.add_to_health = add_to_health;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Set<ItemToppingBean> getItemToppings() {
		return itemToppings;
	}

	public void setItemToppings(Set<ItemToppingBean> itemToppings) {
		this.itemToppings = itemToppings;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public DrinkBean getDrinkBean() {
		return drinkBean;
	}

	public void setDrinkBean(DrinkBean drinkBean) {
		this.drinkBean = drinkBean;
	}

	public TempLevelBean getTempLevelBean() {
		return tempLevelBean;
	}

	public void setTempLevelBean(TempLevelBean tempLevelBean) {
		this.tempLevelBean = tempLevelBean;
	}

	public SugarLevelBean getSugarLevelBean() {
		return sugarLevelBean;
	}

	public void setSugarLevelBean(SugarLevelBean sugarLevelBean) {
		this.sugarLevelBean = sugarLevelBean;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(Integer temp_id) {
		this.temp_id = temp_id;
	}

	public Integer getSugar_id() {
		return sugar_id;
	}

	public void setSugar_id(Integer sugar_id) {
		this.sugar_id = sugar_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemBean [item_id=");
		builder.append(item_id);
		builder.append(", order_id=");
		builder.append(order_id);
		builder.append(", product_id=");
		builder.append(product_id);
		builder.append(", temp_id=");
		builder.append(temp_id);
		builder.append(", sugar_id=");
		builder.append(sugar_id);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append(", item_cal=");
		builder.append(item_cal);
		builder.append(", price=");
		builder.append(price);
		builder.append(", message=");
		builder.append(message);
		builder.append(", add_to_health=");
		builder.append(add_to_health);
		builder.append(", itemStatus=");
		builder.append(itemStatus);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", note=");
		builder.append(note);
		builder.append(", orderBean=");
		builder.append(orderBean);
		builder.append(", drinkBean=");
		builder.append(drinkBean);
		builder.append(", tempLevelBean=");
		builder.append(tempLevelBean);
		builder.append(", sugarLevelBean=");
		builder.append(sugarLevelBean);
		builder.append("]");
		return builder.toString();
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null || getClass() != obj.getClass())
//			return false;
//		ItemBean itemBean = (ItemBean) obj;
//		System.out.println(itemBean.getProduct_id());
//		System.out.println(this.product_id);
//		System.out.println(itemBean.getProduct_id() != this.product_id);
//		
//		if (itemBean.getProduct_id() != this.product_id || itemBean.getSugar_id() != this.getSugar_id()
//				|| itemBean.getTemp_id() != this.getTemp_id()
//				|| !itemBean.getMessage().strip().equals(this.getMessage().strip())
//				|| !itemBean.getNote().strip().equals(this.getNote().strip()))
//			return false;
//
//		if (itemBean.getItemToppings().size() != this.getItemToppings().size()) {
//			return false;
//		} else {
//			Collection<Integer> oldList = new ArrayList<>();
//			for (ItemToppingBean bean : this.getItemToppings()) {
//				oldList.add(bean.getTopping_id());
//			}
//			Collection<Integer> newList = new ArrayList<>();
//			for (ItemToppingBean bean : itemBean.getItemToppings()) {
//				newList.add(bean.getTopping_id());
//			}
//			return oldList.containsAll(newList);
//		}
//
//	}

}
