package appium.demo.androidapptest;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class UnLockTest {
	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception {
		// Install and open application
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "app/");
		File app = new File(appDir, "Locker.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		/*
		 * capabilities.setCapability("unicodeKeyboard", true);
		 * capabilities.setCapability("resetKeyboard", true);
		 */
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void GustureLockerTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		MobileElement button = (MobileElement) driver
				.findElementByAndroidUIAutomator("new UiSelector().text(\"设置手势密码\")");
		// button.tap(1, 1000);
		button.click();
		// get all the items of gesture locker
		List<MobileElement> items = driver.findElementsByClassName("android.widget.ImageView");

		for (MobileElement item : items) {
			/**
			 * 0 1 2 3 4 5 6 7 8
			 */
			item.click();
		}

		// create a Z from 0->1->2->4->6->7->8
		@SuppressWarnings("rawtypes")
		TouchAction touches = new TouchAction(driver);
		touches.press(ElementOption.element(items.get(0))).waitAction().moveTo(ElementOption.element(items.get(1)))
				.waitAction().moveTo(ElementOption.element(items.get(2))).waitAction()
				.moveTo(ElementOption.element(items.get(4))).moveTo(ElementOption.element(items.get(6))).waitAction()
				.moveTo(ElementOption.element(items.get(7))).waitAction().moveTo(ElementOption.element(items.get(8))).release();

		touches.perform();
		Thread.sleep(1000);
		touches.press(ElementOption.element(items.get(0))).waitAction().moveTo(ElementOption.element(items.get(1))).waitAction()
				.moveTo(ElementOption.element(items.get(2))).waitAction().moveTo(ElementOption.element(items.get(4))).release();
		touches.perform();
		Thread.sleep(1000);
		String a=driver.findElementById("com.AppiumGirls.locker:id/text_tip").getText().toString();
		//System.out.println(a);
		Assert.assertEquals("与上一次绘制不一致，请重新绘制", a);
		
		//Assert.assertTrue(driver.findElementByName("与上一次绘制不一致，请重新绘制").isDisplayed());

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
