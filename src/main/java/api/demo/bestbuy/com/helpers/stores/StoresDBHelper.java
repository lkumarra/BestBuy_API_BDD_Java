package demo.bestbuy.com.stores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import demo.bestbut.com.dbKeys.DBkeys.StoresTableKeys;
import demo.bestbuy.com.apihelper.StringHelper;
import demo.bestbuy.com.dbhelpers.DBHelpers;
import demo.bestbuy.com.stores.StoresModal.Services;
import demo.bestbuy.com.stores.StoresModal.StoresData;
import demo.bestbuy.com.stores.StoresModal.StoresServices;

public class StoresDBHelper {

	private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Stores/";

	/**
	 * Fetch the list of stores from DB.
	 * @return List of {@link StoresData}
	 */
	public static List<StoresData> getStoresFromDb() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetStores.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		List<StoresData> storesModalList = new ArrayList<StoresData>();
		List<Services> servicesList = getServices(dataTable);
		dataTable.forEach(x -> {
			StoresData storesData = new StoresData();
			int storeId = Integer.parseInt(x.get(StoresTableKeys.STORESID));
			storesData.setId(storeId);
			storesData.setName(x.get(StoresTableKeys.STORESNAME));
			storesData.setType(x.get(StoresTableKeys.TYPE));
			storesData.setAddress(x.get(StoresTableKeys.ADDRESS));
			storesData.setAddress2(x.get(StoresTableKeys.ADDRESS2));
			storesData.setCity(x.get(StoresTableKeys.CITY));
			storesData.setState(x.get(StoresTableKeys.STATE));
			storesData.setZip(x.get(StoresTableKeys.ZIP));
			storesData.setLat(Double.parseDouble(x.get(StoresTableKeys.LAT)));
			storesData.setLng(Double.parseDouble(x.get(StoresTableKeys.LNG)));
			storesData.setHours(x.get(StoresTableKeys.HOURS));
			storesData.setCreatedAt(new StringHelper(x.get(StoresTableKeys.STORESCREATEDAT)).getModifiedDateString());
			storesData.setUpdatedAt(new StringHelper(x.get(StoresTableKeys.STORESUPDATEDAT)).getModifiedDateString());
			List<Services> servicesList1 = servicesList.stream()
					.filter(y -> y.getStoreservices().getStoreId() == storeId).collect(Collectors.toList());
			storesData.setServices(servicesList1);
			storesModalList.add(storesData);
		});
		return storesModalList.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * Fetch the list of store services from DB
	 * @param dataTable : Data to map
	 * @return
	 */
	public static List<StoresServices> getStoresServices(List<HashMap<String, String>> dataTable) {
		List<StoresServices> storesServicesList = new ArrayList<StoresModal.StoresServices>();
		dataTable.forEach(x -> {
			StoresServices storesServices = new StoresServices();
			storesServices.setStoreId(Integer.parseInt(x.get(StoresTableKeys.STORESID)));
			storesServices.setServiceId(Integer.parseInt(x.get(StoresTableKeys.SERVICESID)));
			storesServices.setCreatedAt(
					new StringHelper(x.get(StoresTableKeys.STORESSERVICECREATEDAT)).getModifiedDateString());
			storesServices.setUpdatedAt(
					new StringHelper(x.get(StoresTableKeys.STORESSERVICEUPDATEDAT)).getModifiedDateString());
			storesServicesList.add(storesServices);
		});
		return storesServicesList.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * Fetch the list of services 
	 * @param dataTable
	 * @return
	 */
	public static List<Services> getServices(List<HashMap<String, String>> dataTable) {
		List<Services> servicesList = new ArrayList<Services>();
		List<StoresServices> storesServices = getStoresServices(dataTable);
		dataTable.forEach(x -> {
			Services services = new Services();
			int storeId = Integer.parseInt(x.get(StoresTableKeys.STORESID));
			int serviceId = Integer.parseInt(x.get(StoresTableKeys.SERVICESID));
			services.setId(serviceId);
			services.setName(x.get(StoresTableKeys.SERVICESNAME));
			services.setUpdatedAt(new StringHelper(x.get(StoresTableKeys.SERVICESCREATEDAT)).getModifiedDateString());
			services.setCreatedAt(new StringHelper(x.get(StoresTableKeys.SERVICEUPDATEDAT)).getModifiedDateString());
			StoresServices storesService = storesServices.stream()
					.filter(y -> y.getStoreId() == storeId && y.getServiceId() == serviceId).findFirst().get();
			services.setStoreservices(storesService);
			servicesList.add(services);
		});
		return servicesList.stream().distinct().collect(Collectors.toList());
	}
	
	public static int getTotalStores() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalStores.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		return Integer.parseInt(dataTable.get(0).get("count"));
	}

}
