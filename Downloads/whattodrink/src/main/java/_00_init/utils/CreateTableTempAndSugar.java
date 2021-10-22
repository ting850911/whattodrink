package _00_init.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _01_Register.b_01_register.model.CompanyBean;
import _03_ListDrinks.model.CategoryBean;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.TagBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.SugarLevelBean;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLevelBean;
import _07_Others.model.TempLimitBean;

public class CreateTableTempAndSugar {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		DrinkService drinkService = new DrinkServiceImpl();		
//		TempLevelBean levelBean = new TempLevelBean();
//		for(int i = 1; i <= 6; i++) {
//			levelBean.setTemp_id(i);
//			switch (i) {
//			case 1:
//				levelBean.setTemp_level("正常冰");
//				break;
//			case 2:
//				levelBean.setTemp_level("少冰");
//				break;
//			case 3:
//				levelBean.setTemp_level("微冰");
//				break;
//			case 4:
//				levelBean.setTemp_level("去冰");
//				break;
//			case 5:
//				levelBean.setTemp_level("溫");
//				break;
//			case 6:
//				levelBean.setTemp_level("熱");
//				break;
//			}
//			drinkService.saveTempLevelBean(levelBean);
//		}
//		
//		
//		SugarLevelBean sugarLevelBean = new SugarLevelBean();
//		for(int i = 1; i <= 5; i++) {
//			sugarLevelBean.setSugar_id(i);
//			switch (i) {
//			case 1:
//				sugarLevelBean.setFactor(BigDecimal.valueOf(0.3));
//				sugarLevelBean.setSugar_level("無糖");
//				break;
//			case 2:
//				sugarLevelBean.setFactor(BigDecimal.valueOf(0.5));
//				sugarLevelBean.setSugar_level("微糖");
//				break;
//			case 3:
//				sugarLevelBean.setFactor(BigDecimal.valueOf(0.7));
//				sugarLevelBean.setSugar_level("半糖");
//				break;
//			case 4:
//				sugarLevelBean.setFactor(BigDecimal.valueOf(0.9));
//				sugarLevelBean.setSugar_level("少糖");
//				break;
//			case 5:
//				sugarLevelBean.setFactor(BigDecimal.valueOf(1));
//				sugarLevelBean.setSugar_level("全糖");
//				break;
//			}
//			drinkService.saveSugarLevelBean(sugarLevelBean);
//		}
		
		
//		TempLimitBean limitBean = new TempLimitBean();
//		SugarLimitBean sugarLimitBean = new SugarLimitBean();
//		
//		int count = 0;
//		String line = "";
//		try (FileReader fr = new FileReader("data/TempAndSugarNoCheck.txt"); BufferedReader br = new BufferedReader(fr);) {
//			while ((line = br.readLine()) != null) {
//				count++;
//				String[] sa = line.split("\\|");
//				if(sa[0].trim().equals("B")) {
//					for(int i = 1; i <= 6; i++) {
//						limitBean.setTl_sql(null);
//						limitBean.setProduct_id(count);
//						limitBean.setTemp_id(i);
//						limitBean.setDrinkBean(drinkService.findById(count));
//						limitBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(i));
//						drinkService.saveTempLimitBean(limitBean);
//					}					
//				}else if(sa[0].trim().equals("C")){
//					for(int i = 1; i <= 4; i++) {
//						limitBean.setTl_sql(null);
//						limitBean.setProduct_id(count);
//						limitBean.setTemp_id(i);
//						limitBean.setDrinkBean(drinkService.findById(count));
//						limitBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(i));
//						drinkService.saveTempLimitBean(limitBean);
//					}	
//				}else if(sa[0].trim().equals("H")){
//					for(int i = 5; i <= 6; i++) {
//						limitBean.setTl_sql(null);
//						limitBean.setProduct_id(count);
//						limitBean.setTemp_id(i);
//						limitBean.setDrinkBean(drinkService.findById(count));
//						limitBean.setTempLevelBean(drinkService.findTempLevelBeanByTempId(i));
//						drinkService.saveTempLimitBean(limitBean);
//					}	
//				}
//				
//				if(sa[1].trim().equals("全糖")) {
//					for(int i = 1; i <= 5; i++) {
//						sugarLimitBean.setSl_sql(null);
//						sugarLimitBean.setProduct_id(count);
//						sugarLimitBean.setSugar_id(i);
//						sugarLimitBean.setDrinkBean(drinkService.findById(count));
//						sugarLimitBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(i));
//						drinkService.saveSugarLimitBean(sugarLimitBean);
//					}	
//				}else if(sa[1].trim().equals("少糖")){
//					for(int i = 1; i <= 4; i++) {
//						sugarLimitBean.setSl_sql(null);
//						sugarLimitBean.setProduct_id(count);
//						sugarLimitBean.setSugar_id(i);
//						sugarLimitBean.setDrinkBean(drinkService.findById(count));
//						sugarLimitBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(i));
//						drinkService.saveSugarLimitBean(sugarLimitBean);
//					}	
//				}else if(sa[1].trim().equals("半糖")){
//					for(int i = 1; i <= 3; i++) {
//						sugarLimitBean.setSl_sql(null);
//						sugarLimitBean.setProduct_id(count);
//						sugarLimitBean.setSugar_id(i);
//						sugarLimitBean.setDrinkBean(drinkService.findById(count));
//						sugarLimitBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(i));
//						drinkService.saveSugarLimitBean(sugarLimitBean);
//					}	
//				}else if(sa[1].trim().equals("微糖")){
//					for(int i = 1; i <= 2; i++) {
//						sugarLimitBean.setSl_sql(null);
//						sugarLimitBean.setProduct_id(count);
//						sugarLimitBean.setSugar_id(i);
//						sugarLimitBean.setDrinkBean(drinkService.findById(count));
//						sugarLimitBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(i));
//						drinkService.saveSugarLimitBean(sugarLimitBean);
//					}	
//				}else if(sa[1].trim().equals("無糖")){
//					for(int i = 1; i <= 1; i++) {
//						sugarLimitBean.setSl_sql(null);
//						sugarLimitBean.setProduct_id(count);
//						sugarLimitBean.setSugar_id(i);
//						sugarLimitBean.setDrinkBean(drinkService.findById(count));
//						sugarLimitBean.setSugarLevelBean(drinkService.findSugarLevelBeanBySugarId(i));
//						drinkService.saveSugarLimitBean(sugarLimitBean);
//					}	
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		System.out.println(drinkService.findById(1).getSugarLimits());
		
	}
}