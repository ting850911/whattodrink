package _04_ShoppingCart.dao.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _00_init.utils.HibernateUtils;
import _04_ShoppingCart.dao.ItemDao;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ItemToppingBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

public class ItemDaoImpl implements ItemDao {
	
	
	private static Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);
	SessionFactory factory;
	OrderService orderService;
	
	public ItemDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
		orderService = new OrderServiceImpl();
	}

	@Override
	public void save(ItemBean itemBean) {	
		log.info("儲存訂單(OrderDaoImpl)之Dao: ");
		Session session = factory.getCurrentSession();
		session.persist(itemBean);
	}

	@Override
	public void updateItemStatus(ItemBean itemBean, String itemStatus) {
		log.info("依照itemBean與itemStatus更新此筆訂單之Dao, itemBean=" + itemBean + ", itemStatus=" + itemStatus);
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ItemBean i SET i.itemStatus = :status WHERE o.item_id = :id";		
		session.createQuery(hql).setParameter("status", itemStatus)
								.setParameter("id", itemBean.getItem_id())
								.executeUpdate();
	}

	@Override
	public List<ItemBean> findByOrderId(String order_id) {
		log.info("依照order_id取得所有訂單細項之Dao, order_id=" + order_id);
		List<ItemBean> beans = new LinkedList<ItemBean>();
		Session session = factory.getCurrentSession();
		
		String hql = "FROM ItemBean i WHERE i.orderBean.order_id = :order_id";
		beans = session.createQuery(hql, ItemBean.class)
								       .setParameter("order_id", order_id)
					  	   		       .getResultList();
		return beans;
	}
	
	@Override
	public List<ItemBean> findAllUnfinishedItemsByOrderIdOrderByOrderDate(String order_id) {
		log.info("依照order_id取得所有訂單細項之Dao, order_id=" + order_id);
		Session session = factory.getCurrentSession();
		List<ItemBean> beans = null;
		
		String hql = "FROM ItemBean i WHERE i.order_id = :id AND i.itemStatus <>'已完成'";
		beans = session.createQuery(hql, ItemBean.class).setParameter("id", order_id).getResultList();
		return beans;
	}

	@Override
	public ItemBean findById(Integer item_id) {
		log.info("依照item_id讀取特定一筆訂單細項的所有資料之Dao, item_id=" + item_id);
		Session session = factory.getCurrentSession();
		ItemBean ib = session.get(ItemBean.class, item_id);
		return ib;
	}

	@Override
	 public String getHealthReminderByCustomerId(Integer customerId) {
	  log.info("依照customerId編號讀取每日提醒之Dao, customerId=" + customerId);
	  Session session = factory.getCurrentSession();
	  String hql = "SELECT ib.drinkBean.product_name, ib.capacity, ib.sugarLevelBean.sugar_level, ib.item_cal, ib.orderBean.order_date, ib.orderBean.customerBean.weight "
	       + "FROM ItemBean ib "
	       + "WHERE ib.orderBean.customer_id = :customer_id "
	       + "AND date(ib.orderBean.order_date) = current_date "
	       + "AND ib.add_to_health != 0 "
	       + "AND ib.orderBean.orderStatus = :orderStatus";
	  List<Object[]> itemBeansNeededInfo = session.createQuery(hql, Object[].class)
	             .setParameter("customer_id", customerId)
	             .setParameter("orderStatus", "已領取")
	             .getResultList();
	  List<Object> healthReminder = new ArrayList<>();
	  for (int i = 0; i < itemBeansNeededInfo.size(); i++) {
	   Map<String, Object> info = new LinkedHashMap<>();
	   info.put("product_name", itemBeansNeededInfo.get(i)[0]);
	   info.put("capacity", itemBeansNeededInfo.get(i)[1]);
	   info.put("sugar_level", itemBeansNeededInfo.get(i)[2]);
	   info.put("item_cal", itemBeansNeededInfo.get(i)[3]);
	   SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	   Date date = (Timestamp)itemBeansNeededInfo.get(i)[4];
	   info.put("time", format.format(date));
	   info.put("weight", itemBeansNeededInfo.get(i)[5]);
	   healthReminder.add(info);
	  }
	  String healthReminderJSON = JSON.toJSONString(healthReminder);
	  
	  return healthReminderJSON;
	 }

	@Override
	public void updateItemBean(ItemBean itemBean) {
		log.info("updateItemBean之Dao, itemBean=" + itemBean);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(itemBean);
	}


	@Override
	public List<ItemToppingBean> findByItemId(Integer item_id) {
		Session session = factory.getCurrentSession();
		List<ItemToppingBean> list = null;

		String hql = "FROM ItemToppingBean i WHERE i.item_id = :item_id";
		list = session.createQuery(hql, ItemToppingBean.class).setParameter("item_id", item_id).getResultList();
		return list;
	}
	

}
