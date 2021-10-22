package _01_Register.c_01_register.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import _04_ShoppingCart.model.OrderBean;
import _07_Others.model.CommentBean;
import _07_Others.model.FavoriteBean;
@Entity
@Table(name="customer")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer customer_id;
	String customer_account;
	String customer_password;
	Timestamp sign_date;
	String customer_name;
	Date birthday;
	String email;
	String customer_address;
	Timestamp alter_date;
	String invitation;
	String invited_by;
	Double weight;
	String customer_verification;

	Integer invitationCount;
	
	
	@OneToMany(mappedBy = "customerBean", cascade=CascadeType.ALL)
	private Set<FavoriteBean> favorites = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "customerBean", cascade=CascadeType.ALL)
	private Set<CommentBean> comments = new LinkedHashSet<>();
	
	
	@OneToMany(mappedBy = "customerBean", cascade=CascadeType.ALL)
	private Set<OrderBean> orders = new LinkedHashSet<>();
	
	
	public CustomerBean() {
		super();
	}

	public CustomerBean(String customer_account, String customer_password) {
		super();
		this.customer_account = customer_account;
		this.customer_password = customer_password;
	}

	
	
	// 顧客會員帳戶設定用
	public CustomerBean(String customer_name, Date birthday, String email,
			String customer_address, Double weight) {
		super();
		this.customer_name = customer_name;
		this.birthday = birthday;
		this.email = email;
		this.customer_address = customer_address;
		this.weight = weight;
	}

	public CustomerBean(Integer customer_id, String customer_account, String customer_password) {
		super();
		this.customer_id = customer_id;
		this.customer_account = customer_account;
		this.customer_password = customer_password;
	}


	public CustomerBean(Integer customer_id, String customer_account, String customer_password, Timestamp sign_date,
			String customer_name, Date birthday, String email, String customer_address, Timestamp alter_date,
			String invitation, String invited_by, Double weight) {
		super();
		this.customer_id = customer_id;
		this.customer_account = customer_account;
		this.customer_password = customer_password;
		this.sign_date = sign_date;
		this.customer_name = customer_name;
		this.birthday = birthday;
		this.email = email;
		this.customer_address = customer_address;
		this.alter_date = alter_date;
		this.invitation = invitation;
		this.invited_by = invited_by;
		this.weight = weight;
	}


	public CustomerBean(Integer customer_id, String customer_account, String customer_password, Timestamp sign_date,
			String customer_name, Date birthday, String email, String customer_address, Timestamp alter_date,
			String invitation, String invited_by, Double weight, String customer_verification,
			Integer invitationCount) {
		super();
		this.customer_id = customer_id;
		this.customer_account = customer_account;
		this.customer_password = customer_password;
		this.sign_date = sign_date;
		this.customer_name = customer_name;
		this.birthday = birthday;
		this.email = email;
		this.customer_address = customer_address;
		this.alter_date = alter_date;
		this.invitation = invitation;
		this.invited_by = invited_by;
		this.weight = weight;
		this.customer_verification = customer_verification;
		this.invitationCount = invitationCount;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_account() {
		return customer_account;
	}

	public void setCustomer_account(String customer_account) {
		this.customer_account = customer_account;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public Timestamp getSign_date() {
		return sign_date;
	}

	public void setSign_date(Timestamp sign_date) {
		this.sign_date = sign_date;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public Timestamp getAlter_date() {
		return alter_date;
	}

	public void setAlter_date(Timestamp alter_date) {
		this.alter_date = alter_date;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	public String getInvited_by() {
		return invited_by;
	}

	public void setInvited_by(String invited_by) {
		this.invited_by = invited_by;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Set<FavoriteBean> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<FavoriteBean> favorites) {
		this.favorites = favorites;
	}

	public Set<CommentBean> getComments() {
		return comments;
	}

	public void setComments(Set<CommentBean> comments) {
		this.comments = comments;
	}

	public Set<OrderBean> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderBean> orders) {
		this.orders = orders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getCustomer_verification() {
		return customer_verification;
	}

	public void setCustomer_verification(String customer_verification) {
		this.customer_verification = customer_verification;
	}

	
	
	
	public Integer getInvitationCount() {
		return invitationCount;
	}

	public void setInvitationCount(Integer invitationCount) {
		this.invitationCount = invitationCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerBean [customer_id=");
		builder.append(customer_id);
		builder.append(", customer_account=");
		builder.append(customer_account);
		builder.append(", customer_password=");
		builder.append(customer_password);
		builder.append(", sign_date=");
		builder.append(sign_date);
		builder.append(", customer_name=");
		builder.append(customer_name);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", email=");
		builder.append(email);
		builder.append(", customer_address=");
		builder.append(customer_address);
		builder.append(", alter_date=");
		builder.append(alter_date);
		builder.append(", invitation=");
		builder.append(invitation);
		builder.append(", invited_by=");
		builder.append(invited_by);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", customer_verification=");
		builder.append(customer_verification);
		builder.append(", invitationCount=");
		builder.append(invitationCount);
		builder.append("]");
		return builder.toString();
	}

	
}
