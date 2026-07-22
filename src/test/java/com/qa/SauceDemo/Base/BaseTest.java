package com.qa.SauceDemo.Base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.microsoft.playwright.Page;
import com.qa.SauceDemo.Factory.PlaywrightFactory;
import com.qa.SauceDemo.Pages.InventoryPage;
import com.qa.SauceDemo.Pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory playwrightLaunch;
	Page page;
	protected LoginPage loginPage;
	protected InventoryPage inventoryPage;
	
	protected Properties prop;

	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(String browserName)
	{
		
		playwrightLaunch = new PlaywrightFactory();
		prop = playwrightLaunch.initProp();
		
		if(!browserName.isEmpty())
		{
			prop.setProperty("browser", browserName);
		}
		
		page = playwrightLaunch.initPlaywrightBrowser(prop);
		
		loginPage = new LoginPage(page);
//		inventoryPage = new InventoryPage(page);
	}
	
	@AfterTest
	public void closeBrowser()
	{
		page.context().browser().close();
	}

}
