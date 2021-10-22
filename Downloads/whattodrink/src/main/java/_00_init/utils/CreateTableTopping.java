package _00_init.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;

public class CreateTableTopping {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		CompanyService companyService = new CompanyServiceImpl();
		DrinkService drinkService = new DrinkServiceImpl();
		ToppingBean toppingBean = new ToppingBean();

		String line = "";
		try (FileReader fr = new FileReader("data/topping.txt"); BufferedReader br = new BufferedReader(fr);) {
			while ((line = br.readLine()) != null) {
				String[] sa = line.split("\\|");
				toppingBean.setTopping_id(null);
				toppingBean.setCompany_id(sa[0]);
				toppingBean.setTopping_cal(Integer.parseInt(sa[1]));
				toppingBean.setTopping_name(sa[2]);
				toppingBean.setTopping_picname(sa[3]);
				toppingBean.setTopping_picpath(sa[4]);
				toppingBean.setTopping_price(Integer.parseInt(sa[5]));
				toppingBean.setCompanyBean(companyService.findById(sa[0]));
				drinkService.saveToppingBean(toppingBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}