package demo.bestbuy.com.products;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GetProductDatum {
	public int id;
	public String name;
	public String type;
	public double price;
	public String upc;
	public int shipping;
	public String description;
	public String manufacturer;
	public String model;
	public String url;
	public String image;
	public String createdAt;
	public String updatedAt;
	public List<GetProductCategory> categories;
}
