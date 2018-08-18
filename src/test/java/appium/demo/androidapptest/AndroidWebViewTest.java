package appium.demo.androidapptest;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidWebViewTest {
	 private AppiumDriver<WebElement> driver;

	    @Before
	    public void setUp() throws Exception {
	        // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        File appDir = new File(classpathRoot, "app/");
	        File app = new File(appDir, "selendroid-test-app.apk");
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("deviceName","Android Emulator");
	        capabilities.setCapability("platformVersion", "7.1");
			capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("automationName","Appium");
	        capabilities.setCapability("app", app.getAbsolutePath());
	        capabilities.setCapability("appPackage", "io.selendroid.testapp");
	        capabilities.setCapability("appActivity", ".HomeScreenActivity");
	        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    }
	    @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }

	    @Test
	    public void webView() throws InterruptedException {
	        WebElement button = driver.findElement(By.id("buttonStartWebview"));
	        button.click();
	        Thread.sleep(6000);
	        Set<String> contextNames = driver.getContextHandles();
	        for (String contextName : contextNames) {
	          System.out.println(contextName);
	          if (contextName.contains("WEBVIEW")){
	            driver.context(contextName);
	          }
	        }
	        WebElement inputField = driver.findElement(By.id("name_input"));
	        inputField.clear();
	        inputField.sendKeys("Some name");
	    }
}
