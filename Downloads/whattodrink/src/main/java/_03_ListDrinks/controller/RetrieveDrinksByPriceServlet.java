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

@WebServlet("/RetrieveDrinksByPriceServlet")
public class RetrieveDrinksByPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		String searchMethod = (String)request.getParameter("searchMethod");
		String tagNameOrKeyword = (String)request.getParameter("tagNameOrKeyword");

		session.setAttribute("searchMethod", searchMethod);
		session.setAttribute("tagNameOrKeyword", tagNameOrKeyword);
		
		
		// 判斷"searchMethod"(搜尋方式) -> 依照"tagNameOrKeyword"(搜尋內容)執行搜尋
		DrinkService drinkService = new DrinkServiceImpl();
		if (searchMethod.compareTo("nativeSearch") == 0) {// 自然搜尋法
			List<DrinkBean> searchResultBeans = drinkService.orderDrinksByPriceAndKeyword(tagNameOrKeyword);
			
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
			}
			session.setAttribute("searchResult", searchResultBeans);
			System.out.println("===========================");
			System.out.println(searchResultBeans);
			System.out.println("===========================");
			
		} else if(searchMethod.compareTo("tagSearch") == 0){// 標籤搜尋法
			List<DrinkBean> searchResultBeans = drinkService.orderDrinksByPriceAndTagName(tagNameOrKeyword);
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
			}
			
			session.setAttribute("searchResult", searchResultBeans);
			System.out.println("===========================");
			System.out.println(searchResultBeans);
			System.out.println("===========================");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/_03_ListDrinks/c_03_searchResult/search.jsp");
		rd.forward(request, response);
		return;
	}

}
