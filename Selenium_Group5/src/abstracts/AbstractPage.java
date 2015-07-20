package abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.Article_manager_page;
import pages.Contact_manager_page;
import pages.Weblink_manager_page;
import pages.Banner_Client_manager_page;

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
	
	public void selectCombobox(String xpath) {
		getWebElement(xpath).click();
	}
	
	public void selectComboboxByXpath(String valueXpath, String value) {
		getWebElement(String.format(valueXpath, value)).click();
	}
	public void mouseHover(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}	
	
	
	//Navigate between pages
	public Article_manager_page clickArticleManagerMenu() {
		selectMenu("Content/Article Manager").click();
		return new Article_manager_page(driver);
	}
	
	public Weblink_manager_page clickWeblinkManagerMenu() {
		selectMenu("Components/Weblinks").click();
		return new Weblink_manager_page(driver);
	}
	
	public Contact_manager_page clickContactManagerMenu() {
		selectMenu("Components/Contacts").click();
		return new Contact_manager_page(driver);
	}
	
	public Banner_Client_manager_page clickBannerClientManagerMenu() {
		selectMenu("Components/Banners/Clients").click();
		return new Banner_Client_manager_page(driver);
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
		//sleep to menu appears
		sleep(500);
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
