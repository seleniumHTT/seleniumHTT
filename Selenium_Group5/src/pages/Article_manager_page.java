package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;

public class Article_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Article_manager_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchArticle(String articleTitle) {
		txt_search.clear();
		txt_search.sendKeys(articleTitle);
		btn_search.click();
	}
	
	//Action toolbar
	public Article_add_edit_page clickNewArticle() {
		btn_newArticle.click();
		return new Article_add_edit_page(driver);
	}
	
	public Article_add_edit_page clickEditArticle() {
		btn_editArticle.click();
		return new Article_add_edit_page(driver);
	}
	
	public Article_manager_page clickTrashArticle() {
		btn_trash.click();
		return new Article_manager_page(driver);
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
		String currentStatus = driver.findElement(By.xpath(buttonXpath)).getAttribute("innerHTML");
		
		if(expectedStatus.equals(currentStatus)) {
			return true;
		}
		return false;
	}
	
	//Handle table
	public void clickArticleCheckbox(String articleTitle) {
		String chbXpath = getCellXpath(articleTitle, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void clickArrowOrdering(String articleTitle, String updown) {		
		if(updown.equals("down")) {
			String downXpath = getCellXpath(articleTitle, 6) + "//a[@title='Move Down']";
			getWebElement(downXpath).click();
		} else if (updown.equals("up")) {
			String upXpath = getCellXpath(articleTitle, 6) + "//a[@title='Move Up']";
			getWebElement(upXpath).click();
		}
	}	
	
	public void clickChangeStatus(String articleTitle) {
		String buttonXpath = getCellXpath(articleTitle, 3) + "/a";
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
	WebElement btn_newArticle;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	WebElement btn_editArticle;
	
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
