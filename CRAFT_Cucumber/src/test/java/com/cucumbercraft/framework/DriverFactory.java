package com.cucumbercraft.framework;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cucumbercraft.stepdefinitions.VAWSLogin;

/**
 * DriverFactory which will create respective driver Object
 * 
 * @author Cognizant
 */
public class DriverFactory {
	static Logger log;

	static {
		 //static Logger log = LoggerFactory.getLogger(DriverFactory.class);

		log = LoggerFactory.getLogger(DriverFactory.class);
		log.info("Log has been successfully intiated");
	}
	//static Logger log = LoggerFactory.getLogger(DriverFactory.class);

	

	private static Properties mobileProperties = Settings.getInstance();

	/**
	 * Function to return the object for AppiumDriver {@link AppiumDriver}
	 * object
	 * 
	 * @param testParameters
	 * 
	 * @return Instance of the {@link AppiumDriver} object
	 */
//	@SuppressWarnings("rawtypes")
//	public static AppiumDriver createAppiumInstance(SeleniumTestParameters testParameters) {
//
//		AppiumDriver driver = null;
//		try {
//			switch (testParameters.getExecutionMode()) {
//
//			case MOBILE:
//
//				driver = AppiumDriverFactory.getAppiumDriver(testParameters);
//				break;
//
//			case PERFECTO:
//
//				driver = PerfectoDriverFactory.getPerfectoAppiumDriver(testParameters);
//
//				break;
//
//			case SAUCELABS:
//
//				driver = SauceLabsDriverFactory.getSauceAppiumDriver(testParameters);
//				break;
//
//			case TESTOBJECT:
//
//				driver = SauceLabsDriverFactory.getTestObjectAppiumDriver(testParameters);
//				break;
//
//			case FASTEST:
//
//				driver = FastestDriverFactory.getMintAppiumDriver(testParameters);
//				break;
//
//			default:
//				throw new Exception("Unhandled Execution Mode!");
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			log.error(ex.getMessage());
//		}
//		return driver;
//	}

	/**
	 * Function to return the object for WebDriver {@link WebDriver} object
	 * 
	 * @param testParameters
	 * 
	 * @return Instance of the {@link WebDriver} object
	 */
	public static WebDriver createWebDriverInstance(SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case LOCAL:
				driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
				break;

			case GRID:
				driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),
						testParameters.getBrowserVersion(), testParameters.getPlatform(),
						mobileProperties.getProperty("RemoteUrl"));
				break;
				
			case MOBILE:

				//driver = AppiumDriverFactory.getRemoteWebDriver(testParameters);
				break;

			case PERFECTO:
				//driver = PerfectoDriverFactory.getPerfectoRemoteWebDriverForDesktop(testParameters);
				break;

			case SAUCELABS:
				//driver = SauceLabsDriverFactory.getSauceRemoteWebDriver(System.getProperty("Saucehost-key"),
//						testParameters.getBrowser(), testParameters.getBrowserVersion(), testParameters.getPlatform(),
//						testParameters);
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}

}