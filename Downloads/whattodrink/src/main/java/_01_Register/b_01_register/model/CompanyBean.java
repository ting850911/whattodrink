package _01_Register.b_01_register.model;

import java.io.Serializable;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;

import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.ToppingBean;
import _04_ShoppingCart.model.OrderBean;
import _07_Others.model.FavoriteBean;


//商家修改資料頁面出現3個資訊，但資料庫沒有欄位 先加上


@Entity
@Table(name="company")
public class CompanyBean implements Serializable,Comparable<CompanyBean> {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String			company_id; 
	private String 			company_name;
	@Column(columnDefinition = "VARCHAR(255) UNIQUE KEY")
	private String 			company_account;
	private String 			company_password;
	private String 			company_owner;
	@Column(columnDefinition = "VARCHAR(255) UNIQUE KEY")
	private String 			company_owner_email;
	private Time 			start_time;
	private Time 			end_time;
	@Column(columnDefinition = "VARCHAR(255) UNIQUE KEY")
	private String 			tel;
	private String 			company_address;
	private String 			website;
	private String 			company_iconpath;
	private String 			company_filename;
	private String 			tax_id_number;
	private Timestamp 		register_date;
	private Timestamp 		alter_date;
	@Column(columnDefinition = "VARCHAR(255) UNIQUE KEY")
	private String 			company_owner_phone; //負責人電話
	private String 			trade_name;			 //分店名稱
	private String 			bg_iconpath;		 //底圖
	private String 			bg_filename;
	private String          invitation;    		 //邀請碼
	private String          business_name;    		 //公司行號
	@Transient
	private String 			distance;

	
//	@JSONField(serialize = false)
	@OneToMany(mappedBy = "companyBean", cascade=CascadeType.ALL)
	private Set<DrinkBean> drinks = new LinkedHashSet<>();
	
//	@JSONField(serialize = false)
	@OneToMany(mappedBy = "companyBean", cascade=CascadeType.ALL)
	private Set<CategoryBean> categories = new LinkedHashSet<>();
	
//	@JSONField(serialize = false)
	@OneToMany(mappedBy = "companyBean", cascade=CascadeType.ALL)
	private Set<ToppingBean> toppings = new LinkedHashSet<>();
	
//	@JSONField(serialize = false)
	@OneToMany(mappedBy = "companyBean", cascade=CascadeType.ALL)
	private Set<OrderBean> orders = new LinkedHashSet<>();
	
//	@JSONField(serialize = false)
	@OneToMany(mappedBy = "companyBean", cascade=CascadeType.ALL)
	private Set<FavoriteBean> favorites = new LinkedHashSet<>();
	
	
	public CompanyBean() {
		super();
	}


