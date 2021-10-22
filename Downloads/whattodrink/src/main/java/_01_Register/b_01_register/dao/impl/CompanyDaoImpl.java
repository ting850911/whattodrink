package _01_Register.b_01_register.dao.impl;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import _00_init.utils.HibernateUtils;
import _01_Register.b_01_register.dao.CompanyDao;
import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _04_ShoppingCart.model.OrderBean;






// 本類別負責讀取資料庫內company表格內的紀錄
// 
public class CompanyDaoImpl implements Serializable, CompanyDao {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(CompanyDaoImpl.class);
	
	SessionFactory factory;

	public CompanyDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	
	
	//新增一筆紀錄
	@Override
	public void save(CompanyBean companyBean) {
		Session session = factory.getCurrentSession();
		session.persist(companyBean);
		log.info("儲存CompanyBean功能之Dao: 資料新增成功, CompanyBean=" + companyBean);
	}
	
	//判斷商家帳號是否已存在
	@Override
	public boolean existsByAccount(String account) {
		log.info("商家註冊功能之Dao: 檢查商家輸入的帳號是否已被使用");
		boolean existsByAccount = false;
		String hql = "FROM CompanyBean cb WHERE cb.company_account = :cbAccount";
		Session session = factory.getCurrentSession();
		
		try {
			session.createQuery(hql)
				   .setParameter("cbAccount", account)
				   .getSingleResult();
			
			existsByAccount = true;
		}catch(NoResultException e){
			;
		}catch (NonUniqueResultException e) {
			existsByAccount = true;
		}
		
		log.info("商家註冊功能之Dao: 檢查商家輸入的帳號是否已被使用, existsByAccount=" + existsByAccount);
		return existsByAccount;
	}

	//透過商家帳號，回傳該CompanyBean所有資料
	@Override
	public CompanyBean findByCompanyAccount(String company_account) {
		log.info("商家XX功能之Dao: 取得某個商家的資料, company_account=" + company_account);
		CompanyBean companyBean = null;
		String hql = "FROM CompanyBean cb WHERE cb.company_account = :cbCompany_account";
		Session session = factory.getCurrentSession();
		
		try {
			companyBean = session.createQuery(hql, CompanyBean.class)
								 .setParameter("cbCompany_account", company_account)
								 .getSingleResult();
			
		} catch (NoResultException ex) {
			log.info("商家XX功能之Dao: 取得某個商家的資料, result= NoResultException");
		} catch(NonUniqueResultException ex) {
			log.info("商家XX功能之Dao: 取得某個商家的資料, result= NonUniqueResultException");
		}
		log.info("商家XX功能之Dao: 取得某個商家的資料, CompanyBean=" + companyBean);
		return companyBean;
	}

	//修改一筆CompanyBean資料
	@Override
	public void updateCompany(CompanyBean companyBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(companyBean);
	
	}

	//透過商家帳號及密碼，回傳該CompanyBean
	@Override
	public CompanyBean findByCompanyAccountAndPassword(String company_account, String password) {
		CompanyBean cb = null;
		String hql = "FROM CompanyBean cb WHERE cb.company_account = :cbCompany_account and cb.company_password = :psw";
		Session session = factory.getCurrentSession();
		List<CompanyBean> beans = session.createQuery(hql, CompanyBean.class)
										 .setParameter("cbCompany_account", company_account)
										 .setParameter("psw", password)
										 .getResultList();
		if(beans.size() > 0) {
			cb = beans.get(0);
		}
		
		log.info("商家登入功能之Dao: 檢查帳號/密碼結果:" 
				+ (cb != null ? "帳號/密碼正確" : "帳號/密碼錯誤"));
		return cb;
	}
	
	
	
	
	
//	//透過商家Id，回傳該CompanyBean
//	@Override
//	public CompanyBean findById(Integer company_id) {
//		Session session = factory.getCurrentSession();
//		
//		CompanyBean companyBean = session.get(CompanyBean.class, company_id);
//		log.info("CompanyBean=" + companyBean);
//		return companyBean;
//	}

