package _06_Maintain.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@MultipartConfig
@WebServlet("/BusinessCompanyInfoServlet")
public class BusinessCompanyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		Map<String, Object> details = new HashMap<>();
		details.put("company_name", companyBean.getCompany_name());
		details.put("company_address", companyBean.getCompany_address());
		details.put("start_time", companyBean.getStart_time());
		details.put("end_time", companyBean.getEnd_time());
		details.put("tel", companyBean.getTel());
		details.put("company_owner", companyBean.getCompany_owner());
		details.put("trade_name", companyBean.getTrade_name());
		details.put("tax_id_number", companyBean.getTax_id_number());
		details.put("company_owner_phone", companyBean.getCompany_owner_phone());
		details.put("company_iconpath", companyBean.getCompany_iconpath());
		details.put("bg_iconpath", companyBean.getBg_iconpath());
		details.put("business_name", companyBean.getBusiness_name());
		String companyBeanJson = JSON.toJSONString(details);
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(companyBeanJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int type = Integer.parseInt(request.getParameter("type"));
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		if (type == 1) {
			String storeName = request.getParameter("storeName");
			String storeAddress = request.getParameter("storeAddress");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String storeTel = request.getParameter("storeTel");
			companyBean.setTrade_name(storeName);
			companyBean.setCompany_address(storeAddress);
			companyBean.setStart_time(Time.valueOf(startTime));
			companyBean.setEnd_time(Time.valueOf(endTime));
			companyBean.setTel(storeTel);
		}
		if (type == 2) {
			String person = request.getParameter("person");
			String num = request.getParameter("num");
			String tax_id_number = request.getParameter("tax_id_number");
			String tel = request.getParameter("tel");
			companyBean.setCompany_owner(person);
			companyBean.setBusiness_name(num);
			companyBean.setTax_id_number(tax_id_number);
			companyBean.setCompany_owner_phone(tel);
		}
		if (type == 3) {
			Part part = request.getPart("pic1");
//			String mimeType = part.getSubmittedFileName()
//								  .substring(part.getSubmittedFileName().lastIndexOf("."));
//			System.out.println(mimeType);
//			if (!mimeType.equals("jpg")) {
//				response.getWriter().write(0);
//				return;
//			}
			String dirPath = "C:/_JSP/workspace"+ 
						   getServletContext().getContextPath() + 
						   "/src/main/webapp/images/" +
						   companyBean.getCompany_id();
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String realPath = dirPath + "/" + companyBean.getCompany_id() + ".jpg";
			part.write(realPath);
			companyBean.setCompany_iconpath(realPath.substring(realPath.indexOf("images")));
			companyBean.setCompany_filename(realPath.substring(realPath.lastIndexOf("/") + 1));
		}
		if (type == 4) {
			Part part = request.getPart("pic2");
			String dirPath = "C:/_JSP/workspace"+ 
					   getServletContext().getContextPath() + 
					   "/src/main/webapp/images/" +
					   companyBean.getCompany_id();
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String realPath = dirPath + "/" + companyBean.getCompany_id() + "_bg.jpg";
			part.write(realPath);
			companyBean.setBg_iconpath(realPath.substring(realPath.indexOf("images")));
			companyBean.setBg_filename(realPath.substring(realPath.lastIndexOf("/") + 1));
		}
		companyBean.setAlter_date(new Timestamp(System.currentTimeMillis()));
		CompanyService companyService = new CompanyServiceImpl();
		companyService.updateCompany(companyBean);
		
		if (type == 1 || type == 2) {
			// 因為前兩大欄是Ajax 後端沒辦法跳轉頁面 (應該啦)
			// 寫出特定字串讓前端判別要跳轉到哪裡
			String url = request.getContextPath() + "/_06_Maintain/b_06_maintain/1_business_company_info.jsp";
			response.getWriter().write(url);
		}
		if (type == 3 || type == 4) {
			// 更新圖片是一般的form表單 可以直接透過後端跳轉頁面
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.sendRedirect(request.getContextPath() + "/_06_Maintain/b_06_maintain/1_business_company_info.jsp");
		}
		
	}

}
