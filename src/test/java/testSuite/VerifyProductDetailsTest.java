package testSuite;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import infra.DriverClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObjects.CheckoutPage;
import pageObjects.HomeScreen;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductSearchResultPage;
import pageObjects.SignInPage;
import utility.Log;
import utility.MobileActions;
import utility.ReadExcelData;

public class VerifyProductDetailsTest {
	String timeValueFromApi;
	private AppiumDriver<MobileElement> driver;
	private MobileActions utils;
	CheckoutPage cp;
	HomeScreen hs;
	ProductDetailsPage pdp;
	ProductSearchResultPage psr;
	SignInPage sp;
	ReadExcelData red;

	@BeforeSuite
	public void beforeSuite() {
	}

	@Parameters({ "deviceName", "platform", "udid" })
	@BeforeTest
	public void setUp(String deviceName, String platformVersion, String udid) throws InterruptedException, IOException {
		DriverClass in = new DriverClass();
		driver = in.driver(deviceName, platformVersion, udid);
		utils = new MobileActions(driver);
		cp = new CheckoutPage(driver, utils);
		hs = new HomeScreen(driver, utils);
		pdp = new ProductDetailsPage(driver, utils);
		psr = new ProductSearchResultPage(driver, utils);
		sp = new SignInPage(driver, utils);
		red = new ReadExcelData();
	}

	@Test(priority = 1)
	public void verifyProductDetailsOnCheckoutScreen() throws IOException {
		sp.clickOnSkipSignInButton().clickOnSearchButton()
				.searchItems(red.readDataFromExcel("Product to search", "product to search"))
				.clickOnFirstSuggestedSearchResult();

		// Reading and storing all the required values of a product on search results
		// screen
		String productNameInSearchResult = psr.getProductName(2);
		String productPriceInSearchResult = psr.getProductPrice(2);
		String productDescInSearchResult = psr.getProductDescription(2);
		Log.INFO("Product Name on search result is: " + productNameInSearchResult
				+ ", Product Price on search result is: " + productPriceInSearchResult
				+ ", Product description on search result is: " + productDescInSearchResult);

		psr.selectProduct(2);

		utils.scrollToTextContains("Add to Cart");

		pdp.addToCartButton.click();
		utils.isElementPresent(pdp.cartButton, 10);
		pdp.clickOnCartButton();

		// Reading and storing all the required values of a product on checkout screen
		// removing the '...' string from the product description
		String productDescOnCheckout = cp.getProductDescription().replace("...", "");
		String productPriceOnCheckout = cp.getProductPrice().replace("₹", "");
		Log.INFO("Product Price on Checkout screen is: " + productPriceOnCheckout
				+ ", Product Description on search result is: " + productDescOnCheckout);

		// using SoftAssert to assert 'Product desc' and 'Product Price' values on two
		// different screens
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(productDescInSearchResult.contains(productDescOnCheckout));
		sa.assertEquals(productPriceOnCheckout, productPriceInSearchResult);
		sa.assertAll();

	}

}
