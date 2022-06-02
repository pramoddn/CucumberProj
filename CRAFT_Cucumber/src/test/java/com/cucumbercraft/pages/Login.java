package com.cucumbercraft.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cucumbercraft.framework.WebDriverLaunch;

public class Login extends WebDriverLaunch {
	public Login(WebDriver driver) {
		this.driver = driver;
	}

	private final By vws_UserNameTextBox = By.name("UserNameTextBox");
	private final By vws_PasswordTextBox = By.name("PasswordTextBox");
	private final By vws_LogOnButton = By.name("LogOnButton");

	public void verify_Login(String applicationName) {
		try {
			switch (applicationName) {

			case "VWS":
				driver.findElement(vws_UserNameTextBox).sendKeys("VendorUser001");
				driver.findElement(vws_PasswordTextBox).sendKeys("mainstream2");
				driver.findElement(vws_LogOnButton).click();
				break;
			}
		} catch (Exception e) {
			Assert.assertTrue("Something went wrong login.." + e.getMessage(), false);
		}

	}
}
