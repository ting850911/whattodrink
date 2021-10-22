package _01_Register.b_01_register.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _00_init.utils.GlobalService;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;


@WebServlet("/_01_Register/B_RegisterUpdateServlet.do")
public class B_RegisterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static Logger log = LoggerFactory.getLogger(B_RegisterUpdateServlet.class);
//	private static final String PASSWORD_PATTERN = "/^[A-Za-z0-9]+{8,}";
//	private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,}";
//	private static final String PASSWORD_PATTERN = "[(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])]{8,}";
//	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%^'\"\\]){8,}";
//	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
//	private Pattern pattern = null;
//	private Matcher matcher = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家註冊帳密insert功能之Controller: 開始");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();	
		request.setCharacterEncoding("UTF-8");
		CompanyService companyService = new CompanyServiceImpl();
			

		String email = (String) session.getAttribute("successEmail");
		//取得商家帳密
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		String password2 = request.getParameter("password2");	
		System.out.println(username + " " + password + " " + email);
//		pattern = Pattern.compile(PASSWORD_PATTERN);
//		matcher = pattern.matcher(password);
//		if(!matcher.matches()){
//			errorMsg.put("passwordFormatError", "密碼僅為大小寫字母與數字混和且長度不能小於八個字元");
//		}
//		if(companyService.existsByAccount(account) == true) {
//			errorMsg.put("accountError", "此帳號已有人使用");
//		}
//		if(!errorMsg.isEmpty()) {
//			response.getWriter().println(gson.toJson(errorMsg));
//			return;
//		}
		
		//確認帳號
		if(companyService.findByCompanyAccount(username) == null  && username.trim().length() > 0) {
			response.getWriter().println(gson.toJson("yes"));				
			if(username != null && password != null &&  password.trim().length() > 0 && password2.trim().length() > 0) { //同個請求收到兩個值才能insert
//			if(password == null) {
//				response.getWriter().println(gson.toJson("yes"));				
//			}else if(username != null && password != null &&  password.trim().length() > 0) { //同個請求收到兩個值才能insert
				try {
					//insert username和password
					CompanyBean cb = companyService.findByCompanyEmail(email);
					cb.setCompany_account(username);
					cb.setCompany_password(password);
					cb.setBg_iconpath("images/noimageCompany.png");
					cb.setBg_filename("noimageCompany.png");
					cb.setCompany_filename("noimageCompany.png");
					cb.setCompany_iconpath("images/noimageCompany.png");
					cb.setAlter_date(new Timestamp(System.currentTimeMillis()));
					companyService.updateCompany(cb);
					gson = new Gson();	
					response.getWriter().println(gson.toJson("InsertOK"));
				}catch(Exception e){
					log.info("商家註冊帳密update失敗");
					e.printStackTrace();
				}			
			}
		}
		
	}
}
