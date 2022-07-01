package com.CucumberCraft;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class create_order {
	static AppiumDriver driver = null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("appiumVersion", "1.17.1");
		desiredCapabilities.setCapability("deviceName", "iPhone XS Max Simulator");
		desiredCapabilities.setCapability("deviceOrientation", "portrait");
		desiredCapabilities.setCapability("platformVersion", "13.4");
		desiredCapabilities.setCapability("platformName", "iOS");
		desiredCapabilities.setCapability("app", "storage:a8776c42-7a3d-4195-a62e-72cd36c874ac");
		desiredCapabilities.setCapability("automationName", "XCUITest");
		driver = new IOSDriver(new URL("https://jayaprakash_cbre:8ab24b3c-c2ed-423a-9474-a405b5b1ebe1@ondemand.us-west-1.saucelabs.com:443/wd/hub"), desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name='main'])[2]/XCUIElementTypeTextField")).sendKeys("344");
		Thread.sleep(7000);
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name='main'])[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]")).click();
		By pickers = MobileBy.className("XCUIElementTypePickerWheel");
		List<WebElement> pickerEls =driver.findElements(pickers);
		pickerEls.get(0).sendKeys("English");
		driver.findElement(By.id("Done")).click();
		driver.findElement(By.name("Save")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeTextField")).sendKeys("AutomatedTester@mail.com");
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeSecureTextField")).sendKeys("");
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeSecureTextField")).sendKeys("Automation!2020");
		driver.findElement(By.xpath("//XCUIElementTypeLink[@name='Login']")).click();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow Once']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Automated Test PC']")).click();
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='CBRE - Work Order Verification']/XCUIElementTypeOther/XCUIElementTypeOther[10]/XCUIElementTypeTextView")).sendKeys("test");
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Request Service']")).click();
		create_order.waitUntilElementLocated(By.xpath("//*[contains(@value, 'Request Number: ')]"), 60);
		MobileElement el22=(MobileElement) driver.findElementByXPath("//*[contains(@value, 'Request Number: ')]");
		System.out.println("hii"+el22.getText());
	}
	public static  void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.presenceOfElementLocated(by));
	}


}
