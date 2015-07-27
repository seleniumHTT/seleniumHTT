package pages;

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
		return getWindowTitle().contains(windowsTitle);
	}
	
	public void closeBackToParentPage() {
		closeWindow();
		driver.switchTo().window(PageFactory.getParentWindow());
	}
	
}
