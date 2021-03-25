package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Log;
import utility.MobileActions;

public class CheckoutPage {
	@AndroidFindBy(xpath = "(//*[@resource-id='activeCartViewForm']//android.view.View/android.view.View[1])[4]")
	public MobileElement productDesc;
	
	@AndroidFindBy(xpath = "(//*[@class='android.widget.ListView'])[1]/android.view.View[2]")
	public MobileElement productPrice;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public CheckoutPage(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getProductDescription()
	{
		Log.INFO("Getting the description of product added to the cart");
		return productDesc.getAttribute("content-desc");	
	}
	
	public String getProductPrice()
	{
		Log.INFO("Getting the price of product added to the cart");
		return productPrice.getText();	
	}
}
