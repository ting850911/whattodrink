package _04_ShoppingCart.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.HibernateUtils;
import _04_ShoppingCart.dao.OrderDao;
import _04_ShoppingCart.dao.daoImpl.OrderDaoImpl;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.OrderService;

public class OrderServiceImpl implements OrderService{
		
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	SessionFactory factory;
	OrderDao orderDao;
	

	public OrderServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		orderDao = new OrderDaoImpl();
	}

	@Override
	public void save(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單之Service:交易開始，準備儲存訂單");
			orderDao.save(orderBean);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();				
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderBean findById(String orderId) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ob = orderDao.findById(orderId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}		
		return ob;
	}
	
	@Override //商家明細頁面限定用途，其他處請使用上方
	public OrderBean findByOrderId(String orderId) {
		return orderDao.findByOrderId(orderId);
	}

	@Override
	public List<OrderBean> findByCompanyId(String companyId) {
		List<OrderBean>  beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = orderDao.findByCompanyId(companyId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
		return beans;
	}

	@Override
	public List<OrderBean> findByCustomerAccount(String CustomerAccount) {
		List<OrderBean>  beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = orderDao.findByCustomerAccount(CustomerAccount);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
		return beans;
	}

	@Override
	public void updateOrderStatus(OrderBean orderBean, String orderStatus) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單之Service: 更新訂單狀態");
			orderDao.updateOrderStatus(orderBean, orderStatus);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();				
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}		
	}

	@Override
	public void deleteOrderById(String orderId) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單之Service:刪除訂單");
			orderDao.deleteOrderById(orderId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();				
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getOrderIdByCompanyName(String companyName) {
		String orderId = "";
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			log.info("處理訂單之Service: 使用companyName產生orderId");
			orderId = orderDao.getOrderIdByCompanyName(companyName);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}		
		return orderId;
	}

	@Override
	public List<OrderBean> findByCustomerId(int customerId) {
		List<OrderBean>  beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = orderDao.findByCustomerId(customerId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
		return beans;
	}

	@Override
	public String getLastOrderIdByCompanyId(String companyId) {
		String orderId = "";
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			log.info("處理訂單之Service: 使用companyName產生orderId");
			orderId = orderDao.getLastOrderIdByCompanyId(companyId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}		
		return orderId;
	}

	@Override
	 public Map<String, Object> createDailyReport(String companyName) {
	  Map<String, Object>  monthlyReport = null;
	  Session session = factory.getCurrentSession();
	  Transaction tx = null;
	  try {
	   tx = session.beginTransaction();
	   monthlyReport = orderDao.createDailyReport(companyName);
	   tx.commit();
	  }catch(Exception e) {
	   if(tx != null){
	    tx.rollback();
	   }
	   System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());   
	   throw new RuntimeException(e);
	  }
	  return monthlyReport;
	 }

	@Override
	public void updateOrderBean(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orderDao.updateOrderBean(orderBean);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();				
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}
		
	}


}
