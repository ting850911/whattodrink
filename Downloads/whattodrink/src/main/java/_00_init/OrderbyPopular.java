package _00_init;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

@WebServlet("/OrderbyPopular")
public class OrderbyPopular extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CompanyService companyService = new CompanyServiceImpl();
		List<CompanyBean> companyBeans = companyService.findAllByHitRank();
		Map<String, JSONArray> map = new HashMap<>();
		JSONArray Company_iconPathJson = new JSONArray();
		JSONArray Company_nameJson = new JSONArray();
		JSONArray Trade_nameJson = new JSONArray();
		JSONArray DistanceJson = new JSONArray();
		JSONArray Company_idJson = new JSONArray();
				HttpSession session = request.getSession();
			if (session.getAttribute("A01") != null) {
				String A01 = (String) session.getAttribute("A01");
				String B01 = (String) session.getAttribute("B01");
				String C01 = (String) session.getAttribute("C01");
				String D01 = (String) session.getAttribute("D01");
				String E01 = (String) session.getAttribute("E01");
				String F01 = (String) session.getAttribute("F01");
				String G01 = (String) session.getAttribute("G01");

				
				
				for (int i = 0; i < companyBeans.size(); i++) {

					if (companyBeans.get(i).getCompany_id().equals("A01")) {
						companyBeans.get(i).setDistance(A01);
					}
					if (companyBeans.get(i).getCompany_id().equals("B01")) {
						companyBeans.get(i).setDistance(B01);
					}
					if (companyBeans.get(i).getCompany_id().equals("C01")) {
						companyBeans.get(i).setDistance(C01);
					}
					if (companyBeans.get(i).getCompany_id().equals("D01")) {
						companyBeans.get(i).setDistance(D01);
					}
					if (companyBeans.get(i).getCompany_id().equals("E01")) {
						companyBeans.get(i).setDistance(E01);
					}
					if (companyBeans.get(i).getCompany_id().equals("F01")) {
						companyBeans.get(i).setDistance(F01);
					}
					if (companyBeans.get(i).getCompany_id().equals("G01")) {
						companyBeans.get(i).setDistance(G01);
					}
				
				}
				
			}
			

			for (int i = 0; i < companyBeans.size(); i++) {
				Company_iconPathJson.add(companyBeans.get(i).getCompany_iconpath());
				Company_nameJson.add(companyBeans.get(i).getCompany_name());
				Trade_nameJson.add(companyBeans.get(i).getTrade_name());
				DistanceJson.add(companyBeans.get(i).getDistance());
				Company_idJson.add(companyBeans.get(i).getCompany_id());
			}
			
			map.put("Company_iconPathJson", Company_iconPathJson);
			map.put("Company_nameJson", Company_nameJson);
			map.put("Trade_nameJson", Trade_nameJson);
			map.put("DistanceJson", DistanceJson);
			map.put("Company_idJson", Company_idJson);
			
		
	System.out.println(map);
		response.getWriter().println(JSON.toJSONString(map));
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
