package com.timesmed.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.timesmed.pages.LoginPage;
import com.timesmed.utils.ReadProp;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

class LoginTest extends BaseTest {

	private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

	LoginPage loginPage = null;

	@BeforeClass
	@Parameters({ "deviceName", "udid", "platformName", "platformVersion", "appPackage", "appActivity" })

	public void initClass(String deviceName, String udid, String platformName, String platformVersion,
			String appPackage, String appActivity) throws Exception {
		this.readProp = new ReadProp(deviceName, udid, platformName, platformVersion, appPackage, appActivity);

		driver = this.initMobileDriver(readProp);

		this.loginPage = new LoginPage(driver);

	}

	@Test(priority = 1)
	@Description("Verify the Login functionality with Valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void VerifyLogin() throws Exception {

		logger.info("Starting of VerifyLogin Method ");

		try {

			loginPage.getLoginLabel(expectedAssertionsProp.getProperty("login.page"));

			loginPage.setPhoneNumber(testDataProp.getProperty("phoneNumber"));

			loginPage.setPassword(testDataProp.getProperty("password"));
			
			Thread.sleep(2000);
			driver.hideKeyboard();
			loginPage.clickonLoginButton();

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Ending of VerifyLogin Method");

	}

	@Test(priority = 2)
	@Description("Verify the Dashboard tab after successfull login")
	@Severity(SeverityLevel.BLOCKER)

	public void VerifyDashboardTab() {

		logger.info("Starting of VerifyLogin Method ");

		try {

			loginPage.getDashboardTabName(expectedAssertionsProp.getProperty("dashboard"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Ending of VerifyLogin Method");

	}

}