package abs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractPage {
	WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	
	public WebElement getWebElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	//Interact method
	public void selectCombobox(WebElement combobox, String value) {		
		new Select(combobox).selectByVisibleText(value);
	}
	
	public void mouseHover(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}	
	
	//Check methods
	public boolean isMessageDisplay(String msg) {
		return isElementExist(".//*[@id='system-message']//*[contains(text(),'"+ msg +"')]");
	}	
	
	public boolean isElementExist(String xpath) {
		return !driver.findElements(By.xpath(xpath)).isEmpty();
	}
	
	//Wait, sleep methods
	public void sleep(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
}
