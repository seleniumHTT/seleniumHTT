package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactory;

import abstracts.AbstractPage;

public class Weblink_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Weblink_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchWeblink(String weblinkTitle) {
		txt_search.clear();
		txt_search.sendKeys(weblinkTitle);
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
	
	public void clickArchiveWeblink() {
		btn_archive.click();
	}
		
	public void clickChangeStatusToolbar(String status) {
		if(status.equals("Publish")) {
			btn_publish.click();
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
		}
	}
	
	public Help_page clickHelpToolbar() {
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	//Verify
	public boolean isWeblinkExist(String weblinkTitle) {
		return isElementExist("//a[contains(text(),'"+ weblinkTitle +"')]");
	}
	
	public boolean isWeblinkLocateAt(String weblinkTitle, int row) {
		int currentRow = getRowNumber(weblinkTitle);
		
		if(row == currentRow) {			
			return true;
		}
		return false;		
	}	
	
	public boolean isWeblinkPublished(String weblinkTitle, String expectedStatus) {
		String buttonXpath = getCellXpath(weblinkTitle, 3) + "/a/span/span";
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML");
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	public boolean isWeblinkCheckedIn(String weblinkTitle) {
		String buttonXpath = getCellXpath(weblinkTitle, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
	
	//Handle table
	public void clickWeblinkCheckbox(String weblinkTitle) {
		String chbXpath = getCellXpath(weblinkTitle, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String weblinkTitle, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(weblinkTitle, 5) + "//a[@title='Move Down']";
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(weblinkTitle, 5) + "//a[@title='Move Up']";
			getWebElement(upXpath).click();
		}
	}	
	
	public boolean isIdSortedCorrect(String asc_dec) {
		return isNumberSortedCorrect(asc_dec, _rowTable, 9);
	}		
	
	public void checkIn(String name) {
		clickWeblinkCheckbox(name);
		btn_checkin.click();		
	}
	
	public void clickChangeStatus(String weblinkTitle) {
		String buttonXpath = getCellXpath(weblinkTitle, 3) + "/a";
		getWebElement(buttonXpath).click();
	}
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);		
	}
	
	public void filterCategory(String category) {
		selectComboboxByXpath(_categoryValue, category);
		
	}
	
	public void clickIdColumn() {		
		lnk_ID.click();
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
		
	@FindBy(xpath="//select[@name='filter_category_id']")
	WebElement cb_filterCategory;
		
	@FindBy(xpath="//a[text()='ID']")
	WebElement lnk_ID;
	
	String _iconCheckedOut = "/a/span[@class='state checkedout']";
	String _rowTable = "//table[@class='adminlist']/tbody/tr";
	String _categoryValue = "//select[@name='filter_category_id']/option[contains(text(), '%s')]";
}
