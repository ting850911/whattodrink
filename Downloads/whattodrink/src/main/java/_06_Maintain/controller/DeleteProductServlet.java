package _06_Maintain.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

@MultipartConfig
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeleteProductServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("企業用戶顯示飲品列表之Controller-GET方法開始");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession(false);
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		String company_id = companyBean.getCompany_id();
		System.out.println("1. ===========================");
		log.info("company_id: " + company_id);
		System.out.println("2. ===========================");
		
		DrinkService drinkService = new DrinkServiceImpl();
		List<DrinkBean> drinkBeanList = drinkService.findByCompanyId(company_id);

		System.out.println("3. ===========================");
		log.info("drinkBeanList: " + drinkBeanList);
		System.out.println("4. ===========================");		
		
		List<Map<String, Object>> list = new LinkedList<>();
		for (int i = 0; i < drinkBeanList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("proId", drinkBeanList.get(i).getProduct_id());
			map.put("proName", drinkBeanList.get(i).getProduct_name());
			map.put("proCap", drinkBeanList.get(i).getCapacity());
			map.put("picPath", drinkBeanList.get(i).getProduct_picpath());
			list.add(map);
		}
		response.getWriter().print(JSON.toJSONString(list));
		System.out.println("5. ===========================");
		System.out.println(JSON.toJSONString(list));
		System.out.println("6. ===========================");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("企業用戶刪除飲品之Controller-POST方法開始");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		
		if (request.getParameter("proId").length() != 0) {
			Integer product_id = Integer.parseInt(request.getParameter("proId"));
			DrinkService drinkService = new DrinkServiceImpl();
			drinkService.deleteDrink(product_id);	
			// date
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			DrinkBean drinkBean = drinkService.findById(product_id);
			drinkBean.setAlter_date(ts);
			drinkBean.setEnabled(false);
			drinkService.updateDrink(drinkBean);
			System.out.println("7. ===========================");
			System.out.println("product_id: " + product_id + "已刪除(enabled欄位改為FALSE)。");
			System.out.println("8. ===========================");	
			response.getWriter().print("yes");
			log.info("企業用戶刪除飲品之Controller-POST方法結束");		
		}	
	}
}
