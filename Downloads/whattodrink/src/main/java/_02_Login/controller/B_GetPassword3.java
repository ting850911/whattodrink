package _02_Login.controller;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@WebServlet("/B_GetPassword3")
public class B_GetPassword3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_GetPassword3.class);
	
//	private static final String PASSWORD_PATTERN = "/^[A-Za-z0-9]+{8,}";
//	private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,}";
//	private static final String PASSWORD_PATTERN = "[(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])]{8,}";
//	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%^'\"\\]){8,}";
//	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
//	private Pattern pattern = null;
//	private Matcher matcher = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("商家找回密碼功能之insert新密碼Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		CompanyService companyService = new CompanyServiceImpl();

		String inputEmail = (String) session.getAttribute("inputEmail");
		System.out.println(inputEmail);
		if(inputEmail == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_02_Login/b_02_login/1_business_forgot_password.jsp"));
			return;
		}

		// 確認密碼
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		System.out.println("password:" + password);
		System.out.println("password2:" + password2);
//		pattern = Pattern.compile(PASSWORD_PATTERN);
//		matcher = pattern.matcher(password);
//		if(!matcher.matches()){
//			response.getWriter().print(gson.toJson("密碼僅為大小寫字母與數字混和且長度不能小於八個字元"));
//			return;
//		}
		
		if(password.equals(password2) && password.trim().length() > 0) {
			CompanyBean bean =  companyService.findByCompanyEmail(inputEmail);
			bean.setCompany_password(password);
			companyService.updateCompany(bean);
			response.getWriter().print(gson.toJson("yes"));
			return;
		}else {
			response.getWriter().print(gson.toJson("密碼不一致"));
			return;
		}
	}

}
