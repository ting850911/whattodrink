package _00_init.utils;

import java.io.BufferedReader;
import java.io.FileReader;
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

public class CreateTableDrink {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		DrinkService drinkService = new DrinkServiceImpl();

		CompanyBean companyBean = new CompanyBean();
		CategoryBean categoryBean = new CategoryBean();
		DrinkBean drinkBean = new DrinkBean();
		Set<TagBean> tagBean = new HashSet<>();
		List<TagBean> list = new ArrayList<>();
		int count = 0;

		String line = "";
		try (FileReader fr = new FileReader("data/drink.txt"); BufferedReader br = new BufferedReader(fr);) {
			while ((line = br.readLine()) != null) {
				count++;
				String[] sa = line.split("\\|");
				drinkBean.setProduct_name(sa[0]);
				drinkBean.setProduct_price(Integer.parseInt(sa[1]));
				drinkBean.setCapacity(sa[2]);
				drinkBean.setProduct_cal(Integer.parseInt(sa[3]));
				drinkBean.setCompany_id(sa[4].trim());
				drinkBean.setCategory_id(Integer.parseInt(sa[5]));
				drinkBean.setProduct_picname(sa[6]);
				drinkBean.setProduct_picpath(sa[7]);
				drinkBean.setCompanyBean(companyBean);
				drinkBean.setCategory(categoryBean);
				drinkBean.setAdd_date(new Timestamp(System.currentTimeMillis()));
				drinkBean.setAlter_date(new Timestamp(System.currentTimeMillis()));
				companyBean.setCompany_id(sa[4]);
				categoryBean.setCategory_id(Integer.parseInt(sa[5]));
				for (int i = 8; i < sa.length; i++) {
					TagBean tb = new TagBean(null, count, sa[i].trim());
					tagBean.add(tb);
				}
				drinkBean.setTags(tagBean);
				drinkService.save(drinkBean);

			}

			for (int i = 1; i <= count; i++) {
				list = drinkService.findByProductId(i);
				if (list != null) {
					for (TagBean tb : list) {
						drinkService.updateTagProductFK(i, drinkService.findById(i));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到對應的路徑檔案");
		}
	}
}