package appium.demo.androidapptest;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class BrowserTest {
	private AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "Android Emulator");
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "7.1");
		desiredCapabilities.setCapability("browserName", "Chrome");

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<WebElement>(remoteUrl, desiredCapabilities);
	}

	@Test
	public void testBrowser() {
		driver.get("http://www.baidu.com");

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
