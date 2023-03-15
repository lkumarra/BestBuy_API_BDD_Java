package demo.bestbuy.com.categories;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class CategoriesModal {
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
	public static class GetCategoryModal {
		private int total;
		private int limit;
		private int skip;
		private List<GetCategoryDatum> data;

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
	public static class GetCategoryDatum {
		private String id;
		private String name;
		private String createdAt;
		private String updatedAt;
		private List<GetCategorySubCategory> subCategories;
		private List<GetCategoryCategoryPath> categoryPath;
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
	public static class GetCategoryCategoryPath {
		private String id;
		private String name;
		private String createdAt;
		private String updatedAt;
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
	public static class GetCategorySubCategory {
		private String id;
		private String name;
		private String createdAt;
		private String updatedAt;
	}

}
