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
	
	
	//Select menu item, split by '/'
	public WebElement selectMenu(String menu) {
		String menuXpath = "";
		WebElement eMenu = null;
		
		String[] arrMenu = menu.split("/");
		
		if(arrMenu.length == 1) {
			menuXpath = "//ul[@id='menu']/li/a[text()='"+ menu +"']";
			eMenu = getWebElement(menuXpath);
			
		} else {
			menuXpath = "//ul[@id='menu']/li/a[text()='"+ arrMenu[0] +"']";
			eMenu = getWebElement(menuXpath);
			mouseHover(eMenu);
			
			for (int i = 0; i < arrMenu.length - 1; i++) {				 
				 menuXpath = menuXpath + "/../ul/li/a[text()='"+ arrMenu[i+1] +"']";
				 eMenu = getWebElement(menuXpath);
				 mouseHover(eMenu);			 
				 //sleep to menu appears
				 sleep(500);
			}
		}
		return eMenu;
	}
	
	//Click main buttons
	public Article_add_page clickAddNewArticle() {
		btn_addNewArticle.click();		
		return new Article_add_page(driver);		
	}

	public Article_Manager_page clickArticleManager() {
		btn_addNewArticle.click();		
		return new Article_Manager_page(driver);		
	}
	
	@FindBy(xpath="//span[contains(text(),'Add New Article')]")
	WebElement btn_addNewArticle;
	
	@FindBy(xpath="//span[contains(text(),'Article Manager')]")
	WebElement btn_articleManager;
	
	@FindBy(xpath="//span[contains(text(),'Category Manager')]")
	WebElement btn_categoryManager;
	
	@FindBy(xpath="//span[contains(text(),'Media Manager')]")
	WebElement btn_mediaManager;
	
	
}
