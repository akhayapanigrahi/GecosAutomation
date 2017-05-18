package com.Gecos.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Gecos.common.GecosConstants;
import com.Gecos.common.GecosUtil;
import com.Gecos.pages.AddUserAsSuperAdmin;
import com.Gecos.util.BaseTestObject;
import com.Gecos.util.ExcelutilObject;

import junit.framework.Assert;

public class AddUserAsSuperAdminTest extends BaseTestObject {

	AddUserAsSuperAdmin sadmin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLoginPage() throws Exception {
		try {

			sadmin = new AddUserAsSuperAdmin(driver);
			flag = sadmin.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			sadmin.LoginAsUser(username, Password);
			flag = sadmin.isLandingPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Parameters({ "browserType"})
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyGecosSuperAdmin() throws Exception {
		try {

			sadmin = new AddUserAsSuperAdmin(driver);
			GecosUtil.explicitWait(3000);
			String user =getValFromExcel(2,2);
			String addedUser =getValFromExcel(2,4);
			String textToSelect=getValFromExcel(2,3);
			sadmin.addPermissionSuperForAdmin(user,textToSelect,addedUser);
			flag = sadmin.isGecos_SuperAdminPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to add users as Super admin"+ "\n " + e.getLocalizedMessage());
		}
	}


public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "UserList");
	return ExcelutilObject.getCellData(row, col);
}

}