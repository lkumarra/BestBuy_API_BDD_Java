package demo.bestbuy.com.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbuy.com.dbhelpers.DBHelpers;

/**
 * This class contains the DB methods related to /products API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class ProductDBHelper {

	static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Products/";

	/**
	 * Fetch the product list fromn DB.
	 * 
	 * @return List of {@link GetProductDatum}
	 * @author Lavendra rajput
	 */
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

	/**
	 * Fetch the product category from DB on the basis of productId
	 * 
	 * @param productId : Product Id to fectch product category.
	 * @return List of {@link GetProductCategory}
	 * @author Lavendra rajput
	 */
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

	/**
	 * Fetch the total product count from DB.
	 * 
	 * @return Total count of product
	 * @author Lavendra rajput
	 */
	public static Integer getTotalProducts() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalProductCount.sql");
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script);
		return hashMap.get("id").size();
	}

	/**
	 * Get the product from DB via product Id.
	 * 
	 * @param id : Product Id to get product
	 * @return {@link GetProductDatum}
	 * @author Lavendra rajput
	 */
	public static GetProductDatum getProductViaId(int id) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetProductViaId.sql");
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script, param);
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
		return datumList.get(0);
	}
}
