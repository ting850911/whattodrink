package _00_init;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _01_Register.c_01_register.model.CustomerBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.CommentBean;
import _07_Others.model.FavoriteBean;
import _07_Others.service.CommentService;
import _07_Others.service.FavoriteService;
import _07_Others.service.serviceImpl.CommentServiceImpl;
import _07_Others.service.serviceImpl.FavoriteServiceImpl;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter(urlPatterns = "/_00_Index/index.jsp")
public class IndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IndexFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		CompanyService companyService = new CompanyServiceImpl();
//		List<CompanyBean> companyBeans = companyService.findAll();
//		request.setAttribute("companyList", companyBeans);
		
//		CommentService commentService = new CommentServiceImpl();
//		List<CommentBean> commentBeans = commentService.findAllCommentBeans();
//		request.setAttribute("commentList", commentBeans);
	
//		chain.doFilter(request, response);
		
		if (((HttpServletRequest) request).getSession(false) != null) {
			
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			
			if (session.getAttribute("CLoginOK") != null) {
				CustomerBean customerBean = (CustomerBean) session.getAttribute("CLoginOK");
				Integer customer_id = customerBean.getCustomer_id();

				FavoriteService favoriteService = new FavoriteServiceImpl();
				List<FavoriteBean> favoriteBeans = favoriteService.findByCustomer_id(customer_id);
				List<String> orderNoList=favoriteBeans.stream().map(FavoriteBean::getCompany_id).collect(Collectors.toList());
				String favoritecompany = orderNoList.stream().collect(Collectors.joining(","));
		
				request.setAttribute("favoritecompany", favoritecompany);
			}
		}
		
		CompanyService companyService = new CompanyServiceImpl();
		List<CompanyBean> companyBeans = companyService.findAllByHitRank();
		
		if (((HttpServletRequest) request).getSession(false) != null) {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
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
		}
		
		request.setAttribute("companyList", companyBeans);
		
		CommentService commentService = new CommentServiceImpl();
		List<CommentBean> commentBeans = commentService.findAllCommentBeans();
		LinkedList<DrinkBean> drinkList = new LinkedList<>();
		request.setAttribute("commentListLength", commentBeans.size());
		
		
		CommentBean[] commentArray = commentBeans.toArray(new CommentBean[commentBeans.size()]);
		request.setAttribute("commentArray", commentArray);
		
		DrinkService drinkService = new DrinkServiceImpl();
		
		for(CommentBean bean : commentBeans) {
			drinkList.add(drinkService.findById(bean.getProduct_id()));
		}
		DrinkBean[] drinkArray = drinkList.toArray(new DrinkBean[drinkList.size()]);
		request.setAttribute("drinkArray", drinkArray);
	
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
