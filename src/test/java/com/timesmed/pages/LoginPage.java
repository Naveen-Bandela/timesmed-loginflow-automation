package com.timesmed.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.timesmed.testcases.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class LoginPage extends BaseTest {

	protected static AppiumDriver<WebElement> driver;
	protected static WebDriverWait wait;
	protected static TouchAction touchAction;

	private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	
	// The below xpath for the WebElements on the application

	@FindBy(xpath = "//android.view.View[@content-desc='LOGIN']")
	private WebElement lblLogin;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='']")
	private WebElement txtPhoneNumber;

	@FindBy(xpath = "//android.widget.EditText[@resource-id=''][2]")
	private WebElement txtPassword;

	@FindBy(xpath = "//android.widget.Button[@resource-id=''][2]")
	private WebElement btnLogin;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"DASHBOARD\r\n"
			+ "Tab 5 of 10\"]")
	private WebElement tabDashboard;
	
	

	public LoginPage(AppiumDriver<WebElement> driver) {

		logger.info("Starting of LoginPage Page ");
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		touchAction = new TouchAction<>(driver);
		
		logger.info("Ending of LoginPage Page");

	}

	public String getLoginLabel(String lblLogin) {
		logger.info("Starting of getLoginLabel Method");
		logger.info("Starting of getLoginLabel Method");
	
		return lblLogin;
	}

	public void setPhoneNumber(String phoneNumber) {
		logger.info("Starting of setPhoneNumber Method");

		txtPhoneNumber.click();
		this.txtPhoneNumber.sendKeys(phoneNumber);

		logger.info("Ending of setPhoneNumber Method");
	}

	public void setPassword(String password) {
		logger.info("Starting of setPassword Method");

		 txtPassword.click();
		this.txtPassword.sendKeys(password);

		logger.info("Ending of setPassword Method");

	}

	public void clickonLoginButton() throws InterruptedException {
		
		logger.info("Starting of clickonLoginButton Method");

		Thread.sleep(2000);
		btnLogin.click();

		logger.info("Ending of clickonLoginButton Method");

	}
	public String getDashboardTabName(String dashboardTab) {
		
		logger.info("Starting of getDashboardTabName Method");
		logger.info("Ending of getDashboardTabName Method");
		return dashboardTab;
	}

}
