package com.itc.pages;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.itc.util.BasePageObject;
import com.itc.util.ExcelutilObject;

public class SearchSpecialCharInAppConfig extends BasePageObject {
	SearchSpecialCharInAppConfig strial = null;

	public SearchSpecialCharInAppConfig(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean startFlag=false;
	public static Logger logger = Logger.getLogger(SearchSpecialCharInAppConfig.class);
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By appConfigLink=By.linkText("App Config");
	By appNameTextEnter=By.xpath("//*[@placeholder='Application/Bundles Name']");
	By searchBtn=By.id("searchButton");
	

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
			waitExplicit(appConfigLink, 120);
		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}

	public void searchSpecialCharacter(String character) throws Exception {
		try {
			clickAppConfig();
			enterSpecialChar(character);
			clickSearch();
			waitImplicit();
			getPageTitle();

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}
	public void clickAppConfig() throws Exception {
		if (isElementPresent(appConfigLink)) {
			setElement(appConfigLink).click();
			waitExplicit(appNameTextEnter, 60);			
		}
	}
public void enterSpecialChar(String character) throws Exception{
	
	if (isElementPresent(appNameTextEnter)) {
		setElement(appNameTextEnter).sendKeys(character);
		waitExplicit(searchBtn, 30);
	}
	
}

public void clickSearch() throws Exception {
	if (isElementPresent(searchBtn)) {
		setElement(searchBtn).click();
		waitImplicit();
		
	}
}
public void verifyDisplayedAppList() throws Exception{
	String character=getValFromExcel(5,2);
	By app_list=By.xpath("//table[@id='appTable']//td[contains(text(),'"+character+"')]");
	List<WebElement> app_Elements = driver.findElements(app_list);
	int count =0;

	try {
		for(int i=0;i<app_Elements.size();i++){

			if(app_Elements.get(i).getText().contains(character)){
				count=i+1;
				
			}
		
			else {
				logger.info("Character not found in the App name in the listed APP");

			}
			
			
		}
		logger.info("Total no of Apps with Searched Special character "+character+" found are  "+count);
	} 
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public boolean isResultDisplayed() throws Exception {
	try {

		startFlag = isElementPresent(appConfigLink);
		if (startFlag) {
			verifyDisplayedAppList();
			logger.info("App names found by searching Special Character");
		} else {
			logger.info("Error Message is found by searching Special Character ");
		}
	} catch (Exception e) {
		throw new Exception("Start Trial Link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
	}
	return startFlag;
}


	public boolean isLandingPageDisplayed() throws Exception {
		try {

			flag1 = isElementPresent(appConfigLink);
			if (flag1) {
				logger.info("This user is having Admin access and have access to Start links");
			} else {
				logger.info("This user is not having Admin access and only Home page access is available");
			}
		} catch (Exception e) {
			throw new Exception("Home Link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}
	
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "AppName");
		return ExcelutilObject.getCellData(row, col);
	}
}
