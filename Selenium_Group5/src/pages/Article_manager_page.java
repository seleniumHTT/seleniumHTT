package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.PageFactory;
import common.config;
import abstracts.AbstractPage;

public class Article_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Article_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchArticle(String articleTitle) {
		waitElementDisplay(_txt_search);
		txt_search.clear();
		txt_search.sendKeys(articleTitle);
		btn_search.click();
		waitForPageLoaded(driver);
	}
	
	//Action toolbar
	public Article_add_edit_page clickNewArticle() {
		btn_newArticle.click();
		waitForPageLoaded(driver);
		return new Article_add_edit_page(driver);
	}
	
	public Article_add_edit_page clickEditArticle() {
		btn_editArticle.click();
		waitForPageLoaded(driver);
		return new Article_add_edit_page(driver);
	}
	
	public Article_manager_page clickTrashArticle() {
		btn_trash.click();
		waitForPageLoaded(driver);
		return new Article_manager_page(driver);
	}
	
	public void checkIn(String contactName) {
		clickArticleCheckbox(contactName);
		btn_checkin.click();		
		waitForPageLoaded(driver);
	}	
	
	public void clickArchiveArticle() {
		btn_archive.click();
		waitForPageLoaded(driver);		
	}
	
	public Help_page clickHelpToolbar() {
		PageFactory.setParentWindow(driver.getWindowHandle());		
		btn_help.click();	
		sleep(config.getShortTime());
		switchToNextWindow();		
		return new Help_page(driver);
	}
	
	public void clickChangeStatusToolbar(String status) {
		if(status.equals("Publish")) {
			btn_publish.click();
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
		}
		waitForPageLoaded(driver);
	}
	
	//Verify
	public boolean isArticleExist(String articleTitle) {
		return isElementExist("//a[contains(text(),'"+ articleTitle +"')]");
	}
	
	public boolean isArticleLocateAt(String articleTitle, int row) {
		int currentRow = getRowNumber(articleTitle);
		
		if(row == currentRow) {			
			return true;
		}
		return false;		
	}	
	
	public boolean isArticlePublished(String articleTitle, String expectedStatus) {
		String buttonXpath = getCellXpath(articleTitle, 3) + "/a/span/span";
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML").trim();
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	public boolean isArticlePublic(String articleTitle, String expectedAccess) {
		String xpathAccess = getCellXpath(articleTitle, 7);
		String currentAccess = driver.findElement(By.xpath(xpathAccess)).getAttribute("innerHTML").trim();
		if(currentAccess.equals(expectedAccess)) {return true; }
		return false;
	}
	
	public boolean isArticleCheckedIn(String contactName) {
		String buttonXpath = getCellXpath(contactName, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
	
	public boolean isIdSortedCorrect(String asc_dec) {
		if(isElementExist(_lnk_ID + "/img")) {
			return isNumberSortedCorrect(asc_dec, _rowTable, 12);
		} return false;
	}	
	
	public boolean isArticleFeatured(String articleTitle, String access) {
		
		String icoXpath;
		
		if(access.equals("Featured")) {
			icoXpath = getCellXpath(articleTitle, 4) + _icoFeatured;
			return isElementExist(icoXpath);
		} else 
			
		if(access.equals("Unfeatured")) {
			icoXpath = getCellXpath(articleTitle, 4) + _icoUnFeatured;
			return isElementExist(icoXpath);
		} else {
			System.out.println("Any icon isn't found");
			return false;
		}
	}
	
	//Handle table
	public void clickArticleCheckbox(String articleTitle) {		
		String chbXpath = getCellXpath(articleTitle, 1) + "/input";
		waitToClick(chbXpath);
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String articleTitle, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(articleTitle, 6) + _iconMoveDown;
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(articleTitle, 6) + _iconMoveUp;
			getWebElement(upXpath).click();
		}
		waitForPageLoaded(driver);
	}	
	
	public void clickChangeStatus(String articleTitle) {
		String buttonXpath = getCellXpath(articleTitle, 3) + "/a";
		waitToClick(buttonXpath);
		getWebElement(buttonXpath).click();
		waitForPageLoaded(driver);
	}
	
	public void clickChangeFeature(String articleTitle) {
		String icoFeatured = getCellXpath(articleTitle, 4) + _icoFeatured;
		String icoUnFeatured = getCellXpath(articleTitle, 4) + _icoUnFeatured;
		
		if(isElementExist(icoFeatured)) {
			waitToClick(icoFeatured);
			getWebElement(icoFeatured).click();
		} else {
			waitToClick(icoUnFeatured);
			getWebElement(icoUnFeatured).click();
		} 
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
		waitElementDisplay("//a[text()='Ordering']");
		lnk_ordering.click();
		waitForPageLoaded(driver);
	}
	
	public void clickIdColumn() {
		lnk_ID.click();
		waitForPageLoaded(driver);
	}
	
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;
	private String _txt_search = "//input[@id='filter_search']";
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath="//button[text()='Clear']")
	private WebElement btn_clear;
	
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_newArticle;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	private WebElement btn_editArticle;
	
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
		
	@FindBy(xpath="//a[contains(text(),'ID')]")
	private WebElement lnk_ID;
	private String _lnk_ID = "//a[contains(text(),'ID')]";
		
	private String _iconCheckedOut = "/a/span[@class='state checkedout']";
	private String _rowTable = "//table[@class='adminlist']/tbody/tr";
	private String _categoryValue = "//select[@name='filter_category_id']/option[contains(text(), '%s')]";
	private String _icoFeatured = "//img[contains(@alt, 'Featured')]";
	private String _icoUnFeatured = "//img[contains(@alt, 'Unfeatured')]";	
	private String _iconMoveUp = "//a[@title='Move Up']";
	private String _iconMoveDown = "//a[@title='Move Down']";
}
