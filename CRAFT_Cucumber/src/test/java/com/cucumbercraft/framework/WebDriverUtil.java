package com.cucumbercraft.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//import com.cucumbercraft.pages.MobileAmfmAndroid10;
//import com.cucumbercraft.pages.MobileAmfmAndroid11;
//import com.cucumbercraft.pages.MobileAmfmBasePageAndroidNative;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * Class containing useful WebDriver utility functions
 * 
 * @author Cognizant
 */
public class WebDriverUtil {
	private WebDriver driver;
	private static Properties properties;

	/**
	 * Constructor to initialize the {@link WebDriverUtil} object
	 * 
	 * @param driver The {@link WebDriver} object
	 */
	public WebDriverUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Function to pause the execution for the specified time period
	 * 
	 * @param milliSeconds The wait time in milliseconds
	 */
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is visible
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementVisible(By by, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitUntilWebElementVisible(WebElement element, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Function to wait until the specified element is enabled
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementEnabled(By by, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitUntilElementSelected(By by, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeSelected(by));
	}

	/**
	 * Function to wait until the specified element is disabled
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
				.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
	}

	/**
	 * Function to select the specified value from a listbox
	 * 
	 * @param by   The {@link WebDriver} locator used to identify the listbox
	 * @param item The value to be selected within the listbox
	 */
	public void selectListItem(By by, String item, WebDriver driver) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(item);
	}

	/**
	 * Function to select the specified value from a listbox by index
	 */

	public void selectListItem_Index(By by, int index, WebDriver driver) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByIndex(index);
	}

