package pages;

import abstracts.AbstractPage;
import common.PageFactory;
import common.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Banner_Client_add_edit_page extends AbstractPage{
	WebDriver driver;	

	public Banner_Client_add_edit_page(WebDriver driver) {
		super(driver);	
		this.driver = driver;
	}
	
	//Form actions
	public void enterData(String clientName, String contactName, String contactEmail, String status){
		if(clientName != null && clientName!=""){
			txt_clientName.clear();
			txt_clientName.sendKeys(clientName);
		}
			
		if(contactName!=null && contactName!=""){
			txt_contactName.clear();
			txt_contactName.sendKeys(contactName);
		}

		if(contactEmail!=null && contactEmail!=""){
			txt_contactEmail.clear();
			txt_contactEmail.sendKeys(contactEmail);
		}
			
		if(status != null && status != "") {
			selectCombobox(cb_status, status);
		}
			
	}	
	
	//Toolbar action
	public Banner_Client_manager_page clickSaveClose(){
		btn_saveClose.click();
		waitForPageLoaded(driver); 
		return new Banner_Client_manager_page(driver);
	}
		
	public Help_page clickHelpPage(){
		PageFactory.setParentWindow(driver.getWindowHandle());
		btn_help.click();
		sleep(config.getShortTime());
		switchToNextWindow();
		return new Help_page(driver);
	}
	
	public Banner_Client_manager_page clickCancel(){
		btn_cancel.click();
		waitForPageLoaded(driver);
		return new Banner_Client_manager_page(driver);
	}
	
	public void clickSave(){
		btn_save.click();
		waitForPageLoaded(driver);
	}
	
	public void clickSaveCopy(){
		btn_saveCopy.click();
		waitForPageLoaded(driver);
	}
	
	public void clickSaveNew(){
		btn_saveNew.click();
		waitForPageLoaded(driver);
	}
	
	public void refreshPage(){
		driver.navigate().refresh();		
		waitForPageLoaded(driver);
	}
	
	//Verify action
	public boolean isContactNameHighlighed(){
		return isElementExist(_txt_contactNameInvalid);
	}
	
	public boolean isContactEmailHighlighed(){
		return isElementExist(_txt_contactEmailInvalid);
	}	
	
	public boolean isNewClientPageOpening(){
		return isElementExist(_txt_newClientTitle);
	}
	
	public boolean isEditClientPageOpening(){
		return isElementExist(_txt_editClientitle);
	}
	
	//Editor		
	@FindBy(xpath="//input[@id = 'jform_name']")
	private WebElement txt_clientName;
	
	@FindBy(xpath="//input[@id='jform_contact']")
	private WebElement txt_contactName;
	
	@FindBy(xpath="//input[@id='jform_email']")
	private WebElement txt_contactEmail;
	
	@FindBy(xpath="//select[@id='jform_state']")
	private WebElement cb_status;
	
	@FindBy(xpath="//li[@id='toolbar-save']/a")
	private WebElement btn_saveClose;
	
	@FindBy(xpath="//li[@id='toolbar-save-new']/a")
	private WebElement btn_saveNew;
	
	@FindBy(xpath="//li[@id='toolbar-apply']/a")
	private WebElement btn_save;
	
	@FindBy(xpath="//li[@id='toolbar-save-copy']/a")
	private WebElement btn_saveCopy;
	
	@FindBy(xpath="//li[@id='toolbar-help']/a")
	private WebElement btn_help;
	
	@FindBy(xpath="//li[@id='toolbar-cancel']/a")
	private WebElement btn_cancel;
	
	private String _txt_contactNameInvalid = "//input[@id='jform_contact' and @aria-invalid='true']";
	private String _txt_contactEmailInvalid = "//input[@id='jform_email' and @aria-invalid='true']";
	private String _txt_newClientTitle = ".//*[@id='toolbar-box']/div/div[2]/h2[contains (text(),'Banner Manager: New Client')]";
	private String _txt_editClientitle = ".//*[@id='toolbar-box']/div/div[2]/h2[contains (text(),'Banner Manager: Edit Client')]";
	
}
