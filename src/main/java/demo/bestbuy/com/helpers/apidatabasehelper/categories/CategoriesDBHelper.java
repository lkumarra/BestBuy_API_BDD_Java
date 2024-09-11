package demo.bestbuy.com.helpers.apidatabasehelper.categories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import demo.bestbuy.com.data.dbKeys.DataBaseKeys.CategoriesTableKeys;
import demo.bestbuy.com.data.dbKeys.DataBaseKeys.CategoryPathTableKeys;
import demo.bestbuy.com.data.dbKeys.DataBaseKeys.SubCategoriesTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryCategoryPath;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryDatum;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategorySubCategory;
import lombok.extern.slf4j.Slf4j;


/**
 * This class contains the DB Methods of /categories API.
 *
 * @author Lavendra Kumar Rajput
 * @Date 2 July 2021
 */
@Slf4j
public final class CategoriesDBHelper {

    private static final String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Categories/";

    /**
     * Fetch the categories list from DB.
     *
     * @return List of {@link GetCategoryDatum}
     * @author Lavendra rajput
     */
    public static List<GetCategoryDatum> getCategoriesList() {
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategories.sql");

        // Execute the script and retrieve the data table
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);

        // Convert the result set to a list of GetCategoryDatum objects
        return dataTable.stream().map(row -> {
            GetCategoryDatum category = new GetCategoryDatum();
            category.setId(row.get(CategoriesTableKeys.ID));
            category.setName(row.get(CategoriesTableKeys.NAME));
            category.setCreatedAt(formatDateString(row.get(CategoriesTableKeys.CREATED_AT)));
            category.setUpdatedAt(formatDateString(row.get(CategoriesTableKeys.UPDATED_AT)));
            category.setSubCategories(getSubcategoriesList(row.get(CategoriesTableKeys.ID)));
            category.setCategoryPath(getCategoryPathList(row.get(CategoriesTableKeys.ID)));
            return category;
        }).collect(Collectors.toList());
    }

    private static String formatDateString(String dateString) {
        return dateString != null ? new StringHelper(dateString).getModifiedDateString() : null;
    }

    /**
     * Fetch the subcategory on the basis of category Id.
     *
     * @param categoryId : Category id to fetch subcategory
     * @return List of {@link GetCategorySubCategory}
     * @author Lavendra rajput
     */
    private static List<GetCategorySubCategory> getSubcategoriesList(String categoryId) {
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetSubCategories.sql");

        // Execute the script with the category ID as a parameter
        List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, Collections.singletonList(categoryId));

        // Convert the result set to a list of GetCategorySubCategory objects
        return dataTable.stream().map(row -> {
            GetCategorySubCategory subCategory = new GetCategorySubCategory();
            try {
                subCategory.setId(row.get(SubCategoriesTableKeys.ID));
                subCategory.setName(row.get(SubCategoriesTableKeys.NAME));
                subCategory.setCreatedAt(formatDateString(row.get(CategoriesTableKeys.CREATED_AT)));
                subCategory.setUpdatedAt(formatDateString(row.get(CategoriesTableKeys.UPDATED_AT)));
            } catch (Exception e) {
                // Handle potential errors such as null values or parsing issues
                log.error("Error processing subcategory data: {}", e.getMessage());
            }
            return subCategory;
        }).collect(Collectors.toList());
    }

    /**
     * Fetch the category path on the basis of category Id.
     *
     * @param categoryId : category id to fetch Category path.
     * @return List of {@link GetCategoryCategoryPath}
     * @author Lavendra rajput
     */
    private static List<GetCategoryCategoryPath> getCategoryPathList(String categoryId) {
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoryPath.sql");

        // Execute the script with the category ID as a parameter
        List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, Collections.singletonList(categoryId));

        // Convert the result set to a list of GetCategoryCategoryPath objects
        return dataTable.stream().map(row -> {
            GetCategoryCategoryPath categoryPath = new GetCategoryCategoryPath();
            try {
                categoryPath.setId(row.get(CategoryPathTableKeys.ID));
                categoryPath.setName(row.get(CategoryPathTableKeys.NAME));
                categoryPath.setCreatedAt(formatDateString(row.get(CategoriesTableKeys.CREATED_AT)));
                categoryPath.setUpdatedAt(formatDateString(row.get(CategoriesTableKeys.UPDATED_AT)));
            } catch (Exception e) {
                // Handle potential errors such as null values or parsing issues
                System.err.println("Error processing category path data: " + e.getMessage());
            }
            return categoryPath;
        }).collect(Collectors.toList());
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
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoriesViaId.sql");

        // Execute the script with the category ID as a parameter
        List<HashMap<String, String>> dataTable = DBHelpers.executeScriptWithStringParam(script, Collections.singletonList(categoryId));

        // Convert the result set to a GetCategoryDatum object if available
        return dataTable.stream().findFirst().map(row -> {
            GetCategoryDatum category = new GetCategoryDatum();
            try {
                category.setId(row.get(CategoriesTableKeys.ID));
                category.setName(row.get(CategoriesTableKeys.NAME));
                category.setCreatedAt(formatDateString(row.get(CategoriesTableKeys.CREATED_AT)));
                category.setUpdatedAt(formatDateString(row.get(CategoriesTableKeys.UPDATED_AT)));
                category.setSubCategories(getSubcategoriesList(row.get(CategoriesTableKeys.ID)));
                category.setCategoryPath(getCategoryPathList(row.get(CategoriesTableKeys.ID)));
            } catch (Exception e) {
                // Handle potential errors such as null values or parsing issues
                System.err.println("Error processing category data: " + e.getMessage());
            }
            return category;
        }).orElse(new GetCategoryDatum()); // Return null if no category found
    }
}
