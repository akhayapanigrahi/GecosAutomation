package com.itc.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.itc.util.BasePageObject;
import com.itc.util.ExcelutilObject;

public class DeleteTrial extends BasePageObject {
	DeleteTrial strial = null;

	public DeleteTrial(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean manageFlag=false;
	public static Logger logger = Logger.getLogger(DeleteTrial.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By startTrialLink=By.linkText("Start Trials");
	By manageTrialLink=By.linkText("Manage Trials");
	By appNameTextEnter=By.xpath("//*[@placeholder='Application/Bundles Name']");
	By searchBtn=By.id("searchButton");
	By Next_Btn=By.id("submitButton");
	By email_text_input=By.id("email");
	By start_Btn=By.xpath("//input[@value='Start Trial']");
	By ok_Btn=By.xpath("//span[contains(text(),'OK')]");
    By OrgIdLink=By.xpath("//a[contains(@href,'https://sspsp-stg15.good.com/#/a/organization')]");
    By MemberLink=By.linkText("Members");
    By expandIcon=By.xpath("//td[@class='expand icon']");
    By emailText=By.xpath("//tr[@ng-show='member.expand']/td[2]/span[2]");
    By orgIdText=By.xpath("//div[@class='edit-org__sub']/div[1]");
    By approveTrialLink=By.linkText("Approve Trials");
    By deleteTrialLink=By.linkText("Delete Trials");
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

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}

public void startBESTrial(String appName, String email) throws Exception{
	
	clickStartTrial();
	waitImplicit();
	enterAppName(appName);
	clickSearch();
	clickCheckBox();
	clickNextBtn();
	inputEmail(email);
	clickStartBtn();
	clickOKBtn();
}
public void clickStartTrial() throws Exception {
	if (isElementPresent(startTrialLink)) {
		setElement(startTrialLink).click();
	}
}

public void enterAppName(String appName) throws Exception {
	if (isElementPresent(appNameTextEnter)) {
		setElement(appNameTextEnter).sendKeys(appName);
		waitExplicit(searchBtn, 30);
	}
}

public void clickSearch() throws Exception {
	if (isElementPresent(searchBtn)) {
		setElement(searchBtn).click();
		waitImplicit();
		
	}
}
public void clickCheckBox() throws Exception {
	String appName =getValFromExcel(9,4);
	By AppName_checkBox=By.xpath("//input[@class='buddleAndApp_checkbox'][@value='" +appName+ "']");
	if (isElementPresent(AppName_checkBox)) {
		setElement(AppName_checkBox).click();
		waitExplicit(Next_Btn, 50);
		
	}
}
public void clickNextBtn() throws Exception{
	
	if (isElementPresent(Next_Btn)) {
		setElement(Next_Btn).click();
		waitExplicit(email_text_input, 50);
		
	}
}
public void inputEmail(String email) throws Exception{
	
	if (isElementPresent(email_text_input)) {
		setElement(email_text_input).sendKeys(email);
		waitExplicit(start_Btn, 50);
		
	}
}
public void clickStartBtn() throws Exception{
	
	if (isElementPresent(start_Btn)) {
		setElement(start_Btn).click();
		waitExplicit(ok_Btn, 180);
		
	}
}
public void clickOKBtn() throws Exception{
	waitExplicit(ok_Btn, 180);
	if (isElementPresent(ok_Btn)) {
	     setElement(ok_Btn).click();
  		logger.info("OK btn is clicked");
  		waitImplicit();
	}
}
public void rejectTrial() throws Exception{
	
	if (isElementPresent(manageTrialLink)) {
		List<WebElement> OrgId_Links = driver.findElements(OrgIdLink);
		logger.info("No of Org ID displayed is "+OrgId_Links.size());
		
		for(int i=0;i<=OrgId_Links.size();i++){
			OrgId_Links.get(i).click();
		for (String Child_Window : driver.getWindowHandles())  
	    {  
	    driver.switchTo().window(Child_Window); 
			
			}
		waitExplicit(MemberLink, 60);
		setElement(MemberLink).click();
		waitExplicit(expandIcon, 50);
		setElement(expandIcon).click();
		waitImplicit(3000);
		String emailId = setElement(emailText).getText();
		
		String email =getValFromExcel(9,3);

		if(emailId.equals(email)){
			
			String orgText = setElement(orgIdText).getText();
			String Actual_org_text = orgText.replace("OrgID: ", "");
			logger.info("Actual org ID for this user is:"+Actual_org_text);
			driver.close();
			
			for (String parent_Window : driver.getWindowHandles())  
		    {  
		    driver.switchTo().window(parent_Window); 
				}
			for(WebElement orgToSerach : OrgId_Links){
				String appName =getValFromExcel(9,4);
				By app_id_text=By.xpath("//td[contains(text(),'"+appName+"')]");
				String app_ID=setElement(app_id_text).getText();
				By TrialStatus=By.xpath("//td[@id='status"+Actual_org_text+appName+"']");
	        	String Trial_Status = setElement(TrialStatus).getText();
		        if(orgToSerach.getText().contains(Actual_org_text)&&app_ID.equals(appName)){ 
		        	
		        		if(Trial_Status.equals("PENDING APPROVAL")){
						setElement(approveTrialLink).click();
						waitImplicit(3000);
						
		        		}
						By RejectLink=By.xpath("//a[contains(@onclick,\"rejecttrialapp('"+Actual_org_text+"','"+appName+"'\")]");
						setElement(RejectLink).click();
						waitImplicit(3000);
						waitExplicit(deleteTrialLink, 60);
						   setElement(deleteTrialLink).click(); 
							By DeleteLink=By.xpath("//a[contains(@onclick,\"deletetrial('"+Actual_org_text+"','"+appName+"'\")]");
							waitExplicit(DeleteLink, 60);
							setElement(DeleteLink).click();
							Alert alert=driver.switchTo().alert();
							alert.accept();
						   logger.info("The Trial is deleted for OrgID: "+Actual_org_text+" APP id "+appName);
						
		        }
		       
		        break;
		        }
		        	
		
		}
			break;
			}
	}
		else {
			logger.info("Email and Org ID is not matching...... ");
			logger.info("Searching again............. ");

			driver.close();
			for (String parent_Window : driver.getWindowHandles())  
		    {  
		    driver.switchTo().window(parent_Window); 
				}
		
		}
		
	}
		

public boolean isStartTrialPageDisplayed() throws Exception {
	try {

		flag1 = isElementPresent(startTrialLink);
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

public boolean isTrialDisplayedInDeleteTrials() throws Exception {
	try {

		manageFlag = isElementPresent(manageTrialLink);
		if (manageFlag) {
			setElement(manageTrialLink).click();
			rejectTrial();
			logger.info("The Deleted trial is displayed in Delete trials page for the user");
		} else {
			logger.info("The trial is not deleted for the user");
		}
	} catch (Exception e) {
		throw new Exception("Home Link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
	}
	return manageFlag;
}


	
	public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "AppName");
		return ExcelutilObject.getCellData(row, col);
	}
	
}
