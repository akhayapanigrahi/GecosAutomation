package com.Gecos.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Gecos.common.GecosConstants;
import com.Gecos.common.GecosUtil;
import com.Gecos.pages.ApproveTrial;
import com.Gecos.util.BaseTestObject;
import com.Gecos.util.ExcelutilObject;

import junit.framework.Assert;

public class ApproveTrialTest extends BaseTestObject {

	ApproveTrial trial = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLogin() throws Exception {
		try {

			trial = new ApproveTrial(driver);
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

			trial = new ApproveTrial(driver);
			GecosUtil.explicitWait(3000);
			String appName =getValFromExcel(6,2);
			String email =getValFromExcel(6,3);
			trial.startBESTrial(appName,email);
			flag = trial.isTrialDisplayedInManageTrials();
			Assert.assertTrue(flag);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to Approve the trial"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "AppName");
	return ExcelutilObject.getCellData(row, col);
}

	
}
