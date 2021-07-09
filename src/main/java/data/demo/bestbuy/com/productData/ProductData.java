package demo.bestbuy.com.productData;

public class ProductData {
	public enum ProductDataEnum {
		DuracellCopperTechBattery, DuracellAABattery, Invalid
	}

	public static int getProductIdViaProductName(ProductDataEnum productDataEnum) {
		switch (productDataEnum) {
		case DuracellCopperTechBattery:
			return 48530;
		case DuracellAABattery:
			return 127684;
		case Invalid:
			return 0;
		default:
			return 0;
		}
	}
}
