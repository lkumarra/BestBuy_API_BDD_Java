package demo.bestbuy.com.categories;

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
public class GetCategoryModal {
	private int total;
	private int limit;
	private int skip;
	private List<GetCategoryDatum> data;

}
