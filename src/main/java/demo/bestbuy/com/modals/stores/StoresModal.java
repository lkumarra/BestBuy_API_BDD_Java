package demo.bestbuy.com.modals.stores;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public final class StoresModal {

	@Getter
	@Setter
	@EqualsAndHashCode
	public static class DateModal {
		private String createdAt;
		private String updatedAt;
	}

	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = true)
	public static class StoresData extends DateModal {
		private int id;
		private String name;
		private String type;
		private String address;
		private String address2;
		private String city;
		private String state;
		private String zip;
		private double lat;
		private double lng;
		private String hours;
		private List<Services> services;
	}

	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = true)
	public static class Services extends DateModal {
		private int id;
		private String name;
		private StoresServices storeservices;
	}

	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = true)
	public static class StoresServices extends DateModal {
		private int storeId;
		private int serviceId;
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	public static class GetStoresModal {
		private int total;
		private int limit;
		private int skip;
		private List<StoresData> data;
	}
}
