package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.AddUserAsAdmin;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

import junit.framework.Assert;

public class AddUserAsAdminTest extends BaseTestObject {

	AddUserAsAdmin admin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLoginPage() throws Exception {
		try {

			admin = new AddUserAsAdmin(driver);
			flag = admin.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			admin.LoginAsUser(username, Password);
			flag = admin.isLandingPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Parameters({ "browserType"})
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyGecosAdminPage() throws Exception {
		try {

			admin = new AddUserAsAdmin(driver);
			GecosUtil.explicitWait(3000);
			String user =getValFromExcel(1,2);
			String addedUser =getValFromExcel(1,4);
			String textToSelect=getValFromExcel(1,3);
			admin.addPermissionForAdmin(user,textToSelect,addedUser);
			flag = admin.isGecos_AdminPageDisplayed();
			Assert.assertTrue(flag);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to add users as Admin"+ "\n " + e.getLocalizedMessage());
		}
	}
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "UserList");
	return ExcelutilObject.getCellData(row, col);
}
public static String getValFromExcelForLogin(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}
}