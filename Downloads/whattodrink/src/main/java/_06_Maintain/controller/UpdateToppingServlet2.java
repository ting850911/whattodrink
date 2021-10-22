package _06_Maintain.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

@MultipartConfig
@WebServlet("/UpdateToppingServlet2")
public class UpdateToppingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UpdateToppingServlet2.class);

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

		Integer topping_id = Integer.parseInt(request.getParameter("toppingId"));
		ToppingBean toppingBean = drinkService.findToppingBeanById(topping_id);
		toppingBean.setEnabled(false);
		drinkService.updateTopping(toppingBean);
		System.out.println("3. ===========================");
		log.info("PRE-toppingBean: " + toppingBean);
		System.out.println("4. ===========================");

		ToppingBean toppingBean2 = new ToppingBean();
		toppingBean2.setCompany_id(company_id);
		toppingBean2.setCompanyBean(companyBean);
		toppingBean2.setTopping_name(request.getParameter("toppingName"));
		toppingBean2.setTopping_price(Integer.parseInt(request.getParameter("toppingPrice")));
		toppingBean2.setTopping_cal(Integer.parseInt(request.getParameter("toppingCal")));
		toppingBean2.setEnabled(true);

		// Picture
		if (request.getPart("toppingImg").getSize() == 0) {
			// 沒上傳新圖檔
			toppingBean2.setTopping_picname(toppingBean.getTopping_picname());
			toppingBean2.setTopping_picpath(toppingBean.getTopping_picpath());
		} else {
			// 有上傳新圖檔
			String dirPath = "C:/_JSP/workspace" + getServletContext().getContextPath() + "/src/main/webapp/images/"
					+ companyBean.getCompany_id();
			String toppingName = request.getParameter("toppingName");
			String picName = dirPath + "/" + toppingName + ".jpg";
			Part part = request.getPart("toppingImg");
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			part.write(picName);
			toppingBean2.setTopping_picname(picName.substring(picName.lastIndexOf("/") + 1));
			toppingBean2.setTopping_picpath(picName.substring(picName.indexOf("images")));
		}
		drinkService.saveToppingBean(toppingBean2);

		System.out.println("5. ===========================");
		log.info("NEW-toppingBean: " + toppingBean2);
		System.out.println("6. ===========================");

		RequestDispatcher rd = request.getRequestDispatcher("/_06_Maintain/b_06_maintain/1_business_toppings_list.jsp");
		rd.forward(request, response);

	}

}
