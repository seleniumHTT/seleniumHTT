package common;

import org.openqa.selenium.WebDriver;

import pages.*;

public class PageFactory {


	public static Login_page getLoginPage() {
		driver = config.getDriver();
		driver.get(AppData.getUrlLogin());
		return new Login_page(driver);
	}

	public static Admin_page getAdminPage() {
		driver = config.getDriver();
		return new Admin_page(driver);
	}

	public static WebDriver getDriver() {
		return config.getDriver();
	}

	
	
	private static WebDriver driver = null;
	private static String parentWindow = null;
	
	public static String getParentWindow() {
		return parentWindow;
	}

	public static void setParentWindow(String parentWindow) {
		PageFactory.parentWindow = parentWindow;
	}
	

}
