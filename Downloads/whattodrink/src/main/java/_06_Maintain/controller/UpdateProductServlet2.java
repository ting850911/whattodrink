package _06_Maintain.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.service.CategoryService;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.CategoryServiceImpl;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLevelBean;
import _07_Others.model.TempLimitBean;

@MultipartConfig
@WebServlet("/UpdateProductServlet2")
public class UpdateProductServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UpdateProductServlet2.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		DrinkService drinkService = new DrinkServiceImpl();

//		舊有的飲品記錄_Product表格 -> setEnabled(false)
		Integer proId = Integer.parseInt(request.getParameter("proId"));
		DrinkBean drinkBean = drinkService.findById(proId);
		// date
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		drinkBean.setAlter_date(ts);
		drinkBean.setEnabled(false);
		drinkService.updateDrink(drinkBean);
		System.out.println("3. ===========================");
		log.info("PRE-drinkBean: " + drinkBean);
		System.out.println("4. ===========================");

//		新建的飲品記錄_Product表格
		DrinkBean drinkBean2 = new DrinkBean();

		// date
		drinkBean2.setAdd_date(ts);
		drinkBean2.setAlter_date(ts);

		// capacity
		String capacity = drinkBean.getCapacity();
		drinkBean2.setCapacity(capacity);

		// categories
//		CategoryService categoryService = new CategoryServiceImpl();
		Integer catagory_id = Integer.parseInt(request.getParameter("catagories"));
//		categoryService.findById(catagory_id);
		drinkBean2.setCategory_id(catagory_id);

		// CategoryBean
		CategoryBean categoryBean = drinkService.findByCategory_id(catagory_id);
		drinkBean2.setCategory(categoryBean);

		// name
		String pro_name = request.getParameter("pro_name");
		drinkBean2.setProduct_name(pro_name);

		// company, companyBean
		drinkBean2.setCompany_id(company_id);
		drinkBean2.setCompanyBean(companyBean);

		// Picture
		if (request.getPart("proImg").getSize() == 0) {
			// 沒上傳新圖檔
			drinkBean2.setProduct_picname(drinkBean.getProduct_picname());
			drinkBean2.setProduct_picpath(drinkBean.getProduct_picpath());
		} else {
			// 有上傳新圖檔
			String dirPath = "C:/_JSP/workspace" + getServletContext().getContextPath() + "/src/main/webapp/images/"
					+ companyBean.getCompany_id();

			String toppingName = request.getParameter("pro_name");
			String picName = dirPath + "/" + toppingName + ".jpg";
			Part part = request.getPart("proImg");
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			part.write(picName);
			drinkBean2.setProduct_picname(picName.substring(picName.lastIndexOf("/") + 1));
			drinkBean2.setProduct_picpath(picName.substring(picName.indexOf("images")));
		}

		// enabled
		drinkBean2.setEnabled(true);

		// price
		Integer proPrice = Integer.parseInt(request.getParameter("proPrice"));
		drinkBean2.setProduct_price(proPrice);

		// cal
		Integer proCal = Integer.parseInt(request.getParameter("proCal"));
		drinkBean2.setProduct_cal(proCal);

		drinkService.save(drinkBean2);
		request.setAttribute("newDrinkBean", drinkBean2);
		DrinkBean newDrinkBean = (DrinkBean) request.getAttribute("newDrinkBean");
		Integer product_id = newDrinkBean.getProduct_id();
		log.info("New product_id: " + product_id);

		System.out.println("5. ===========================");
		System.out.println("NEW-drinkBean: " + drinkBean2);
		System.out.println("6. ===========================");

//		新建的飲品記錄_Sugar表格(checkbox)
		String[] sugarArray = request.getParameterValues("sugar");
		for (int i = 0; i < sugarArray.length; i++) {
			Integer sugar_id = Integer.parseInt(sugarArray[i]);
			SugarLimitBean sugarLimitBean2 = new SugarLimitBean();
			SugarLevelBean sugarLevelBean = drinkService.findSugarLevelBeanBySugarId(sugar_id);
			sugarLimitBean2.setProduct_id(product_id);
			sugarLimitBean2.setSugar_id(sugar_id);
			sugarLimitBean2.setDrinkBean(drinkBean2);
			sugarLimitBean2.setSugarLevelBean(sugarLevelBean);
			sugarLimitBean2.setEnabled(true);
			drinkService.saveSugarLimitBean(sugarLimitBean2);
		}
//		舊有的飲品記錄_Sugar表格 -> setEnabled(false)
		List<SugarLimitBean> sugarLimitBean1 = drinkService.findSugarLimitsByProductIdWithTx(proId);
		for (SugarLimitBean sugarLimitBean : sugarLimitBean1) {
			sugarLimitBean.setEnabled(false);
			drinkService.updateSugarLimitBean(sugarLimitBean);
		}

