package demo.bestbuy.com.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbuy.com.dbhelpers.DBHelpers;

/**
 * This class contains the DB Methods of /categories API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class CategoriesDBHelper {

	static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Categories/";

	/**
	 * Fetch the categories list from DB.
	 * 
	 * @return List of {@link GetCategoryDatum}
	 * @author Lavendra rajput
	 */
	public static List<GetCategoryDatum> getCategoriesList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategories.sql");
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScript(script);
		List<GetCategoryDatum> datumList = new ArrayList<GetCategoryDatum>();
		for (int i = 0; i < hashMap.get("id").size(); i++) {
			GetCategoryDatum datum = new GetCategoryDatum();
			datum.setId((String) hashMap.get("id").get(i));
			datum.setCreatedAt(
					(String) hashMap.get("createdAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			datum.setUpdatedAt(
					(String) hashMap.get("updatedAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			datum.setName((String) hashMap.get("name").get(i));
			datum.setSubCategories(getSubcategoriesList((String) hashMap.get("id").get(i)));
			datum.setCategoryPath(getCategoryPathList((String) hashMap.get("id").get(i)));
			datumList.add(datum);
		}
		return datumList;
	}

	/**
	 * Fetch the subcategory on the basis of category Id.
	 * 
	 * @param categoryId : Category id to fetch subcategory
	 * @return List of {@link GetCategorySubCategory}
	 * @author Lavendra rajput
	 */
	private static List<GetCategorySubCategory> getSubcategoriesList(String categoryId) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetSubCategories.sql");
		List<String> param = new ArrayList<>();
		param.add(categoryId);
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScriptWithStringParam(script, param);
		List<GetCategorySubCategory> subCategoryList = new ArrayList<GetCategorySubCategory>();
		for (int i = 0; i < hashMap.get("id").size(); i++) {
			GetCategorySubCategory subCategory = new GetCategorySubCategory();
			subCategory.setId((String) hashMap.get("id").get(i));
			subCategory.setName((String) hashMap.get("name").get(i));
			subCategory.setCreatedAt(
					(String) hashMap.get("createdAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			subCategory.setUpdatedAt(
					(String) hashMap.get("updatedAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			subCategoryList.add(subCategory);
		}
		return subCategoryList;
	}

	/**
	 * Fetch the category path on the basis of category Id.
	 * 
	 * @param categoryId : category id to fetch Category path.
	 * @return List of {@link GetCategoryCategoryPath}
	 * @author Lavendra rajput
	 */
	private static List<GetCategoryCategoryPath> getCategoryPathList(String categoryId) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoryPath.sql");
		List<String> param = new ArrayList<String>();
		param.add(categoryId);
		HashMap<String, List<Object>> hashMap = DBHelpers.executeScriptWithStringParam(script, param);
		List<GetCategoryCategoryPath> categoryPathList = new ArrayList<GetCategoryCategoryPath>();
		for (int i = 0; i < hashMap.get("id").size(); i++) {
			GetCategoryCategoryPath categoryPath = new GetCategoryCategoryPath();
			categoryPath.setId((String) hashMap.get("id").get(i));
			categoryPath.setName((String) hashMap.get("name").get(i));
			categoryPath.setCreatedAt(
					(String) hashMap.get("createdAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			categoryPath.setUpdatedAt(
					(String) hashMap.get("updatedAt").get(i).toString().replace(" +00:00", "Z").replace(" ", "T"));
			categoryPathList.add(categoryPath);
		}
		return categoryPathList;
	}

	/**
	 * Fetch the total count of category in Db.
	 * 
	 * @return : Total count of category in Db.
	 * @author Lavendra rajput
	 */
	public static int getTotalCategoryCount() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalCategories.sql");
		return DBHelpers.executeScript(script).get("total").size();
	}
}
