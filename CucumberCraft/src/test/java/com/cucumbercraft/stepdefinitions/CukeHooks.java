package com.cucumbercraft.stepdefinitions;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cucumbercraft.framework.Settings;
import com.cucumbercraft.framework.TestHarness;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

//import static com.cucumbercraft.framework.SauceLabsDriverFactory.getAndroidOSVersion;

public class CukeHooks extends MasterStepDefs {

	static Logger log = LoggerFactory.getLogger(CukeHooks.class);

	static Properties propertiesFile = Settings.getInstance();
	private TestHarness testHarness;


	@Before
	public void setUp(Scenario scenario) {

		testHarness = new TestHarness();
		//Reporter.addScenarioLog(scenario.getName());
		currentScenario = scenario;
		properties = propertiesFile;
//		DriverManager.getTestParameters().setScenarioName(scenario.getName()+" - "+getOSVersionForScenario());
//		System.out.println("This is the OS Version added to test cases"+getOSVersionForScenario());
//		testHarness.invokeDriver(scenario);
	}

	//private String getOSVersionForScenario(){
//		String osVersion = getAndroidOSVersion(DriverManager.getTestParameters(), propertiesFile);
//
//		if(osVersion.equals(10) && !DriverManager.getTestParameters().getMobileExecutionPlatform().equals(MobileExecutionPlatform.ANDROID)) {
//			return "";
//		}else {
//			return osVersion;
//		}
	//}

	@After
	public void tearDown(Scenario scenario) throws IOException {

		testHarness.updateDefectInJira(scenario);
		testHarness.downloadAddtionalReports(scenario);
		//testHarness.closeRespestiveDriver(scenario);
	}

}