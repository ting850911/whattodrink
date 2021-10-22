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

@WebServlet("/B_GetPassword2")
public class B_GetPassword2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_GetPassword2.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("商家找回密碼功能之確認驗證碼Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");

		// 確認驗證碼
		String inputCode = request.getParameter("variviedCode");
		System.out.println("inputCode:" + inputCode);
		String code = (String) session.getAttribute("code");
		String inputEmail = (String) session.getAttribute("inputEmail");
//		if (code == null) {
//			response.getWriter().print(gson.toJson("超時"));
//			return;
//		}
		System.out.println("inputEmail: " + inputEmail);
		System.out.println("sessionCode:" + code);
		if (inputCode.equals(code)) {
			response.getWriter().print(gson.toJson("yes"));
			return;
		} else {
			response.getWriter().print(gson.toJson("驗證碼錯誤"));
			return;
		}

	}

}
