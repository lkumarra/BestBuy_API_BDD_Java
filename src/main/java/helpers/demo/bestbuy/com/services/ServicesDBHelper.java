package demo.bestbuy.com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.bestbut.com.dbKeys.DBkeys.ProductTableKeys;
import demo.bestbut.com.dbKeys.DBkeys.ServicesTableKeys;
import demo.bestbuy.com.apihelper.StringHelper;
import demo.bestbuy.com.dbhelpers.DBHelpers;
import demo.bestbuy.com.services.ServicesModal.Datum;

public class ServicesDBHelper {

	private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Services/";

	public static List<Datum> getServicesFromDB() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetServices.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		List<Datum> servicesList = new ArrayList<Datum>();
		dataTable.forEach(x -> {
			Datum services = new Datum();
			services.setId(Integer.parseInt(x.get(ServicesTableKeys.ID)));
			services.setName(x.get(ServicesTableKeys.NAME));
			services.setCreatedAt(new StringHelper(x.get(ProductTableKeys.CREATEDAT)).getModifiedDateString());
			services.setUpdatedAt(new StringHelper(x.get(ProductTableKeys.UPDATEDAT)).getModifiedDateString());
			servicesList.add(services);
		});
		return servicesList;
	}

	public static int getTotalServices() {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetTotalServices.sql");
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script);
		return dataTable.size();
	}

	public static Datum getServicesFromDB(int id) {
		String script = DBHelpers.getDBScript(scriptPath + "Script.GetServicesViaId.sql");
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);
		List<HashMap<String, String>> dataTable = DBHelpers.executeScript(script, param);
		List<Datum> servicesList = new ArrayList<Datum>();
		dataTable.forEach(x -> {
			Datum services = new Datum();
			services.setId(Integer.parseInt(x.get(ServicesTableKeys.ID)));
			services.setName(x.get(ServicesTableKeys.NAME));
			services.setCreatedAt(new StringHelper(x.get(ProductTableKeys.CREATEDAT)).getModifiedDateString());
			services.setUpdatedAt(new StringHelper(x.get(ProductTableKeys.UPDATEDAT)).getModifiedDateString());
			servicesList.add(services);
		});
		return servicesList.get(0);
	}
}
