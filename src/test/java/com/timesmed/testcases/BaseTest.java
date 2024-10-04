package com.timesmed.testcases;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.timesmed.pages.LoginPage;
import com.timesmed.utils.ReadProp;

import io.appium.java_client.AppiumDriver;

public abstract class BaseTest {

	private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
	protected static AppiumDriver<WebElement> driver = null;

	protected static Map<String, AppiumDriver<WebElement>> driversMap = new HashMap<String, AppiumDriver<WebElement>>();
	protected LoginPage loginPage = null;
	protected ReadProp readProp = null;

	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;

	@BeforeSuite
	public void initTestData() {
		logger.info("Starting of method initTestData in BaseTest");

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;
			try {
				testDataReader = new FileReader("src/test/resources/testdata.properties");
				assertionsReader = new FileReader("src/test/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("Ending of method initTestData in BaseTest");

	}

	protected synchronized AppiumDriver<WebElement> initMobileDriver(ReadProp readProp) throws MalformedURLException {
		
		logger.info("Starting of method initMobileDriver");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", readProp.getDeviceName());
		cap.setCapability("udid", readProp.getUdId());
		cap.setCapability("platformName", readProp.getPlatformName());
		cap.setCapability("platformVersion", readProp.getPlatformVersion());

		cap.setCapability("appPackage", readProp.getAppPackage());
		cap.setCapability("appActivity", readProp.getAppActivity());

		driver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driversMap.put(readProp.getUdId(), driver);

		logger.info("Ending of method initMobileDriver ");
		return driver;
	}

	protected synchronized AppiumDriver<WebElement> getMobileDriver(String driverKey) throws Exception {
		logger.info("Starting of method getMobileDriver in BaseTest");

		driver = (AppiumDriver<WebElement>) driversMap.get(driverKey);

		// Use existing driver
		if (driver == null) {

			logger.error("Driver not initialized, Please call initDriver Method. Before calling getDriver ");
			throw new Exception("Drivier not initialized");
		
		}

		// Otherwise create new driver
		logger.info("Ending of method getMobileDriver");
		return driver;
	}


	@AfterSuite
	protected synchronized void quitMobileDriver() {
		logger.info("Starting of method quitDriver in BaseTest");

		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of method quitDriver in BaseTest");
	}
}
