package example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.AioCheckOutBARCODE;
import ecpay.payment.integration.domain.AioCheckOutCVS;
import ecpay.payment.integration.domain.AioCheckOutWebATM;
import ecpay.payment.integration.domain.InvoiceObj;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;


@WebServlet("/example/ExampleECPay")
public class ExampleECPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String itemName; 
    
	
	
	public static AllInOne all;

    public ExampleECPay() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initial();
		response.setContentType("text/html;charset=UTF-8");
		itemName = request.getParameter("itemName");
        PrintWriter out = response.getWriter();
        out.println(genAioCheckOutALL());
//        out.println(genAioCheckOutBARCODE());
//        out.println(genAioCheckOutWebATM());
//        out.println(genAioCheckOutCVS());
        
        
	}
	
	private static void initial(){
		all = new AllInOne();
	}
	
	
	//ALL
	public static String genAioCheckOutALL(){		
		AioCheckOutALL obj = new AioCheckOutALL();
		InvoiceObj invoice = new InvoiceObj();		
		obj.setMerchantTradeNo("test990o0");
		obj.setMerchantTradeDate("2021/07/23 15:14:00");
		obj.setTotalAmount("100");
		obj.setTradeDesc("aaaa");
		obj.setItemName(itemName);
//		EcpayFunction.genCheckMacValue(key, iv, obj);
		//必填，僅停留付款成功頁面，此處須設計頁面接收綠界交易結果並回傳參數
//		obj.setReturnURL("https://whattodrink.000webhostapp.com/");
		obj.setReturnURL("http://localhost:8080/whattodrink/");
		
		//付款成功後按返回按鈕
		obj.setClientBackURL("http://localhost:8080/whattodrink/");
		
		
		//付款成功後返回client端網址  
//		obj.setOrderResultURL("https://whattodrink.000webhostapp.com/");
		obj.setNeedExtraPaidInfo("N");
		
		obj.setInvoiceMark("Y");
//		invoice.setRelateNumber("test202031test");
		invoice.setRelateNumber("202107233");
		invoice.setCustomerID("123456");
		invoice.setCarruerType("1");
		invoice.setTaxType("1");
		invoice.setCarruerNum("");
		invoice.setDonation("0");
//		invoice.setLoveCode("X123456");
		invoice.setPrint("0");
		invoice.setCustomerName("Bellll");
		invoice.setCustomerAddr("台北市南港區三重路");
		invoice.setCustomerPhone("0911429215");
		invoice.setDelayDay("0");
		invoice.setInvType("07");
		invoice.setInvoiceItemName("測試");
		invoice.setInvoiceItemCount("1");
		invoice.setInvoiceItemWord("個");
		invoice.setInvoiceItemPrice("50");
		invoice.setInvoiceItemTaxType("1");
		
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
	
	//超商條碼
	public static String genAioCheckOutBARCODE(){
		AioCheckOutBARCODE obj = new AioCheckOutBARCODE();
		obj.setMerchantTradeNo("testCompan0001");
		obj.setMerchantTradeDate("2021/07/22 00:21:00");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setStoreExpireDate("3");
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
	//網路ATM 不建議
	public static String genAioCheckOutWebATM(){
		AioCheckOutWebATM obj = new AioCheckOutWebATM();
		obj.setMerchantTradeNo("SQSQSQ");
		obj.setMerchantTradeDate("2021/07/22 00:21:00");
		obj.setTotalAmount("100");
		obj.setTradeDesc("test Description");
		obj.setItemName("Test Item");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
	
	//超商代碼
	public static String genAioCheckOutCVS(){
		AioCheckOutCVS obj = new AioCheckOutCVS();
		InvoiceObj invoice = new InvoiceObj();
		UUID uid = UUID.randomUUID();
		obj.setMerchantTradeNo(uid.toString().replaceAll("-", "").substring(0, 20));
		obj.setMerchantTradeDate("2021/07/22 00:21:00");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setStoreExpireDate("3");
		obj.setInvoiceMark("Y");
		invoice.setRelateNumber("test202023test");
		invoice.setCustomerID("123456");
		invoice.setCarruerType("1");
		invoice.setTaxType("1");
		invoice.setCarruerNum("");
		invoice.setDonation("0");
		invoice.setLoveCode("X123456");
		invoice.setPrint("0");
		invoice.setCustomerName("Mark");
		invoice.setCustomerAddr("台北市南港區三重路");
		invoice.setCustomerPhone("0911429215");
		invoice.setDelayDay("1");
		invoice.setInvType("07");
		invoice.setInvoiceItemName("測試");
		invoice.setInvoiceItemCount("1");
		invoice.setInvoiceItemWord("個");
		invoice.setInvoiceItemPrice("50");
		invoice.setInvoiceItemTaxType("1");
		String form = all.aioCheckOut(obj, invoice);
		return form;
	}

}
