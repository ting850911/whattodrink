package _07_Others.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _03_ListDrinks.model.DrinkBean;

@Entity
@Table(name = "temp_limit")
public class TempLimitBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tl_sql;
	
	private Integer product_id;
	
	private Integer temp_id;
	@Column(columnDefinition = "TINYINT(1) default 1" )
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "FK_TempLevelBean_temp_id")
	private TempLevelBean tempLevelBean;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_DrinkBean_product_id")
	private DrinkBean drinkBean;


	public TempLimitBean() {
		super();
	}



	public TempLimitBean(Integer tl_sql, Integer product_id, Integer temp_id, boolean enabled) {
		super();
		this.tl_sql = tl_sql;
		this.product_id = product_id;
		this.temp_id = temp_id;
		this.enabled = enabled;
	}



	public Integer getTl_sql() {
		return tl_sql;
	}


	public void setTl_sql(Integer tl_sql) {
		this.tl_sql = tl_sql;
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


	public TempLevelBean getTempLevelBean() {
		return tempLevelBean;
	}


	public void setTempLevelBean(TempLevelBean tempLevelBean) {
		this.tempLevelBean = tempLevelBean;
	}


	public DrinkBean getDrinkBean() {
		return drinkBean;
	}


	public void setDrinkBean(DrinkBean drinkBean) {
		this.drinkBean = drinkBean;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TempLimitBean [tl_sql=");
		builder.append(tl_sql);
		builder.append(", product_id=");
		builder.append(product_id);
		builder.append(", temp_id=");
		builder.append(temp_id);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", tempLevelBean=");
		builder.append(tempLevelBean);
		builder.append(", drinkBean=");
		builder.append(drinkBean);
		builder.append("]");
		return builder.toString();
	}



	

}
