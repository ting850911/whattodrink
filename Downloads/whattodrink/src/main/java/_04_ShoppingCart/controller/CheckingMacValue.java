package _04_ShoppingCart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecpay.payment.integration.domain.ATMRequestObj;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@WebServlet("/_04_ShoppingCart/CheckingMacValue")
public class CheckingMacValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String IP_ADDR = "localhost";
	public static final int PORT = 80;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("HI");
		String key = request.getParameter("MerchantID");
		System.out.println("after HI");
		String iv = "50BE3989953C1734E32DD18EB23698241E035F9CBCAC74371CCCF09E0E15BD61";
		String merchantTradeNo = request.getParameter("MerchantTradeNo");
		String paymentDate = request.getParameter("PaymentDate");
		String paymentType = request.getParameter("PaymentType");
		String paymentTypeChargeFee = request.getParameter("PaymentTypeChargeFee");
		String rtnCode = request.getParameter("RtnCode");
		String rtnMsg = request.getParameter("RtnMsg");
		String simulatePaid = request.getParameter("SimulatePaid");
		String tradeAmt = request.getParameter("TradeAmt");
		String tradeDate = request.getParameter("TradeDate");
		String tradeNo = request.getParameter("TradeNo");
		String checkMacValue = request.getParameter("CheckMacValue");
		
		
		ATMRequestObj obj = new ATMRequestObj();
		obj.setMerchantID(key);
		obj.setMerchantTradeNo(merchantTradeNo);
		obj.setPaymentDate(paymentDate);
		obj.setPaymentType(paymentType);
		obj.setPaymentTypeChargeFee(paymentTypeChargeFee);
		obj.setRtnCode(rtnCode);
		obj.setRtnMsg(rtnMsg);
		obj.setSimulatePaid(simulatePaid);
		obj.setTradeAmt(tradeAmt);
		obj.setTradeDate(tradeDate);
		obj.setTradeNo(tradeNo);
		obj.setCheckMacValue(checkMacValue);
		
		
		System.out.println("merchantTradeNo" + merchantTradeNo);
		System.out.println("paymentDate" + paymentDate);
		System.out.println("paymentType" + paymentType);
		System.out.println("paymentTypeChargeFee" + paymentTypeChargeFee);
		System.out.println("rtnCode" + rtnCode);
		System.out.println("rtnMsg" + rtnMsg);
		System.out.println("simulatePaid" + simulatePaid);
		System.out.println("tradeAmt" + tradeAmt);
		System.out.println("tradeDate" + tradeDate);
		System.out.println("tradeNo" + tradeNo);
		System.out.println("checkMacValue" + checkMacValue);
		
		System.out.println("------");
		System.out.println(EcpayFunction.genCheckMacValue(key, iv, obj));
		
		if(rtnCode.equals("1")) {
			System.out.println("1|OK");
			response.getWriter().write("1|OK");
			return;
		}
		System.out.println("Fail");
		
		
		
		
	}

}
