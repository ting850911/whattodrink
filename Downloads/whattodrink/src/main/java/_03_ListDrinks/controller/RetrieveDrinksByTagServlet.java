package _03_ListDrinks.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

@WebServlet("/_03_listDrinks/RetrieveDrinksByTag")
public class RetrieveDrinksByTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		String tagName = request.getParameter("tagName");
		DrinkService drinkService = new DrinkServiceImpl();
		List<DrinkBean> searchResultBeans = drinkService.findByTagName(tagName);
		
		
if (session.getAttribute("A01") != null) {
			
			for (int i = 0; i < searchResultBeans.size(); i++) {
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("A01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("A01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("B01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("B01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("C01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("C01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("D01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("D01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("E01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("E01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("F01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("F01"));
				}
				if(searchResultBeans.get(i).getCompanyBean().getCompany_id().equals("G01")) {
					searchResultBeans.get(i).getCompanyBean().setDistance((String) session.getAttribute("G01"));
				}
			
			
			}
			Collections.sort(searchResultBeans);
			}
		
		
		
		session.setAttribute("searchMethod", "tagSearch");
		session.setAttribute("tagNameOrKeyword", tagName);
		session.setAttribute("searchResult", searchResultBeans);

		RequestDispatcher rd = request.getRequestDispatcher("/_03_ListDrinks/c_03_searchResult/search.jsp");
		rd.forward(request, response);
	}
}