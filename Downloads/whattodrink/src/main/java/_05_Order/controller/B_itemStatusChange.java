package _05_Order.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _04_ShoppingCart.model.ItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.service.ItemService;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.serviceImpl.ItemServiceImpl;
import _04_ShoppingCart.service.serviceImpl.OrderServiceImpl;

@WebServlet("/B_itemStatusChange")
public class B_itemStatusChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(B_itemStatusChange.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("商家所有明細頁面修改狀態之Controller: 開始");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ItemService itemService = new ItemServiceImpl();
		Integer item_id = Integer.valueOf(request.getParameter("item_id"));
		ItemBean itemBean = itemService.findById(item_id);
//		OrderService orderService = new OrderServiceImpl();
//		OrderBean orderBean = itemBean.getOrderBean();
		
		//待製作(預設)、製作中、已完成
		String itemStatus = request.getParameter("itemStatus"); 
		//狀態存DB後通知前端
		if(itemStatus.equals("製作中")) {
			//一筆訂單內的其中一個細項狀態改為"製作中"，這筆訂單狀態自動改為"製作中"，已經是"製作中"的訂單狀態就保持製作中 不變
			itemBean.setItemStatus(itemStatus);
			itemService.updateItemBean(itemBean);
//			if(!orderBean.getOrderStatus().equals("製作中")) {
//				orderBean.setOrderStatus("製作中");
//				orderService.updateOrderBean(orderBean);
//			}
			response.getWriter().print(itemBean.getItemStatus());
			return;
		}else if(itemStatus.equals("已完成")) {
			//一筆訂單所有細項都完成後，這筆訂單狀態自動改為"可領取"
			itemBean.setItemStatus(itemStatus);
			itemService.updateItemBean(itemBean);
//			List<ItemBean> list = itemService.findByOrderId(orderBean.getOrder_id());
//			int count = 0;
//			for(ItemBean bean : list) {
//				if(!bean.getItemStatus().equals("已完成")){
//					count++;
//					break;
//				}
//			}
//			if(count == 0) {
//				orderBean.setOrderStatus("可領取");
//				orderService.updateOrderBean(orderBean);
//				response.getWriter().print("canPickup");
//				return;
//			}
			response.getWriter().print(itemBean.getItemStatus());
			return;
		}else {
			//一筆訂單所有細項都是待製作(預防商家手誤點錯)，這筆訂單狀態自動改為"待製作"
			itemBean.setItemStatus(itemStatus);
			itemService.updateItemBean(itemBean);
//			List<ItemBean> list2 = itemService.findByOrderId(orderBean.getOrder_id());
//			int count = 0;
//			for(ItemBean bean : list2) {
//				if(!bean.getItemStatus().equals("待製作")){
//					System.out.println("for:" + count);
//					count++;
//					break;
//				}
//			}
//			if(count == 0) {
//				orderBean.setOrderStatus("待製作");
//				orderService.updateOrderBean(orderBean);
//				response.getWriter().print("yes");
//				return;
//			}
			response.getWriter().print(itemBean.getItemStatus());
		}
	}

}
