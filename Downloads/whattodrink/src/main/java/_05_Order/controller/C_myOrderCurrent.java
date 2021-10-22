package _05_Order.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.OrderBean;

@WebServlet("/C_myOrderCurrent")
public class C_myOrderCurrent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(C_myOrderCurrent.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("顧客訂單頁面_目前訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		List<Map<String, Object>> lastList = new LinkedList<Map<String, Object>>();

		Map<String, Object> map0 = new HashMap<String, Object>();
		List<OrderBean> list = customerService.findTodayOrdersByCustomerId(customerBean.getCustomer_id());
		if (list.size() != 0) {
			map0.put("newOrd", 1);
			lastList.add(0, map0); // 加至List指定位置
		} else {
			map0.put("newOrd", 0);
			lastList.add(0, map0); // 加至List指定位置
		}

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			String ordId = list.get(i).getOrder_id();
			String picPath = list.get(i).getCompanyBean().getBg_iconpath();
			String storeName = list.get(i).getCompanyBean().getCompany_name();
			String branchName = list.get(i).getCompanyBean().getTrade_name();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ordTime = sdf.format(list.get(i).getOrder_date());
			String ordState = list.get(i).getOrderStatus();
			map1.put("ordId", ordId);
			map1.put("picPath", picPath);
			map1.put("storeName", storeName);
			map1.put("branchName", branchName);
			map1.put("ordTime", ordTime);
			map1.put("ordState", ordState);
			lastList.add(map1);
		}

		System.out.println("=================================================");
		System.out.println(lastList);
		System.out.println("=================================================");
		response.getWriter().print(JSON.toJSONString(lastList));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
