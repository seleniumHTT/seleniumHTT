package abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.config;
import pages.*;

public abstract class AbstractPage {
	WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	
	public WebElement getWebElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getWebElement(String xpath, int timeOut) {
		WebDriverWait wait =  new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}
	
	public String getCellXpath(String objectTitle, int colNumber) {
		return String.format(_cell, objectTitle, colNumber);
	}
	
	public String getInnerText(String xpath) {
		return driver.findElement(By.xpath(xpath)).getAttribute("innerHTML").trim();
	}
	
	public int getRowNumber(String objectTitle) {
		String previousRows = String.format(_previousRows, objectTitle);
		config.setImplicitlyWait(config.getMediumTime());
		int rows = driver.findElements(By.xpath(previousRows)).size() + 1;
		config.setImplicitlyWait(config.getLongTime());
		return rows;
	}
	
	//Interact methods
	public void selectCombobox(WebElement combobox, String value) {		
		new Select(combobox).selectByVisibleText(value);
	}
	
	public void selectCombobox(String xpath) {
		getWebElement(xpath).click();
	}
	
	public void selectComboboxByXpath(String valueXpath, String value) {
		getWebElement(String.format(valueXpath, value)).click();
	}
	public void mouseHover(WebElement e) {
		if(!config.getBrowserName().equals("ie")) {
			Actions action = new Actions(driver);
			action.moveToElement(e).perform();
			//sleep between the moving
			sleep(1/2);
		}
	}	
	
	
	//Navigate between pages
	public Article_manager_page clickArticleManagerMenu() {
		clickMenu(menuArticleManager);		
		return new Article_manager_page(driver);
	}	
	
	public Weblink_manager_page clickWeblinkManagerMenu() {		
		clickMenu(menuWeblinkManager);
		return new Weblink_manager_page(driver);
	}
	
	public Contact_manager_page clickContactManagerMenu() {		
		clickMenu(menuContactManager);
		return new Contact_manager_page(driver);
	}
	
	public Banner_manager_page clickBannerManagerMenu() {
		clickMenu(menuBannerManager);
		return new Banner_manager_page(driver);
	}
	
	public Banner_Client_manager_page clickBannerClientManagerMenu() {
		clickMenu(menuBannerClientManager);
		return new Banner_Client_manager_page(driver);
	}
	
	public Banner_Category_manager_page clickBannerCategoryManagerMenu() {
		clickMenu(menuBannerCategoryManager);
		return new Banner_Category_manager_page(driver);
	}
	
	public Category_manager_page clickCategoryManagerMenu() {		
		clickMenu(menuCategoryManager);
		return new Category_manager_page(driver);
	}
	
	public void clickMenu(String menu) {
		String currentUrl = driver.getCurrentUrl();
		do {			
			if(config.getBrowserName().equals("ie")) {
				clickByScript(getSelectedMenu(menu));
			} else {
				getSelectedMenu(menu).click();				
			}
			if(currentUrl == driver.getCurrentUrl()) {
				//wait for click again
				sleep(config.getMediumTime());
			}
		} while (currentUrl == driver.getCurrentUrl());
	}
	
	//Select menu, split by '/'
	public WebElement getSelectedMenu(String menu) {
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
			}
		}		
		return eMenu;
		 
	}

	
	//check number sorting
	public boolean isNumberSortedCorrect(String asc_dec, String xpathRow, int col) {
		
		int rows = driver.findElements(By.xpath(xpathRow)).size();
		int num1, num2;
		int check = 0;
		
		if(rows>1) {
			if(asc_dec.equals("asc")) {
				for (int i = 1; i <= rows - 1; i++) {
					num1 = getNumberInCell(xpathRow, i, col);
					num2 = getNumberInCell(xpathRow, i + 1, col);	
					
					if(num1 > num2) { check++;}				
				}
			}
			
			if(asc_dec.equals("dec")) {
				for (int i = 1; i <= rows - 1; i++) {
					num1 = getNumberInCell(xpathRow, i, col);
					num2 = getNumberInCell(xpathRow, i + 1, col);	
					
					if(num1 < num2) { check++;}				
				}
			}
		} else {
			System.out.println("Rows < 1, cannot check");
			check ++;
		}
		
		return check == 0;
	}
	
	public int getNumberInCell(String xpathRowTable, int row, int col) {
		return Integer.parseInt(driver.findElement(By.xpath(xpathRowTable+ "["+ row +"]/td["+ col +"]")).getText());
	}
	//Check methods
	public boolean isMessageDisplay(String msg) {
		return isElementExist(String.format(_systemMsg, msg));
	}	
	
	public boolean isElementExist(String xpath) {
		//config.setImplicitlyWait(config.getMediumTime());
		boolean check = false;
		try {
			waitElementDisplay(xpath);
			check = true;
		} catch(TimeoutException ex) {
			System.out.println("Element not found");
			check = false;
		}		
		config.setImplicitlyWait(config.getLongTime());
		return check;
	}
	
	//Wait, sleep methods
	public void sleep(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	//Sleep in some special cases for IE
	public void sleepIE() {
		if(config.getBrowserName().equals("ie")) {
			System.out.println("Sleep for IE special case: " + config.getShortTime() + " second(s)");
			sleep(config.getShortTime());
		}
	}
	
	public void waitElementDisplay(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, config.getMediumTime());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		config.setImplicitlyWait(config.getLongTime());
	}
	
	public void waitToClick(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, config.getMediumTime());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		config.setImplicitlyWait(config.getLongTime());
	}
	
	public void waitForPageLoaded(WebDriver driver) {

	     ExpectedCondition<Boolean> expectation = new
		 ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,60);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	              System.out.println("Timed out!!!");
	      }
	 } 
	
	public void waitWindowsTitleDisplay(String title) {
		WebDriverWait wait = new WebDriverWait(driver, config.getMediumTime());
		wait.until(ExpectedConditions.titleIs(title));
		config.setImplicitlyWait(config.getLongTime());
	}
	
	// Switch to next opened window
	public WebDriver switchToNextWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			// switch focus of WebDriver to the next found window handle (that's your newly opened window)
		    driver.switchTo().window(winHandle); 
		}
		return driver;
	}
	
	//Close current window of page
	public void closeWindow() {
		driver.close();
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
	
	public void clickByScript(WebElement webElement) {		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", webElement);
	}
	
	private String _cell = "//a[contains(text(), '%s')]/ancestor::tr/td[%s]";
	private String _previousRows = "//a[contains(text(), '%s')]/ancestor::tr/preceding-sibling::*";
	private String _systemMsg = ".//*[@id='system-message']//*[contains(text(),'%s')]";	
	private final String menuContactManager = "Components/Contacts";
	private final String menuWeblinkManager = "Components/Weblinks";
	private final String menuBannerManager = "Components/Banners/Banners";
	private final String menuBannerClientManager = "Components/Banners/Clients";
	private final String menuBannerCategoryManager = "Components/Banners/Categories";
	private final String menuCategoryManager = "Content/Category Manager";
	private final String menuArticleManager = "Content/Article Manager";
}
