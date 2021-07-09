package demo.bestbut.com.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.dbhelpers.DBHelpers;
import demo.bestbuy.com.services.Datum;

public class ServicesDBHelper {
	
	private static String scriptPath = System.getProperty("user.dir") + "/src/main/resources/DBScripts/Services/";
	
	public static List<Datum> getServicesFromDB(){
		String script = DBHelpers.getDBScript(scriptPath +"Script.GetServices.sql");
		String json = DBHelpers.executeScript(script);
		Datum[] datumMappedArray = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json, Datum[].class);
		List<Datum> datumList = Arrays.asList(datumMappedArray);
		datumList.forEach(x->{
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return datumList;
	}
	
	public static int getTotalServices() {
		String script = DBHelpers.getDBScript(scriptPath +"Script.GetTotalServices.sql");
		String json = DBHelpers.executeScript(script);
		Datum[] datumMappedArray = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json, Datum[].class);
		return datumMappedArray.length;
	}
	
	public static Datum getServicesFromDB(int id){
		String script = DBHelpers.getDBScript(scriptPath +"Script.GetServicesViaId.sql");
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);
		String json = DBHelpers.executeScript(script, param);
		Datum[] datumMappedArray = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(json, Datum[].class);
		List<Datum> datumList = Arrays.asList(datumMappedArray);
		datumList.forEach(x->{
			x.setCreatedAt(x.getCreatedAt().replace(" +00:00", "Z").replace(" ", "T"));
			x.setUpdatedAt(x.getUpdatedAt().replace(" +00:00", "Z").replace(" ", "T"));
		});
		return datumList.get(0);
	}
}
