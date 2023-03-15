package demo.bestbuy.com.data.categories;

public final class CategoriesData {

	public enum CategoriesDataEnum{
		DVD_Games,
        Unique_Gifts,
        Invalid
	}
	
	public static String getEnumString(CategoriesDataEnum categoriesDataEnum) {
		switch (categoriesDataEnum)
        {
            case DVD_Games:
                return "abcat0020002";
            case Unique_Gifts:
                return "abcat0020004";
            case Invalid:
            	return "aaaaaaaaaaaa";
            default:
                return null;
        }
	}
}
