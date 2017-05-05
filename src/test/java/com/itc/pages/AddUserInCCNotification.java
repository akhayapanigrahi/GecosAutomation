package com.itc.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itc.util.BasePageObject;
import com.itc.util.ExcelutilObject;

public class AddUserInCCNotification extends BasePageObject {
	AddUserInCCNotification objLoginPage = null;
	WebDriverWait wait;
	public AddUserInCCNotification(WebDriver driver) {
		super(driver);
	}
	boolean flag=false;
	boolean configFlag=false;
	boolean userFlag=false;
	
	public static Logger logger = Logger.getLogger(AddUserInCCNotification.class);
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By AppConfiLink=By.linkText("App Config");
	By SearchTextBox=By.xpath(".//*[@id='appName']");
	By searchBtn=By.id("searchButton");
	By CCNotif_link=By.xpath("//a[contains(text(),'Cc Notifications')]");
	By CCTextBox=By.id("cctype");
	By addBtn=By.id("ccTypeButton");
	By emailList=By.xpath("//table[@id='CC']//tr//td");
	By appNameText=By.xpath("//span/b");
	
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
			waitForAnElement(AppConfiLink, 50);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}


public void selectApp(String Appname,String AppID) throws Exception{
	
	waitExplicit(SearchTextBox, 120);
	setElement(SearchTextBox).sendKeys(Appname);
	waitExplicit(searchBtn, 60);
	setElement(searchBtn).click();
	By EditApp_link=By.xpath("//a[contains(@onclick,\"submitlink('','"+AppID+"','0'\")]");
	waitExplicit(EditApp_link, 60);
	setElement(EditApp_link).click();
	
	
}	

public void addUserAsCC(String CCId, String Appname,String AppID) throws Exception{
	
	waitExplicit(AppConfiLink, 120);
	setElement(AppConfiLink).click();
	selectApp(Appname, AppID);
	waitExplicit(CCNotif_link, 120);
	setElement(CCNotif_link).click();
	waitExplicit(CCTextBox, 100);
	setElement(CCTextBox).sendKeys(CCId);
	
	if(setElement(addBtn).isEnabled()){
		
		setElement(addBtn).click();
	}
	else{
		logger.info("Add button is not enabled");
	}
	
	}

public boolean isAppConfigPageDisplayed() throws Exception {
	try {
    	configFlag = isElementPresent(AppConfiLink);
		if (configFlag) {
			logger.info("This added user is validated and have access to App config page");
		} else {
			logger.info("This user is not having Admin access and only Home page access is available");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return configFlag;

}
//Validation
	public boolean isUserDisplayed() throws Exception {
		try {

			userFlag = isElementPresent(AppConfiLink);
			
			if (userFlag) {
				
				List<WebElement> emails = driver.findElements(emailList);
				String CCId=getValFromExcel(10,3);
				for(int i=0;i<emails.size();i++){
					if(CCId.equals(emails.get(i).getText())){
						String AppNameWithID = setElement(appNameText).getText();
						logger.info("Email found in the CC list "+ "for the App " +AppNameWithID);
						break;
					}
				
					else {
						logger.info("Email not found in the CC list");

					}
				}
			}
			
		} catch (Exception e) {
			throw new Exception("Manage trials link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return userFlag;
	}
	
	public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "AppName");
		return ExcelutilObject.getCellData(row, col);
	}

}
