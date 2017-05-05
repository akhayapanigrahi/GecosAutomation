package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.itc.util.BasePageObject;

public class LoginPage extends BasePageObject {
	LoginPage objLoginPage = null;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	public static Logger logger = Logger.getLogger(LoginPage.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By AppConfigLink=By.linkText("App Config");

	public boolean isLoginPageDisplayed() throws Exception {
		try {
			flag = isElementPresent(login);
			if (flag) {
				logger.info("Login Page is displayed");
			} else {
				logger.info("Login Page is not displayed");

			}
		} catch (Exception e) {
			throw new Exception("Login page is displayed::" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}

	public void enterEmailAddr(String username) throws Exception {
		if (isElementPresent(userEmailAddr)) {
			setElement(userEmailAddr).sendKeys(username);
			Assert.assertTrue(isElementPresent(userEmailAddr), "Email textbox is not displayed");

		}
	}

	public void enterPassword(String Password) throws Exception {
		if (isElementPresent(userPwd)) {
			setElement(userPwd).sendKeys(Password);
			Assert.assertTrue(isElementPresent(userPwd), "Password textbox is not displayed");

		}

	}

	public void clickLogin() throws Exception {
		if (isElementPresent(login)) {
			setElement(login).click();
		}
	}

	public void LoginAsUser(String AdminUser, String AdminPassword) throws Exception {
		try {
			enterEmailAddr(AdminUser);
			enterPassword(AdminPassword);
			clickLogin();
			waitImplicit();
			getPageTitle();
			waitForAnElement(AppConfigLink, 20);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}


	public boolean isLandingPageDisplayed() throws Exception {
		try {

			flag1 = isElementPresent(AppConfigLink);
			if (flag1) {
				logger.info("This user is having Admin access and have access to App Config and other links");
			} else {
				logger.info("This user is not having Admin access and only Home page access is available");
			}
		} catch (Exception e) {
			throw new Exception("Home Link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}
}
