package _01_Register.b_01_register.controller;

import java.io.IOException;
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

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;


@WebServlet("/_01_Register/B_RegisterCheckServlet.do")
public class B_RegisterCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(B_RegisterCheckServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家註冊功能之Controller: 開始");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		CompanyService companyService = new CompanyServiceImpl();
		
		//取得商家email和邀請碼
		String storeEmail = request.getParameter("email");		
		String storeInvitationCode = request.getParameter("invitationCode");
		System.out.println(storeEmail + " " + storeInvitationCode);
		
		//確認email
		if(companyService.findByCompanyEmail(storeEmail) != null) {	
			session.setAttribute("successEmail", storeEmail);
			response.getWriter().print(gson.toJson("yes"));
			return;
		}
//		else if(storeInvitationCode == null){
//			response.getWriter().print(gson.toJson("no"));
//			return;
//		}
		
		//確認邀請碼
		if(companyService.findByCompanyInvitation(storeInvitationCode) != null) {	
			response.getWriter().print(gson.toJson("yes"));
			return;
		}else {
			response.getWriter().print(gson.toJson("no"));
		}
		
		
		
//		//確認email
//		if(companyService.findByCompanyEmail(storeEmail) != null && storeInvitationCode == null) {	
//			session.setAttribute("successEmail", storeEmail);
//			response.getWriter().print(gson.toJson("yes"));
//		}else if(storeEmail == null && storeInvitationCode == null){
//			response.getWriter().print(gson.toJson("no"));
//		}
//		
//		//確認邀請碼
//		if(companyService.findByCompanyInvitation(storeInvitationCode) != null && storeEmail == null) {	
//			response.getWriter().print(gson.toJson("yes"));
//		}else if(companyService.findByCompanyInvitation(storeInvitationCode) != null && companyService.findByCompanyEmail(storeEmail) != null){
//			response.getWriter().print(gson.toJson("no"));
//		}
	
		
	}
}
