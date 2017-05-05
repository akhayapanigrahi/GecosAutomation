package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.GecosConstants;
import com.itc.common.GecosUtil;
import com.itc.pages.LoginPage;
import com.itc.util.BaseTestObject;

import junit.framework.Assert;

public class LoginPageTest extends BaseTestObject {

	LoginPage objLoginPage = null;
	GecosConstants obj = new GecosConstants();
	boolean flag = false;
	public String username = ObjProperty.getProperty("AdminUser");
	public String Password = ObjProperty.getProperty("AdminPassword");

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true)
	public void verifyLoginPage() throws Exception {
		try {

			objLoginPage = new LoginPage(driver);
			flag = objLoginPage.isLoginPageDisplayed();
			GecosUtil.explicitWait(3000);
			objLoginPage.LoginAsUser(username, Password);
			flag = objLoginPage.isLandingPageDisplayed();
			Assert.assertTrue(flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login to Gecos Admin console"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
}
