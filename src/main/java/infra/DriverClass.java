package infra;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utility.Log;


/**
 * To grab the driver Session at any time. Passed
 * parameter will invoke the new session if driver is null;
 */

public class DriverClass extends Init {
	// public AppiumDriver<MobileElement> driver;

	public AppiumDriver<MobileElement> driver;
	public AppiumDriver<MobileElement> driver(String deviceName, String PlatformVersion, String udid) {
		Log.INFO("Inside Driver Class");
		if (getDriver() == null) {
			Log.INFO("driver is null");
			driver = invokeDriver(deviceName, PlatformVersion, udid);

		} else {
			getDriver().launchApp();
		}

		Log.INFO("Session Returned by Driver: " + getDriver().getSessionId().toString());
		return getDriver();

	}

}