package _04_ShoppingCart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ShoppingCart;

//買家點選健康提醒
@WebServlet("/_04_ShoppingCart/AddToHealthServlet")
public class AddToHealthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AddToHealthServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("修改購物車之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		//取得前端健康提醒的杯數(變數: addToHealth)，以及cartKey
		int addToHealth = Integer.valueOf(request.getParameter("addToHealth"));
		System.out.println("addToHealth" + addToHealth);
		int cartKey = Integer.valueOf(request.getParameter("cartKey"));
		System.out.println("cartKey" + cartKey);
		
		//藉由cartKey取得要修改的購物車列itemBean
		ItemBean itemBean = cart.getCartItemBean(cartKey);
		System.out.println("itemBean" + itemBean);
		
		//將健康提醒的杯數set到上方itemBean的add_to_health
		itemBean.setAdd_to_health(addToHealth);
		
		
		
	}

}
