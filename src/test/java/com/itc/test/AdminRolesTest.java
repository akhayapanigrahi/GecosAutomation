package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.AdminRoles;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

import junit.framework.Assert;

public class AdminRolesTest extends BaseTestObject {

	AdminRoles admin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	boolean manageFlag=true;
	boolean deleteFlag=false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyHomePageDisplay() throws Exception {
		try {

			admin = new AdminRoles(driver);
			flag = admin.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			String username=getValFromExcel(1,2);
			String Password=getValFromExcel(1,3);
			admin.LoginAsUser(username, Password);
			flag = admin.isHomePageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyManageTrialsPageDisplay() throws Exception {
		try {

			admin = new AdminRoles(driver);
			manageFlag = admin.isManageTrialPageDisplayed();
			Assert.assertTrue(manageFlag);
		}

		catch (Exception e) {
			throw new Exception("Failed to verify the Admin Roles in Manage trials page"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Test(priority = 2, enabled = true,groups="BasicTests")
	public void verifyDeleteTrialsPageDisplay() throws Exception {
		try {

			admin = new AdminRoles(driver);
			deleteFlag = admin.isdeleteLinkDisplayed();
			Assert.assertTrue(deleteFlag);
		}

		catch (Exception e) {
			throw new Exception("Failed to verify the delete link"+ "\n " + e.getLocalizedMessage());
		}
	}
	
public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}

}