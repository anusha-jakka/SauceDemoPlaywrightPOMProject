package com.qa.SauceDemo.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;

	Properties prop;

	// Thread local concept implementation to avoid parallel execution issues and
	// the tl variable will have a local value

	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}

	public Page initPlaywrightBrowser(Properties prop) {
		tlPlaywright.set(playwright = Playwright.create());

		String browserName = prop.getProperty("browser").trim();
		String url = prop.getProperty("url");

		switch (browserName.toLowerCase()) {
		case "chromium":

//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));

			break;

		case "firefox":

//			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":

//			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "chrome":

//			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));

			break;

		default:

			System.out.println("Browser not found.Please send the correct browser!!");

			break;
		}

//		browserContext = browser.newContext();
		tlBrowserContext.set(getBrowser().newContext());
//		page = browserContext.newPage();
		tlPage.set(getBrowserContext().newPage());

//		page.navigate(url);
		getPage().navigate(url);

		return getPage();

	}

	public Properties initProp() {

		try {
			FileInputStream file = new FileInputStream("./src/test/resource/Configuration/config.properties");

			prop = new Properties();
			prop.load(file);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}
	
	public static String takeScreenshot()
	{
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);
		
		return base64Path;
	}

}
