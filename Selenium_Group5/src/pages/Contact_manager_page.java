package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactory;
import common.config;
import abstracts.AbstractPage;

public class Contact_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Contact_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchContact(String contactName) {
		txt_search.clear();
		txt_search.sendKeys(contactName);
		btn_search.click();
		waitForPageLoaded(driver);
	}
	
	//Action toolbar
	public Contact_add_edit_page clickNewContact() {
		btn_newContact.click();
		waitForPageLoaded(driver);
		return new Contact_add_edit_page(driver);
	}
	
	public Contact_add_edit_page clickEditContact() {
		btn_editContact.click();
		waitForPageLoaded(driver);
		return new Contact_add_edit_page(driver);
	}
	
	public Contact_manager_page clickTrashContact() {
		btn_trash.click();
		waitForPageLoaded(driver);
		return new Contact_manager_page(driver);
	}
	
	public void clickArchiveContact() {
		btn_archive.click();
		waitForPageLoaded(driver);
	}
	
	public void clickChangeStatusToolbar(String status) {
		if(status.equals("Publish")) {
			btn_publish.click();
			waitForPageLoaded(driver);
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
			waitForPageLoaded(driver);
		}
	}
	
	public Help_page clickHelpToolbar() {
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		sleep(config.getShortTime());
		switchToNextWindow();		
		return new Help_page(driver);
	}
	
	//Verify
	public boolean isContactExist(String contactName) {
		return isElementExist("//a[contains(text(),'"+ contactName +"')]");
	}
	
	public boolean isContactLocateAt(String contactName, int row) {
		int currentRow = getRowNumber(contactName);
		
		if(row == currentRow) {			
			return true;
		}
		return false;		
	}	
	
	public boolean isContactPublished(String contactName, String expectedStatus) {
		String buttonXpath = getCellXpath(contactName, 4) + "/a/span/span";
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML");
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	public boolean isContactCheckedIn(String contactName) {
		String buttonXpath = getCellXpath(contactName, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
	
	public boolean isContactFeatured(String name, String access) {
		String icoXpath;
		
		if(access.equals("Featured")) {
			icoXpath = getCellXpath(name, 5) + _icoFeatured;
			return isElementExist(icoXpath);
		} else 
			
		if(access.equals("Unfeatured")) {
			icoXpath = getCellXpath(name, 5) + _icoUnFeatured;
			return isElementExist(icoXpath);
		} else {
			System.out.println("Any icon isn't found");
			return false;
		}
	}
	
	public boolean isIdSortedCorrect(String asc_dec) {
		return isNumberSortedCorrect(asc_dec, _rowTable, 10);
	}	
	
	public boolean isContactPublic(String name, String expectedAccess) {
		String xpathAccess = getCellXpath(name, 8);
		String currentAccess = driver.findElement(By.xpath(xpathAccess)).getAttribute("innerHTML").trim();
		if(currentAccess.equals(expectedAccess)) {return true; }
		return false;
	}
	
	//Handle table
	public void clickContactCheckbox(String contactName) {
		String chbXpath = getCellXpath(contactName, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String contactName, String updown) {		
		if(updown.equals("down")) {			
			String downXpath = getCellXpath(contactName, 7) + _iconMoveDown;
			getWebElement(downXpath).click();
			waitForPageLoaded(driver);
		} else if (updown.equals("up")) {			
			String upXpath = getCellXpath(contactName, 7) + _iconMoveUp;
			getWebElement(upXpath).click();
			waitForPageLoaded(driver);
		}
	}	
	
	public void clickChangeFeature(String name) {
		String icoFeatured = getCellXpath(name, 5) + _icoFeatured;
		String icoUnFeatured = getCellXpath(name, 5) + _icoUnFeatured;
		
		if(isElementExist(icoFeatured)) {
			getWebElement(icoFeatured).click();
			waitForPageLoaded(driver);
		} else {
			getWebElement(icoUnFeatured).click();
			waitForPageLoaded(driver);
		}
		
	}	
	
	public void clickChangeStatus(String contactName) {
		String buttonXpath = getCellXpath(contactName, 4) + "/a";
		getWebElement(buttonXpath).click();
		waitForPageLoaded(driver);
	}
	
	public void checkIn(String contactName) {
		clickContactCheckbox(contactName);
		btn_checkin.click();
		waitForPageLoaded(driver);
	}	
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);
		waitForPageLoaded(driver);
	}
	
	public void filterCategory(String category) {
		selectComboboxByXpath(_categoryValue, category);
		waitForPageLoaded(driver);
		
	}	
		
	public void clickOrderingColumn() {		
		lnk_ordering.click();
		waitForPageLoaded(driver);
	}
	
	public void clickIdColumn() {		
		lnk_ID.click();
		waitForPageLoaded(driver);
	}
	
	
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath="//button[text()='Clear']")
	private WebElement btn_clear;
	
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_newContact;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	private WebElement btn_editContact;
	
	@FindBy(xpath="//li[@id='toolbar-publish']/a")
	private WebElement btn_publish;
	
	@FindBy(xpath="//li[@id='toolbar-unpublish']/a")
	private WebElement btn_unpublish;
	
	@FindBy(xpath="//li[@id='toolbar-featured']/a")
	private WebElement btn_featured;
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	private WebElement btn_archive;
	
	@FindBy(xpath="//li[@id='toolbar-checkin']/a")
	private WebElement btn_checkin;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	private WebElement btn_trash;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_help;
	
	@FindBy(xpath="//select[@name='filter_published']")
	private WebElement cb_filterStatus;
		
	@FindBy(xpath="//select[@name='filter_category_id']")
	private WebElement cb_filterCategory;
	
	@FindBy(xpath="//a[text()='ID']")
	private WebElement lnk_ID;

	@FindBy(xpath="//a[text()='Ordering']")
	private WebElement lnk_ordering;
	
	private String _iconCheckedOut = "/a/span[@class='state checkedout']";
	private String _rowTable = "//table[@class='adminlist']/tbody/tr";
	private String _categoryValue = "//select[@name='filter_category_id']/option[contains(text(), '%s')]";
	private String _icoFeatured = "//img[contains(@alt, 'Featured')]";
	private String _icoUnFeatured = "//img[contains(@alt, 'Unfeatured')]";	
	private String _iconMoveUp = "//a[@title='Move Up']";
	private String _iconMoveDown = "//a[@title='Move Down']";
	

	

		
}
