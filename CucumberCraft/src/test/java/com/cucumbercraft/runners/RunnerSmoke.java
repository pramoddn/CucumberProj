package com.cucumbercraft.runners;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.cucumbercraft.framework.Browser;
import com.cucumbercraft.framework.CustomReporter;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExecutionMode;
//import com.cucumbercraft.framework.MobileExecutionPlatform;
//import com.cucumbercraft.framework.SeleniumTestParameters;
import com.cucumbercraft.framework.Settings;
import com.cucumbercraft.framework.TimeStamp;
import com.cucumbercraft.framework.ToolName;
import com.cucumbercraft.framework.Util;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@ExtendedCucumberOptions(jsonReport = "target/cucumber-report/API/cucumber.json", jsonUsageReport = "target/cucumber-report/API/cucumber-usage.json", outputFolder = "target/cucumber-report/API", detailedReport = true, detailedAggregatedReport = true, overviewReport = true, usageReport = true)

/**
 * Please notice that com.CucumberCraft.stepDefinations.CukeHooks class is in
 * the same package as the steps definitions. It has two methods that are
 * executed before or after scenario. I'm using to take a screenshot if scenario
 * fails.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.cucumbercraft.stepdefinitions" },tags = {"@VAWSLogin"}, dryRun = false, strict = true, monochrome = true, plugin = { "pretty",
				"com.cucumber.listener.ExtentCucumberFormatter:" })

public class RunnerSmoke extends AbstractTestNGCucumberTests {

	static String resultFolder;
	Properties properties = Settings.getInstance();
	static Logger log = LoggerFactory.getLogger(RunnerSmoke.class);	 
	@BeforeSuite
	private void beforeSuite() {

		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			resultFolder = TimeStamp.getInstance();
		}
	}

	@BeforeClass
	private void beforeClass() {

		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath("target/ExtentReport/CRAFTExtent.html");
		new File("target/ExtentReport/screenshots").mkdir();
	}

	@AfterClass
	private void afterClass() {
		Properties properties = Settings.getInstance();
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("Project Name", properties.getProperty("ProjectName"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows 7");
	}

	@BeforeTest
//	private void beforeTest(ITestContext testContext) {
//		//SeleniumTestParameters testParameters = new SeleniumTestParameters();
//		try {
//			setDefaultTestParameters(testContext, testParameters);
//			DriverManager.setTestParameters(testParameters);
//		} catch (Exception ex) {
//			log.error(ex.getMessage());
//			ex.printStackTrace();
//		}
//	}

	@AfterTest
	private void afterTest() {
		generateCustomReports();
	}

	@AfterSuite()
	private void afterSuite() throws IOException {
		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			copyReportsFolder();
			CustomReporter custom=new CustomReporter();
			//custom.generateReport(xmlSuites, suites, outputDirectory);
			//EmailReport.sendEmail();
		}
	}

	@SuppressWarnings("unused")
	private void generateCustomReports() {

		CucumberResultsOverview results1 = new CucumberResultsOverview();
		results1.setOutputDirectory("target/cucumber-report/API");
		results1.setOutputName("cucumber-results");
		results1.setSourceFile("target/cucumber-report/API/cucumber.json");
		try {
			results1.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CucumberDetailedResults detailedResults = new CucumberDetailedResults();
		detailedResults.setOutputDirectory("target/cucumber-report/API");
		detailedResults.setOutputName("cucumber-results");
		detailedResults.setSourceFile("target/cucumber-report/API/cucumber.json");
		detailedResults.setScreenShotLocation("./API");
		try {
			detailedResults.executeDetailedResultsReport(false, true);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	private void copyReportsFolder() {

		File sourceCucumber = new File(Util.getTargetPath());
		File sourceExtent = new File(Util.getTargetExtentReportPath());
		File destination = new File(resultFolder);
		try {
			FileUtils.copyDirectory(sourceCucumber, destination);
			FileUtils.copyDirectory(sourceExtent, destination);
			// try {
			// FileUtils.cleanDirectory(sourceCucumber);
			// } catch (Exception e) {
			//
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TimeStamp.reportPathWithTimeStamp = null;
	}

	//private void setDefaultTestParameters(ITestContext testContext, SeleniumTestParameters testParameters) {
//
//		try {
//			String executionMode = testContext.getCurrentXmlTest().getLocalParameters().get("ExecutionMode");
//			String toolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//
//			isMobileExecution(executionMode, toolName, testParameters);
//			isAPIExecution(executionMode, toolName, testParameters);
//
//			switch (executionMode) {
//
//			case "API":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				break;
//
//			case "LOCAL":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//
//				} else {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
//				}
//
//				break;
//
//			case "GRID":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//
//				} else {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
//					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));
//
//				} else {
//					testParameters.setBrowserVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
//					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
//
//				} else {
//					testParameters
//							.setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
//				}
//
//				break;
//			case "MOBILE":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
//					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
//				} else {
//					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
//				}
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				break;
//
//			case "PERFECTO":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
//					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
//				} else {
//					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
//				}
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				// For One Lab Integration - Desktop Browser Execution
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//
//				} else {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
//					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));
//
//				} else {
//					testParameters.setBrowserVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
//					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
//
//				} else {
//					testParameters
//							.setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
//				}
//
//				break;
//
//			case "SEETEST":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//				testParameters.setMobileToolName(ToolName.valueOf("DEFAULT"));
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("SeeTestPort") == null) {
//					testParameters.setSeeTestPort(properties.getProperty("SeeTestDefaultPort"));
//
//				} else {
//					testParameters
//							.setSeeTestPort(testContext.getCurrentXmlTest().getLocalParameters().get("SeeTestPort"));
//				}
//
//				break;
//
//			case "SAUCELABS":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
//					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
//				} else {
//					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
//				}
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
//					testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));
//
//				} else {
//					testParameters.setmobileOSVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
//				}
//
//				// For Sauce Labs Browser based execution
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//
//				} else {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
//					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));
//
//				} else {
//					testParameters.setBrowserVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
//					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
//
//				} else {
//					testParameters
//							.setBrowserVersion(testContext.getCurrentXmlTest().getLocalParameters().get("Platform"));
//				}
//
//				break;
//
//			case "FASTEST":
//
//				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
//					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
//				} else {
//					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
//				}
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
//					testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));
//
//				} else {
//					testParameters.setmobileOSVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
//				}
//
//				// For FASTEST Browser based execution
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName") == null) {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//
//				} else {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowserName")));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion") == null) {
//					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));
//
//				} else {
//					testParameters.setBrowserVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("BrowserVersion"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("Platform") == null) {
//					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
//
//				} else {
//					testParameters.setPlatform(
//							Platform.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("Platform")));
//				}
//
//				break;
//
//			case "TESTOBJECT":
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("ToolName") == null) {
//					testParameters.setMobileToolName(ToolName.valueOf(properties.getProperty("DefaultToolName")));
//				} else {
//					String mobileToolName = testContext.getCurrentXmlTest().getLocalParameters().get("ToolName");
//					testParameters.setMobileToolName((ToolName.valueOf(mobileToolName)));
//				}
//
////				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
////					testParameters.setMobileExecutionPlatform(
////							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
////				} else {
////					String mobileExecutionPlatform = testContext.getCurrentXmlTest().getLocalParameters()
////							.get("MobileExecutionPlatform");
////					testParameters
////							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
////				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName") == null) {
//					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
//
//				} else {
//					testParameters
//							.setDeviceName(testContext.getCurrentXmlTest().getLocalParameters().get("DeviceName"));
//				}
//
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
//					testParameters.setmobileOSVersion(properties.getProperty("DefautMobileOsVersion"));
//
//				} else {
//					testParameters.setmobileOSVersion(
//							testContext.getCurrentXmlTest().getLocalParameters().get("MobileOsVersion"));
//				}
//
//				break;
//
//			default:
//				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
//				if (testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName") == null) {
//					testParameters.setBrowser(
//							Browser.valueOf(testContext.getCurrentXmlTest().getLocalParameters().get("BrowerName")));
//				} else {
//					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
//				}
//			}
//
//		} catch (Exception ex) {
//			log.error(
//					"Error at settings TestParameters , please check the TestNG XML suite File and pass valid key & values"
//							+ ex.getMessage());
//		}
//	}
//
//	private void isAPIExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {
//
//		testParameters.setAPIExecution(false);
//		if (executionMode.equals(ExecutionMode.API.toString())) {
//			testParameters.setAPIExecution(true);
//		}
//	}
//
//	private void isMobileExecution(String executionMode, String toolName, SeleniumTestParameters testParameters) {
//
//		testParameters.setMobileExecution(false);
//		if (!(toolName == null)) {
//			if (toolName.equals(ToolName.APPIUM.toString())) {
//				testParameters.setMobileExecution(true);
//			}
//		}
	//}

}