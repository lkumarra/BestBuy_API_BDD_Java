package demo.bestbuy.com.helpers.apidatabasehelper.products;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import demo.bestbuy.com.data.dbKeys.DataBaseKeys.CategoriesTableKeys;
import demo.bestbuy.com.data.dbKeys.DataBaseKeys.ProductTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.products.ProductModal.GetProductCategory;
import demo.bestbuy.com.modals.products.ProductModal.GetProductDatum;


/**
 * This class contains the DB methods related to /products API.
 *
 * @author Lavendra Kumar Rajput
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

        return dataTable.stream().map(row -> {
            GetProductDatum product = new GetProductDatum();
            try {
                product.setId(Integer.parseInt(row.get(ProductTableKeys.ID)));
                product.setName(row.get(ProductTableKeys.NAME));
                product.setType(row.get(ProductTableKeys.TYPE));
                product.setPrice(parseDoubleOrNull(row.get(ProductTableKeys.PRICE)));
                product.setUpc(row.get(ProductTableKeys.UPC));
                product.setShipping(parseDoubleOrNull(row.get(ProductTableKeys.SHIPPING)));
                product.setDescription(row.get(ProductTableKeys.DESCRIPTION));
                product.setManufacturer(row.get(ProductTableKeys.MANUFACTURER));
                product.setModel(row.get(ProductTableKeys.MODEL));
                product.setUrl(row.get(ProductTableKeys.URL));
                product.setImage(row.get(ProductTableKeys.IMAGE));
                product.setCreatedAt(formatDateString(row.get(ProductTableKeys.CREATED_AT)));
                product.setUpdatedAt(formatDateString(row.get(ProductTableKeys.UPDATED_AT)));
                product.setCategories(getFilteredCategories(Integer.parseInt(row.get(ProductTableKeys.ID))));
            } catch (NumberFormatException e) {
                // Handle the exception if needed, e.g., log it or set default values
                System.err.println("Error parsing number: " + e.getMessage());
            }
            return product;
        }).collect(Collectors.toList());
    }

    private static Double parseDoubleOrNull(String value) {
        return Double.parseDouble(value);
    }

    private static String formatDateString(String dateString) {
        return dateString != null ? new StringHelper(dateString).getModifiedDateString() : null;
    }

    /**
     * Fetch the product category from DB on the basis of productId
     *
     * @param productId : Product Id to fectch product category.
     * @return List of {@link GetProductCategory}
     * @author Lavendra rajput
     */
    public static List<GetProductCategory> getFilteredCategories(int productId) {
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetCategoriesFilterd.sql");
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, Collections.singletonList(productId));
        return dataTable.stream().map(row -> {
            GetProductCategory productCategory = new GetProductCategory();
            productCategory.setId(row.get(CategoriesTableKeys.ID));
            productCategory.setName(row.get(CategoriesTableKeys.NAME));
            productCategory.setCreatedAt(formatDateString(row.get(ProductTableKeys.CREATED_AT)));
            productCategory.setUpdatedAt(formatDateString(row.get(ProductTableKeys.UPDATED_AT)));
            return productCategory;
        }).collect(Collectors.toList());
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
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, Collections.singletonList(id));
        return dataTable.stream().findFirst().map(row -> {
            GetProductDatum product = new GetProductDatum();
            product.setId(Integer.parseInt(row.get(ProductTableKeys.ID)));
            product.setName(row.get(ProductTableKeys.NAME));
            product.setType(row.get(ProductTableKeys.TYPE));
            product.setPrice(parseDoubleOrNull(row.get(ProductTableKeys.PRICE)));
            product.setUpc(row.get(ProductTableKeys.UPC));
            product.setShipping(parseDoubleOrNull(row.get(ProductTableKeys.SHIPPING)));
            product.setDescription(row.get(ProductTableKeys.DESCRIPTION));
            product.setManufacturer(row.get(ProductTableKeys.MANUFACTURER));
            product.setModel(row.get(ProductTableKeys.MODEL));
            product.setUrl(row.get(ProductTableKeys.URL));
            product.setImage(row.get(ProductTableKeys.IMAGE));
            product.setCreatedAt(formatDateString(row.get(ProductTableKeys.CREATED_AT)));
            product.setUpdatedAt(formatDateString(row.get(ProductTableKeys.UPDATED_AT)));
            product.setCategories(getFilteredCategories(Integer.parseInt(row.get(ProductTableKeys.ID))));
            return product;
        }).orElse(new GetProductDatum());
    }


    public static void deleteAddedProduct() {
        String script = DBHelpers.getDBScript(scriptPath + "Script.DeleteAddedProduct.sql");
        DBHelpers.executeNonQuery(script);
    }

}
