package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;

public class Banner_Category_manager_page extends AbstractPage {
	WebDriver driver;
	
	public Banner_Category_manager_page(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	//Toolbar action
	public Banner_Category_add_edit_page clickNewCategories(){
		btn_New.click();
		waitForPageLoaded(driver);
		return new Banner_Category_add_edit_page(driver);
	}
	
	public void searchBannerCategory(String categoryName){
		txt_search.clear();
		txt_search.sendKeys(categoryName);
		btn_search.click();
		waitForPageLoaded(driver);		
	}
	
	//Form action
	public void clickBannerCategoryCheckbox(String categoryName){
		String chbXpath = getCellXpath(categoryName, 1) + "/input";
		getWebElement(chbXpath).click();
		waitForPageLoaded(driver);
	}	
	
	//Verify
	public boolean isBannerCategoryExist(String categoryName){
		return isElementExist(".//*[@id='adminForm']/table/tbody//a[contains(text(),'"+categoryName+"')]");
	}
	
	//Editor
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_New;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;

}