	/**
	 * Function to select the specified value from a listbox
	 * 
	 * @param by   The {@link WebDriver} locator used to identify the listbox
	 * @param item The value to be selected within the listbox
	 */
	public void selectListItem_Value(By by, String item, WebDriver driver) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByValue(item);
	}

	/**
	 * Function to verify whether the specified object exists within the current
	 * page
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @return Boolean value indicating whether the specified object exists
	 */
	public Boolean objectExists(By by) {
		if (driver.findElements(by).size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Function to verify whether the specified text is present within the current
	 * page
	 * 
	 * @param textPattern The text to be verified
	 * @return Boolean value indicating whether the specified test is present
	 */
	public Boolean isTextPresent(String textPattern) {
		if (driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to check if an alert is present on the current page
	 * 
	 * @param timeOutInSeconds The number of seconds to wait while checking for the
	 *                         alert
	 * @return Boolean value indicating whether an alert is present
	 */
	public Boolean isAlertPresent(long timeOutInSeconds, WebDriver driver) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	/**
	 * Function to check if title is present on the current page
	 * 
	 * @param timeOutInSeconds The number of seconds to wait while checking for the
	 *                         title
	 * @return Boolean value indicating whether an title is present
	 */

	public Boolean titleOfthepage(long timeOutInSeconds, String title) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.titleContains(title));
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	/*
	 * Function to scroll to that particular element
	 */
	public void clickWithjs(By ele, WebDriver driver) {
		WebElement element = driver.findElement(ele);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/*
	 * Function returns 0 if no option is their in the select box
	 */
	public int checkselectVisibility(By selVis, WebDriver driver) {
		Select drpCountry = new Select(driver.findElement(selVis));
		List<WebElement> checkWeb = drpCountry.getOptions();
		return checkWeb.size();
	}

	/*
	 * Function scroll down to the page end
	 */
	public void scrollToPageEnd(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/*
	 * Function scroll to the page end
	 */
	public void scrollToPageup(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	/*
	 * Function to write text in textfile
	 */
	public void writeTextFile(String succesno) throws IOException {
		FileWriter fileWriter = new FileWriter(switch_path("testdata.txt"));
		fileWriter.write("");
		fileWriter.write(succesno);
		fileWriter.close();
	}

	/*
	 * Function to read text in textfile
	 */
	public String readText() throws IOException {
		File file = new File(switch_path("testdata.txt"));
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) {
			return st;
		}
		return null;
	}

	/*
	 * Function for decoding the encoded base64 password
	 */
	public String getEncodeValue(String getVale) {
		Base64.Decoder decoder = Base64.getDecoder();
		String dStr = new String(decoder.decode(getVale));
		return dStr;
	}

	/*
	 * Function for selecting last index in the drop down
	 */
	public void selectLastindex(WebElement ele) {
		Select SPidDDL_obj = new Select(ele);
		int selectOptionsSPidDDL = SPidDDL_obj.getOptions().size();
		SPidDDL_obj.selectByIndex(selectOptionsSPidDDL - 1);
	}

	/*
	 * Function for check the visibility
	 */
	public Boolean isElementVisible(By locator, WebDriver driver) {
		Boolean isPresent = driver.findElements(locator).size() > 0;
		return isPresent;
	}

	// get the url of the application
	public String getUrl(String apk, String release, String env) throws ParseException {
		// Equivalent command conversion for Java execution
		String uri = null;
		if (env.equalsIgnoreCase("qa") || env.equalsIgnoreCase("uat")) {
			uri = "https://api.appcenter.ms/v0.1/apps/Service-Insight/" + apk + release;
		
		} else {
			if (apk.equalsIgnoreCase("aMFM")) {
				uri = "https://api.appcenter.ms/v0.1/apps/Service-Insight/Prod-aMFM/releases/latest";
			} else if (apk.equalsIgnoreCase("SI-Request_Android")) {
				uri = "https://api.appcenter.ms/v0.1/apps/Service-Insight/Prod-SI-Request/releases/latest";

			}
		}
		String inurl = "";
		try {
			HashMap mp = new HashMap<String, String>();
			mp.put("X-API-Token", "f959ce4666b6a489b3600f4f69350981918ce1b7");
			CloseableHttpResponse response = getWithHader(uri, mp);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			Object obj = new JSONParser().parse(responseString.toString());
			JSONObject jo = (JSONObject) obj;
			inurl = (String) jo.get("install_url");
			return inurl;
		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return inurl;
	}

	/*
	 * Get value from json by key
	 */
	public String getValueJson(String path, String key) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject.get(key).toString();
	}

	/*
	 * Get value from json by master key and key
	 */
	public String getValueJson(String path, String masterkey, String key) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject ob1 = (JSONObject) jsonObject.get(masterkey);
		return ob1.get(key).toString();
	}

	/*
	 * Get value of DB that is used
	 */
	public String getDB(){
		Properties properties= Settings.getInstance();
		if(properties.getProperty("Execution").equalsIgnoreCase("Local")) {
			return properties.getProperty("LocalDatabase");
		}
		else {
			return System.getenv("DatabaseId");
		}
	}

	/*
	 * Get String value from json by path and key
	 */
	public String getValueFromTagName(String path,String keyName){
		String equipmentPath=switch_path(path);
		String selection = null;
		try {
			selection = getValueJson(equipmentPath,getDB(), keyName);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error in reading value's from "+equipmentPath+" using the key "+keyName);
		}
		return selection;
	}

	/*
	 * Get JSONObject from Path and master key
	 */
	public JSONObject getJsonObject(String path, String masterkey) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject ob1 = (JSONObject) jsonObject.get(masterkey);
		return ob1;
	}

	/*
	 * Delete any file
	 */
	public boolean delete(String path) {
		File file = new File(path);
		if (file.delete()) {
			return true;
		} else if (file.exists() == false) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Create a json
	 */
	public void create_json(JSONObject jsonObject, String name) {
		try {
			String path = System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\";
			FileWriter file = new FileWriter(path + name);
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Get url,userid,password from application
	 */
	public String get_Database_url(String application, String database, String env) {
		// HashMap<String, String> getDetails = new HashMap<String, String>();
		String url = null;
		String typeenv = null;
		String envtype = "";
		if (database.equalsIgnoreCase("526")) {
			typeenv = "TST";
			envtype = "tst";
		} else if (env.equalsIgnoreCase("qa") || env.equalsIgnoreCase("uat")) {
			typeenv = "WEB";
			envtype = "tst";
		} else {
			typeenv = "PRD";
			envtype = "www";
		}
		if (application.equalsIgnoreCase("cfg")) {
			url = "https://" + envtype + ".mainstreamsasp.com/" + typeenv + "00" + database + "Cfg/LogOn.aspx";
		} else if (application.equalsIgnoreCase("crs")) {
			url = "https://" + envtype + ".mainstreamsasp.com/" + typeenv + "00" + database + "CRS/LogOn.aspx";
		} else if (application.equalsIgnoreCase("vws")) {
			url = "https://" + envtype + ".mainstreamsasp.com/" + typeenv + "00" + database + "VWS/LogOn.aspx";
		} else if (application.equalsIgnoreCase("simobile")) {
			url = database;
		} else if (application.equalsIgnoreCase("amfm")) {
			url = database;
		}
		return url;
	}

	// get call with header
	public CloseableHttpResponse getWithHader(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		return closebaleHttpResponse;

	}

	// select value by name mobile
	public void selectValue(WebElement element, String selectText) {
		Select assobj = new Select(element);
		assobj.selectByVisibleText(selectText);
	}

	// clean the txtdata file
	public void cleandata() throws FileNotFoundException {
		cleandataTXTFile("testdata.txt");
	}

	// get integer from string
	public String extractInt(String str) {
		// Replacing every non-digit number
		// with a space(" ")
		str = str.replaceAll("[^\\d]", " ");

		// Remove extra spaces from the beginning
		// and the ending of the string
		str = str.trim();

		// Replace all the consecutive white
		// spaces with a single space
		str = str.replaceAll(" +", " ");

		if (str.equals(""))
			return "-1";

		return str;
	}

	public boolean waitForJSandJQueryToLoad(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	// get the date as per requirement
	public String getCurrentdate() {
		SimpleDateFormat formatterdd = new SimpleDateFormat("dd");
		SimpleDateFormat formattermm = new SimpleDateFormat("MM");
		SimpleDateFormat formatteryyy = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int datet = Integer.parseInt(formatterdd.format(date).toString());
		int mmt = Integer.parseInt(formattermm.format(date).toString());
		int yyt = Integer.parseInt(formatteryyy.format(date).toString());
		String fulldate = String.valueOf(mmt) + "/" + String.valueOf(datet) + "/" + String.valueOf(yyt);
		return fulldate;
	}

	// return json object
	public void getJson(String city, String building, String floor, String room) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("City", city);
		obj.put("Building", building);
		obj.put("Floor", floor);
		obj.put("Room", room);
		writeTextFile(obj.toJSONString());
	}

	// get json value from json string through key
	public String getdata(String jdata, String keyget) throws ParseException {
		Object obj;
		obj = new JSONParser().parse(jdata);
		JSONObject jo = (JSONObject) obj;
		String getvalue = (String) jo.get(keyget);
		return getvalue;
	}

	// get Date in the format
	public String getDate(String pattern) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String strDate = formatter.format(date);
		return strDate;
	}

	// get selected option values
	public String getSelectedOption(By by, WebDriver driver) {
		Select selDrop = new Select(driver.findElement(by));
		WebElement option = selDrop.getFirstSelectedOption();
		String defaultItem = option.getText();
		return defaultItem;
	}

	// Used to clear textbox
	public void clearTextBox(WebElement element) throws Exception {
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}

	// wait until attribute to change
	public void waitUntilattibuteChange(By by, long timeOutInSeconds, WebDriver driver, String attribute,
			String value) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.attributeToBe(by, attribute, value));
	}

	/**
	 * Function to identify and switch modes in the mobile application
	 */
	public Object getContexts(AppiumDriver driver, int index) {
		Set<String> contextNames = driver.getContextHandles();
		return contextNames.toArray()[index];
	}

	/**
	 * Function to wait until text to be present in the textbox
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilTexttopresent(By by, long timeOutInSeconds, WebDriver driver, String text) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.textToBePresentInElementValue(by, text));
	}

	/**
	 * Function to wait until the iframe to be displayed and switch to
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntiliframe(long timeOutInSeconds, String frameid_name, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds))
				.until(ExpectedConditions.not(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameid_name)));
	}

	/**
	 * Wait until the element to invisible
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementnotVisible(By by, long timeOutInSeconds, WebDriver driver) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Windows mac conversion
	 */
	public String switch_path(String filename) {
		String fullpath = "";
		properties = Settings.getInstance();
		if (properties.getProperty("DefaultPlatform").equalsIgnoreCase("WINDOWS")) {
			fullpath = System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\" + filename;
		} else {
			fullpath = "src/test/java/TestData/" + filename;
		}
		return fullpath;
	}

	/* Scroll to particular element in iOS */
	public void scroll_element_ios(AppiumDriver mobiledriver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) mobiledriver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: swipe", params);
	}

	/* Click menu in imfm */
	public void click_menu(AppiumDriver mobiledriver, MobileElement element, String menuname) {
		JavascriptExecutor js = (JavascriptExecutor) mobiledriver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", element.getId());
		scrollObject.put("toVisible", menuname);
		js.executeScript("mobile:scroll", scrollObject);
		// Swipe and select
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "up");
		params.put("element", element.getId());
		js.executeScript("mobile: swipe", params);
	}

	/* To handle confirmationPopup */
	public void handle_confirmPopup() {
		if (isAlertPresent(30, driver) == true) {

			driver.switchTo().alert().accept();

		}
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void waitForJQueryLoad(WebDriver mobileDriver) {
		try {
			ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) mobileDriver)
					.executeScript("return jQuery.active") == 0);

			boolean jqueryReady = (Boolean) ((JavascriptExecutor) mobileDriver)
					.executeScript("return jQuery.active==0");

			WebDriverWait jsWait = new WebDriverWait(mobileDriver, 10);
			if (!jqueryReady) {
				jsWait.until(jQueryLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	@SuppressWarnings("rawtypes")
	public void performVerticalSwipeOperation(AppiumDriver mobiledriver) {
		mobiledriver.context(getContexts(mobiledriver, 0).toString());
		Dimension dimension = mobiledriver.manage().window().getSize();
		int start_x = (int) (dimension.width * 0.01);
		int start_y = (int) (dimension.height * 0.8);
		int end_x = (int) (dimension.width * 0.01);
		int end_y = (int) (dimension.width * 0.2);
		TouchAction touch = new TouchAction(mobiledriver);
		touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(end_x, end_y)).release().perform();

	}

	// clean the txtdata file
	public void cleandataTXTFile(String fileName) throws FileNotFoundException {
		File file = new File(switch_path(fileName));
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}

	/*
	 * Function to write text in textfile
	 */
	public void writeDataTextFile(String fileName, String succesno) throws IOException {
		FileWriter fileWriter = new FileWriter(switch_path(fileName));
		fileWriter.write("");
		fileWriter.write(succesno);
		fileWriter.close();
	}

	/*
	 * Function to read text in textfile
	 */
	public String readDataText(String fileName) throws IOException {
		File file = new File(switch_path(fileName));
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) {
			return st;
		}
		return null;
	}

	/*
	 * Function to get unix timestamp
	 */
	public long getUnixTime(){
		long unixTime = System.currentTimeMillis() / 1000L;
		return unixTime;
	}

	public void performDownSwipe(AppiumDriver mobiledriver) {
		mobiledriver.context(getContexts(mobiledriver, 0).toString());
		Dimension dimension = mobiledriver.manage().window().getSize();
		int end_x = (int) (dimension.width * 0.01);
		int end_y = (int) (dimension.height * 0.8);
		int start_x = (int) (dimension.width * 0.01);
		int start_y = (int) (dimension.width * 0.2);
		TouchAction touch = new TouchAction(mobiledriver);
		touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(end_x, end_y)).release().perform();

	}

//	public MobileAmfmBasePageAndroidNative getAndroidNativeFunctions(AppiumDriver mobileDriver) throws Exception {
//		MobileAmfmBasePageAndroidNative mobileAmfmBasePageAndroidNative;
//		String androidOS = (String) mobileDriver.getCapabilities().getCapability("platformVersion");
//		if("11".equals(androidOS)){
//			//mobileAmfmBasePageAndroidNative = new MobileAmfmAndroid11(mobileDriver);
//		} else if("10".equals(androidOS)) {
//			//mobileAmfmBasePageAndroidNative = new MobileAmfmAndroid10(mobileDriver);
//		}else {
//			throw new Exception(" Unknown Android version '"+ androidOS+"'");
//		}
//		return mobileAmfmBasePageAndroidNative;
//	}

    public void writeOpenWOTextFile(String woNumber) throws IOException {
		FileWriter fileWriter = new FileWriter(switch_path("openWO.txt"));
		cleandataTXTFile("openWO.txt");
		fileWriter.write("");
		fileWriter.write(woNumber);
		fileWriter.close();
    }
}