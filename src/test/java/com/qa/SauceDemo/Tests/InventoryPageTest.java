package com.qa.SauceDemo.Tests;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qa.SauceDemo.Base.BaseTest;
import com.qa.SauceDemo.Constants.LabelConstants;

public class InventoryPageTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void verifyAddToCart()
	{
		inventoryPage= loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		
		String actualUrl= inventoryPage.pageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("inventoryUrl"));
		
		String pageHeaderactual = inventoryPage.productsHeader();
		Assert.assertEquals(pageHeaderactual,LabelConstants.PRODUCTS_HEADER);
		
	   boolean addToCart=inventoryPage.addToCart();
	   Assert.assertEquals(addToCart, true);
		
	}
	
	@Ignore
	@Test()
	public void verifyRemoveFromCart()
	{
	  boolean removeFromCart = inventoryPage.removeFromCart();
	  Assert.assertEquals(removeFromCart, true);
	  
	}
	
	@Test(priority = 2)
	public void sortByAToZ()
	{
		boolean isProductsSorted = inventoryPage.sortProductsAToZ();
		Assert.assertTrue(isProductsSorted);
	}
	
	@Test(priority = 3)
	public void sortByZToA()
	{
		boolean isProductsSorted = inventoryPage.sortProductsZToA();
		Assert.assertTrue(isProductsSorted); 
	}
	

}
