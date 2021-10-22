package _00_init;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DistanceServlet
 */
@WebServlet("/DistanceServlet")
public class DistanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		// 註冊測試 抓三表單的值
		String A01 = request.getParameter("A01");
		String B01 = request.getParameter("B01");
		String C01 = request.getParameter("C01");
		String D01 = request.getParameter("D01");
		String E01 = request.getParameter("E01");
		String F01 = request.getParameter("F01");
		String G01 = request.getParameter("G01");
		
	
		
		session.setAttribute("A01", A01);
		session.setAttribute("B01", B01);
		session.setAttribute("C01", C01);
		session.setAttribute("D01", D01);
		session.setAttribute("E01", E01);
		session.setAttribute("F01", F01);
		session.setAttribute("G01", G01);
		
		response.getWriter().println("寫入距離成功");
		
	}

}
