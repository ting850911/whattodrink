package _03_ListDrinks.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _01_Register.b_01_register.model.CompanyBean;


@Entity
@Table(name = "category")
public class CategoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	private String category_name;
	private String company_id;
	
	@ManyToOne
	@JoinColumn(name="FK_CompanyBean_company_id")
	private CompanyBean	companyBean;
	
	
	public CategoryBean() {
		super();
	}


	public CategoryBean(Integer category_id, String category_name, String company_id) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.company_id = company_id;
	}


	public Integer getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}


	public CompanyBean getCompanyBean() {
		return companyBean;
	}


	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryBean [category_id=");
		builder.append(category_id);
		builder.append(", category_name=");
		builder.append(category_name);
		builder.append(", company_id=");
		builder.append(company_id);
		builder.append(", companyBean=");
		builder.append(companyBean);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
}
