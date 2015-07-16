package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {
	
	WebDriver driver;
	
	//
	public Login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public Admin_page login(String username, String password) {
		txt_username.sendKeys(username);
		txt_password.sendKeys(password);
		btn_login.click();
		return new Admin_page(driver);
	}	
	
	@FindBy(xpath="//input[@id='mod-login-username']")
	public WebElement txt_username;
	
	@FindBy(xpath="//input[@id='mod-login-password']")
	public WebElement txt_password;
	
	@FindBy(xpath="//a[contains(text(),'Log in')]")
	public WebElement btn_login;
}
