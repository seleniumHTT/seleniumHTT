package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactory;
import common.config;
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
		waitForPageLoaded(driver);
	}
	
	//Action toolbar
	public Category_add_edit_page clickNewCategory() {
		btn_newCategory.click();
		waitForPageLoaded(driver);
		return new Category_add_edit_page(driver);
	}
	
	public Category_add_edit_page clickEditCategory() {
		btn_editCategory.click();
		waitForPageLoaded(driver);
		return new Category_add_edit_page(driver);
	}
	
	public Category_manager_page clickTrashCategory() {
		btn_trash.click();
		waitForPageLoaded(driver);
		return new Category_manager_page(driver);
	}

	public void clickArchiveCategory() {
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
			waitForPageLoaded(driver);
		} else if(status.equals("Unpublish")) {
			btn_unpublish.click();
			waitForPageLoaded(driver);
		}
	}
	
	//Action for Batch process the selected categories table
	public void batchProcess(String accesslevel, String language, String selectparent, String action ){
		if(accesslevel!=null && accesslevel !=""){
			selectCombobox(cb_setAccess, accesslevel);
		}
		
		if(language!=null && language !=""){
			selectCombobox(cb_setLanguage, language);
		}
		
		if(selectparent!=null && selectparent !=""){
			selectCombobox(cb_selectParent, selectparent);
		}
		
		if(action== "Copy"){
			chb_copy.click();
			waitForPageLoaded(driver);
		}
		else{
			chb_move.click();
			waitForPageLoaded(driver);
		}
			
	}
	
	public void clickProcess(){
		btn_process.click();
		waitForPageLoaded(driver);
	}
	
	//Verify
	public boolean isCategoryExist(String categoryTitle) {
		return isElementExist("//a[contains(text(),'"+ categoryTitle +"')]");
	}
	
	//Handle table
	public void clickCategoryCheckbox(String categoryTitle) {
		String chbXpath = getCellXpath(categoryTitle, 1) + "/input";
		getWebElement(chbXpath).click();
		waitForPageLoaded(driver);
	}

	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);
		waitForPageLoaded(driver);
	}
		
	public void filterAccess(String access) {
		selectCombobox(cb_filterAccess, access);
		waitForPageLoaded(driver);
	}
	
	public void filterLanguage(String language) {
		selectCombobox(cb_filterLanguage, language);
		waitForPageLoaded(driver);
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
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	private WebElement btn_archive;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	private WebElement btn_trash;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_help;
	
	@FindBy(xpath="//button[contains (text(),'Process')]")
	private WebElement btn_process;
	
	@FindBy(xpath="//select[@name='filter_published']")
	private WebElement cb_filterStatus;
	
	@FindBy(xpath="//select[@name='filter_language']")
	private WebElement cb_filterLanguage;
	
	@FindBy(xpath="//select[@name='filter_access']")
	private WebElement cb_filterAccess;
	
	@FindBy(xpath="//select[@name='batch[assetgroup_id]']")
	private WebElement cb_setAccess;
	
	@FindBy(xpath="//select[@name='batch[language_id]']")
	private WebElement cb_setLanguage;
		
	@FindBy(xpath="//select[@name='batch[category_id]']")
	private WebElement cb_selectParent; 
	
	@FindBy(xpath="//input[@id='batch[move_copy]m']")
	private WebElement chb_move;
	
	@FindBy(xpath="//input[@id='batch[move_copy]c']")
	private WebElement chb_copy;
		
}
