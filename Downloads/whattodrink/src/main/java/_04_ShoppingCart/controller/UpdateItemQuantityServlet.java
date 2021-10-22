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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.ShoppingCart;

@WebServlet("/_04_ShoppingCart/UpdateItemQuantityServlet")
public class UpdateItemQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(UpdateItemQuantityServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("修改購物車數量之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}

		//取得購物車內容
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		int cartKey = Integer.valueOf(request.getParameter("cartKey"));
		ItemBean itemBean = cart.getCartItemBean(cartKey);
		String quantityStr = request.getParameter("quantity");
		
		//移除或是修改數量(回傳總金額)
		if(quantityStr.equals("0")) {
			cart.deleteItemBean(cartKey);
			JSONArray priceAndSubtotal0 = new JSONArray();
			priceAndSubtotal0.add("0");
			priceAndSubtotal0.add(cart.getCartSubTotal());
			cart.deleteItemToppings(cartKey);
			response.getWriter().println(JSON.toJSONString(priceAndSubtotal0));
		}else {
			int quantity = Integer.valueOf(quantityStr);
			int price = itemBean.getPrice() / itemBean.getQuantity() * quantity;
			itemBean.setQuantity(quantity);
			itemBean.setPrice(price);
			JSONArray priceAndSubtotal = new JSONArray();
			priceAndSubtotal.add(price);
			priceAndSubtotal.add(cart.getCartSubTotal());
			
			response.getWriter().println(JSON.toJSONString(priceAndSubtotal));
		}
	}

}
