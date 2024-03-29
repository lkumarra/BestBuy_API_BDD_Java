package demo.bestbuy.com.modals.products;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public final class PostProductModal {
	

	@Data
	public static class PostProduct {
		private String name;
		private String type;
		private int price;
		private int shipping;
		private String upc;
		private String description;
		private String manufacturer;
		private String model;
		private String url;
		private String image;
	}

	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = true)
	public static class DateTimeModal extends PostProduct {
		private String createdAt;
		private String updatedAt;
	}
}
