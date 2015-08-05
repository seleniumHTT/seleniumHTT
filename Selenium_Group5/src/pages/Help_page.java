package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

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
		try {
			waitWindowsTitleDisplay(windowsTitle);
			System.out.println(driver.getTitle());
			return true;
		} catch (TimeoutException ex) {
			System.out.println(driver.getTitle());
			return false;
			
		}
		
	}
	
	public void closeBackToParentPage() {
		driver.close();
		driver.switchTo().window(PageFactory.getParentWindow());
	}
	
}
