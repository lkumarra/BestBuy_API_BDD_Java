package demo.bestbuy.com.products;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class ProductModal {

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
		public int total;
		public int limit;
		public int skip;
		public List<GetProductDatum> data;
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
		public String id;
		public String name;
		public String createdAt;
		public String updatedAt;
	}
}
