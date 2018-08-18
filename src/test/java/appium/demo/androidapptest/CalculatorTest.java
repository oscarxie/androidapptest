package appium.demo.androidapptest;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CalculatorTest {
	private AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "Android Emulator");
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "7.1");
		desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
		desiredCapabilities.setCapability("appActivity", ".Calculator");

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver<WebElement>(remoteUrl, desiredCapabilities);
	}

	@Test
	public void sampleTest() {
		MobileElement el1 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_1");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("plus");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_2");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("equals");
		el4.click();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
