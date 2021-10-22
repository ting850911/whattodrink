package _07_Others.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _07_Others.model.FavoriteBean;
import _07_Others.service.FavoriteService;
import _07_Others.service.serviceImpl.FavoriteServiceImpl;


@WebServlet("/AddDeleteMyFavoriteServlet")
public class AddDeleteMyFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Integer customer_id;
	String company_id;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		FavoriteBean favoriteBean = new FavoriteBean();
		
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
		customer_id = customerBean.getCustomer_id();		
		
		company_id = request.getParameter("company_id");
		CompanyService companyService = new CompanyServiceImpl();
		CompanyBean companyBean = (CompanyBean) companyService.findById(company_id);
	
		FavoriteService favoriteService = new FavoriteServiceImpl();
		if (favoriteService.existFavorite(customer_id, company_id)) {
			favoriteService.deleteFavorite(customer_id, company_id);
		} else {
			favoriteBean.setCustomer_id(customer_id);
			favoriteBean.setCompany_id(company_id);
			favoriteBean.setCompanyBean(companyBean);
			favoriteBean.setCustomerBean(customerBean);
			favoriteService.saveFavorite(favoriteBean);
		}
	
	}

}
