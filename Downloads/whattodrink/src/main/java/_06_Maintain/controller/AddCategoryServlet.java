package _06_Maintain.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.service.CategoryService;
import _03_ListDrinks.service.serviceImpl.CategoryServiceImpl;

@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AddCategoryServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		String company_id = companyBean.getCompany_id();
		CategoryService categoryService = new CategoryServiceImpl();

		Map<String, Object> map = new LinkedHashMap<>();
		List<String> categoryNameList = new LinkedList<String>();

		List<CategoryBean> categoryBeans = categoryService.findByCompanyId(company_id);
		int empty = 10 - categoryBeans.size();
		if (categoryBeans.size() == 0) {
			for (int i = 0; i < empty; i++) {
				categoryNameList.add("");
			}
		} else {
			for (CategoryBean categoryBean : categoryBeans) {
				String name = categoryBean.getCategory_name();
				categoryNameList.add(name);
			}
			for (int i = 0; i < empty; i++) {
				categoryNameList.add("");
			}
		}
		map.put("name", categoryNameList);
		response.getWriter().print(JSON.toJSONString(map));
		System.out.println("===========================");
		System.out.println(JSON.toJSONString(map));
		System.out.println("===========================");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("企業用戶新增分類之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		CompanyBean companyBean = (CompanyBean) session.getAttribute("BLoginOK");
		String company_id = companyBean.getCompany_id();
		CategoryService categoryService = new CategoryServiceImpl();

		for (int i = 1; i <= 10; i++) {
			String inputKey = "name" + i;
			if (request.getParameter(inputKey).length() != 0) {
				String name = request.getParameter(inputKey);
				// list當中只會是零筆或一筆資料
				List<CategoryBean> list = categoryService.findByCategoryNameAndCompanyId(name, company_id);
				System.out.println("--------------------------");
				System.out.println("inputKey is " + inputKey);
				System.out.println("list.size() " + list.size());
				System.out.println("--------------------------");
				if (list.size() == 1) {
					// list一筆 -> 舊有資料無變動 -> 不做任何處理
				} else {
					// list零筆 -> 新增
					CategoryBean categoryBean = new CategoryBean();
					categoryBean.setCompany_id(company_id);
					categoryBean.setCategory_name(name);
					categoryBean.setCompanyBean(companyBean);
					categoryService.save(categoryBean);
					System.out.println("==========================");
					System.out.println("categoryBean " + categoryBean);
					System.out.println("==========================");
				}
			}
		}

		// 新增之後倒回原頁面
		RequestDispatcher rd = request.getRequestDispatcher("/_06_Maintain/b_06_maintain/1_business_add_catalog.jsp");
		rd.forward(request, response);

	}

}
