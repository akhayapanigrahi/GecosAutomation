package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itc.util.BasePageObject;

public class SuperAdminRoles extends BasePageObject {
	SuperAdminRoles sadmin = null;
	WebDriverWait wait;
	public SuperAdminRoles(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean loginFlag = false;
	boolean manageFlag = false;

	public static Logger logger = Logger.getLogger(SuperAdminRoles.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By ManageTrialsLink=By.linkText("Manage Trials");
	By HomePageLink=By.linkText("Home");

	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

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
			waitExplicit(HomePageLink, 50);
			if(isElementPresent(HomePageLink)){
			clickOnHomePage();
			}
		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}


public void clickOnHomePage() throws Exception{
	
	if (isElementPresent(HomePageLink)) {
		setElement(HomePageLink).click();
		waitForAnElement(HomePageLink, 20);
		logger.info("Home Page is accessible and displayed for the Super admin user");

	}
}
public void clickOnManageTrials() throws Exception{
	
	if (isElementPresent(ManageTrialsLink)) {
		setElement(ManageTrialsLink).click();
		logger.info("Manage trials Page is accessible and displayed for the Super admin user");

	}
}




	public boolean isHomePageDisplayed() throws Exception {
		try {

			loginFlag = isElementPresent(HomePageLink);
			if (loginFlag) {
				logger.info("This user is having Super Admin access and have  access to Home page only");
			} else {
				logger.info("This user is not a Super Admin access and all the pages are visible");
			}
		} catch (Exception e) {
			throw new Exception("Home trials link is not displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return loginFlag;
	}
	
	public boolean isManagaTrialsPageDisplayed() throws Exception {
		try {

			manageFlag = isElementPresent(ManageTrialsLink);
			if (manageFlag) {
				logger.info("This user is not a Super Admin access and all the pages are visible");
			} else {
				logger.info("Trying to Search Manage trials page..............................");
				logger.info("This user is having Super Admin access and have not access to Manage trials page");

			}
		} catch (Exception e) {
			throw new Exception("Manage trials link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return manageFlag;
	}
	
	
}