	public CompanyBean(String company_id, String company_name, String company_account, String company_password,
			String company_owner, String company_owner_email, Time start_time, Time end_time, String tel,
			String company_address, String website, String company_iconpath, String company_filename,
			String tax_id_number, Timestamp register_date, Timestamp alter_date, String company_owner_phone,
			String trade_name, String bg_iconpath, String bg_filename, String invitation) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.company_account = company_account;
		this.company_password = company_password;
		this.company_owner = company_owner;
		this.company_owner_email = company_owner_email;
		this.start_time = start_time;
		this.end_time = end_time;
		this.tel = tel;
		this.company_address = company_address;
		this.website = website;
		this.company_iconpath = company_iconpath;
		this.company_filename = company_filename;
		this.tax_id_number = tax_id_number;
		this.register_date = register_date;
		this.alter_date = alter_date;
		this.company_owner_phone = company_owner_phone;
		this.trade_name = trade_name;
		this.bg_iconpath = bg_iconpath;
		this.bg_filename = bg_filename;
		this.invitation = invitation;
	}








	public String getBusiness_name() {
		return business_name;
	}


	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}


	public String getInvitation() {
		return invitation;
	}


	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}


	public String getCompany_name() {
		return company_name;
	}


	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}


	public String getCompany_account() {
		return company_account;
	}


	public void setCompany_account(String company_account) {
		this.company_account = company_account;
	}


	public String getCompany_password() {
		return company_password;
	}


	public void setCompany_password(String company_password) {
		this.company_password = company_password;
	}


	public String getCompany_owner() {
		return company_owner;
	}


	public void setCompany_owner(String company_owner) {
		this.company_owner = company_owner;
	}


	public String getCompany_owner_email() {
		return company_owner_email;
	}


	public void setCompany_owner_email(String company_owner_email) {
		this.company_owner_email = company_owner_email;
	}


	public Time getStart_time() {
		return start_time;
	}


	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}


	public Time getEnd_time() {
		return end_time;
	}


	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getCompany_address() {
		return company_address;
	}


	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getCompany_iconpath() {
		return company_iconpath;
	}


	public void setCompany_iconpath(String company_iconpath) {
		this.company_iconpath = company_iconpath;
	}


	public String getCompany_filename() {
		return company_filename;
	}


	public void setCompany_filename(String company_filename) {
		this.company_filename = company_filename;
	}


	public String getTax_id_number() {
		return tax_id_number;
	}


	public void setTax_id_number(String tax_id_number) {
		this.tax_id_number = tax_id_number;
	}


	public Timestamp getRegister_date() {
		return register_date;
	}


	public void setRegister_date(Timestamp register_date) {
		this.register_date = register_date;
	}


	public Timestamp getAlter_date() {
		return alter_date;
	}


	public void setAlter_date(Timestamp alter_date) {
		this.alter_date = alter_date;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public String getCompany_owner_phone() {
		return company_owner_phone;
	}


	public void setCompany_owner_phone(String company_owner_phone) {
		this.company_owner_phone = company_owner_phone;
	}


	public String getTrade_name() {
		return trade_name;
	}


	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}


	public String getBg_iconpath() {
		return bg_iconpath;
	}


	public void setBg_iconpath(String bg_iconpath) {
		this.bg_iconpath = bg_iconpath;
	}


	public String getBg_filename() {
		return bg_filename;
	}


	public void setBg_filename(String bg_filename) {
		this.bg_filename = bg_filename;
	}

	
	

	public Set<DrinkBean> getDrinks() {
		return drinks;
	}


	public void setDrinks(Set<DrinkBean> drinks) {
		this.drinks = drinks;
	}


	public Set<CategoryBean> getCategories() {
		return categories;
	}


	public void setCategories(Set<CategoryBean> categories) {
		this.categories = categories;
	}


	public Set<ToppingBean> getToppings() {
		return toppings;
	}


	public void setToppings(Set<ToppingBean> toppings) {
		this.toppings = toppings;
	}



	public String getDistance() {
		return distance;
	}


	public void setDistance(String distance) {
		this.distance = distance;
	}


	public Set<OrderBean> getOrders() {
		return orders;
	}


	public void setOrders(Set<OrderBean> orders) {
		this.orders = orders;
	}


	public Set<FavoriteBean> getFavorites() {
		return favorites;
	}


	public void setFavorites(Set<FavoriteBean> favorites) {
		this.favorites = favorites;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyBean [company_id=");
		builder.append(company_id);
		builder.append(", company_name=");
		builder.append(company_name);
		builder.append(", company_account=");
		builder.append(company_account);
		builder.append(", company_password=");
		builder.append(company_password);
		builder.append(", company_owner=");
		builder.append(company_owner);
		builder.append(", company_owner_email=");
		builder.append(company_owner_email);
		builder.append(", start_time=");
		builder.append(start_time);
		builder.append(", end_time=");
		builder.append(end_time);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", company_address=");
		builder.append(company_address);
		builder.append(", website=");
		builder.append(website);
		builder.append(", company_iconpath=");
		builder.append(company_iconpath);
		builder.append(", company_filename=");
		builder.append(company_filename);
		builder.append(", tax_id_number=");
		builder.append(tax_id_number);
		builder.append(", register_date=");
		builder.append(register_date);
		builder.append(", alter_date=");
		builder.append(alter_date);
		builder.append(", company_owner_phone=");
		builder.append(company_owner_phone);
		builder.append(", trade_name=");
		builder.append(trade_name);
		builder.append(", bg_iconpath=");
		builder.append(bg_iconpath);
		builder.append(", bg_filename=");
		builder.append(bg_filename);
		builder.append(", invitation=");
		builder.append(invitation);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(CompanyBean o) {
		return Double.compare(Double.parseDouble(this.getDistance()),Double.parseDouble(o.getDistance()));
	}

	
}
