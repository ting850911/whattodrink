package _07_Others.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Register.c_01_register.model.CustomerBean;
import _07_Others.model.FavoriteBean;
import _07_Others.service.FavoriteService;
import _07_Others.service.serviceImpl.FavoriteServiceImpl;


@WebServlet("/ShowMyFavoriteServlet")
public class ShowMyFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Integer customer_id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
		customer_id = customerBean.getCustomer_id();		
	
		FavoriteService favoriteService = new FavoriteServiceImpl();
		List<FavoriteBean> favoriteBeans = favoriteService.findByCustomer_id(customer_id);
		request.setAttribute("favoriteBeans", favoriteBeans);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_07_Others/c__07_others_favorite/favorite.jsp");
		rd.forward(request, response);
		return;
	
	}
	
}
