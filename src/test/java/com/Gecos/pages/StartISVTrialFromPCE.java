package com.Gecos.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.Gecos.util.BasePageObject;

public class StartISVTrialFromPCE extends BasePageObject {
	StartISVTrialFromPCE strial = null;

	public StartISVTrialFromPCE(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = true;
	boolean manageFlag = false;
	public static Logger logger = Logger.getLogger(StartISVTrialFromPCE.class);
	String PCEUrl = "https://ssapps-stg15.good.com/#/apps";

	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By startTrialLink = By.xpath("//a[contains(text(),'Start trial')]");
	By searchApp = By.xpath("//input[@placeholder='Search']");
	By email_text_input = By.id("email");
	By OrgIdLink = By.xpath("//a[contains(@href,'https://sspsp-stg15.good.com/#/a/organization')]");
	By pceLogin = By.xpath("//a[contains(text(),'Log in')]");
	By texts_click = By.xpath("//ti-autocomplete-match//a[@class='ng-binding']");
	By confirmBtn = By.xpath("//button[contains(.,'Confirm')]");
	By text = By.xpath("//h1[contains(text(),'Cloud SaaS Terms of Service')]");
	By text1 = By.xpath("//h2[contains(text(),'Trial Request Acknowledged')]");
	By text2 = By.xpath("//h2[contains(text(),'Trial Started')]");

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

	public void loginAsUserInPCE(String User, String Password, String pceUrl) throws Exception {
		try {
			driver.get(pceUrl);
			waitExplicit(pceLogin, 60);
			if (isElementPresent(pceLogin)) {
				setElement(pceLogin).click();
			}

			enterEmailAddr(User);
			enterPassword(Password);
			clickLogin();
			waitExplicit(searchApp, 120);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}

	public void startIVSTrial(String appName, String email, String textToSelect) throws Exception {

		clickSearch();
		enterAppName(appName);
		waitExplicit(texts_click, 120);
		selectOptionWithText(textToSelect);
		waitExplicit(startTrialLink, 120);
		clickStartTrial();
		// waitExplicit(confirmBtn, 20);
		clickConfirmBtn();
		// waitImplicit();

	}

	public void clickSearch() throws Exception {
		if (isElementPresent(searchApp)) {
			setElement(searchApp).click();
			waitImplicit();

		}
	}

	public void enterAppName(String appName) throws Exception {
		if (isElementPresent(searchApp)) {
			setElement(searchApp).sendKeys(appName);
			waitExplicit(searchApp, 30);
		}
	}

	public void selectOptionWithText(String textToSelect) throws Exception {

		List<WebElement> ele_texts = driver.findElements(texts_click);

		for (WebElement textToClick : ele_texts) {
			logger.info("The Text to be clickable is " + textToClick.getText());
			if (textToClick.getText().contains(textToSelect)) {
				logger.info("Found the expected text " + textToSelect);
				textToClick.click();
				break;
			} else {
				System.out.println("Not finding element to click");
			}

		}

	}

	public void clickStartTrial() throws Exception {
		if (isElementPresent(startTrialLink)) {
			setElement(startTrialLink).click();
			waitExplicit(confirmBtn, 120);
		}
	}

	public void clickConfirmBtn() throws Exception {
		scrollTillElementFound(confirmBtn);
		setElement(confirmBtn).click();
		waitExplicit(text, 300);

	}

public boolean verifyTrialStarted() throws Exception {
	
		try {
			
			if (isElementPresent(text)) {
			String actualText1=setElement(text).getText();
			String expectedText1="Cloud SaaS Terms of Service";
						
			if (actualText1.equals(expectedText1)) {
				
				logger.info("The ISV trial is started successfully");
			} 
			
			}
			
			else if(isElementPresent(text1)) { 
				
				String actualText2=setElement(text1).getText();
				String expectedText2="Trial Request Acknowledged";
				if (actualText2.equals(expectedText2)) {
						
						logger.info("The ISV trial is started successfully");
					} 
			}
			else if (isElementPresent(text2)) { 
				
				String actualText3=setElement(text2).getText();
				String expectedText3="Trial Started";
				
				if (actualText3.equals(expectedText3)) {
						
						logger.info("The ISV trial is started successfully");
					} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

return flag1;
}

}
