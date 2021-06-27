package demo.bestbuy.com.categories;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GetCategoryModal {
	private int total;
	private int limit;
	private int skip;
	private List<GetCategoryDatum> data;

}
