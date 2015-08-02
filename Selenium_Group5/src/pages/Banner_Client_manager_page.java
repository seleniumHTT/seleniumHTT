package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;
import common.PageFactory;

public class Banner_Client_manager_page extends AbstractPage{
	WebDriver driver;
	
	public Banner_Client_manager_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	//Action toolbar
	public Banner_Client_add_edit_page clickNewBannerClient(){
		btn_newBannerClient.click();
		return new Banner_Client_add_edit_page(driver);
	}
	
	public Banner_Client_add_edit_page clickEditBannerClient(){
		btn_editBannerClient.click();
		return new Banner_Client_add_edit_page(driver);
	}
	
	public Help_page clickHelpBannerClient(){
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_helpBannerClient.click();
		switchToNextWindow();
		return new Help_page(driver);
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
	
	public void clickCheckInBannerClient(){
		btn_checkInBannerClient.click();
	}
	
	public void searchBannerClient(String bannerClientName){
		txt_search.clear();
		txt_search.sendKeys(bannerClientName);
		btn_search.click();		
	}
	
	//Handle table
	public void clickClientCheckbox(String bannerClientName) {
		String chbXpath = getCellXpath(bannerClientName, 1) + "/input";
		getWebElement(chbXpath).click();
	}
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);		
	}
	
	//Verify
	public boolean isBannerClientExist(String bannerClientName){
		return isElementExist("//a[contains(text(),'"+ bannerClientName + "')]");
		
	}
	
	public boolean isBannerClientCheckedIn(String bannerClientName) {
		String buttonXpath = getCellXpath(bannerClientName, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
	
	public boolean isBannerClientManagerTitleCorrect(){
		return isElementExist(_txt_BannerClientManagerTitle);
	}
			
	//Editor
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_newBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	private WebElement btn_editBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-publish']/a")
	private WebElement btn_publishBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-unpublish']/a")
	private WebElement btn_unpublishBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	private WebElement btn_archiveBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	private WebElement btn_trashBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_helpBannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-checkin']/a")
	private WebElement btn_checkInBannerClient;
		
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement btn_search;
	
	@FindBy(xpath="//select[@name='filter_state']")
	private WebElement cb_filterStatus;
	
	private String _iconCheckedOut = "/a/span[@class='state checkedout']";
	private String _txt_BannerClientManagerTitle = ".//*[@id='toolbar-box']//h2[contains(text(),'Banner Manager: Clients')]";
	
}
