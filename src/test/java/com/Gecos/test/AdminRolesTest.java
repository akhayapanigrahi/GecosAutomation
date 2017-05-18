package com.Gecos.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Gecos.common.GecosConstants;
import com.Gecos.common.GecosUtil;
import com.Gecos.pages.AdminRoles;
import com.Gecos.util.BaseTestObject;
import com.Gecos.util.ExcelutilObject;

import junit.framework.Assert;

public class AdminRolesTest extends BaseTestObject {

	AdminRoles admin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	boolean manageFlag=true;
	boolean deleteFlag=false;
	boolean addFlag=false;
	boolean removeFlag=false;
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
	@Test(priority = 3, enabled = true,groups="BasicTests")
	public void verifyAddBtnEnabled() throws Exception {
		try {

			admin = new AdminRoles(driver);
			String Appname=getValForApp(2,2);
			String AppID=getValForApp(2,4);
			addFlag = admin.isAddButtonEnabled(Appname,AppID);
			Assert.assertTrue(addFlag);
		}

		catch (Exception e) {
			throw new Exception("Failed to verify the Add button"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Test(priority = 4, enabled = true,groups="BasicTests")
	public void verifyRemoveBtnEnabled() throws Exception {
		try {

			admin = new AdminRoles(driver);
			removeFlag = admin.isRemoveButtonEnabled();
			Assert.assertTrue(removeFlag);
		}

		catch (Exception e) {
			throw new Exception("Failed to verify the Remove button"+ "\n " + e.getLocalizedMessage());
		}
	}
    public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}
    public static String getValForApp(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "AppName");
	return ExcelutilObject.getCellData(row, col);
}
}