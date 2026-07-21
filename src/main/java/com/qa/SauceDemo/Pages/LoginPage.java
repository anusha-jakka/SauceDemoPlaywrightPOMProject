package com.qa.SauceDemo.Pages;

import java.util.Properties;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	//public constructor to initialize the page
	public LoginPage(Page page){
		
		this.page=page;
		// initialize locators after the page has been assigned to avoid NPE
		swagLabsLabel = page.locator("div.login_logo");
		userNameLocator = page.getByPlaceholder("Username");
		passwordLocator = page.getByPlaceholder("Password");
		loginLocator = page.locator("#login-button");
		errorMessageLocator = page.locator("[data-test='error']");
	}
	
	// Private Locators (initialized in constructor)
	private Locator swagLabsLabel;
	private Locator userNameLocator;
	private Locator passwordLocator;
	private Locator loginLocator;
	private Locator errorMessageLocator;
	
	
	//public page action methods
	
	public String loginPageTitle() {
		
		return page.title();
	}
	
	public String loginPageHeader()
	{
		return swagLabsLabel.textContent();
	}
	
	public InventoryPage login(String userName,String passWord)
	{
		
		userNameLocator.clear();
		userNameLocator.fill(userName);
		passwordLocator.clear();
		passwordLocator.fill(passWord);
		loginLocator.click();
		
		return new InventoryPage(page);
	}
	
	public String errorMessage()
	{
	   return errorMessageLocator.textContent();
	}

	public String enterUsernameOnly(String userName)
	{
		userNameLocator.fill(userName);
		passwordLocator.clear();
		loginLocator.click();
		return errorMessageLocator.textContent();
	}
	
	public String enterPasswordOnly(String password)
	{
		userNameLocator.clear();
		passwordLocator.fill(password);
		loginLocator.click();
		return errorMessageLocator.textContent();
	}
	
	public String clickLoginOnly()
	{

		userNameLocator.clear();
		passwordLocator.clear();
		loginLocator.click();
		return errorMessageLocator.textContent();
	}
}
