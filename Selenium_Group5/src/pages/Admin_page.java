package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Admin_page extends AbstractPage {
	WebDriver driver;
	
	public Admin_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}	
	
	
	
	
	//Click main buttons
	public Article_add_edit_page clickAddNewArticle() {
		btn_addNewArticle.click();		
		return new Article_add_edit_page(driver);		
	}
	
	//Select menu item, split by '/'
	
	@FindBy(xpath="//span[contains(text(),'Add New Article')]")
	WebElement btn_addNewArticle;
	
	@FindBy(xpath="//span[contains(text(),'Article Manager')]")
	WebElement btn_articleManager;
	
	@FindBy(xpath="//span[contains(text(),'Category Manager')]")
	WebElement btn_categoryManager;
	
	@FindBy(xpath="//span[contains(text(),'Media Manager')]")
	WebElement btn_mediaManager;
	
	
}
