package _03_ListDrinks.controller;

import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.gson.Gson;

import _01_Register.b_01_register.model.CompanyBean;
import _01_Register.b_01_register.service.CompanyService;
import _01_Register.b_01_register.service.serviceImpl.CompanyServiceImpl;
import _03_ListDrinks.model.DrinkBean;
import _03_ListDrinks.model.ToppingBean;
import _03_ListDrinks.service.DrinkService;
import _03_ListDrinks.service.serviceImpl.DrinkServiceImpl;
import _07_Others.model.SugarLimitBean;
import _07_Others.model.TempLimitBean;

@MultipartConfig
@WebServlet("/_03_ListDrinks/DrinkDetail")
public class DrinkDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DrinkDetail.class);
	CompanyService companyService = new CompanyServiceImpl();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("飲料細節視窗之Controller: 開始");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Map<String, JSONArray> map = new HashMap<>();
		DrinkService drinkService = new DrinkServiceImpl();

		// 使用飲料ID取得飲料detail
		int productId = Integer.parseInt(request.getParameter("productId"));
		DrinkBean drinkBean = drinkService.findDrinkBeanByProductId(productId);
		Set<ToppingBean> toppingBean = drinkBean.getCompanyBean().getToppings();
		List<TempLimitBean> tempLimits = drinkService.findTempLimitsByProductId(productId);
		List<SugarLimitBean> sugarLimits = drinkService.findSugarLimitsByProductId(productId);
				
		
		JSONArray toppingNameJson = new JSONArray();
		JSONArray toppingIdJson = new JSONArray();
		JSONArray toppingPriceJson = new JSONArray();
		for(ToppingBean tmp : toppingBean) {
			if(tmp.getEnabled() == true) {
				toppingNameJson.add(tmp.getTopping_name());
				toppingIdJson.add(tmp.getTopping_id());
				toppingPriceJson.add(tmp.getTopping_price());
			}
		}
		
		JSONArray drinkNameAndCapacity = new JSONArray();
		drinkNameAndCapacity.add(drinkBean.getProduct_name());
		drinkNameAndCapacity.add(drinkBean.getCapacity());
		
		
		JSONArray tempIdJson = new JSONArray();
		JSONArray tempNameJson = new JSONArray();
		for(TempLimitBean tmp : tempLimits) {
			tempIdJson.add(tmp.getTempLevelBean().getTemp_id());
			tempNameJson.add(tmp.getTempLevelBean().getTemp_level());
		}
		JSONArray sugarIdJson = new JSONArray();
		JSONArray sugarNameJson = new JSONArray();
		for(SugarLimitBean sug : sugarLimits) {
			sugarIdJson.add(sug.getSugarLevelBean().getSugar_id());
			sugarNameJson.add(sug.getSugarLevelBean().getSugar_level());
		}
		JSONArray toppingJson = new JSONArray();
		toppingJson.add(toppingBean);
		
		map.put("drinkNameAndCapacity", drinkNameAndCapacity);
		map.put("tempIdJson", tempIdJson);
		map.put("tempNameJson", tempNameJson);
		map.put("sugarIdJson", sugarIdJson);
		map.put("sugarNameJson", sugarNameJson);
		map.put("toppingNameJson", toppingNameJson);
		map.put("toppingIdJson", toppingIdJson);
		map.put("toppingPriceJson", toppingPriceJson);
		
		response.getWriter().println(JSON.toJSONString(map));
		
		 
		
//		 String dirString = getServletContext().getRealPath("/");
//		 String dirString = "src/main/Webapp/images";
//		 System.out.println(dirString);
//		 
//		 Part imageFile = request.getPart("imageFile");
//		 String fileName = imageFile.getSubmittedFileName();
//		 
//		 File dir = new File(dirString + "/A01");
//		 if(!dir.exists()) {
//			 dir.mkdirs();
//		 }
//		 File file = new File(dir, fileName);
//		 
//		try (
////			FileInputStream fis = new FileInputStream("C:\\test.png");
//			InputStream is = imageFile.getInputStream();
//			FileOutputStream fos = new FileOutputStream(file);
//		){
//			BufferedInputStream bis = new BufferedInputStream(is);
//			BufferedOutputStream bos = new BufferedOutputStream(fos);
//			byte[] buf = new byte[81920];
//			int len = 0;
//			while ((len = bis.read(buf)) != -1) {
//				bos.write(buf, 0, len);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		

	}
}

