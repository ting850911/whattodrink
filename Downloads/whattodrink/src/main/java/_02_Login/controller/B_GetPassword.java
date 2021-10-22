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

import _00_init.utils.CreateVerificationCode;
import _00_init.utils.SendingEmail;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@WebServlet("/B_GetPassword")
public class B_GetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_GetPassword.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("商家找回密碼功能之寄送驗證碼Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		
		// 取得信箱並寄送驗證碼
		String email = request.getParameter("email");
		System.out.println("email:" + email);
		String code = CreateVerificationCode.getVerificationCode();
		session.setAttribute("code", code);
		System.out.println("code:" + code);
		session.setAttribute("inputEmail", email);
		SendingEmail.SendVerificationCodeTo(email, code);
//		try {
//			new Thread(() -> {
//				try {
//					Thread.sleep(115000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				session.removeAttribute("code");
//				System.out.println("移除code:" + code);
//			}).start();
//		}catch(Exception e) {
//			e.getMessage();
//		}
	}

}
