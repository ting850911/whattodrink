package _07_Others.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import _01_Register.b_01_register.model.CompanyBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

/**
 * Servlet implementation class BusinessReportServlet
 */
@WebServlet("/BusinessReportServlet")
public class BusinessReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		OrderService orderService = new OrderServiceImpl();
		Map<String, Object> dailyReport = orderService.createDailyReport(companyBean.getCompany_id());
		String dailyReportJson = JSON.toJSONString(dailyReport);
		response.getWriter().write(dailyReportJson);;
		System.out.println(dailyReportJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
