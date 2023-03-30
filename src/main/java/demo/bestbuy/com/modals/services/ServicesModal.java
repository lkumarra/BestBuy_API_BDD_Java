package demo.bestbuy.com.modals.services;

import java.util.List;

import lombok.Data;

public final class ServicesModal {

	@Data
	public static class GetServicesModal {
		private int total;
		private int limit;
		private int skip;
		private List<Datum> data;
	}

	@Data
	public static class Datum {
		private int id;
		private String name;
		private String createdAt;
		private String updatedAt;
	}

}
