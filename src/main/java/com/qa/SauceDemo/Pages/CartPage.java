package com.qa.SauceDemo.Pages;

import java.awt.print.Pageable;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
	
	Page page;
	
	//private Locators
	
	private Locator yourCartHeaderLocator;
	
	//public CartPage constructor with private locators initialization;
	
	public CartPage(Page page)
	{
		this.page=page;
		
		yourCartHeaderLocator = page.locator("[data-test='title']");
		
	}
	
	//public page methods
	
	public String cartPageUrl()
	{
		return page.url();
	}
	
	public String pageHeader()
	{
		return yourCartHeaderLocator.textContent();
	}
	
	

}
