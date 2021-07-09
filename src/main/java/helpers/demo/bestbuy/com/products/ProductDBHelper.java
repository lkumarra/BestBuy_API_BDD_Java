package demo.bestbuy.com.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.dbhelpers.DBHelpers;

/**
 * This class contains the DB methods related to /products API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class ProductDBHelper {

	private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Products/";

	/**
	 * Fetch the product list fromn DB.
	 * 
	 * @return List of {@link GetProductDatum}
	 * @author Lavendra rajput
	 */
	public static List<GetProductDatum> getProductsList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetProduct.sql");
		String json = DBHelpers.executeScript(script);
		GetProductDatum[] getProductDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetProductDatum[].class);
		List<GetProductDatum> getPDatums = Arrays.asList(getProductDatums);
		getPDatums.forEach(x -> {
			x.setCategories(getFilterdCategories(x.getId()));
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return getPDatums;
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
		String json = DBHelpers.executeScript(script, list);
		GetProductCategory[] getProductCategories = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(json, GetProductCategory[].class);
		List<GetProductCategory> categoryList = Arrays.asList(getProductCategories);
		categoryList.forEach(x -> {
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
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
		String json = DBHelpers.executeScript(script);
		GetProductDatum[] getProductDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetProductDatum[].class);
		return getProductDatums.length;
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
		String json = DBHelpers.executeScript(script,param);
		GetProductDatum[] getProductDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetProductDatum[].class);
		List<GetProductDatum> getPDatums = Arrays.asList(getProductDatums);
		getPDatums.forEach(x -> {
			x.setCategories(getFilterdCategories(x.getId()));
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return getPDatums.get(0);
	}
}
