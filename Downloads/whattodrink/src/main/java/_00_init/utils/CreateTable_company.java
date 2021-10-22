package _00_init.utils;
import java.sql.Date;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _01_Register.b_01_register.dao.impl.CompanyDaoImpl;
import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;

public class CreateTable_company {
	public static void main(String[] args) {

		CompanyService companyService = new CompanyServiceImpl();
		
//		String 	company_name = 			"萬波";
//		String  company_id = 			companyService.getCompanyId(company_name);
//		String 	company_account = 		"rrr3333";	
//		String 	company_password = 		"00000000";
//		String 	company_owner = 		"張三三";
//		String 	company_owner_email = 	"44@bb.cc";
//		Time 	start_time = 			Time.valueOf("9:00:00");
//		Time 	end_time = 				Time.valueOf("22:00:00");	
//		String 	tel = 					"0944444444";
//		String 	company_address = 		"台北市中山區";
//		String 	website = 				"https://www.google.com/";
//		String 	company_iconpath = 		"images/company4.jpg";
//		String 	company_filename = 		"company4.jpg";
//		String 	tax_id_number = 		"33333333";
//		Timestamp register_date = 		new Timestamp(System.currentTimeMillis());
//		Timestamp alter_date = 			new Timestamp(System.currentTimeMillis());
//		String 	company_owner_phone = 	"0944444444"; 					//負責人電話
//		String 	trade_name = 			"萬波有限公司";  					//公司行號
//		String 	bg_iconpath = 			"images/company4_bg.jpg";		//底圖
//		String 	bg_filename = 			"company4_bg.jpg";
		
		CompanyBean companyBean1 = new CompanyBean
				(companyService.getCompanyId("萬波"),
				 "萬波", 
				 "account_1", 
				 "Password1", 
				 "owner_1", 
				 "owner_email_1@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0200000000", 
				 "台北市中正區八德路一段82巷9弄18號", 
				 "https://wanpotea.com/", 
				 "images/B01/B01.jpg", 
				 "B01.jpg", 
				 "11111111", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0911111111",
				 "萬波公司行號", 
				 "images/B01/B01_bg.jpg", 
				 "B01_bg.jpg",
				 "B01");
		
		CompanyBean companyBean2 = new CompanyBean
				(companyService.getCompanyId("五十嵐"),
				 "五十嵐", 
				 "account_2", 
				 "Password2", 
				 "owner_2", 
				 "owner_email_2@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0222222222", 
				 "台北市中正區新生南路一段52之3號", 
				 "http://50lan.com/web/products.asp", 
				 "images/A01/A01.jpg", 
				 "A01.jpg", 
				 "22222222", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0922222222",
				 "五十嵐公司行號", 
				 "images/A01/A01_bg.jpg", 
				 "A01_bg.jpg",
				 "A01");
		
		CompanyBean companyBean3 = new CompanyBean
				(companyService.getCompanyId("可不可"),
				 "可不可", 
				 "account_3", 
				 "Password3", 
				 "owner_3", 
				 "owner_email_3@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0233333333", 
				 "台北市中正區八德路一段82巷9弄20之1號", 
				 "https://www.kebuke.com/menu/", 
				 "images/C01/C01.jpg", 
				 "C01.jpg", 
				 "33333333", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0933333333",
				 "可不可公司行號", 
				 "images/C01/C01_bg.jpg", 
				 "C01_bg.jpg",
				 "C01");
		
		CompanyBean companyBean4 = new CompanyBean
				(companyService.getCompanyId("迷克夏"),
				 "迷克夏", 
				 "account_4", 
				 "Password4", 
				 "owner_4", 
				 "owner_email_4@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0244444444", 
				 "台北市中山區遼寧街38號", 
				 "https://www.milkshoptea.com/products.php", 
				 "images/D01/D01.jpg", 
				 "D01.jpg", 
				 "44444444", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0944444444",
				 "迷克夏公司行號", 
				 "images/D01/D01_bg.jpg", 
				 "D01_bg.jpg",
				 "D01");
		
		CompanyBean companyBean5 = new CompanyBean
				(companyService.getCompanyId("清心"),
				 "清心", 
				 "account_5", 
				 "Password5", 
				 "owner_5", 
				 "owner_email_5@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0255555555", 
				 "台北市大安區市民大道三段136號", 
				 "https://www.chingshin.tw/product.php", 
				 "images/E01/E01.jpg", 
				 "E01.jpg", 
				 "55555555", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0955555555",
				 "清心公司行號", 
				 "images/E01/E01_bg.jpg", 
				 "E01_bg.jpg",
				 "E01");
		
		CompanyBean companyBean6 = new CompanyBean
				(companyService.getCompanyId("Coco"),
				 "Coco", 
				 "account_6", 
				 "Password6", 
				 "owner_6", 
				 "owner_email_6@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0266666666", 
				 "台北市中正區青島東路21之10號", 
				 "https://www.coco-tea.com/", 
				 "images/F01/F01.jpg", 
				 "F01.jpg", 
				 "66666666", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0966666666",
				 "Coco公司行號", 
				 "images/F01/F01_bg.jpg", 
				 "F01_bg.jpg",
				 "F01");
		
		CompanyBean companyBean7 = new CompanyBean
				(companyService.getCompanyId("ComeBuy"),
				 "ComeBuy", 
				 "account_7", 
				 "Password7", 
				 "owner_7", 
				 "owner_email_7@gmail.com", 
				 Time.valueOf("9:00:00"), 
				 Time.valueOf("22:00:00"), 
				 "0277777777", 
				 "台北市大安區新生南路一段1號", 
				 "http://www.comebuy2002.com.tw/home.php", 
				 "images/G01/G01.jpg", 
				 "G01.jpg", 
				 "77777777", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0977777777",
				 "ComeBuy公司行號", 
				 "images/G01/G01_bg.jpg", 
				 "G01_bg.jpg",
				 "G01");

//		companyBean.setDrinks(null);
//		companyBean.setCategories(null);
//		companyBean.setToppings(null);
//		companyBean.setOrders(null);
//		companyBean.setFavorites(null);		
		
		companyService.save(companyBean1);
		companyService.save(companyBean2);
		companyService.save(companyBean3);
		companyService.save(companyBean4);
		companyService.save(companyBean5);
		companyService.save(companyBean6);
		companyService.save(companyBean7);
	}
}
