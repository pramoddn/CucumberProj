package com.CucumberCraft;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class aMFM_get {
	static AppiumDriver driver = null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("appiumVersion", "1.17.1");
		desiredCapabilities.setCapability("deviceName", "iPhone XS Max Simulator");
		desiredCapabilities.setCapability("deviceOrientation", "portrait");
		desiredCapabilities.setCapability("platformVersion", "13.4");
		desiredCapabilities.setCapability("platformName", "iOS");
		desiredCapabilities.setCapability("app", "storage:edad43b8-8618-48e9-8bc3-ec995e1fa725");
		desiredCapabilities.setCapability("automationName", "XCUITest");
		driver = new IOSDriver(new URL("https://jayaprakash_cbre:8ab24b3c-c2ed-423a-9474-a405b5b1ebe1@ondemand.us-west-1.saucelabs.com:443/wd/hub"), desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		MobileElement el1 = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"main\"])[2]/XCUIElementTypeTextField");
		el1.sendKeys("344");
		//This is to bring dropdown to view 
		MobileElement el2 = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"main\"])[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]");
		el2.click();
		//Selecting the wheel and sending the language code. 
		MobileElement el3 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"iMFM-UAT\"]/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePicker/XCUIElementTypePickerWheel");
		el3.sendKeys("English");
		// Save to continue
		MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Save");
		el4.click();
		MobileElement allow = (MobileElement) driver.findElementByAccessibilityId("Allow Once");
		allow.click();
		MobileElement el5=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeTextField");
		el5.sendKeys("AutomatedTester");
		MobileElement el6=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeSecureTextField");
		el6.sendKeys("");
		el6.sendKeys("Automation!2020");
		MobileElement el7=(MobileElement) driver.findElementByAccessibilityId("Login");
		el7.click();
		Thread.sleep(5000);
		MobileElement el8=(MobileElement) driver.findElementByAccessibilityId("Menu");
		el8.click();
		MobileElement el9=(MobileElement) driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='Create Work Order fo...'])[1]");
		el9.click();
		MobileElement el10=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]");
		el10.click();
		MobileElement el11=(MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='iMFM-UAT']/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePicker/XCUIElementTypePickerWheel");
		el11.sendKeys("Employee");
		MobileElement done=(MobileElement) driver.findElementByAccessibilityId("Done");
		done.click();
		MobileElement el12=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]");
		el12.click();
		MobileElement el13=(MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='iMFM-UAT']/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePicker/XCUIElementTypePickerWheel");
		el13.sendKeys("Automated Tester");
		done.click();
		MobileElement el14=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeTextField");
		el14.sendKeys("%%%");
		MobileElement el15=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[3]");
		el15.click();
		MobileElement el16=(MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='iMFM-UAT']/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePicker/XCUIElementTypePickerWheel");
		el16.sendKeys("Automated Testing Location");
		MobileElement el17=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeTextField");
		el17.sendKeys("Automated");
		Thread.sleep(200);
		MobileElement el18=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeTextView");
		el18.sendKeys("Test work order for release verification. Please disregard");
		done.click();
		MobileElement el19=(MobileElement) driver.findElementByAccessibilityId("Submit");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement) el19).getId());
		js.executeScript("mobile: swipe", params);
		MobileElement el20=(MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[7]/XCUIElementTypeOther/XCUIElementTypeOther[3]");
		el20.click();
		MobileElement el21=(MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='iMFM-UAT']/XCUIElementTypeWindow[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypePicker/XCUIElementTypePickerWheel");
		el21.sendKeys("SelfGen");
		done.click();
		Thread.sleep(100);
		el19.click();
		aMFM_get.waitUntilElementLocated(By.xpath("//*[contains(@value, 'WO # ')]"), 60);
		MobileElement el22=(MobileElement) driver.findElementByXPath("//*[contains(@value, 'WO # ')]");
		System.out.println("hii"+el22.getText());
	}
	public static  void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.presenceOfElementLocated(by));
	}
	
}
