package _06_Maintain.controller;

import java.io.File;
import java.io.IOException;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

@MultipartConfig
@WebServlet("/AddAddOnServlet")
public class AddAddOnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AddAddOnServlet.class);   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("企業用戶新增配料之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
	
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");

		String company_id = companyBean.getCompany_id();		
		String topping_name = request.getParameter("name");
		Integer topping_cal = Integer.parseInt(request.getParameter("cal"));
		Integer topping_price = Integer.parseInt(request.getParameter("money"));
		ToppingBean toppingBean = new ToppingBean();
		toppingBean.setCompany_id(company_id);
		toppingBean.setCompanyBean(companyBean);
		toppingBean.setTopping_name(topping_name);
		toppingBean.setTopping_cal(topping_cal);
		toppingBean.setTopping_price(topping_price);
		toppingBean.setEnabled(true);
		
		// picture
		String dirPath = "C:/_JSP/workspace"+ 
				   getServletContext().getContextPath() + 
				   "/src/main/webapp/images/" +
				   companyBean.getCompany_id();
		String picName = dirPath + "/" + topping_name + ".jpg";

			Part part = request.getPart("topping_pic");

			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			part.write(picName);
			
			
		toppingBean.setTopping_picname(picName.substring(picName.lastIndexOf("/") + 1));
		toppingBean.setTopping_picpath(picName.substring(picName.indexOf("images")));
	
		DrinkService drinkService = new DrinkServiceImpl();
		drinkService.saveToppingBean(toppingBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_06_Maintain/b_06_maintain/1_business_add_addOn.jsp");
		rd.forward(request, response);
	}

}
