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
import _04_ShoppingCart.model.OrderBean;

@WebServlet("/B_order")
public class B_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(B_order.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家未處理訂單頁面之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		CompanyService companyService = new CompanyServiceImpl();
		CompanyBean bean = (CompanyBean) session.getAttribute("BLoginOK");
		System.out.println(bean.getCompany_id());
		
		List<Map<String, Object>> list = companyService.findAllUnfinishedOrdersByCompanyId(bean.getCompany_id());
		response.getWriter().print(JSON.toJSONString(list));
		
		//[{"length":4},   共有幾列，有可能length=0 不用顯示任何資料
		// {"order_id":"A0120210901001","scheduled_time":"20:04 ~ 20:19","payment":"信用卡","order_quantity":10,"order_total":700,"orderStatus":"待確認","taxId":"no","invitationDiscount":"無折扣"},
		// {"order_id":"A0120210902001","scheduled_time":"04:40 ~ 04:55","payment":"信用卡","order_quantity":4,"order_total":270,"orderStatus":"待確認","taxId":"no","invitationDiscount":"已折扣50元"},
		// {"order_id":"A0120210903001","scheduled_time":"18:34 ~ 18:49","payment":"信用卡","order_quantity":5,"order_total":150,"orderStatus":"待確認","taxId":"no","invitationDiscount":"已折扣50元"},
		// {"order_id":"A0120210904001","scheduled_time":"20:46 ~ 21:01","payment":"信用卡","order_quantity":5,"order_total":400,"orderStatus":"待確認","taxId":"no","invitationDiscount":"已折扣50元"}]
		
		
		//載入頁面時請求後端資料
//		$(document).ready(function () {
//		  $.ajax({
//		    type: "POST",
//		    url: "http://localhost:8080/whattodrink/B_order",
//		    dataType: "json",
//		    success: function (response) {
//		    	console.log(response);
//				$("#feedback").text(response[0].length);
//				$("#feedback2").text(response[1].order_id);
//				$("#feedback3").text(response[2].order_id);
//				$("#feedback4").text(response[3].order_id);
//				$("#feedback5").text(response[4].order_id);
//		    },
//		  });
//		});

	}

}
