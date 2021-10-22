package _06_Maintain.controller;

import java.io.IOException;
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
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.service.CategoryService;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.CategoryServiceImpl;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

@MultipartConfig
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UpdateProductServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession(false);
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		String company_id = companyBean.getCompany_id();
		System.out.println("1. ===========================");
		log.info("company_id: " + company_id);
		System.out.println("2. ===========================");

//		if (request.getParameter("proId").length() != 0) {
		Integer proId = Integer.parseInt(request.getParameter("proId"));
		DrinkService drinkService = new DrinkServiceImpl();
		DrinkBean drinkBean = drinkService.findById(proId);
		System.out.println("3. ===========================");
		log.info("drinkBean: " + drinkBean);
		System.out.println("4. ===========================");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proId", drinkBean.getProduct_id());
		map.put("proName", drinkBean.getProduct_name());
		map.put("proPrice", drinkBean.getProduct_price());
		map.put("proCal", drinkBean.getProduct_cal());

		// picPath
		map.put("picPath", drinkBean.getProduct_picpath());

		// temperature
		Long tempLimitsize = drinkService.determineTempLimitsByProductId(proId);
		System.out.println("tempLimitsize" + tempLimitsize);
		String templimit;
//		Integer templimit; //-> 改為傳送Integer
		if (tempLimitsize == 2) {
			// 只有熱飲
			templimit = "熱";
		} else if (tempLimitsize == 4) {
			// 只有冷飲
			templimit = "冰";
		} else {
			// 熱飲冷飲都有
			templimit = "皆可";
		}
		map.put("proTemp", templimit);

		// sugar
		List<Integer> sugarIntegers = drinkService.determineSugarLimitsByProductId(proId);
		// List<String> sugarNameList = new LinkedList<>(); -> 改為傳送Integer
		List<Integer> sugarIntegers2 = new LinkedList<>();
		for (Integer integer : sugarIntegers) {
			if (integer == 1) {
				sugarIntegers2.add(1);
			}
			if (integer == 2) {
				sugarIntegers2.add(2);
			}
			if (integer == 3) {
				sugarIntegers2.add(3);
			}
			if (integer == 4) {
				sugarIntegers2.add(4);
			}
			if (integer == 5) {
				sugarIntegers2.add(5);
			}
		}
		Integer[] sugarArray = sugarIntegers2.toArray(new Integer[0]);
		map.put("sugar", sugarArray);

		// hashtag -> 0~2
		if (drinkService.findByProductId(proId).size() == 0) {
			String[] tagArray = { "" };
			map.put("hashtag", tagArray);
		} else {
			List<TagBean> tagBeans = drinkService.findByProductId(proId);
			List<String> tagNameList = new LinkedList<>();
			for (TagBean tagBean : tagBeans) {
				String tagName = tagBean.getTag_name();
				tagNameList.add(tagName);
			}
			String[] tagArray = tagNameList.toArray(new String[0]);
			map.put("hashtag", tagArray);
		}

		// category
		CategoryService categoryService = new CategoryServiceImpl();
		List<CategoryBean> list = categoryService.findByCompanyId(company_id);
		List<String> categoryNameList = new LinkedList<>();
		for (CategoryBean categoryBean : list) {
			String categoryName = categoryBean.getCategory_name();
			categoryNameList.add(categoryName);
		}
		String[] CategoryStringArray = categoryNameList.toArray(new String[0]);
		map.put("catalog", CategoryStringArray);

		// category2
		Integer category_id = drinkBean.getCategory_id();
		String catalog2 = categoryService.findById(category_id).getCategory_name();
		map.put("catalog2", catalog2);

		// category3
		List<CategoryBean> list3 = categoryService.findByCompanyId(company_id);
		List<Integer> categoryIdList = new LinkedList<>();
		for (CategoryBean categoryBean : list3) {
			Integer categoryId = categoryBean.getCategory_id();
			categoryIdList.add(categoryId);
		}
		Integer[] CategoryIdArray = categoryIdList.toArray(new Integer[0]);
		map.put("catalog3", CategoryIdArray);

		// category4
		map.put("catalog4", category_id);

		response.getWriter().print(JSON.toJSONString(map));
		System.out.println("5. ===========================");
		System.out.println(JSON.toJSONString(map));
		System.out.println("6. ===========================");

		System.out.println("7. ===========================");
		System.out.println("proId: " + proId + "已送出該筆proId所對應之細項。");
		System.out.println("8. ===========================");
//		}
		log.info("企業用戶顯示配料列表之Controller-POST方法開結束始");

	}

}
