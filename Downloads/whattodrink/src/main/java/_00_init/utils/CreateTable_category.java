package _00_init.utils;
import java.sql.Time;
import java.sql.Timestamp;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.service.CategoryService;
import _03_ListDrinks.service.serviceImpl.CategoryServiceImpl;

public class CreateTable_category {
	public static void main(String[] args) {

		CompanyService companyService = new CompanyServiceImpl();
		CategoryService categoryService = new CategoryServiceImpl();
		
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
				 "images/company1.jpg", 
				 "company1.jpg", 
				 "11111111", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0911111111",
				 "萬波公司行號", 
				 "images/company1_bg.jpg", 
				 "company1_bg.jpg",
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
				 "images/company2.jpg", 
				 "company2.jpg", 
				 "22222222", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0922222222",
				 "五十嵐公司行號", 
				 "images/company2_bg.jpg", 
				 "company2_bg.jpg",
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
				 "images/company3.jpg", 
				 "company3.jpg", 
				 "33333333", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0933333333",
				 "可不可公司行號", 
				 "images/company3_bg.jpg", 
				 "company3_bg.jpg",
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
				 "images/company4.jpg", 
				 "company4.jpg", 
				 "44444444", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0944444444",
				 "迷克夏公司行號", 
				 "images/company4_bg.jpg", 
				 "company4_bg.jpg",
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
				 "images/company5.jpg", 
				 "company5.jpg", 
				 "55555555", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0955555555",
				 "清心公司行號", 
				 "images/company5_bg.jpg", 
				 "company5_bg.jpg",
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
				 "images/company6.jpg", 
				 "company6.jpg", 
				 "66666666", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0966666666",
				 "Coco公司行號", 
				 "images/company6_bg.jpg", 
				 "company6_bg.jpg",
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
				 "images/company7.jpg", 
				 "company7.jpg", 
				 "77777777", 
				 new Timestamp(System.currentTimeMillis()), 
				 new Timestamp(System.currentTimeMillis()),
				 "0977777777",
				 "ComeBuy公司行號", 
				 "images/company7_bg.jpg", 
				 "company7_bg.jpg",
				 "G01");

//		companyBean.setDrinks(null);
//		companyBean.setCategories(null);
//		companyBean.setToppings(null);
//		companyBean.setOrders(null);
//		companyBean.setFavorites(null);		
		
		CategoryBean categoryBean1 = new CategoryBean(null, "果", companyBean1.getCompany_id());
		categoryBean1.setCompanyBean(companyBean1);
		CategoryBean categoryBean2 = new CategoryBean(null, "茗", companyBean1.getCompany_id());
		categoryBean2.setCompanyBean(companyBean1);
		CategoryBean categoryBean3 = new CategoryBean(null, "季", companyBean1.getCompany_id());
		categoryBean3.setCompanyBean(companyBean1);
		CategoryBean categoryBean4 = new CategoryBean(null, "濃", companyBean1.getCompany_id());
		categoryBean4.setCompanyBean(companyBean1);
		categoryService.save(categoryBean1);
		categoryService.save(categoryBean2);
		categoryService.save(categoryBean3);
		categoryService.save(categoryBean4);
		
		CategoryBean categoryBean5 = new CategoryBean(null, "找好茶系列", companyBean2.getCompany_id());
		categoryBean5.setCompanyBean(companyBean2);
		CategoryBean categoryBean6 = new CategoryBean(null, "找奶茶系列", companyBean2.getCompany_id());
		categoryBean6.setCompanyBean(companyBean2);
		CategoryBean categoryBean7 = new CategoryBean(null, "找口感系列", companyBean2.getCompany_id());
		categoryBean7.setCompanyBean(companyBean2);
		CategoryBean categoryBean8 = new CategoryBean(null, "找新鮮系列", companyBean2.getCompany_id());
		categoryBean8.setCompanyBean(companyBean2);
		CategoryBean categoryBean9 = new CategoryBean(null, "找拿鐵系列", companyBean2.getCompany_id());
		categoryBean9.setCompanyBean(companyBean2);
		CategoryBean categoryBean10 = new CategoryBean(null, "客製化系列", companyBean2.getCompany_id());
		categoryBean10.setCompanyBean(companyBean2);
		categoryService.save(categoryBean5);
		categoryService.save(categoryBean6);
		categoryService.save(categoryBean7);
		categoryService.save(categoryBean8);
		categoryService.save(categoryBean9);
		categoryService.save(categoryBean10);
		
		CategoryBean categoryBean11 = new CategoryBean(null, "經典飲品", companyBean3.getCompany_id());
		categoryBean11.setCompanyBean(companyBean3);
		CategoryBean categoryBean12 = new CategoryBean(null, "限定飲品", companyBean3.getCompany_id());
		categoryBean12.setCompanyBean(companyBean3);
		categoryService.save(categoryBean11);
		categoryService.save(categoryBean12);
	
