package pageObjects;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Log;
import utility.MobileActions;

public class SignInPage {
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
	public MobileElement skipSignInButton;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public SignInPage(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public HomeScreen clickOnSkipSignInButton()
	{
		Log.INFO("Clicking on 'Skip Sign In' button");
		skipSignInButton.click();
		
		return new HomeScreen(driver, utility);
	}
}
