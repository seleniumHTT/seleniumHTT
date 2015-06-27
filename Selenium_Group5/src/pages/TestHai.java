package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Selenium;
import common.config;

public class TestHai {

	public static void main(String[] args) {
		TestHai t = new TestHai(new Selenium().getDriver(config.urlLogin));
		
	}
	
	WebDriver driver;
	

	public TestHai(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
		
	
	@FindBy(xpath="//input[@id='mod-login-usernam']")
	public WebElement txt_username;
	
	@FindBy(xpath="//input[@id='mod-login-password']")
	public WebElement txt_password;
	
	@FindBy(xpath="//a[contains(text(),'Log in')]")
	public WebElement btn_login;
}
