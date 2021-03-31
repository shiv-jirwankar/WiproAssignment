package pageObjects;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Log;
import utility.MobileActions;

public class ProductSearchResultPage {
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/list_product_description_layout")
	public List<MobileElement> searchResults;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	public List<MobileElement> productDescriptions;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'rs_item_styled_byline')]/android.widget.TextView")
	public List<MobileElement> productNames;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'rs_vertical_stack_view')]/android.widget.TextView")
	public List<MobileElement> productList;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'rs_results_styled_price_v2')]/android.widget.TextView")
	public List<MobileElement> productPrices;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public ProductSearchResultPage(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to select the product from the search results
	 * 
	 * @param productNumber --> argument is the product count from 1 to 4
	 */
	public ProductDetailsPage selectProduct(int productNumber) {
		Log.INFO("Selecting product no." + productNumber + " from the search result list");
		MobileElement product = searchResults.get(productNumber - 1);
		product.click();

		return new ProductDetailsPage(driver, utility);
	}

	/**
	 * Gets the product description of a desired product from the Product Search
	 * results screen
	 * 
	 * @param productNumber
	 * 
	 */
	public String getProductDescription(int productNumber) {
		Log.INFO("Getting the description of product no." + productNumber + " from the search result list");
		MobileElement productDescription = productDescriptions.get(productNumber - 1);
		return productDescription.getText();
	}

	/**
	 * Gets the product name from the Product Search results screen
	 * 
	 * @param productNumber
	 * @return
	 */
	public String getProductName(int productNumber) {
		Log.INFO("Getting the name of product no. " + productNumber + " from the search result list");
		MobileElement productName = productNames.get(productNumber - 1);
		return productName.getText();
	}

	/**
	 * Gets product discount price from the Product Search results screen
	 * 
	 * @param productNumber
	 */
	public String getProductPrice(int productNumber) {
		Log.INFO("Getting the price of product no. " + productNumber + " from the search result list");
		MobileElement product = productPrices.get(productNumber - 1);
		String priceText = product.getText().replace("₹", "");
		String discountPrice = priceText.substring(0, priceText.indexOf(' '));

		return discountPrice;

	}

}
