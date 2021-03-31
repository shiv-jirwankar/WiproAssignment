package testSuite;

import java.io.IOException;

import org.testng.Assert;
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
	String productNameInSearchResult;
	String productPriceInSearchResult;
	String productDescInSearchResult;

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

	/**
	 * Test to verify the value getting updated on the CART icon on the top right of
	 * the Product details screen. For eg. when user add a product to the cart, then
	 * on the CART icon on above, the value will be updated to one.
	 * 
	 * @throws IOException
	 */
	@Test(priority = 1)
	public void verifyNumericValueOnCartIcon() throws IOException {
		sp.clickOnSkipSignInButton().clickOnSearchButton()
				.searchItems(red.readDataFromExcel("Product to search", "product to search"))
				.clickOnFirstSuggestedSearchResult();

		productNameInSearchResult = psr.getProductName(2);
		productPriceInSearchResult = psr.getProductPrice(2);
		productDescInSearchResult = psr.getProductDescription(2);
		Log.INFO("Product Name on search result is: " + productNameInSearchResult
				+ ", Product Price on search result is: " + productPriceInSearchResult
				+ ", Product description on search result is: " + productDescInSearchResult);

		psr.selectProduct(2);
		utils.scrollToTextContains("Add to Cart");
		pdp.addToCartButton.click();
		utils.isElementPresent(pdp.cartButton, 10);
		String numericValueOnCartIcon = pdp.cartButton.getText();
		String expectedNumericValueOnCart = red.readDataFromExcel("Sheet", "expected card value on icon");

		Assert.assertEquals(numericValueOnCartIcon, expectedNumericValueOnCart);
	}

	/**
	 * Test to verify the 'Product Description' and 'Product Price' are same in 'Product
	 * search result screen' and 'Checkout screen'
	 * 
	 * @throws IOException
	 */
	@Test(priority = 2)
	public void verifyProductDetailsOnCheckoutScreen() throws IOException {
		pdp.clickOnCartButton();
		String productDescOnCheckout = cp.getProductDescription().replace("...", "");
		String productPriceOnCheckout = cp.getProductPrice().replace("₹", "");
		Log.INFO("Product Price on Checkout screen is: " + productPriceOnCheckout
				+ ", Product Description on search result is: " + productDescOnCheckout);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(productDescInSearchResult.contains(productDescOnCheckout));
		sa.assertEquals(productPriceOnCheckout, productPriceInSearchResult);
		sa.assertAll();
	}

}