	//回傳所有商家的CompanyBean資料
	@Override
	public List<CompanyBean> findAll() {
		Session session = factory.getCurrentSession();
		List<CompanyBean> list = new ArrayList<>();
		String hql = "FROM CompanyBean";
		list = session.createQuery(hql, CompanyBean.class)
					  .getResultList();		
		log.info("回傳所有商家資料之Dao#findAll(), List<CompanyBean>=" + list); 
		return list;
	}

	@Override
	public List<CompanyBean> findAllByHitRank() {
		log.info("findAllByHitRank()"); 
		Session session = factory.getCurrentSession();
		List<CompanyBean> list = new LinkedList<>();
		List<String> compList = new ArrayList<>(Arrays.asList("A01","B01","C01","D01","E01","F01","G01"));
		String hql = "SELECT ob.companyBean FROM OrderBean ob WHERE ob.orderStatus = '已領取' AND ifnull(ob.order_text, ' ') != '商家刪除資料' GROUP BY ob.companyBean ORDER BY SUM(ob.order_quantity) DESC";
		list = session.createQuery(hql, CompanyBean.class)
					  .getResultList();
		for(CompanyBean b :list) {
			if(compList.contains(b.getCompany_id())) {
				compList.remove(b.getCompany_id());
			};
		}
		for(String str : compList) {
			list.add(findById(str));
		}
		log.info("回傳所有商家資料之Dao#findAll(), List<CompanyBean>=" + list); 
		return list;
	}


	@Override
	public CompanyBean findById(String companyId) {
		Session session = factory.getCurrentSession();		
		CompanyBean companyBean = session.get(CompanyBean.class, companyId);
		log.info("CompanyBean=" + companyBean);
		return companyBean;
	}



	@Override
	public boolean checkByCompanyEmailAndInvitation(String email, String invitation) {
		log.info("商家註冊功能之Dao: 檢查商家輸入的email和邀請碼是否正確");
		boolean existsByCompanyEmailAndInvitation = false;
		String hql = "FROM CompanyBean cb WHERE cb.company_owner_email = :email AND cb.invitation = :invitation";
		Session session = factory.getCurrentSession();
		
		try {
			CompanyBean cb = (CompanyBean) session.createQuery(hql)
											      .setParameter("email", email)
											      .setParameter("invitation", invitation)
											      .getSingleResult();
			if(cb != null) {
				existsByCompanyEmailAndInvitation = true;
			}
		}catch(NoResultException e) {
			existsByCompanyEmailAndInvitation = false;
		} catch (NonUniqueResultException e) {
			existsByCompanyEmailAndInvitation = true;
		}
		log.info("商家註冊功能之Dao: 檢查商家輸入的email和邀請碼是否正確, check=" + existsByCompanyEmailAndInvitation);
		return existsByCompanyEmailAndInvitation;
	}



	@Override
	public CompanyBean findByCompanyEmail(String email) {
		CompanyBean companyBean = null;
		String hql = "FROM CompanyBean cb WHERE cb.company_owner_email = :email";
		Session session = factory.getCurrentSession();
		
		try {
			companyBean = (CompanyBean) session.createQuery(hql)
											   .setParameter("email", email)
											   .getSingleResult();
			
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} catch(NonUniqueResultException ex) {
			ex.printStackTrace();
		}
		log.info("商家註冊功能之Dao: 取得商家的資料, CompanyBean=" + companyBean);
		return companyBean;
	}



	@Override
	public CompanyBean findByCompanyInvitation(String invitation) {
		CompanyBean companyBean = null;
		String hql = "FROM CompanyBean cb WHERE cb.invitation = :invitation";
		Session session = factory.getCurrentSession();
		
		try {
			companyBean = (CompanyBean) session.createQuery(hql)
											   .setParameter("invitation", invitation)
											   .getSingleResult();
			
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} catch(NonUniqueResultException ex) {
			ex.printStackTrace();
		}
		log.info("商家註冊功能之Dao: 取得商家的資料, CompanyBean=" + companyBean);
		return companyBean;
	}



