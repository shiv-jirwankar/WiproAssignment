package infra;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utility.Constant;
import utility.Log;

/**
 * 
 *
 * @author Shiv Jirwankar
 *
 */
public class Init {

	// private AppiumServiceBuilder builder;
	public static AppiumDriverLocalService server = null;

	private static ThreadLocal<AppiumDriver<MobileElement>> localDriver = new ThreadLocal<AppiumDriver<MobileElement>>();

	DesiredCapabilities capabilities = new DesiredCapabilities();
	public AppiumDriver<MobileElement> driver = null;

	/**
	 * The function is used to set Capabilities and invoke driver Server.
	 */

	public AppiumDriver<MobileElement> invokeDriver(String deviceName, String PlatformVersion, String udid) {
		try {
			capabilities.setCapability("platformVersion", PlatformVersion);
			capabilities.setCapability("udid", udid);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			Log.INFO(capabilities.toString());
			String Url = "http://127.0.0.1:4723/wd/hub";
			Log.INFO(Url);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(MobileCapabilityType.APP, Constant.APK_PATH);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.amazon.mShop.android.shopping");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					"com.amazon.mShop.splashscreen.StartupActivity");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

			capabilities.setCapability("autoGrantPermissions", "true");
			capabilities.setCapability("autoDismissAlerts", "true");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
			// capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");

			driver = new AndroidDriver<MobileElement>(new URL(Url), capabilities);
			// driver = new AndroidDriver<MobileElement>(server.getUrl(), capabilities);
			driver.manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
			// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}

		catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}

		setDriver(driver);

		Log.INFO("Message" + getDriver().getSessionId());
		return driver;
	}

	public AppiumDriver<MobileElement> getDriver() {
		return localDriver.get();
	}

	public void setDriver(AppiumDriver<MobileElement> driver) {
		localDriver.set(driver);
	}

	public String getDeviceProperty(String property) {
		property = getDriver().getCapabilities().getCapability(property).toString();
		return property;
	}

	public void quitDriver() {
		try {
			getDriver().quit();
			localDriver.set(null);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void closeDriver() {
		try {
			getDriver().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			;
		}
	}

	public void launchApp() {
		try {
			getDriver().launchApp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			;
		}
	}

}
