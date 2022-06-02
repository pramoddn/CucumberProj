package com.cucumbercraft.framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverLaunch  {
	protected static WebDriver driver;
	 WebDriverUtil webutil=new WebDriverUtil(driver);
	 String chromepath="";
	
	  public  void selectBrowser(String fileName ) throws Exception {
		  String firefox="";
          FileInputStream fis = null;
          File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName);
          fis = new FileInputStream(file);

          Properties prop = new Properties();
          prop.load(fis); 
          String browser = prop.getProperty("DefaultBrowser");
          while (browser == null) {
          System.out.println("Browser is not specified in Configuration file. Terminating process !!!");
          }
          if (browser.equalsIgnoreCase("firefox")) {
//        	  firefox=webutil.switch_path("geckodriver.exe");
//        	  System.setProperty("webdriver.gecko.driver", firefox);
//				
        	  WebDriverManager.firefoxdriver().setup();
        	 driver = new FirefoxDriver();		
          } 
          else if (browser.equalsIgnoreCase("CHROME")) {
//        	  chromepath=webutil.switch_path("chromedriver.exe");
//        	  	System.setProperty("webdriver.chrome.driver", chromepath);
//        	 	
        	  System.out.println("Browser selected for testing is :  Chrome");
        	  WebDriverManager.chromedriver().setup();
        	  driver = new ChromeDriver();
          } else if (browser.equalsIgnoreCase("ie")) {
//        	  String iebrowser=System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\IEDriverServer.exe";
//        	  System.setProperty("webdriver.ie.driver", iebrowser);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		        capabilities.setCapability("ignoreZoomSetting", true);
		        WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver(capabilities);
          }
         // System.out.println("Browser selected for testing is :  Internet Explorer");
          driver.manage().window().maximize();
          driver.get("https://vaws-sit-eu.ABCDapps.com/TST00432VWS/Logon.aspx");
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
          driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

         
		
          

}
}
