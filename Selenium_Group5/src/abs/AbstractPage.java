package abs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.Article_manager_page;
import pages.Weblink_manager_page;

public abstract class AbstractPage {
	WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	
	public WebElement getWebElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public String getCellXpath(String objectTitle, int colNumber) {
		return "//a[contains(text(), '" + objectTitle + "')]/ancestor::tr/td["+ colNumber +"]";
	}
	
	
	public int getRowNumber(String objectTitle) {
		return driver.findElements(By.xpath("//a[contains(text(), '"+ objectTitle +"')]/ancestor::tr/preceding-sibling::*")).size() + 1;
	}
	//Interact methods
	public void selectCombobox(WebElement combobox, String value) {		
		new Select(combobox).selectByVisibleText(value);
	}
	
	public void mouseHover(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}	
	
	
	public Article_manager_page clickArticleManagerMenu() {
		selectMenu("Content/Article Manager").click();
		return new Article_manager_page(driver);
	}
	
	public Weblink_manager_page clickWeblinkManagerMenu() {
		selectMenu("Components/Weblinks").click();
		return new Weblink_manager_page(driver);
	}
	
	//Select menu, split by '/'
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
	
	
	//Javascript
	public String getTextByScript(String xpath) {
		String script = "return document.evaluate(\""+ xpath +"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;";
		try {
			return (String)((JavascriptExecutor)driver).executeScript(script);
		} catch (Exception e) {
			return null;
		}
	}
}
