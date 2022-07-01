/**
 * 
 */
package com.cucumbercraft.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cucumbercraft.framework.WebDriverLaunch;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.pages.Login;
import com.cucumbercraft.pages.HomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author EI12673
 *
 */

public class VAWSLogin extends WebDriverLaunch {
	HomePage vawsobj = new HomePage(driver);
	WebDriverUtil webutil = new WebDriverUtil(driver);
	String chromepath = "";
	static Logger log;
	static {
		log = LoggerFactory.getLogger(VAWSLogin.class);
	}

	@Given("^browser open$")
	public void browser_open() throws Throwable {
		WebDriverLaunch basepage = new WebDriverLaunch();
		basepage.selectBrowser("Global Settings.properties");
	}

	@When("^User enters valid credentials$")
	public void user_enters_valid_credentials() throws Throwable {
		Login login = new Login(driver);
		login.verify_Login("VWS");
		log.info("User logged in with Valid credentails");
	}

	@When("^User verifies Home$")
	public void user_verifies_Home() throws Throwable {
		try {
			HomePage vawsobj = new HomePage(driver);
			vawsobj.verify_Home();
		} catch (Exception e) {
			Assert.assertTrue("Something went wrong login.." + e.getMessage(), false);
		}
		log.info("Home Button has been verified");
	}

	@Then("^Verify the tabs present in the application$")
	public void verify_the_tabs_present_in_the_application() throws Throwable {
		HomePage vawsobj = new HomePage(driver);
		vawsobj.verify_tabs();
		log.info("Verified all existing tabs");
		driver.quit();
	}
}
