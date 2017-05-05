package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.SuperAdminRoles;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

import junit.framework.Assert;

public class SuperAdminRolesTest extends BaseTestObject {

	SuperAdminRoles sadmin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	boolean manageFlag=true;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyHomePageDisplay() throws Exception {
		try {

			sadmin = new SuperAdminRoles(driver);
			flag = sadmin.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			String username=getValFromExcel(2,2);
			String Password=getValFromExcel(2,3);
			sadmin.LoginAsUser(username, Password);
			flag = sadmin.isHomePageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyManageTrialsPageDisplay() throws Exception {
		try {

			sadmin = new SuperAdminRoles(driver);
			manageFlag = sadmin.isManagaTrialsPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to verify Super admin Roles"+ "\n " + e.getLocalizedMessage());
		}
	}
	public void Logout(){
		
		
		
	}
public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}

}