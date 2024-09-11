package demo.bestbuy.com.helpers.apidatabasehelper.services;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import demo.bestbuy.com.data.dbKeys.DataBaseKeys.ProductTableKeys;
import demo.bestbuy.com.data.dbKeys.DataBaseKeys.ServicesTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.services.ServicesModal.Datum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ServicesDBHelper {

    private static final String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Services/";

    private static void logProcessingErrorMessage(String message) {
        log.error("Error processing service data: {}", message);
    }

    public static List<Datum> getServicesFromDB() {
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetServices.sql");

        // Execute the script and retrieve the data table
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);


        // Convert the result set to a list of Datum objects using streams
        return dataTable.stream().map(row -> {
            Datum service = new Datum();
            try {
                service.setId(Integer.parseInt(row.get(ServicesTableKeys.ID)));
                service.setName(row.get(ServicesTableKeys.NAME));
                service.setCreatedAt(formatDateString(row.get(ProductTableKeys.CREATED_AT)));
                service.setUpdatedAt(formatDateString(row.get(ProductTableKeys.UPDATED_AT)));
            } catch (Exception e) {
                // Handle potential errors such as parsing issues or null values
                logProcessingErrorMessage(e.getMessage());
            }
            return service;
        }).collect(Collectors.toList());
    }

    public static int getTotalServices() {
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalServices.sql");
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
        return dataTable.size();
    }

    public static Datum getServicesFromDB(int id) {
        // Load the SQL script
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetServicesViaId.sql");

        // Execute the script with the ID as a parameter
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, Collections.singletonList(id));

        // Convert the result set to a Datum object if available
        return dataTable.stream().findFirst().map(row -> {
            Datum service = new Datum();
            try {
                service.setId(Integer.parseInt(row.get(ServicesTableKeys.ID)));
                service.setName(row.get(ServicesTableKeys.NAME));
                service.setCreatedAt(formatDateString(row.get(ProductTableKeys.CREATED_AT)));
                service.setUpdatedAt(formatDateString(row.get(ProductTableKeys.UPDATED_AT)));
            } catch (Exception e) {
                // Handle potential errors such as parsing issues or null values
                logProcessingErrorMessage(e.getMessage());
            }
            return service;
        }).orElse(new Datum()); // Return null if no data is found
    }


    private static String formatDateString(String dateString) {
        return dateString != null ? new StringHelper(dateString).getModifiedDateString() : null;
    }

}
