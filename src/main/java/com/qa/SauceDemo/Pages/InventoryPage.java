package com.qa.SauceDemo.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {

	private Page page;

	// Private locators
	private Locator productsHeaderLocator;
	private Locator leftMenuLocator;
	private Locator logoutLocator;
	private Locator backpackAddLocator;
	private Locator backpackRemoveLocator;
	private Locator shoppingCartBadgeLocator;
	private Locator shoppingCartLocator;
	private Locator filterIconLocator;
	private Locator productsLocator;
	

	// Constructor to intialise the page

	public InventoryPage(Page page) {

		this.page = page;

		productsHeaderLocator = page.locator("[data-test='title']");
		leftMenuLocator = page.locator("#react-burger-menu-btn");
		logoutLocator = page.locator("#logout_sidebar_link");
		backpackAddLocator = page.locator("#add-to-cart-sauce-labs-backpack");
		shoppingCartBadgeLocator = page.locator("span.shopping_cart_badge");
		backpackRemoveLocator = page.locator("#remove-sauce-labs-backpack");
		shoppingCartLocator = page.locator(".shopping_cart_link");
		filterIconLocator = page.locator("select.product_sort_container");
		productsLocator = page.locator("div.inventory_item_name");
	}

	// public methods for page actions

	public String productsHeader() {

		return productsHeaderLocator.textContent();
	}

	public String pageUrl() {
		return page.url();
	}

	public void logout() {
		leftMenuLocator.click();
		logoutLocator.click();

	}

	public boolean addToCart() {

		backpackAddLocator.click();
		if (shoppingCartBadgeLocator.isVisible() && shoppingCartBadgeLocator.textContent().contains("1")) {
			System.out.println("Product added successfully to the cart");
			return true;
		}
		return false;
	}

	public boolean removeFromCart() {

		backpackRemoveLocator.click();
		if (!shoppingCartBadgeLocator.isVisible())

		{
			System.out.println("Cart is empty");
			return true;
		}
		return false;
	}

	public CartPage navigateToCart() {

		shoppingCartLocator.click();

		return new CartPage(page);
	}
	
	public boolean sortProductsAToZ()
	{
		filterIconLocator.selectOption("az");
		
		List<String> productNameList = new ArrayList<String>();
		
		for(int i=0; i<productsLocator.count(); i++)
		{
			productNameList.add(productsLocator.nth(i).textContent());
		}
		
		// Create a copy to sort
		List<String> sortedList = new ArrayList<String>(productNameList);
		Collections.sort(sortedList);
		
		// Compare the two lists
		if(productNameList.equals(sortedList))
		{
			System.out.println("Products are sorted A to Z");
			return true;
		}
		else
		{
			System.out.println("Products are NOT sorted A to Z");
			return false;
		}
	}
	
	public boolean sortProductsZToA()
	{
		filterIconLocator.selectOption("za");
		
		List<String> productNameList = new ArrayList<String>();
		
		for(int i=0; i<productsLocator.count(); i++)
		{
			productNameList.add(productsLocator.nth(i).textContent());
		}
		
		// Create a copy to sort
		List<String> sortedList = new ArrayList<String>(productNameList);
		Collections.sort(sortedList,Collections.reverseOrder());
		
		// Compare the two lists
		if(productNameList.equals(sortedList))
		{
			System.out.println("Products are sorted Z to A");
			return true;
		}
		else
		{
			System.out.println("Products are NOT sorted Z to A");
			return false;
		}
	}

}
