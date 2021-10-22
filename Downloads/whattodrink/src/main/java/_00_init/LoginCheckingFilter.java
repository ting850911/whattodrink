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

import _01_Register.c_01_register.model.CustomerBean;
import _04_ShoppingCart.model.ShoppingCart;

//買家購物車下單必須確認是否已經登入
@WebFilter(
		urlPatterns = { 
//				"/_04_ShoppingCart/payment.jsp", "/_05_Order/PaymentServlet", 
				"/_04_ShoppingCart/confirmOrderPage.jsp", "/_05_Order/controller/ConfirmOrderServlet"}, 
		initParams = { 
//				@WebInitParam(name = "CMustLogin1", value = "/_04_ShoppingCart/payment.jsp"), 
//				@WebInitParam(name = "CMustLogin2", value = "/_05_Order/PaymentServlet"), 
				@WebInitParam(name = "CMustLogin3", value = "/_04_ShoppingCart/confirmOrderPage.jsp"),
				@WebInitParam(name = "CMustLogin4", value = "/_05_Order/controller/ConfirmOrderServlet")
		})
public class LoginCheckingFilter implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(LoginCheckingFilter.class);
	List<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;
	

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while(e.hasMoreElements()) {
			String path = e.nextElement();
			url.add(fConfig.getInitParameter(path));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			servletPath = req.getServletPath();  
			contextPath = req.getContextPath();
			requestURI  = req.getRequestURI();
			HttpSession session = req.getSession();
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();
			
			
			ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
			if(cart == null || cart.getCartSum() < 1) {
				resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/_00_Index/index.jsp"));
				return;
			}
			
			if(mustLogin()) {
				if(checkLogin(req)) {
					log.info("此頁面需要登入，但已登入");
					chain.doFilter(request, response);
				}else {
					log.info("此頁面需要登入，尚未登入，所以送回index");

					if ( !isRequestedSessionIdValid ) {
						session.setAttribute("CTimeOut", "使用逾時，請重新登入");
					} else {
						// 記住原本的"requestURI"，稍後如果登入成功，系統可以自動轉入原本要執行的程式。
						session.setAttribute("CRequestURI", requestURI);	
					}
					resp.sendRedirect(resp.encodeRedirectURL(
										contextPath + "/_01_Register/c_01_register/LoginRegister.jsp"));
					return;
				}
				
			}else{
				log.info("此頁面不需要登入，直接執行此頁面程式");
				chain.doFilter(request, response);
			}
			
			
		}else {
			throw new ServletException("Request/Response 型態錯誤");
		}
		
	}

	// 判斷Session物件內是否含有識別字串為CLoginOK的屬性物件，如果有，表示已經登入，否則尚未登入
	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session =  req.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("CLoginOK");
		if(bean == null) {
			return false;
		}else {
			return true;
		}
	}

	// 如果請求的ServletPath的前導字是以某個必須登入才能使用之資源的路徑，那就必須登入。
	private boolean mustLogin() {
		boolean login = false;
		for(String sUrl : url) {
			if(sUrl.endsWith("*")) {
				sUrl = sUrl.substring(0, sUrl.length()-1);
				if(servletPath.startsWith(sUrl)) {
					login = true;
					break;
				}
			}else {
				if(servletPath.equals(sUrl)) {
					login = true;
					break;
				}
			}
		}
		return login;
	}

	public void destroy() {
	}
}