		CategoryBean categoryBean13 = new CategoryBean(null, "嚼對推薦", companyBean4.getCompany_id());
		categoryBean13.setCompanyBean(companyBean4);
		CategoryBean categoryBean14 = new CategoryBean(null, "愛茶的牛", companyBean4.getCompany_id());
		categoryBean14.setCompanyBean(companyBean4);
		CategoryBean categoryBean15 = new CategoryBean(null, "牧場鮮奶茶", companyBean4.getCompany_id());
		categoryBean15.setCompanyBean(companyBean4);
		CategoryBean categoryBean16 = new CategoryBean(null, "綠光牧場鮮奶", companyBean4.getCompany_id());
		categoryBean16.setCompanyBean(companyBean4);
		CategoryBean categoryBean17 = new CategoryBean(null, "手作特調", companyBean4.getCompany_id());
		categoryBean17.setCompanyBean(companyBean4);
		categoryService.save(categoryBean13);
		categoryService.save(categoryBean14);
		categoryService.save(categoryBean15);
		categoryService.save(categoryBean16);
		categoryService.save(categoryBean17);
		
		CategoryBean categoryBean18 = new CategoryBean(null, "特調系列", companyBean5.getCompany_id());
		categoryBean18.setCompanyBean(companyBean5);
		CategoryBean categoryBean19 = new CategoryBean(null, "鮮奶 / 拿鐵系列", companyBean5.getCompany_id());
		categoryBean19.setCompanyBean(companyBean5);
		CategoryBean categoryBean20 = new CategoryBean(null, "優多系列", companyBean5.getCompany_id());
		categoryBean20.setCompanyBean(companyBean5);
		CategoryBean categoryBean21 = new CategoryBean(null, "季節鮮果系列", companyBean5.getCompany_id());
		categoryBean21.setCompanyBean(companyBean5);
		CategoryBean categoryBean22 = new CategoryBean(null, "冰淇淋系列", companyBean5.getCompany_id());
		categoryBean22.setCompanyBean(companyBean5);
		CategoryBean categoryBean23 = new CategoryBean(null, "冬瓜系列", companyBean5.getCompany_id());
		categoryBean23.setCompanyBean(companyBean5);
		CategoryBean categoryBean24 = new CategoryBean(null, "茗品系列", companyBean5.getCompany_id());
		categoryBean24.setCompanyBean(companyBean5);
		CategoryBean categoryBean25 = new CategoryBean(null, "果醋系列", companyBean5.getCompany_id());
		categoryBean25.setCompanyBean(companyBean5);
		categoryService.save(categoryBean18);
		categoryService.save(categoryBean19);
		categoryService.save(categoryBean20);
		categoryService.save(categoryBean21);
		categoryService.save(categoryBean22);
		categoryService.save(categoryBean23);
		categoryService.save(categoryBean24);
		categoryService.save(categoryBean25);
		
		CategoryBean categoryBean26 = new CategoryBean(null, "醇香茶品", companyBean6.getCompany_id());
		categoryBean26.setCompanyBean(companyBean6);
		CategoryBean categoryBean27 = new CategoryBean(null, "冰沙系列", companyBean6.getCompany_id());
		categoryBean27.setCompanyBean(companyBean6);
		CategoryBean categoryBean28 = new CategoryBean(null, "鮮調果茶", companyBean6.getCompany_id());
		categoryBean28.setCompanyBean(companyBean6);
		CategoryBean categoryBean29 = new CategoryBean(null, "香濃奶茶", companyBean6.getCompany_id());
		categoryBean29.setCompanyBean(companyBean6);
		CategoryBean categoryBean30 = new CategoryBean(null, "濃醇鮮乳", companyBean6.getCompany_id());
		categoryBean30.setCompanyBean(companyBean6);
		CategoryBean categoryBean31 = new CategoryBean(null, "嚴選咖啡", companyBean6.getCompany_id());
		categoryBean31.setCompanyBean(companyBean6);
		categoryService.save(categoryBean26);
		categoryService.save(categoryBean27);
		categoryService.save(categoryBean28);
		categoryService.save(categoryBean29);
		categoryService.save(categoryBean30);
		categoryService.save(categoryBean31);
		
		CategoryBean categoryBean32 = new CategoryBean(null, "原葉鮮萃茶", companyBean7.getCompany_id());
		categoryBean32.setCompanyBean(companyBean7);
		CategoryBean categoryBean33 = new CategoryBean(null, "鮮萃茶拿鐵", companyBean7.getCompany_id());
		categoryBean33.setCompanyBean(companyBean7);
		CategoryBean categoryBean34 = new CategoryBean(null, "門市限定", companyBean7.getCompany_id());
		categoryBean34.setCompanyBean(companyBean7);
		CategoryBean categoryBean35 = new CategoryBean(null, "鮮調果茶", companyBean7.getCompany_id());
		categoryBean35.setCompanyBean(companyBean7);
		CategoryBean categoryBean36 = new CategoryBean(null, "果然系列", companyBean7.getCompany_id());
		categoryBean36.setCompanyBean(companyBean7);
		CategoryBean categoryBean37 = new CategoryBean(null, "夏季限定", companyBean7.getCompany_id());
		categoryBean37.setCompanyBean(companyBean7);
		CategoryBean categoryBean38 = new CategoryBean(null, "鮮奶 / 特調", companyBean7.getCompany_id());
		categoryBean38.setCompanyBean(companyBean7);
		CategoryBean categoryBean39 = new CategoryBean(null, "冬季限定", companyBean7.getCompany_id());
		categoryBean39.setCompanyBean(companyBean7);
		categoryService.save(categoryBean32);
		categoryService.save(categoryBean33);
		categoryService.save(categoryBean34);
		categoryService.save(categoryBean35);
		categoryService.save(categoryBean36);
		categoryService.save(categoryBean37);
		categoryService.save(categoryBean38);
		categoryService.save(categoryBean39);
	}
}
