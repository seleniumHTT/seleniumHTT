package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

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
	}
	
	//Action toolbar
	public Contact_add_edit_page clickNewContact() {
		btn_newContact.click();
		return new Contact_add_edit_page(driver);
	}
	
	public Contact_add_edit_page clickEditContact() {
		btn_editContact.click();
		return new Contact_add_edit_page(driver);
	}
	
	public Contact_manager_page clickTrashContact() {
		btn_trash.click();
		return new Contact_manager_page(driver);
	}
	
	public void clickArchiveContact() {
		btn_archive.click();
	}
	
	public void clickChangeStatusToolbar(String status) {
		if(status.equals("Publish")) {
			btn_publish.click();
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
		}
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
	
	//Handle table
	public void clickContactCheckbox(String contactName) {
		String chbXpath = getCellXpath(contactName, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String contactName, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(contactName, 7) + "//a[@title='Move Down']";
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(contactName, 7) + "//a[@title='Move Up']";
			getWebElement(upXpath).click();
		}
	}	
	
	public void clickChangeStatus(String contactName) {
		String buttonXpath = getCellXpath(contactName, 4) + "/a";
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
	WebElement btn_newContact;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	WebElement btn_editContact;
	
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
