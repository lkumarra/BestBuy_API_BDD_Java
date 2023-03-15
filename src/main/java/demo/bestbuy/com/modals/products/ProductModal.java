package demo.bestbuy.com.modals.products;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public final class ProductModal {

	@Getter
	@Setter
	@EqualsAndHashCode
	/**
	 * This class contains getters and setters for Deserilization
	 * 
	 * @author Lavendra Kumar Rajput
	 *
	 * @Date 2 July 2021
	 */
	public static class GetProductModal {
		private int total;
		private int limit;
		private int skip;
		private List<GetProductDatum> data;
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	/**
	 * This class contains getters and setters for Deserilization
	 * 
	 * @author Lavendra Kumar Rajput
	 *
	 * @Date 2 July 2021
	 */
	public static class GetProductDatum {
		private int id;
		private String name;
		private String type;
		private double price;
		private String upc;
		private double shipping;
		private String description;
		private String manufacturer;
		private String model;
		private String url;
		private String image;
		private String createdAt;
		private String updatedAt;
		private List<GetProductCategory> categories;
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	/**
	 * This class contains getters and setters for Deserilization
	 * 
	 * @author Lavendra Kumar Rajput
	 *
	 * @Date 2 July 2021
	 */
	public static class GetProductCategory {
		private String id;
		private String name;
		private String createdAt;
		private String updatedAt;
	}
}
