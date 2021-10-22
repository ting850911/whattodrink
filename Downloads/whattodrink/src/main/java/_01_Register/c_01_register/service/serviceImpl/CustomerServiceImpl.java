package _01_Register.c_01_register.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.HibernateUtils;
import _01_Register.c_01_register.dao.CustomerDao;
import _01_Register.c_01_register.dao.daoImpl.CustomerDaoImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _04_ShoppingCart.model.OrderBean;


public class CustomerServiceImpl implements CustomerService {
	private static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	CustomerDao customerDao;
	
	SessionFactory factory;

	public CustomerServiceImpl() {
		this.customerDao = new CustomerDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	public CustomerServiceImpl(CustomerDao memberDao) {
		this.customerDao = memberDao;
		this.factory = HibernateUtils.getSessionFactory();
	}
	

	@Override
	public void save(CustomerBean customerBean) {
		log.info("顧客會員-註冊功能-Service: 儲存顧客會員資料");
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerDao.saveCustomer(customerBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)  
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public boolean existsByCustomerAccount(String customer_account) {
		log.info("顧客會員-註冊功能-Service: 檢查顧客會員輸入的編號是否已被使用");
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = customerDao.existsByCustomerAccount(customer_account);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)  
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return exist;
	}

	@Override
	public boolean existsByCustomerEmail(String email) {
		log.info("顧客會員-註冊功能-Service: 檢查顧客會員輸入的email是否已被使用");
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = customerDao.existsByCustomerEmail(email);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)  
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return exist;
	}

	
	@Override
	public CustomerBean findByCustomerAccount(String customer_account) {
		log.info("顧客會員-登入功能-Service: 由會員編號找出對應的會員");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByCustomerAccount(customer_account);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) 
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}
	
	@Override
	public CustomerBean findByCustomerAccountAndPassword(String customer_account, String customer_password) {
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByCustomerAccountAndPassword(customer_account, customer_password);
	    	log.info("顧客會員-登入功能-Service: 檢查帳號/密碼結果:" 
					+ (customerBean != null? "帳號/密碼正確" : "帳號/密碼錯誤"));
	    	tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}

	
	@Override
	public CustomerBean findByCustomerVerification(String customer_account, String customer_verification) {
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByCustomerVerification(customer_account, customer_verification);
	    	log.info("顧客會員-登入功能-Service: 檢查驗證碼結果: " 
					+ (customerBean != null? "驗證碼正確" : "驗證碼錯誤"));
	    	tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}

	
	@Override
	public void updateCustomer(CustomerBean customerBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerDao.updateCustomer(customerBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		log.info("顧客會員-Service: 更新顧客會員資料完成" );
	}

	@Override
	public boolean checkInvited(String customer_account) {
		log.info("顧客會員Service: 檢查顧客會員是否有好友邀請碼");
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = customerDao.checkInvited(customer_account);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)  
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return exist;
	}

	@Override
	 public boolean existsByInvitation(String invitation) {
	  Session session = factory.getCurrentSession();
	  boolean exist;
	  Transaction tx = null;
	  try {
	   tx = session.beginTransaction();
	   exist = customerDao.existsByInvitation(invitation);
	   tx.commit();
	  } catch (Exception ex) {
	   if (tx != null)
	    tx.rollback();
	   ex.printStackTrace();
	   throw new RuntimeException(ex);
	  }
	  log.info("顧客會員-Service: 檢查自動產生的邀請碼是否重複" );
	  return exist;
	 }

	@Override
	public CustomerBean findByCustomerId(int customer_id) {
		log.info("顧客會員-登入功能-Service: 由會員ID找出對應的會員");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByCustomerId(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) 
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}

	@Override
	public CustomerBean findByInvitation(String invitation) {
		log.info("顧客會員-登入功能-Service: 由邀請碼找出對應的會員");
		Session session = factory.getCurrentSession();
		CustomerBean customerBean = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByInvitation(invitation);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) 
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}

	@Override
	public void updateCustomers(CustomerBean... customerBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerDao.updateCustomers(customerBean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		log.info("顧客會員-Service: 更新顧客會員資料完成" );
		
	}

	@Override
	public boolean existsByAccountAndVerificationCode(String customer_account, String verificationCode) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean exist;
		try {
			tx = session.beginTransaction();
			exist = customerDao.existsByAccountAndVerificationCode(customer_account, verificationCode);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return exist;
	}
	
	@Override
	public boolean existsByEmailAndVerificationCode(String email, String verificationCode) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean exist;
		try {
			tx = session.beginTransaction();
			exist = customerDao.existsByEmailAndVerificationCode(email, verificationCode);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return exist;
	}

	@Override
	public CustomerBean findByEmail(String email) {
		log.info("顧客會員-登入功能-Service: 由Email找出對應的會員");
		CustomerBean customerBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			customerBean = customerDao.findByEmail(email);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) 
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return customerBean;
	}

	@Override
	public List<Map<String, Object>> findAllHistoryOrdersByCustomerId(Integer customer_id) {

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<Map<String, Object>> list = null;

		try {
			tx = session.beginTransaction();
			list = customerDao.findAllHistoryOrdersByCustomerId(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCurrentOrdersByCustomerId(Integer customer_id) {

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<Map<String, Object>> list = null;

		try {
			tx = session.beginTransaction();
			list = customerDao.findAllCurrentOrdersByCustomerId(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
	
	@Override
	public List<OrderBean> findAllDeleteOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<OrderBean> list = null;

		try {
			tx = session.beginTransaction();
			list = customerDao.findAllDeleteOrdersByCustomerId(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
	
	@Override
	public List<OrderBean> findTodayOrdersByCustomerId(Integer customer_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<OrderBean> list = null;

		try {
			tx = session.beginTransaction();
			list = customerDao.findTodayOrdersByCustomerId(customer_id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
}
