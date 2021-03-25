package utility;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
/**
 * All the generic android automation methods are defined here.
 * @author Shiv Jirwankar
 *
 */
public class MobileActions{

	public MobileActions(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	private AppiumDriver<MobileElement> driver;


	/*
	 * Added by Shiv
	 */

	public void clickOnBackButtonOfDevice() {
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		Log.INFO("Navigating back from the current screen");
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void clickOnHomeButtonOfDevice() {
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent().withKey(AndroidKey.HOME));
	}

	public void isElementPresent(MobileElement element, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
	}
	
	public void waitTillPresenceOfElementLocated(By element, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
	}

	public void isElementNotPresent(MobileElement element, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void scrollToText(String text) {
		Log.INFO("Scrolling to text: " + text);
		try {
			((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
							+ text + "\").instance(0))");
		} catch (Exception e) {
		}
	}

	public void scrollToTextContains(String text) {
		Log.INFO("Scrolling to text: " + text);
		((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))");
	}

	public void scrollToTextAndClick(String text) {
		Log.INFO("Scrolling to text: " + text);
		((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
						+ text + "\").instance(0))")
				.click();
	}

	public void scrollToText(String eleResourceId, String text) {
		((AndroidDriver<MobileElement>) driver)
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()" + ".resourceId(\"" + eleResourceId
						+ "\")).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"));");

	}

	/**
	 * scroll screen up. Start point element has to be passed as the argument. And
	 * scroll up fraction is for end point scroll.
	 */
	public void scrollUpScreen(MobileElement ele, double scrollUpFraction) {
		Dimension size = driver.manage().window().getSize();
		Point locationSendHistory = ele.getLocation();
		int y_cor_SendHistory = locationSendHistory.getY();

		int endPoint = (int) (y_cor_SendHistory - (y_cor_SendHistory * scrollUpFraction));
		Log.INFO("end point is " + endPoint);

		int startPoint = y_cor_SendHistory;
		Log.INFO("start point is " + startPoint);

		int anchor = (int) (size.width * 0.50);
		new TouchAction(driver).press(point(anchor, startPoint)).waitAction(waitOptions(ofMillis(3000)))
				.moveTo(point(anchor, endPoint)).release().perform();
	}

	
	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception ex) {
			Log.ERROR("Soft Keyboard not present..During HideKeyboard action");
		}
	}


}
