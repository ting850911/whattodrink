package _03_ListDrinks.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import _01_Register.b_01_register.model.CompanyBean;
import _07_Others.model.CommentBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLimitBean;


@Entity
@Table(name = "product")
public class DrinkBean implements Serializable,Comparable<DrinkBean>{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	private String company_id;
	private String product_name;
	private Integer product_price;
	private String capacity;
	private Integer product_cal;
	private Integer category_id;
	private String product_picname;
	private String product_picpath;	
	private Timestamp add_date;
	private Timestamp alter_date;
	@Column(columnDefinition = "TINYINT(1) default 1" )
	private boolean enabled;
	
	@Transient
	private BigDecimal avgStar;
	
	
	@OneToMany(mappedBy = "drinkBean", cascade=CascadeType.ALL)
	private Set<TagBean> tags = new LinkedHashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name="FK_CompanyBean_company_id")
	private CompanyBean companyBean;
	
	@ManyToOne
	@JoinColumn(name="FK_CategoryBean_category_id")
	private CategoryBean category;
	
	public DrinkBean() {
		super();
	}
	
	@OneToMany(mappedBy = "drinkBean", cascade=CascadeType.ALL)
	private List<SugarLimitBean> sugarLimits = new ArrayList<>();
	
	@OneToMany(mappedBy = "drinkBean", cascade=CascadeType.ALL)
	private List<TempLimitBean> tempLimits = new ArrayList<>();
	
	@OneToMany(mappedBy = "drinkBean", cascade=CascadeType.ALL)
	private List<CommentBean> comments = new ArrayList<>();
	
	

	public DrinkBean(Integer product_id, String company_id, String product_name, Integer product_price, String capacity,
			Integer product_cal, Integer category_id, String product_picname, String product_picpath,
			Timestamp add_date, Timestamp alter_date, boolean enabled) {
		super();
		this.product_id = product_id;
		this.company_id = company_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.capacity = capacity;
		this.product_cal = product_cal;
		this.category_id = category_id;
		this.product_picname = product_picname;
		this.product_picpath = product_picpath;
		this.add_date = add_date;
		this.alter_date = alter_date;
		this.enabled = enabled;
	}




	public String getCompany_id() {
		return company_id;
	}




	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}




	public boolean getEnabled() {
		return enabled;
	}




	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}




	public BigDecimal getAvgStar() {
		return avgStar;
	}




	public void setAvgStar(BigDecimal avgStar) {
		this.avgStar = avgStar;
	}




	public Integer getCategory_id() {
		return category_id;
	}




	public List<CommentBean> getComments() {
		return comments;
	}



	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}


	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}




	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Integer getProduct_cal() {
		return product_cal;
	}

	public void setProduct_cal(Integer product_cal) {
		this.product_cal = product_cal;
	}

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public String getProduct_picname() {
		return product_picname;
	}

	public void setProduct_picname(String product_picname) {
		this.product_picname = product_picname;
	}

	public String getProduct_picpath() {
		return product_picpath;
	}

	public void setProduct_picpath(String product_picpath) {
		this.product_picpath = product_picpath;
	}

	public Timestamp getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Timestamp add_date) {
		this.add_date = add_date;
	}

	public Timestamp getAlter_date() {
		return alter_date;
	}

	public void setAlter_date(Timestamp alter_date) {
		this.alter_date = alter_date;
	}

	public Set<TagBean> getTags() {
		return tags;
	}

	public void setTags(Set<TagBean> tags) {
		this.tags = tags;
	}


	public List<SugarLimitBean> getSugarLimits() {
		return sugarLimits;
	}

	public void setSugarLimits(List<SugarLimitBean> sugarLimits) {
		this.sugarLimits = sugarLimits;
	}

	public List<TempLimitBean> getTempLimits() {
		return tempLimits;
	}

	public void setTempLimits(List<TempLimitBean> tempLimits) {
		this.tempLimits = tempLimits;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrinkBean [product_id=");
		builder.append(product_id);
		builder.append(", company_id=");
		builder.append(company_id);
		builder.append(", product_name=");
		builder.append(product_name);
		builder.append(", product_price=");
		builder.append(product_price);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append(", product_cal=");
		builder.append(product_cal);
		builder.append(", category_id=");
		builder.append(category_id);
		builder.append(", product_picname=");
		builder.append(product_picname);
		builder.append(", product_picpath=");
		builder.append(product_picpath);
		builder.append(", add_date=");
		builder.append(add_date);
		builder.append(", alter_date=");
		builder.append(alter_date);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", companyBean=");
		builder.append(companyBean);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int compareTo(DrinkBean o) {
		return Double.compare(Double.parseDouble(this.getCompanyBean().getDistance()),Double.parseDouble(o.getCompanyBean().getDistance()));
	}
	
}
