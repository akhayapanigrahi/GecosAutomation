package com.Gecos.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Gecos.common.GecosConstants;
import com.Gecos.common.GecosUtil;
import com.Gecos.pages.SearchEmptyDataWithCFN;
import com.Gecos.util.BaseTestObject;

import junit.framework.Assert;

public class SearchEmptyDataWithCFNTest extends BaseTestObject {

	SearchEmptyDataWithCFN trial = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");

	@Parameters({ "browserType"})
	
	@Test(priority = 0, enabled = true,groups="BasicTests")
	public void verifyLogin() throws Exception {
		try {

			trial = new SearchEmptyDataWithCFN(driver);
			flag = trial.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			trial.LoginAsUser(username, Password);
			flag = trial.isResultDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"+ "\n " + e.getLocalizedMessage());
		}
	}
	
		
}
