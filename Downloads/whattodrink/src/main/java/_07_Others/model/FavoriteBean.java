package _07_Others.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.c_01_register.model.CustomerBean;

@Entity
@Table(name = "favorite")
public class FavoriteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer f_seq;
	private Integer customer_id;
	private String company_id;
	
	
 	@ManyToOne
	@JoinColumn(name="FK_CompanyBean_Company_id")
	private CompanyBean companyBean;
 	
 	@ManyToOne
	@JoinColumn(name="FK_CustomerBean_customer_id")
	private CustomerBean customerBean;

 	
 	
	public FavoriteBean() {
		super();
	}


	

	public FavoriteBean(Integer f_seq, Integer customer_id, String company_id) {
		super();
		this.f_seq = f_seq;
		this.customer_id = customer_id;
		this.company_id = company_id;
	}








	public Integer getCustomer_id() {
		return customer_id;
	}




	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}




	public String getCompany_id() {
		return company_id;
	}




	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}




	public Integer getF_seq() {
		return f_seq;
	}



	public void setF_seq(Integer f_seq) {
		this.f_seq = f_seq;
	}



	public CompanyBean getCompanyBean() {
		return companyBean;
	}



	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}



	public CustomerBean getCustomerBean() {
		return customerBean;
	}






	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FavoriteBean [f_seq=");
		builder.append(f_seq);
		builder.append(", customer_id=");
		builder.append(customer_id);
		builder.append(", company_id=");
		builder.append(company_id);
		builder.append(", companyBean=");
		builder.append(companyBean);
		builder.append(", customerBean=");
		builder.append(customerBean);
		builder.append("]");
		return builder.toString();
	}




	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}



 	
 	
}
