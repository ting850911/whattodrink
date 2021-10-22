package _07_Others.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;
import _07_Others.model.CommentBean;
import _07_Others.service.CommentService;
import _07_Others.service.serviceImpl.CommentServiceImpl;

@MultipartConfig
@WebServlet("/AddCommentsServlet")
public class AddCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemService itemService = new ItemServiceImpl();
		String order_id = request.getParameter("order_id");
		List<ItemBean> itemBeans = itemService.findByOrderId(order_id);
		request.setAttribute("itemBeans", itemBeans);
		RequestDispatcher rd = request.getRequestDispatcher("/_07_Others/c__07_others_review/review.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String dirPath = "c:/_JSP/workspace" 
					   + getServletContext().getContextPath() 
					   + "/src/main/webapp/images/comment";
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		CustomerService customerService = new CustomerServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		DrinkService drinkService = new DrinkServiceImpl();
		
		CommentService commentService = new CommentServiceImpl();
		for (int i = 0; i < request.getParameterValues("product_id").length; i++) {
			CommentBean commentBean = new CommentBean();
			commentBean.setCustomer_id(Integer.parseInt(request.getParameterValues("customer_id")[i]));
			commentBean.setOrder_id(request.getParameterValues("order_id")[i]);
			commentBean.setProduct_id(Integer.parseInt(request.getParameterValues("product_id")[i]));
			commentBean.setStar(BigDecimal.valueOf(Double.parseDouble(request.getParameterValues("star")[i])));
			
			if (!request.getParameterValues("comment")[i].isBlank()) {
				commentBean.setFeedback(request.getParameterValues("comment")[i]);
			}
			
			commentBean.setComment_date(new Timestamp(System.currentTimeMillis()));
			
			if (request.getParameterValues("upload")[i].equals("1")) {
				System.out.println("第" + i + "筆有上傳圖片");
				commentBean.setComment_picpath(dirPath.substring(dirPath.indexOf("images")) + "/" + request.getParameterValues("item_id")[i] + ".jpg");
				commentBean.setComment_picname(request.getParameterValues("item_id")[i] + ".jpg");
			}
			
			commentBean.setCustomerBean(customerService.findByCustomerId(commentBean.getCustomer_id()));
			commentBean.setOrderBean(orderService.findById(commentBean.getOrder_id()));
			commentBean.setDrinkBean(drinkService.findById(commentBean.getProduct_id()));
			commentService.save(commentBean);
		}
		
		//存評價照片至指定位置
		Iterator<Part> pics = request.getParts().iterator();
		int i = 0;
		while (pics.hasNext()) {
			Part pic = pics.next();
			if (pic.getName().equals("photo[0]")) {
				String fileName = dirPath + "/" + request.getParameterValues("item_id")[i] + ".jpg";
				i++;
				if (pic.getSubmittedFileName() != null) {
					pic.write(fileName);
				}
			}
		}
	}
}
