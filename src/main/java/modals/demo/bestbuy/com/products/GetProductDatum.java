package demo.bestbuy.com.products;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
public class GetProductDatum {
	private int id;
	private String name;
	private String type;
	private double price;
	private String upc;
	private int shipping;
	private String description;
	private String manufacturer;
	private String model;
	private String url;
	private String image;
	private String createdAt;
	private String updatedAt;
	private List<GetProductCategory> categories;
}
