package _07_Others.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import _04_ShoppingCart.model.ItemBean;

@Entity
@Table(name = "temp_level")
public class TempLevelBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer temp_id;
	
	private String temp_level;
	
	@OneToMany(mappedBy="tempLevelBean", cascade=CascadeType.ALL)
	private Set<ItemBean> items = new LinkedHashSet<>();
	

	@OneToMany(mappedBy="tempLevelBean", cascade=CascadeType.ALL)
	private Set<TempLimitBean> tempLimits = new LinkedHashSet<>();


	public TempLevelBean() {
		super();
	}






	public TempLevelBean(Integer temp_id, String temp_level) {
		super();
		this.temp_id = temp_id;
		this.temp_level = temp_level;
	}






	public Integer getTemp_id() {
		return temp_id;
	}


	public void setTemp_id(Integer temp_id) {
		this.temp_id = temp_id;
	}


	public String getTemp_level() {
		return temp_level;
	}


	public void setTemp_level(String temp_level) {
		this.temp_level = temp_level;
	}


	public Set<ItemBean> getItems() {
		return items;
	}


	public void setItems(Set<ItemBean> items) {
		this.items = items;
	}


	public Set<TempLimitBean> getTempLimits() {
		return tempLimits;
	}


	public void setTempLimits(Set<TempLimitBean> tempLimits) {
		this.tempLimits = tempLimits;
	}






	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TempLevelBean [temp_id=");
		builder.append(temp_id);
		builder.append(", temp_level=");
		builder.append(temp_level);
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
}
