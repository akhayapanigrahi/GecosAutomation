package com.Gecos.pages;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Gecos.util.BasePageObject;
import com.Gecos.util.ExcelutilObject;

public class AddUserAsSuperAdmin extends BasePageObject {
	AddUserAsSuperAdmin sadmin = null;
	WebDriverWait wait;
	public AddUserAsSuperAdmin(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean loginFlag = false;
	boolean adminFlag = false;

	public static Logger logger = Logger.getLogger(AddUserAsSuperAdmin.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By ManageTrialsLink=By.linkText("Manage Trials");
	By ManageTrialsHeader=By.xpath("//h1[contains(text(),'Manage Trials')]");
	By ManageDropdown=By.xpath("//span[contains(text(),'Manage')]");
    By SelectPermission=By.xpath("//a[contains(text(),'Permission') and @ui-sref='home.permissions.groups']");
	By PermissionDropdown=By.xpath("//span[contains(text(),'Permission')]");
    By Super_Admin_Link=By.xpath("//a[contains(text(),'SUPER_ADMIN')]");
    By AddUser_link=By.xpath("//a[contains(text(),'Add Users')]");
    By input_text=By.xpath("//input[@placeholder='Type e-mail or username']");
    By AddBtn=By.xpath("//button[contains(text(),'Add')]");
    By user_link=By.xpath("//a[@ui-sref='home.user({username:member.username, uuid:member.federation_id})']");
    By logoutBtn=By.xpath("//a[contains(text(),'Log Out')]");
    By OrgIdLink=By.xpath("//a[contains(@href,'https://sspsp-stg15.good.com/#/a/organization')]");
    By texts_click=By.xpath("//ng-include/span[@class='ng-binding ng-scope']");
    
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
			waitForAnElement(ManageTrialsLink, 50);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}

public void addPermissionSuperForAdmin(String user,String textToSelect,String addedUser) throws Exception{
	
	clickOnManageTrials();
	waitImplicit();

	clickOnOrgId();
	waitImplicit();

	clickOnPermission();
	waitImplicit();

	clickOnGecosSuperAdmin();
	waitImplicit();

	addUserAsSuperAdmin(user, textToSelect);
	waitImplicit();

	validateUser(addedUser);
	
}

public void clickOnManageTrials() throws Exception{
	
	if (isElementPresent(ManageTrialsLink)) {
		setElement(ManageTrialsLink).click();
		waitForAnElement(ManageTrialsHeader, 20);
		logger.info("Manage trial page is clicked");

	}
}

public void clickOnOrgId() throws InterruptedException{
	if (isElementPresent(ManageTrialsHeader)) {
	
	List<WebElement> OrgId_Links = driver.findElements(OrgIdLink);
	logger.info("No of Org ID displayed is "+OrgId_Links.size());
	OrgId_Links.get(1).click();
	
	Thread.sleep(15000);
	}
}

public void	clickOnPermission() throws Exception{

	 for (String Child_Window : driver.getWindowHandles())  
     {  
     driver.switchTo().window(Child_Window); 
		
		}
	 
	if(isElementPresent(ManageDropdown)){
	try {
		waitExplicit(ManageDropdown, 70);
		setElement(ManageDropdown).click();
		waitImplicit(3000);
	    setElement(SelectPermission).click();	
		logger.info("Permission page is clicked");

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	
}

public void clickOnGecosSuperAdmin() throws Exception{
	
	if(isElementPresent(Super_Admin_Link)){
		waitExplicit(Super_Admin_Link, 70);
		setElement(Super_Admin_Link).click();
		logger.info("Super Admin Link  is clicked");

	}
	}

public void addUserAsSuperAdmin(String user, String textToSelect ) throws Exception{
	if(isElementPresent(AddUser_link)){
		 textToSelect=getValFromExcel(2,3);
		waitExplicit(AddUser_link, 50);
		setElement(AddUser_link).click();
		waitImplicit();
		setElement(input_text).sendKeys(user);
		waitImplicit();
		selectOptionWithText(textToSelect);
		waitExplicit(AddBtn, 30);
		setElement(AddBtn).click();
		logger.info("Super Admin is added in Gecos");


	}
	
}

public void selectOptionWithText(String textToSelect) throws Exception{
	 textToSelect=getValFromExcel(2,3);

	List<WebElement> ele_texts = driver.findElements(texts_click);
	
	for(WebElement textToClick : ele_texts){
		logger.info("All texts present are "+ textToClick.getText());
        if(textToClick.getText().contains(textToSelect)) {
        	logger.info("Found the expected text "+textToSelect);
        	textToClick.click();
            break;
        }
        else{
        	System.out.println("Not finding element to click");
        }
	
    }
	
}

public void validateUser(String addedUser) throws Exception{
	
	waitExplicit(PermissionDropdown, 180);
	setElement(PermissionDropdown).click();
	waitImplicit(3000);
    setElement(SelectPermission).click();	
    waitExplicit(Super_Admin_Link, 50);
	setElement(Super_Admin_Link).click();
	
	List<WebElement> all_Admin_Users = driver.findElements(user_link);
	String expected_user=getValFromExcel(2,4);
		for(int i=0;i<=all_Admin_Users.size();i++){
        if(all_Admin_Users.get(i).getText().contains(expected_user)) {
        	logger.info("Searching user........: User  found "+expected_user);
    		Assert.assertTrue(all_Admin_Users.get(i).getText().contains(expected_user), "Test case is passed");

        	break;
        }
        else{
        	System.out.println("Not finding element to click");
        }
	}
	
	}


//Validation
	public boolean isLandingPageDisplayed() throws Exception {
		try {

			loginFlag = isElementPresent(ManageTrialsLink);
			if (loginFlag) {
				logger.info("This user is having Super Admin access and have access to Manage trials page");
			} else {
				logger.info("This user is not having Super Admin access and only Home page access is available");
			}
		} catch (Exception e) {
			throw new Exception("Manage trials link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return loginFlag;
	}
	public boolean isGecos_SuperAdminPageDisplayed() throws Exception {
		try {

			adminFlag = isElementPresent(AddUser_link);
			if (adminFlag) {
				logger.info("This user is added as Super Admin in Gecos");
			} else {
				logger.info("This user is not added as Super Admin in Gecos and Add user page is displayed..");
			}
		} catch (Exception e) {
			throw new Exception("Add users link is not displayed" + isGecos_SuperAdminPageDisplayed() + e.getLocalizedMessage());
		}
		return adminFlag;
	}

public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "UserList");
	return ExcelutilObject.getCellData(row, col);
}
}
