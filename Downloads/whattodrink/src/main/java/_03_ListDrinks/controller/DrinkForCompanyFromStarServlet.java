package _03_ListDrinks.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.CommentBean;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@WebServlet("/DrinkForCompanyFromStarServlet")
public class DrinkForCompanyFromStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DrinkForCompanyFromStarServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("從星號進入飲品資訊之Controller-POST方法開始");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		Integer product_id = Integer.parseInt(request.getParameter("product_id"));		
		DrinkService drinkService = new DrinkServiceImpl();
		DrinkBean drinkBean = drinkService.findById(product_id);
		CommentService commentService = new CommentServiceImpl();
		List<CommentBean> commentBeans = commentService.findCommentBeansById(product_id);
//		BigDecimal drinkStar = commentService.avgStarForDrink(product_id);
		
		// 創造JSON
		List<Map<String, Object>> list = new LinkedList<>();		
		for (int i = 0; i < commentBeans.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("drinkName", drinkBean.getProduct_name());
			map.put("drinkPicpath",commentBeans.get(i).getComment_picpath());
			map.put("drinkComment", commentBeans.get(i).getFeedback());
			map.put("drinkStar", commentBeans.get(i).getStar().intValue());
			list.add(map);
		}
		
		response.getWriter().print(JSON.toJSONString(list));
		System.out.println("===========================");
		System.out.println(JSON.toJSONString(list));
		System.out.println("===========================");

	}

}
