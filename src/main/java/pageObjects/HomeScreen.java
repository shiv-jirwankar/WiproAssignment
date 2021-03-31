package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Log;
import utility.MobileActions;

public class HomeScreen {
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon")
	public MobileElement hamburgerMenuButton;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	public MobileElement searchButton;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions'])[1]")
	public MobileElement searchSuggestionFirst;

	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;

	public HomeScreen(AppiumDriver<MobileElement> driver, MobileActions utility) {
		this.driver = driver;
		this.utility = utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Click on the Search button on the Home Screen
	 */
	public HomeScreen clickOnSearchButton() {
		Log.INFO("Clicking on SEARCH button");
		utility.isElementPresent(searchButton, 10);
		searchButton.click();

		return this;
	}

	/**
	 * Search items on the search field
	 * @param itemName
	 *  
	 */
	public HomeScreen searchItems(String itemName) {
		Log.INFO("Searching for " + itemName + " on the search bar");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")));
		} catch (Exception e) {
		}
		searchButton.sendKeys(itemName);

		return this;
	}

	/**
	 * Click on the first suggestion from the searched item
	 */
	public ProductSearchResultPage clickOnFirstSuggestedSearchResult() {
		Log.INFO("Clicking on first suggested product from search text");
		utility.isElementPresent(searchSuggestionFirst, 10);
		searchSuggestionFirst.click();

		return new ProductSearchResultPage(driver, utility);

	}

}
