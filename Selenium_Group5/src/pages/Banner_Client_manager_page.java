package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abs.AbstractPage;

public class Banner_Client_manager_page extends AbstractPage{
	WebDriver driver;
	
	public Banner_Client_manager_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}

	
	public Banner_Client_add_edit_page clickNewBannerClient(){
		btn_newBannerClient.click();
		return new Banner_Client_add_edit_page(driver);
	}
	
	
	public void searchBannerClient(String bannerClientName){
		txt_search.clear();
		txt_search.sendKeys(bannerClientName);
		btn_search.click();		
	}
	
	public void clickClientCheckbox(String bannerClientName) {
		String chbXpath = getCellXpath(bannerClientName, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	
	public boolean isBannerClientExist(String bannerClientName){
		return isElementExist("//a[contains(text(),'"+ bannerClientName + "')]");
		
	}
	
		
	//Editor
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	WebElement btn_newBannerClient;
		
	@FindBy(xpath="//input[@id='filter_search']")
	WebElement txt_search;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn_search;
}
