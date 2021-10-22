package _03_ListDrinks.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.FavoriteBean;
import _07_Others.service.CommentService;
import _07_Others.service.FavoriteService;
import _07_Others.service.serviceImpl.CommentServiceImpl;
import _07_Others.service.serviceImpl.FavoriteServiceImpl;


@WebServlet("/_03_ListDrinks/StorePage")
public class StorePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(StorePage.class);
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家飲料頁面之Controller: 開始");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		DrinkService drinkService = new DrinkServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();
		
		
		if (session.getAttribute("CLoginOK") != null) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
			Integer customer_id = customerBean.getCustomer_id();

			FavoriteService favoriteService = new FavoriteServiceImpl();
			List<FavoriteBean> favoriteBeans = favoriteService.findByCustomer_id(customer_id);
			List<String> orderNoList=favoriteBeans.stream().map(FavoriteBean::getCompany_id).collect(Collectors.toList());
			String favoritecompany = orderNoList.stream().collect(Collectors.joining(","));
	
			request.setAttribute("favoritecompany", favoritecompany);
		}
		
		
		//使用公司ID取得公司所有分類及飲料
		String companyId = request.getParameter("companyId");
		List<CategoryBean> storeCategoryBeans = companyService.findByCompanyId(companyId);
		List<DrinkBean> storeDrinkBeans = drinkService.findByCompanyId(companyId);

		CommentService commentService = new CommentServiceImpl();
		for (int i = 0; i < storeDrinkBeans.size(); i++) {
			BigDecimal avg = commentService.avgStarForDrink(storeDrinkBeans.get(i).getProduct_id());
			avg=avg.setScale(1,RoundingMode.HALF_UP);
			storeDrinkBeans.get(i).setAvgStar(avg);	
		}
		
		storeDrinkBeans.forEach(bean -> {
			System.out.println(bean.getProduct_name());
			System.out.println(bean.getCategory_id());
		});
		
		
		
		session.setAttribute("storeCategoryBeans", storeCategoryBeans);
		session.setAttribute("storeDrinkBeans", storeDrinkBeans);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_03_ListDrinks/c_03_storePage/StorePage.jsp");
		rd.forward(request, response);
		return;
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
