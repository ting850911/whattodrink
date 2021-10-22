	package _02_Login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import _00_init.utils.SendingEmail;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@WebServlet("/B_GetAccount")
public class B_GetAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_GetAccount.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家找回帳戶/密碼功能之確認信箱Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		CompanyService companyService = new CompanyServiceImpl();
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		
		//取得信箱
		String email = request.getParameter("email");
		System.out.println("email:" + email);
		CompanyBean companyBean = companyService.findByCompanyEmail(email);
		if(companyBean != null) {
			response.getWriter().print(gson.toJson("yes"));
		}else {
			response.getWriter().print(gson.toJson("no"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家找回帳戶功能之寄送帳號Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		CompanyService companyService = new CompanyServiceImpl();
		response.setCharacterEncoding("UTF-8");
		
		//取得信箱
		String email = request.getParameter("email");
		System.out.println("email:" + email);
		CompanyBean companyBean = companyService.findByCompanyEmail(email);
		SendingEmail.SendCompanyAccountTo(email, companyBean.getCompany_account());
	}

}
