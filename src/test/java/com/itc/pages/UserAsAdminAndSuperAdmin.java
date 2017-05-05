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

public class UserAsAdminAndSuperAdmin extends BasePageObject {
	UserAsAdminAndSuperAdmin objLoginPage = null;
	WebDriverWait wait;
	public UserAsAdminAndSuperAdmin(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean loginFlag = false;
	boolean adminFlag = false;
	boolean manageFlag=false;
	boolean superadminFlag=false;
	public static Logger logger = Logger.getLogger(UserAsAdminAndSuperAdmin.class);


	/* Web elements */

	By userEmailAddr = By.xpath("//input[@id='IDToken1'][@placeholder='Email or Username']");
	By userPwd = By.xpath("//input[@id='IDToken2'][@placeholder='Password']");
	By login = By.xpath("//input[@value='Log In'][@name='Login.Submit']");
	By ManageTrialsLink=By.linkText("Manage Trials");
	By ManageTrialsHeader=By.xpath("//h1[contains(text(),'Manage Trials')]");
	By ManageDropdown=By.xpath("//span[contains(text(),'Manage')]");
    By SelectPermission=By.xpath("//a[contains(text(),'Permission') and @ui-sref='home.permissions.groups']");
	By PermissionDropdown=By.xpath("//span[contains(text(),'Permission')]");
    By Admin_Link=By.xpath("//a[contains(text(),'GECOS_ADMIN')]");
    By AddUser_link=By.xpath("//a[contains(text(),'Add Users')]");
    By input_text=By.xpath("//input[@placeholder='Type e-mail or username']");
    By AddBtn=By.xpath("//button[contains(text(),'Add')]");
    By OrgIdLink=By.xpath("//a[contains(@href,'https://sspsp-stg15.good.com/#/a/organization')]");
    By Super_Admin_Link=By.xpath("//a[contains(text(),'SUPER_ADMIN')]");
    By PermissionLink=By.xpath("//a[contains(text(),'Permissions') and @translate='permissions.breadcrumbs']");
    By texts_click=By.xpath("//ng-include/span[@class='ng-binding ng-scope']");
    By admin_users_link=By.xpath("//a[@ui-sref='home.user({username:member.username, uuid:member.federation_id})']");
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

public void addPermissionForAdmin(String user,String textToSelect,String addedUser) throws Exception{
	
	clickOnManageTrials();
	waitImplicit();

	clickOnOrgId();
	waitImplicit();

	clickOnPermission();
	waitImplicit();

	clickOnGecosAdmin();
	waitImplicit();

	addUserAsAdmin(user, textToSelect);
	waitImplicit();

	validateUser(addedUser);
	
}
public void addPermissionSuperForAdmin(String user,String textToSelect,String addedUser) throws Exception{
	
	waitExplicit(PermissionLink, 60);
	setElement(PermissionLink).click();
	waitImplicit();

	clickOnGecosSuperAdmin();
	waitImplicit();

	addUserAsSuperAdmin(user, textToSelect);
	waitImplicit();

	validateUser(addedUser);
	
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
		 textToSelect=getValFromExcelForUserList(3,3);
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
public void clickOnManageTrials() throws Exception{
	
	if (isElementPresent(ManageTrialsLink)) {
		setElement(ManageTrialsLink).click();
		waitForAnElement(ManageTrialsHeader, 20);

	}
}

public void clickOnOrgId() throws InterruptedException{
	if (isElementPresent(ManageTrialsHeader)) {
	
	List<WebElement> OrgId_Links = driver.findElements(OrgIdLink);
	logger.info("No of Org ID displayed is "+OrgId_Links.size());
	
	OrgId_Links.get(1).click();
	waitImplicit(15000);
	
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
		waitExplicit(SelectPermission, 70);
	    setElement(SelectPermission).click();		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	
}

public void clickOnGecosAdmin() throws Exception{
	
	if(isElementPresent(Admin_Link)){
		waitExplicit(Admin_Link, 50);
		setElement(Admin_Link).click();
	}
	}

public void addUserAsAdmin(String user, String textToSelect ) throws Exception{
	if(isElementPresent(AddUser_link)){
		 textToSelect=getValFromExcelForUserList(3,3);
		waitExplicit(AddUser_link, 50);
		setElement(AddUser_link).click();
		waitImplicit();
		setElement(input_text).sendKeys(user);
		waitImplicit();
		selectOptionWithText(textToSelect);
		waitExplicit(AddBtn, 30);
		setElement(AddBtn).click();

	}
	
}

public void selectOptionWithText(String textToSelect) throws Exception{
	 textToSelect=getValFromExcelForUserList(3,3);

	List<WebElement> ele_texts = driver.findElements(texts_click);
	
	for(WebElement textToClick : ele_texts){
		logger.info("The Text to be clickable is "+ textToClick.getText());
        if(textToClick.getText().contains(textToSelect)) {
        	logger.info("Found the expected text "+textToSelect);
        	textToClick.click();
            break;
        }
        else {
        	System.out.println("Not finding element to click");
        }
	
    }
	
}

public void validateUser(String addedUser) throws Exception{
	
	waitExplicit(PermissionDropdown, 70);
	setElement(PermissionDropdown).click();
	waitExplicit(SelectPermission, 70);
    setElement(SelectPermission).click();	
    waitExplicit(Admin_Link, 50);
	setElement(Admin_Link).click();
	
	List<WebElement> all_Admin_Users = driver.findElements(admin_users_link);
	String expected_user=getValFromExcelForUserList(3,4);
		for(int i=0;i<=all_Admin_Users.size();i++){
        if(all_Admin_Users.get(i).getText().contains(expected_user)) {
        	logger.info("Searching user........: User  found "+expected_user);
    		Assert.assertTrue(all_Admin_Users.get(i).getText().contains(expected_user), "Test case is passed");
        	break;
        }
        else{
        	logger.info("Not finding element to click");
        }
	}
	
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
	return adminFlag;

}
//Validation
	public boolean isLandingPageDisplayed() throws Exception {
		try {

			manageFlag = isElementPresent(ManageTrialsLink);
			if (manageFlag) {
				logger.info("This user is having Admin access and have access to Manage trials page");
			} else {
				logger.info("This user is not having Admin access and only Home page access is available");
			}
		} catch (Exception e) {
			throw new Exception("Manage trials link displayed" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return manageFlag;
	}
	public boolean isGecos_AdminPageDisplayed() throws Exception {
		try {

			adminFlag = isElementPresent(AddUser_link);
			if (adminFlag) {
				logger.info("This user is added as Admin in Gecos");
			} else {
				logger.info("This user is not added as Admin in Gecos and Add user page is displayed..");
			}
		} catch (Exception e) {
			throw new Exception("Add users link is not displayed" + isGecos_AdminPageDisplayed() + e.getLocalizedMessage());
		}
		return adminFlag;
	}
	public boolean isGecos_SuperAdminPageDisplayed() throws Exception {
		try {

			superadminFlag = isElementPresent(AddUser_link);
			if (superadminFlag) {
				logger.info("This user is added as Super Admin in Gecos");
			} else {
				logger.info("This user is not added as Admin in Gecos and Add user page is displayed..");
			}
		} catch (Exception e) {
			throw new Exception("Add users link is not displayed" + isGecos_AdminPageDisplayed() + e.getLocalizedMessage());
		}
		return superadminFlag;
	}

public static String getValFromExcelForUserList(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "UserList");
	return ExcelutilObject.getCellData(row, col);
}
public static String getValFromExcelForLogin(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}
}