	@Override
	public List<CategoryBean> findByCompanyId(String companyId) {
		Session session = factory.getCurrentSession();
		List<CategoryBean> list = new ArrayList<>();
		String hql = "FROM CategoryBean cb WHERE cb.company_id = :id";
		list = session.createQuery(hql, CategoryBean.class)
					  .setParameter("id", companyId)
					  .getResultList();		
		log.info("取得公司所有分類之Dao#findByCompanyId(), List<CategoryBean>=" + list); 
		return list;
	}



	@Override
	public List<CompanyBean> findCompanyHitRank() {
		Session session = factory.getCurrentSession();
		List<CompanyBean> list = new ArrayList<>();
		String hql = "SELECT o.companyBean FROM OrderBean o GROUP BY o.companyBean ORDER BY SUM(o.order_quantity) DESC";
		list = session.createQuery(hql, CompanyBean.class)
					  .getResultList();		
		log.info("回傳所有商家資料之Dao#findCompanyIdByHit(),List<String>=" + list); 
		return list;
	}



	@Override
	public List<Map<String, Object>> findAllUnfinishedOrdersByCompanyId(String company_id) {
		log.info("COMPANYID:" + company_id); 
		Session session = factory.getCurrentSession();
		String hql = "SELECT o.order_id, o.scheduled_time, o.payment, o.order_quantity, o.order_total, o.orderStatus,o.taxId, o.invitationDiscount "
				   + "FROM OrderBean o WHERE o.company_id = :id AND DATE(o.order_date) = CURDATE() AND o.orderStatus not in ('已領取','取消') ORDER BY o.order_date";
		List<Object[]> temp = session.createQuery(hql, Object[].class)
		              .setParameter("id", company_id)
		              .getResultList();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		Map<String, Object> lengthMap = new HashMap<>();
		lengthMap.put("length", temp.size());
		list.add(lengthMap);
		for(Object[] obj : temp) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("order_id", obj[0]);
			String time = (String) obj[1];
			map.put("scheduled_time", time.substring(11));
			map.put("payment", obj[2]);
			map.put("order_quantity", obj[3]);
			map.put("order_total", obj[4]);
			map.put("orderStatus", obj[5]);
			map.put("taxId", obj[6]);
			map.put("invitationDiscount", obj[7]);
			list.add(map);
		}
		log.info("回傳商家非已領取狀態的所有Order之Dao#findAllUnfinishedOrdersByCompanyId(),List<Map<String, Object>>=" + list); 
		return list;
	}



	@Override
	public List<Map<String, Object>> findAllHistoryOrdersByCompanyId(String company_id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT o.order_id, o.order_date, o.payment, o.order_quantity, o.order_total, o.orderStatus, o.invitationDiscount "
				   + "FROM OrderBean o WHERE o.company_id = :id AND o.orderStatus = '已領取' AND ifnull(o.order_text, ' ') != '商家刪除資料' ORDER BY o.order_date DESC";
		List<Object[]> temp = session.createQuery(hql, Object[].class)
		              .setParameter("id", company_id)
		              .getResultList();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
//		Map<String, Object> lengthMap = new HashMap<>();
//		lengthMap.put("length", temp.size());
//		list.add(lengthMap);
		for(Object[] obj : temp) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("order_id", obj[0]);
			Timestamp ts = (Timestamp) obj[1];
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
			map.put("order_date", date);
			map.put("payment", obj[2]);
			map.put("order_quantity", obj[3]);
			map.put("order_total", obj[4]);
			map.put("orderStatus", obj[5]);
			map.put("invitationDiscount", obj[6]);
			list.add(map);
		}
		log.info("回傳商家所有歷史訂單Order之Dao#findAllHistoryOrdersByCompanyId(),List<Map<String, Object>>=" + list); 
		return list;
	}

}