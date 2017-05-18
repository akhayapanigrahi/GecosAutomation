package com.Gecos.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Gecos.common.GecosConstants;
import com.Gecos.common.GecosUtil;
import com.Gecos.pages.StartISVTrialFromPCE;
import com.Gecos.util.BaseTestObject;
import com.Gecos.util.ExcelutilObject;

import junit.framework.Assert;

public class StartISVTrialFromPCETest extends BaseTestObject {

	StartISVTrialFromPCE objLoginPage = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLoginPage() throws Exception {
		try {

			objLoginPage = new StartISVTrialFromPCE(driver);
			//flag = objLoginPage.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			String Username=getValFromExcel(4,2);
			String Password=getValFromExcel(4,3);
			String pceUrl=getValFromExcel(4,1);
			objLoginPage.loginAsUserInPCE(Username, Password,pceUrl);
			
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
	
	
	@Test(priority = 1, enabled = true,groups="BasicTests")
	public void verifyStartTrialFromPCE() throws Exception {
		try {

			objLoginPage = new StartISVTrialFromPCE(driver);
			//flag = objLoginPage.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			String appName=getValFromExcelForApp(4,2);
			String email=getValFromExcelForApp(4,3);
			String textToSelect=getValFromExcelForApp(4,2);
			objLoginPage.startIVSTrial(appName,email,textToSelect);

		}

		catch (Exception e) {
			throw new Exception("Failed to Start Trial from PCE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
	@Test(priority = 2, enabled = true,groups="BasicTests")
	public void verifyTrialStatus() throws Exception {
		try {

			objLoginPage = new StartISVTrialFromPCE(driver);
			//flag = objLoginPage.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			flag=objLoginPage.verifyTrialStarted();
			Assert.assertTrue(flag);

		}

		catch (Exception e) {
			throw new Exception("Failed to  ISV trial for user from PCE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
		return ExcelutilObject.getCellData(row, col);
	}
public static String getValFromExcelForApp(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "AppName");
		return ExcelutilObject.getCellData(row, col);
	}
}
