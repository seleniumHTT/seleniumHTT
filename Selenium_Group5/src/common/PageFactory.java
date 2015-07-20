package common;

import org.openqa.selenium.WebDriver;

import pages.*;

public class PageFactory {


	public static Login_page getLoginPage() {		
		driver.get(AppData.getUrlLogin());
		return new Login_page(driver);
	}

	public static Admin_page getAdminPage() {		
		return new Admin_page(driver);
	}

	public static WebDriver getDriver() {
		return config.getDriver();
	}

	private static WebDriver driver = config.getDriver();
}
