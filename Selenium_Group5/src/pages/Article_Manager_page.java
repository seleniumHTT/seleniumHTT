package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Article_Manager_page extends AbstractPage {
	WebDriver driver;
	
	public Article_Manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchArticle(String articleTitle) {
		txt_search.sendKeys(articleTitle);
		btn_search.click();
	}
	
	public boolean isArticleExist(String articleTitle) {
		return isElementExist("//a[contains(text(),'"+ articleTitle +"')]");
	}
	
	@FindBy(xpath="//input[@id='filter_search']")
	WebElement txt_search;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement btn_search;
	
	@FindBy(xpath="//button[text()='Clear']")
	WebElement btn_clear;
}
