package _03_ListDrinks.model;

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

import _01_Register.b_01_register.model.CompanyBean;


@Entity
@Table(name = "topping")
public class ToppingBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer topping_id;
	private String topping_name;
	private Integer topping_cal;
	private Integer topping_price;
	private String company_id;
	private String topping_picname;
	private String topping_picpath;
	@Column(columnDefinition = "TINYINT(1) default 1" )
	private boolean enabled;
	
	
	@ManyToOne
	@JoinColumn(name="FK_CompanyBean_company_id")
	private CompanyBean companyBean;
	
	public ToppingBean() {
		super();
	}

	public ToppingBean(Integer topping_id, String topping_name, Integer topping_cal, Integer topping_price,
			String company_id, String topping_picname, String topping_picpath, boolean enabled) {
		super();
		this.topping_id = topping_id;
		this.topping_name = topping_name;
		this.topping_cal = topping_cal;
		this.topping_price = topping_price;
		this.company_id = company_id;
		this.topping_picname = topping_picname;
		this.topping_picpath = topping_picpath;
		this.enabled = enabled;
	}

	public Integer getTopping_id() {
		return topping_id;
	}

	public void setTopping_id(Integer topping_id) {
		this.topping_id = topping_id;
	}

	public String getTopping_name() {
		return topping_name;
	}

	public void setTopping_name(String topping_name) {
		this.topping_name = topping_name;
	}

	public Integer getTopping_cal() {
		return topping_cal;
	}

	public void setTopping_cal(Integer topping_cal) {
		this.topping_cal = topping_cal;
	}

	public Integer getTopping_price() {
		return topping_price;
	}

	public void setTopping_price(Integer topping_price) {
		this.topping_price = topping_price;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getTopping_picname() {
		return topping_picname;
	}

	public void setTopping_picname(String topping_picname) {
		this.topping_picname = topping_picname;
	}

	public String getTopping_picpath() {
		return topping_picpath;
	}

	public void setTopping_picpath(String topping_picpath) {
		this.topping_picpath = topping_picpath;
	}

	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}
	
	
	

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ToppingBean [topping_id=");
		builder.append(topping_id);
		builder.append(", topping_name=");
		builder.append(topping_name);
		builder.append(", topping_cal=");
		builder.append(topping_cal);
		builder.append(", topping_price=");
		builder.append(topping_price);
		builder.append(", company_id=");
		builder.append(company_id);
		builder.append(", topping_picname=");
		builder.append(topping_picname);
		builder.append(", topping_picpath=");
		builder.append(topping_picpath);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}



	
	
}
