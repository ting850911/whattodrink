package _04_ShoppingCart.dao.daoImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _00_init.utils.GlobalService;
import _00_init.utils.HibernateUtils;
import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.dao.OrderDao;
import _04_ShoppingCart.model.OrderBean;

public class OrderDaoImpl implements OrderDao{
	
	private static Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	SessionFactory factory;
	
	
	public OrderDaoImpl() {
		factory = HibernateUtils.getSessionFactory();		
	}

	
	
	@Override
	public void save(OrderBean orderBean) {
		log.info("儲存訂單(OrderDaoImpl)之Dao: ");
		Session session = factory.getCurrentSession();
		session.save(orderBean);
	}

	@Override
	public OrderBean findById(String orderId) {
		log.info("依照orderId編號讀取特定一筆訂單的所有資料之Dao, orderId=" + orderId);
		Session session = factory.getCurrentSession();
		OrderBean ob = session.get(OrderBean.class, orderId);
		return ob;
	}
	
	@Override
	public OrderBean findByOrderId(String orderId) {
		log.info("依照orderId編號讀取特定一筆訂單的所有資料之Dao, orderId=" + orderId);
		Session session = factory.getCurrentSession();
		OrderBean ob = session.get(OrderBean.class, orderId);
		return ob;
	}

	@Override
	public List<OrderBean> findByCustomerAccount(String customer_account) {		
		log.info("依照customer_account編號讀取特定會員的所有訂單之Dao, customer_account=" + customer_account);
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean c WHERE c.customer_account = :account";
		CustomerBean cb = session.createQuery(hql, CustomerBean.class)
						         .setParameter("account", customer_account)
			  	   		         .getSingleResult();
		
		String hql2 = "FROM OrderBean o WHERE o.customer_id = :id";
		List<OrderBean> beans = session.createQuery(hql2, OrderBean.class)
								       .setParameter("id", cb.getCustomer_id())
					  	   		       .getResultList();
		return beans;
	}



	@Override
	public List<OrderBean> findByCompanyId(String companyId) {
		log.info("依照companyId編號讀取特定公司的所有訂單之Dao, companyId=" + companyId);
		Session session = factory.getCurrentSession();	
		String hql = "FROM OrderBean o WHERE o.company_id = :id";
		List<OrderBean> beans = session.createQuery(hql, OrderBean.class)
									   .setParameter("id", companyId)
									   .getResultList();
								
		return beans;
	}



	@Override
	public void deleteOrderById(String orderId) {
		log.info("依照orderId編號刪除特定訂單之Dao, orderId=" + orderId);
		Session session = factory.getCurrentSession();	
		String hql = "DELETE FROM OrderBean o WHERE o.order_id = :id";			
		session.createQuery(hql).setParameter("id", orderId).executeUpdate();		
	}



	@Override
	public void updateOrderStatus(OrderBean orderBean, String orderStatus) {
		log.info("依照orderBean與orderStatus更新此筆訂單之Dao, orderBean=" + orderBean + ", orderStatus=" + orderStatus);
		Session session = factory.getCurrentSession();
		String hql = "UPDATE OrderBean o SET o.orderStatus = :status WHERE o.order_id = :id";		
		session.createQuery(hql).setParameter("status", orderStatus)
								.setParameter("id", orderBean.getOrder_id())
								.executeUpdate();
		
		
		
		String hqlAddPickUpDate = "UPDATE OrderBean o SET o.pickup_date = :pickup WHERE o.order_id = :id";
		if(orderStatus == "已領取") {
			session.createQuery(hqlAddPickUpDate).setParameter("pickup", new Timestamp(System.currentTimeMillis()))
												 .setParameter("id", orderBean.getOrder_id())
												 .executeUpdate();
		}
	}



	@Override
	public String getOrderIdByCompanyName(String companyName) {
		log.info("依照companyName產生該訂單OrderId之Dao, companyName=" + companyName);
		String orderId = "";
		Session session = factory.getCurrentSession();
		
		ProcedureCall query = session.createStoredProcedureCall("getOrderNo");
		query.registerParameter("companyId", String.class, ParameterMode.IN).bindValue(GlobalService.getCompanyId(companyName));
		query.registerParameter("orderId", String.class, ParameterMode.OUT);
		ProcedureOutputs  procedureOutputs  = query.getOutputs();
		orderId = (String) procedureOutputs.getOutputParameterValue("orderId");
	
		return orderId;
	}



