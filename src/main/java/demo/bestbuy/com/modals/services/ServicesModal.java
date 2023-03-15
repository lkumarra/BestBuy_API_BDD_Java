package demo.bestbuy.com.modals.services;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public final class ServicesModal {

	@Getter
	@Setter
	@EqualsAndHashCode
	public static class GetServicesModal {
		private int total;
		private int limit;
		private int skip;
		private List<Datum> data;
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	public static class Datum {
		private int id;
		private String name;
		private String createdAt;
		private String updatedAt;
	}

}
