package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Log;
import utility.MobileActions;

public class ProductDetailsPage {
	@AndroidFindBy(xpath = "//*[@resource-id='add-to-cart-button']")
	public MobileElement addToCartButton;
	
	@AndroidFindBy(accessibility = "Cart")
	public MobileElement cartButton;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public ProductDetailsPage(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public CheckoutPage clickOnCartButton()
	{
		Log.INFO("Clicking on the 'Cart' button on top right");
		cartButton.click();
		
		return new CheckoutPage(driver , utility);
	}

}