	@Override
	public List<OrderBean> findByCustomerId(int customerId) {
		log.info("依照customerId編號讀取特定會員的所有訂單之Dao, customerId=" + customerId);
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean o WHERE o.customer_id = :account";
		List<OrderBean> beans = session.createQuery(hql, OrderBean.class)
								       .setParameter("account", customerId)
					  	   		       .getResultList();
		return beans;
	}



	@Override
	public String getLastOrderIdByCompanyId(String companyId) {
		log.info("依照companyId產生該訂單OrderId之Dao, companyId=" + companyId);
		Session session = factory.getCurrentSession();
		String hql = "SELECT o.order_id FROM OrderBean o WHERE o.company_id = :id ORDER BY o.order_id DESC";
		List<String> orderId = session.createQuery(hql, String.class)
							         .setParameter("id", companyId)
				  	   		         .getResultList();
		
		if(orderId.size() > 0) {
			return orderId.get(0);
		}
		return null;
	}



	 @Override
	 public Map<String, Object> createDailyReport(String companyName) {
	  log.info("依照companyName編號產生日報表所需數值, companyName=" + companyName);
	  Map<String, Object> dailyReport = new LinkedHashMap<>();
	  Session session = factory.getCurrentSession();
	  String hql = "FROM OrderBean o "
	       + "WHERE o.company_id = :company_id "
	       + "AND date(o.order_date) = current_date "
	       + "AND o.orderStatus = :orderStatus "
	       + "AND COALESCE(o.order_text, 0) != :order_text";
//	       + "AND o.order_text != :order_text";
	  List<OrderBean> orderBeans = session.createQuery(hql, OrderBean.class)
	           .setParameter("company_id", companyName)
	           .setParameter("orderStatus", "已領取")
	           .setParameter("order_text", "商家刪除資料")
	           .getResultList();
	  
	  Calendar hour = Calendar.getInstance();
	  int[] countByHour = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	  int total = 0;
	  for (int i = 0; i < orderBeans.size(); i++) {
	   total += orderBeans.get(i).getOrder_total();
	   hour.setTime(orderBeans.get(i).getOrder_date());
	   int order_hour = hour.get(Calendar.HOUR_OF_DAY);
	   if (order_hour == 10) {
	    countByHour[0]++;
	   }
	   if (order_hour == 11) {
	    countByHour[1]++;
	   }
	   if (order_hour == 12) {
	    countByHour[2]++;
	   }
	   if (order_hour == 13) {
	    countByHour[3]++;
	   }
	   if (order_hour == 14) {
	    countByHour[4]++;
	   }
	   if (order_hour == 15) {
	    countByHour[5]++;
	   }
	   if (order_hour == 16) {
	    countByHour[6]++;
	   }
	   if (order_hour == 17) {
	    countByHour[7]++;
	   }
	   if (order_hour == 18) {
	    countByHour[8]++;
	   }
	   if (order_hour == 19) {
	    countByHour[9]++;
	   }
	   if (order_hour == 20) {
	    countByHour[10]++;
	   }
	   if (order_hour == 21) {
	    countByHour[11]++;
	   }
	   if (order_hour == 22) {
	    countByHour[12]++;
	   }
	   if (order_hour == 23) {
	    countByHour[13]++;
	   }
	  }
	  
		if (orderBeans.size() == 0) {
			dailyReport.put("orderCount", 0);
			dailyReport.put("countByHour", 0);
			dailyReport.put("total", 0);
			dailyReport.put("start_time", 0);
			dailyReport.put("end_time", 0);
			return dailyReport;
		} else {
			dailyReport.put("orderCount", orderBeans.size());
			dailyReport.put("countByHour", countByHour);
			dailyReport.put("total", total);
			dailyReport.put("start_time", orderBeans.get(0).getCompanyBean().getStart_time());
			dailyReport.put("end_time", orderBeans.get(0).getCompanyBean().getEnd_time());
			return dailyReport;
		}
	}



	@Override
	public void updateOrderBean(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(orderBean);
	}
}
