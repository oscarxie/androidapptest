package appium.demo.androidapptest;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;

public class ToastTest {
	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception {
		// Install and open application
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "app/");
		File app = new File(appDir, "app-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "Android");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.example.android.apis");
		capabilities.setCapability("appActivity", ".ApiDemos");
		capabilities.setCapability("automationName", "UiAutomator2");
		// capabilities.setCapability("unicodeKeyboard", true);
		// capabilities.setCapability("resetKeyboard", true);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void testToast() throws InterruptedException {
		driver.findElementByXPath("//android.widget.TextView[@text='App']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//android.widget.TextView[@text='Notification']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//android.widget.TextView[@text='NotifyWithText']").click();
		Thread.sleep(1000);
		driver.findElementById("com.example.android.apis:id/long_notify").click();
		WebElement toast = driver.findElementByXPath("//android.widget.Toast");
		System.out.println(toast.getText());
		Assert.assertEquals("This is a long notification. See, you might need a second more to read it.",
				toast.getText());

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
