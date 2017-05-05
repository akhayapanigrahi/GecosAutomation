package com.itc.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.itc.util.BasePageObject;

public class AdminRoles extends BasePageObject {
	AdminRoles objLoginPage = null;
	WebDriverWait wait;
	public AdminRoles(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean loginFlag = false;
	boolean adminFlag = false;
	boolean manageFlag=false;
    boolean deleteFlag=false;
	public static Logger logger = Logger.getLogger(AdminRoles.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
    By ManageTrialsLink=By.linkText("Manage Trials");
	By HomePageLink=By.linkText("Home");
	By DeleteTrialsLink=By.linkText("Delete Trials");
	By deleteLink=By.id("DeleteTrial");

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
			waitForAnElement(ManageTrialsLink, 50);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}


	public void clickOnHomePage() throws Exception{
		
		if (isElementPresent(HomePageLink)) {
			setElement(HomePageLink).click();
			waitForAnElement(HomePageLink, 20);
			logger.info("Home Page is accessible and displayed for the Admin user");

		}
	}
	public void clickOnManageTrials() throws Exception{
		
		if (isElementPresent(ManageTrialsLink)) {
			setElement(ManageTrialsLink).click();
			logger.info("Manage trials Page is accessible and displayed for the Admin user");

		}
	}
	
	public boolean isHomePageDisplayed() throws Exception {
		try {

			loginFlag = isElementPresent(HomePageLink);
			if (loginFlag) {
				logger.info("This user is having  Admin access and have  access to Home page only");
			} else {
				logger.info("This user is not a  Admin access and all the pages are visible");
			}
		} catch (Exception e) {
			throw new Exception("Home trials link is not displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return loginFlag;
	}
	public boolean isManageTrialPageDisplayed() throws Exception {
	try {
    	manageFlag = isElementPresent(ManageTrialsLink);
		if (manageFlag) {
			logger.info("This added user is validated as Gecos Admin  and have access to Manage trials page");
		} else {
			logger.info("This user is not having Admin access and only Home page access is available");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return manageFlag;

}
	public boolean isdeleteLinkDisplayed() throws Exception{
		try {
			deleteFlag = isElementPresent(DeleteTrialsLink);
			if (deleteFlag) {
				setElement(DeleteTrialsLink).click();
				logger.info("Delete trials Page is accessible and displayed for the Admin user");
				List<WebElement> delete_Links =driver.findElements(deleteLink);
				if(delete_Links.size()==0){
					logger.info("Delete Link is not displayed as User is having only Admin access");
				}
				else{
					logger.info("This user is not having Admin access and Delete trials access is available");
					logger.info("This user is not having Admin access and only Home page access is available");					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteFlag;
	}
}
