package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactory;

import abstracts.AbstractPage;

public class Help_page extends AbstractPage {
	WebDriver driver;
	
	public Help_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}	
	
	public String getWindowTitle() {
		return driver.getTitle();
	}
	
	public boolean isHelpWindowDisplays(String windowsTitle) {		
		return getWindowTitle().contains(windowsTitle);
	}
	
	public void closeBackToParentPage() {
		closeWindow();
		driver.switchTo().window(PageFactory.getParentWindow());
	}
	
}
