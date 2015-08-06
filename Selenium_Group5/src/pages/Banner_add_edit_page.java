package pages;

import abstracts.AbstractPage;
import common.PageFactory;
import common.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Banner_add_edit_page extends AbstractPage{
	WebDriver driver;	

	public Banner_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	//Form action
	public void enterData(String bannerName, String alias, String category, String status, String client){
		if(bannerName != null && bannerName!=""){
			txt_bannerName.clear();
			txt_bannerName.sendKeys(bannerName);
		}
			
		if(alias!=null && alias!=""){
			txt_alias.clear();
			txt_alias.sendKeys(alias);
		}

		if(category != null && category != "") {
			category = "- " + category;
			selectCombobox(cb_category, category);
			waitForPageLoaded(driver);
		}
			
		if(status != null && status != "") {
			selectCombobox(cb_status, status);
			waitForPageLoaded(driver);
		}
		
		if(client != null && client != "") {
			selectCombobox(cb_client, client);
			waitForPageLoaded(driver);
		}
			
	}	
	
	//Toolbar action
	public Banner_manager_page clickSaveClose(){
		btn_saveClose.click();
		waitForPageLoaded(driver);
		return new Banner_manager_page(driver);
	}
		
	public Help_page clickHelpPage(){
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		sleep(config.getShortTime());
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	public Banner_manager_page clickCancel(){
		btn_cancel.click();
		waitForPageLoaded(driver);
		return new Banner_manager_page(driver);
	}
	
	public void clickSave(){
		btn_save.click();
		waitForPageLoaded(driver);
	}
	
	public void clickSaveNew(){
		btn_saveNew.click();
		waitForPageLoaded(driver);
	}
	
	public void clickSaveCopy(){
		btn_savecopy.click();
		waitForPageLoaded(driver);
	}
	
	public void refreshPage(){
		driver.navigate().refresh();
		waitForPageLoaded(driver);
	}
	
	//Verify action
	public boolean isBannerNameHighlighed(){
		return isElementExist(_txt_nameInvalid);
	}

	public boolean isNewBannerPageOpening(){
		return isElementExist(_txt_newBannerTitle);
	}
	
	public boolean isEditBannerPageOpening(){
		return isElementExist(_txt_editBannertitle);
	}
	
	//Editor		
	@FindBy(xpath="//input[@id ='jform_name']")
	private WebElement txt_bannerName;
	
	@FindBy(xpath="//input[@id='jform_alias']")
	private WebElement txt_alias;
	
	@FindBy(xpath="//select[@id='jform_catid']")
	private WebElement cb_category;
	
	@FindBy(xpath="//select[@id='jform_state']")
	private WebElement cb_status;
	
	@FindBy(xpath="//select[@id='jform_cid']")
	private WebElement cb_client;
	
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_saveClose;
	
	@FindBy(xpath="//li[@id='toolbar-save-new']/a")
	private WebElement btn_saveNew;
	
	@FindBy(xpath="//li[@id='toolbar-save-copy']/a")
	private WebElement btn_savecopy;
	
	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	private WebElement btn_save;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_help;
	
	@FindBy(xpath="//li[@id='toolbar-cancel']/a")
	private WebElement btn_cancel;		
	
	private String _txt_nameInvalid = "//input[@id='jform_name' and @aria-invalid='true']";
	private String _txt_newBannerTitle = ".//*[@id='toolbar-box']/div/div[2]/h2[contains (text(),'Banner Manager: New Banner')]";
	private String _txt_editBannertitle = ".//*[@id='toolbar-box']/div/div[2]/h2[contains (text(),'Banner Manager: Edit Banner')]";
	
}
