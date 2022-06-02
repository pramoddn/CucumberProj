package com.cucumbercraft.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.cucumbercraft.framework.WebDriverLaunch;
import com.cucumbercraft.framework.WebDriverUtil;

public class HomePage extends WebDriverLaunch {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By home = By.name("HomeButton");
	By Menu = By.xpath("//*[@id='ctl00_TopMenu']/tbody/tr/td/table/tbody/tr/td/a");
	WebDriverUtil webutil = new WebDriverUtil(driver);

	public void verify_Home() {
		if (webutil.isElementVisible(home, driver)) {
			webutil.waitUntilElementEnabled(home, 30, driver);
			driver.findElement(home).click();
		}

	}

	public void verify_tabs() {
		List<WebElement> Menubar = driver.findElements(Menu);
		for (int i = 1; i <= Menubar.size(); i++) {
			WebElement Menubars = driver
					.findElement(By.xpath("(//*[@id='ctl00_TopMenu']/tbody/tr/td/table/tbody/tr/td/a)[" + i + "]"));
			Menubars.isDisplayed();
			String Tab_Value = Menubars.getText();
			if (Tab_Value.equalsIgnoreCase("HOME")) {
				System.out.println("HOME tab is present");
			}
			if (Tab_Value.equalsIgnoreCase("Open Orders Today")) {
				System.out.println("Open Orders Today tab is present");
			}
			if (Tab_Value.equalsIgnoreCase("Closed Orders")) {
				System.out.println("Closed Orders is present");

			}
			if (Tab_Value.equalsIgnoreCase("Vendor Profile")) {
				System.out.println("Vendor Profile is present");
			}
			if (Tab_Value.equalsIgnoreCase("Service Center Lookup")) {
				System.out.println("Service Center Lookup tab is present");
			}
			if (Tab_Value.equalsIgnoreCase("Invoice Review")) {
				System.out.println("Invoice Review tab is present");
			}
			if (Tab_Value.equalsIgnoreCase("Import and Export")) {
				System.out.println("Import and Export tab is present");
			}
			if (Tab_Value.equalsIgnoreCase("Complete Work Orders")) {
				System.out.println("Complete Work Orders tab is present");
			}
		}
	}
}