//		新建的飲品記錄_Temp表格(checkbox)
		String temp = request.getParameter("temp");
		if (temp.compareTo("熱") == 0) {
			for (int i = 5; i <= 6; i++) {
				TempLimitBean tempLimitBean2 = new TempLimitBean();
				TempLevelBean tempLevelBean = drinkService.findTempLevelBeanByTempId(i);

				tempLimitBean2.setProduct_id(product_id);
				tempLimitBean2.setTemp_id(i);
				tempLimitBean2.setTempLevelBean(tempLevelBean);
				tempLimitBean2.setDrinkBean(drinkBean2);
				tempLimitBean2.setEnabled(true);
				drinkService.saveTempLimitBean(tempLimitBean2);
			}
		} else if (temp.compareTo("冰") == 0) {
			for (int i = 1; i <= 4; i++) {
				TempLimitBean tempLimitBean2 = new TempLimitBean();
				TempLevelBean tempLevelBean = drinkService.findTempLevelBeanByTempId(i);

				tempLimitBean2.setProduct_id(product_id);
				tempLimitBean2.setTemp_id(i);
				tempLimitBean2.setTempLevelBean(tempLevelBean);
				tempLimitBean2.setDrinkBean(drinkBean2);
				tempLimitBean2.setEnabled(true);
				drinkService.saveTempLimitBean(tempLimitBean2);
			}
		} else if (temp.compareTo("皆可") == 0) {
			for (int i = 1; i <= 6; i++) {
				TempLimitBean tempLimitBean2 = new TempLimitBean();
				TempLevelBean tempLevelBean = drinkService.findTempLevelBeanByTempId(i);

				tempLimitBean2.setProduct_id(product_id);
				tempLimitBean2.setTemp_id(i);
				tempLimitBean2.setTempLevelBean(tempLevelBean);
				tempLimitBean2.setDrinkBean(drinkBean2);
				tempLimitBean2.setEnabled(true);
				drinkService.saveTempLimitBean(tempLimitBean2);
			}
		}
//		舊有的飲品記錄_Temp表格 -> setEnabled(false)		
		List<TempLimitBean> tempLimitBean1 = drinkService.findTempLimitsByProductIdWithTx(proId);
		for (TempLimitBean tempLimitBean : tempLimitBean1) {
			tempLimitBean.setEnabled(false);
			drinkService.updateTempLimitBean(tempLimitBean);
		}

//		新建的飲品記錄_Tag表格(checkbox: 前端已規定最多兩個標籤)
		TagBean tagBeanL1 = new TagBean();
		TagBean tagBeanL2 = new TagBean();
		if (request.getParameterValues("hashtag") == null) {
			// 沒有任何標籤 -> 不做處理
		} else {
			// 至少一個標籤，至多兩個標籤。
			if (request.getParameterValues("customized") == null) {
				String[] hashtagArray = request.getParameterValues("hashtag");
				if (hashtagArray.length == 1) {
					// 僅有一個現成標籤
					String formatTag1 = hashtagArray[0];
					tagBeanL1.setProduct_id(product_id);
					tagBeanL1.setTag_name(formatTag1);
					tagBeanL1.setDrinkBean(drinkBean2);
					drinkService.save(tagBeanL1);
				} else {
					// 兩個皆為現成標籤
					String formatTag1 = hashtagArray[0];
					String formatTag2 = hashtagArray[1];
					tagBeanL1.setProduct_id(product_id);
					tagBeanL1.setTag_name(formatTag1);
					tagBeanL1.setDrinkBean(drinkBean2);
					drinkService.save(tagBeanL1);
					tagBeanL2.setProduct_id(product_id);
					tagBeanL2.setTag_name(formatTag2);
					tagBeanL2.setDrinkBean(drinkBean2);
					drinkService.save(tagBeanL2);
				}
			} else {
				String[] hashtagArray = request.getParameterValues("hashtag");
				String customized_tag = request.getParameter("customized");

				if (hashtagArray.length == 1) {
					// 僅有一個自訂標籤
					tagBeanL1.setProduct_id(product_id);
					tagBeanL1.setTag_name(customized_tag);
					tagBeanL1.setDrinkBean(drinkBean2);
					drinkService.save(tagBeanL1);
				} else if (hashtagArray.length == 2) {
					// 一個現成標籤+一個自訂標籤
					String formatTag1 = hashtagArray[0];
					tagBeanL1.setProduct_id(product_id);
					tagBeanL1.setTag_name(formatTag1);
					tagBeanL1.setDrinkBean(drinkBean2);

					drinkService.save(tagBeanL1);
					tagBeanL2.setProduct_id(product_id);
					tagBeanL2.setTag_name(customized_tag);
					tagBeanL2.setDrinkBean(drinkBean2);
					drinkService.save(tagBeanL2);
				}
			}
		}

//		舊有的飲品記錄_Tag表格 -> 整筆刪除
		drinkService.deleteTagByProductId(proId);

		RequestDispatcher rd = request
				.getRequestDispatcher("/_06_Maintain/b_06_maintain/1_business_beverages_list.jsp");
		rd.forward(request, response);
	}

}
