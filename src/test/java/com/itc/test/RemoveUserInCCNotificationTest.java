package com.itc.test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.RemoveUserInCCNotification;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class RemoveUserInCCNotificationTest extends BaseTestObject {

	RemoveUserInCCNotification admin = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyHomePageDisplay() throws Exception {
		try {

			admin = new RemoveUserInCCNotification(driver);
			flag = admin.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			String username=getValFromExcelForuser(3,2);
			String Password=getValFromExcelForuser(3,3);
			admin.LoginAsUser(username, Password);
			flag = admin.isAppConfigPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyUserAddedasCC() throws Exception {
		try {

			admin = new RemoveUserInCCNotification(driver);
			GecosUtil.explicitWait(3000);
			String user =getValFromExcel(10,3);
			String appname=getValFromExcel(10,2);
			String appID =getValFromExcel(10,4);
			admin.removeUserAsCC(user,appname,appID);
			flag = admin.isUserDisplayed();
			Assert.assertTrue(flag);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to verify the CC list"+ "\n " + e.getLocalizedMessage());
		}
	}
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "AppName");
	return ExcelutilObject.getCellData(row, col);
}
	public static String getValFromExcelForuser(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
		return ExcelutilObject.getCellData(row, col);
	}
	
}