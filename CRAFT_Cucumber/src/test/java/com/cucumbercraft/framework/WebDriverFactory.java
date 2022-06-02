package com.cucumbercraft.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 * @author Cognizant
 */
public class WebDriverFactory {
	private static Properties properties;
	static Logger log = LoggerFactory.getLogger(WebDriverFactory.class);

	private WebDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the appropriate {@link WebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */

	public static WebDriver getWebDriver(Browser browser) {
		DesiredCapabilities caps=null;
		WebDriver driver = null;
		WebDriverUtil webutil=new WebDriverUtil(driver);
		properties = Settings.getInstance();
		String chromepath="";
		String firefox="";
		String iebrowser=System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\IEDriverServer.exe";
		if(properties.getProperty("DefaultPlatform").equalsIgnoreCase("WINDOWS")) 
		{
			chromepath=webutil.switch_path("chromedriver.exe");
			firefox=webutil.switch_path("geckodriver.exe");
		}
		else
		{
			chromepath=webutil.switch_path("chromedriver");
			firefox=webutil.switch_path("geckodriver.tar");
		}
		try {
			switch (browser) {
			case CHROME:
				// Takes the system proxy settings automatically
				System.setProperty("webdriver.chrome.driver", chromepath);
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				// Takes the system proxy settings automatically
				System.setProperty("webdriver.gecko.driver", firefox);
				driver = new FirefoxDriver();
				break;
			case INTERNET_EXPLORER:
				System.setProperty("webdriver.ie.driver", iebrowser);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		        capabilities.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(capabilities);
				break;
			case SAUCELABS:
				if(properties.getProperty("SauceBrowser").equalsIgnoreCase("edge"))
				{
					caps = DesiredCapabilities.edge();
				}
				else if(properties.getProperty("SauceBrowser").equalsIgnoreCase("mozila"))
				{
					caps = DesiredCapabilities.firefox();
				}
				else
				{
					caps = DesiredCapabilities.chrome();
				}
			    caps.setCapability("platform", properties.getProperty("SaucePlatform"));
			    caps.setCapability("version", properties.getProperty("SauceVersion"));
			    //caps.setCapability("screenResolution","1912x961");
			    caps.setCapability("name", DriverManager.getTestParameters().getScenarioName());
				String timeStamp = "Run_" + Util.getCurrentFormattedTime("dd-MMM-yyyy hh a");
			    caps.setCapability("build","SI-Automation, "+properties.getProperty("Execution")+" Execution at: "+timeStamp);
			    driver = new RemoteWebDriver(new URL(System.getProperty("Saucehost-key")), caps);
				break;
			default:
				throw new Exception("Unhandled browser!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;
	}

	private static DesiredCapabilities getProxyCapabilities() {
		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

		return desiredCapabilities;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param browserVersion
	 *            The browser version to be used for the test execution
	 * @param platform
	 *            The {@link Platform} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
			String remoteUrl) {

		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if (proxyRequired) {
			desiredCapabilities = getProxyCapabilities();
		} else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		return url;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
		return getRemoteWebDriver(browser, null, null, remoteUrl);
	}

}