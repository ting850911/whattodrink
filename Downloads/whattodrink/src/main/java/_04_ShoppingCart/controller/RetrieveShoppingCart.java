package _04_ShoppingCart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.ShoppingCart;


@WebServlet("/_04_ShoppingCart/RetrieveShoppingCart")
public class RetrieveShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(RetrieveShoppingCart.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("顯示購物車資料之Controller: 開始");
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_00_Index/index.jsp"));
			return;
		}
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(cart == null || cart.getCartSum() < 1) {
			response.getWriter().print("NoCart");
			return;
		}else {
			response.getWriter().print("HasCart");
			return;
		}
		
		
	}

}







//		CustomerBean customer = (CustomerBean) session.getAttribute("CLoginOK");
//		if(customer != null) {
//			Cookie[] cookies = request.getCookies();
//			if(cookies != null) {
//				for (Cookie c : cookies) {
//					if (c.getName().equals(customer.getCustomer_account() + "session")) {
//						try {
//							
//							String cSessionId = c.getValue().trim();
//							ServletContext context = session.getServletContext();
//							HttpSession cSession = (HttpSession) context.getAttribute(cSessionId);
//							
//						} catch (NumberFormatException e) {
//							;
//						}
//						break;
//					}
//				}
//			}else {
//				
//			}
//		}