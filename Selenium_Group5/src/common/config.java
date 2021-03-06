package common;

import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utilities.XMLhelper;

public class config {
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void setup() {
		getBrowser();
		AppData.getData();		
	}
	
	public static int getLongTime() {
		return longTime;
	}

	public static int getMediumTime() {
		return mediumTime;
	}
	
	public static int getShortTime() {
		return shortTime;
	}
	
	public static String getBrowserName() {
		return browser;
	}
	
	public static void getBrowser() {
		getConfig();
		if(browser.equals("chrome")) {
			System.out.println("Chrome is selected");
			System.setProperty("webdriver.chrome.driver", chromePath);			
			driver = new ChromeDriver();
			
		} else if(browser.equals("ie")) {
			System.out.println("Internet explorer is selected");
			System.setProperty("webdriver.ie.driver", iePath);
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
//			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
//			cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			driver = new InternetExplorerDriver(cap);
			
		} else if(browser.equals("firefox")) {
			System.out.println("Firefox is selected");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser not found, set default to Firefox");
			driver = new FirefoxDriver();
		}		
		
		driver.manage().timeouts().implicitlyWait(longTime, TimeUnit.SECONDS);
		
		if(maximizeBrowser) {
			driver.manage().window().maximize();
		}
	}
		
	public static void setImplicitlyWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	private static void getConfig() {
		try {
			XMLhelper xmlHelper = new XMLhelper(filePath);
			
			longTime = Integer.parseInt(xmlHelper.getContentByXpath(_longTime));
			shortTime = Integer.parseInt(xmlHelper.getContentByXpath(_shortTime));
			mediumTime = Integer.parseInt(xmlHelper.getContentByXpath(_mediumTime));
			browser = xmlHelper.getContentByXpath(_browser);			
			maximizeBrowser = xmlHelper.getContentByXpath(_maximizeBrowser).equals("YES");			
			closeBrowser = xmlHelper.getContentByXpath(_closeBrowser).equals("YES");			
			
		} catch (XPathExpressionException e) {			
			e.printStackTrace();
		}		
	}	
	
	public static void tearDown() {
		if(closeBrowser == true) {
			driver.quit();
			System.out.println("AUT Closed");
		}
		System.out.println("Teared down");
	}
	private static WebDriver driver;
	private static String browser;
	private static boolean maximizeBrowser, closeBrowser;  
	private static int shortTime, longTime, mediumTime;
	
	private static final String filePath = "./resources/automation.config.xml";
	private static final String chromePath = "./resources/chromedriver.exe";
	private static final String iePath = "./resources/IEDriverServer.exe";
	private static final String _longTime = "//longTime";
	private static final String _mediumTime = "//mediumTime";
	private static final String _shortTime = "//shortTime";
	private static final String _browser = "//browser";
	private static final String _maximizeBrowser = "//maximizeBrowser";
	private static final String _closeBrowser = "//closeBrowser";
	
}
