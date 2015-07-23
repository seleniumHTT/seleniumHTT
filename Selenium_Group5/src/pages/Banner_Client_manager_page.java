package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;

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
	
	public Banner_Client_add_edit_page clickEditBannerClient(){
		btn_editBannerClient.click();
		return new Banner_Client_add_edit_page(driver);
	}
	
	public void clickPublishBannerClient(){
		btn_publishBannerClient.click();		
	}
	
	public void clickUnpublishBannerClient(){
		btn_unpublishBannerClient.click();		
	}
	
	public void clickArchiveBannerClient(){
		btn_archiveBannerClient.click();		
	}
	
	public void clickTrashBannerClient(){
		btn_trashBannerClient.click();		
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
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);		
	}
	
	public boolean isBannerClientExist(String bannerClientName){
		return isElementExist("//a[contains(text(),'"+ bannerClientName + "')]");
		
	}
	
		
	//Editor
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	WebElement btn_newBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	WebElement btn_editBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-publish']/a")
	WebElement btn_publishBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-unpublish']/a")
	WebElement btn_unpublishBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	WebElement btn_archiveBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	WebElement btn_trashBannerClient;
		
	@FindBy(xpath="//input[@id='filter_search']")
	WebElement txt_search;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn_search;
	
	@FindBy(xpath="//select[@name='filter_state']")
	WebElement cb_filterStatus;
}
