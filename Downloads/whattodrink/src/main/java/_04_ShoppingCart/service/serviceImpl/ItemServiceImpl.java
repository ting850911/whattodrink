package _04_ShoppingCart.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import _00_init.utils.HibernateUtils;
import _04_ShoppingCart.dao.ItemDao;
import _04_ShoppingCart.dao.daoImpl.ItemDaoImpl;
import _04_ShoppingCart.dao.daoImpl.OrderDaoImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.service.ItemService;

public class ItemServiceImpl implements ItemService{
	
	private static Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	SessionFactory factory;
	ItemDao itemDao;
	
	
	public ItemServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		itemDao = new ItemDaoImpl();
	}

	

	@Override
	public void save(ItemBean itemBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單細項之Service:交易開始，準備儲存訂單細項");
			itemDao.save(itemBean);
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
	public void updateItemStatus(ItemBean itemBean, String itemStatus) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單細項之Service: 更新訂單細項狀態");
			itemDao.updateItemStatus(itemBean, itemStatus);
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
	public List<ItemBean> findByOrderId(String order_id) {
		List<ItemBean>  beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = itemDao.findByOrderId(order_id);
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
	public List<ItemBean> findAllUnfinishedItemsByOrderIdOrderByOrderDate(String order_id) {
		List<ItemBean>  beans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			beans = itemDao.findAllUnfinishedItemsByOrderIdOrderByOrderDate(order_id);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
			e.printStackTrace();
//			throw new RuntimeException(e);
		}
		return beans;
	}

	@Override
	public ItemBean findById(Integer item_id) {
		ItemBean ib = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ib = itemDao.findById(item_id);
			tx.commit();
		}catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			e.printStackTrace();
		}		
		return ib;
	}



	@Override
	 public String getHealthReminderByCustomerId(Integer customerId) {
	  String healthReminder = null;
	  Session session = factory.getCurrentSession();
	  Transaction tx = null;
	  try {
	   tx = session.beginTransaction();
	   healthReminder = itemDao.getHealthReminderByCustomerId(customerId);
	   tx.commit();
	  }catch(Exception e) {
	   if(tx != null){
	    tx.rollback();
	   }
	   System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
	   throw new RuntimeException(e);
	  }
	  return healthReminder;
	 }


	@Override
	public void updateItemBean(ItemBean itemBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.info("處理訂單細項之Service: 更新訂單細項狀態");
			itemDao.updateItemBean(itemBean);
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
	public List<ItemToppingBean> findByItemId(Integer item_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		List<ItemToppingBean> list = null;
		try {
			tx = session.beginTransaction();
			log.info("處理訂單細項之Service: 更新訂單細項狀態");
			list = itemDao.findByItemId(item_id);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();				
			}
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());			
			throw new RuntimeException(e);
		}	
		return list;
	}

	
}
