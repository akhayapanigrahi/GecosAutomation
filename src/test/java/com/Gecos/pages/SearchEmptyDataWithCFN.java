package com.Gecos.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Gecos.util.BasePageObject;

public class SearchEmptyDataWithCFN extends BasePageObject {
	SearchEmptyDataWithCFN strial = null;

	public SearchEmptyDataWithCFN(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean startFlag=false;
	public static Logger logger = Logger.getLogger(SearchEmptyDataWithCFN.class);
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By CFNUpdateLink=By.linkText("CFN Update");
	By appNameTextEnter=By.xpath("//*[@placeholder='Application/Bundles Name']");
	By searchBtn=By.id("searchButton");
	By error_message=By.id("message");

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
			Assert.assertTrue(isElementPresent(login), "Login link is not displayed");

		}
	}
	public void LoginAsUser(String AdminUser, String AdminPassword) throws Exception {
		try {
			enterEmailAddr(AdminUser);
			enterPassword(AdminPassword);
			clickLogin();
			waitImplicit();
			getPageTitle();
			waitExplicit(CFNUpdateLink, 120);
		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}

	public void searchEmptyData() throws Exception {
		try {
			clickCFNUpdate();
			clickSearch();
			waitImplicit();

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}
	public void clickCFNUpdate() throws Exception {
		if (isElementPresent(CFNUpdateLink)) {
			setElement(CFNUpdateLink).click();
			waitExplicit(searchBtn, 60);
			Assert.assertTrue(isElementPresent(CFNUpdateLink), "CFN update link is not displayed");

		}
	}

public void clickSearch() throws Exception {
	if (isElementPresent(searchBtn)) {
		setElement(searchBtn).click();
		waitImplicit();
		Assert.assertTrue(isElementPresent(searchBtn), "Search Button link is not displayed");

	}
}
public void verifyErrorMessage() throws Exception{
	String Expected_msg="Please enter a proper CFN or Org Id. Atleast one is mandatory and should be numeric.";
	
	String Actual_Error_Msg = setElement(error_message).getText();
	
	if(Expected_msg.equals(Actual_Error_Msg)){
		logger.info("Error message is validated after clicking Search button");
	}
}

public boolean isResultDisplayed() throws Exception {
	try {

		startFlag = isElementPresent(CFNUpdateLink);
		if (startFlag) {
			searchEmptyData();
			verifyErrorMessage();
		} else {
			logger.info("Error Message is not found by clicking Search button ");
		}
	} catch (Exception e) {
		throw new Exception("Home page is displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
	}
	return startFlag;
}

	

}
