package _05_Order.controller;

import java.io.IOException;
import java.util.HashMap;
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
import com.google.gson.Gson;

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.ShoppingCart;

@WebServlet("/PaymentDiscount")
public class PaymentDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(PaymentDiscount.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("僅傳價格與折扣值給前端之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		CustomerBean bean = (CustomerBean) session.getAttribute("CLoginOK");
		
		double sub_total = cart.getCartSubTotal();
		double total = sub_total - 50;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sub_total", (int)sub_total);
		map.put("storeTel", cart.getShoppingCart().get(cart.getShoppingCart().keySet().iterator().next()).getDrinkBean().getCompanyBean().getTel());
		map.put("total", (int)total);
		map.put("hasDiscount", (bean.getInvitationCount() == 0 ? 0 : 1));
		
		response.getWriter().print(JSON.toJSONString(map));
//		System.out.println(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}

//{"total":15,"hasDiscount":1,"sub_total":65}
