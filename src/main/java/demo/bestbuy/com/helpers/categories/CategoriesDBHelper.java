package demo.bestbuy.com.helpers.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbuy.com.data.dbKeys.DBkeys.CategoriesTableKeys;
import demo.bestbuy.com.data.dbKeys.DBkeys.CategoryPathTableKeys;
import demo.bestbuy.com.data.dbKeys.DBkeys.SubCategoriesTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryCategoryPath;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryDatum;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategorySubCategory;


/**
 * This class contains the DB Methods of /categories API.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public final class CategoriesDBHelper {

	private static final String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Categories/";

	/**
	 * Fetch the categories list from DB.
	 * 
	 * @return List of {@link GetCategoryDatum}
	 * @author Lavendra rajput
	 */
	public static List<GetCategoryDatum> getCategoriesList() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategories.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		List<GetCategoryDatum> categoriesList = new ArrayList<GetCategoryDatum>();
		dataTable.forEach(x -> {
			GetCategoryDatum categories = new GetCategoryDatum();
			categories.setId(x.get(CategoriesTableKeys.ID));
			categories.setName(x.get(CategoriesTableKeys.NAME));
			categories.setCreatedAt(new StringHelper(x.get(CategoriesTableKeys.CREATEDAT)).getModifiedDateString());
			categories.setUpdatedAt(new StringHelper(x.get(CategoriesTableKeys.UPDATEDAT)).getModifiedDateString());
			categories.setSubCategories(getSubcategoriesList(x.get(CategoriesTableKeys.ID)));
			categories.setCategoryPath(getCategoryPathList(x.get(CategoriesTableKeys.ID)));
			categoriesList.add(categories);
		});
		return categoriesList;
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, param);
		List<GetCategorySubCategory> subCategoryList = new ArrayList<GetCategorySubCategory>();
		dataTable.forEach(x -> {
			GetCategorySubCategory subCategory = new GetCategorySubCategory();
			subCategory.setId(x.get(SubCategoriesTableKeys.ID));
			subCategory.setName(x.get(SubCategoriesTableKeys.NAME));
			subCategory.setCreatedAt(new StringHelper(x.get(CategoriesTableKeys.CREATEDAT)).getModifiedDateString());
			subCategory.setUpdatedAt(new StringHelper(x.get(CategoriesTableKeys.UPDATEDAT)).getModifiedDateString());
			subCategoryList.add(subCategory);
		});
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, param);
		List<GetCategoryCategoryPath> categoryPathList = new ArrayList<GetCategoryCategoryPath>();
		dataTable.forEach(x -> {
			GetCategoryCategoryPath categoryPath = new GetCategoryCategoryPath();
			categoryPath.setId(x.get(CategoryPathTableKeys.ID));
			categoryPath.setName(x.get(CategoryPathTableKeys.NAME));
			categoryPath.setCreatedAt(new StringHelper(x.get(CategoriesTableKeys.CREATEDAT)).getModifiedDateString());
			categoryPath.setUpdatedAt(new StringHelper(x.get(CategoriesTableKeys.UPDATEDAT)).getModifiedDateString());
			categoryPathList.add(categoryPath);
		});
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		return dataTable.size();
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
		List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, param);
		List<GetCategoryDatum> categoriesList = new ArrayList<GetCategoryDatum>();
		dataTable.forEach(x -> {
			GetCategoryDatum categories = new GetCategoryDatum();
			categories.setId(x.get(CategoriesTableKeys.ID));
			categories.setName(x.get(CategoriesTableKeys.NAME));
			categories.setCreatedAt(new StringHelper(x.get(CategoriesTableKeys.CREATEDAT)).getModifiedDateString());
			categories.setUpdatedAt(new StringHelper(x.get(CategoriesTableKeys.UPDATEDAT)).getModifiedDateString());
			categories.setSubCategories(getSubcategoriesList(x.get(CategoriesTableKeys.ID)));
			categories.setCategoryPath(getCategoryPathList(x.get(CategoriesTableKeys.ID)));
			categoriesList.add(categories);
		});
		return categoriesList.get(0);
	}
}
