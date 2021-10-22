package _05_Order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _01_Register.c_01_register.model.CustomerBean;
import _01_Register.c_01_register.service.CustomerService;
import _01_Register.c_01_register.service.serviceImpl.CustomerServiceImpl;
import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/B_orderStatusChange")
public class B_orderStatusChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_orderStatusChange.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家未處理訂單頁面修改狀態之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrderService orderService = new OrderServiceImpl();
		ItemService itemService = new ItemServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		String order_id = request.getParameter("order_id");
		OrderBean orderBean = orderService.findById(order_id);
		List<ItemBean> list = itemService.findByOrderId(orderBean.getOrder_id());
		
		//預設待確認 、接單、取消、可領取、已領取
		//狀態 可領取: 僅顯示下拉選單"已領取"
		//狀態 已領取: 自動刪除列，換成歷史清單出現
		String orderStatus = request.getParameter("orderStatus");
		System.out.println(order_id);
		System.out.println(orderStatus);
		
		//狀態存DB後通知前端
		if(orderStatus.equals("取消")) {
			//商家取消，如果買家有使用好友折扣退還，並修改order invitaionDiscount=> 商家取消，退還好友優惠折扣50元
			CustomerBean bean = customerService.findByCustomerId(orderBean.getCustomer_id());
			bean.setInvitationCount(bean.getInvitationCount() + 1);
			orderBean.setInvitationDiscount("商家取消，退還好友優惠折扣50元");
			customerService.updateCustomer(bean);
			list.forEach(b->{
				b.setItemStatus("取消");
				itemService.updateItemBean(b);
				orderBean.setOrderStatus(orderStatus);
				orderService.updateOrderBean(orderBean);
				return;
			});
		}else if(orderStatus.equals("已領取")) {
			//set pickup_date 領取時間
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			list.forEach(b->{
				b.setItemStatus("已完成");
				itemService.updateItemBean(b);
				orderBean.setPickup_date(ts);
				orderBean.setOrderStatus(orderStatus);
				orderService.updateOrderBean(orderBean);
				return;
			});
		}
		orderBean.setOrderStatus(orderStatus);
		orderService.updateOrderBean(orderBean);
		response.getWriter().print(orderBean.getOrderStatus());
	}

}
