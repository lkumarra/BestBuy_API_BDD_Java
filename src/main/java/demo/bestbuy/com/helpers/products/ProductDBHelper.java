package demo.bestbuy.com.helpers.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbuy.com.data.dbKeys.DBkeys.CategoriesTableKeys;
import demo.bestbuy.com.data.dbKeys.DBkeys.ProductTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.products.ProductModal.GetProductCategory;
import demo.bestbuy.com.modals.products.ProductModal.GetProductDatum;


/**
 * This class contains the DB methods related to /products API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public final class ProductDBHelper {

	private static final String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Products/";

	/**
	 * Fetch the product list fromn DB.
	 * 
	 * @return List of {@link GetProductDatum}
	 * @author Lavendra rajput
	 */
	public static List<GetProductDatum> getProductsList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetProduct.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		List<GetProductDatum> productList = new ArrayList<GetProductDatum>();
		dataTable.forEach(x -> {
			GetProductDatum product = new GetProductDatum();
			product.setId(Integer.parseInt(x.get(ProductTableKeys.ID)));
			product.setName(x.get(ProductTableKeys.NAME));
			product.setType(x.get(ProductTableKeys.TYPE));
			product.setPrice(Double.parseDouble(x.get(ProductTableKeys.PRICE)));
			product.setUpc(x.get(ProductTableKeys.UPC));
			product.setShipping(Double.parseDouble(x.get(ProductTableKeys.SHIPPING)));
			product.setDescription(x.get(ProductTableKeys.DESCRIPTION));
			product.setManufacturer(x.get(ProductTableKeys.MANUFACTURER));
			product.setModel(x.get(ProductTableKeys.MODEL));
			product.setUrl(x.get(ProductTableKeys.URL));
			product.setImage(x.get(ProductTableKeys.IMAGE));
			product.setCreatedAt(new StringHelper(x.get(ProductTableKeys.CREATEDAT)).getModifiedDateString());
			product.setUpdatedAt(new StringHelper(x.get(ProductTableKeys.UPDATEDAT)).getModifiedDateString());
			product.setCategories(getFilterdCategories(Integer.parseInt(x.get(ProductTableKeys.ID))));
			productList.add(product);
		});
		return productList;
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, list);
		List<GetProductCategory> productCategoriesList = new ArrayList<GetProductCategory>();
		dataTable.forEach(x -> {
			GetProductCategory productCategory = new GetProductCategory();
			productCategory.setId(x.get(CategoriesTableKeys.ID));
			productCategory.setName(x.get(CategoriesTableKeys.NAME));
			productCategory.setCreatedAt(new StringHelper(x.get(ProductTableKeys.CREATEDAT)).getModifiedDateString());
			productCategory.setUpdatedAt(new StringHelper(x.get(ProductTableKeys.UPDATEDAT)).getModifiedDateString());
			productCategoriesList.add(productCategory);
		});
		return productCategoriesList;
	}

	/**
	 * Fetch the total product count from DB.
	 * 
	 * @return Total count of product
	 * @author Lavendra rajput
	 */
	public static Integer getTotalProducts() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalProductCount.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		return dataTable.size();
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, param);
		List<GetProductDatum> productList = new ArrayList<GetProductDatum>();
		dataTable.forEach(x -> {
			GetProductDatum product = new GetProductDatum();
			product.setId(Integer.parseInt(x.get(ProductTableKeys.ID)));
			product.setName(x.get(ProductTableKeys.NAME));
			product.setType(x.get(ProductTableKeys.TYPE));
			product.setPrice(Double.parseDouble(x.get(ProductTableKeys.PRICE)));
			product.setUpc(x.get(ProductTableKeys.UPC));
			product.setShipping(Double.parseDouble(x.get(ProductTableKeys.SHIPPING)));
			product.setDescription(x.get(ProductTableKeys.DESCRIPTION));
			product.setManufacturer(x.get(ProductTableKeys.MANUFACTURER));
			product.setModel(x.get(ProductTableKeys.MODEL));
			product.setUrl(x.get(ProductTableKeys.URL));
			product.setImage(x.get(ProductTableKeys.IMAGE));
			product.setCreatedAt(new StringHelper(x.get(ProductTableKeys.CREATEDAT)).getModifiedDateString());
			product.setUpdatedAt(new StringHelper(x.get(ProductTableKeys.UPDATEDAT)).getModifiedDateString());
			product.setCategories(getFilterdCategories(Integer.parseInt(x.get(ProductTableKeys.ID))));
			productList.add(product);
		});
		return productList.get(0);
	}
	
	public static void deleteAddedProduct() {
		String script = DBHelpers.getDBScript(scriptPath+"Script.DeleteAddedProduct.sql");
		DBHelpers.executeNonQuery(script);
	}
	
}
