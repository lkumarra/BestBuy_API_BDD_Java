package demo.bestbuy.com.products;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GetProductModal {
	public int total;
	public int limit;
	public int skip;
	public List<GetProductDatum> data;
}
