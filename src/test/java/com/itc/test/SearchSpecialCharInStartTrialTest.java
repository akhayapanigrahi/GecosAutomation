package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.SearchSpecialCharInStartTrial;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

import junit.framework.Assert;

public class SearchSpecialCharInStartTrialTest extends BaseTestObject {

	SearchSpecialCharInStartTrial trial = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLogin() throws Exception {
		try {

			trial = new SearchSpecialCharInStartTrial(driver);
			flag = trial.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			trial.LoginAsUser(username, Password);
			flag = trial.isLandingPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifySpecialCharForAppname() throws Exception {
		try {

			trial = new SearchSpecialCharInStartTrial(driver);
			GecosUtil.explicitWait(3000);
			String character =getValFromExcel(5,2);
			trial.searchSpecialCharacter(character);
			flag = trial.isResultDisplayed();
			Assert.assertTrue(flag);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to find Apps"+ "\n " + e.getLocalizedMessage());
		}
	}
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "AppName");
	return ExcelutilObject.getCellData(row, col);
}

	
}
