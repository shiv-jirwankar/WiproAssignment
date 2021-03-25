package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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

	@AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_item_styled_byline']/android.widget.TextView")
	public List<MobileElement> productNames;

	@AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_vertical_stack_view']")
	public List<MobileElement> productList;

	@AndroidFindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView")
	public List<MobileElement> productPrices;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public ProductSearchResultPage(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to select the product from the search results.
	 * 
	 * @param productNumber --> argument is the product count from 1 to 4
	 * @return
	 */
	public ProductDetailsPage selectProduct(int productNumber) {
		Log.INFO("Selecting product no." + productNumber + " from the search result list");

		// List 'searchResults' will have a list of all search results products
		MobileElement product = searchResults.get(productNumber - 1);
		product.click();

		return new ProductDetailsPage(driver, utility);
	}

	/*
	 * public String getProductName(int productNumber) {
	 * Log.INFO("Getting the name of " + productNumber +
	 * " product from the search result list");
	 * 
	 * // List 'productName' will have a list of all search results products name
	 * MobileElement productName = productNames.get(productNumber - 1); return
	 * productName.getText(); }
	 */

	public String getProductDescription(int productNumber) {
		Log.INFO("Getting the description of product no." + productNumber + " from the search result list");

		// List 'productDescriptions' will have a list of all search results product
		// descriptions
		MobileElement productDescription = productDescriptions.get(productNumber - 1);
		return productDescription.getText();
	}

	public String getProductName(int productNumber) {
		Log.INFO("Getting the name of product no. " + productNumber + " from the search result list");

		// List 'productDescriptions' will have a list of all search results product
		// descriptions
		MobileElement productName = productNames.get(productNumber - 1);
		return productName.getText();
	}

	public String getProductPrice(int productNumber) {
		Log.INFO("Getting the price of product no. " + productNumber + " from the search result list");

		// List 'productDescriptions' will have a list of all search results product
		// descriptions
		MobileElement product = productPrices.get(productNumber - 1);
		//MobileElement productPrice = product
			//	.findElement(By.xpath("[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_price']"));
		String priceText = product.getText().replace("₹", "");

		// the price text also contains original price and discount price i.e ₹3,500
		// ₹7,000 etc
		// hence, just trying to get the discount value
		String discountPrice = priceText.substring(0, priceText.indexOf(' '));

		return discountPrice;

	}

}
