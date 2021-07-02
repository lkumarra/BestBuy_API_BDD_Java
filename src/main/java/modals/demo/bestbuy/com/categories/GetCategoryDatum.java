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
public class GetCategoryDatum {
	private String id;
	private String name;
	private String createdAt;
	private String updatedAt;
	private List<GetCategorySubCategory> subCategories;
	private List<GetCategoryCategoryPath> categoryPath;
}
