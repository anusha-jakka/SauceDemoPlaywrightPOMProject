package com.qa.SauceDemo.Tests;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.SauceDemo.Base.BaseTest;
import com.qa.SauceDemo.Constants.LabelConstants;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void verifyTitle()
	{
		String actualTitle = loginPage.loginPageTitle();
		
		Assert.assertEquals(actualTitle, LabelConstants.SWAG_LABS);
	}
	
	@Test
	public void verifyHeader()
	{
		String actualHeader = loginPage.loginPageHeader();
		
		Assert.assertEquals(actualHeader, LabelConstants.SWAG_LABS);
	}
	
	@DataProvider
	public Object[][] loginUserData()
	{
		return new Object[][] {
				{"standard_user","secret_sauce"},
				{"error_user","secret_sauce"},
				{"problem_user","secret_sauce"}
		};
	}
	
	@Test(priority = 1,dataProvider = "loginUserData")
	public void login(String userName,String password)
	{
		inventoryPage = loginPage.login(userName, password);
		
		
		String actualPageHeaderString = inventoryPage.productsHeader();
		Assert.assertEquals(actualPageHeaderString, LabelConstants.PRODUCTS_HEADER);
		
		String urlString = inventoryPage.pageUrl();
		Assert.assertEquals(urlString, prop.getProperty("inventoryUrl"));
		
		inventoryPage.logout();
		
	}
	
	@Test
	public void invalidLogin()
	{
		loginPage.login("standard_test", "secret_sauce");
		
		String actualError = loginPage.errorMessage();
		Assert.assertEquals(actualError, LabelConstants.INVALID_LOGIN_ERROR);
		
	}
	
	@Test
	public void lockedOutUser()
	{
		loginPage.login("locked_out_user", "secret_sauce");
		
		String actualError=loginPage.errorMessage();
		Assert.assertEquals(actualError, LabelConstants.LOCKED_USER_ERROR);
		
	}
	
	@Test
    public void verifyOnlyUserName()
    {
	String actualError=	loginPage.enterUsernameOnly(prop.getProperty("userName"));
		
	Assert.assertEquals(actualError, LabelConstants.PASSWORD_REQUIRED_ERROR);	
    }
	
	@Test
    public void verifyOnlyPassword()
    {
	String actualError=	loginPage.enterPasswordOnly(prop.getProperty("password"));
		
	Assert.assertEquals(actualError, LabelConstants.USERNAME_REQUIRED_ERROR);	
    }
	
	@Test
    public void verifyClickLoginOnly()
    {
	String actualError=	loginPage.clickLoginOnly();
		
	Assert.assertEquals(actualError, LabelConstants.USERNAME_REQUIRED_ERROR);	
    }

}
