package _05_Order.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@WebServlet("/B_orderHistory")
public class B_orderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_orderHistory.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家歷史訂單頁面之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		CompanyService companyService = new CompanyServiceImpl();
		CompanyBean bean = (CompanyBean) session.getAttribute("BLoginOK");
		
		List<Map<String, Object>> list = companyService.findAllHistoryOrdersByCompanyId(bean.getCompany_id());
//		List<Map<String, Object>> list = companyService.findAllHistoryOrdersByCompanyId("E01");
		System.out.println(list);
		response.getWriter().print(JSON.toJSONString(list));
//		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//[{"length":2},  //越新的越上面，有可能length=0 不用顯示任何資料
		// {"order_id":"E0120210906003","order_date":"2021-09-06 14:59:08","payment":"現金","order_quantity":3,"order_total":150,"orderStatus":"已領取","invitationDiscount":"無折扣"},
		// {"order_id":"E0120210905002","order_date":"2021-09-05 23:37:31","payment":"信用卡","order_quantity":5,"order_total":260,"orderStatus":"已領取","invitationDiscount":"已折扣50元"}]
		
	}

}
