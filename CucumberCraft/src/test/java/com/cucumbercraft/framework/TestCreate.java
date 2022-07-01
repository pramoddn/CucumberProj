package com.cucumbercraft.framework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreate {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\java\\TestData\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		WebDriverUtil webutil=new WebDriverUtil(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://tst.mainstreamsasp.com/WEB00344CRS/LogOn.aspx");
		By sir_UserNameTextBox = By.name("UserNameTextBox");
		By sir_PasswordTextBox = By.name("PasswordTextBox");
		By sir_LogOnButton = By.name("LogOnButton");
		driver.findElement(sir_UserNameTextBox).clear();
		driver.findElement(sir_UserNameTextBox).sendKeys("AutomatedTester@mail.com");
		driver.findElement(sir_PasswordTextBox).clear();
		driver.findElement(sir_PasswordTextBox).sendKeys("Automation!2020");
		driver.findElement(sir_LogOnButton).click();
		By request = By.xpath("//input[@id='ctl00_CreateOrderButton']");
		By publicrequest =By.xpath("//a[contains(text(),'Facility Maintenance Request')]");
		 webutil.waitUntilElementEnabled(request,30,driver);
		 webutil.clickWithjs(request,driver);
		 webutil.waitUntilElementEnabled(publicrequest,10,driver);
		 webutil.clickWithjs(publicrequest,driver);
		 By profile_name=By.xpath("//span[text()='Profile Name:']");
		 webutil.waitUntilElementLocated(profile_name, 10, driver);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		 By Service_Request_Group=By.xpath("//select[@name=\"ctl00$MainContentPlaceHolder$EquipmentGroupDropDownList\"]");
		 webutil.waitUntilElementLocated(Service_Request_Group, 30, driver);
		 WebElement equipment_group_select=driver.findElement(Service_Request_Group);
		 webutil.selectValue(equipment_group_select, "Automated Testing");
		 By wait=By.xpath("//select[@name='ctl00$MainContentPlaceHolder$EquipmentGroupDropDownList']/option[2]");
		 webutil.waitUntilElementLocated(wait, 30, driver);
		 webutil.scrollToPageEnd(driver);
		 By problemdesc=By.xpath("//textarea[@name='ctl00$MainContentPlaceHolder$ctl23$DescriptionRequestTextBox' or @id='ctl00_MainContentPlaceHolder_ctl23_DescriptionRequestTextBox']");
		 By createvalue=By.xpath("//input[@value='Create']");
			driver.findElement(problemdesc).sendKeys("Test work order for release verification. Please disregard.");
			driver.findElement(createvalue).click();
		By waitele=By.xpath("//input[@value='Enter Feedback']");
		webutil.waitUntilElementEnabled(waitele, 60, driver);
		By success_id=By.xpath("//span[@id='ctl00_MainContentPlaceHolder_WorkOrderNumberLabel']");
		System.out.println("Success id is:"+driver.findElement(success_id).getText());
			
	}

}
