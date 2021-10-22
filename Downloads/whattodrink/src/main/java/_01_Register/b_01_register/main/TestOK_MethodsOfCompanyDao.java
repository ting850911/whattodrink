package _01_Register.b_01_register.main;

import java.util.List;

import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;


public class TestOK_MethodsOfCompanyDao {
	public static void main(String[] args) {

//updateCompany()
		CompanyService companyService = new CompanyServiceImpl();
		
//		CompanyBean cb = new CompanyBean();
//		cb = companyService.findByCompanyAccount("dog1111");
//		cb.setCompany_owner("我原本叫黃小新，被改成黃大新了");
//		companyService.updateCompany(cb);
		
//		System.out.println(companyService.findById(cb.getCompany_id()));
		
		
		
//existsByAccount()
//		System.out.println(companyService.existsByAccount("kitty1111"));
		
		
//findByCompanyAccountAndPassword()  => 例子為密碼錯誤，改0000
//		System.out.println(companyService.findByCompanyAccountAndPassword("kitty1111", "0001"));
		
		
//findByCompanyAccount()
//		System.out.println(companyService.findByCompanyAccount("account_1"));
				
//findById()
//		CompanyBean companyBean = companyService.findById(1);
//		System.out.println(companyBean);
		
//findAll()
//		List<CompanyBean> list = companyService.findAll();
//		for(CompanyBean company : list) {
//			System.out.println(company);
//		}
		
	}

}
