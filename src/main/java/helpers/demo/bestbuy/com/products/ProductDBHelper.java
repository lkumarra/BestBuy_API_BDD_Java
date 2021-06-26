package demo.bestbuy.com.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbuy.com.dbhelpers.DBHelpers;

public class ProductDBHelper {

	static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Products/";

	public static List<GetProductDatum> getProductsList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetProduct.sql");
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script);
		List<GetProductDatum> datumList = new ArrayList<>();
		for (int i = 0; i < hashMap.get("id").size(); i++) {
			GetProductDatum datum = new GetProductDatum();
			datum.setId((Integer) hashMap.get("id").get(i));
			datum.setName((String) hashMap.get("name").get(i));
			datum.setType((String) hashMap.get("type").get(i));
			datum.setPrice((Double) hashMap.get("price").get(i));
			datum.setUpc((String) hashMap.get("upc").get(i));
			datum.setShipping((Integer) hashMap.get("shipping").get(i));
			datum.setDescription((String) hashMap.get("description").get(i));
			datum.setManufacturer((String) hashMap.get("manufacturer").get(i));
			datum.setModel((String) hashMap.get("model").get(i));
			datum.setUrl((String) hashMap.get("url").get(i));
			datum.setImage((String) hashMap.get("image").get(i));
			datum.setCreatedAt(
					(String) hashMap.get("createdAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			datum.setUpdatedAt(
					(String) hashMap.get("updatedAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			datum.setCategories(getFilterdCategories((Integer) hashMap.get("id").get(i)));
			datumList.add(datum);
		}
		return datumList;
	}

	public static List<GetProductCategory> getFilterdCategories(int productId) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoriesFilterd.sql");
		List<Integer> list = new ArrayList<Integer>();
		list.add(productId);
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script, list);
		List<GetProductCategory> categoryList = new ArrayList<>();
		for (int i = 0; i < hashMap.get("id").size(); i++) {
			GetProductCategory category = new GetProductCategory();
			category.setId((String) hashMap.get("id").get(i));
			category.setName((String) hashMap.get("name").get(i));
			category.setCreatedAt(
					(String) hashMap.get("createdAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			category.setUpdatedAt(
					(String) hashMap.get("updatedAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			categoryList.add(category);
		}
		return categoryList;
	}

	public static Integer getTotalProducts() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalProductCount.sql");
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script);
		return hashMap.get("id").size();
	}
}
