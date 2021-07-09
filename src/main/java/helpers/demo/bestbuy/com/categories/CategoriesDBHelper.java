package demo.bestbuy.com.categories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.dbhelpers.DBHelpers;

/**
 * This class contains the DB Methods of /categories API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class CategoriesDBHelper {

	private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Categories/";

	/**
	 * Fetch the categories list from DB.
	 * 
	 * @return List of {@link GetCategoryDatum}
	 * @author Lavendra rajput
	 */
	public static List<GetCategoryDatum> getCategoriesList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategories.sql");
		String json = DBHelpers.executeScript(script);
		GetCategoryDatum[] getCategoryDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetCategoryDatum[].class);
		List<GetCategoryDatum> datumList = Arrays.asList(getCategoryDatums);
		datumList.forEach(x -> {
			x.setSubCategories(getSubcategoriesList(x.getId()));
			x.setCategoryPath(getCategoryPathList(x.getId()));
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
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
		String json = DBHelpers.executeScriptWithStringParam(script, param);
		GetCategorySubCategory[] getCSubCategories = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(json, GetCategorySubCategory[].class);
		List<GetCategorySubCategory> getCategorySubCategories = Arrays.asList(getCSubCategories);
		getCategorySubCategories.forEach(x -> {
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return getCategorySubCategories;
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
		String json = DBHelpers.executeScriptWithStringParam(script, param);
		GetCategoryCategoryPath[] getCSubCategories = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(json, GetCategoryCategoryPath[].class);
		List<GetCategoryCategoryPath> getCategoryCategoryPaths = Arrays.asList(getCSubCategories);
		getCategoryCategoryPaths.forEach(x -> {
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return getCategoryCategoryPaths;
	}

	/**
	 * Fetch the total count of category in Db.
	 * 
	 * @return : Total count of category in Db.
	 * @author Lavendra rajput
	 */
	public static int getTotalCategoryCount() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalCategories.sql");
		String json = DBHelpers.executeScript(script);
		GetCategoryDatum[] getCategoryDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetCategoryDatum[].class);
		return getCategoryDatums.length;
	}

	/**
	 * Get categories via Id
	 * 
	 * @param categoryId : Id to get categories
	 * @return {@link GetCategoryDatum}
	 */
	public static GetCategoryDatum getCategoriesViaId(String categoryId) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoriesViaId.sql");
		List<String> param = new ArrayList<String>();
		param.add(categoryId);
		String json = DBHelpers.executeScriptWithStringParam(script,param);
		GetCategoryDatum[] getCategoryDatums = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json,
				GetCategoryDatum[].class);
		List<GetCategoryDatum> datumList = Arrays.asList(getCategoryDatums);
		datumList.forEach(x -> {
			x.setSubCategories(getSubcategoriesList(x.getId()));
			x.setCategoryPath(getCategoryPathList(x.getId()));
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return datumList.get(0);
	}
}
