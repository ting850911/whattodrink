package _01_Register.c_01_register.dao.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.HibernateUtils;
import _01_Register.c_01_register.dao.CustomerDao;
import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.OrderBean;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

public class CustomerDaoImpl implements CustomerDao {

	private static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);

	SessionFactory factory;

	public CustomerDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	// 儲存CustomerBean物件，將參數companyBean新增到customer表格內。
	public void saveCustomer(CustomerBean customerBean) {
		log.info("顧客會員-註冊功能-DAO: 儲存顧客會員資料");
		Session session = factory.getCurrentSession();
		session.save(customerBean);
		log.info("顧客會員-註冊功能-DAO-新增成功: customerBean = " + customerBean);
	}

	// 判斷參數（顧客帳號）是否已經被使用，如果是傳回true(此id不能使用)，否則傳回false(此id可使用)。
	@Override
	public boolean existsByCustomerAccount(String customer_account) {
		log.info("顧客會員-註冊功能-DAO: 檢查顧客會員輸入的帳號名稱是否已被使用");
		boolean exist = false;
		String hql = "FROM CustomerBean c WHERE c.customer_account = :account ";
		Session session = factory.getCurrentSession();
		try {
			CustomerBean bean = (CustomerBean) session.createQuery(hql).setParameter("account", customer_account)
					.getSingleResult();
			if (bean != null) {
				exist = true;
			}
		} catch (NoResultException e) {
			exist = false;
		} catch (NonUniqueResultException e) {
			exist = true;
		}
		log.info("顧客會員-註冊功能-DAO: 檢查顧客會員輸入的帳號名稱是否已被使用, exist = " + exist);
		return exist;
	}

	// 由參數（顧客帳號）到customer表格中 取得某個會員的所有資料，傳回值為一個CustomerBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public CustomerBean findByCustomerAccount(String customer_account) {
		log.info("顧客會員-註冊功能-DAO: 檢查帳號/密碼");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.customer_account = :account ";
		try {
			customerBean = (CustomerBean) session.createQuery(hql).setParameter("account", customer_account)
					.getSingleResult();
		} catch (NoResultException e) {
			customerBean = null;
		}
		log.info("顧客會員-註冊功能-DAO: 取得某個顧客會員的資料, customerBean = " + customerBean);
		return customerBean;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的CustomerBean物件，否則傳回 null。
	@Override
	public CustomerBean findByCustomerAccountAndPassword(String customer_account, String customer_password) {
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.customer_account = :account AND c.customer_password = :password";
		try {
			customerBean = (CustomerBean) session.createQuery(hql).setParameter("account", customer_account)
					.setParameter("password", customer_password).getSingleResult();
		} catch (NoResultException e) {
			customerBean = null;
		}
		log.info("顧客會員-註冊功能-DAO: 檢查帳號/密碼結果: " + (customerBean != null ? "帳號/密碼正確" : "帳號/密碼錯誤"));
		return customerBean;
	}
	
	@Override
	public CustomerBean findByCustomerVerification(String customer_account, String customer_verification) {
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.customer_account = :account AND c.customer_verification = :customer_verification";
		try {
			customerBean = session.createQuery(hql, CustomerBean.class).setParameter("account", customer_account)
																	.setParameter("customer_verification", customer_verification)
																	.getSingleResult();
		} catch (NoResultException e) {
			customerBean = null;
		}
		log.info("顧客會員-註冊功能-DAO: 驗證碼核對 " + (customerBean != null ? "驗證碼正確" : "驗證碼錯誤"));
		return customerBean;
	}

	public void updateCustomer(CustomerBean customerBean) {
		Session session = factory.getCurrentSession();
		session.update(customerBean);
		log.info("顧客會員-註冊功能-DAO: 修改customerBean：" + (customerBean != null ? "驗證碼正確" : "驗證碼錯誤"));
	}
	public void forOrderServletUpdateCustomer(CustomerBean customerBean) {
		Session session = factory.getCurrentSession();
		session.update(customerBean);
	}

	@Override
	public boolean existsByCustomerEmail(String email) {
		log.info("顧客會員-註冊功能-DAO: 檢查顧客會員輸入的email是否已被使用");
		boolean exist = false;
		String hql = "FROM CustomerBean c WHERE c.email = :email ";
		Session session = factory.getCurrentSession();
		try {
			CustomerBean bean = (CustomerBean) session.createQuery(hql).setParameter("email", email).getSingleResult();
			if (bean != null) {
				exist = true;
			}
		} catch (NoResultException e) {
			exist = false;
		} catch (NonUniqueResultException e) {
			exist = true;
		}
		log.info("顧客會員-註冊功能-DAO: 檢查顧客會員輸入的email是否已被使用, exist = " + exist);
		return exist;
	}

	@Override
	public boolean checkInvited(String customer_account) {
		log.info("顧客會員DAO: 檢查顧客會員是否有好友邀請碼");
		boolean exist = false;
		String hql = "SELECT c.invited_by FROM CustomerBean c WHERE c.customer_account = :account ";
		Session session = factory.getCurrentSession();
		try {
			String invited_by = session.createQuery(hql, String.class).setParameter("account", customer_account)
					.getSingleResult();
			if (invited_by != null && invited_by.length() > 0) {
				exist = true;
			}
		} catch (NoResultException e) {
			exist = false;
		} catch (NonUniqueResultException e) {
			exist = true;
		}
		log.info("顧客會員-DAO: 檢查顧客會員是否有好友邀請碼, exist= " + exist);
		return exist;
	}

	@Override
	 public boolean existsByInvitation(String invitation) {
	  boolean exist = false;
	  String hql = "FROM CustomerBean c WHERE c.invitation = :invitation";
	  Session session = factory.getCurrentSession();
	  exist = !session.createQuery(hql)
	         .setParameter("invitation", invitation)
	         .getResultList()
	         .isEmpty();
	  return exist;
	 }


	@Override
	public CustomerBean findByCustomerId(int customer_id) {
		log.info("顧客會員-DAO: findByCustomerId");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.customer_id = :id ";
		try {
			customerBean = (CustomerBean) session.createQuery(hql).setParameter("id", customer_id)
					.getSingleResult();
		} catch (NoResultException e) {
			customerBean = null;
		}
		log.info("顧客會員-DAO: findByCustomerId, customerBean = " + customerBean);
		return customerBean;
	}

	
	@Override
	public CustomerBean findByInvitation(String invitation) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.invitation = :invitation";
		CustomerBean customerBean = session.createQuery(hql, CustomerBean.class)
										   .setParameter("invitation", invitation)
										   .getResultList()
										   .get(0);
		return customerBean;
	}

	@Override
	public void updateCustomers(CustomerBean... customerBean) {
		for (CustomerBean cb : customerBean) {
			Session session = factory.getCurrentSession();
			session.update(cb);
		}
	}
	
	@Override
	public boolean existsByAccountAndVerificationCode(String customer_account, String verificationCode) {
		Session session = factory.getCurrentSession();
		boolean exist = false;
		String hql = "FROM CustomerBean c "
				   + "WHERE c.customer_account = :customer_account "
				   + "AND c.customer_verification = :customer_verification";
		exist = !session.createQuery(hql, CustomerBean.class)
					    .setParameter("customer_account", customer_account)
					    .setParameter("customer_verification", verificationCode)
					    .getResultList()
					    .isEmpty();
		return exist;
	}
	
	@Override
	public boolean existsByEmailAndVerificationCode(String email, String verificationCode) {
		Session session = factory.getCurrentSession();
		boolean exist = false;
		String hql = "FROM CustomerBean c "
				   + "WHERE c.email = :email "
				   + "AND c.customer_verification = :customer_verification";
		exist = !session.createQuery(hql, CustomerBean.class)
					    .setParameter("email", email)
					    .setParameter("customer_verification", verificationCode)
					    .getResultList()
					    .isEmpty();
		return exist;
	}

	@Override
	public CustomerBean findByEmail(String email) {
		log.info("顧客會員-忘記密碼功能-DAO: 以Email搜尋會員資料");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.email = :email ";
		try {
			customerBean = (CustomerBean) session.createQuery(hql)
												 .setParameter("email", email)
												 .getSingleResult();
		} catch (NoResultException e) {
			customerBean = null;
		}
		log.info("顧客會員-忘記密碼功能-DAO: 取得某個顧客會員的資料, customerBean = " + customerBean);
		return customerBean;
	}

	
	@Override
	public List<Map<String, Object>> findAllHistoryOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();

		String hql = "SELECT o.order_id, o.order_date, o.orderStatus, o.companyBean.company_name, o.companyBean.trade_name, o.companyBean.company_iconpath, o.customer_id "
				+ "FROM OrderBean o WHERE o.customer_id = :customer_id AND o.orderStatus = '已領取' AND ifnull(o.order_text, ' ') != '商家刪除資料' ORDER BY o.order_date DESC";
		List<Object[]> temp = session.createQuery(hql, Object[].class).setParameter("customer_id", customer_id)
				.getResultList();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		CommentService commentService = new CommentServiceImpl();

		for (Object[] obj : temp) {
			Map<String, Object> map = new LinkedHashMap<>();
			String ordId = (String) obj[0];
			map.put("ordId", ordId);
			Timestamp ts = (Timestamp) obj[1];
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
			map.put("ordTime", date);
			map.put("ordState", obj[2]);
			map.put("storeName", obj[3]);
			map.put("branchName", obj[4]);
			map.put("picPath", obj[5]);
			map.put("review", "???");
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCurrentOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();

		// o.orderStatus = '接單' 改成 o.orderStatus in ('待製作','可領取','待確認')
		String hql = "SELECT o.order_id, o.order_date, o.orderStatus, o.companyBean.company_name, o.companyBean.trade_name, o.companyBean.company_iconpath "
				+ "FROM OrderBean o WHERE o.customer_id = :customer_id AND o.orderStatus in ('待製作','可領取','待確認') AND ifnull(o.order_text, ' ') != '商家刪除資料' ORDER BY o.order_date DESC";
		List<Object[]> temp = session.createQuery(hql, Object[].class).setParameter("customer_id", customer_id)
				.getResultList();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		for (Object[] obj : temp) {
			Map<String, Object> map = new LinkedHashMap<>();
			String ordId = (String) obj[0];
			map.put("ordId", ordId);
			Timestamp ts = (Timestamp) obj[1];
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
			map.put("ordTime", date);
			map.put("ordState", obj[2]);
			map.put("storeName", obj[3]);
			map.put("branchName", obj[4]);
			map.put("picPath", obj[5]);
			list.add(map);
		}
		return list;
	}
	
	@Override
	public List<OrderBean> findAllDeleteOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean o WHERE o.customer_id = :customer_id AND o.orderStatus = '取消' ORDER BY o.order_date DESC";
		List<OrderBean> list = session.createQuery(hql, OrderBean.class).setParameter("customer_id", customer_id).getResultList();
		return list;
	}
	
	@Override
	public List<OrderBean> findTodayOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();

		String hql = "FROM OrderBean o WHERE o.customer_id = :customer_id AND o.orderStatus in ('待製作','可領取','待確認') AND ifnull(o.order_text, ' ') != '商家刪除資料' ORDER BY o.order_date DESC";
		List<OrderBean> list1 = session.createQuery(hql, OrderBean.class).setParameter("customer_id", customer_id)
				.getResultList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		String nowDate = sdf.format(nowTimestamp);
		System.out.println("------------------");
		System.out.println("nowDate" + nowDate);
		System.out.println("------------------");

		List<OrderBean> list2 = new LinkedList<OrderBean>();
		for (int i = 0; i < list1.size(); i++) {
			OrderBean o = list1.get(i);
//			String orderTime = sdf.format(list1.get(i).getOrder_date());
			String orderTime = sdf.format(o.getOrder_date());
//			String orderDate = orderTime.substring(1, 11);
			System.out.println("------------------");
			System.out.println("orderDate" + orderTime);
			System.out.println("------------------");
			if (orderTime.compareTo(nowDate) == 0) {
				list2.add(o);
				System.out.println(o);
			}
		}

		return list2;
	}
}
