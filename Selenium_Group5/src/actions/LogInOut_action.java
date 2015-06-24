package actions;

import org.openqa.selenium.WebDriver;

import pages.Login_page;

public class LogInOut_action {
	WebDriver driver;
	Login_page loginPage;
	
	public LogInOut_action (WebDriver driver) {
		this.driver = driver;
		loginPage = new Login_page(driver);
	}	
	
	public void login(String username, String password) {
		loginPage.txt_username.sendKeys(username);
		loginPage.txt_password.sendKeys(password);
		loginPage.btn_login.click();
	}
}
