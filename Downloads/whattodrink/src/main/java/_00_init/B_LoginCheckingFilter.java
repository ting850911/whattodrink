package _00_init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.ShoppingCart;

//商家所有資訊都必須登入
@WebFilter(
		urlPatterns = {
				"/_05_Order/b_05_order/1_business_orders.jsp", "/_05_Order/b_05_order/1_business_order_history.jsp", 
				"/_05_Order/b_05_order/1_business_order_details(all).jsp", "/_06_Maintain/*", "/B_item", "/B_order","/B_orderHistory",
				"/_07_Others/b_07_other/1_business_report.jsp","/B_orderStatusChange.java","/B_orderHistoryDelete","/B_itemStatusChange",
				"/B_orderHistoryItemDetail","/BusinessReportServlet"
		})
public class B_LoginCheckingFilter implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(B_LoginCheckingFilter.class);
	String contextPath;
	String requestURI;
	

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			contextPath = req.getContextPath();
			requestURI  = req.getRequestURI();
			HttpSession session = req.getSession();
			
			CompanyBean bean = (CompanyBean) session.getAttribute("BLoginOK");
			if(bean == null) {
				log.info("商家尚未登入，跳轉至登入頁面，存入session的requestURI:" + requestURI);
				// 記住原本的"requestURI"，稍後如果登入成功，系統可以自動轉入
				// 原本要執行的程式。
				session.setAttribute("requestURI", requestURI);	
				resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/_02_Login/b_02_login/1_business_login.jsp"));
				return;
			}
			String company_id = bean.getCompany_id();
			log.info("商家已經登入，直接執行程式，company_id: " + company_id);
			session.setAttribute("company_id", company_id);
			chain.doFilter(request, response);
			
		}else {
			throw new ServletException("Request/Response 型態錯誤");
		}
		
	}

	public void destroy() {
	}
}
