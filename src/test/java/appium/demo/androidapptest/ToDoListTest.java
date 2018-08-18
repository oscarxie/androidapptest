package appium.demo.androidapptest;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ToDoListTest {
	private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        // Install and open application
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "app/");
        File app = new File(appDir, "ToDoList.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    
    @Test
    public void addItem(){
        String itemText = "使用 Appium 编写测试脚本";    

        // Add new item
        WebElement editText = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/etNewItem"));
        editText.sendKeys(itemText);
        WebElement addItemBtn = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/btnAddItem"));
        addItemBtn.click();

        // Check if item is added
        List<AndroidElement> appiumItems = driver.findElementsByXPath("//android.widget.TextView[@text='"+itemText+"']");
        Assert.assertEquals("找不到待办事项 '"+itemText+"'", false, appiumItems.isEmpty());
    }
    
    @Test
    public void addItemInWeekNetwork(){
        String itemText = "模拟弱网";
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//--> Add implicitly wait

        // Add new item
        WebElement editText = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/etNewItem"));
        editText.sendKeys(itemText);
        WebElement addItemBtn = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/btnAddItem"));
        addItemBtn.click();

        // Check if item is added
        List<AndroidElement> appiumItems = driver.findElementsByXPath("//android.widget.TextView[@text='"+itemText+"']");
        Assert.assertEquals("找不到待办事项 '"+itemText+"'", false, appiumItems.isEmpty());

    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
    
}
