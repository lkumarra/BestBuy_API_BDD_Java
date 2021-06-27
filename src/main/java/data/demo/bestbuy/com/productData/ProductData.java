package demo.bestbuy.com.productData;

public class ProductData {

	private static int DuracellCopperTechBattery = 48530;
	private static int DuracellAABattery = 127684;
	private static int Invalid = 0;

	public static int getProductIdViaProductName(String productName) {
		switch (productName) {
		case "DuracellCopperTechBattery":
			return DuracellCopperTechBattery;
		case "DuracellAABattery":
			return DuracellAABattery;
		default:
			return Invalid;
		}
	}
}
