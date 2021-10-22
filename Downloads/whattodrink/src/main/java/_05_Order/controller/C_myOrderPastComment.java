package _05_Order.controller;

import java.io.IOException;
import java.text.DateFormat;
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
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;
import _07_Others.model.CommentBean;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@WebServlet("/C_myOrderPastComment")
public class C_myOrderPastComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(C_myOrderPastComment.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("顧客訂單頁面_目前訂單之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得顧客會員登入資訊
		HttpSession session = request.getSession(false);
//		CustomerService customerService = new CustomerServiceImpl();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");

		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		String order_id = request.getParameter("reviewId"); // 取得該筆訂單編號
		CommentService commentService = new CommentServiceImpl();
		List<CommentBean> commentBeans = commentService.findCommentBeansByOrderId(order_id);
		for (CommentBean commentBean : commentBeans) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemName", commentBean.getDrinkBean().getProduct_name());
			map.put("star", commentBean.getStar());
			map.put("feedback", commentBean.getFeedback());
			map.put("picPath", commentBean.getComment_picpath());
			list.add(map);
		}
		System.out.println("=================================================");
		System.out.println(list);
		System.out.println("=================================================");
		response.getWriter().print(JSON.toJSONString(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
