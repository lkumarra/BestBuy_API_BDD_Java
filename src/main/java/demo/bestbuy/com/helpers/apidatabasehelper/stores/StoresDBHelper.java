package demo.bestbuy.com.helpers.apidatabasehelper.stores;

import java.util.*;
import java.util.stream.Collectors;

import demo.bestbuy.com.data.dbKeys.DataBaseKeys.StoresTableKeys;
import demo.bestbuy.com.helpers.apihelper.StringHelper;
import demo.bestbuy.com.helpers.dbhelpers.DBHelpers;
import demo.bestbuy.com.modals.stores.StoresModal.Services;
import demo.bestbuy.com.modals.stores.StoresModal.StoresData;
import demo.bestbuy.com.modals.stores.StoresModal.StoresServices;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StoresDBHelper {

    private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Stores/";

    /**
     * Fetch the list of stores from DB.
     *
     * @return List of {@link StoresData}
     */
    public static List<StoresData> getStoresFromDb() {
        // Load the SQL script for fetching store data
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetStores.sql");

        // Execute the script and retrieve the data table
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);

        // Fetch services data from the database
        List<Services> servicesList = getServices(dataTable);

        // Convert the data table to a list of StoresData objects
        Map<Integer, StoresData> storesMap = dataTable.stream().collect(Collectors.toMap(
                row -> Integer.parseInt(row.get(StoresTableKeys.STORES_ID)), // Key: Store ID
                row -> {
                    StoresData store = new StoresData();
                    store.setId(Integer.parseInt(row.get(StoresTableKeys.STORES_ID)));
                    store.setName(row.get(StoresTableKeys.STORES_NAME));
                    store.setType(row.get(StoresTableKeys.TYPE));
                    store.setAddress(row.get(StoresTableKeys.ADDRESS));
                    store.setAddress2(row.get(StoresTableKeys.ADDRESS2));
                    store.setCity(row.get(StoresTableKeys.CITY));
                    store.setState(row.get(StoresTableKeys.STATE));
                    store.setZip(row.get(StoresTableKeys.ZIP));
                    store.setLat(Double.parseDouble(row.get(StoresTableKeys.LAT)));
                    store.setLng(Double.parseDouble(row.get(StoresTableKeys.LNG)));
                    store.setHours(row.get(StoresTableKeys.HOURS));
                    store.setCreatedAt(formatDateString(row.get(StoresTableKeys.STORES_CREATED_AT)));
                    store.setUpdatedAt(formatDateString(row.get(StoresTableKeys.STORES_UPDATED_AT)));
                    return store;
                },
                (existing, replacement) -> existing // Handle duplicate keys (if necessary)
        ));

        // Map store IDs to their services
        Map<Integer, List<Services>> storeIdToServicesMap = servicesList.stream().collect(Collectors.groupingBy(
                s -> s.getStoreservices().getStoreId()
        ));

        // Set services for each store
        storesMap.values().forEach(store -> store.setServices(storeIdToServicesMap.getOrDefault(store.getId(), Collections.emptyList())));

        // Return the list of stores ensuring uniqueness
        return new ArrayList<>(storesMap.values());
    }

    /**
     * Fetch the list of store services from DB
     *
     * @param dataTable : Data to map
     * @return
     */
    public static List<StoresServices> getStoresServices(List<HashMap<String, String>> dataTable) {
        // Convert the data table to a list of StoresServices objects
        return dataTable.stream().map(row -> {
            StoresServices storesServices = new StoresServices();
            try {
                storesServices.setStoreId(Integer.parseInt(row.get(StoresTableKeys.STORES_ID)));
                storesServices.setServiceId(Integer.parseInt(row.get(StoresTableKeys.SERVICES_ID)));
                storesServices.setCreatedAt(formatDateString(row.get(StoresTableKeys.STORES_SERVICE_CREATED_AT)));
                storesServices.setUpdatedAt(formatDateString(row.get(StoresTableKeys.STORES_SERVICE_UPDATED_AT)));
            } catch (Exception e) {
                // Handle potential parsing errors or null values
                log.error("Error processing store service data: {} ", e.getMessage());
            }
            return storesServices;
        }).distinct().collect(Collectors.toList()); // Convert Set back to List

    }

    /**
     * Fetch the list of services
     *
     * @param dataTable
     * @return
     */
    public static List<Services> getServices(List<HashMap<String, String>> dataTable) {
        // Retrieve stores services data
        List<StoresServices> storesServices = getStoresServices(dataTable);

        // Map of (storeId, serviceId) to StoresServices for quick lookup
        Map<String, StoresServices> storesServicesMap = storesServices.stream()
                .collect(Collectors.toMap(
                        s -> s.getStoreId() + ":" + s.getServiceId(), // Key for quick lookup
                        s -> s
                ));

        // Convert dataTable to list of Services objects

        return dataTable.stream().map(row -> {
                    Services services = new Services();
                    int storeId = Integer.parseInt(row.get(StoresTableKeys.STORES_ID));
                    int serviceId = Integer.parseInt(row.get(StoresTableKeys.SERVICES_ID));
                    String key = storeId + ":" + serviceId;

                    services.setId(serviceId);
                    services.setName(row.get(StoresTableKeys.SERVICES_NAME));
                    services.setCreatedAt(formatDateString(row.get(StoresTableKeys.SERVICES_CREATED_AT)));
                    services.setUpdatedAt(formatDateString(row.get(StoresTableKeys.SERVICE_UPDATED_AT)));

                    // Lookup the StoresServices object from the map
                    StoresServices storesService = storesServicesMap.get(key);
                    services.setStoreservices(storesService);

                    return services;
                }).distinct() // Ensure uniqueness
                .collect(Collectors.toList());
    }


    public static int getTotalStores() {
        String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalStores.sql");
        List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
        return Integer.parseInt(dataTable.get(0).get("count"));
    }

    private static String formatDateString(String dateString) {
        return dateString != null ? new StringHelper(dateString).getModifiedDateString() : null;
    }
}
