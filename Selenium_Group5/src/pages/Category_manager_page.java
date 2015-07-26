package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactory;

import abstracts.AbstractPage;

public class Category_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Category_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchCategory(String categoryTitle) {
		txt_search.clear();
		txt_search.sendKeys(categoryTitle);
		btn_search.click();
	}
	
	//Action toolbar
	public Category_add_edit_page clickNewCategory() {
		btn_newCategory.click();
		return new Category_add_edit_page(driver);
	}
	
	public Category_add_edit_page clickEditCategory() {
		btn_editCategory.click();
		return new Category_add_edit_page(driver);
	}
	
	public Category_manager_page clickTrashCategory() {
		btn_trash.click();
		return new Category_manager_page(driver);
	}
	
	public void checkIn(String contactName) {
		clickCategoryCheckbox(contactName);
		btn_checkin.click();		
	}	
	
	public void clickArchiveCategory() {
		btn_archive.click();
		
	}
	
	public Help_page clickHelpToolbar() {
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	public void clickChangeStatusToolbar(String status) {
		if(status.equals("Publish")) {
			btn_publish.click();
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
		}
	}
	
	//Verify
	public boolean isCategoryExist(String categoryTitle) {
		return isElementExist("//a[contains(text(),'"+ categoryTitle +"')]");
	}
	
	public boolean isCategoryLocateAt(String categoryTitle, int row) {
		int currentRow = getRowNumber(categoryTitle);
		
		if(row == currentRow) {			
			return true;
		}
		return false;		
	}	
	
	public boolean isCategoryPublished(String categoryTitle, String expectedStatus) {
		String buttonXpath = getCellXpath(categoryTitle, 3) + "/a/span/span";
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML").trim();
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	public boolean isCategoryPublic(String categoryTitle, String expectedAccess) {
		String xpathAccess = getCellXpath(categoryTitle, 7);
		String currentAccess = driver.findElement(By.xpath(xpathAccess)).getAttribute("innerHTML").trim();
		if(currentAccess.equals(expectedAccess)) {return true; }
		return false;
	}
	
	public boolean isCategoryCheckedIn(String contactName) {
		String buttonXpath = getCellXpath(contactName, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
	
	public boolean isIdSortedCorrect(String asc_dec) {
		return isNumberSortedCorrect(asc_dec, _rowTable, 12);
	}	
		
	
	//Handle table
	public void clickCategoryCheckbox(String categoryTitle) {
		String chbXpath = getCellXpath(categoryTitle, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String categoryTitle, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(categoryTitle, 6) + _iconMoveDown;
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(categoryTitle, 6) + _iconMoveUp;
			getWebElement(upXpath).click();
		}
	}	
	
	public void clickChangeStatus(String categoryTitle) {
		String buttonXpath = getCellXpath(categoryTitle, 3) + "/a";
		getWebElement(buttonXpath).click();
	}
	
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);		
	}
	
	
	public void filterCategory(String category) {
		selectComboboxByXpath(_categoryValue, category);		
	}
	public void clickOrderingColumn() {		
		lnk_ordering.click();
	}
	
	public void clickIdColumn() {		
		lnk_ID.click();
	}
	
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath="//button[text()='Clear']")
	private WebElement btn_clear;
	
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_newCategory;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	private WebElement btn_editCategory;
	
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
	
	@FindBy(xpath="//a[text()='Ordering']")
	private WebElement lnk_ordering;	
		
	@FindBy(xpath="//a[text()='ID']")
	private WebElement lnk_ID;
	
		
	private String _iconCheckedOut = "/a/span[@class='state checkedout']";
	private String _rowTable = "//table[@class='adminlist']/tbody/tr";
	private String _categoryValue = "//select[@name='filter_category_id']/option[contains(text(), '%s')]";	
	private String _iconMoveUp = "//a[@title='Move Up']";
	private String _iconMoveDown = "//a[@title='Move Down']";
}
