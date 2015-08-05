package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstracts.AbstractPage;
import common.PageFactory;
import common.config;

public class Banner_manager_page extends AbstractPage{
	WebDriver driver;
	
	public Banner_manager_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	//Action toolbar
	public Banner_add_edit_page clickNewBanner(){
		btn_newBanner.click();
		waitForPageLoaded(driver);
		return new Banner_add_edit_page(driver);
	}
	
	public Banner_add_edit_page clickEditBanner(){
		btn_editBanner.click();
		waitForPageLoaded(driver);
		return new Banner_add_edit_page(driver);
	}
	
	public Help_page clickHelpBanner(){
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_helpBanner.click();
		sleep(config.getShortTime());
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	public Banner_Client_manager_page switchToBannerClient(){
		lk_bannerClient.click();
		waitForPageLoaded(driver);
		return new Banner_Client_manager_page(driver);
	}
	
	public void clickPublishBanner(){
		btn_publishBanner.click();	
		waitForPageLoaded(driver);
	}
	
	public void clickUnpublishBanner(){
		btn_unpublishBanner.click();
		waitForPageLoaded(driver);
	}
	
	public void clickArchiveBanner(){
		btn_archiveBanner.click();	
		waitForPageLoaded(driver);
	}
	
	public void clickTrashBanner(){
		btn_trashBanner.click();	
		waitForPageLoaded(driver);
	}
	
	public void clickCheckInBanner(){
		btn_checkInBanner.click();
		waitForPageLoaded(driver);
	}
	
	public void searchBanner(String bannerName){
		txt_search.clear();
		txt_search.sendKeys(bannerName);
		btn_search.click();		
		waitForPageLoaded(driver);
	}
	
	//Handle table
	public void clickBannerCheckbox(String bannerName) {
		String chbXpath = getCellXpath(bannerName, 1) + "/input";
		getWebElement(chbXpath).click();
		waitForPageLoaded(driver);
	}
	
	public void filterStatus(String status) {
		selectCombobox(cb_filterStatus, status);	
		waitForPageLoaded(driver);
	}
	
	//Verify
	public boolean isBannerExist(String bannerName){
		return isElementExist("//a[contains(text(),'"+ bannerName + "')]");
		
	}
	
	public boolean isBannerCheckedIn(String bannerName) {
		String buttonXpath = getCellXpath(bannerName, 2) + _iconCheckedOut;
		return !isElementExist(buttonXpath);
	}
			
	//Editor
	@FindBy(xpath=".//*[@id='submenu']//a[contains(text(),'Clients')]")
	private WebElement lk_bannerClient;
	
	@FindBy(xpath="//li[@id='toolbar-new']/a")
	private WebElement btn_newBanner;
	
	@FindBy(xpath="//li[@id='toolbar-edit']/a")
	private WebElement btn_editBanner;
	
	@FindBy(xpath="//li[@id='toolbar-publish']/a")
	private WebElement btn_publishBanner;
	
	@FindBy(xpath="//li[@id='toolbar-unpublish']/a")
	private WebElement btn_unpublishBanner;
	
	@FindBy(xpath="//li[@id='toolbar-archive']/a")
	private WebElement btn_archiveBanner;
	
	@FindBy(xpath="//li[@id='toolbar-trash']/a")
	private WebElement btn_trashBanner;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_helpBanner;
	
	@FindBy(xpath="//li[@id='toolbar-checkin']/a")
	private WebElement btn_checkInBanner;
		
	@FindBy(xpath="//input[@id='filter_search']")
	private WebElement txt_search;
	
	@FindBy(xpath="//button[text()='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath="//select[@name='filter_state']")
	private WebElement cb_filterStatus;
	
	private String _iconCheckedOut = "/a/span[@class='state checkedout']";
	
}
