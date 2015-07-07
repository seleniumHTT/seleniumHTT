package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Weblink_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Weblink_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchWeblink(String WeblinkTitle) {
		txt_search.clear();
		txt_search.sendKeys(WeblinkTitle);
		btn_search.click();
	}
	
	//Action toolbar
	public Weblink_add_edit_page clickNewWeblink() {
		btn_newWeblink.click();
		return new Weblink_add_edit_page(driver);
	}
	
	public Weblink_add_edit_page clickEditWeblink() {
		btn_editWeblink.click();
		return new Weblink_add_edit_page(driver);
	}
	
	public Weblink_manager_page clickTrashWeblink() {
		btn_trash.click();
		return new Weblink_manager_page(driver);
	}
	
	//Verify
	public boolean isWeblinkExist(String WeblinkTitle) {
		return isElementExist("//a[contains(text(),'"+ WeblinkTitle +"')]");
	}
	
	public boolean isWeblinkLocateAt(String WeblinkTitle, int row) {
		int currentRow = getRowNumber(WeblinkTitle);
		
		if(row == currentRow) {			
			return true;
		}
		return false;		
	}	
	
	public boolean isWeblinkPublished(String WeblinkTitle, String expectedStatus) {
		String buttonXpath = getCellXpath(WeblinkTitle, 3) + "/a/span/span";
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML");
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	//Handle table
	public void clickWeblinkCheckbox(String WeblinkTitle) {
		String chbXpath = getCellXpath(WeblinkTitle, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String WeblinkTitle, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(WeblinkTitle, 6) + "//a[@title='Move Down']";
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(WeblinkTitle, 6) + "//a[@title='Move Up']";
			getWebElement(upXpath).click();
		}
	}	
	
	public void clickChangeStatus(String WeblinkTitle) {
		String buttonXpath = getCellXpath(WeblinkTitle, 3) + "/a";
		getWebElement(buttonXpath).click();
	}
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);		
	}
	
	public void clickOrderingColumn() {		
		lnk_ordering.click();
	}
	
	
	@FindBy(xpath="//input[@id='filter_search']")
	WebElement txt_search;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement btn_search;
	
	@FindBy(xpath="//button[text()='Clear']")
	WebElement btn_clear;
	
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	WebElement btn_newWeblink;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	WebElement btn_editWeblink;
	
	@FindBy(xpath="//li[@id='toolbar-publish']/a")
	WebElement btn_publish;
	
	@FindBy(xpath="//li[@id='toolbar-unpublish']/a")
	WebElement btn_unpublish;
	
	@FindBy(xpath="//li[@id='toolbar-featured']/a")
	WebElement btn_featured;
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	WebElement btn_archive;
	
	@FindBy(xpath="//li[@id='toolbar-checkin']/a")
	WebElement btn_checkin;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	WebElement btn_trash;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	WebElement btn_help;
	
	@FindBy(xpath="//select[@name='filter_published']")
	WebElement cb_filterStatus;
	
	@FindBy(xpath="//a[text()='Ordering']")
	WebElement lnk_ordering;	
		
}
