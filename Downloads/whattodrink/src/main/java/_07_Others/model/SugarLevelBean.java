package _07_Others.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import _04_ShoppingCart.model.ItemBean;

@Entity
@Table(name = "sugar_level")
public class SugarLevelBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer sugar_id;
	
	private String sugar_level;
	
	private BigDecimal factor;
	
	
	@OneToMany(mappedBy="sugarLevelBean", cascade=CascadeType.ALL)
	private Set<ItemBean> items = new LinkedHashSet<>();
	

	@OneToMany(mappedBy="sugarLevelBean", cascade=CascadeType.ALL)
	private Set<SugarLimitBean> sugarLimits = new LinkedHashSet<>();
	

	public SugarLevelBean() {
		super();
	}



	public SugarLevelBean(Integer sugar_id, String sugar_level, BigDecimal factor) {
		super();
		this.sugar_id = sugar_id;
		this.sugar_level = sugar_level;
		this.factor = factor;
	}



	public Integer getSugar_id() {
		return sugar_id;
	}


	public void setSugar_id(Integer sugar_id) {
		this.sugar_id = sugar_id;
	}


	public String getSugar_level() {
		return sugar_level;
	}


	public void setSugar_level(String sugar_level) {
		this.sugar_level = sugar_level;
	}


	public BigDecimal getFactor() {
		return factor;
	}


	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}


	public Set<ItemBean> getItems() {
		return items;
	}


	public void setItems(Set<ItemBean> items) {
		this.items = items;
	}


	public Set<SugarLimitBean> getSugarLimits() {
		return sugarLimits;
	}


	public void setSugarLimits(Set<SugarLimitBean> sugarLimits) {
		this.sugarLimits = sugarLimits;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SugarLevelBean [sugar_id=");
		builder.append(sugar_id);
		builder.append(", sugar_level=");
		builder.append(sugar_level);
		builder.append(", factor=");
		builder.append(factor);
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
}
