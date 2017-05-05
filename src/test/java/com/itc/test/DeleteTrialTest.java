package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.DeleteTrial;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

import junit.framework.Assert;

public class DeleteTrialTest extends BaseTestObject {

	DeleteTrial trial = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	boolean deleteFlag=false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLogin() throws Exception {
		try {

			trial = new DeleteTrial(driver);
			flag = trial.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			trial.LoginAsUser(username, Password);
			flag = trial.isStartTrialPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyApproveTrial() throws Exception {
		try {

			trial = new DeleteTrial(driver);
			GecosUtil.explicitWait(3000);
			String appName =getValFromExcel(9,2);
			String email =getValFromExcel(9,3);
			trial.startBESTrial(appName,email);
			deleteFlag = trial.isTrialDisplayedInDeleteTrials();
			Assert.assertTrue(deleteFlag);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to Delete user"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "AppName");
	return ExcelutilObject.getCellData(row, col);
}

	
}
